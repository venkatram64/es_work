package com.venkat.es.esdemo.service;

public class LambdaExample {

    interface StringLength {
        public int length(String s);
    }

    public int length(StringLength s, String msg){
        return s.length(msg);
    }

    public static void main(String[] args) {
        //lambda way of doing
        StringLength msg = (String s) -> s.length();
        System.out.println(msg.length("Hello World!!!"));

        //calling method with lambda
        LambdaExample lx = new LambdaExample();
        StringLength msg2 = (String s) -> s.length();
        System.out.println(lx.length(msg2,"Venkatram Reddy"));

        //calling method with lambda with different way
        //type inference
        LambdaExample lx2 = new LambdaExample();
        StringLength msg3 = (s) -> s.length();
        System.out.println(lx2.length(msg2,"Venkatram Reddy"));
    }
}
