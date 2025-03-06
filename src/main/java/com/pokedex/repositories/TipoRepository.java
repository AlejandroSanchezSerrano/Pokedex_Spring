package com.pokedex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokedex.models.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
