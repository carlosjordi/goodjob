package com.goodjob.classes

import org.json.JSONObject

class ActividadAceptada(var titulo: String? = null,
                        var nombreAutor: String? = null,
                        var fechaFin: String? = null,
                        var descripcion: String? = null,
                        var recompensa: String? = null) {

    companion object {

        fun cargarDatosDesdeJsonObject(data: JSONObject): ActividadAceptada {
            return ActividadAceptada().apply {
                titulo = data.optString("titulo")
                nombreAutor = data.optString("nombre_completo")
                fechaFin = data.optString("fecha_fin")
                descripcion = data.optString("descripcion")
                recompensa = data.optString("recompensa")
            }
        }
    }
}