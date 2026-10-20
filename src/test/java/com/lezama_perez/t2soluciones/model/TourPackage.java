package com.lezama_perez.t2soluciones.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String destination;

    @NotNull
    @Column(nullable = false)
    private Integer days;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    private Boolean includesFlight;

    @NotNull
    @Column(nullable = false)
    private Integer availableSlots;
}
