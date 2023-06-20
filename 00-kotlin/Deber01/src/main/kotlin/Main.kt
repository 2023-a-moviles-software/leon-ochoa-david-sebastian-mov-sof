import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val tiendaCRUD = TiendaCRUD()
    val videojuegoCRUD = VideojuegoCRUD()

    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

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
                tiendaCRUD.crearTienda(tienda)
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
                val fechaLanzamiento = formatoFecha.parse(scanner.next())
                scanner.nextLine() // Consumir el salto de línea pendiente

                val videojuego = Videojuego(nombre, genero, calificacion, isMultijugador, fechaLanzamiento)
                videojuegoCRUD.crearVideojuego(videojuego)
            }
            3 -> {
                println("=== Tiendas ===")
                tiendaCRUD.listarTiendas()
                println("================")
            }
            4 -> {
                println("=== Videojuegos ===")
                videojuegoCRUD.listarVideojuegos()
                println("===================")
            }
            5 -> {
                println("Ingrese el índice de la tienda a actualizar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val tiendas = tiendaCRUD.cargarTiendas()
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

                    val tienda = Tienda(nombre, ubicacion, cantidadEmpleados, isOpen, ingresosAnuales)
                    tiendaCRUD.actualizarTienda(index, tienda)
                } else {
                    println("El índice de tienda ingresado es inválido.")
                }
            }
            6 -> {
                println("Ingrese el índice del videojuego a actualizar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val videojuegos = videojuegoCRUD.cargarVideojuegos()
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
                    val fechaLanzamiento = formatoFecha.parse(scanner.next())
                    scanner.nextLine() // Consumir el salto de línea pendiente

                    val videojuego = Videojuego(nombre, genero, calificacion, isMultijugador, fechaLanzamiento)
                    videojuegoCRUD.actualizarVideojuego(index, videojuego)
                } else {
                    println("El índice de videojuego ingresado es inválido.")
                }
            }
            7 -> {
                println("Ingrese el índice de la tienda a eliminar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val tiendas = tiendaCRUD.cargarTiendas()
                if (index >= 0 && index < tiendas.size) {
                    tiendaCRUD.eliminarTienda(index)
                } else {
                    println("El índice de tienda ingresado es inválido.")
                }
            }
            8 -> {
                println("Ingrese el índice del videojuego a eliminar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val videojuegos = videojuegoCRUD.cargarVideojuegos()
                if (index >= 0 && index < videojuegos.size) {
                    videojuegoCRUD.eliminarVideojuego(index)
                    println("Videojuego eliminado exitosamente.")
                } else {
                    println("El índice de videojuego ingresado es inválido.")
                }
            }
            9 -> break@loop
            else -> println("Opción inválida.")
        }
    }

    println("¡Hasta luego!")
}
