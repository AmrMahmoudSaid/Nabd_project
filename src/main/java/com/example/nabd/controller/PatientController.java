package com.example.nabd.controller;

import com.example.nabd.dtos.AddMedicineDto;
import com.example.nabd.dtos.BasisResponse;
import com.example.nabd.dtos.PatientDto;
import com.example.nabd.service.IPatientService;
import com.example.nabd.utility.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@Tag(
        name = "Patient service apis "
)
public class PatientController {
    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    @Operation(
            summary = "create Patient "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> createPatient(@Valid @RequestBody PatientDto patientDto){
        return new ResponseEntity<>(patientService.createPatient(patientDto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    @Operation(
            summary = "get Patient "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> getPatient(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = AppConstants.DEFAULT_SORT_BY , required = false) String sortBy ,
            @RequestParam(value = "filterType" ,required = false) String filterType ,
            @RequestParam(value = "filterValue" , required = false) String filterValue
    ){
        return ResponseEntity.ok(patientService.getPatient(pageNo,pageSize,sortBy,filterType,filterValue));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    @Operation(
            summary = "get Patient by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> getPatientById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
    @GetMapping("/{id}/medicine")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    @Operation(
            summary = "get Patient medicine"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> getPatientMedicine(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(patientService.getPatientMedicine(id));
    }
    @GetMapping("/{id}/history")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    @Operation(
            summary = "get Patient history"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> getPatientHistory(
            @PathVariable(name = "id") Long id ,
            @RequestParam(name = "year") int year,
            @RequestParam(name = "month") int month){
        return ResponseEntity.ok(patientService.getPatientHistory(id,year,month));
    }
    @GetMapping("/{id}/medicine/all")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    @Operation(
            summary = "get Patient all medicine"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> getAllPatientMedicine(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(patientService.getAllPatientMedicine(id));

    }
    @GetMapping("/{id}/history/dates")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    public ResponseEntity<BasisResponse> getPatientDateOfHHistories(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(patientService.getPatientDateHistory(id));
    }
    @PostMapping("/{patientID}/medicine/{medicineId}")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU','ROLE_NU')")
    @Operation(
            summary = "add Patient medicine"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> addMedicineToPatient(
            @PathVariable(name = "patientID") Long patientId,
            @PathVariable(name = "medicineId") Long medicineId,
            @Valid @RequestBody AddMedicineDto addMedicineDto){
        System.out.println(addMedicineDto.getStartIn());
        return ResponseEntity.ok(patientService.addMedicine(medicineId,patientId,addMedicineDto));

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SU')")
    @Operation(
            summary = "update Patient"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<BasisResponse> updatePatient(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody PatientDto patientDto ){
        return  ResponseEntity.ok(patientService.updatePatient(id,patientDto));
    }
    @PutMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ROLE_SU')")
    @Operation(
            summary = "deactivate patient"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<String> deactivatePatient(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(patientService.deactivatePatient(id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SU')")
    @Operation(
            summary = "delete Patient"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Create"
    )
    public ResponseEntity<String> deletePatient(@PathVariable(name = "id") Long id){
        return  ResponseEntity.ok(patientService.deletePatient(id));
    }
}
