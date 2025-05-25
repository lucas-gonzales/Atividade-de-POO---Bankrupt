public class Aleatorio extends Jogador {
    public Aleatorio() {
        super("Aleatório");
    }

    public boolean deveComprar(Propriedade prop) {
        return saldo >= prop.custo && rand.nextBoolean();
    }
}