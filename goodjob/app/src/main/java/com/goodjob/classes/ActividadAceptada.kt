package com.goodjob.classes

import org.json.JSONObject

class ActividadAceptada(var titulo: String? = null,
                        var nombreAutor: String? = null,
                        var fechaFin: String? = null,
                        var descripcion: String? = null,
                        var recompensa: String? = null) {

    companion object {

        fun cargarDatosDesdeJsonObject(data: JSONObject): ActividadAceptada {
            val actividadAceptada = ActividadAceptada()

            actividadAceptada.titulo = data.optString("titulo")
            actividadAceptada.nombreAutor = data.optString("nombre_completo")
            actividadAceptada.fechaFin = data.optString("fecha_fin")
            actividadAceptada.descripcion = data.optString("descripcion")
            actividadAceptada.recompensa = data.optString("recompensa")

            return actividadAceptada
        }
    }
}