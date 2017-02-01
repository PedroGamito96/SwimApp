package pt.ipbeja.pdm2.swimapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro Gamito on 31/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //--------------------------------- TABLES ---------------------------------//
    private static final int I_PROOFS = 0;
    private static final String[] TableNames = {
            "proofs"
    };

    //--------------------------------- ANIMALS TABLE ---------------------------------//
    private static final String T_PROOFS_ID = "_id_proof";
    private static final String T_PROOFS_NAME = "name_proof";
    private static final String T_PROOFS_DESCRIPTION = "description_proof";
    private static final String T_PROOFS_PHOTO = "photo_proof";
    private static final String T_PROOFS_GPS = "gps_proof";

    private static final String T_CREATE_PROOFS =
            "CREATE TABLE " + TableNames[I_PROOFS] + "(" +
                    T_PROOFS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    T_PROOFS_NAME + " TEXT , " +
                    T_PROOFS_DESCRIPTION + " TEXT, " +
                    T_PROOFS_PHOTO + " TEXT , " +
                    T_PROOFS_GPS + " TEXT)";

    //--------------------------------- CREATE TABLES ---------------------------------//
    private static final String[] T_CREATE = {
            T_CREATE_PROOFS};

    //-------------------------------------- END --------------------------------------//


    private static final String DB_FILENAME = "Proofs.db";
    private static final int DB_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory factory = null;

    public DBHelper(Context context) {
        super(context, DB_FILENAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String createTable : T_CREATE) {
            db.execSQL(createTable);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String TableName : TableNames) {
            db.execSQL("DROP TABLE IF EXISTS " + TableName);
        }
        onCreate(db);
    }

    //--------------------------------- Animals ---------------------------------//

    public void insertProofs(String name, String description, String photo, String gps) {

        //for(int i = 0; i)
        //SwimData.Titles


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T_PROOFS_NAME, name);
        contentValues.put(T_PROOFS_DESCRIPTION, description);
        contentValues.put(T_PROOFS_PHOTO, photo);
        contentValues.put(T_PROOFS_GPS, gps);

        db.insert(TableNames[I_PROOFS], null, contentValues);
    }

    public List<String> getAllNames() {
        List<String> res = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + T_PROOFS_NAME + " FROM " + TableNames[I_PROOFS];

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(T_PROOFS_NAME));
                res.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return res;
    }

    public List<String> getAllDescriptions() {
        List<String> res = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + T_PROOFS_DESCRIPTION + " FROM " + TableNames[I_PROOFS];

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(T_PROOFS_DESCRIPTION));
                res.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return res;
    }

    public List<String> getAllPhotos() {
        List<String> res = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + T_PROOFS_PHOTO + " FROM " + TableNames[I_PROOFS];

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(T_PROOFS_PHOTO));
                res.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return res;
    }

    public List<String> getAllGps() {
        List<String> res = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + T_PROOFS_GPS + " FROM " + TableNames[I_PROOFS];

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(T_PROOFS_GPS));
                res.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return res;
    }
}
