// exercício 01
function seriesInvalidas(series) {
    var filtraSeriesInvalidas = function(item) {
        let anoAtual = (new Date()).getFullYear();
        let anoInvalido =  item.anoEstreia > anoAtual;
        let campoInvalido = false;

        if(anoInvalido === false)
            campoInvalido = Object.keys(item).some(val => typeof item[val] === "undefined" || item[val] === null);

        return anoInvalido || campoInvalido;
    };
    var seriesInvalidas = series.filter(filtraSeriesInvalidas);
    return `Séries inválidas: ${(seriesInvalidas.map(e => e.titulo)).join(" - ")}`;
}
console.log("series_invalidas", seriesInvalidas(series));

// exercício 02
function filtrarSeriesPorAno(series, ano) {
    return series.filter((e)=>e.anoEstreia>=ano);
}
console.log("filtrar_series_por_ano", filtrarSeriesPorAno(series, 2017));

// exercício 03
function mediaDeEpisodios(series) {
    var numDeEps = (series.map(e=>e.numeroEpisodios)).reduce((a,b) => a+b);
    return numDeEps / (series.length);
}
console.log("media_eps", mediaDeEpisodios(series));

// exercício 04
function procurarPorNome(series, nome) {
    var filtraNome = function(item) {
        return (item.elenco.some((e)=>(e.toLowerCase()).includes(nome.toLowerCase())));
    };
    return series.some(filtraNome);
}
console.log("procurar_por_nome", procurarPorNome(series, "Lucas"));

// exercício 05
function mascadaEmSerie(serie) {
    const valorPorDiretor = 100000;
    const valorPorPessoaDoElenco = 40000;
    return (serie.diretor.length * valorPorDiretor) + (serie.elenco.length * valorPorPessoaDoElenco);
}
console.log("mascada_em_serie", mascadaEmSerie(series[0]));

// exercício 06
function queroGenero(genero) {
    var filtraGenero = function(item) {
        return (item.genero.some((e)=>(e.toLowerCase()).includes(genero.toLowerCase())));
    };
    return series.filter(filtraGenero).map(e=>e.titulo);
}
console.log("quero_genero", queroGenero("CAOS"));

function queroTitulo(titulo) {
    var filtraTitulo = function(item) {
        return (item.titulo.toLowerCase()).includes(titulo.toLowerCase());
    };
    return series.filter(filtraTitulo).map(e=>e.titulo);
}
console.log("quero_titulo", queroTitulo("The"));

// exercício 07
function creditosIlluminatis(serie) {
    var creditos = `--- ${serie.titulo.toUpperCase()} ---`;
    var sortPorLastName = function(a, b) {
        var getLastName = function(nome) {
            var nomes = nome.trim().split(" ");
            return nomes[nomes.length - 1];
        };
        a = getLastName(a);
        b = getLastName(b);
        return a.localeCompare(b);
    };
    var diretores = serie.diretor.sort(sortPorLastName);
    var elenco = serie.elenco.sort(sortPorLastName);
    creditos += "\n  - DIRETORES -  ";
    var adicionarAosCreditos = (e)=> creditos+=`\n ${e}`;
    diretores.forEach(adicionarAosCreditos);
    creditos += "\n  - ELENCO -  ";
    elenco.forEach(adicionarAosCreditos);
    return creditos;
}
console.log("creditos", creditosIlluminatis(series[4]));

// exercício 08
String.prototype.temAbreviacao = function() {
    return this.match(/ [A-Z][.] /g)  !== null;
}

function easterEgg(series) {
    let elencoSerie = series.find(s=>s.elenco.every(e=>e.temAbreviacao())).elenco;
    var easterEgg = elencoSerie.map(e => e.match(/ [A-Z][.] /g)[0][1]).join("");
    return `#${easterEgg}`;
}
console.log("easter_egg", easterEgg(series));