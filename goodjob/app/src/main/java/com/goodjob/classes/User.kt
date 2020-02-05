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
        this.id = jsonObject.optInt("id")
        this.url_foto = jsonObject.optString("url_foto")
        this.nombres = jsonObject.optString("nombre")
        this.paterno = jsonObject.optString("paterno")
        this.materno = jsonObject.optString("materno")
        this.dni = jsonObject.optString("dni")
        this.fechaNacimiento = jsonObject.optString("fecha_nacimiento")
        this.celular = jsonObject.optString("celular")
        this.correo = jsonObject.optString("correo")
        this.direccion = jsonObject.optString("direccion")
        this.fechaRegistro = jsonObject.optString("fecha_registro")
        this.reputacionPuntos = jsonObject.optDouble("reputacion_ptos")
        this.cantidadVotos = jsonObject.optInt("cantidad_votos")
        this.estado = jsonObject.optInt("estado")
        this.numeroTarjeta = jsonObject.optString("numero_tarjeta")
        this.genero = jsonObject.optString("genero")
        this.estadoCivil = jsonObject.optString("estado_civil")
        this.cv = jsonObject.optString("cv")
        this.distrito = jsonObject.optString("distrito")
        this.linkFb = jsonObject.optString("link_fb")
        this.linkLd = jsonObject.optString("link_linkedin")
        this.tipoUsuario = jsonObject.optString("tipo_usuario")
        this.puntaje = jsonObject.optInt("puntaje")
    }
}