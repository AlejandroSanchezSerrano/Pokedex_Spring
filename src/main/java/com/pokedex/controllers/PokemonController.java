package com.pokedex.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokedex.models.Pokemon;
import com.pokedex.models.Tipo;
import com.pokedex.repositories.PokemonRepository;
import com.pokedex.repositories.TipoRepository;

@Controller
public class PokemonController {

    private final PokemonRepository pokemonRepository;
    private final TipoRepository tipoRepository;

    public PokemonController(PokemonRepository pokemonRepository, TipoRepository tipoRepository) {
        this.pokemonRepository = pokemonRepository;
        this.tipoRepository = tipoRepository;
    }

    @GetMapping("/pokemon")
    public String listarPokemons(@RequestParam(defaultValue = "numPokedex") String ordenPor, Model model) {
        List<Pokemon> pokemons = pokemonRepository.findAll(Sort.by(ordenPor));
        model.addAttribute("pokemons", pokemons);
        model.addAttribute("ordenActual", ordenPor);

        // Verificar si el usuario está autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser");
        model.addAttribute("isAuthenticated", isAuthenticated);

        return "pokemon";
    }

    @GetMapping("/")
    public String listarPokemons2(@RequestParam(defaultValue = "numPokedex") String ordenPor, Model model) {
        List<Pokemon> pokemons = pokemonRepository.findAll(Sort.by(ordenPor));
        model.addAttribute("pokemons", pokemons);
        model.addAttribute("ordenActual", ordenPor);
        return "pokemon";
    }

    @GetMapping("/pokemon/nuevo")
    public String mostrarFormularioNuevoPokemon(Model model) {
        model.addAttribute("tipos", tipoRepository.findAll()); // Pasar los tipos disponibles
        return "pokemon-form";
    }

    @PostMapping("/pokemon/agregar")
    public String agregarPokemon(@RequestParam String numPokedex,
            @RequestParam String nombre,
            @RequestParam Long tipo1Id,
            @RequestParam(required = false) Long tipo2Id) {

        // Construir la URL de la imagen
        String imagen = "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/"
                + String.format("%03d", Integer.parseInt(numPokedex)) + ".png";

        // Buscar los tipos en la base de datos usando sus IDs
        Optional<Tipo> tipo1 = tipoRepository.findById(tipo1Id);
        Optional<Tipo> tipo2 = (tipo2Id != null) ? tipoRepository.findById(tipo2Id) : Optional.empty();

        // Verificar que tipo1 existe
        if (tipo1.isEmpty()) {
            return "redirect:/pokemon?error=Tipo1NoEncontrado";
        }

        // Crear el nuevo Pokemon con los tipos encontrados
        Pokemon nuevoPokemon = new Pokemon(null, numPokedex, nombre, tipo1.get(), tipo2.orElse(null), imagen);

        // Guardar el nuevo Pokemon en la base de datos
        pokemonRepository.save(nuevoPokemon);

        return "redirect:/pokemon";
    }

    @GetMapping("/pokemon/buscar")
    @ResponseBody
    public List<Pokemon> buscarPokemons(@RequestParam(required = false) String query, 
                                         @RequestParam(required = false) String numPokedex) {
        if (query != null && !query.isEmpty()) {
            // Si se pasa un nombre, buscar por nombre
            return pokemonRepository.findByNombreContainingIgnoreCase(query);
        } else if (numPokedex != null) {
            // Si se pasa un número de Pokédex, buscar por número de la Pokédex
            return pokemonRepository.findByNumPokedex(numPokedex);
        } else {
            // Si no se pasa ninguno de los parámetros
            return new ArrayList<>();  
        }
    }
    
    @GetMapping("/pokemon/eliminar")
    public String eliminarPokemon(@RequestParam Long id) {
        // Buscar el Pokémon por ID
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        // Si el Pokémon existe, eliminarlo
        if (pokemon.isPresent()) {
            pokemonRepository.delete(pokemon.get());
        }

        return "redirect:/pokemon";
    }

    @GetMapping("/pokemon/editar/{id}")
    public String mostrarFormularioEditarPokemon(@PathVariable Long id, Model model) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        if (pokemon.isPresent()) {
            model.addAttribute("pokemon", pokemon.get());
            model.addAttribute("tipos", tipoRepository.findAll());
            return "pokemon-edit";
        } else {
            return "redirect:/pokemon?error=PokemonNoEncontrado";
        }
    }

    @PostMapping("/pokemon/editar")
    public String editarPokemon(@RequestParam Long id,
                                @RequestParam String numPokedex,
                                @RequestParam String nombre,
                                @RequestParam Long tipo1Id,
                                @RequestParam(required = false) String tipo2Id) {

        Optional<Pokemon> pokemonOptional = pokemonRepository.findById(id);
        if (pokemonOptional.isPresent()) {
            Pokemon pokemon = pokemonOptional.get();

            // Construir la URL de la imagen
            String imagen = "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/"
                    + String.format("%03d", Integer.parseInt(numPokedex)) + ".png";

            // Buscar los tipos en la base de datos usando sus IDs
            Optional<Tipo> tipo1 = tipoRepository.findById(tipo1Id);
            Optional<Tipo> tipo2 = (tipo2Id != null && !"Ninguno".equals(tipo2Id)) ? tipoRepository.findById(Long.parseLong(tipo2Id)) : Optional.empty();

            // Verificar que tipo1 existe
            if (tipo1.isEmpty()) {
                return "redirect:/pokemon?error=Tipo1NoEncontrado";
            }

            // Actualizar los datos del Pokémon
            pokemon.setNumPokedex(numPokedex);
            pokemon.setNombre(nombre);
            pokemon.setTipo1(tipo1.get());
            pokemon.setTipo2(tipo2.orElse(null));
            pokemon.setImagen(imagen);

            // Guardar el Pokémon actualizado en la base de datos
            pokemonRepository.save(pokemon);

            return "redirect:/pokemon";
        } else {
            return "redirect:/pokemon?error=PokemonNoEncontrado";
        }
    }
}