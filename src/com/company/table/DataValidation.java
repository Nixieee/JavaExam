package com.company.table;

public class DataValidation {
    static public Object validate(Column column, String value){
        if(value == null || value.isEmpty()){
            return "NULL";
        }
        if(column.getTypeOfData().equals(TypeOfData.STRING))
        {
          return value;
        }
        else if(column.getTypeOfData().equals(TypeOfData.DOUBLE))
        {
            try {
                Double val =  Double.valueOf(value);
                return value;
            } catch(Exception e) {
                System.out.println("Incorrect data type");
            }

        }
        else if(column.getTypeOfData().equals(TypeOfData.INTEGER))
        {
            try {
                Integer val =  Integer.parseInt(value);
                return value;
            } catch(Exception e) {
                System.out.println("Incorrect data type");
            }

        }
    return null;
    }
}
