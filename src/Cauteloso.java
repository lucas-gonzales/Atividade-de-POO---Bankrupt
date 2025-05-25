public class Cauteloso extends Jogador {
    public Cauteloso() {
        super("Cauteloso");
    }

    public boolean deveComprar(Propriedade prop) {
        return (saldo - prop.custo) >= 80;
    }
}