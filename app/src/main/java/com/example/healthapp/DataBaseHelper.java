package com.example.healthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.healthapp.adapters.BillingAdapter;
import com.example.healthapp.models.BillingModel;
import com.example.healthapp.models.MessageModel;
import com.example.healthapp.models.PatientModel;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    // User table
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERID = "USER" + COLUMN_ID;
    public static final String COLUMN_PASSWORD = "PASSWORD";

    // User doctor
    public static final String DOCTOR_TABLE = "DOCTOR_TABLE";
    public static final String COL_ID = "ID";
    public static final String COL_uID = "userId";
    public static final String COL_NAME = "name";
    public static final String COL_TITLE = "title";
    public static final String COL_OFFICE_DETAILS = "office";
    public static final String COL_SHIFT = "SHIFT";

    // patients
    public static final String PATIENT_TABLE = "patients";
    public static final String COL_USERID = "ID";
    public static final String COL_DISEASES = "diseases";
    public static final String COL_ALLERGIES = "allergies";
    public static final String COL_MEDICATION = "medications";


    // messages
    public static final String MESSAGES_TABLE = "messages";
    public static final String COL_mID = "id";
    public static final String COL_SENDER = "sender";
    public static final String COL_MESSAGE = "message";

    // billing
    public static final String BILLING_TABLE = "billing";
    public static final String COL_useID = "id";
    public static final String COL_dID = "doctorID";
    public static final String COL_AMOUNT = "amount";
    public static final String COL_STATUS = "status";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "userData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // CREATE TABLE WITH USER
        String userTable = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_TITLE + " TEXT, " + COLUMN_USERID + " TEXT, " + COLUMN_PASSWORD + " TEXT )";
        db.execSQL(userTable);

        String billingTable = "CREATE TABLE " + BILLING_TABLE + "(" + COL_useID + " TEXT, " + COL_dID + " TEXT, " + COL_AMOUNT + " TEXT, " + COL_STATUS + " TEXT )";
        db.execSQL(billingTable);

        // CREATE TABLE WITH DOCTOR
        String doctorTable = "CREATE TABLE " + DOCTOR_TABLE + "(" + COL_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_uID + " TEXT, " + COL_NAME + " TEXT, " + COL_TITLE + " TEXT, " + COL_OFFICE_DETAILS + " TEXT, " + COL_SHIFT + " TEXT )";
        db.execSQL(doctorTable);

        String patientTable = "CREATE TABLE " + PATIENT_TABLE + "(" + COL_USERID +
                " TEXT, " + COL_DISEASES + " TEXT, " + COL_ALLERGIES + " TEXT, " + COL_MEDICATION + " TEXT )";
        db.execSQL(patientTable);

        String messageTable = "CREATE TABLE " + MESSAGES_TABLE + "(" + COL_mID +
                " TEXT, " + COL_SENDER + " TEXT, " + COL_MESSAGE + " TEXT )";
        db.execSQL(messageTable);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists " + USER_TABLE);
        db.execSQL("DROP TABLE if exists " + DOCTOR_TABLE);
        db.execSQL("DROP TABLE if exists " + PATIENT_TABLE);
        db.execSQL("DROP TABLE if exists " + BILLING_TABLE);
        db.execSQL("DROP TABLE if exists " + MESSAGES_TABLE);
        onCreate(db);


    }

    // Add user
    public boolean addUser(DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, dataModel.getName());
        cv.put(COLUMN_TITLE, dataModel.getTitle());
        cv.put(COLUMN_USERID, dataModel.getUserId());
        cv.put(COLUMN_PASSWORD, dataModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean addBilling(BillingModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_useID, dataModel.getUserID());
        cv.put(COL_dID, dataModel.getDoctorName());
        cv.put(COL_AMOUNT, dataModel.getAmount());
        cv.put(COL_STATUS, dataModel.getStatus());

        long insert = db.insert(BILLING_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addMessage(MessageModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_mID, dataModel.getUserID());
        cv.put(COL_SENDER, dataModel.getSender());
        cv.put(COL_MESSAGE, dataModel.getMessage());

        long insert = db.insert(MESSAGES_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addPatientDetails(PatientModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USERID, model.getUserID());
        cv.put(COL_DISEASES, model.getDiseases());
        cv.put(COL_ALLERGIES, model.getAllergies());
        cv.put(COL_MEDICATION, model.getMedication());

        long insert = db.insert(PATIENT_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    //add Doctor
    public boolean addDoctor(DoctorModel doctorModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_uID, doctorModel.getUserID());
        values.put(COL_TITLE, doctorModel.getTitle());
        values.put(COL_OFFICE_DETAILS, doctorModel.getOfficeDetails());
        values.put(COL_SHIFT, doctorModel.getShift());

        long r = sqLiteDatabase.insert(DOCTOR_TABLE, null, values);
        if (r > 0)
            return true;
        else
            return false;

    }

    // View doctor information
    public Cursor viewDoctor() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DOCTOR_TABLE;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public ArrayList<DoctorModel> allDoctors() {
        ArrayList<DoctorModel> doctorModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DOCTOR_TABLE;
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        while (c.moveToNext()) {
            String userid = c.getString(1);
            String name = c.getString(2);
            String title = c.getString(3);
            String office = c.getString(4);
            String shift = c.getString(5);

            DoctorModel model = new DoctorModel(userid, name, title, office, shift);
            doctorModels.add(model);
        }
        return doctorModels;
    }

    // update Doctor
    public boolean updateDoc(DoctorModel model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //  values.put(COLUMN_DOB1,date);
        values.put(COL_OFFICE_DETAILS, model.getOfficeDetails());
        values.put(COL_SHIFT, model.getShift());
        int d = sqLiteDatabase.update(DOCTOR_TABLE, values, "userId=?",
                new String[]{model.getUserID()});
        if (d > 0)
            return true;
        else
            return false;
    }

    public boolean updateUser(String id, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, pass);
        int update = db.update(USER_TABLE, values, "userId=?",
                new String[]{id});
        if (update > 0)
            return true;
        else
            return false;
    }

    // view Doctor with user ID and pass word
    public Cursor viewDocProfile() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT DID, NAME, TITLE, SHIFT, USER, PASSWORD from "
                + "USER_TABLE inner join DOCTOR_TABLE on" +
                " USER_TABLE.TITLe = DOCTOR_TABLE.TITLE";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }


    public boolean deleteOne(DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USER_TABLE + " WHERE " + COLUMN_ID + " = " + dataModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }

    public DataModel getUserDetails(String userID) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USERID + " = '" + userID + "'";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        String name = c.getString(1);
        String title = c.getString(2);
        String userid = c.getString(3);
        String password = c.getString(4);
        DataModel model = new DataModel(name, title, userid, password);

        return model;
    }

    public ArrayList<MessageModel> retrieveAllMessages() {
        ArrayList<MessageModel> messageModelArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + MESSAGES_TABLE;
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        while (c.moveToNext()) {
            String userid = c.getString(0);
            String sender = c.getString(1);
            String message = c.getString(2);

            MessageModel messageModel = new MessageModel(userid, sender, message);
            messageModelArrayList.add(messageModel);
        }
        return messageModelArrayList;
    }

    public ArrayList<BillingModel> retrieveAllBillings() {
        ArrayList<BillingModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + BILLING_TABLE;
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        while (c.moveToNext()) {
            String userid = c.getString(0);
            String doctorId = c.getString(1);
            String amount = c.getString(2);
            String status = c.getString(3);

            BillingModel messageModel = new BillingModel(userid, doctorId, amount, status);
            list.add(messageModel);
        }
        return list;
    }

    public ArrayList<DataModel> retrieveAllUsers() {
        ArrayList<DataModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor and put them into the return list
            do {
                int ID = cursor.getInt(0);
                String Name = cursor.getString(1);
                String Title = cursor.getString(2);
                String UserID = cursor.getString(3);
                String Password = cursor.getString(4);

                DataModel newUser = new DataModel(ID, Name, Title, UserID, Password);
                returnList.add(newUser);
            }
            while (cursor.moveToNext());
        } else {
            // error -> don't add anything to the list
        }
        // close the cursor and db after using them
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<DoctorModel> retrieveAllDoctors() {
        ArrayList<DoctorModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + DOCTOR_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor and put them into the return list
            do {
                int ID = cursor.getInt(0);
                String Uid = cursor.getString(1);
                String Title = cursor.getString(2);
                String Office = cursor.getString(3);
                String Shift = cursor.getString(4);

                DoctorModel newUser = new DoctorModel(Uid, Title, Office, Shift);
                returnList.add(newUser);
            }
            while (cursor.moveToNext());
        } else {
            // error -> don't add anything to the list
        }
        // close the cursor and db after using them
        cursor.close();
        db.close();
        return returnList;
    }
}
