package com.yg;

class A{
    private int a;
    public void fun(Object obj){
        A t = (A)obj;
        if(a == t.a){
            System.out.println("相等");
        }
    }
}

public class T1 {

}
