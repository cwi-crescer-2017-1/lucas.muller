function buscarPokemon() {
    var form = document.forms.buscaPoke;
    var submitBtDisabled = form.submitBt.disabled;
    submitBtDisabled = true;
    var id = form.id.value;
    form.id.value = "";
    form.id.disabled = true;

    var divResult = document.getElementById("resultado");
    divResult.innerHTML = "";
    divResult.appendChild(createEl("hr"));
    divResult.appendChild(createEl("span", "Carregando..."));

    fetch("https://pokeapi.co/api/v2/pokemon/"+id)
        .then(response => response.json())
        .then(json => {
            console.log(json);
            var img = document.createElement("img");
            img.src = json.sprites.front_default;
            img.className += " img-thumbnail";

            var h1 = createEl("h3", `${(json.name).toUpperCase()} (${json.id})`);

            var tiposTitle = createEl("h4", "Tipo(s) deste Pokémon:");

            var tipos = createEl("ul", "", "list-unstyled");
            json.types.forEach((e)=> {
                let li = createEl("li", capitalizeFirstLetter(e.type.name));
                tipos.appendChild(li);
            });

            divResult.innerHTML = "";
            divResult.appendChild(createEl("hr"));
            divResult.appendChild(h1);
            divResult.appendChild(img);
            divResult.appendChild(tiposTitle);
            divResult.appendChild(tipos);

            submitBtDisabled = false;
            form.id.disabled = false;
        })
        .catch(() => {
            submitBtDisabled = false;
            form.id.disabled = false;
        });
}

function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

function createEl(el, text, classes) {
    var el = document.createElement(el);
    if(typeof text === "string")
        el.innerText = text;
    if(typeof classes === "string")
        el.className += " " + classes;
    return el;
}