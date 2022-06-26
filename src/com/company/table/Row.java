package com.company.table;

import java.util.*;

public class Row {
    public LinkedHashMap<Column, Object> values = new LinkedHashMap<>();

    public boolean addCell(Column column,String value){
        if(column!=null) {
            Object object = DataValidation.validate(column, value);
            values.put(column, object);
            return object != null;
        }
        else {
            System.out.println("Column doesn't exist!");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}

