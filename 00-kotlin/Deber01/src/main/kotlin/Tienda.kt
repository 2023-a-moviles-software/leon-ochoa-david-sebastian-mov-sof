import java.io.Serializable

data class Tienda(
    var nombre: String,
    var ubicacion: String,
    var cantidadEmpleados: Int,
    var isOpen: Boolean,
    var ingresosAnuales: Double
) : Serializable
