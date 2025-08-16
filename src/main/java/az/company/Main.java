package az.company;

import az.company.entity.Student;
import az.company.process.DBProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Student student2 = new Student(8, "Pasha", "SHarifov", 2004, "on");
        Student student3 = new Student(9, "Pasha", "SHarifov", 2004, "on");
        Student student4 = new Student(10, "Pasha", "SHarifov", 2004, "on");
        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.add(student2);
        listOfStudents.add(student3);
        listOfStudents.add(student4);
//        DBProcess.insertStudent(listOfStudents);
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        DBProcess.searchStudent(key);
    }
}
