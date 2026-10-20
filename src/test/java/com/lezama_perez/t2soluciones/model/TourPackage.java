package com.lezama_perez.t2soluciones.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.

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
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 150)
    private String destination;


    @Min(value = 1)
    @Max(value = 30)
    @Column(nullable = false)
    private Integer days;


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Boolean includesFlight = false;
}
