package com.example.amosmadalinneculau.objects;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.amosmadalinneculau.objects.Person;import java.lang.Integer;import java.lang.Override;import java.lang.String;import java.util.ArrayList;

/**
 * Created by nashwan on 01/02/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = DatabaseHelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserDataManager";

    // Table Names

    private static final String TABLE_USERPROFILE = "userProfile";
    private static final String TABLE_USERSTATUS = "userStatus";

    // Common column names
    private static final String KEY_EMAIL_ADDRESS = "email_address";


    // UserProfile Table -column names
    private static final String PASSWORD = "password";
    private static final String D_O_B = "dob";
    private static final String FIRST_NAME = "first_name";

    //  UserStatus Table - column names
    private static final String STATUS = "Status";
    private static final String LOCATION = "Location";


    // Table Create Statements

    // UserProfile table create statement
    private static final String CREATE_TABLE_USERSTATUS = "CREATE TABLE " + TABLE_USERSTATUS
            + "(" + KEY_EMAIL_ADDRESS + " TEXT PRIMARY KEY, " + STATUS + " TEXT, " + LOCATION + " TEXT" + ")";

    // UserProfile table create statement
    private static final String CREATE_TABLE_USERPROFILE = "CREATE TABLE " + TABLE_USERPROFILE
            + "(" + KEY_EMAIL_ADDRESS + " TEXT PRIMARY KEY, " + PASSWORD + " TEXT, " + FIRST_NAME + " TEXT, "+ D_O_B + " TEXT" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USERPROFILE);
        db.execSQL(CREATE_TABLE_USERSTATUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERPROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERSTATUS);

        // create new tables
        onCreate(db);
    }

    // ---------------------- General Methods ------------------------//


    // -------------------------"UserProfile methods" ----------------//

    /**
     * Query that checks to see if login attempt is successful
     *
     * @param emailAddress the email inserted
     * @param password     the password inserted
     * @return the Person if login successful, otherwise returns null
     */
    public Person checkLogin(String emailAddress, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM" + TABLE_USERPROFILE + " WHERE" + KEY_EMAIL_ADDRESS + "='" + emailAddress + "' AND "
                + PASSWORD + "='" + password+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c == null)
            return null;


        Person person = new Person();
        person.setName(c.getString(c.getColumnIndex(FIRST_NAME)));
        person.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL_ADDRESS)));
        person.setPassword(c.getString(c.getColumnIndex(PASSWORD)));

        //HAS TO BE IN FORM OF DD/MM/YYYY
        //DATE OF BIRTH
        String dob = c.getString(c.getColumnIndex(D_O_B));
        int dd = Integer.parseInt(dob.subSequence(0, 2) + "");
        int mm = Integer.parseInt(dob.subSequence(3, 5) + "");
        int yyyy = Integer.parseInt(dob.subSequence(6, 10) + "");

        person.setDateOfBirth(yyyy, mm, dd);

        return person;
    }

    /**
     * Query to check if the email has been used in database
     *
     * @param EmailAddress the email Address to be checked
     * @return true if email has been used
     */
    public boolean checkEmailUsed(String EmailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + KEY_EMAIL_ADDRESS + " FROM " + TABLE_USERPROFILE + " WHERE" + KEY_EMAIL_ADDRESS + "='" + EmailAddress+"'";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c == null) {
            return false;
        }
        return true;
    }

    /**
     * Create a Row in UserProfile
     * @param person person to be added
     */
    public void createPerson(Person person) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL_ADDRESS, person.getEmail());
        values.put(PASSWORD, person.getPassword());
        values.put(FIRST_NAME, person.getName());
        values.put(D_O_B, person.getDate().toString());

        // insert row
        db.insert(TABLE_USERPROFILE, null, values);

    }

    // ------------------------ "UserStatus" table methods ----------------//

    /**
     * finds the emails of all online users
     * @return all online users
     */
    public ArrayList<String> usersOnline() {
        ArrayList<String> personArrayList = new ArrayList<>();

        String selectQuery = "SELECT " + KEY_EMAIL_ADDRESS + " FROM " + TABLE_USERSTATUS + " WHERE " + STATUS + "= 'Online'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                //added to array
                personArrayList.add(c.getString(c.getColumnIndex(KEY_EMAIL_ADDRESS)));
            } while (c.moveToNext());
        }

        return personArrayList;
    }

}

