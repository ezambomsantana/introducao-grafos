package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class Grafo {

    private int[][] matrizAdjacencia;

    public Grafo(int[][] matriz) {
        matrizAdjacencia = matriz;
    }

    /**
     *
     * Return the number of vertices of the graph.
     *
     * @return an integer with the number of vertices
     */
    public int numeroVertices() {
        return matrizAdjacencia.length;
    }

    /**
     *
     * Return the number of edges of the graph.
     *
     * @return an integer with the number of edges.
     */
    public int numeroArestas() {
        int num = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] != 0) {
                    num = num + 1;
                    //             if (matrizAdjacencia[i][j] < 0) {
                    //               num = num + (-matrizAdjacencia[i][j]);
                    //         } else {
                    //           num = num + matrizAdjacencia[i][j];
                    //     }
                }
            }
        }
        return num;
    }

    /**
     *
     * Get the name (origin -> destination) of all edges og the graph.
     *
     * @return a String with all the edges name.
     */
    public String nomeArestas() {

        String arestas = "";
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                arestas = arestas + "[" + i + "," + j + "] ";
            }
        }
        return arestas;
    }

    /**
     *
     * Verify the number of loops of the graph.
     *
     * @return an integer with the number of loops of the graph.
     */
    public int loops() {
        int numLoops = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            if (matrizAdjacencia[i][i] != 0) {
                numLoops++;
            }
        }
        return numLoops;
    }

    /**
     *
     * Return the number of parallel edges of the graph.
     *
     * @return the number of parallel edges.
     */
    public int numArestasParalelas() {
        int numArestasParalelas = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] > 1) {
                    numArestasParalelas++;
                }
            }
        }
        return numArestasParalelas;
    }

    public int[] graus() {

        int[] graus = new int[matrizAdjacencia.length];
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            int somaGrau = 0;
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    somaGrau = somaGrau + matrizAdjacencia[i][j];
                }
                graus[i] = somaGrau;
            }
        }
        return graus;

    }

    public int[][] matrizIncidencia() {

        int[][] matriz = new int[numeroVertices()][numeroArestas()];
        int contArestas = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                        matriz[i][contArestas] = 1;
                        matriz[j][contArestas] = 1;
                        contArestas++;
                    }
                }
            }
        }

        return matriz;

    }

    /**
     *
     * Execute the BFS algorithm to the adjacency matrix.
     *
     * @param verticeInicial - The origin.
     *
     * @return a vector with all distances from the origin.
     */
    public int[] buscaLargura(int verticeInicial) {

        ArrayList<Integer> fila = new ArrayList<Integer>();
        int distancia[] = new int[numeroVertices()];
        boolean naFila[] = new boolean[numeroVertices()];

        fila.add(verticeInicial);
        distancia[verticeInicial] = 0;
        naFila[verticeInicial] = true;

        int[] coresVertices = new int[numeroVertices()];
        for (int i = 0; i < coresVertices.length; i++) {
            coresVertices[i] = -1;
        }
        coresVertices[verticeInicial] = 0;

        while (!fila.isEmpty()) {

            int verticeAnalisado = fila.get(0);
            fila.remove(0);
            for (int i = 0; i < matrizAdjacencia[verticeAnalisado].length; i++) {
                if (matrizAdjacencia[verticeAnalisado][i] != 0 && !naFila[i]) {
                    fila.add(i);
                    distancia[i] = distancia[verticeAnalisado] + 1;
                    naFila[i] = true;
                    coresVertices[i] = definirCor(i, coresVertices);
                }
            }
        }

        for (int i = 0; i < coresVertices.length; i++) {
            System.out.println("cores: " + coresVertices[i]);
        }

        return distancia;

    }

    int cores[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    public int definirCor(int vertice, int[] vetorCores) {

        List<Integer> coresVizinhos = new ArrayList<Integer>();

        for (int i = 0; i < matrizAdjacencia.length; i++) {
            if (matrizAdjacencia[vertice][i] != 0) {
                if (vetorCores[i] != -1) {
                    coresVizinhos.add(i);
                }
            }
        }
        int maior = 0;
        for (int i = 0; i < cores.length; i++) {
            if (!coresVizinhos.contains(cores[i])) {
                if (cores[i] > maior) {
                    maior = cores[i];
                }
                return cores[i];
            }
        }
        return maior + 1;

    }

    /**
     *
     * Execute the DFS algorithm to the adjacency matrix.
     *
     * @param verticeInicial - The origin.
     *
     * @return a vector with all distances from the origin.
     */
    public int[] buscaProfundidade(int verticeInicial) {

        ArrayList<Integer> pilha = new ArrayList<Integer>();
        int distancia[] = new int[numeroVertices()];
        boolean naFila[] = new boolean[numeroVertices()];

        pilha.add(verticeInicial);
        distancia[verticeInicial] = 0;
        naFila[verticeInicial] = true;

        while (!pilha.isEmpty()) {

            int verticeAnalisado = pilha.get(pilha.size() - 1);
            boolean encontrouVizinho = false;
            for (int i = 0; i < matrizAdjacencia[verticeAnalisado].length; i++) {
                if (matrizAdjacencia[verticeAnalisado][i] != 0 && !naFila[i]) {
                    pilha.add(i);
                    distancia[i] = distancia[verticeAnalisado] + 1;
                    naFila[i] = true;
                    encontrouVizinho = true;
                    break;
                }
            }
            if (!encontrouVizinho) {
                pilha.remove(pilha.size() - 1);
            }

        }
        return distancia;

    }

    /**
     *
     * Find a path from the result of BFS or DFS algorithms.
     *
     * @param vInicio - The origin.
     * @param vFim - The destination.
     * @param distancia - The vector, result of BFS or DFS.
     *
     * @return a String with the path.
     */
    public String caminho(int vInicio, int vFim, int[] distancia) {

        String caminho = "";
        caminho = caminho + " " + vFim;
        int pesoAnterior = distancia[vFim];
        int verticeAnalisado = vFim;
        for (int i = 0; i < distancia[vFim]; i++) {

            for (int j = 0; j < matrizAdjacencia[verticeAnalisado].length; j++) {
                if (matrizAdjacencia[verticeAnalisado][j] != 0
                        && distancia[j] == pesoAnterior - 1) {
                    caminho = caminho + " " + j;
                    pesoAnterior--;
                    verticeAnalisado = j;
                    break;
                }

            }

        }

        return caminho;

    }

    /**
     *
     * Prim's algorithm to find a minimal spanning tree.
     *
     * @param vInicial - The origin.
     *
     * @return a String with the sequence of edges add to the subgraph.
     */
    public String prim(int vInicio) {

        List<Integer> lista = new ArrayList<Integer>();
        lista.add(vInicio);

        String arvore = " ";

        for (int k = 0; k < numeroVertices() - 1; k++) {

            int menorPeso = 10;
            int iMenorPeso = 0;
            int jMenorPeso = 0;
            for (int i = 0; i < lista.size(); i++) {

                int vertice = lista.get(i);

                for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                    if (!lista.contains(j)) {
                        if (matrizAdjacencia[vertice][j] != 0
                                && matrizAdjacencia[vertice][j] < menorPeso) {
                            menorPeso = matrizAdjacencia[vertice][j];
                            iMenorPeso = vertice;
                            jMenorPeso = j;
                        }
                    }
                }
            }
            arvore = arvore + "[" + iMenorPeso + "," + jMenorPeso + "], ";
            lista.add(jMenorPeso);
        }
        return arvore;

    }

    /**
     * 
     * Fleury algorithm to find a Euler path
     * 
     * @param vInicial
     * @return a String with the vertex sequence
     */
    public String fleury(int vInicial) {

        int[] graus = this.graus();
        for (int g : graus) {
            if (g % 2 != 0) {
                return "Erro! Grafo com vértice com grau ímpar!";
            }
        }

        int[][] matriz = matrizAdjacencia.clone();
        int vAtual = vInicial;
        String saida = "";
        
        int numeroArestas = numeroArestas();

        for (int k = 0; k < numeroArestas; k++) {

            for (int j = 0; j < matriz.length; j++) {
                if (matriz[vAtual][j] != 0) {

                    if (!isBridge(vAtual, j, matriz.clone())) {
                        saida += "[" + vAtual + "," + j + "]";
                        matriz[vAtual][j] = 0;
                        matriz[j][vAtual] = 0;
                        vAtual = j;
                        break;
                    } else {

                        boolean temOutraAresta = false;
                        for (int l = j + 1; l < matriz.length; l++) {
                            if (matriz[vAtual][l] != 0) {
                                temOutraAresta = true;
                            }
                        }
                        if (!temOutraAresta) {
                            saida += "[" + vAtual + "," + j + "]";
                            matriz[vAtual][j] = 0;
                            matriz[j][vAtual] = 0;
                            vAtual = j;
                            break;
                        }

                    }
                }
            }
        }
        return saida;

    }

    /**
     * 
     * Verify if an edge linking the vertex vAtual and j is a bridge
     * 
     * @param vAtual
     * @param j
     * @param matriz
     * @return true case the edge is a bridge, false otherwise
     */
    private boolean isBridge(int vAtual, int j, int[][] matriz) {

        // remove a aresta que será verificada.
        matriz[vAtual][j] = 0;
        matriz[j][vAtual] = 0;

        int[] d = buscaLarguraKruskal(matriz, vAtual);
                
        // recoloca a aresta para não modificar a matriz.
        matriz[vAtual][j] = 1;
        matriz[j][vAtual] = 1;

        return d[j] == 0;

    }

    /**
     *
     * Kruskal's algorithm to find a minimal spanning tree.
     *
     * @return a String with the sequence of edges add to the subgraph.
     */
    public String kruskal() {

        String arvore = " ";
        List<Integer> nos = new ArrayList<Integer>();
        int[][] novaMatriz = new int[numeroVertices()][numeroVertices()];

        for (int k = 0; k < numeroVertices() - 1; k++) {

            int menorPeso = 10;
            int iMenorPeso = 0;
            int jMenorPeso = 0;
            for (int i = 0; i < matrizAdjacencia.length; i++) {
                for (int j = i; j < matrizAdjacencia[i].length; j++) {
                    if (matrizAdjacencia[i][j] != 0
                            && matrizAdjacencia[i][j] < menorPeso
                            && !verificaCiclo(novaMatriz, nos, i, j)) {
                        menorPeso = matrizAdjacencia[i][j];
                        iMenorPeso = i;
                        jMenorPeso = j;
                    }
                }
            }
            arvore = arvore + "[" + iMenorPeso + "," + jMenorPeso + "], ";
            nos.add(iMenorPeso);
            nos.add(jMenorPeso);
            novaMatriz[iMenorPeso][jMenorPeso] = 1;
            novaMatriz[jMenorPeso][iMenorPeso] = 1;
        }
        return arvore;

    }

    /**
     *
     * Veirfy if a graph has a cycle or not, using the BFS algorithm.
     *
     * @return true if there is a cycle, false otherwise.
     */
    private boolean verificaCiclo(int[][] matriz, List<Integer> nos, int i, int j) {

        if (nos.contains(i) && nos.contains(j)) {
            int[] dist = buscaLarguraKruskal(matriz, i);
            if (dist[j] == 0) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public int[] buscaLarguraKruskal(int[][] matriz, int verticeInicial) {

        ArrayList<Integer> fila = new ArrayList<Integer>();
        int distancia[] = new int[numeroVertices()];
        boolean naFila[] = new boolean[numeroVertices()];

        fila.add(verticeInicial);
        distancia[verticeInicial] = 0;
        naFila[verticeInicial] = true;

        while (!fila.isEmpty()) {

            int verticeAnalisado = fila.get(0);
            fila.remove(0);
            for (int i = 0; i < matriz[verticeAnalisado].length; i++) {
                if (matriz[verticeAnalisado][i] != 0 && !naFila[i]) {
                    fila.add(i);
                    distancia[i] = distancia[verticeAnalisado] + 1;
                    naFila[i] = true;
                }
            }
        }
        return distancia;

    }

    /**
     *
     * Djikistra algorithm to find the best path in a weight graph.
     *
     * @return the vector with all the distances from the origin.
     */
    public int[] djikistra(int verticeInicial) {

        // 1 - tirei a fila, agora verifica no vetor de distâncias.
        // 2 - Adicionei o vetor antecessor
        int distancia[] = new int[numeroVertices()];
        int antecessor[] = new int[numeroVertices()];
        boolean verificado[] = new boolean[numeroVertices()];

        for (int i = 0; i < distancia.length; i++) {
            distancia[i] = Integer.MAX_VALUE;
        }

        distancia[verticeInicial] = 0;

        for (int h = 0; h < distancia.length; h++) {

            int menor = Integer.MAX_VALUE;
            int menorI = 0;
            for (int i = 0; i < distancia.length; i++) {
                if (distancia[i] < menor && !verificado[i]) {
                    menor = distancia[i];
                    menorI = i;
                }
            }

            int verticeAnalisado = menorI;
            verificado[verticeAnalisado] = true;
            for (int i = 0; i < matrizAdjacencia[verticeAnalisado].length; i++) {
                if (matrizAdjacencia[verticeAnalisado][i] != 0
                        && !verificado[i]
                        && distancia[i] > distancia[verticeAnalisado] + matrizAdjacencia[verticeAnalisado][i]) {
                    distancia[i] = distancia[verticeAnalisado] + matrizAdjacencia[verticeAnalisado][i];
                    antecessor[i] = verticeAnalisado;
                }
            }
        }

        for (int i = 0; i < antecessor.length; i++) {
            System.out.println("I: " + i + " A: " + antecessor[i]);
        }

        return distancia;

    }

    /**
     *
     * Floyd-Warshall algorithm to find the best path in a weight graph.
     *
     * @return the matrix with all distances.
     */
    public int[][] floydWarshall() {

        // monta a matriz inicial
        // não inicializar com Integer.MAX_VALUE porque se fizer Integer.MAX_VALUE + 1, o valor fica negativo.
        int[][] matrizD = matrizAdjacencia;
        int[][] matrizPi = new int[numeroVertices()][numeroVertices()];
        for (int i = 0; i < matrizD.length; i++) {
            for (int j = 0; j < matrizD[i].length; j++) {
                if (i == j) {
                    matrizD[i][j] = 0;
                } else if (matrizD[i][j] == 0) {
                    matrizD[i][j] = 1000000;
                }
            }
        }

        for (int k = 0; k < numeroVertices(); k++) {
            for (int i = 0; i < matrizD.length; i++) {
                for (int j = 0; j < matrizD[i].length; j++) {
                    if (matrizD[i][j] > matrizD[i][k] + matrizD[k][j]) {
                        matrizD[i][j] = matrizD[i][k] + matrizD[k][j];
                    }

                }
            }
        }

        return matrizD;

    }

    /**
     *
     * A simple visualization of the graph, using the JUNG API.
     *
     */
    public void visualizarGrafo(int[][] matriz) {

        JFrame jf = new JFrame();

        Graph g = new UndirectedSparseGraph();
        for (int i = 0; i < matriz.length; i++) {
            g.addVertex(i);
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = i; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    String nome = i + " -> " + j;
                    g.addEdge(nome, i, j);
                }
            }
        }

        VisualizationViewer vv = new VisualizationViewer(new FRLayout(g));
        jf.getContentPane().add(vv);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vv.getRenderContext().setVertexLabelTransformer(new Transformer<Integer, String>() {
            @Override
            public String transform(Integer e) {
                return String.valueOf(e);
            }
        });

        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
            public String transform(String e) {
                return (e);
            }
        });

        jf.pack();
        jf.setVisible(true);

    }

}
