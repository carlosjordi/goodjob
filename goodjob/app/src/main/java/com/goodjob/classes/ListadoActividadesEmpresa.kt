package com.goodjob.classes

import org.json.JSONObject

class ListadoActividadesEmpresa(var id: Int? = null,
                                var titulo: String? = null,
                                var descripcion: String? = null,
                                var empresa: String? = null,
                                var fechaCreacion: String? = null,
                                var fechaFin: String? = null,
                                var participantesActuales: Int? = null,
                                var participantesRequeridos: Int? = null,
                                var urlFoto: String? = null,
                                var tipoRecompensa: String? = null,
                                var recompensa: Double? = null,
                                var distrito: String? = null,
                                var tipoSeleccion: Int? = null,
                                var mensaje: String? = null,
                                var estado: Int? = null) {

    companion object {
        fun crearDesdeJson(json: JSONObject): ListadoActividadesEmpresa {
            return ListadoActividadesEmpresa().apply {
                id = json.optInt("id")
                titulo = json.optString("titulo")
                descripcion = json.optString("descripcion")
                empresa = json.optString("empresa")
                fechaCreacion = json.optString("fecha_creacion")
                fechaFin = json.optString("fecha_fin")
                participantesActuales = json.optInt("participantes_actuales")
                participantesRequeridos = json.optInt("participantes_requeridos")
                urlFoto = json.optString("url_foto")
                tipoRecompensa = json.optString("tipo_recompensa")
                recompensa = json.optDouble("recompensa")
                distrito = json.optString("distrito")
                tipoSeleccion = json.optInt("tipo_seleccion")
                mensaje = json.optString("mensaje")
                estado = json.optInt("estado")
            }
        }
    }
}
