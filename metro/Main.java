package metro;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections15.Transformer;
import org.xml.sax.SAXException;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
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

        visualizarGrafo(matriz, Metro.estacoes);
        
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
    
    public static void visualizarGrafo(int [][] matriz, List<String> estacoes) {
    	
        JFrame jf = new JFrame();
        jf.setSize(2000, 2000);
        Graph g = new UndirectedSparseGraph();
        for (int i = 0; i < estacoes.size(); i++) {
        	String estacao = estacoes.get(i);
            g.addVertex(estacao);
        }
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = i; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                	String estacaoO = estacoes.get(i);
                	String estacaoD = estacoes.get(j);
                	
                    String nome = i + " -> " + j;
                    g.addEdge(nome, estacaoO, estacaoD);
                }
            }
        }
        
        VisualizationViewer vv = new VisualizationViewer(new FRLayout(g));
        jf.getContentPane().add(vv);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
			@Override
			public String transform(String e) {
				return e;
			}
        });
        
        
        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
            public String transform(String e) {
               return ("");
            }
        });
        
        jf.pack();
        jf.setVisible(true);
    
    
    }
    
}
