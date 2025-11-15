import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Teste e Análise de Desempenho do Algoritmo Rally Greedy");

    }

    /**
     * 
     * @param linhaDeComprimento O comprimento total da linha(L)
     * @param maximoViajado      O maximo que pode ser viajado por dia(D)
     * @param pontosDeParada     Lista dos pontos de parada
     * @return O número mínimo de paradas, ou -1 se for impossível.
     */
    public static int rallyGreedy(int linhaDeComprimento, int maximoViajado, List<Integer> pontosDeParada) {
        int paradas = 0;
        int indicePosicaoAtual = 0;

        while (pontosDeParada.get(indicePosicaoAtual) < linhaDeComprimento) {

            int proximoPontoMaisDistante = encontrarProximoPonto(indicePosicaoAtual, maximoViajado, pontosDeParada);

            if (proximoPontoMaisDistante == indicePosicaoAtual) {

                return -1;
            }

            if (pontosDeParada.get(proximoPontoMaisDistante) < linhaDeComprimento) {
                paradas++;
            }
            indicePosicaoAtual = proximoPontoMaisDistante;
        }
        return paradas;
    }

    /**
     * 
     * @param indicePosicaoAtual Indice do ponto de partida do dia
     * @param maximoViajado      O maximo que pode ser viajado.
     * @param pontosDeParada     A lista de todos os pontos
     * @return O indice do ponto mais distante alcançável
     */
    private static int encontrarProximoPonto(int indicePosicaoAtual, int maximoViajado, List<Integer> pontosDeParada) {

        int alcanceMaximo = pontosDeParada.get(indicePosicaoAtual) + maximoViajado;

        int melhorProximoPonto = indicePosicaoAtual;

        for (int i = indicePosicaoAtual + 1; i < pontosDeParada.size() && pontosDeParada.get(i) <= alcanceMaximo; i++) {
            melhorProximoPonto = i;
        }

        return melhorProximoPonto;

    }
}
