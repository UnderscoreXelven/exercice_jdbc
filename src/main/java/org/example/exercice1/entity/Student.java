package org.example.exercice1.entity;

public class Student {
    //Attributs
    private int id;
    private String firstName;
    private String lastName;
    private String graduationDate;
    private int classNumber;

    //Constructeurs
    public Student(String firstName, String lastName, String graduationDate, int classNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.graduationDate = graduationDate;
        this.classNumber = classNumber;
    }

    public Student(int id, String firstName, String lastName, String graduationDate, int classNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.graduationDate = graduationDate;
        this.classNumber = classNumber;
    }

    //Méthodes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", graduationDate='" + graduationDate + '\'' +
                ", classNumber=" + classNumber +
                '}';
    }
}
