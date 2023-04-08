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
@Table(name = "houses")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @ManyToOne
    @JoinColumn(name = "street_id")
    @ToString.Exclude
    private Street street;

    @Column
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Apartment> apartments = new ArrayList<>();

}
