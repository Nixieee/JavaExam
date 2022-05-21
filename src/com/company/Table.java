package com.company;

import java.util.ArrayList;
import java.util.TreeSet;

public class Table {
    TreeSet<Column> listOfColumns = new TreeSet<>();
    ArrayList<Row> rows = new ArrayList<>();

    public void addColumns(Column column) {
        this.listOfColumns.add(column);

    }

    public void addRows(Row row) {
        this.rows.add(row);
    }

    public Column getColumn (String columnName) throws ColumnException{
        for (Column c : listOfColumns){
            if(c.getColumnName().equals(columnName)){
                return c;
            }
        }
        throw new ColumnException("There is no such column!");
    }

    public TreeSet<Column> getListOfColumns() {
        return listOfColumns;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "Table{" +
                "listOfColumns=" + listOfColumns +
                ", rows=" + rows +
                '}';
    }
}
