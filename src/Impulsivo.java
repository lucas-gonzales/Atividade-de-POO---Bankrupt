public class Impulsivo extends Jogador {
    public Impulsivo() {
        super("Impulsivo");
    }

    public boolean deveComprar(Propriedade prop) {
        return saldo >= prop.custo;
    }
}