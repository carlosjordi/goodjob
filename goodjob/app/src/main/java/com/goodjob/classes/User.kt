package com.goodjob.classes

import org.json.JSONObject

class User(var id: Int? = null,
           var url_foto: String? = null,
           var nombres: String? = null,
           var paterno: String? = null,
           var materno: String? = null,
           var dni: String? = null,
           var fechaNacimiento: String? = null,
           var celular: String? = null,
           var correo: String? = null,
           var direccion: String? = null,
           var password: String? = null,
           var fechaRegistro: String? = null,
           var reputacionPuntos: Double = 0.toDouble(),
           var cantidadVotos: Int? = null,
           var estado: Int? = null,
           var numeroTarjeta: String? = null,
           var genero: String? = null,
           var estadoCivil: String? = null,
           var cv: String? = null,
           var distrito: String? = null,
           var linkFb: String? = null,
           var linkLd: String? = null,
           var tipoUsuario: String? = null,
           var puntaje: Int? = null) {

    fun loadUserDataFromJsonObject(jsonObject: JSONObject) {
        this.apply {
            id = jsonObject.optInt("id")
            url_foto = jsonObject.optString("url_foto")
            nombres = jsonObject.optString("nombre")
            paterno = jsonObject.optString("paterno")
            materno = jsonObject.optString("materno")
            dni = jsonObject.optString("dni")
            fechaNacimiento = jsonObject.optString("fecha_nacimiento")
            celular = jsonObject.optString("celular")
            correo = jsonObject.optString("correo")
            direccion = jsonObject.optString("direccion")
            fechaRegistro = jsonObject.optString("fecha_registro")
            reputacionPuntos = jsonObject.optDouble("reputacion_ptos")
            cantidadVotos = jsonObject.optInt("cantidad_votos")
            estado = jsonObject.optInt("estado")
            numeroTarjeta = jsonObject.optString("numero_tarjeta")
            genero = jsonObject.optString("genero")
            estadoCivil = jsonObject.optString("estado_civil")
            cv = jsonObject.optString("cv")
            distrito = jsonObject.optString("distrito")
            linkFb = jsonObject.optString("link_fb")
            linkLd = jsonObject.optString("link_linkedin")
            tipoUsuario = jsonObject.optString("tipo_usuario")
            puntaje = jsonObject.optInt("puntaje")
        }
    }
}