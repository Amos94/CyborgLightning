package com.example.amosmadalinneculau.objects.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amosmadalinneculau.objects.OOP.JavaClasses.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Name on 31/01/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appDatabase";//db name
    private static final String TABLE_USERS = "users";

    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "firstname";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_PASSWORD = "password";
    //TODO please help me to make this with multiple attributes - 3 (day/month/year)
    // private static final String KEY_DOB = "dob";
    private static final String KEY_GENDER = "sex";

    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //CREATING TABLES
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
        + KEY_EMAIL + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"+
        KEY_SURNAME + " TEXT,"+ KEY_PASSWORD + " TEXT," + KEY_GENDER + " TEXT"
        + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    //UPDATING DB
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Adding new UserProfile
    public void addUserProfile(UserProfile userProfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL,userProfile.getEmail());// put email
        values.put(KEY_NAME,userProfile.getName());//put name
        values.put(KEY_SURNAME,userProfile.getSurname());//put surname
        values.put(KEY_PASSWORD,userProfile.getPassword());//put pass
        //TODO PLEASE PUT THE DATE (COMPOSITE ATTRIBUTE DAY/MONTH/YEAR)
        values.put(KEY_GENDER,userProfile.getGender());//put gender

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // Getting single UserProfile
    public UserProfile getUserProfile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{
                        KEY_EMAIL, KEY_NAME, KEY_SURNAME, KEY_PASSWORD, KEY_GENDER}, KEY_EMAIL + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor !=null)cursor.moveToFirst();
        UserProfile userProfile = new UserProfile(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        return userProfile;

    }

    // Getting All UserProfile
    public List<UserProfile> getAllUserProfile() {
        List<UserProfile> userProfilesList = new ArrayList<UserProfile>();
        String selectQuery = "SELECT  * FROM " +TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                UserProfile userProfile = new UserProfile();
                userProfile.setEmail(cursor.getString(0));
                userProfile.setName(cursor.getString(1));
                userProfile.setSurname(cursor.getString(2));
                userProfile.setPassword(cursor.getString(3));
                userProfile.setGender(cursor.getString(4));
            }while(cursor.moveToNext());
        }return userProfilesList;
    }



    // Updating single userProfile
    public int updateUserProfile(UserProfile userProfile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL,userProfile.getEmail());// put email
        values.put(KEY_NAME,userProfile.getName());//put name
        values.put(KEY_SURNAME,userProfile.getSurname());//put surname
        values.put(KEY_PASSWORD,userProfile.getPassword());//put pass
        //TODO PLEASE PUT THE DATE (COMPOSITE ATTRIBUTE DAY/MONTH/YEAR)
        values.put(KEY_GENDER,userProfile.getGender());//put gender
        // updating row
        return db.update(TABLE_USERS, values, KEY_EMAIL + " = ?",
        new String[] { String.valueOf(userProfile.getEmail()) });
    }

    // Deleting single UserProfile
    public void deleteUserProfile(UserProfile userProfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_EMAIL + " = ?",
                new String[] { String.valueOf(userProfile.getEmail()) });
        db.close();
    }


    // Getting UserProfile Count
    public int getUserProfileCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}