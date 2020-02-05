package com.goodjob.classes

object ValidSession {

    var usuarioLogueado: User? = null
    var empresaLogueada: Empresa? = null
    private val LOCAL = "https://goodjob-s601.000webhostapp.com"
    val IP = "$LOCAL/Conexiones"
    val IP_IMAGENES = "$LOCAL/imagenes"
    val IMAGENES_ACTIVIDADES = "$IP_IMAGENES/imagenes_actividades/"
    val IMAGENES_PRODUCTOS = "$IP_IMAGENES/imagenes_productos/"
}