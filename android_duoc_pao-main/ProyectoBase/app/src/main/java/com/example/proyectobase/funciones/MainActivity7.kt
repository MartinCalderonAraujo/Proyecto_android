package com.example.proyectobase.funciones

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectobase.R

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main7)

        val txTituloMenu : TextView = findViewById(R.id.txTituloMenu7)
        val edTextNombre : EditText = findViewById(R.id.edTextoNombre)
        val edTextApellido : EditText = findViewById(R.id.edTextApellido)
        val spGrupo : Spinner = findViewById(R.id.spGrupo)
        val spSeccion : Spinner = findViewById(R.id.spSeccion)
        val btnFoto : Button = findViewById(R.id.btnFoto)
        val btnGuardar : Button = findViewById(R.id.btnGuardar)

        val grupos = listOf(
            "1","2","3","4","5","6","7","8","9"
        )

        val secciones = listOf(
            "a","b","c","d"
        )

        val adaptadorGrupos = ArrayAdapter(this, android.R.layout.simple_spinner_item, grupos).apply (){
            setDropDownViewResource(android.R.layout.simple_spinner_item)
        }
        val adaptadorSecciones = ArrayAdapter(this, android.R.layout.simple_spinner_item, secciones).apply (){
            setDropDownViewResource(android.R.layout.simple_spinner_item)
        }

        spGrupo.adapter = adaptadorGrupos
        spSeccion.adapter = adaptadorSecciones


        btnGuardar.setOnClickListener {

            InsertarAlumnosAPI.insertarAlumno(
                owner = this,
                context = this,
                nombre = if (edTextNombre.text.toString().isEmpty()) {
                    Toast.makeText(this, "El campo está vacío", Toast.LENGTH_SHORT).show()
                    ""
                } else {
                    edTextNombre.text.toString()
                }
                ,
                apellido = if (edTextApellido.text.toString().isEmpty()) {
                    Toast.makeText(this, "El campo está vacío", Toast.LENGTH_SHORT).show()
                    ""
                } else {
                    edTextApellido.text.toString()
                },
                grupo = spGrupo.selectedItem.toString(),
                seccion = spSeccion.selectedItem.toString(),
                archivoBytes = null,
                onSuccess = {
                    println("Termino insert correcto")
                    Toast.makeText(this,"guardado",Toast.LENGTH_SHORT)

                },
                onError = {
                    println("Error")
                }
            )



        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}