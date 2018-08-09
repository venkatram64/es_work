package com.venkat.es.esdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTest {


    @Test
    public void annonymousProcessTest(){
        //annonymous inner class test
        Process p = new Process() {
            @Override
            public void perform() {
                System.out.println("Good Perform with inner class!!!");
            }
        };
        p.perform();

        assert(true);
    }

    @Test
    public void processTest(){
        //lambda
        Process p = () -> System.out.println("Good performer!");
        p.perform();

        assert(true);
    }
}
