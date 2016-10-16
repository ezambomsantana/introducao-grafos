package grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Eduardo Felipe Zambom Santana - Introdução a Teoria dos Grafos
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        int [][] matriz;
        
        FileReader fileReader = new FileReader("/home/eduardo/matriz");
        BufferedReader reader = new BufferedReader(fileReader);
        
        List<String> linhas = new ArrayList<String>();
        String data = null;
        while((data = reader.readLine()) != null){
            linhas.add(data);   
        }
        matriz = new int[linhas.size()][linhas.size()];
        int i = 0;
        for (String l : linhas) {
            StringTokenizer token = new StringTokenizer(l, " ");
            int j = 0;
            while (token.hasMoreTokens()) {
                String t = token.nextToken();
                matriz[i][j] = Integer.parseInt(t);
                j++;
            }
            i++;
        }
        
        
        
        
        for (i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
        
        fileReader.close();
        reader.close();
        
        Grafo grafo = new Grafo(matriz);        

        grafo.visualizarGrafo(matriz);
        
        System.out.println("Num Vértices: " + grafo.numeroVertices());
        System.out.println("Num arestas: " + grafo.numeroArestas());
        System.out.println("Arestas:" + grafo.nomeArestas());
        System.out.println("Loops: " + grafo.loops());
        System.out.println("Num arestas paralelas: " + grafo.numArestasParalelas());
        //System.out.println("Conexo: " + grafo.isConexo());
        
        System.out.println("Graus:");
        int [] graus = grafo.graus();
        for (i = 0; i < graus.length; i++) {
            System.out.println(i + " " + graus[i]);
        }
        
        System.out.println("Matriz incidencia");
        int [][] matrizIncidencia = grafo.matrizIncidencia();
        for (i = 0; i < matrizIncidencia.length; i++) {
            for (int j = 0; j < matrizIncidencia[i].length; j++) {
                System.out.print(matrizIncidencia[i][j] + " ");
           }
           System.out.println("");
        }
        
        System.out.println("Busca Largura:");
        int [] distancia = grafo.buscaLargura(1);
        for (i = 0; i < distancia.length; i++) {
            System.out.println(i + " " + distancia[i]);
        }        
        System.out.println("Caminho: " + grafo.caminho(1, 3, distancia));
        
        
        System.out.println("Busca Profundidade:");
        distancia = grafo.buscaProfundidade(1);
        for (i = 0; i < distancia.length; i++) {
            System.out.println(i + " " + distancia[i]);
        }        
        System.out.println("Caminho: " + grafo.caminho(1, 3, distancia));
        
        
        System.out.println("Árvore: " + grafo.prim(0));
        System.out.println("Árvore: " + grafo.kruskal());
        
        System.out.println("Dijikstra:");
        distancia = grafo.djikistra(0);
        for (i = 0; i < distancia.length; i++) {
            System.out.println(i + " " + distancia[i]);
        }        
        
        System.out.println("Floyd-Warshall");
        int [][] matrizD = grafo.floydWarshall();
        for (i = 0; i < matrizD.length; i++) {
            for (int j = 0; j < matrizD[i].length; j++) {
                System.out.print(matrizD[i][j] + " ");
            }
            System.out.println("");
        }
                
    }
    
}
