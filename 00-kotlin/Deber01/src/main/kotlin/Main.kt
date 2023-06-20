import java.io.*
import java.text.SimpleDateFormat
import java.util.*

data class Tienda(
    var nombre: String,
    var ubicacion: String,
    var cantidadEmpleados: Int,
    var isOpen: Boolean,
    var ingresosAnuales: Double
) : Serializable

data class Videojuego(
    var nombre: String,
    var genero: String,
    var calificacion: Int,
    var isMultijugador: Boolean,
    var fechaLanzamiento: Date
) : Serializable

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

fun listarTiendas() {
    val tiendas = cargarTiendas()
    if (tiendas.isNotEmpty()) {
        for ((index, tienda) in tiendas.withIndex()) {
            println("=== Tienda $index ===")
            println(tienda)
        }
    } else {
        println("No hay tiendas registradas.")
    }
}

fun listarVideojuegos() {
    val videojuegos = cargarVideojuegos()
    if (videojuegos.isNotEmpty()) {
        for ((index, videojuego) in videojuegos.withIndex()) {
            println("=== Videojuego $index ===")
            println(videojuego)
        }
    } else {
        println("No hay videojuegos registrados.")
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

fun actualizarVideojuego(index: Int, nuevoVideojuego: Videojuego) {
    val videojuegos = cargarVideojuegos()
    if (index >= 0 && index < videojuegos.size) {
        videojuegos[index] = nuevoVideojuego
        guardarVideojuegos(videojuegos)
        println("Videojuego actualizado con éxito.")
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

fun guardarTiendas(tiendas: List<Tienda>) {
    val archivo = File("tiendas.dat")
    ObjectOutputStream(FileOutputStream(archivo)).use { oos ->
        oos.writeObject(tiendas)
    }
}

fun guardarVideojuegos(videojuegos: List<Videojuego>) {
    val archivo = File("videojuegos.dat")
    ObjectOutputStream(FileOutputStream(archivo)).use { oos ->
        oos.writeObject(videojuegos)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    loop@ while (true) {
        println("==== MENÚ ====")
        println("1. Crear tienda")
        println("2. Crear videojuego")
        println("3. Listar tiendas")
        println("4. Listar videojuegos")
        println("5. Actualizar tienda")
        println("6. Actualizar videojuego")
        println("7. Eliminar tienda")
        println("8. Eliminar videojuego")
        println("9. Salir")
        println("Ingrese la opción deseada:")
        when (scanner.nextInt()) {
            1 -> {
                scanner.nextLine()
                println("Ingrese el nombre de la tienda:")
                val nombre = scanner.nextLine()
                println("Ingrese la ubicación de la tienda:")
                val ubicacion = scanner.nextLine()
                println("Ingrese la cantidad de empleados de la tienda:")
                val cantidadEmpleados = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("La tienda está abierta? (true/false):")
                val isOpen = scanner.nextBoolean()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("Ingrese los ingresos anuales de la tienda:")
                val ingresosAnuales = scanner.nextDouble()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val tienda = Tienda(nombre, ubicacion, cantidadEmpleados, isOpen, ingresosAnuales)
                crearTienda(tienda)
            }
            2 -> {
                scanner.nextLine()
                println("Ingrese el nombre del videojuego:")
                val nombre = scanner.nextLine()
                println("Ingrese el género del videojuego:")
                val genero = scanner.nextLine()
                println("Ingrese la calificación del videojuego:")
                val calificacion = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("El videojuego es multijugador? (true/false):")
                val isMultijugador = scanner.nextBoolean()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("Ingrese la fecha de lanzamiento del videojuego (dd/mm/aaaa):")
                val fechaLanzamiento = SimpleDateFormat("dd/MM/yyyy").parse(scanner.next())
                scanner.nextLine() // Consumir el salto de línea pendiente

                val videojuego = Videojuego(nombre, genero, calificacion, isMultijugador, fechaLanzamiento)
                crearVideojuego(videojuego)
            }
            3 -> {
                println("=== Tiendas ===")
                listarTiendas()
                println("================")
            }
            4 -> {
                println("=== Videojuegos ===")
                listarVideojuegos()
                println("===================")
            }
            5 -> {
                println("Ingrese el índice de la tienda a actualizar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente
                val tiendas = cargarTiendas()
                if (index >= 0 && index < tiendas.size) {
                    println("Ingrese el nombre de la tienda:")
                    val nombre = scanner.nextLine()
                    println("Ingrese la ubicación de la tienda:")
                    val ubicacion = scanner.nextLine()
                    println("Ingrese la cantidad de empleados de la tienda:")
                    val cantidadEmpleados = scanner.nextInt()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("La tienda está abierta? (true/false):")
                    val isOpen = scanner.nextBoolean()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("Ingrese los ingresos anuales de la tienda:")
                    val ingresosAnuales = scanner.nextDouble()
                    scanner.nextLine() // Consumir el salto de línea pendiente

                    val nuevaTienda = Tienda(nombre, ubicacion, cantidadEmpleados, isOpen, ingresosAnuales)
                    actualizarTienda(index, nuevaTienda)
                } else {
                    println("Índice de tienda inválido.")
                }
            }
            6 -> {
                println("Ingrese el índice del videojuego a actualizar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente
                val videojuegos = cargarVideojuegos()
                if (index >= 0 && index < videojuegos.size) {
                    println("Ingrese el nombre del videojuego:")
                    val nombre = scanner.nextLine()
                    println("Ingrese el género del videojuego:")
                    val genero = scanner.nextLine()
                    println("Ingrese la calificación del videojuego:")
                    val calificacion = scanner.nextInt()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("El videojuego es multijugador? (true/false):")
                    val isMultijugador = scanner.nextBoolean()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("Ingrese la fecha de lanzamiento del videojuego (dd/mm/aaaa):")
                    val fechaLanzamiento = SimpleDateFormat("dd/MM/yyyy").parse(scanner.next())
                    scanner.nextLine() // Consumir el salto de línea pendiente

                    val nuevoVideojuego = Videojuego(nombre, genero, calificacion, isMultijugador, fechaLanzamiento)
                    actualizarVideojuego(index, nuevoVideojuego)
                } else {
                    println("Índice de videojuego inválido.")
                }
            }
            7 -> {
                println("Ingrese el índice de la tienda a eliminar:")
                val index = scanner.nextInt()
                eliminarTienda(index)
            }
            8 -> {
                println("Ingrese el índice del videojuego a eliminar:")
                val index = scanner.nextInt()
                eliminarVideojuego(index)
            }
            9 -> break@loop
            else -> println("Opción inválida.")
        }
        println()
    }
}