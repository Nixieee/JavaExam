package com.company;

import java.util.HashMap;
import java.util.Map;

public class Cells {
    Map<Column, Object> cells = new HashMap<>();

    public void addCell(Column column,String value) throws IncorrectDataTypeException {
        if(column.getTypeOfData().equals(TypeOfData.STRING))
        {
            cells.put(column,value);
        }
        if(column.getTypeOfData().equals(TypeOfData.DOUBLE))
        {
            try {
                Double val =  Double.valueOf(value);
            } catch(Exception e) {
                throw new IncorrectDataTypeException("Incorrect data type");
            }
            cells.put(column,value);

        }
        if(column.getTypeOfData().equals(TypeOfData.INTEGER))
        {
            try {
                Integer val =  Integer.parseInt(value);
            } catch(Exception e) {
                throw new IncorrectDataTypeException("Incorrect data type");
            }
            cells.put(column,value);
        }
    }

    public Map<Column, Object> getCells() {
        return cells;
    }
}

