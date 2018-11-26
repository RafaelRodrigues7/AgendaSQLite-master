package br.edu.ifspsaocarlos.agenda.data;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "agenda.db";
    static final String DATABASE_TABLE = "contatos";
    static final String KEY_ID = "id";
    static final String KEY_NAME = "nome";
    static final String KEY_FONE = "fone";
    static final String KEY_EMAIL = "email";
    static final String KEY_FAV = "favorites";
    static final String KEY_FONED = "foneD";
    static final String KEY_DATANIVERSARIO = "dataniver";
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_CREATE = "CREATE TABLE " + DATABASE_TABLE + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_FONE + " TEXT, " +
            KEY_FONED + " TEXT," +
            KEY_EMAIL + " TEXT, " +
            KEY_FAV + " INTEGER, " +
            KEY_DATANIVERSARIO + " TEXT); ";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }

}



