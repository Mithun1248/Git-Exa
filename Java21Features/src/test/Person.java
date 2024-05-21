package test;

//Record is final class with getters and constructor
//Record can't be inherited
//fields are private final
public record Person(String name, Integer age){
}

record Human(String name){}
