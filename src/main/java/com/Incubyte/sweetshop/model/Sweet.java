package com.Incubyte.sweetshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sweets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false)
    private String category;

    @Column(nullable=false)
    private double price;

    @Column(nullable=false)
    private int quantity;
}
