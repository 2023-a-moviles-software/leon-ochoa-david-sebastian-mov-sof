import java.io.*

class VideojuegoCRUD {
    fun crearVideojuego(videojuego: Videojuego) {
        val archivo = File("videojuegos.dat")
        val videojuegos: MutableList<Videojuego> = if (archivo.exists()) {
            cargarVideojuegos()
        } else {
            mutableListOf()
        }
        videojuegos.add(videojuego)
        guardarVideojuegos(videojuegos)
        println("Videojuego creado con éxito.")
    }

    fun listarVideojuegos() {
        val videojuegos = cargarVideojuegos()
        if (videojuegos.isNotEmpty()) {
            for ((index, videojuego) in videojuegos.withIndex()) {
                println("=== Videojuego ${index + 1} ===")
                println(videojuego)
            }
        } else {
            println("No hay videojuegos registrados.")
        }
    }

    fun actualizarVideojuego(index: Int, nuevoVideojuego: Videojuego) {
        val videojuegos = cargarVideojuegos()
        if (index >= 0 && index < videojuegos.size) {
            videojuegos[index+1] = nuevoVideojuego
            guardarVideojuegos(videojuegos)
            println("Videojuego actualizado con éxito.")
        }
    }

    fun eliminarVideojuego(index: Int) {
        val videojuegos = cargarVideojuegos()
        if (index >= 0 && index < videojuegos.size) {
            videojuegos.removeAt(index)
            guardarVideojuegos(videojuegos)
            println("Videojuego eliminado con éxito.")
        } else {
            println("Índice de videojuego inválido.")
        }
    }

    fun cargarVideojuegos(): MutableList<Videojuego> {
        val archivo = File("videojuegos.dat")
        if (archivo.exists()) {
            ObjectInputStream(FileInputStream(archivo)).use { ois ->
                val videojuegos = ois.readObject() as MutableList<Videojuego>
                return videojuegos
            }
        }
        return mutableListOf()
    }

    private fun guardarVideojuegos(videojuegos: List<Videojuego>) {
        val archivo = File("videojuegos.dat")
        ObjectOutputStream(FileOutputStream(archivo)).use { oos ->
            oos.writeObject(videojuegos)
        }
    }
}
