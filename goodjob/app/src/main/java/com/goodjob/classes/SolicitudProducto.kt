package com.goodjob.classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SolicitudProducto(var id: Int? = null,
                        var nombre: String? = null,
                        var stock: Int? = null,
                        var valor: Double? = null,
                        var empresa: String? = null,
                        var lugarCanje: String? = null,
                        var imagenUrl: String? = null) : Parcelable
