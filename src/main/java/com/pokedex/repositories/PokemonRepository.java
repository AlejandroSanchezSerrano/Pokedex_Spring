package com.pokedex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokedex.models.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByNombreContainingIgnoreCase(String nombre);
    List<Pokemon> findByNumPokedex(String numPokedex);
}
