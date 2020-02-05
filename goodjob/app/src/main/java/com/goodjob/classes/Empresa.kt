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

            val empresa = Empresa()
            empresa.id = data.optInt("id")
            empresa.razonSocial = data.optString("razon_social")
            empresa.nombreComercial = data.optString("nombre_comercial")
            empresa.ruc = data.optString("ruc")
            empresa.telefono = data.optString("telefono")
            empresa.correo = data.optString("correo")
            empresa.direccion = data.optString("direccion")
            empresa.fechaRegistro = data.optString("fecha_registro")
            empresa.codigoPostal = data.optString("codigo_postal")
            empresa.distrito = data.optString("distrito")
            empresa.numeroActividades = data.optInt("numero_actividades")
            empresa.estado = data.optInt("estado")
            return empresa
        }
    }
}