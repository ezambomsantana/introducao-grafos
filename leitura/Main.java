package leitura;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import grafo.Grafo;

/**
 *
 * @author Eduardo Felipe Zambom Santana - Introdução a Teoria dos Grafos
 */
public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        
    	int [][] matriz = Metro.getMetroMatrix();
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
        
        Grafo grafo = new Grafo(matriz);        

        grafo.visualizarGrafo(matriz);
        
        System.out.println("Num Vértices: " + grafo.numeroVertices());
        System.out.println("Num arestas: " + grafo.numeroArestas());
        System.out.println("Arestas:" + grafo.nomeArestas());
        System.out.println("Loops: " + grafo.loops());
        
        System.out.println("Graus:");
        int [] graus = grafo.graus();
        for (int i = 0; i < graus.length; i++) {
            System.out.println(i + " " + graus[i]);
        }
            
        System.out.println("Busca Largura:");
        int [] distancia = grafo.buscaLargura(0);
        for (int i = 0; i < distancia.length; i++) {
            System.out.println(i + " " + distancia[i]);
        }        
        System.out.println("Caminho: " + grafo.caminho(1, 4, distancia));
           
        System.out.println("Dijikstra:");
        distancia = grafo.djikistra(0);
        for (int i = 0; i < distancia.length; i++) {
            System.out.println(i + " " + distancia[i]);
        }        
        
        System.out.println("Floyd-Warshall");
        int [][] matrizD = grafo.floydWarshall();
        for (int i = 0; i < matrizD.length; i++) {
            for (int j = 0; j < matrizD[i].length; j++) {
                System.out.print(matrizD[i][j] + " ");
            }
            System.out.println("");
        }
                
    }
    
}
