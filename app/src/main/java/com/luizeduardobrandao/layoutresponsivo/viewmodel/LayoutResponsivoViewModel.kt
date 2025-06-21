package com.luizeduardobrandao.layoutresponsivo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luizeduardobrandao.layoutresponsivo.model.LayoutResponsivoModel

class LayoutResponsivoViewModel: ViewModel() {

    private val model = LayoutResponsivoModel()

    // exposição do texto atual (para preencher o EditText)
    private val _nameText = MutableLiveData<String>(model.name)
    val nameText: LiveData<String> = _nameText

    // mensagem de Toast
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    // clicar em enviar
    fun onSendClicked(nameInput: String){
        if (nameInput.isBlank()){
            _toastMessage.value = "Escreva um nome."
        }
        else {
            _toastMessage.value = "Nome salvo."
        }
    }

    // emite "" para a Activity limpar o campo
    fun onClearClicked() {
        model.clearName()
        _nameText.value = model.name
    }


}