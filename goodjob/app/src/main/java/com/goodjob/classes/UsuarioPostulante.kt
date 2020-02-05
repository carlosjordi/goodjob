package com.goodjob.classes

import org.json.JSONObject

class UsuarioPostulante(var id: Int? = null,
                        var nombre: String? = null,
                        var reputacion: Double? = null,
                        var estado: String? = null) {

    companion object {

        fun cargarDesdeJsonObject(data: JSONObject): UsuarioPostulante {
            val postulante = UsuarioPostulante()
            postulante.id = data.optInt("id")
            postulante.nombre = data.optString("nombre")
            val reputacionPtos = data.optDouble("reputacion_ptos")
            val cantidadVotos = data.optInt("cantidad_votos")
            val estado = data.optInt("estado")

            if (cantidadVotos == 0)
                postulante.reputacion = 0.0
            else
                postulante.reputacion = reputacionPtos / cantidadVotos

            when (estado) {
                1 -> postulante.estado = "En Espera"
                2 -> postulante.estado = "Aceptado"
                3 -> postulante.estado = "Rechazado"
            }
            return postulante
        }
    }
}