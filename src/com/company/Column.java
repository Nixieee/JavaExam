package com.company;

import java.util.Objects;

public class Column{
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return columnName.equals(column.columnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnName);
    }

    @Override
    public String toString() {
        return "columnName='" + columnName +
                ", typeOfData=" + typeOfData + "\n";
    }

}

