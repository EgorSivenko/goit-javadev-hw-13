package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @Column(length = 50)
    private String id;

    @Column(length = 500, nullable = false, unique = true)
    private String name;
}
