package az.company.process;

import az.company.connection.DBConnection;
import az.company.entitiy.Student;

import java.sql.*;
import java.util.List;

public class DBProcess {
    private static Connection connection = DBConnection.getConnection();
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    public static void createStudentTable(){
        try {
            String query = "CREATE TABLE student(studentId INT PRIMARY KEY NOT NULL, name VARCHAR(255), surname VARCHAR(255), birthdate INT, studentNumber VARCHAR(30))";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Students table has been created successfully !");
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        DBConnection.closeConnection();
        }
    }
    public static void insertStudent(List<Student> listOfStudents){
        try {
            String query = "INSERT INTO student (studentId, name, surname, birthdate, studentNumber) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            for (Student student : listOfStudents) {
                preparedStatement.setInt(1, student.getStudentId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3, student.getSurname());
                preparedStatement.setInt(4, student.getBirthOfDate());
                preparedStatement.setString(5, student.getStudentNumber());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Data inserted succesfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
                preparedStatement.close();
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
        finally {
            DBConnection.closeConnection();
        }
    }
    public static void updateStudent(Student student){
        String query = "UPDATE student SET name = ? WHERE studentId = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getStudentId());
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
    public static void deleteStudent(Student student){
        String query = "DELETE  FROM student WHERE studentId = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.execute();
            System.out.println("Student deleted correctly !!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DBConnection.closeConnection();
        }
    }
    public static void searchStudent(String word){
        String query = "SELECT * FROM student WHERE name LIKE ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%"+word+"%" );
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("studentId");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int birthdate = resultSet.getInt("birthdate");
                String studentNumber = resultSet.getString("studentNumber");
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
