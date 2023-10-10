package pls.xngotax.bank69;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "my_app.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_USERS = "users";
    static final String COLUMN_ID_USER = "id_user";
    static final String COLUMN_USERNAME = "username";
    static final String COLUMN_PASSWORD = "password";
    static final String COLUMN_NAMA_LENGKAP = "nama_lengkap";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_SALDO = "saldo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID_USER + " TEXT PRIMARY KEY," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_NAMA_LENGKAP + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_SALDO + " INTEGER" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);

        addDummyUser(db, "1", "dummyuser", "password", "Dummy User", "dummy@example.com", 1000);
    }

    private void addDummyUser(SQLiteDatabase db, String idUser, String username, String password, String namaLengkap, String email, int saldo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_USER, idUser);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_NAMA_LENGKAP, namaLengkap);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_SALDO, saldo);
        db.insert(TABLE_USERS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
