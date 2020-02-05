package com.goodjob.classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import org.json.JSONObject

@Parcelize
class Actividad(var id: Int? = null,
                var title: String? = null,
                var description: String? = null,
                var author: String? = null,
                var creationDate: String? = null,
                var endDate: String? = null,
                var currentParticipants: Int? = null,
                var requiredParticipants: Int? = null,
                var photo: String? = null,
                var rewardType: String? = null,
                var reward: Double? = null,
                var distrito: String? = null,
                var tipoSeleccion: String? = null,
                var mensaje: String? = null,
                var status: Int? = null) : Parcelable {

    companion object {

        fun loadActivityDataFromJsonObject(jsonObject: JSONObject): Actividad {
            val actividad = Actividad()

            actividad.id = jsonObject.optInt("id")
            actividad.title = jsonObject.optString("titulo")
            actividad.description = jsonObject.optString("descripcion")
            actividad.author = jsonObject.optString("empresa")
            actividad.creationDate = jsonObject.optString("fecha_creacion")
            actividad.endDate = jsonObject.optString("fecha_fin")
            actividad.currentParticipants = jsonObject.optInt("participantes_actuales")
            actividad.requiredParticipants = jsonObject.optInt("participantes_requeridos")
            actividad.photo = jsonObject.optString("url_foto")
            actividad.rewardType = jsonObject.optString("tipo_recompensa")
            actividad.reward = jsonObject.optDouble("recompensa")
            actividad.distrito = jsonObject.optString("distrito")
            actividad.tipoSeleccion = jsonObject.optString("tipo_seleccion")
            actividad.mensaje = jsonObject.optString("mensaje")
            actividad.status = jsonObject.optInt("estado")

            return actividad
        }
    }
}