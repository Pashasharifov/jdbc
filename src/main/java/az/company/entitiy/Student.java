package az.company.entitiy;

public class Student {
    private Integer studentId;
    private String name;
    private String surname;
    private Integer birthOfDate;
    private String studentNumber;

    public Student(Integer studentId, String name, String surname, Integer birthOfDate, String studentNumber) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.birthOfDate = birthOfDate;
        this.studentNumber = studentNumber;
    }

    public Integer getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Integer getBirthOfDate() {
        return birthOfDate;
    }
    public String getStudentNumber() {
        return studentNumber;
    }
}
