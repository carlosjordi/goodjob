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

        fun loadFromJsonObject(jsonObject: JSONObject): Actividad {
            return Actividad().apply {
                id = jsonObject.optInt("id")
                title = jsonObject.optString("titulo")
                description = jsonObject.optString("descripcion")
                author = jsonObject.optString("empresa")
                creationDate = jsonObject.optString("fecha_creacion")
                endDate = jsonObject.optString("fecha_fin")
                currentParticipants = jsonObject.optInt("participantes_actuales")
                requiredParticipants = jsonObject.optInt("participantes_requeridos")
                photo = jsonObject.optString("url_foto")
                rewardType = jsonObject.optString("tipo_recompensa")
                reward = jsonObject.optDouble("recompensa")
                distrito = jsonObject.optString("distrito")
                tipoSeleccion = jsonObject.optString("tipo_seleccion")
                mensaje = jsonObject.optString("mensaje")
                status = jsonObject.optInt("estado")
            }
        }
    }
}