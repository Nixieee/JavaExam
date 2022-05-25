package com.company;

import javax.xml.crypto.Data;
import java.util.*;

public class Cells {
    private final LinkedHashMap<Column, Object> cells = new LinkedHashMap<>();

    public void addCell(Column column,String value) throws IncorrectDataTypeException {
        if(DataValidation.validate(column,value))
            cells.put(column,value);
    }

    public LinkedHashMap<Column, Object> getCells() {
        return cells;
    }
}

