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
            return EstadoMisActividadesResponse().apply {
                id = data.optInt("id")
                titulo = data.optString("titulo")
                fecha = data.optString("fecha_fin")
                estado = when (data.optInt("estado")) {
                    1 -> "En Espera"
                    2 -> "Aceptado"
                    3 -> "Rechazado"
                    else -> ""
                }
                distrito = data.optString("distrito")
            }
        }
    }
}