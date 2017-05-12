// exercício 01
function daisyGame(num) {
    if(typeof num == "number")
        return ((parseInt(num)%2)!==0)?"Love me":"Love me not";
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
        return parseFloat(num1) + parseFloat(num2);
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