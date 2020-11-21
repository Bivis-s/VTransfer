package by.bivis.database;

public interface SQLAble {
    /*
    object can be written into a database

    toSQLString() should return String fields separated by ','
    example of return: "'one', 'two', 'three'"

    ! special attention to the order of fields !
     */
    String toSQLString();
}
