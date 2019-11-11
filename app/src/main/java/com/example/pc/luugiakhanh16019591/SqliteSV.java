package com.example.pc.luugiakhanh16019591;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class SqliteSV extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sinhvienmanager";
    private static final String TABLE_NAME = "sinhvien";
    private static final String MSSV = "mssv";
    private static final String TEN_SV = "tensv";
    private static final String LOP = "lop";
    private static final int VERSION = 1;

    private Context context;

    private static final String CREATE_TABLE = "CREATE TABLE sinhvien(" +
            MSSV + " integer primary key," +
            TEN_SV +" nvarchar(100),"+
            LOP + " varchar(20))";

    public SqliteSV(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long createSV(SinhVien sinhVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MSSV, sinhVien.getMssv());
        values.put(TEN_SV, sinhVien.getTenSV());
        values.put(LOP, sinhVien.getLop());
        long n = db.insert(TABLE_NAME, null, values);
        db.close();
        return n;
    }

    public ArrayList<SinhVien> getAllSV() {
        ArrayList<SinhVien> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setMssv(cursor.getInt(0));
                sinhVien.setTenSV(cursor.getString(1));
                sinhVien.setLop(cursor.getString(2));
                list.add(sinhVien);
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public SinhVien getSVById(String id) {
        SinhVien sinhVien = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + MSSV + " = '" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0) {
            return null;
        } else {
            cursor.moveToFirst();
            sinhVien = new SinhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return sinhVien;
    }

    public long updateSinhVien(String id, String name, String lop) {
        long n = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_SV, name);
        values.put(LOP, lop);
        n = db.update(TABLE_NAME, values, MSSV + "=?", new String[]{String.valueOf(id)});
        db.close();
        return n;
    }

    public long deleteSinhVien(String id) {
        long n = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        n = db.delete(TABLE_NAME, MSSV + "=?", new String[]{String.valueOf(id)});
        db.close();
        return n;
    }
}
