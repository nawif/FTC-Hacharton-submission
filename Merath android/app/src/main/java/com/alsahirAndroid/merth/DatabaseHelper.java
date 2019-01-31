package com.alsahirAndroid.merth;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.alsahirAndroid.merth.DatabaseHelper.DB.NAME;
import static com.alsahirAndroid.merth.DatabaseHelper.DB.PATH;

/**
 * Created by Fahd on 02/08/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static class DB {
        public static Uri URI;
        public static int VERSION = 1;
        public static final String NAME = "merath.sqlite";
        public static String PATH = "/data/data/" + "android.com.merath" +  "/databases/";

    }
    //region Variable Declarations
    // The Android's default system path of your application database.

    public static SQLiteDatabase myDataBase;

    private final Context myContext;


    //endregion

    //region Private Methods

    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     */
    public DatabaseHelper(Context context) {

        super(context, NAME, null, 1);
        this.myContext = context;

        PATH = myContext.getDatabasePath(NAME).getPath();
        DB.URI = Uri.fromFile(myContext.getDatabasePath(NAME));
        // try {
        // openDataBase();
        // } catch (SQLException e) {
        // // TODO Auto-generated catch block
        // Utils.PrintStackTrace(e);
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // Utils.PrintStackTrace(e);
        // }
    }

    /**
     * *****************************************
     * Creates a empty database on the system
     * and rewrites it with your own database.
     * ******************************************
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (!dbExist) {
            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * *******************************************
     * Check if the database already exist to avoid
     * re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     * *******************************************
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = PATH + NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY
                            | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
            /*int DB_EXIST_VERSION = PreferenceManager
                    .getDefaultSharedPreferences(myContext).getInt(
                            "DB_VERSION", 0);
            if (DB.VERSION != DB_EXIST_VERSION) {
                checkDB = null;
            }*/

        } catch (SQLiteException e) {

            Toast.makeText(myContext, "" + e.toString(), Toast.LENGTH_LONG).show();

        } catch (Exception e1) {
            Toast.makeText(myContext, "" +e1.toString(), Toast.LENGTH_LONG).show();
        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null;
    }

    /**
     * *******************************************
     * Copies your database from your local
     * assets-folder to the just created empty
     * database in the system folder, from
     * where it can be accessed and handled.
     * This is done by transferring bytestream.
     * *******************************************
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(NAME);

        // Path to the just created empty db
        String outFileName = PATH;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        PreferenceManager.getDefaultSharedPreferences(myContext).edit()
                .putInt("DB_VERSION", DB.VERSION).commit();
        Toast.makeText(myContext, "ok", Toast.LENGTH_SHORT).show();
    }

    /**
     * *****************************************
     * Method to open the database
     * ******************************************
     */
    public void openDataBase() throws SQLException, IOException {
        createDataBase();
        // Open the database
        String myPath = PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.NO_LOCALIZED_COLLATORS);

    }

    /**
     * *****************************************
     * Method to close database
     * ******************************************
     */
    @Override
    public synchronized void close() {

        try {
            if (myDataBase != null)
                myDataBase.close();
            myDataBase = null;
            super.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(myContext, "" + e.toString(), Toast.LENGTH_LONG).show();

        }

    }

    /**
     * *****************************************
     * Method onCreate
     * ******************************************
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * *****************************************
     * Method onUpgrade
     * ******************************************
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//endregion

    public Map<String,String> getDatabase(String table, String[] column) throws java.sql.SQLException {


        String myPath = PATH + NAME;

        myDataBase= SQLiteDatabase.openOrCreateDatabase(myPath, null);

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

}
