package com.goodjob.classes

import org.json.JSONObject

class UsuarioParticipante(var id: Int? = null,
                          var nombre: String? = null,
                          var reputacionPromedio: Double? = null) {

    companion object {
        fun cargarDataDesdeJsonObject(data: JSONObject): UsuarioParticipante {
            return UsuarioParticipante().apply {
                id = data.optInt("id")
                nombre = data.optString("nombre")
                val reputacionPtos = data.optDouble("reputacion_ptos")
                val cantidadVotos = data.optInt("cantidad_votos")

                reputacionPromedio = if (cantidadVotos == 0) 0.0
                else reputacionPtos / cantidadVotos
            }
        }
    }
}