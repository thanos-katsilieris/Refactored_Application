package input;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLInput extends Input {
	
	private NodeList salesmanNodes;
	private NodeList receiptsNodeList;
	int parsedReceipts = 0;
	
	public XMLInput(File receiptFileXML ){
		inputFile = receiptFileXML;		
	}
	
	public void setUpReceiptStructureForReading() throws ParserConfigurationException, 
															IOException, SAXException  {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder docBuilder;
			
			docBuilder = docBuilderFactory.newDocumentBuilder();
			
	    	Document doc = docBuilder.parse(inputFile);
	    	doc.getDocumentElement().normalize();
	    	salesmanNodes = doc.getElementsByTagName("Salesman");     
		} catch (ParserConfigurationException | IOException e) {            
            throw new IOException("Error in XMLInput setup: Issues while creating DocumentBuilder"
            		+ " or parsing the file or file doesn't exist");
        } catch (SAXException e) {           
            throw new SAXException("Error in XMLInput setup: SAX exception during parsing");
        }
	}

	@Override
	public String readData(String dataName) {
		String value = "";
		try {
			if(dataName.equals("Name")) {
				value = ((Element) salesmanNodes.item(0)).getElementsByTagName("Name").
		    			item(0).getChildNodes().item(0).getNodeValue().trim();
			}
			if(dataName.equals("AFM")) {
				value = ((Element) salesmanNodes.item(0)).getElementsByTagName("AFM").
		        		item(0).getChildNodes().item(0).getNodeValue().trim();     
			}else {
				receiptsNodeList = ((Element) salesmanNodes.item(0)).getElementsByTagName("Receipt");
				Element element = (Element) receiptsNodeList.item(parsedReceipts);
			    if (element != null) {
			        NodeList nodeList = element.getElementsByTagName(dataName.trim());
			        if (nodeList.getLength() > 0) {
			            value = nodeList.item(0).getChildNodes().item(0).getNodeValue().trim();
			        }
			    }
			}
		} catch (NullPointerException e) {         
            throw new NullPointerException("Error in XMLInput readData: Null pointer "
            		+ "exception when accessing XML nodes");
		}
		return value;	
	}
	
	@Override
	protected void readUnusedLines() throws IOException {

	}
	
	@Override
	protected int checkIfLoopFinished() {
		parsedReceipts++;
		
		if(parsedReceipts == receiptsNodeList.getLength()) {
			return 1;
		}
		return 0;
	}
}
