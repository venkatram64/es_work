package com.venkat.es.esdemo.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {

    Employee emp;

    @Before
    public void setup(){
        emp = new Employee();
    }

    @Test
    public void empAdd7Test(){
        List<Employee> empList = Arrays.asList(
                new Employee(1,"Venkatram","Veerareddy","Male",52),
                new Employee(1,"Arjun","Koduru","Male",19),
                new Employee(1,"Laxman","Bora","Male",47),
                new Employee(1,"Ram","Advani","Male",27)
        );

        //sort by last name, custom order
        Collections.sort(empList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });

        //create a method to print
        emp.printAll(empList);

        //create a method to print only names with start "V"
        emp.printByFilter(empList,"V");

        //create a method to print only names with start "A"
        emp.printByCondition(empList, new Condition() {
            @Override
            public boolean test(Employee e) {
                return e.getLastName().startsWith("A");
            }
        });
    }

    @Test
    public void empAdd8Test(){
        List<Employee> empList = Arrays.asList(
                new Employee(1,"Venkatram","Veerareddy","Male",52),
                new Employee(1,"Arjun","Koduru","Male",19),
                new Employee(1,"Laxman","Bora","Male",47),
                new Employee(1,"Ram","Advani","Male",27)
        );

        //sort by last name, custom order

        Collections.sort(empList, (o1, o2)->{
            return o1.getLastName().compareTo(o2.getLastName());
        });

        //create a method to print
        emp.printByCondition(empList,e->true);

        //create a method to print only names with start "V"
        emp.printByCondition(empList,e -> e.getLastName().startsWith("A"));

        //create a method to print only names with start "A"
        emp.printByCondition(empList, e -> e.getLastName().startsWith("A"));
    }
}
