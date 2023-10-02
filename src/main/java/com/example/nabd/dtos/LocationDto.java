package com.example.nabd.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {
    private Long id;
    @NotEmpty(message = "Location name cant be empty")
    private String locationName;
}
