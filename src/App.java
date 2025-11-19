import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Integer> pontos = List.of(0, 100, 200, 350, 500, 650, 900, 1000);
        int L = 1000;
        int D = 300;

        long inicio = System.nanoTime();
        Resultado resultado = rallyGreedy(L, D, pontos);
        long fim = System.nanoTime();
        
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║           RESULTADO RALLY GREEDY               ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf("║ Paradas necessárias:              %-12d ║%n", resultado.paradas);
        System.out.printf("║ Iterações rallyGreedy:            %-12d ║%n", resultado.iteracoesRally);
        System.out.printf("║ Iterações encontrarProximoPonto:  %-12d ║%n", resultado.iteracoesBusca);
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf("║ Tempo de execução:                %.6f s   ║%n", tempoSegundos);
        System.out.println("╚════════════════════════════════════════════════╝");
    }

    static class Resultado {
        int paradas;
        int iteracoesRally;
        int iteracoesBusca;

        Resultado(int p, int r, int b) {
            paradas = p;
            iteracoesRally = r;
            iteracoesBusca = b;
        }
    }

    public static Resultado rallyGreedy(int linhaDeComprimento, int maximoViajado, List<Integer> pontosDeParada) {
        int paradas = 0;
        int indicePosicaoAtual = 0;
        int iteracoesRally = 0;
        int iteracoesBusca = 0;

        while (pontosDeParada.get(indicePosicaoAtual) < linhaDeComprimento) {
            iteracoesRally++;
            
            ResultadoBusca busca = encontrarProximoPonto(
                indicePosicaoAtual, 
                maximoViajado, 
                pontosDeParada
            );
            iteracoesBusca += busca.iteracoes;
            
            int proximo = busca.indiceProximo;
            
            if (proximo == indicePosicaoAtual) {
                return new Resultado(-1, iteracoesRally, iteracoesBusca);
            }
            
            if (pontosDeParada.get(proximo) < linhaDeComprimento) {
                paradas++;
            }
            
            indicePosicaoAtual = proximo;
        }
        
        return new Resultado(paradas, iteracoesRally, iteracoesBusca);
    }

    static class ResultadoBusca {
        int indiceProximo;
        int iteracoes;

        ResultadoBusca(int idx, int it) {
            indiceProximo = idx;
            iteracoes = it;
        }
    }

    private static ResultadoBusca encontrarProximoPonto(
        int indicePosicaoAtual, 
        int maximoViajado, 
        List<Integer> pontosDeParada
    ) {
        int iteracoes = 0;
        int alcanceMaximo = pontosDeParada.get(indicePosicaoAtual) + maximoViajado;
        int melhorProximoPonto = indicePosicaoAtual;

        for (int i = indicePosicaoAtual + 1; 
             i < pontosDeParada.size() && pontosDeParada.get(i) <= alcanceMaximo; 
             i++) {
            iteracoes++;
            melhorProximoPonto = i;
        }

        return new ResultadoBusca(melhorProximoPonto, iteracoes);
    }
}