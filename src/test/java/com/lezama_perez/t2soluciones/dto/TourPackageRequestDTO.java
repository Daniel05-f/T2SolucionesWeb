package com.lezama_perez.t2soluciones.dto;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class TourPackageRequestDTO {
    @NotBlank(message = "El destino es obligatorio")
    private String destination;


    @Min(value = 1, message = "Mínimo 1 día")
    @Max(value = 30, message = "Máximo 30 días")
    private Integer days;


    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal price;


    private Boolean includesFlight;


    @PositiveOrZero(message = "Los cupos no pueden ser negativos")
    private Integer availableSlots;

}
