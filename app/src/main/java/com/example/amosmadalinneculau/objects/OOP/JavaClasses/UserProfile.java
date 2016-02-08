package com.example.amosmadalinneculau.objects.OOP.JavaClasses;

import com.example.amosmadalinneculau.objects.OOP.Enums.Gender;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Name on 01/02/2016.
 */
public class UserProfile {

        /*
        A PERSON HAS ONE EMAIL, ONE NAME AND ONE PASSWORD.

        N.B. PASSWORD TO BE CHANGED FROM STRING IN TERMS OF SECURITY AND REDUCE VULNERABILITIES

        MORE FIELDS TO BE ADDED SUCH AS:
        1) UID = USER ID = WE SHOULD AGREE HOW TO COMPOSE IT
         */
        private String email;
        private String name;
        private String surname;
        private String password;
        private Date dateOfBirth;
        private String gender;
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
        public UserProfile(String email, String name,String surname, String password, int year, int month, int day, String gender){

            this.email = email;
            this.name = name;
            this.surname = surname;
            this.password = password;
            this.gender = gender;

            dateOfBirth = new Date(year, month, day);
        }

        //PERSON CONSTRUCTOR WITHOUT DATE
        public UserProfile(String email, String name,String surname, String password, String gender){

            this.email = email;
            this.name = name;
            this.surname = surname;
            this.password = password;
            this.gender = gender;

        }

        public UserProfile(){}

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

        /*
         Change the name. Return the old name.
         */
        public String setSurname(String newSurname){

        String oldSurname = "";
        oldSurname = surname;
        surname = newSurname;

        return oldSurname;
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
        public String setGender(String newGender){
            String oldGender;
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
        Return the name.
        */
        public String getSurname(){
        return surname;
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
        /*
        Return the date of birth
         */
        public String getGender(){
           String temp;
           if(this.gender.equalsIgnoreCase("male")) temp = "male";
           else {temp="female";}
           return temp;
    }


    }


