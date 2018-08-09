package com.venkat.es.esdemo.model;

import java.util.List;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;

    public Employee(){}

    public Employee(int id, String firstName, String lastName, String gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    public void printAll(List<Employee> empList){
        for(Employee e: empList){
            System.out.println(e);
        }
    }

    public void printByFilter(List<Employee> empList, String filter){
        for(Employee e: empList){
            if(e.getLastName().startsWith(filter)) {
                System.out.println(e);
            }
        }
    }

    public void printByCondition(List<Employee> empList, Condition condition){
        for(Employee e: empList){
            if(condition.test(e)) {
                System.out.println(e);
            }
        }
    }
}


