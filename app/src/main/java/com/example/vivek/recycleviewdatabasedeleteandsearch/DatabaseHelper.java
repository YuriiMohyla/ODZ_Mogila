package com.example.vivek.recycleviewdatabasedeleteandsearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_FIRST_NAME = "user_firstname";
    private static final String COLUMN_USER_PHONE = "user_lastname";



    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_FIRST_NAME + " TEXT," +
            COLUMN_USER_PHONE + " TEXT " + ")";


    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(ListDataModel userDataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FIRST_NAME, userDataModel.getFirstName());
        values.put(COLUMN_USER_PHONE, userDataModel.getPhoneNumber());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<ListDataModel> getAllUser() {
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_FIRST_NAME,
                COLUMN_USER_PHONE
        };
        String sortOrder =
                COLUMN_USER_FIRST_NAME + " ASC";
        List<ListDataModel> userDataModelList = new ArrayList<ListDataModel>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_USER,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);


        if (cursor.moveToFirst()) {
            do {
                ListDataModel listDataModel = new ListDataModel();
                listDataModel.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                listDataModel.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME)));
                listDataModel.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));

                userDataModelList.add(listDataModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userDataModelList;
    }
    boolean deleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_USER, COLUMN_USER_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }
    Cursor getAllUsers() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USER, null);
    }
    public boolean deleteFact(ListDataModel user) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_USER + " WHERE " +COLUMN_USER_ID + " ='" + user.getId() + "'");
        database.close();
        return true;
    }


}
