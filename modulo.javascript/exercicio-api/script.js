function buscarPokemon() {
    var form = document.forms.buscaPoke;
    var submitBtDisabled = form.submitBt.disabled;
    submitBtDisabled = true;
    var id = form.id.value;
    fetch("https://pokeapi.co/api/v2/pokemon/"+id)
        .then(response => response.json())
        .then(json => {
            console.log(json);
            var img = document.createElement("img");
            img.src = json.sprites.front_default;
            var p = document.createElement("p");
            p.innerText = (json.name).toUpperCase();
            var divResult = document.getElementById("resultado");
            divResult.innerHTML = null;
            divResult.appendChild(img);
            divResult.appendChild(p);
            submitBtDisabled = false;
        })
        .catch(() => {
            submitBtDisabled = false;
        });
}