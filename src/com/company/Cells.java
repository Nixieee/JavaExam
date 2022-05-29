package com.company;

import java.util.*;

public class Cells {
    private final LinkedHashMap<Column, Object> cells = new LinkedHashMap<>();

    public void addCell(Column column,String value) throws IncorrectDataTypeException {
            cells.put(column,DataValidation.validate(column,value));
    }

    public LinkedHashMap<Column, Object> getCells() {
        return cells;
    }
}

