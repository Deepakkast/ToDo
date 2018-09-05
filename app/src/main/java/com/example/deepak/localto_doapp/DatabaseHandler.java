package com.example.deepak.localto_doapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABUASE_NAME = 1;
    private static final String DATABASE_NAME = "usersDatabase";
    private static final int DATABASE_VERSION = 1;
    public static final String Table_users = "ToDo_users";
    public static final String table_entity1 = "Subject";
    public static final String table_entity2 = "Date";
    public static  final String table_entity3="Completed";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table = "CREATE TABLE " + Table_users + "("

                + table_entity1 + " TEXT," + table_entity2 + " TEXT," + table_entity3 + " TEXT" + ")";
        sqLiteDatabase.execSQL(create_table);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_users);
        onCreate(sqLiteDatabase);

    }
    public void delete(String subject)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + Table_users + " WHERE " +table_entity1 + " = '"+ subject + "'" );

    }

    void addtodo(ToDoRetro toDoList) {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(table_entity1, toDoList.getSubject());
        val.put(table_entity2, toDoList.getDate());
        val.put(table_entity3,"no");
        sql.insert(Table_users, null, val);
        sql.close();

    }

     void add_check(String subject,String date ){
        SQLiteDatabase sql=this.getWritableDatabase();
        ContentValues val=new ContentValues();
         val.put(table_entity1,subject);
         val.put(table_entity2, date);
         val.put(table_entity3,"yes");
         sql.insert(Table_users, null, val);
         sql.close();

     }


    public List<ToDoRetro> display() {
        List<ToDoRetro> list = new ArrayList<ToDoRetro>();
        String selectQuery = "SELECT * FROM " + Table_users + " WHERE " + table_entity3 + " = 'no'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {


                //ToDoRetro toDoList = new ToDoRetro();
                //toDoList.setSubject(cursor.getString(0));
                //toDoList.setDate(cursor.getString(1));
                //list.add(toDoList);
            }while (cursor.moveToNext()) ;
        }


        cursor.close();
        return list;
    }


    public List<ToDoRetro> displayChecked() {
        List<ToDoRetro> list = new ArrayList<ToDoRetro>();
        String selectQuery = "SELECT * FROM " + Table_users + " WHERE " + table_entity3 + " = 'yes'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {


               /* ToDoRetro toDoList = new ToDoRetro();
                toDoList.setSubject(cursor.getString(0));
                toDoList.setDate(cursor.getString(1));
                toDoList.set
                list.add(toDoList);*/
            }while (cursor.moveToNext()) ;
        }


        cursor.close();
        return list;
    }
    }

