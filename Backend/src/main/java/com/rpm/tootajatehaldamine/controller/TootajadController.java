package com.rpm.tootajatehaldamine.controller;

import com.rpm.tootajatehaldamine.model.TootajaRequest;
import com.rpm.tootajatehaldamine.model.TootajaResponse;
import com.rpm.tootajatehaldamine.service.DatabaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tootajad/api")
@RequiredArgsConstructor
public class TootajadController {

    private final DatabaseService databaseService;

    @GetMapping("/getTootajad")
    public ResponseEntity<Page<TootajaResponse>> getTootajad(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(databaseService.getTootajad(pageable));
    }


    @PostMapping("/addTootaja")
    public ResponseEntity<Void> addTootaja(@Valid @RequestBody TootajaRequest request) {
        databaseService.addTootaja(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/editTootaja")
    public ResponseEntity<Void> editTootaja(@Valid @RequestBody TootajaRequest request) {
        databaseService.editTootaja(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteTootaja")
    public ResponseEntity<Void> deleteTootaja(@RequestBody Long id) {
        databaseService.deleteTootaja(id);
        return ResponseEntity.ok().build();
    }
}
