package com.goodjob.classes

import org.json.JSONObject

class EstadoMisPublicacionesResponse(var id: Int? = null,
                                     var titulo: String? = null,
                                     var fecha: String? = null,
                                     var postulantes: Int? = null,
                                     var estado: Int? = null) {

    companion object {
        fun cargarDesdeJsonObject(data: JSONObject): EstadoMisPublicacionesResponse {
            return EstadoMisPublicacionesResponse().apply {
                id = data.optInt("id")
                titulo = data.optString("titulo")
                fecha = data.optString("fecha_fin")
                postulantes = data.optInt("participantes_actuales")
                estado = data.optInt("estado")
            }
        }
    }
}