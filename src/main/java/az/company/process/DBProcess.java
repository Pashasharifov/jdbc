package az.company.process;

import az.company.connection.DBConnection;

import java.sql.*;

public class DBProcess {
    private static Connection connection = DBConnection.getConnection();
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    public static void createStudentTable(){
        try {
            String query = "CREATE TABLE student(studentId INT PRIMARY KEY NOT NULL, name VARCHAR(255), surname VARCHAR(255), birthdate INT, studentNumber VARCHAR(30))";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Students table has been created successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        DBConnection.closeConnection();
    }
    public static void insertStudent(){
        try {
            String query = "INSERT INTO student (studentId, name, surname, birthdate, studentNumber) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,3);
            preparedStatement.setString(2, "Sharifov");
            preparedStatement.setString(3, "Test");
            preparedStatement.setInt(4, 2003);
            preparedStatement.setString(5, "ombir");
            preparedStatement.execute();
            System.out.println("Data inserted succesfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            DBConnection.closeConnection();
        }
    }
    public static void updateStudent(){
        String query = "UPDATE student SET name = ? WHERE studentId = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "updated Pasha");
            preparedStatement.setInt(2, 2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStatement.executeUpdate();
            System.out.println("Student updated succefsully qaqash");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         finally {
            DBConnection.closeConnection();
        }
    }
    public static void deleteStudent(){
        String query = "DELETE  FROM student WHERE studentId = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 3);
            preparedStatement.execute();
            System.out.println("Student deleted !!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DBConnection.closeConnection();
        }
    }
    public static void searchStudent(){
        String query = "SELECT * FROM student WHERE studentId = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 2);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("studentId");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int birthdate = resultSet.getInt("birthdate");
                int studentNumber = resultSet.getInt("studentNumber");
            System.out.println("ID: " + id +
                    ", Name: " + name +
                    ", Surname: " + surname +
                    ", Birthdate: " + birthdate +
                    ", StudentNumber: " + studentNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DBConnection.closeConnection();
        }

    }
}
