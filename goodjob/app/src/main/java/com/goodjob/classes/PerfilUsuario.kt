package com.goodjob.classes

import org.json.JSONObject

class PerfilUsuario(var id: Int? = null,
                    var nombre: String? = null,
                    var puntaje: Int? = null,
                    var reputacion: Double? = null) {

    companion object {

        fun cargarDataDesdeJsonObject(data: JSONObject): PerfilUsuario {

            val perfilUsuario = PerfilUsuario()
            perfilUsuario.id = data.optInt("id")
            perfilUsuario.nombre = data.optString("nombre")
            perfilUsuario.puntaje = data.optInt("puntaje")

            val reputacionTotal = data.optDouble("reputacion_ptos")
            val cantidadVotos = data.optInt("cantidad_votos")

            if (cantidadVotos == 0)
                perfilUsuario.reputacion = 0.0
            else
                perfilUsuario.reputacion = reputacionTotal / cantidadVotos

            return perfilUsuario
        }
    }
}