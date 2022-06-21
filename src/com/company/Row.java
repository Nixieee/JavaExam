package com.company;
import java.util.*;

public class Row {
    LinkedHashMap<Column, Object> values = new LinkedHashMap<>();

    public boolean addCell(Column column,String value){
        if(column!=null)
        values.put(column,DataValidation.validate(column,value));
        else {
            System.out.println("Column doesn't exist!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}

