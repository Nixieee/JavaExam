package com.company;

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

    public LinkedHashSet<Row> select(int columnIndex, String value) throws Exception {
        LinkedHashSet<Row> list = new LinkedHashSet<>();
        Column column = listOfColumns.get(columnIndex);
        for (Row r : rows) {
            if (r.values.containsKey(column) && r.values.containsValue(value)) {
                list.add(r);
            } //else throw new Exception("There is no such record in that column!");
        }
        return list;
    }

    public void update(int searchColumnIndex, String searchValue, int targetColumnIndex, String targetValue){
        Column searchColumn = listOfColumns.get(searchColumnIndex);
        Column targetColumn = listOfColumns.get(targetColumnIndex);
        for (Row r : rows) {
            try {
                if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                    if (r.values.containsKey(targetColumn)) {
                        try {
                            r.values.replace(targetColumn, DataValidation.validate(targetColumn, targetValue));
                        } catch (Exception e) {
                            System.out.println("Invalid data type!");
                        }
                    } else System.out.println("No such target column");
                }

            } catch (Exception e) {
                System.out.println("No such search column and value!");
            }
        }
    }

    public void delete(int searchColumnIndex, String searchValue) throws Exception {
        Column searchColumn = listOfColumns.get(searchColumnIndex);
        ArrayList<Row> rowsToDelete = new ArrayList<>();
        for (Row r : rows) {
            try {
                if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                    rowsToDelete.add(r);
                }
            } catch (Exception e) {
                throw new Exception("No such row");
            }
        }
        rows.removeAll(rowsToDelete);
    }

    public int count(int searchColumnIndex, String searchValue) {
        Column searchColumn = listOfColumns.get(searchColumnIndex);
        int count = 0;
        for (Row r : rows) {
            if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                count++;
            }
        }
        return count;
    }

    public double aggregate(int searchColumnIndex, String searchValue, int targetColumnIndex, String function) {
        //TODO:Complete it pls!!
        Column searchColumn = listOfColumns.get(searchColumnIndex);
        Column targetColumn = listOfColumns.get(targetColumnIndex);
        double sum = 0.0;
        double product = 1.0;
        double minimum = Double.parseDouble(rows.get(0).values.get(targetColumn).toString());
        double maximum = Double.parseDouble(rows.get(0).values.get(targetColumn).toString());
        if(searchColumn.getTypeOfData() == TypeOfData.INTEGER || searchColumn.getTypeOfData() == TypeOfData.DOUBLE &&
                targetColumn.getTypeOfData() == TypeOfData.INTEGER || targetColumn.getTypeOfData() == TypeOfData.DOUBLE ) {
            for (Row r : rows) {
                if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                    switch (function) {
                        case "sum": {
                            sum += Double.parseDouble(r.values.get(targetColumn).toString());
                        }
                        break;
                        case "product": {
                            product *= Double.parseDouble(r.values.get(targetColumn).toString());
                            sum = product;
                        }
                        break;
                        case "minimum": {
                            if (Double.parseDouble(r.values.get(targetColumn).toString()) < minimum)
                                minimum = Double.parseDouble(r.values.get(targetColumn).toString());
                            sum = minimum;
                        }
                        break;
                        case "maximum": {
                            if (Double.parseDouble(r.values.get(targetColumn).toString()) > maximum)
                                maximum = Double.parseDouble(r.values.get(targetColumn).toString());
                            sum = maximum;
                        }
                        break;
                    }
                }
            }
            System.out.println(sum);
        }
        else System.out.println("Not numbers!");
        return 0.0;
    }

    public void rename(String oldName, String newName) {
        boolean flag = false;
        boolean error = true;
        for (Table t : Database.listofTables) {
            if (t.getTableName().equals(newName)) {
                flag = true;
                break;
            }
        }
        for (Table t : Database.listofTables) {
            if (!flag && t.getTableName().equals(oldName)) {
                t.setTableName(newName);
                error = false;
            }
        }
        if (error) {
            System.out.println("There is a table with that name already!");
        }
    }



    public Table innerJoin(Table tableOne,String tableOneColumnName,Table tableTwo,String tableTwoColumnName)
    {
        Column tableOneColumn = tableOne.getColumn(tableOneColumnName);
        Column tableTwoColumn = tableTwo.getColumn(tableTwoColumnName);
        Table innerJoin = new Table(tableOne.getTableName()+tableTwo.getTableName());
        innerJoin.addColumns(tableOneColumn);
        innerJoin.addColumns(tableTwoColumn);
        for(Row rOne: tableOne.rows)
        {
          for(Row rTwo: tableTwo.rows)
          {
              if(rOne.values.get(tableOneColumn).equals(rTwo.values.get(tableTwoColumn))){
                  innerJoin.addRows(rOne);
              };
          }
        }
      return innerJoin;
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
