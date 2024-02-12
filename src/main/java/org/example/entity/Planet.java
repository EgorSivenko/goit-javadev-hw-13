package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 500, nullable = false, unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "fromPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> ticketsFrom = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "toPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> ticketsTo = new HashSet<>();
}
