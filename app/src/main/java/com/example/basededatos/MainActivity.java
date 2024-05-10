package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.basededatos.db.DbHelper;

public class MainActivity extends AppCompatActivity
{
    Button btCrear, btnInsertarActivity, btnMostrarActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializarElementos();

        btCrear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CrearBaseDeDatos();
            }
        });

        btnInsertarActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), InsertarRegistro.class);
                startActivity(intent);
            }
        });

        btnMostrarActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MostrarRegistros.class);
                startActivity(intent);
            }
        });
    }

    private void InicializarElementos()
    {
        btCrear = (Button) findViewById(R.id.btnCrear);
        btnInsertarActivity = (Button) findViewById(R.id.btnInsertarActivity);
        btnMostrarActivity = (Button) findViewById(R.id.btnMostrarActivity);
    }

    public void CrearBaseDeDatos()
    {
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db !=null)
        {
            Toast.makeText(getApplicationContext(),"Base de Datos Creada",Toast.LENGTH_LONG).show();
        } else
        {
            Toast.makeText(getApplicationContext(),"Error al Crear Base de Datos Creada",Toast.LENGTH_LONG).show();
        }
    }
}