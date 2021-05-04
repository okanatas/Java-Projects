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

import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class created to test created tables.
 * @author Okan Atas, created on April 18, 2021
 * @version 1.0.0
 */
public class Tester {
    /**
     * The main method of Tester.
     * @param args command line arguments
     * @throws SQLException exception
     */
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String response = "Y";
        int i = -1;

        // Set connection
        UserInterActives.setConnection("LiveLab","src\\database" );

        // Firstly DROP TABLE IF EXISTS
        AGSStudent.dropTable();
        ExerciseAssigned.dropTable();
        AGSLog.dropTable();

        // CREATE TABLE
        AGSStudent.createTable();
        ExerciseAssigned.createTable();
        AGSLog.createTable();

        // INSERT ALL DEFAULT VALUES
        for(String[] student : UserInterActives.students){
            AGSStudent.insertValues(student[0],student[1],student[2],student[3]);
        }

        for(String[] exercise : UserInterActives.exercises){
            ExerciseAssigned.insertValues(exercise[0],exercise[1],exercise[2]);
        }

        for(String[] log : UserInterActives.logs){
            if(log.length == 4){
                AGSLog.insertValues(log[0],log[1],log[2],log[3]);
            }else{
                AGSLog.insertValues(log[0],log[1]);
            }
        }

        // PRINT BEGINNING TABLES
        AGSStudent.printTable();
        System.out.println();
        ExerciseAssigned.printTable();
        System.out.println();
        AGSLog.printTable();
        System.out.println();

        // NEW LOGS PROCESS
        String[][] afterLogs = new String[1][4];
        // EXAMPLE: String[][] afterLogs = {{"abc", "e2"}, {"wbc", "e1"}, {"cde", "e1"}, {"cde", "e4"}};
        while (response.equals("Y")){
            do{
                System.out.print("Do you want to assign students for exercise? ( Y/N ) : ");
                response = scanner.nextLine().toUpperCase();
            }while (!UserInterActives.validateYesNo(response));

            if(response.equals("Y")){
                i++;

                if(i > 0){
                    String[][] copy = new String[afterLogs.length][4];
                    for(int j=0; j<afterLogs.length; j++){
                        System.arraycopy(afterLogs[j], 0, copy[j], 0, 4);
                    }

                    afterLogs = new String[i+1][4];
                    for(int j=0; j<copy.length; j++){
                        System.arraycopy(copy[j], 0, afterLogs[j], 0, 4);
                    }
                }

                String innerResponse;
                System.out.print("Enter username: ");
                afterLogs[i][0] = scanner.nextLine();

                System.out.print("Enter exercise name: ");
                afterLogs[i][1] = scanner.nextLine();

                do{
                    System.out.print("Has the student submitted the exercise? ( Y/N ) : ");
                    innerResponse = scanner.nextLine().toUpperCase();
                }while (!UserInterActives.validateYesNo(innerResponse));

                if(innerResponse.equals("Y")){
                    afterLogs[i][3] = "1";

                    System.out.print("Enter score: ");
                    afterLogs[i][2] = scanner.nextLine();
                }
            }
        }

        for(String[] log : afterLogs){
            if(log[3] != null){
                AGSLog.insertValues(log[0],log[1],log[2],log[3]);
            }else{
                AGSLog.insertValues(log[0],log[1]);
            }
        }

        // print final table
        AGSLog.printTable();
        System.out.println();

        // close resources
        UserInterActives.closeResources();
    }
}
