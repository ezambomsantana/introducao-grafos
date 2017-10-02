package leitura;

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

public class Metro {
	
	public static String inputFile = "C:\\Users\\ezamb\\Desktop\\grafos\\metro.xml";

	public static int[][] getMetroMatrix() throws ParserConfigurationException, SAXException, IOException {
				
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docMap = builder.parse(inputFile);
		
		docMap.getDocumentElement().normalize();
		NodeList nList = docMap.getElementsByTagName("station");
		
		List<String> estacoes = new ArrayList<String>();

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				String nameStation = eElement.getAttribute("name");
				
				estacoes.add(nameStation);

			}

		}
		

		int [][] matrix = new int[nList.getLength()][nList.getLength()];
		
		nList = docMap.getElementsByTagName("link");
		HashMap<Integer, Integer> links = new HashMap<>();

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				String nameStation = eElement.getAttribute("nameOrigin");
				String nameDestination = eElement.getAttribute("nameDestination");
				
				int origem = estacoes.indexOf(nameStation);
				int destination = estacoes.indexOf(nameDestination);
				
				matrix[origem][destination] = 1;
				matrix[destination][origem] = 1;
				
				links.put(origem, destination);

			}

		}
		
		for (int i = 0; i < estacoes.size(); i++) {
			System.out.print(i + ":");
			System.out.println(estacoes.get(i));
		}
		
		return matrix;

	}
	
}
