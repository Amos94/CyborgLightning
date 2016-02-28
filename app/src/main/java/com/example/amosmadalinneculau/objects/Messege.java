package com.example.amosmadalinneculau.objects;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Amos Madalin Neculau on 01/02/2016.
 */

/*
    SIMPLE CLASS THAT CREATES A MESSEGE OBJECT
 */
public class Messege {
/*
    A MESSEGE WILL HAVE: THE SENDER, THE RECEIVER, THE ACTUAL MESSEGE AND THE DATE
 */
    public UserProfile personFrom;
    public UserProfile personTo;
    public String messege;
    public java.sql.Date dateTime;
    /*
        Constructor for the Messege class
     */
    public Messege(UserProfile personFrom, UserProfile personTo, String messege, java.sql.Date dateTime){
        this.personFrom = personFrom;
        this.personTo = personTo;
        this.messege = messege;
        this.dateTime = dateTime;
    }

    /*
        A simple toString method to get the Messege
     */
    public String toString(){
        return personFrom.getName() + " "
                +personTo.getName() + " "
                +messege+ " "
                +dateTime.getTimezoneOffset();
    }

    /*
        A simple GET method that gets the sender
     */
    public UserProfile getPersonFrom(){
        return personFrom;
    }

    /*
        A simple GET method that gets the receiver
     */
    public UserProfile getPersonTo(){
        return personTo;
    }

    /*
        A simple GET method that gets the messege
     */
    public String getMessege(){
        return messege;
    }

    /*
        A simple GET method that gets the date and time
     */
    public java.sql.Date getDateTime(){
        return dateTime;
    }

    /*
        A simple SET method that sets a new messege
     */
    public String setMessage(String newMessege){
        messege = newMessege;

        return messege;
    }

    /*
        A simple SET method that sets a new sender
     */
    public UserProfile setPersonFrom(UserProfile newPersonFrom){
        personFrom = newPersonFrom;

        return personFrom;
    }

    /*
        A simple SET method that sets a new receiver
     */
    public UserProfile setPersonTo(UserProfile newPersonTo){
        personTo = newPersonTo;

        return personTo;
    }
}
