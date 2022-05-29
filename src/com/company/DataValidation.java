package com.company;

public class DataValidation {
    static public Object validate(Column column,String value) throws IncorrectDataTypeException {
        if(value.isEmpty()){
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
            } catch(Exception e) {
                throw new IncorrectDataTypeException("Incorrect data type");
            }
            return value;
        }
        else if(column.getTypeOfData().equals(TypeOfData.INTEGER))
        {
            try {
                Integer val =  Integer.parseInt(value);
            } catch(Exception e) {
                throw new IncorrectDataTypeException("Incorrect data type");
            }
           return value;
        }
    return "NULL";
    }
}
