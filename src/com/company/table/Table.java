package com.company.table;

import java.util.*;

public class Table {
    private String tableName;
    private String fileLocation;
    private final ArrayList<Column> listOfColumns = new ArrayList<>();
    private final ArrayList<Row> rows = new ArrayList<>();

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void addColumns(Column column){
        if (this.listOfColumns.contains(column)) {
            System.out.println("Column already exists!");
        } else {
            this.listOfColumns.add(column);
            if (!rows.isEmpty()) {
                for (Row r : rows) {
                    r.addCell(column, null);
                }
            }
        }
    }

    public void addRows(Row row) {
        this.rows.add(row);

    }

    public Column getColumn(String columnName){
        for (Column c : listOfColumns) {
            if (c.getColumnName().equals(columnName)) {
                return c;
            }
        }
        System.out.println("There is no such column!");
        return null;
    }

    public ArrayList<Column> getListOfColumns() {
        return listOfColumns;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public String describe() {
        return listOfColumns.toString();
    }

    public String print() {
        return rows.toString();
    }


    @Override
    public String toString() {
        return "Table{" +
                "listOfColumns=" + listOfColumns +
                ", rows=" + rows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(tableName, table.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName);
    }
}
