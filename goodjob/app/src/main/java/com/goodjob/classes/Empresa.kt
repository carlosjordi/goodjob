package com.goodjob.classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import org.json.JSONObject

@Parcelize
class Empresa(var id: Int? = null,
              var razonSocial: String? = null,
              var nombreComercial: String? = null,
              var ruc: String? = null,
              var telefono: String? = null,
              var correo: String? = null,
              var direccion: String? = null,
              var fechaRegistro: String? = null,
              var codigoPostal: String? = null,
              var password: String? = null,
              var distrito: String? = null,
              var numeroActividades: Int? = null,
              var estado: Int? = null) : Parcelable {

    companion object {

        fun cargarDatosDesdeJson(data: JSONObject): Empresa {
            return Empresa().apply {
                id = data.optInt("id")
                razonSocial = data.optString("razon_social")
                nombreComercial = data.optString("nombre_comercial")
                ruc = data.optString("ruc")
                telefono = data.optString("telefono")
                correo = data.optString("correo")
                direccion = data.optString("direccion")
                fechaRegistro = data.optString("fecha_registro")
                codigoPostal = data.optString("codigo_postal")
                distrito = data.optString("distrito")
                numeroActividades = data.optInt("numero_actividades")
                estado = data.optInt("estado")
            }
        }
    }
}