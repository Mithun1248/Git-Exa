package test;

import static java.lang.StringTemplate.STR;

public class Main1 {

    public static void main(String[] args){
        String name = "Mithun";
        String message = STR."Hi \{name}!";
        System.out.println(message);
    }
}
