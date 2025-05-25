public class Propriedade {
    int custo;
    int aluguel;
    Jogador dono;

    public Propriedade(int custo, int aluguel) {
        this.custo = custo;
        this.aluguel = aluguel;
        this.dono = null;
    }

    public boolean temDono() {
        return dono != null;
    }

    public void resetarDono() {
        this.dono = null;
    }
}