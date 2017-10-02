package mapa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Mapa {
	
	public static String inputFile = "C:\\Users\\ezamb\\Desktop\\grafos\\map.xml";

	public static List<String> nos = new ArrayList<String>();
	
	public static int[][] getMetroMatrix() throws ParserConfigurationException, SAXException, IOException {
				
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docMap = builder.parse(inputFile);
		
		docMap.getDocumentElement().normalize();
		NodeList nList = docMap.getElementsByTagName("node");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				String nameStation = eElement.getAttribute("id");
				nos.add(nameStation);

			}

		}
		

		int [][] matrix = new int[nList.getLength()][nList.getLength()];
		
		nList = docMap.getElementsByTagName("link");
		HashMap<Integer, Integer> links = new HashMap<>();

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				String noOrigem = eElement.getAttribute("from");
				String noDestino = eElement.getAttribute("to");
				
				String tamanho = eElement.getAttribute("length");
				tamanho = tamanho.substring(0, tamanho.indexOf('.'));
				
				
				int origem = nos.indexOf(noOrigem);
				int destination = nos.indexOf(noDestino);
				
				matrix[origem][destination] = Integer.parseInt(tamanho);
				matrix[destination][origem] = Integer.parseInt(tamanho);
				
				links.put(origem, destination);

			}

		}
		
		
		return matrix;

	}
	
}
