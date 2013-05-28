package ru.spbau.mayorov.task7;

/**
 * Holds information about student.
 * @author Arseny Mayorov.
 */
public class Student {

    /** Holds student's name. */
    private String name = new String();
    /** Holds student's surname. */
    private String surname = new String();
    /** Holds student's age. */
    private int age = 0;
    /** Holds average of student's grades. */
    private double avgGrade = 0;

    /** Getter for name. */
    public String getName() {
        return name;
    }
    /** Setter for name. */
    public void setName(String name) {
        this.name = name;
    }
    /** Getter for surname. */
    public String getSurname() {
        return surname;
    }
    /** Setter for surname. */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /** Getter for age. */
    public int getAge() {
        return age;
    }
    /** Setter for age. */
    public void setAge(int age) {
        this.age = age;
    }
    /** Getter for average grade. */
    public double getAvgGrade() {
        return avgGrade;
    }
    /** Setter for average grade. */
    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

}
