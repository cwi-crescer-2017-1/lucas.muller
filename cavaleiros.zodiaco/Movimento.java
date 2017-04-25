public interface Movimento {
    void executar(); // método para executar o movimento
    //boolean movimentoTiraDano(); // retorna se o movimento tira ou não dano
    TipoMovimento getTipoMovimento(); 
}