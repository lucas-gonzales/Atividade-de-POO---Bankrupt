import java.util.Random;

public abstract class Jogador {
    protected int saldo = 300;
    protected int posicao = 0;
    protected boolean ativo = true;
    protected String nome;
    protected Random rand = new Random();

    public Jogador(String nome) {
        this.nome = nome;
    }

    public abstract boolean deveComprar(Propriedade prop);

    public void movimentar(int passos, int totalPropriedades) {
        int novaPos = (posicao + passos);
        if (novaPos >= totalPropriedades) {
            saldo += 100;
        }
        posicao = novaPos % totalPropriedades;
    }

    public void pagar(int valor) {
        saldo -= valor;
        if (saldo < 0) {
            ativo = false;
        }
    }

    public void receber(int valor) {
        saldo += valor;
    }

    public boolean estaAtivo() {
        return ativo;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getPosicao() {
        return posicao;
    }

    public String getNome() {
        return nome;
    }
}