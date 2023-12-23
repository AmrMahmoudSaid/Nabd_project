package com.example.nabd.controller;

import com.example.nabd.dtos.BasisResponse;
import com.example.nabd.dtos.ReportMedicineAmountDto;
import com.example.nabd.service.IReportService;
import com.example.nabd.utility.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@Tag(
        name = "report api"
)
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU')")
    ResponseEntity<BasisResponse> createReport(){
        return ResponseEntity.ok(reportService.createReport());
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU')")
    ResponseEntity<BasisResponse> getReport( @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                             @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                             @RequestParam(value = "sortBy" ,defaultValue = "medicine", required = false) String sortBy){
        return ResponseEntity.ok(reportService.getReport(pageNo,pageSize,sortBy));
    }
    @PutMapping("/medicine/amount/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU')")
    ResponseEntity<BasisResponse> editeAmount(@PathVariable(name = "id") Long id,
                                              @RequestBody ReportMedicineAmountDto reportMedicineAmountDto){
        return ResponseEntity.ok(reportService.editeMedicineAmount(id,reportMedicineAmountDto));
    }
    @PutMapping("/medicine/{id}/newmedicine/{new_id}")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU')")
    ResponseEntity<BasisResponse> editeMedicine(@PathVariable(name = "id") Long id,
                                              @PathVariable(name = "new_id") Long newId){
        return ResponseEntity.ok(reportService.editeMedicine(id,newId));
    }
    @DeleteMapping("/medicine/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU')")
    ResponseEntity<BasisResponse> deleteMedicine(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(reportService.deleteMedicine(id));
    }
    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_SU','ROLE_AU')")
    ResponseEntity<BasisResponse> DeleteReport(){
        return ResponseEntity.ok(reportService.deleteReport());
    }
}
