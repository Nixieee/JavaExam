package com.company.table;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class TableOperations {

    static public LinkedHashSet<Row> select(Table table, String columnName, String value){
        LinkedHashSet<Row> list = new LinkedHashSet<>();
        Column column = table.getColumn(columnName);
        ArrayList<Row> rows = table.getRows();
        for (Row r : rows) {
            if (r.values.containsKey(column) && r.values.containsValue(value)) {
                list.add(r);
            } //else throw new Exception("There is no such record in that column!");
        }
        return list;
    }

    static public void update(Table table,String searchColumnName, String searchValue, String targetColumnName, String targetValue){
        Column searchColumn = table.getColumn(searchColumnName);
        Column targetColumn = table.getColumn(targetColumnName);
        ArrayList<Row> rows = table.getRows();
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

    static public void delete(Table table,String searchColumnName, String searchValue){
        Column searchColumn = table.getColumn(searchColumnName);
        ArrayList<Row> rowsToDelete = new ArrayList<>();
        ArrayList<Row> rows = table.getRows();
        for (Row r : rows) {
            try {
                if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                    rowsToDelete.add(r);
                }
            } catch (Exception e) {
                System.out.println("No such row!");
            }
        }
        table.getRows().removeAll(rowsToDelete);
    }

    static public int count(Table table,String searchColumnName, String searchValue) {
        Column searchColumn = table.getColumn(searchColumnName);
        ArrayList<Row> rows = table.getRows();
        int count = 0;
        for (Row r : rows) {
            if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                count++;
            }
        }
        return count;
    }

    static public void aggregate(Table table,String searchColumnName, String searchValue, String targetColumnName, String function) {
        Column searchColumn = table.getColumn(searchColumnName);
        Column targetColumn = table.getColumn(targetColumnName);
        ArrayList<Row> rows = table.getRows();
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


    static public Table innerJoin(Table tableOne,String tableOneColumnName,Table tableTwo,String tableTwoColumnName)
    {
        Column tableOneColumn = tableOne.getColumn(tableOneColumnName);
        Column tableTwoColumn = tableTwo.getColumn(tableTwoColumnName);
        Table innerJoin = new Table(tableOne.getTableName()+tableTwo.getTableName());
        innerJoin.addColumns(tableOneColumn);
        innerJoin.addColumns(tableTwoColumn);
        for(Row rOne: tableOne.getRows())
        {
            for(Row rTwo: tableTwo.getRows())
            {
                if(rOne.values.get(tableOneColumn).equals(rTwo.values.get(tableTwoColumn))){
                    innerJoin.addRows(rOne);
                };
            }
        }
        return innerJoin;
    }
}
