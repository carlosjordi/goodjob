package com.goodjob.classes

import org.json.JSONObject

class UsuarioPostulante(var id: Int? = null,
                        var nombre: String? = null,
                        var reputacion: Double? = null,
                        var estado: String? = null) {

    companion object {

        fun cargarDesdeJsonObject(data: JSONObject): UsuarioPostulante {
            return UsuarioPostulante().apply {
                id = data.optInt("id")
                nombre = data.optString("nombre")
                val reputacionPtos = data.optDouble("reputacion_ptos")
                val cantidadVotos = data.optInt("cantidad_votos")

                reputacion = if (cantidadVotos == 0) 0.0
                else reputacionPtos / cantidadVotos

                estado = when (data.optInt("estado")) {
                    1 -> "En Espera"
                    2 -> "Aceptado"
                    3 -> "Rechazado"
                    else -> ""
                }
            }
        }
    }
}