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
            
            divResult.innerHTML = "";
            appendPokemon(divResult, json);

            submitBtDisabled = false;
            form.id.disabled = false;
        })
        .catch(() => {
            divResult.innerHTML = "";
            divResult.appendChild(createEl("hr"));
            divResult.appendChild(createEl("span", "Houve um erro ao procurar este Pokémon."));
            submitBtDisabled = false;
            form.id.disabled = false;
        });
}

function buscarPokemonPorCor() {
    var form = document.forms.buscaPokePorCor;
    var corHex = form.cor.value;
    var corRgb = hexToRgb(corHex);

    document.querySelector("body").style.backgroundColor = corHex;

    var divResult = document.getElementById("resultado");
    divResult.innerHTML = "";
    divResult.appendChild(createEl("hr"));
    divResult.appendChild(createEl("span", "Carregando..."));

    var corArray = [{cor: corRgb.r}, {cor: corRgb.g}, {cor: corRgb.b}];
    divResult.innerHTML = "";

    var rowImagens = createEl("div", undefined, "row");
    rowImagens.style.height = "96px";
    var columnImagens = createEl("div", undefined, "col-md-4 col-md-offset-4 text-center");
    columnImagens.appendChild(createEl("hr"));

    var row = createEl("div", undefined, "row");
    for(let i = 0; i < corArray.length; i++) {
        let id = corArray[i].cor;
        let column = createEl("div", undefined, "col-md-4 text-center");
        column.appendChild(createEl("hr"));
        column.appendChild(createEl("span", "Carregando..."));
        fetch("https://pokeapi.co/api/v2/pokemon/"+id)
            .then(response => response.json())
            .then(json => {
                column.innerHTML = "";
                let img = document.createElement("img");
                img.src = json.sprites.front_default;
                img.className += " img-pokemix";
                columnImagens.appendChild(img);
                appendPokemon(column, json);
            })
            .catch(() => {
                column.innerHTML = "";
                column.appendChild(createEl("hr"));
                column.appendChild(createEl("span", "Houve um erro ao procurar o Pokémon de ID " + id + "."));
            });
        row.appendChild(column);
        rowImagens.appendChild(columnImagens);
    }
    divResult.appendChild(rowImagens);
    divResult.appendChild(row);
}

function appendPokemon(el, json) {
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

    var statsTitle = createEl("h4", "Estatísticas deste Pokémon:");

    var stats = createEl("ul", "", "list-unstyled");
    json.stats.forEach((e)=> {
        let progress = document.createElement("progress");
        progress.max = 100;
        progress.value = e.base_stat;

        let li = createEl("li", `${e.base_stat}% de ${capitalizeFirstLetter(e.stat.name)}`);

        stats.appendChild(progress);
        stats.appendChild(li);
    });

    el.appendChild(createEl("hr"));
    el.appendChild(h1);
    el.appendChild(img);
    el.appendChild(tiposTitle);
    el.appendChild(tipos);
    el.appendChild(statsTitle);
    el.appendChild(stats);
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

function hexToRgb(hex) {
        // script retirado de http://stackoverflow.com/questions/5623838/rgb-to-hex-and-hex-to-rgb
        var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
        hex = hex.replace(shorthandRegex, function(m, r, g, b) {
            return r + r + g + g + b + b;
        });

        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? {
            r: parseInt(result[1], 16),
            g: parseInt(result[2], 16),
            b: parseInt(result[3], 16)
        } : null;
    }