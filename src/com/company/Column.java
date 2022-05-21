package com.company;

public class Column implements Comparable{
    private final String columnName;
    private final TypeOfData typeOfData;

    public Column(String columnName, TypeOfData typeOfData) {
        this.columnName = columnName;
        this.typeOfData = typeOfData;
    }

    public String getColumnName() {
        return columnName;
    }


    public TypeOfData getTypeOfData() {
        return typeOfData;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", typeOfData=" + typeOfData +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.getColumnName().compareTo(((Column)o).getColumnName());
    }
}

