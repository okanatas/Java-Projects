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
 * This class includes executions and print for the AGSStudent Table.
 * @author Okan Atas, created on April 18, 2021
 * @version 1.0.0
 */
public class AGSStudent {
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULL_NAME = "fullName";
    private static final String COLUMN_EMAIL = "instructorEmail";

    /** primary key */
    private static final String PK_AGS_STUDENT = "pkAGSStudent";

    /** table name */
    public static String TABLE_AGS_STUDENT = "AGSStudent";

    /**
     * This method creates table.
     * @throws SQLException exception
     */
    public static void createTable() throws SQLException {
        // CREATE TABLE
        UserInterActives.statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_AGS_STUDENT + " ("
                + COLUMN_USERNAME + " varchar(50) not NULL, "
                + COLUMN_PASSWORD + " varchar(50) not NULL, "
                + COLUMN_FULL_NAME + " varchar(200) not NULL, "
                + COLUMN_EMAIL + " varchar(100) not NULL, "
                + "constraint " + PK_AGS_STUDENT + " primary key(" + COLUMN_USERNAME + "))");
    }

    /**
     * This method inserts the values.
     * @param username username
     * @param password password
     * @param fullName full name
     * @param email email
     * @throws SQLException exception
     */
    public static void insertValues(String username, String password,
                                    String fullName, String email) throws SQLException {

        String valuesStr = "'" + username + "'" + ", "
                + "'" + password + "'" + ", "
                + "'" + fullName + "'" + ", "
                + "'" + email + "'";

        UserInterActives.statement.execute("INSERT INTO " + TABLE_AGS_STUDENT + " (" + COLUMN_USERNAME + ", "
                + COLUMN_PASSWORD + ", " + COLUMN_FULL_NAME + ", " + COLUMN_EMAIL + ")" +
                "VALUES(" + valuesStr + ")");
    }

    /**
     * This method drops the table.
     * @throws SQLException exception
     */
    public static void dropTable() throws SQLException {
        // DROP IF EXIST
        UserInterActives.statement.execute("DROP TABLE IF EXISTS " + TABLE_AGS_STUDENT);
    }

    /**
     * This method prints the table.
     * @throws SQLException exception
     */
    public static void printTable() throws SQLException {
        ResultSet results = UserInterActives.statement.executeQuery("SELECT * FROM " + TABLE_AGS_STUDENT);

        System.out.format("TABLE : %s\n", TABLE_AGS_STUDENT);
        System.out.format("%-20s%-20s%-20s%-25s\n", COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_FULL_NAME, COLUMN_EMAIL);
        System.out.format("%-20s%-20s%-20s%-25s\n", "===============", "===============", "===============", "==================");
        while(results.next()) {
            System.out.format("%-20s%-20s%-20s%-25s\n", results.getString(COLUMN_USERNAME), results.getString(COLUMN_PASSWORD),
                    results.getString(COLUMN_FULL_NAME), results.getString(COLUMN_EMAIL));
        }
        results.close();
    }
}
