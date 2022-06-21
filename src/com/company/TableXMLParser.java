package com.company;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class TableXMLParser {

    public static void XMLParse(Table table,String fileName) {
        ArrayList<Row> rows = table.getRows();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Table element
            Element rootElement = doc.createElement("table");
            // table attribute
            Attr tableName = doc.createAttribute("name");
            tableName.setValue(table.getTableName()); //gets name of the table and adds it as attribute
            rootElement.setAttributeNode(tableName);
            doc.appendChild(rootElement);

            // Row element
            for (Row r : rows) {
                Element row = doc.createElement("row");
                //row attribute
                Attr rowId = doc.createAttribute("id");
                rowId.setValue(String.valueOf(rows.indexOf(r)));
                row.setAttributeNode(rowId);
                rootElement.appendChild(row);
                for (Map.Entry<Column, Object> c : r.values.entrySet()) {
                    Element column = doc.createElement("column");
                    Attr columnName = doc.createAttribute("name");
                    Attr typeOfData = doc.createAttribute("typeOfData");
                    columnName.setValue(c.getKey().getColumnName());
                    column.setAttributeNode(columnName);
                    typeOfData.setValue(c.getKey().getTypeOfData().toString());
                    column.setAttributeNode(typeOfData);
                    column.appendChild(doc.createTextNode((String) c.getValue()));
                    row.appendChild(column);
                }
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File file = new File(fileName);
            StreamResult result = new StreamResult(file);
            table.setFileLocation(file.getAbsolutePath());
            Attr tableUri = doc.createAttribute("uri");
            tableUri.setValue(table.getFileLocation());
            rootElement.setAttributeNode(tableUri);
            transformer.transform(source, result);
            // Output to console for testing
            // StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Table queryTable(String fileLocation) throws Exception {
        try {
            File inputFile = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("row");
            Table table = new Table(doc.getDocumentElement().getAttribute("name"));
            table.setFileLocation(doc.getDocumentElement().getAttribute("uri"));

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Row row = new Row();
                Node nNode = nList.item(temp);

                Element eElement = (Element) nNode;

                NodeList columnList = eElement.getElementsByTagName("column");

                for (int count = 0; count < columnList.getLength(); count++) {
                    Node node1 = columnList.item(count);

                    if (node1.getNodeType() == node1.ELEMENT_NODE) {
                        Element column = (Element) node1;
                        if(temp == 0) {
                            table.addColumns(new Column(column.getAttribute("name"), TypeOfData.valueOf(column.getAttribute("typeOfData"))));
                        }
                        row.addCell(table.getColumn(column.getAttribute("name")),column.getTextContent());
                    }
                }
                table.addRows(row);
            }

         return table;
        } catch (Exception e) {
            e.printStackTrace();
        }
    throw new Exception("Couldn't create table");
    }
}


