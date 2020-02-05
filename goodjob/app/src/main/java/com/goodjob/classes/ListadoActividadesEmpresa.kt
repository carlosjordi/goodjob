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
            val actividad = ListadoActividadesEmpresa()
            actividad.id = json.optInt("id")
            actividad.titulo = json.optString("titulo")
            actividad.descripcion = json.optString("descripcion")
            actividad.empresa = json.optString("empresa")
            actividad.fechaCreacion = json.optString("fecha_creacion")
            actividad.fechaFin = json.optString("fecha_fin")
            actividad.participantesActuales = json.optInt("participantes_actuales")
            actividad.participantesRequeridos = json.optInt("participantes_requeridos")
            actividad.urlFoto = json.optString("url_foto")
            actividad.tipoRecompensa = json.optString("tipo_recompensa")
            actividad.recompensa = json.optDouble("recompensa")
            actividad.distrito = json.optString("distrito")
            actividad.tipoSeleccion = json.optInt("tipo_seleccion")
            actividad.mensaje = json.optString("mensaje")
            actividad.estado = json.optInt("estado")
            return actividad
        }
    }
}
