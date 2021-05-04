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
 * This class includes executions and print for the ExerciseAssigned Table.
 * @author Okan Atas, created on April 18, 2021
 * @version 1.0.0
 */
public class ExerciseAssigned {

    private static final String COLUMN_EMAIL = "instructorEmail";
    private static final String COLUMN_EXERCISE = "exerciseName";
    private static final String COLUMN_MAX_SCORE = "maxScore";

    /** primary key */
    private static final String PK_EXERCISE = "pkCustomExercise";

    /** table name */
    public static String TABLE_EXERCISE = "ExerciseAssigned";

    /**
     * This method creates table.
     * @throws SQLException exception
     */
    public static void createTable() throws SQLException {
        UserInterActives.statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EXERCISE + " ("
                + COLUMN_EMAIL + " varchar(100), "
                + COLUMN_EXERCISE + " varchar(100), "
                + COLUMN_MAX_SCORE + " double default 10, "
                + "constraint " + PK_EXERCISE + " primary key(" + COLUMN_EMAIL + ", " + COLUMN_EXERCISE + "))");
    }

    /**
     * This method inserts the values.
     * @param email email
     * @param exerciseName exercise name
     * @param maxScore max score
     * @throws SQLException exception
     */
    public static void insertValues(String email, String exerciseName, String maxScore) throws SQLException {
        String valuesStr = "'" + email + "'" + ", "
                + "'" + exerciseName + "'" + ", ";

        UserInterActives.statement.execute("INSERT INTO " + TABLE_EXERCISE + " (" + COLUMN_EMAIL + ", " + COLUMN_EXERCISE
                + ", " + COLUMN_MAX_SCORE + ")" +
                "VALUES(" + valuesStr + Double.parseDouble(maxScore) + ")");
    }

    /**
     * This method drops the table.
     * @throws SQLException exception
     */
    public static void dropTable() throws SQLException {
        // DROP IF EXIST
        UserInterActives.statement.execute("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
    }

    /**
     * This method prints the table.
     * @throws SQLException exception
     */
    public static void printTable() throws SQLException {
        ResultSet results = UserInterActives.statement.executeQuery("SELECT * FROM " + TABLE_EXERCISE) ;

        System.out.format("TABLE : %s\n", TABLE_EXERCISE);
        System.out.format("%-25s%-20s%-20s\n", COLUMN_EMAIL, COLUMN_EXERCISE, COLUMN_MAX_SCORE);
        System.out.format("%-25s%-20s%-20s\n", "==================", "===============", "===============");
        while(results.next()) {
            System.out.format("%-25s%-20s%-20.0f\n", results.getString(COLUMN_EMAIL), results.getString(COLUMN_EXERCISE),
                    results.getDouble(COLUMN_MAX_SCORE));
        }
        results.close();
    }
}
