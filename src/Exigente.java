public class Exigente extends Jogador {
    public Exigente() {
        super("Exigente");
    }

    public boolean deveComprar(Propriedade prop) {
        return saldo >= prop.custo && prop.aluguel > 50;
    }
}