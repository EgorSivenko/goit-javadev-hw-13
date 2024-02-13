package org.example.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 500, nullable = false, unique = true)
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "fromPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> ticketsFrom = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "toPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> ticketsTo = new HashSet<>();
}
