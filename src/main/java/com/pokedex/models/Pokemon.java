package com.pokedex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pokemon")
@Getter  
@Setter  
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numPokedex")  
    private String numPokedex;
    
    private String nombre;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "tipo1", nullable = false)
    private Tipo tipo1;

    @ManyToOne
    @JoinColumn(name = "tipo2")
    private Tipo tipo2;

    public Pokemon() {}

    public Pokemon(Long id, String numPokedex, String nombre, Tipo tipo1, Tipo tipo2, String imagen) {
        this.id = id;
        this.numPokedex = numPokedex;
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.imagen = imagen;
    }
}