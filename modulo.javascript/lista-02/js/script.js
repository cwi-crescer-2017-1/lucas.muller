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
function easterEgg(series) {
    var easterEgg = "";
    var temAbreviacao = function(item) {
        let indexDoPonto = item.search(/ .\. /);
        if(indexDoPonto === -1) return false;
        indexDoPonto += 2; // pois regex está pegando index da última letra do primeiro nome
        easterEgg += item.slice((indexDoPonto-1), indexDoPonto);
        return true;
    };
    for(let i = 0; i < series.length; i++){
        let s = series[i];
        let todosTemAbreviacao = s.elenco.every(e=> temAbreviacao(e));

        if(todosTemAbreviacao === false) easterEgg = "";
        else break;
    }
    return `#${easterEgg}`;
}
console.log("easter_egg", easterEgg(series));