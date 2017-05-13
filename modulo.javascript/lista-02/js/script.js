// exercício 01
function seriesInvalidas(series) {
    var filtraSeriesInvalidas = function(item) {
        let anoAtual = (new Date()).getFullYear();
        let anoInvalido =  item.anoEstreia > anoAtual;
        let campoInvalido = false;

        if(anoInvalido === false) {
            for(let prop in item) {
                let val = item[prop];
                if(typeof val === "undefined" || val === null) {
                    campoInvalido = true;
                    break;
                }
            }
        }
        return anoInvalido || campoInvalido;
    };
    var seriesInvalidas = series.filter(filtraSeriesInvalidas);
    var titulosSeriesInvalidas=[];
    seriesInvalidas.forEach((e)=>titulosSeriesInvalidas.push(e.titulo));
    return "Séries inválidas: " + (titulosSeriesInvalidas).join(" - ");
}
console.log("series_invalidas", seriesInvalidas(series));

// exercício 02
function filtrarSeriesPorAno(series, ano) {
    return series.filter((e)=>e.anoEstreia>=ano);
}
console.log("filtrar_series_por_ano", filtrarSeriesPorAno(series, 2017));

// exercício 03
function mediaDeEpisodios(series) {
    var somaDeEps = 0;
    var quantDeSeries = series.length;
    series.forEach((e)=>(somaDeEps+=e.numeroEpisodios));
    return somaDeEps/quantDeSeries;
}
console.log("media_eps", mediaDeEpisodios(series));

// exercício 04
function procurarPorNome(series, nome) {
    var filtraNome = function(item) {
        return (item.elenco.filter((e)=>(e.toLowerCase()).includes(nome.toLowerCase()))).length > 0;
    };
    return series.filter(filtraNome);
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
        return (item.genero.filter((e)=>(e.toLowerCase()).includes(genero.toLowerCase()))).length > 0;
    };
    return series.filter(filtraGenero);
}
console.log("quero_genero", queroGenero("CAOS"));

function queroTitulo(titulo) {
    var filtraTitulo = function(item) {
        return (item.titulo.toLowerCase()).includes(titulo.toLowerCase());
    };
    return series.filter(filtraTitulo);
}
console.log("quero_titulo", queroTitulo("The"));


// exercício 07
function creditosIlluminatis(serie) {
    var creditos = `--- ${serie.titulo.toUpperCase()} ---`;
    var sortPorLastName = function(a, b) {
        a = a.split(" ");
        a = a[(a.length-1)];
        b = b.split(" ");
        b = b[(b.length-1)];
        if (a > b) {
            return 1;
        }
        if (a < b) {
            return -1;
        }
        return 0;
    }
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
        let indexDoPonto = item.indexOf(". ");
        if(indexDoPonto === -1) return false;
        easterEgg += item.slice((indexDoPonto-1), indexDoPonto);
        return true;
    }
    for(let i = 0; i < series.length; i++){
        let s = series[i];
        let countAbreviacoes = 0;
        s.elenco.forEach((e)=>{
            if(temAbreviacao(e) === true) countAbreviacoes++;
        });
        if(countAbreviacoes!==s.elenco.length) easterEgg = "";
        else break;
    }
    return `#${easterEgg}`;
}
console.log("easter_egg", easterEgg(series));