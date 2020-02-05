package com.goodjob.classes

import org.json.JSONObject

class ProductoEspera(var id: Int? = null,
                     var producto: String? = null,
                     var stock: Int? = null,
                     var valor: Double? = null,
                     var fechaRegistro: String? = null) {

    companion object {
        fun cargarDesdeJson(data: JSONObject): ProductoEspera {
            return ProductoEspera().apply {
                id = data.optInt("id")
                producto = data.optString("nombre")
                stock = data.optInt("stock")
                valor = data.optDouble("valor") * 7
                fechaRegistro = data.optString("fecha_registro")
            }
        }
    }
}
