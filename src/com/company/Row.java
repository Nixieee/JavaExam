package com.company;

import java.util.*;

public class Row {
    LinkedHashMap<Column, Object> values = new LinkedHashMap<>();

    public void addCell(Column column,String value) throws IncorrectDataTypeException {
        values.put(column,DataValidation.validate(column,value));
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}

