package com.example.olhaoburaco_av2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.olhaoburaco_av2.databinding.ActivityCadastroOcorrenciaBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CadastroOcorrenciaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCadastroOcorrenciaBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroOcorrenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var edOcorrencia = binding.edOcorrencia
        var edEndereco = binding.edEndereco
        var edCEP = binding.edCEP
        var btCadastrar = binding.button

        dbRef = FirebaseDatabase.getInstance().getReference("Ocorrências")

        btCadastrar.setOnClickListener{
            val empOcorrencia = edOcorrencia.text.toString()
            val empEndereco = edEndereco.text.toString()
            val empCEP = edCEP.text.toString()

            if(empOcorrencia.isEmpty()){
                edOcorrencia.error = "Por favor insira uma ocorrência"
            }
            if(empEndereco.isEmpty()){
                edEndereco.error = "Por favor insira um endereço válido"
            }
            if(empCEP.length != 8){
                edCEP.error = "Por favor insira um CEP válido"
            }

            val empId = dbRef.push().key!!

            val ocorrencia = ModeloOcorrencia(empId, empOcorrencia, empEndereco, empCEP)

            dbRef.child(empId).setValue(ocorrencia)
                .addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()

                    edOcorrencia.text.clear()
                    edEndereco.text.clear()
                    edCEP.text.clear()

                    val intent = Intent(this, ListaOcorrenciasActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}

