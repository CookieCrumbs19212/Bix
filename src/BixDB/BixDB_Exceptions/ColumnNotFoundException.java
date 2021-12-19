package BixDB.BixDB_Exceptions;

public class ColumnNotFoundException extends Exception {
    public ColumnNotFoundException(String column_name) {
        super("The column \"" + column_name + "\" is not found.");
    }
}