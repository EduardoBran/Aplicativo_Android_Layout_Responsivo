package com.luizeduardobrandao.layoutresponsivo.view

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizeduardobrandao.layoutresponsivo.R
import com.luizeduardobrandao.layoutresponsivo.databinding.ActivityMainBinding
import com.luizeduardobrandao.layoutresponsivo.viewmodel.LayoutResponsivoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LayoutResponsivoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1) Infla o layout e seta a ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2) Configura a Toolbar (binding.toolbarMain) como ActionBar da Activity
        setSupportActionBar(binding.toolbar)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListenners()
        setObservers()

    }

    // Sobrescrever o mét0do que infla o menu para aparecer o ícone
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Envia eventos para o ViewModel
    private fun setListenners(){

        binding.buttonSend.setOnClickListener {
            viewModel.onSendClicked(binding.editTextInput.text.toString())
        }

        binding.buttonClear.setOnClickListener {
            viewModel.onClearClicked()
        }

    }

    // Observa as mudanças de estado
    private fun setObservers() {

        viewModel.toastMessage.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.nameText.observe(this) { newText ->
            if (binding.editTextInput.text.toString() != newText){
                binding.editTextInput.setText(newText)
            }
        }
    }
}