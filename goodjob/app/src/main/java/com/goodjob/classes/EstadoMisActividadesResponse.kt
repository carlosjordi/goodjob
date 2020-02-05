package com.goodjob.classes

import android.view.View

import org.json.JSONObject

class EstadoMisActividadesResponse(var id: Int? = null,
                                   var titulo: String? = null,
                                   var autor: String? = null,
                                   var fecha: String? = null,
                                   var estado: String? = null,
                                   var distrito: String? = null,
                                   var item: View? = null) {

    companion object {

        fun cargarDataDesdeJsonObject(data: JSONObject): EstadoMisActividadesResponse {
            val miActividad = EstadoMisActividadesResponse()

            miActividad.id = data.optInt("id")
            miActividad.titulo = data.optString("titulo")
            miActividad.fecha = data.optString("fecha_fin")
            when (data.optInt("estado")) {
                1 -> miActividad.estado = "En Espera"
                2 -> miActividad.estado = "Aceptado"
                3 -> miActividad.estado = "Rechazado"
            }
            miActividad.distrito = data.optString("distrito")
            return miActividad
        }
    }
}