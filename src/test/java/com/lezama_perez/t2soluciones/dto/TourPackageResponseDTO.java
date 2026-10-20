package com.lezama_perez.t2soluciones.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TourPackageResponseDTO {
    private Integer id;
    private String destination;
    private Integer days;
    private BigDecimal price;
    private Boolean includesFlight;
    private Integer availableSlots;
}
