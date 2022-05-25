package com.company;

import java.util.*;

public class Row {
    LinkedHashMap<Column, Object> values;

    public Row(LinkedHashMap<Column, Object> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}

