function buscarPokemon() {
    let query = document.getElementById('searchInput').value;

    query = String(query);  // Convertir explícitamente a string

    // Realiza la búsqueda 
    fetch(`/pokemon/buscar?query=${query}`)
        .then(response => response.json())
        .then(data => {
            actualizarPokemons(data);
        })
        .catch(error => console.error('Error al buscar Pokémon:', error));
}

function actualizarPokemons(pokemons) {
    const pokemonContainer = document.querySelector('.row');
    pokemonContainer.innerHTML = ''; // Limpiar la lista

    // Crear cada carta dinamicamente
    pokemons.forEach(pokemon => {
        const pokemonCard = `
            <div class="col d-flex justify-content-center">
                <div class="card text-start shadow-sm carta border-0 shadow-none mt-4" style="width: 200px; max-height: 500px;">
                    <img src="${pokemon.imagen}" class="card-img-top p-2" alt="Imagen de Pokémon" style="background-color: #f2f2f2; max-height: 200px; max-width: 100%; object-fit: contain;">
                    <div class="card-body d-flex flex-column align-items-start">
                        <h3 class="card-title mb-2">${pokemon.nombre}</h3>
                        <p class="card-text mb-2 fw-bolder text-secondary opacity-50">Nº ${pokemon.numPokedex}</p>
                        <div class="container d-flex justify-content-start w-100 g-0" style="gap: 2%;">
                            <span class="badge text-white text-center tipo-${pokemon.tipo1.nombre.toLowerCase()}" style="width: 50%;">${pokemon.tipo1.nombre}</span>
                            ${pokemon.tipo2 ? `<span class="badge text-white text-center tipo-${pokemon.tipo2.nombre.toLowerCase()}" style="width: 50%;">${pokemon.tipo2.nombre}</span>` : ''}
                        </div>
                    </div>
                </div>
            </div>
        `;
        
        pokemonContainer.innerHTML += pokemonCard;
    });
}
