package com.venkat.es.esdemo.service;

import com.venkat.es.esdemo.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaxReaderServiceTest {

    @Autowired
    StaxReaderService staxReaderService;

    @Test
    public void readEmpTest(){
        List<Employee> list = staxReaderService.parseXML("xml/employee.xml");
        for(Employee emp : list){
            System.out.println(emp.getId());
            System.out.println(emp.getFirstName());
            System.out.println(emp.getLastName());
            System.out.println(emp.getGender());
            System.out.println(emp.getAge());
        }
        assert(true);
    }
}
