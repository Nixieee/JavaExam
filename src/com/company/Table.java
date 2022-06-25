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

    public LinkedHashSet<Row> select(String columnName, String value){
        LinkedHashSet<Row> list = new LinkedHashSet<>();
        Column column = this.getColumn(columnName);
        for (Row r : rows) {
            if (r.values.containsKey(column) && r.values.containsValue(value)) {
                list.add(r);
            } //else throw new Exception("There is no such record in that column!");
        }
        return list;
    }

    public void update(String searchColumnName, String searchValue, String targetColumnName, String targetValue){
        Column searchColumn = this.getColumn(searchColumnName);
        Column targetColumn = this.getColumn(targetColumnName);
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

    public void delete(String searchColumnName, String searchValue){
        Column searchColumn = this.getColumn(searchColumnName);
        ArrayList<Row> rowsToDelete = new ArrayList<>();
        for (Row r : rows) {
            try {
                if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                    rowsToDelete.add(r);
                }
            } catch (Exception e) {
                System.out.println("No such row!");
            }
        }
        rows.removeAll(rowsToDelete);
    }

    public int count(String searchColumnName, String searchValue) {
        Column searchColumn = this.getColumn(searchColumnName);
        int count = 0;
        for (Row r : rows) {
            if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                count++;
            }
        }
        return count;
    }

    public void aggregate(String searchColumnName, String searchValue, String targetColumnName, String function) {
        Column searchColumn = this.getColumn(searchColumnName);
        Column targetColumn = this.getColumn(targetColumnName);
        double sum = 0.0;
        double product = 1.0;
        double minimum = Double.parseDouble(rows.get(0).values.get(targetColumn).toString());
        double maximum = Double.parseDouble(rows.get(0).values.get(targetColumn).toString());
        if(targetColumn.getTypeOfData() == TypeOfData.INTEGER || targetColumn.getTypeOfData() == TypeOfData.DOUBLE ) {
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
        //return 0.0;
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
