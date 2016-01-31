package com.example.amosmadalinneculau.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Amos Madalin Neculau on 31/01/2016.
 */

//CREATION OF THE PERSON CLASS
public class Person {

    /*
    A PERSON HAS ONE EMAIL, ONE NAME AND ONE PASSWORD.

    N.B. PASSWORD TO BE CHANGED FROM STRING IN TERMS OF SECURITY AND REDUCE VULNERABILITIES

    MORE FIELDS TO BE ADDED SUCH AS:
    1) UID = USER ID = WE SHOULD AGREE HOW TO COMPOSE IT
     */
    private String email;
    private String name;
    private String password;
    private Date dateOfBirth;
    private Gender gender;
    //Interests
    private ArrayList<Interest> interests = new ArrayList<>();
    public ArrayList<Interest> getInterests(){
        return interests;
    }
    public Interest getInterestByID(int id){
        return interests.get(id);
    }
    public void addInterest(Interest interest){
        interests.add(interest);
    }


    //PERSON CONSTRUCTOR
    public Person(String email, String name, String password, int year, int month, int day, Gender gender){

        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;

        dateOfBirth = new Date(year, month, day);
    }

    //PERSON CONSTRUCTOR WITHOUT DATE
    public Person(String email, String name, String password, Gender gender){

        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;

    }

    //SETTERS(Change of the above variables)
    //NOTE: THESE CAN ALSO BE TOUGHT AS VOIDS (DON'T RETURN THE OLD VARIABLE)
    //I THINK IS BETTER TO RETURN IT BECAUSE WILL BE EASIER FOR DEBUGGING
    //TO BE CHANGED IF IT'S REQUIRED

    /*
    Change the email. Return the old email.
     */
    public String setEmail(String newEmail){

        String oldEmail = "";
        oldEmail = email;
        email = newEmail;

        return oldEmail;
    }

    /*
    Change the name. Return the old name.
     */
    public String setName(String newName){

        String oldName = "";
        oldName = name;
        name = newName;

        return oldName;
    }

    //N.B. PASSWORD TO BE CHANGED FROM STRING IN TERMS OF SECURITY AND REDUCE VULNERABILITIES
    /*
    Change the password. Return the old password.
     */
    public String setPassword(String newPassword){
        String oldPassword = "";
        oldPassword = password;
        password = newPassword;

        return oldPassword;
    }

    /*
    Change/ set the dateOfBirth. Return the old dateOfBirth if exists.
     */
    public Date setDateOfBirth(int year, int month, int day){

        Date oldDate, newDate;

        oldDate = dateOfBirth;

        newDate = new Date(year,month,day);

        dateOfBirth.setDate(newDate.getDate());

        return oldDate;
    }

    /*
    Change the gender. Return the old gender.
     */
    public Gender setGender(Gender newGender){
        Gender oldGender;
        oldGender = gender;
        gender = newGender;

        return oldGender;
    }

    //GETTERS(GET ONE OF THE ABOVE VARIABLES)

    /*
    Return the email.
     */
    public String getEmail(){
        return email;
    }

    /*
   Return the name.
    */
    public String getName(){
        return name;
    }

    /*
   Return the password.
    */
    public String getPassword(){
        return password;
    }

    /*
    Return the date of birth
     */
    public Date getDate(){
        return dateOfBirth;
    }
}
