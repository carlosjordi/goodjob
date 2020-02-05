package com.goodjob.classes

import org.json.JSONObject

class ProductoEspera(var id: Int? = null,
                     var producto: String? = null,
                     var stock: Int? = null,
                     var valor: Double? = null,
                     var fechaRegistro: String? = null) {

    companion object {

        fun cargarDesdeJson(data: JSONObject): ProductoEspera {
            val pe = ProductoEspera()
            pe.id = data.optInt("id")
            pe.producto = data.optString("nombre")
            pe.stock = data.optInt("stock")
            pe.valor = data.optDouble("valor") * 7
            pe.fechaRegistro = data.optString("fecha_registro")
            return pe
        }
    }
}
