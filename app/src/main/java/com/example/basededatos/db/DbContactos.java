package com.example.basededatos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.basededatos.entidades.Contactos;

import java.util.ArrayList;

public class DbContactos extends DbHelper
{
    Context context;

    public DbContactos(@Nullable Context context)
    {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String telefono, String correo)
    {
        long id = 0;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            ContentValues valores = new ContentValues();
            valores.put("nombre", nombre);
            valores.put("telefono", telefono);
            valores.put("correo", correo);

            id = db.insert(TABLE_CONTACTOS, null, valores);
        } catch (Exception ex)
        {
            Toast.makeText(context.getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        } finally
        {
            db.close();
        }
        
        return id;
    }

    public ArrayList<Contactos> mostrarContactos()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        String query = "SELECT * FROM " + TABLE_CONTACTOS + " ORDER BY nombre ASC";

        cursorContactos = db.rawQuery(query, null);

        if (cursorContactos.moveToFirst())
        {
            do
            {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo(cursorContactos.getString(3));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        } else
        {
            Toast.makeText(context.getApplicationContext(), "no hay conexion",Toast.LENGTH_LONG).show();
        }

        cursorContactos.close();
        db.close();

        return listaContactos;
    }

    public Contactos verContactos(int id)
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        String query = "SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1";

        cursorContactos = db.rawQuery(query, null);

        if (cursorContactos.moveToFirst())
        {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo(cursorContactos.getString(3));
        } else
        {
            Toast.makeText(context.getApplicationContext(), "no hay conexion",Toast.LENGTH_LONG).show();
        }

        cursorContactos.close();
        db.close();

        return contacto;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String correo)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            String query = "UPDATE " + TABLE_CONTACTOS + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo = '" + correo + "' WHERE id='" + id + "' ";
            db.execSQL(query);
            correcto = true;
        } catch (Exception ex)
        {
            Toast.makeText(context.getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        } finally
        {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id)
    {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            String query = "DELETE FROM " + TABLE_CONTACTOS + " WHERE id = '" + id + "'";
            db.execSQL(query);
            correcto = true;
        } catch (Exception ex)
        {
            ex.toString();
            correcto = false;
        } finally
        {
            db.close();
        }

        return correcto;
    }
}
