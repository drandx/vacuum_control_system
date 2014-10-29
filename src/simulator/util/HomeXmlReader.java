package simulator.util;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import simulator.model.*;


public class HomeXmlReader {

	public static Home ReadXML(String filePath)
	{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			File file = new File(filePath);
			Document document = builder.parse(file);		   

			Home home = new Home();
			LinkedList<FloorLevel> floorList = new LinkedList<FloorLevel>();
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node node = nodeList.item(i);
				if (node instanceof Element) 
				{
					FloorLevel floorItem = new FloorLevel();
					floorItem.setFloor(Integer.parseInt(node.getAttributes().getNamedItem("floor").getNodeValue()));
					NodeList childNodes = node.getChildNodes();
					for (int j = 0; j < childNodes.getLength(); j++) 
					{
						Node cNode = childNodes.item(j);
						if (cNode instanceof Element) 
						{
							Cell cellItem = new Cell();
							cellItem.setXs(Integer.parseInt(cNode.getAttributes().getNamedItem("xs").getNodeValue()));
							cellItem.setYs(Integer.parseInt(cNode.getAttributes().getNamedItem("ys").getNodeValue()));
							cellItem.setSs(Integer.parseInt(cNode.getAttributes().getNamedItem("ss").getNodeValue()));
							cellItem.setPs(cNode.getAttributes().getNamedItem("ps").getNodeValue());
							cellItem.setDs(Integer.parseInt(cNode.getAttributes().getNamedItem("ds").getNodeValue()));
							cellItem.setCs(Integer.parseInt(cNode.getAttributes().getNamedItem("cs").getNodeValue()));

							floorItem.getCells().add(cellItem);

						}
					}
					floorList.add(floorItem);
				}

			}
			home.setFloors(floorList);

			return home;
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
		catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}

}
