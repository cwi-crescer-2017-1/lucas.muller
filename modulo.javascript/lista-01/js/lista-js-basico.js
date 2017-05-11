// exercício 01
function daisyGame(num) {
    return (num%2!=0)?"Love me":"Love me not";
}

// exercício 02
function maiorTexto(array) {
    var maior = "";
    for(var i = 0; i < array.length; i++) {
        if(array[i].length > maior.length)
            maior = array[i];
    }
    return maior;
}

// exercício 03
function imprime(array, funcao) {
    if(typeof funcao !== "function") return;
    for(var i = 0; i < array.length; i++)
        funcao(array[i]);
}

// exercício 04
function adicionar(num1) {
    return function(num2) {
        return parseInt(num1) + parseInt(num2);
    };
}

// exercício 05
function fiboSum(num) {
    num = parseInt(num);
    var array = [0, 1];
    var soma = 1;
    for(var i = 2; i<=num; i++) {
        array.push((array[i-2] + array[i-1]));
        soma += array[i];
    }
    return soma;
}

// exercício 06
function queroCafe(mascada, precos) {
    mascada = parseFloat(mascada);
    var array = [];
    for(var i = 0; i<precos.length; i++){
        if(precos[i] <= mascada)
            array.push(precos[i]);
    }
    return array.sort().toString();
}