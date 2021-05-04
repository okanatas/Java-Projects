/**********************************************
 Workshop #11
 Course: JAC444 - Winter 2021
 Last Name: Atas
 First Name: Okan
 ID: 130627193
 Section: NFF
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature: Okan Atas
 Date: April 18, 2021
 **********************************************/

package task;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class includes executions and print for the AGSLog Table.
 * @author Okan Atas, created on April 18, 2021
 * @version 1.0.0
 */
public class AGSLog {

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EXERCISE = "exerciseName";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_SUBMIT = "submitted";

    /** primary key */
    public static final String PK_LOG = "pkLog";

    /** table name */
    public static String TABLE_AGS_LOG = "AGSLog";

    private static int counter = 0;

    /**
     * This method creates table.
     * @throws SQLException exception
     */
    public static void createTable() throws SQLException {
        UserInterActives.statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_AGS_LOG + " ("
                + COLUMN_USERNAME + " varchar(50), "
                + COLUMN_EXERCISE + " varchar(100), "
                + COLUMN_SCORE + " double default NULL, "
                + COLUMN_SUBMIT + " bit default 0, "
                + "constraint " + PK_LOG + " primary key(" + COLUMN_USERNAME + ", " + COLUMN_EXERCISE + "))");
    }

    /**
     * This method inserts values.
     * @param username username
     * @param exerciseName exercise name
     * @param score score
     * @param submitted submitted (byte)
     */
    public static void insertValues(String username, String exerciseName,
                                    String score, String submitted) {

        counter++;
        String valuesStr = "'" + username + "'" + ", "
                + "'" + exerciseName + "'" + ", ";

        try {
            UserInterActives.statement.execute("INSERT INTO " + TABLE_AGS_LOG + " (" + COLUMN_USERNAME + ", " + COLUMN_EXERCISE
                    + ", " + COLUMN_SCORE + ", " + COLUMN_SUBMIT + ")" +
                    "VALUES(" + valuesStr + Double.parseDouble(score) + ", " + Byte.parseByte(submitted) + ")");

            if(counter > 2){
                System.out.println("\nTable " + TABLE_AGS_LOG + " updated...\n");
            }
        } catch (SQLException e) {
            System.out.println("\nTable " + AGSLog.TABLE_AGS_LOG + " NOT updated...\n");
            e.printStackTrace();
        }
    }

    /**
     * This method inserts values.
     * @param username username
     * @param exerciseName exercise name
     */
    public static void insertValues(String username, String exerciseName){

        String valuesStr = "'" + username + "'" + ", "
                + "'" + exerciseName + "'" + ", ";

        double defaultScore = 0;
        byte defaultSubmitted = 0;

        try {
            UserInterActives.statement.execute("INSERT INTO " + TABLE_AGS_LOG + " (" + COLUMN_USERNAME + ", " + COLUMN_EXERCISE
                    + ", " + COLUMN_SCORE + ", " + COLUMN_SUBMIT + ")" +
                    "VALUES(" + valuesStr + defaultScore + ", " + defaultSubmitted + ")");

            System.out.println("\nTable " + TABLE_AGS_LOG + " updated...\n");
        } catch (SQLException e) {
            System.out.println("\nTable " + AGSLog.TABLE_AGS_LOG + " NOT updated...\n");
            e.printStackTrace();
        }
    }

    /**
     * This method drops the table.
     * @throws SQLException exception
     */
    public static void dropTable() throws SQLException {
        // DROP IF EXIST
        UserInterActives.statement.execute("DROP TABLE IF EXISTS " + TABLE_AGS_LOG);
    }

    /**
     * This method prints the table.
     * @throws SQLException exception
     */
    public static void printTable() throws SQLException {
        ResultSet results = UserInterActives.statement.executeQuery("SELECT * FROM " + TABLE_AGS_LOG) ;

        System.out.format("TABLE : %s\n", TABLE_AGS_LOG);
        System.out.format("%-20s%-20s%-20s%-20s\n", COLUMN_USERNAME, COLUMN_EXERCISE, COLUMN_SCORE, COLUMN_SUBMIT);
        System.out.format("%-20s%-20s%-20s%-20s\n", "===============", "===============", "===============", "===============");
        while(results.next()) {
            System.out.format("%-20s%-20s%-20.0f%-20d\n", results.getString(COLUMN_USERNAME), results.getString(COLUMN_EXERCISE),
                    results.getDouble(COLUMN_SCORE), results.getByte(COLUMN_SUBMIT));
        }
        results.close();
    }
}
