// exercício 01
function daisyGame(num) {
    if(typeof num === "number")
        return (parseInt(num) % 2 !== 0) ? "Love me" : "Love me not";
        // OOOUUU: return `Love me${ parseInt(num) % 2 === 0 ? " not" : "" }`;
}

// exercício 02
function maiorTexto(array) {
    var maior = "";
    for(let i = 0; i < array.length; i++) {
        if(array[i].length > maior.length)
            maior = array[i];
    }
    return maior;
}

// exercício 03
function imprime(array, funcao) {
    if(typeof funcao !== "function") return;
    for(let i = 0; i < array.length; i++)
        funcao(array[i]);
    // OOOUUU: array.forEach(funcao);
}

// exercício 04
function adicionar(num1) {
    return function(num2) {
        if(typeof num1 === "number" && typeof num2 === "number")
            return parseFloat(num1) + parseFloat(num2);
    };
}

// exercício 05
function fiboSum(num) {
    if(typeof num !== "number") return;
    num = parseInt(num);
    var array = [0, 1];
    var soma = 1;
    for(let i = 2; i<=num; i++) {
        array.push((array[i-2] + array[i-1]));
        soma += array[i];
    }
    return soma;
}

function fiboSumV2(num) {
    if(typeof num !== "number") return;
    num = parseInt(num);
    var getFibo = function(termo) {
        const a = 1.618034;
        return Math.floor((Math.pow(a, termo) - Math.pow((1-a), termo)) / Math.sqrt(5));
    };

    var soma = 0;
    for(let i = 0; i<=num; i++) {
        soma += getFibo(i);
    }
    return soma;
}

function fiboSumV3(num) {
    if(typeof num !== "number") return;
    num = parseInt(num);
    var getFibo = function(termo) {
        if(termo === 0) return 0;
        if(termo === 1 || termo === 2) return 1;
        return getFibo(termo - 1) + getFibo(termo - 2);
    };

    var soma = 0;
    for(let i = 0; i<=num; i++) {
        soma += getFibo(i);
    }
    return soma;
}

// exercício 06
function queroCafe(mascada, precos) {
    mascada = parseFloat(mascada);
    var array = [];
    for(let i = 0; i<precos.length; i++){
        if(precos[i] <= mascada)
            array.push(precos[i]);
    }
    var criterioOrdenacao = function(a, b) { return a-b; }
    return array.sort(criterioOrdenacao).toString();
    // OOOUUU: return array.sort(criterioOrdenacao).join(",");
    // OOOUUU: return array.filter(a => a <= mascada).sort((a,b)=>a-b).join(",");
}

// testes unitários
QUnit.module("daisyGame()");
QUnit.test("daisyGame('oi')", function(assert){
    assert.notOk(daisyGame('oi')==="Love me");
});
QUnit.test("daisyGame(1)", function(assert){
    assert.ok(daisyGame(1)==="Love me");
});
QUnit.test("daisyGame(4)", function(assert){
    assert.ok(daisyGame(4)==="Love me not");
});

QUnit.module("maiorTexto()");
QUnit.test(`maiorTexto(["Love me", "Love me not"])`, function(assert){
    assert.ok(maiorTexto(["Love me", "Love me not"])==="Love me not");
});
QUnit.test(`maiorTexto(["Do", "you", "love", "me", "?"])`, function(assert){
    assert.ok(maiorTexto(["Do", "you", "love", "me", "?"])==="love");
});

QUnit.module("adicionar()()");
QUnit.test(`adicionar(3)(4)`, function(assert){
    assert.ok(adicionar(3)(4)===7);
});
QUnit.test(`adicionar(5642)(8749)`, function(assert){
    assert.ok(adicionar(5642)(8749)===14391);
});

QUnit.module("fiboSum()");
QUnit.test(`fiboSum(7)`, function(assert){
    assert.ok(fiboSum(7)===33);
});
QUnit.test(`fiboSum(3)`, function(assert){
    assert.ok(fiboSum(3)===4);
});

QUnit.module("queroCafe()");
QUnit.test(`queroCafe(3.14, [ 5.16, 2.12, 1.15, 3.11, 17.5 ])`, function(assert){
    assert.ok(queroCafe(3.14, [ 5.16, 2.12, 1.15, 3.11, 17.5 ])==="1.15,2.12,3.11");
});
QUnit.test(`queroCafe(1.15, [ 5.16, 2.12, 1.15, 3.11, 17.5 ])`, function(assert){
    assert.ok(queroCafe(1.15, [ 5.16, 2.12, 1.15, 3.11, 17.5 ])==="1.15");
});