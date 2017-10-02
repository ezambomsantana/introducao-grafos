package metro;

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

	public static List<String> estacoes = new ArrayList<String>();
	
	public static int[][] getMetroMatrix() throws ParserConfigurationException, SAXException, IOException {
				
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docMap = builder.parse(inputFile);
		
		docMap.getDocumentElement().normalize();
		NodeList nList = docMap.getElementsByTagName("station");
		
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
				
				String tempo = eElement.getAttribute("tempo");
				
				int origem = estacoes.indexOf(nameStation);
				int destination = estacoes.indexOf(nameDestination);
				
				matrix[origem][destination] = Integer.parseInt(tempo);
				matrix[destination][origem] = Integer.parseInt(tempo);
				
				links.put(origem, destination);

			}

		}
		
		
		return matrix;

	}
	
}
