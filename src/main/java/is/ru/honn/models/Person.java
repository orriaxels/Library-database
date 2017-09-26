package is.ru.honn.models;

/**
 * @author Haraldur Ingi Shoshan og Orri Axelsson
 * @version Person.java 1.0 26 September 2017
 * Copyright (c) Haraldur Ingi Shoshan & Orri Axelsson
 *
 * The Person Class is a simple class that holds the information about one
 * person object in the database
 */

public class Person
{
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;

    public Person(String firstName, String lastName, String address, String email, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
