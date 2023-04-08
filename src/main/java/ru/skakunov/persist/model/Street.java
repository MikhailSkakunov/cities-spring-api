package ru.skakunov.persist.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "streets")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @ToString.Exclude
    private City city;

    @Column
    @OneToMany(mappedBy = "street", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<House> houses = new ArrayList<>();

}
