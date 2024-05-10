package com.example.basededatos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.basededatos.db.DbContactos;
import com.example.basededatos.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Ver extends AppCompatActivity
{
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar, btnSalir;
    FloatingActionButton fabEditar, fabEliminar;

    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        InicializarElementos();

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();

            if(extras == null)
            {
                id = Integer.parseInt(null);
            } else
            {
                id = extras.getInt("ID");
            }
        } else
        {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbContactos dbContactos = new DbContactos(Ver.this);
        contacto = dbContactos.verContactos(id);

        if(contacto != null)
        {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo());
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Ver.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ver.this);

                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                                if(dbContactos.eliminarContacto(id))
                                {
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }
                        }).show();
            }
        });


        btnSalir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Ver.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InicializarElementos()
    {
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuardar = findViewById(R.id.btnGuarda);
        btnGuardar.setVisibility(View.INVISIBLE);
        btnSalir = findViewById(R.id.btnSalir);
    }

    private void lista()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}