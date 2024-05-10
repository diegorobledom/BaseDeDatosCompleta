package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basededatos.db.DbContactos;

public class InsertarRegistro extends AppCompatActivity
{
    EditText etNombre, etTelefono, etCorreo;
    Button btnInsertar;
    String nombre, telefono, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_registro);

        InicializarElementos();

        btnInsertar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                InsertarElemento();
            }
        });
    }

    private void InicializarElementos()
    {
        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        btnInsertar = (Button) findViewById(R.id.btnInsertar);
    }

    public void InsertarElemento()
    {
        long id=0;

        nombre = etNombre.getText().toString();
        telefono = etTelefono.getText().toString();
        correo = etCorreo.getText().toString();

        try
        {
            DbContactos dbContactos = new DbContactos(getApplicationContext());
            id = dbContactos.insertarContacto(nombre, telefono, correo);
        } catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Error en Registro",Toast.LENGTH_LONG).show();
        }

        if(id!=0)
        {
            Toast.makeText(getApplicationContext(),"Registro Agregado con éxito",Toast.LENGTH_LONG).show();

            etNombre.setText("");
            etTelefono.setText("");
            etCorreo.setText("");
        }
    }
}