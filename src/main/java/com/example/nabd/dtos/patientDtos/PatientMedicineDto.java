package com.example.nabd.dtos.patientDtos;

import com.example.nabd.enums.MedicineStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientMedicineDto {
    Long id;
    String medicineName;
    @NotNull
    Long specializationId;
    @NotNull(message = "numberBox is empty")
    int numberBox;
    @NotNull(message = "numberPastille is empty")
    int numberPastille;
    @NotNull(message = "Repetition is empty")
    int Repetition;
    @NotNull(message = "startIn is empty")
    String startIn;
    Long medicineId;
    Long patientId;
    String note;
    String specializationName;
    boolean isActive;
    List<Integer> arrayOfMonths = new ArrayList<>();
}
