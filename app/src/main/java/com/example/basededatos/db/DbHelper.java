package com.example.basededatos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="agendas.db";
    public static final String TABLE_CONTACTOS="t_contactos";

    public DbHelper(@Nullable Context context)
    {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE "+ TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nombre TEXT," +
                " telefono TEXT," +
                " correo TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTOS);
        onCreate(db);
    }
}
