console.log("Welcome to the jungle!");

/* -- DIVERSAS FÓRMULAS DE DECLARAR FUNÇÕES -- */

    // forma 1 (maneira normal)
    function somaForma1(a, b) {
        return parseInt(a) + parseInt(b);
    }

    // forma 2 (atribuindo à uma variável)
    var somaForma2 = function(a, b) {
        return parseInt(a) + parseInt(b);
    }

    // forma 3 (arrow function)
    var somaForma3 = (a, b) => parseInt(a) + parseInt(b);

/* -- FIM DAS FORMAS DE DECLARAR FUNÇÕES -- */

function Saint(nome, vida = 100, tipo) {
    this.nome = nome;
    this.vida = parseInt(vida);
    this.tipo = tipo;
}

var seiya = new Saint("Seiya", undefined, "Bronze");
console.log(seiya.nome + " é um Saint?", seiya instanceof Saint);