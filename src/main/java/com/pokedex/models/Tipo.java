package com.pokedex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo")
@Getter  
@Setter  
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public Tipo() {}

    public Tipo(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
