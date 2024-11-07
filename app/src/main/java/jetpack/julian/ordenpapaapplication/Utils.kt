package jetpack.julian.ordenpapaapplication

import jetpack.julian.ordenpapaapplication.model.food.Food

object Utils {
    var useFood: List<Food> = emptyList()
    lateinit var socketManager: SocketManager
    val menu: List<Food> = listOf(
        /*Food(
            duration = 20,
            title = "CORDON BLUE",
            description = "Un plato típico de Colombia con frijoles, arroz, carne, y plátano.",
            price = 20000,
        ),*/
        Food(
            duration = 15,
            title = "Mexicana",
            description = "Papa, Carne Desmechada, Salchicha, Pico de Gallo, Nachos, Frijol, Queso Mozzarella, Huevos Codorniz, Guacamole y Salsas",
            price = 20000,
        ),
        Food(
            duration = 10,
            title = "Clasico",
            description = "Papa, Salchicha, Maicitos, Huevos de codorniz, Queso Mozzarella y Salsas.",
            price = 13000,
        ),
        Food(
            duration = 41,
            title = "Paisa",
            description = "Papa, Salchicha, Chicharrón, Chorizo, Tocineta, Huevos de codorniz, Madurito, Guacamole, Frijol, Queso Mozzarella y Salsas.",
            price = 23000,
        ),
        Food(
            duration = 20,
            title = "Vecetariano",
            description = "Papa, Madurito, Pico de gallo, Frijol Nachos, Guacamole, Proteína vegetal, Queso Mozzarella, Maicitos y Salsas.",
            price = 15000,
        ),
        Food(
            duration = 15,
            title = "De la casa",
            description = "Papa, Salchicha, Costilla, Queso mozzarella, Guacamole, Pollo Crunch, Tocineta, Maicitos, Huevos de codorniz y Salsas.",
            price = 24000,
        ),
        Food(
            duration = 10,
            title = "Exclusiva",
            description = "Papa, Pollo BBQ, Tocineta, Salchicha, Queso mozzarella, Pollo crunch, Tocineta, Maicitos, Huevos de codorniz y Salsas.",
            price = 20000,
        ),
        Food(
            duration = 10,
            title = "Menú Infantil",
            description = "Papa, Pollo crunch, Salchicha, Queso y Salsas.",
            price = 14000,
        ),
        Food(
            duration = 30,
            title = "Charol para 6 ",
            description = "Papa, Carne desemechada, Chorizo, Tocineta, Pollo crunch, Madurito, Maicitos, Salchicha, Nachos, Queso mozzarella, Huevos de codorniz, Pico de gallor, Guacamole, Salsas.",
            price = 72000,
        ),
    )
}