package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import com.example.basededatos.adaptadores.ListaContactosAdapter;
import com.example.basededatos.db.DbContactos;
import com.example.basededatos.entidades.Contactos;
import java.util.ArrayList;

public class MostrarRegistros extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;
    ListaContactosAdapter adapter;

    SearchView txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registros);

        InicializarElementos();

        DbContactos dbContactos = new DbContactos(MostrarRegistros.this);

        listaArrayContactos = new ArrayList<>();

        adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);

        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        txtBuscar.setOnQueryTextListener(this);
    }

    private void InicializarElementos()
    {
        txtBuscar = findViewById(R.id.txtBuscar);
        listaContactos = findViewById(R.id.listaContactos);
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        adapter.filtrado(newText);
        return false;
    }
}