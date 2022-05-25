package com.company;

public class DataValidation {
    static public boolean validate(Column column,String value) throws IncorrectDataTypeException {
        if(column.getTypeOfData().equals(TypeOfData.STRING))
        {
          return true;
        }
        else if(column.getTypeOfData().equals(TypeOfData.DOUBLE))
        {
            try {
                Double val =  Double.valueOf(value);
            } catch(Exception e) {
                throw new IncorrectDataTypeException("Incorrect data type");
            }
            return true;
        }
        else if(column.getTypeOfData().equals(TypeOfData.INTEGER))
        {
            try {
                Integer val =  Integer.parseInt(value);
            } catch(Exception e) {
                throw new IncorrectDataTypeException("Incorrect data type");
            }
           return true;
        }
    return false;}
}
