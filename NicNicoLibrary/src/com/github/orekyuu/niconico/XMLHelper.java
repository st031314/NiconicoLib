package com.github.orekyuu.niconico;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLHelper {

	public static List<String> getXMLValue(String lines,String tagName) throws ParserConfigurationException, SAXException, IOException{
		List<String> list=new ArrayList<String>();
		InputSource is = new InputSource(new StringReader(lines));
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc=builder.parse(is);
		Element root = doc.getDocumentElement();

		if(tagName.equals(root.getNodeName())){
			list.add(root.getTextContent());
		}

		traverse(root,tagName,list);
		return list;
	}

	private static void traverse(Node re,String tagName,List<String> list){
		Node n=re.getFirstChild();
		while(n!=null){
			String name = n.getNodeName();

			if(n.getNodeType() == Node.ELEMENT_NODE){
				if(tagName.equals(name)){
					list.add(n.getTextContent());
					return;
				}
				traverse(n,tagName,list);
			}
			n=n.getNextSibling();
		}
	}
}
