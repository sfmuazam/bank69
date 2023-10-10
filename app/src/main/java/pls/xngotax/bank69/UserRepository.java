package pls.xngotax.bank69;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserRepository {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    public UserRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, user.getUsername());
        values.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.COLUMN_NAMA_LENGKAP, user.getNamaLengkap());
        values.put(DatabaseHelper.COLUMN_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COLUMN_SALDO, user.getSaldo());

        return database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }
    public User getUserById(int id) {
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_USERS, null,
                DatabaseHelper.COLUMN_ID_USER + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        int userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER));
        String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
        String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD));
        String namaLengkap = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA_LENGKAP));
        String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));
        int saldo = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SALDO));

        return new User(userId, username, password, namaLengkap, email, saldo);
    }

    public int updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, user.getUsername());
        values.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.COLUMN_NAMA_LENGKAP, user.getNamaLengkap());
        values.put(DatabaseHelper.COLUMN_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COLUMN_SALDO, user.getSaldo());

        return database.update(DatabaseHelper.TABLE_USERS, values,
                DatabaseHelper.COLUMN_ID_USER + " = ?",
                new String[]{String.valueOf(user.getIdUser())});
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_USERS, null,
                DatabaseHelper.COLUMN_USERNAME + " = ? AND " +
                        DatabaseHelper.COLUMN_PASSWORD + " = ?",
                new String[]{username, password}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            int userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_USER));
            String namaLengkap = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA_LENGKAP));
            String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));
            int saldo = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SALDO));

            return new User(userId, username, password, namaLengkap, email, saldo);
        }

        return null;
    }


}

