package com.company;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class Database {
    private String name;
    static ArrayList<Table> listofTables = new ArrayList<>();


    public void setName(String name){
        this.name = name;
    }

    public void addTable(Table table) {
        listofTables.add(table);
    }

    public void addTableFromFile(String fileLocation) throws Exception {
        if(!listofTables.contains(TableXMLParser.queryTable(fileLocation))) {
            listofTables.add(TableXMLParser.queryTable(fileLocation));
        }else System.out.println("Table already exists!");
    }

    public void getTables(){
      for(Table t : listofTables){
          System.out.println(t.getTableName());
      }
    }

    public Table getTable(String tableName) throws Exception {
        for(Table t : listofTables){
            if (t.getTableName().equals(tableName)){
                return t;
            }
        }
        System.out.println("There is no such table!");
        return null;
    }

    public String getName() {
        return name;
    }

    public void createFile(String fileName){
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

            for(Table t:listofTables) {
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

    public void importTable(Table table){
        listofTables.add(table);
    }

    public void openFile(String fileLocation){
        try {
            File inputFile = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("table");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    this.addTable(TableXMLParser.queryTable(eElement.getAttribute("uri")));
                }
            }
        } catch (Exception e) {
            System.out.println("There is no such database!");
        }
    }
}

