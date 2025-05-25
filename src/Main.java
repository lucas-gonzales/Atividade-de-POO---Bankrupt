import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Propriedade> propriedadesOriginais = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("gameConfig.txt"));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.trim().split("\s+");
            int venda = Integer.parseInt(partes[0]);
            int aluguel = Integer.parseInt(partes[1]);
            propriedadesOriginais.add(new Propriedade(venda, aluguel));
        }
        reader.close();

        Map<String, Integer> vitorias = new HashMap<>();
        int totalTurnos = 0;
        int timeouts = 0;

        for (int i = 0; i < 300; i++) {
            List<Propriedade> propriedadesCopia = new ArrayList<>();
            for (Propriedade p : propriedadesOriginais) {
                propriedadesCopia.add(new Propriedade(p.custo, p.aluguel));
            }

            Jogo jogo = new Jogo(propriedadesCopia);
            Jogo.Resultado resultado = jogo.rodar();

            vitorias.put(resultado.vencedor.getNome(), vitorias.getOrDefault(resultado.vencedor.getNome(), 0) + 1);
            totalTurnos += resultado.turnos;
            if (resultado.timeout) {
                timeouts++;
            }
        }

        System.out.println("Partidas que terminaram por timeout: " + timeouts);
        System.out.println("Turnos médios por partida: " + (totalTurnos / 300.0));

        for (String tipo : vitorias.keySet()) {
            double porcentagem = (vitorias.get(tipo) / 300.0) * 100;
            System.out.printf("Vitórias de %s: %.2f%%\n", tipo, porcentagem);
        }

        String vencedorAbsoluto = vitorias.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("Comportamento que mais vence: " + vencedorAbsoluto);
    }
}