package cn.edu.gdmec.s07150716.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hyh on 2016/10/25.
 */
public class MyDB extends SQLiteOpenHelper {
    private static String DB_NAME = "My_DB.db";
    private static int DB_VERSION = 2;
    private SQLiteDatabase db;

    public MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openConnection() {
        if (!db.isOpen()){
            db=getWritableDatabase();
        }
        return db;
    }

    public void closeConnection(){
        try {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean createTable(String contactsTable){
        try {
            openConnection();
            db.execSQL(contactsTable);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    public boolean save(String contactsTable, ContentValues values){
        try {
            openConnection();
            db.insert(contactsTable,null,values);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public boolean update(String contactsTable,ContentValues values,String whereClause,String[] whereArgs){
        try {
            openConnection();
            db.update(contactsTable,values,whereClause,whereArgs);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public boolean delete(String contactsTable,String deleteSql,String obj[]){
        try{
           openConnection();
            db.delete(contactsTable,deleteSql,obj);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }

        return true;
    }

    public Cursor find(String findSql,String obj[]){
        try {
            openConnection();
            Cursor cursor=db.rawQuery(findSql,obj);
            return cursor;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isTableExits(String contactsTable){
        try{
            openConnection();
            String str="select count(*)xcount from "+contactsTable;
            db.rawQuery(str,null).close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
}
