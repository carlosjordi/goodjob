package com.goodjob.classes

import org.json.JSONObject

class EstadoMisPublicacionesResponse(var id: Int? = null,
                                     var titulo: String? = null,
                                     var fecha: String? = null,
                                     var postulantes: Int? = null,
                                     var estado: Int? = null) {

    companion object {

        fun cargarDesdeJsonObject(data: JSONObject): EstadoMisPublicacionesResponse {
            val response = EstadoMisPublicacionesResponse()
            response.id = data.optInt("id")
            response.titulo = data.optString("titulo")
            response.fecha = data.optString("fecha_fin")
            response.postulantes = data.optInt("participantes_actuales")
            response.estado = data.optInt("estado")
            return response
        }
    }
}