package com.goodjob.classes

import org.json.JSONObject

class UsuarioParticipante(var id: Int? = null,
                          var nombre: String? = null,
                          var reputacionPromedio: Double? = null) {

    companion object {

        fun cargarDataDesdeJsonObject(data: JSONObject): UsuarioParticipante {

            val usuarioParticipante = UsuarioParticipante()
            usuarioParticipante.id = data.optInt("id")
            usuarioParticipante.nombre = data.optString("nombre")
            val reputacionPtos = data.optDouble("reputacion_ptos")
            val cantidadVotos = data.optInt("cantidad_votos")

            if (cantidadVotos == 0)
                usuarioParticipante.reputacionPromedio = 0.0
            else
                usuarioParticipante.reputacionPromedio = reputacionPtos / cantidadVotos

            return usuarioParticipante
        }
    }
}