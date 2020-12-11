package com.example.obtenerviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.obtenerviews.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var boton : Button
    private lateinit var etUser : EditText
    private lateinit var etPassword : EditText

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            // FORMA 1:
            //boton.isEnabled = etUser.text.isNotEmpty() && etPassword.text.isNotEmpty()

            // FORMA 2:
            //button.isEnabled = editTextUser.text.isNotEmpty() && editTextPassword.text.isNotEmpty()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)    // Línea necesaria para las FORMAS 1 y 2

        // Siguiente dos líneas necesarias para la FORMA 3:
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //////////////////////////////////////////////////////////////////////////////////////////////

        // FORMA 1: Creando las vistas necesarias
        /*val tick = findViewById<ImageView>(R.id.tickImage)
        boton = findViewById<Button>(R.id.button)
        etUser = findViewById<EditText>(R.id.editTextUser)
        etPassword = findViewById<EditText>(R.id.editTextPassword)

        etUser.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
        boton.setOnClickListener {
            tick.isVisible = true
        }*/

        //////////////////////////////////////////////////////////////////////////////////////////////

        // FORMA 2: Añadiendo en Gradle Scripts --> id 'kotlin-android-extensions'
        // Y hago los imports sintéticos --> import kotlinx.android.synthetic.main.activity_main.*
        // De esta forma ya puedo usar los elementos con el id que tienen en el activity_main
        /*editTextUser.addTextChangedListener(textWatcher)
        editTextPassword.addTextChangedListener(textWatcher)
        button.setOnClickListener {
            tickImage.isVisible = true
        }*/

        //////////////////////////////////////////////////////////////////////////////////////////////

        // FORMA 3: View BINDING
        // Añadiendo en Gradle Scripts --> viewBinding.enabled = true
        // En vez de usar el xml activity_main, utilizaré la clase autogenerada por la librería de ViewBinding
        // Eliminaría la línea 38, escribo --> val binding = ActivityMainBinding.inflate(layoutInflater)
        // Y al setContentView le paso la vista que se ha inflado con el viewBinding --> setContentView(binding.root)
        // Para acceder a cada vista utilizo binding.nombreVista

        binding.editTextUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    binding.button.isEnabled = it.isNotEmpty()
                }
            }

        })

        binding.button.setOnClickListener {
            binding.tickImage.isVisible = true
        }

        //////////////////////////////////////////////////////////////////////////////////////////////
    }
}