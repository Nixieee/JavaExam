package com.company;

import java.util.*;

public class Table {
    private final String tableName;
    private final ArrayList<Column> listOfColumns = new ArrayList<>();
    private final ArrayList<Row> rows = new ArrayList<>();

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void addColumns(Column column) throws ColumnException{
        if(this.listOfColumns.contains(column)){
            throw new ColumnException("Column already exists!");
        }else
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

    public ArrayList<Column> getListOfColumns() {
        return listOfColumns;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public String describe(){
        return listOfColumns.toString();
    }

    public String print(){
        return rows.toString();
    }

    public LinkedHashSet<Row> select(int columnIndex,String value)throws Exception{
        LinkedHashSet<Row> list = new LinkedHashSet<>();
        Column column = listOfColumns.get(columnIndex);
        for(Row r : rows)
        {
            if(r.values.containsKey(column)&&r.values.containsValue(value))
            {
                list.add(r);
            }else throw new Exception("There is no such record in that column!");
        }
        return list;
    }

    public void update(int searchColumnIndex,String searchValue, int targetColumnIndex, String targetValue) throws Exception,IncorrectDataTypeException
    {
        Column searchColumn = listOfColumns.get(searchColumnIndex);
        Column targetColumn = listOfColumns.get(targetColumnIndex);
        for (Row r : rows)
        {
            try {
                if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                        if (r.values.containsKey(targetColumn)) {
                            try {
                                r.values.replace(targetColumn, DataValidation.validate(targetColumn, targetValue));
                            }catch (Exception e){
                                throw new Exception("Invalid data type!");
                            }
                        }
                       else System.out.println("No such target column");
                    }

            }catch (Exception e){
                throw new Exception("No such search column and value!");
            }
        }
    }
    public void delete(int searchColumnIndex,String searchValue) throws Exception {
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

    public int count(int searchColumnIndex,String searchValue){
        Column searchColumn = listOfColumns.get(searchColumnIndex);
        int count = 0;
        for (Row r : rows) {
                if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                    count++;
                }
        }
        return count;
    }

    public Object aggregate(int searchColumnIndex,String searchValue, int targetColumnIndex,String function) throws Exception {
        Column searchColumn = listOfColumns.get(searchColumnIndex);
        Column targetColumn = listOfColumns.get(targetColumnIndex);
        double sum = 0.0;
        for (Row r : rows) {
            if (r.values.containsKey(searchColumn) && r.values.containsValue(searchValue)) {
                switch(function)
                {
                    case "sum":
                    {
                        sum += Double.parseDouble(r.values.get(targetColumn).toString());
                    }break;
                    case "product":
                    {
                        sum = 1.0;
                        sum *= Double.parseDouble(r.values.get(targetColumn).toString());
                    }
                }
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Table{" +
                "listOfColumns=" + listOfColumns +
                ", rows=" + rows +
                '}';
    }
}
