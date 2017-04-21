public class GoldSaint extends Saint {
    public GoldSaint(String nome, String constelacao) throws Exception {
        super(nome, new Armadura(new Constelacao(constelacao), Categoria.OURO));
        
        if( 
            !constelacao.equals("Áries") 
            && !constelacao.equals("Touro")
            && !constelacao.equals("Gêmeos")
            && !constelacao.equals("Câncer")
            && !constelacao.equals("Virgem")
            && !constelacao.equals("Leão")
            && !constelacao.equals("Libra")
            && !constelacao.equals("Escorpião")
            && !constelacao.equals("Sagitário")
            && !constelacao.equals("Capricórnio")
            && !constelacao.equals("Aquário")
            && !constelacao.equals("Peixes")
        )
            throw new Exception("Constelação inválida.");
            
        this.quantSentidosDespertados = 7;
    }
}