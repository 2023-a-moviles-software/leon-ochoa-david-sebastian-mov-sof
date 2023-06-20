import java.io.*

class TiendaCRUD {
    fun crearTienda(tienda: Tienda) {
        val archivo = File("tiendas.dat")
        val tiendas: MutableList<Tienda> = if (archivo.exists()) {
            cargarTiendas()
        } else {
            mutableListOf()
        }
        tiendas.add(tienda)
        guardarTiendas(tiendas)
        println("Tienda creada con éxito.")
    }

    fun listarTiendas() {
        val tiendas = cargarTiendas()
        if (tiendas.isNotEmpty()) {
            for ((index, tienda) in tiendas.withIndex()) {
                println("=== Tienda ${index + 1} ===")
                println(tienda)
            }
        } else {
            println("No hay tiendas registradas.")
        }
    }

    fun actualizarTienda(index: Int, nuevaTienda: Tienda) {
        val tiendas = cargarTiendas()
        if (index >= 0 && index < tiendas.size) {
            tiendas[index] = nuevaTienda
            guardarTiendas(tiendas)
            println("Tienda actualizada con éxito.")
        }
    }

    fun eliminarTienda(index: Int) {
        val tiendas = cargarTiendas()
        if (index >= 0 && index < tiendas.size) {
            tiendas.removeAt(index)
            guardarTiendas(tiendas)
            println("Tienda eliminada con éxito.")
        } else {
            println("Índice de tienda inválido.")
        }
    }

    fun cargarTiendas(): MutableList<Tienda> {
        val archivo = File("tiendas.dat")
        if (archivo.exists()) {
            ObjectInputStream(FileInputStream(archivo)).use { ois ->
                val tiendas = ois.readObject() as MutableList<Tienda>
                return tiendas
            }
        }
        return mutableListOf()
    }

    private fun guardarTiendas(tiendas: List<Tienda>) {
        val archivo = File("tiendas.dat")
        ObjectOutputStream(FileOutputStream(archivo)).use { oos ->
            oos.writeObject(tiendas)
        }
    }
}
