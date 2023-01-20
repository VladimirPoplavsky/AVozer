package com.firstapp.avozer;

public class Person {
    public String teudat_zeut;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String city;

//
//    public String id;
//    public String name;
//    public String email;
//    public String phone;



//    public Person(String id, String name, String phone, String email) {
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//    }

    public Person(){}

    public Person(String teudat_zeut, String firstName, String lastName, String email, String phone, String city) {
        this.teudat_zeut = teudat_zeut;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }
}
