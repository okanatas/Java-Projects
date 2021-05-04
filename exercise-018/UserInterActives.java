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

import java.sql.*;

/**
 * This class contains helper functions and default values.
 * @author Okan Atas, created on April 18, 2021
 * @version 1.0.0
 */
public class UserInterActives {
    private static Connection connection;
    /** sql statement */
    public static Statement statement;

    /** students default values */
    public static String[][] students = {
            {"abc", "p1", "Kyle Wright", "a@senecacollege.ca"},
            {"cde", "p2", "Yao Mi", "c@senecacollege.ca"},
            {"wbc", "p3", "Jack Jill", "w@senecacollege.ca"}
    };

    /** exercises default values */
    public static String[][] exercises = {
            {"a@senecacollege.ca", "e1", "10"},
            {"a@senecacollege.ca", "e2", "10"},
            {"w@senecacollege.ca", "e1", "4"},
            {"w@senecacollege.ca", "e4", "20"}
    };

    /** logs default values */
    public static String[][] logs = {
            {"abc", "e1", "9", "1"},
            {"wbc", "e2", "7", "1"}
    };

    /**
     * This method sets the sql connection.
     * @param databaseName database name
     * @param connectionRoute connection route
     * @throws SQLException exception
     */
    public static void setConnection(String databaseName, String connectionRoute)
            throws SQLException {
        String connectionBase = "jdbc:sqlite:";
        String connectionString = connectionBase + connectionRoute + "\\" + databaseName + ".db";

        connection = DriverManager.getConnection(connectionString);

        statement = connection.createStatement();
    }

    /**
     * This method closes the resources.
     * @throws SQLException exception
     */
    public static void closeResources() throws SQLException {
        statement.close();
        connection.close();
    }

    /**
     * This method validates Yes/No response of user.
     * @param response user response
     * @return boolean
     */
    public static boolean validateYesNo(String response) {
        if(response.equals("Y") || response.equals("N"))
        {
            return true;
        } else {
            System.out.println("Please enter Y or N");
            return false;
        }
    }
}
