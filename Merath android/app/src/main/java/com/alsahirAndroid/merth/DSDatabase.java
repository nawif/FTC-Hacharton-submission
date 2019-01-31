package com.alsahirAndroid.merth;

/**
 * Created by Fahd on 21/01/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class DSDatabase extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/" + "com.alsahirAndroid.merth" +  "/databases/";

    private static String DB_NAME = "database.sqlite";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DSDatabase(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist

        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database" + e.getMessage().toString());

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    public void DeleteDataBase() throws IOException{

        File file = new File(DB_PATH + DB_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    public void openDataBase() throws SQLException{

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public Map<String,String> getDatabase(String table, String[] column) throws SQLException {

        openDataBase();
        String query = "SELECT * FROM " + table;
        Cursor c = myDataBase.rawQuery(query, null);

        Map<String,String> myMap = new HashMap<>();
        int index = 0;

        if (c != null && c.moveToFirst()) {

            while (c.isAfterLast() == false) {

                for (int i=0; i < column.length; i++)
                {
                    myMap.put(column[i] + "_" + index,c.getString(c.getColumnIndex(column[i])));
                }

                index++;
                c.moveToNext();
            }

        }

        c.close();

        return  myMap;
    }

    public Title getRecordByID(String table, String column, String id) throws SQLException {

        String myPath = DB_PATH + DB_NAME;

        myDataBase= SQLiteDatabase.openOrCreateDatabase(myPath, null);


        String query = "SELECT * FROM " + table + " where " + column + " = " + id;

        Cursor c = myDataBase.rawQuery(query, null);
        Title title = new Title();

        if (c != null && c.moveToFirst()) {
            // do your stuff

            while (c.isAfterLast() == false) {

                title.setId(c.getString(c
                        .getColumnIndex("id")));
                title.setTitle(c.getString(c
                        .getColumnIndex("title")));
                title.setRawi(c.getString(c
                        .getColumnIndex("rawi")));
                title.setBody(c.getString(c
                        .getColumnIndex("body")));
                title.setSource(c.getString(c
                        .getColumnIndex("source")));

                c.moveToNext();
            }

        }

        c.close();
        return title;
    }

    //get Count column from table
    public Boolean checkRecordIsExist(String table, String id) throws SQLException {

        String myPath = DB_PATH + DB_NAME;

        myDataBase= SQLiteDatabase.openOrCreateDatabase(myPath, null);

        String query = "SELECT * FROM " + table + " WHERE title_id = " + id;

        Cursor c = myDataBase.rawQuery(query, null);

        if  (  ( c != null ) && ( c.moveToFirst()) )
        {
            c.close();
            return true ;
        }

        c.close();
        return false;
    }

    //get Count column from table
    public int getCountFromDBByID(String table, int id) throws SQLException {

        String myPath = DB_PATH + DB_NAME;

        myDataBase= SQLiteDatabase.openOrCreateDatabase(myPath, null);

        String query = "SELECT * FROM " + table + " WHERE title_id = " + id;

        Cursor c = myDataBase.rawQuery(query, null);

        int result = c.getCount();

        c.close();

        return result;
    }

    //Insert to database
    public Boolean insertToDB(String table, String column, String valuesP) {

        String myPath = DB_PATH + DB_NAME;

        myDataBase= SQLiteDatabase.openOrCreateDatabase(myPath, null);

        String query = null;

        query = "INSERT INTO " + table + "(" + column + ")" +  "VALUES(" + valuesP +");";

        try {
            myDataBase.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(myContext, "" + e.toString(), Toast.LENGTH_LONG).show();
        }

        myDataBase.close();
        return true;
    }

    //Insert to database
    public Boolean updateCoulmnByID(String table, String column_valuesP, String id) {

        String myPath = DB_PATH + DB_NAME;

        myDataBase= SQLiteDatabase.openOrCreateDatabase(myPath, null);

        String query = null;

        query = "UPDATE " + table + " SET " + column_valuesP + " WHERE id= " + id;

        try {
            myDataBase.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(myContext, "" + e.toString(), Toast.LENGTH_LONG).show();
        }

        myDataBase.close();
        return true;
    }

    //delete from database
    //        static func deleteDataFromDB(table:String,id: Int,isMain:Bool) -> Bool

    public Boolean deleteFromDB(String table, String column, String id) {

        String myPath = DB_PATH + DB_NAME;

        myDataBase= SQLiteDatabase.openOrCreateDatabase(myPath, null);

        String query = "DELETE FROM " + table + " WHERE " + column + " = " + id;

        //myDataBase.execSQL(query);

        Cursor c = myDataBase.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            // do your stuff
            while (c.isAfterLast() == false) {
                System.out.println("its delete");
            }
        }

        c.close();

        return true;
    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}