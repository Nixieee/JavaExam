package com.company.xml.io;

import com.company.database.Database;
import com.company.table.Table;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DatabaseXMLParser {
    static public void createFile(Database database, String fileName){
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Database element
            Element rootElement = doc.createElement("database");
            // Database attribute
            Attr dbName = doc.createAttribute("name");
            dbName.setValue(fileName); //gets name of the table and adds it as attribute
            rootElement.setAttributeNode(dbName);
            doc.appendChild(rootElement);

            for(Table t:database.getTables()) {
                Element tableElement = doc.createElement("table");
                // table attribute
                Attr tableName = doc.createAttribute("name");
                Attr tableURI = doc.createAttribute("uri");
                tableName.setValue(t.getTableName()); //gets name of the table and adds it as attribute
                tableElement.setAttributeNode(tableName);
                tableURI.setValue(t.getFileLocation());
                tableElement.setAttributeNode(tableURI);
                rootElement.appendChild(tableElement);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

            // Output to console for testing
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static public void openFile(Database database){
        try {
            File inputFile = new File(database.getName());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("table");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    database.addTable(TableXMLParser.queryTable(eElement.getAttribute("uri")));
                }
            }
        } catch (Exception e) {
            System.out.println("There is no such database!");
        }
    }
}

