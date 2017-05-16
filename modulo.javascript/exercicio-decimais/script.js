Number.prototype.arredondar = function(casasDecimais = 2) {
    var casas = Math.pow(10, casasDecimais);
    return Math.round(this * casas) / casas;
};

describe('arredondar()', function() {
    it('deve arredondar número pi com 2 casas decimais p/ 3.14', function() {
        expect((Math.PI).arredondar(2)).toBe(3.14);
    });

    it('deve arredondar número pi p/ 3.14 sem passar casa decimal', function() {
        expect((Math.PI).arredondar()).toBe(3.14);
    });

    it('deve arredondar número pi com zero casas decimais p/ 3', function() {
        expect((Math.PI).arredondar(0)).toBe(3);
    });

    it('deve arredondar 3.7890 com 3 casas decimais p/ 3.789', function() {
        expect(3.7890.arredondar(3)).toBe(3.789);
    });

    it('deve arredondar 3.7890 p/ 3.80 sem passar casa decimal', function() {
        expect(3.7890.arredondar()).toBe(3.79);
    });

    it('deve arredondar 3.7890 com zero casas decimais p/ 4', function() {
        expect(3.7890.arredondar(0)).toBe(4);
    });
});