package com.example.nabd.controller;

import com.example.nabd.dtos.BasisResponse;
import com.example.nabd.dtos.SpecializationDto;
import com.example.nabd.service.ISpecializationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/specialization")
public class SpecializationController {
    private final ISpecializationService specializationService;

    public SpecializationController(ISpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SU')")
    public ResponseEntity<BasisResponse> createSpecialization(@Valid  @RequestBody SpecializationDto specializationDto){
        return new ResponseEntity<>(specializationService.createSpecialization(specializationDto) , HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    public ResponseEntity<BasisResponse> getAllSpecialization(){
        return new ResponseEntity<>(specializationService.getSpecializations() , HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SU')")
    public ResponseEntity<BasisResponse> updateSpecialization(
            @PathVariable(name = "id") Long id ,
            @RequestBody SpecializationDto specializationDto){
        return new ResponseEntity<>(specializationService.updateSpecialization(id,specializationDto), HttpStatus.OK);
    }
    @PutMapping("/{specializationId}/patient/{patientId}/deactivate")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    public ResponseEntity<BasisResponse> deactivatemedicine(
            @PathVariable(name = "specializationId") Long specializationId,
            @PathVariable(name = "patientId") Long patientId){
        return ResponseEntity.ok(specializationService.deactivatemedicine(specializationId,patientId));
    }
    @PutMapping("/{specializationId}/patient/{patientId}/activate")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    public ResponseEntity<BasisResponse> activatemedicine(
            @PathVariable(name = "specializationId") Long specializationId,
            @PathVariable(name = "patientId") Long patientId){
        return ResponseEntity.ok(specializationService.activatemedicine(specializationId,patientId));
    }
    @DeleteMapping("/{specializationId}/medicine/{medicineId}")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    public ResponseEntity<String> removeMedicineByspecializationId(
            @PathVariable(name = "specializationId") Long specializationId,
            @PathVariable(name = "medicineId") Long medicineId
    ){
        return ResponseEntity.ok(specializationService.deleteMedicineBySpecialization(medicineId,specializationId));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SU')")
    public ResponseEntity<String> deleteSpecialization(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(specializationService.deleteSpecialization(id), HttpStatus.OK);
    }
}
