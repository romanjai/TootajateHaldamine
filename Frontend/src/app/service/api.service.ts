import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Tootaja} from '../type/tootaja.type';
import {ActivatedRoute} from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class ApiService {
  private apiUrl = '/tootajad/api'
  tootajad: Array<Tootaja> = []
  totalPages = 0
  totalElements = 0
  currentPage = 0
  pageSize = 10
  first = false
  last = false

  constructor(private http: HttpClient, private route: ActivatedRoute) {
    this.route.queryParamMap.subscribe(params => {
      const page = params.get('page')
      const size = params.get('size')
      this.currentPage = page ? Number(page) : 0
      this.pageSize = size ? Number(size) : 10

      this.getTootajad()
    })
  }

  addTootaja(newTootaja: Tootaja) {
    this.http.post(this.apiUrl + '/addTootaja', newTootaja).subscribe({
      next: () => {
        this.getTootajad()
      }
    })
  }

  editTootaja(tootaja: Tootaja) {
    this.http.post(this.apiUrl + '/editTootaja', tootaja).subscribe({
      next: () => {
        this.getTootajad()
      }
    })
  }

  deleteTootaja(id: number) {
    this.http.post(this.apiUrl + '/deleteTootaja', id).subscribe({
      next: () => {
        this.getTootajad()
      }
    })
  }

  getTootajad() {
    this.http.get<any>(this.apiUrl + `/getTootajad?page=${this.currentPage}&size=${this.pageSize}`).subscribe({
      next: res => {
        this.tootajad = res.content
        this.totalPages = res.totalPages
        this.totalElements = res.totalElements
        this.first = res.first
        this.last = res.last
      }
    })
  }
}
