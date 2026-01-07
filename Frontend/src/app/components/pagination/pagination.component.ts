import {Component} from '@angular/core';
import {ApiService} from '../../service/api.service';
import {Router} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'PaginationComponent',
  standalone: true,
  templateUrl:'./pagination.component.html',
  styleUrl: './pagination.component.css',
  imports: [
    NgIf
  ]
})

export class PaginationComponent {
  constructor(public apiService: ApiService, private router: Router) {}

  changePage(page: number, size: number) {
    if (Math.ceil(this.apiService.totalElements / size) <= page) { // when changing to a larger page size the page count gets smaller, so I need to change the page number also
      page = Math.ceil(this.apiService.totalElements / size) - 1
    }
    this.apiService.currentPage = page
    this.router.navigate([], {queryParams: { page: page, size: size }}).catch(error => console.log(error))
    this.apiService.getTootajad()
  }

  getVisiblePageNumbers() {
    const totalPages = this.apiService.totalPages
    const currentPage = this.apiService.currentPage + 1 // +1 so the total and current page numbers match since current starts counting from 0 and total from 1
    let startPage
    let endPage

    if (totalPages <= 3) {
      startPage = 1
      endPage = totalPages
    } else {
      if (currentPage === 1) {
        startPage = 1
        endPage = 3
      } else if (currentPage === totalPages) {
        startPage = totalPages - 2
        endPage = totalPages
      } else {
        startPage = currentPage - 1
        endPage = currentPage + 1
      }
    }

    return Array.from({length: endPage - startPage + 1}, (_, i) => startPage + i)
  }
}
