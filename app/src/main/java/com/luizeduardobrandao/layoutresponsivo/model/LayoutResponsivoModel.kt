package com.luizeduardobrandao.layoutresponsivo.model

class LayoutResponsivoModel {

    // significa que todos podem ler (get) name, mas só a própria classe pode alterá-lo (set),
    // porque o setter foi marcado como private.
    var name: String = ""
        private set

    fun saveName(name: String) {
        this.name = name
        // Aqui você pode persistir em SharedPreferences, DB etc.
    }

    fun clearName() {
        name = ""
    }
}