import java.util.*;

public class Jogo {
    private List<Propriedade> propriedades;
    private List<Jogador> jogadores;
    private int turnos = 0;
    private final int MAX_TURNOS = 1000;
    private Random random = new Random();

    public Jogo(List<Propriedade> propriedades) {
        this.propriedades = propriedades;
        this.jogadores = new ArrayList<>();
        jogadores.add(new Impulsivo());
        jogadores.add(new Exigente());
        jogadores.add(new Cauteloso());
        jogadores.add(new Aleatorio());
        Collections.shuffle(jogadores);
    }

    public Resultado rodar() {
        turnos = 0;
        while (turnos < MAX_TURNOS && jogadores.stream().filter(Jogador::estaAtivo).count() > 1) {
            for (Jogador j : jogadores) {
                if (!j.estaAtivo()) continue;

                int dado = random.nextInt(6) + 1;
                j.movimentar(dado, propriedades.size());

                Propriedade prop = propriedades.get(j.getPosicao());
                if (!prop.temDono()) {
                    if (j.deveComprar(prop)) {
                        j.pagar(prop.custo);
                        if (j.estaAtivo()) {
                            prop.dono = j;
                        }
                    }
                } else if (prop.dono != j) {
                    j.pagar(prop.aluguel);
                    if (j.estaAtivo()) {
                        prop.dono.receber(prop.aluguel);
                    } else {
                        liberarPropriedadesDoJogador(j);
                    }
                }
            }
            turnos++;
        }

        Jogador vencedor = jogadores.stream().filter(Jogador::estaAtivo)
                .max(Comparator.comparingInt(Jogador::getSaldo)).orElse(null);

        boolean timeout = turnos == MAX_TURNOS;
        return new Resultado(vencedor, turnos, timeout);
    }

    private void liberarPropriedadesDoJogador(Jogador j) {
        for (Propriedade p : propriedades) {
            if (p.dono == j) {
                p.resetarDono();
            }
        }
    }

    public static class Resultado {
        Jogador vencedor;
        int turnos;
        boolean timeout;

        public Resultado(Jogador vencedor, int turnos, boolean timeout) {
            this.vencedor = vencedor;
            this.turnos = turnos;
            this.timeout = timeout;
        }
    }
}