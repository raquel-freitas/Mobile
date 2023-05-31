package com.example.olhaoburaco_av2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olhaoburaco_av2.databinding.ActivityTela2Binding

class Tela2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTela2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTela2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuarioNome = binding.tvUsuarioNome
        val nome = intent.getStringExtra("nome")
        usuarioNome.text = nome

        binding.button2.setOnClickListener {
            val intent = Intent(this, CadastroOcorrenciaActivity::class.java)
            startActivity(intent)

        }
}
}
