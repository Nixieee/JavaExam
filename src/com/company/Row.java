package com.company;

import java.util.*;

public class Row {
    Map<Column, Object> values;

    public Row(Map<Column, Object> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}

