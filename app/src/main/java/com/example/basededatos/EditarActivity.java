package com.example.basededatos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basededatos.db.DbContactos;
import com.example.basededatos.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity
{
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuarda, btnSalir;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Contactos contacto;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        InicializarElementos();

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras == null)
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

        final DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contacto = dbContactos.verContactos(id);

        if (contacto != null)
        {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals(""))
                {
                    correcto = dbContactos.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());

                    if(correcto)
                    {
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else
                    {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else
                {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InicializarElementos()
    {
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setVisibility(View.INVISIBLE);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);
    }

    private void verRegistro()
    {
        Intent intent = new Intent(this, Ver.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}