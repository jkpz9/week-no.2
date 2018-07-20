package com.king.DOMParser;
import com.king.Model.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Class Parsers Data from *.xml files
public class XMLReader {
	public static ArrayList<Data> readXMLFILE (String pathToFile)
	{       
                ArrayList<Data> list = new ArrayList<>();
		try {
			
			File inputFile = new File(pathToFile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(inputFile);
			
			doc.getDocumentElement().normalize();
			
			// ELEMENT_NODE or ATTRIBUTE_NODE
			// System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("record");
			
			for (int i=0; i<nList.getLength(); i++) {
				Node node = nList.item(i);
				//System.out.println("\nCurrent Element: " + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
                                        
                                        String word, meaning;
                                        word = element
                                                    .getElementsByTagName("word")
                                                    .item(0)
                                                    .getTextContent();
                                        meaning = element
                                                    .getElementsByTagName("meaning")
                                                    .item(0)
                                                    .getTextContent();
//                                        if (word.charAt(0) == '@') {
//                                            word = word.substring(1);
//                                        }
                                        Data obj = new Data(word, meaning);                            
                                     list.add(obj);
				}
			}
			
		} catch ( ParserConfigurationException | SAXException | IOException e) {
                    e.printStackTrace();
                }
                return list;
 
	}

}
