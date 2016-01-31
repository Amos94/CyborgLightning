package com.example.amosmadalinneculau.objects;

/**
 * Created by Name on 31/01/2016.
 */
public class Interest {
    private InterestTypes interestType;
    private String interest_name;
    private String interest_description;

    public Interest(InterestTypes interestType,String interest_name,String interest_description){
        this.interestType=interestType;
        this.interest_name = interest_name;
        this.interest_description = interest_description;
    }


    //Setters
    //Type
    public void setInterestType(InterestTypes interestType){
        this.interestType=interestType;
    }
    //Name
    public void setInterest_name(String interest_name){
        this.interest_name = interest_name;
    }
    //Description
    public void setInterest_description(String interest_description){
        this.interest_description= interest_description;
    }

    //Getters
    public InterestTypes getInterestType(){return interestType;}//InterestTypes
    public String getName(){return interest_name;}//Interest_name
    public String getInterest_description(){return interest_description;}//Interest_description
}
