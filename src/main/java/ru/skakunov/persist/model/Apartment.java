package ru.skakunov.persist.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "apartment_number", nullable = false)
    private String apartmentNumber;

    @Column(name = "area", nullable = false)
    private Double area;

    @ManyToOne
    @JoinColumn(name = "house_id")
    @ToString.Exclude
    private House house;

}