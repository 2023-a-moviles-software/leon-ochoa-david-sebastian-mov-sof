import java.io.Serializable
import java.util.*

data class Videojuego(
    var nombre: String,
    var genero: String,
    var calificacion: Int,
    var isMultijugador: Boolean,
    var fechaLanzamiento: Date
) : Serializable
