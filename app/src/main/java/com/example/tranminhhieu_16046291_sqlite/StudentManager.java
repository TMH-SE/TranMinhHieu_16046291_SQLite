package com.example.tranminhhieu_16046291_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentManager extends SQLiteOpenHelper {
    public StudentManager(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String scriptLop = "CREATE TABLE LOP(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200))";
        sqLiteDatabase.execSQL(scriptLop);
        String scriptSV = "CREATE TABLE SV(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), class_name VARCHAR(200), subject VARCHAR(200))";
        sqLiteDatabase.execSQL(scriptSV);
    }

    public List<SV> getAllSVs() {
        List<SV> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SV", null);
        if (cursor.moveToFirst()) {
            do {
                SV sv = new SV();
                sv.setId(Integer.parseInt(cursor.getString(0)));
                sv.setName(cursor.getString(1));
                sv.setClass_name(cursor.getString(2));
                sv.setSubject(cursor.getString(3));
                list.add(sv);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<Lop> getAllLop() {
        List<Lop> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Lop", null);
        if (cursor.moveToFirst()) {
            do {
                Lop lop = new Lop();
                lop.setId(cursor.getInt(0));
                lop.setName(cursor.getString(1));
                list.add(lop);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public SV getSV(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("SV", new String[] {"id", "name", "class_name", "subject"}, "id = ?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        SV sv = new SV(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return sv;
    }

    public void themSV(SV sv) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sv.getName());
        values.put("class_name", sv.getClass_name());
        values.put("subject", sv.getSubject());
        db.insert("SV", null, values);
        db.close();
    }

    public void themLop(Lop lop) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", lop.getName());
        db.insert("Lop", null, values);
        db.close();
    }

    public int suaSV(SV sv) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sv.getName());
        values.put("class_name", sv.getClass_name());
        values.put("subject", sv.getSubject());
        return db.update("SV", values, "id = ?", new String[] {String.valueOf(sv.getId())});
    }

    public void xoaSV(SV sv) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("SV", "id = ?",
                new String[] { String.valueOf(sv.getId()) });
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
