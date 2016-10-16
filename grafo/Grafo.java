package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

    private int[][] matrizAdjacencia;

    public Grafo(int[][] matriz) {
        matrizAdjacencia = matriz;
    }

    public int numeroVertices() {
        return matrizAdjacencia.length;
    }

    public int numeroArestas() {
        int num = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] != 0) {

                    if (matrizAdjacencia[i][j] < 0) {
                        num = num + (-matrizAdjacencia[i][j]);
                    } else {
                        num = num + matrizAdjacencia[i][j];
                    }
                }
            }
        }
        return num;
    }

    public String nomeArestas() {

        String arestas = "";
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = i; j < matrizAdjacencia[i].length; j++) {
                for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                    arestas = arestas + "[" + i + "," + j + "] ";
                }
            }
        }
        return arestas;
    }

    public int loops() {
        int numLoops = 0;
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            if (matrizAdjacencia[i][i] != 0) {
                numLoops++;
            }
        }
        return numLoops;
    }

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

    public boolean isConexo() {

        return true;
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

    public int[] buscaLargura(int verticeInicial) {

        int inicio = 0;
        int fim = 1;

        int fila[] = new int[numeroVertices()];
        int distancia[] = new int[numeroVertices()];
        boolean naFila[] = new boolean[numeroVertices()];

        fila[0] = verticeInicial;
        distancia[verticeInicial] = 0;
        naFila[verticeInicial] = true;

        while (inicio != fim) {

            int verticeAnalisado = fila[inicio];
            inicio++;
            for (int i = 0; i < matrizAdjacencia[verticeAnalisado].length; i++) {
                if (matrizAdjacencia[verticeAnalisado][i] != 0 && !naFila[i]) {
                    fila[fim] = i;
                    distancia[i] = distancia[verticeAnalisado] + 1;
                    naFila[i] = true;
                    fim++;
                }

            }

        }
        return distancia;

    }

    public int[] buscaLarguraList(int verticeInicial) {

        ArrayList<Integer> fila = new ArrayList<Integer>();
        int distancia[] = new int[numeroVertices()];
        boolean naFila[] = new boolean[numeroVertices()];

        fila.add(verticeInicial);
        distancia[verticeInicial] = 0;
        naFila[verticeInicial] = true;

        while (!fila.isEmpty()) {

            int verticeAnalisado = fila.get(0);
            fila.remove(0);
            for (int i = 0; i < matrizAdjacencia[verticeAnalisado].length; i++) {
                if (matrizAdjacencia[verticeAnalisado][i] != 0 && !naFila[i]) {
                    fila.add(i);
                    distancia[i] = distancia[verticeAnalisado] + 1;
                    naFila[i] = true;
                }
            }
        }
        return distancia;

    }

    public int[] buscaProfundidade2(int verticeInicial) {

        ArrayList<Integer> fila = new ArrayList<Integer>();
        int distancia[] = new int[numeroVertices()];
        boolean naFila[] = new boolean[numeroVertices()];

        fila.add(verticeInicial);
        distancia[verticeInicial] = 0;
        naFila[verticeInicial] = true;

        while (!fila.isEmpty()) {

            int verticeAnalisado = fila.get(fila.size() - 1);
            boolean entrou = false;
            for (int i = 0; i < matrizAdjacencia[verticeAnalisado].length; i++) {
                if (matrizAdjacencia[verticeAnalisado][i] != 0 && !naFila[i]) {
                    fila.add(i);
                    distancia[i] = distancia[verticeAnalisado] + 1;
                    naFila[i] = true;
                    entrou = true;
                    break;
                }
            }
            if (!entrou) {
                fila.remove(fila.size() - 1);
            }
        }
        return distancia;

    }

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
                        if (matrizAdjacencia[vertice][j] != 0 &&
                                matrizAdjacencia[vertice][j] < menorPeso) {
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
    
    
    public int [][] floydWarshall() {
        
        // monta a matriz inicial
        // não inicializar com Integer.MAX_VALUE porque se fizer Integer.MAX_VALUE + 1, o valor fica negativo.
        int [][] matrizD = matrizAdjacencia;
        int [][] matrizPi = new int[numeroVertices()][numeroVertices()];
        for (int i = 0; i < matrizD.length; i++) {
            for (int j = 0; j < matrizD[i].length; j++) {
                if (i == j) {
                    matrizD[i][j] = 0;
                } else if (matrizD[i][j] == 0) {
                    matrizD[i][j] = 1000;
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
    
    public void visualizarGrafo(List<String> cidades, int [][] matriz) {
        JFrame jf = new JFrame();
        
        Graph g = new UndirectedSparseGraph();
        for (int i = 0; i < cidades.size(); i++) {
            g.addVertex(cidades.get(i));
        }
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = i; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    String nome = cidades.get(i) + " -> " + cidades.get(j) + " : " + matriz[i][j];
                    g.addEdge(nome, cidades.get(i), cidades.get(j));
                }
            }
        }
        
        VisualizationViewer vv = new VisualizationViewer(new FRLayout(g));
        jf.getContentPane().add(vv);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
            public String transform(String e) {
                return (e);
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
