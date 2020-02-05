package com.goodjob.classes

import org.json.JSONObject

class PerfilUsuario(var id: Int? = null,
                    var nombre: String? = null,
                    var puntaje: Int? = null,
                    var reputacion: Double? = null) {

    companion object {
        fun cargarDataDesdeJsonObject(data: JSONObject): PerfilUsuario {
            return PerfilUsuario().apply {
                id = data.optInt("id")
                nombre = data.optString("nombre")
                puntaje = data.optInt("puntaje")

                val reputacionTotal = data.optDouble("reputacion_ptos")
                val cantidadVotos = data.optInt("cantidad_votos")

                reputacion = if (cantidadVotos == 0) 0.0
                else reputacionTotal / cantidadVotos
            }
        }
    }
}