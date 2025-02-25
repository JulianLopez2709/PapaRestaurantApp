package jetpack.julian.ordenpapaapplication.core

import jetpack.julian.ordenpapaapplication.core.socket.SocketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.text.NumberFormat
import java.util.Locale

object Utils {
    var useFood: List<Food> = emptyList()
    val BASE_URL = "http://192.168.122.142:3000"
    lateinit var socketManager: SocketManager

    fun formatPrice (price:Double): String {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(price)
    }

    val menu: List<Food> = listOf(
//        Food(
//            duration = 15,
//            title = "Mexicana",
//            description = "Papa, Carne Desmechada, Salchicha, Pico de Gallo, Nachos, Frijol, Queso Mozzarella, Huevos Codorniz, Guacamole y Salsas",
//            price = 20000,
//            priceForTwo = 38000
//        ),
//        Food(
//            duration = 10,
//            title = "Clasico",
//            description = "Papa, Salchicha, Maicitos, Huevos de codorniz, Queso Mozzarella y Salsas.",
//            price = 13000,
//            priceForTwo = 25000
//        ),
//        Food(
//            duration = 41,
//            title = "Paisa",
//            description = "Papa, Salchicha, Chicharrón, Chorizo, Tocineta, Huevos de codorniz, Madurito, Guacamole, Frijol, Queso Mozzarella y Salsas.",
//            price = 23000,
//            priceForTwo = 45000
//
//        ),
//        Food(
//            duration = 20,
//            title = "Vecetariano",
//            description = "Papa, Madurito, Pico de gallo, Frijol Nachos, Guacamole, Proteína vegetal, Queso Mozzarella, Maicitos y Salsas.",
//            price = 15000,
//            priceForTwo = 26000
//        ),
//        Food(
//            duration = 15,
//            title = "De la casa",
//            description = "Papa, Salchicha, Costilla, Queso mozzarella, Guacamole, Pollo Crunch, Tocineta, Maicitos, Huevos de codorniz y Salsas.",
//            price = 24000,
//            priceForTwo = 43000
//        ),
//        Food(
//            duration = 10,
//            title = "Exclusiva",
//            description = "Papa, Pollo BBQ, Tocineta, Salchicha, Queso mozzarella, Pollo crunch, Tocineta, Maicitos, Huevos de codorniz y Salsas.",
//            price = 20000,
//            priceForTwo = 39000
//        ),
//        Food(
//            duration = 10,
//            title = "Menú Infantil",
//            description = "Papa, Pollo crunch, Salchicha, Queso y Salsas.",
//            price = 14000,
//        ),
//        Food(
//            duration = 30,
//            title = "Charol para 6 ",
//            description = "Papa, Carne desemechada, Chorizo, Tocineta, Pollo crunch, Madurito, Maicitos, Salchicha, Nachos, Queso mozzarella, Huevos de codorniz, Pico de gallor, Guacamole, Salsas.",
//            price = 72000,
//        ),
//        Food(
//            duration = 10,
//            title = "CUATRO PATACONES",
//            description = "CON TOCINETA/CHICHARRON O CARNE DESMECHADA",
//            price = 10000,
//        ),
//        Food(
//            duration = 10,
//            title = " CUATRO AREPITAS",
//            description = "CON  TOCINETA/CHICHARRON O CARNE DESMECHADA.",
//            price = 10000,
//        ),
//        Food(
//            duration = 10,
//            title = "NACHOS EN SOUR CREAM",
//            description = "MEZCLA CON TOCINETA + ESPECIAS",
//            price = 10000,
//        ),
//        Food(
//            duration = 10,
//            title = "YUQUITAS FRITAS",
//            description = "CON LA PROHIBIDA",
//            price = 10000,
//        ),
//        Food(
//            duration = 18,
//            title = "CEVICHE DE CHICHARRON",
//            description = "con patacón",
//            price = 16000,
//        ),
//        Food(
//            duration = 18,
//            title = "CEVICHE DE CAMARON",
//            description = "con patacón",
//            price = 16000,
//        ),
//        Food(
//            duration = 30,
//            title = " DESGRANADOS DE LIMÓN",
//            description = "Maicitos Desgranados - Pollo Desmechado - Tocineta Mayonesa - Queso Gratinado Sour Cream - Papa a la francesa Nachos y Limón",
//            price = 18000,
//        ),
//        Food(
//            duration = 30,
//            title = " DESGRANADOS DE MIEL",
//            description = "Maicitos Desgranados - Pollo Desmechado - Tocineta Mayonesa - Queso Gratinado Sour Cream  - Papa a la francesa Nachos y Miel",
//            price = 18000,
//        ),
//        Food(
//            duration = 30,
//            title = "DESGRANADOS DE MIEL Y LIMON",
//            description = "Maicitos Desgranados - Pollo Desmechado - Tocineta Mayonesa - Queso Gratinado Sour Cream - Papa a la francesa Nachos - Limón y Miel",
//            price = 18000,
//        ),
//        Food(
//            duration = 30,
//            title = "DESGRANADOS DE TAJÍN",
//            description = "Maicitos Desgranados - Pollo Desmechado - Tocineta Mayonesa - Queso Gratinado Sour Cream Limón - Papa a la francesa- Nachos y Tajin",
//            price = 18000,
//        ),
//        Food(
//            duration = 30,
//            title = "HAMBURGUESA",
//            description = "CARNE ARTESANAL, MAICITOS, LECHUGA, TOMATE, CEBOLLA CARAMELIZADA, TOCINETA, SALSAS, PAPA A LA FRANCESA Y GASEOSA.",
//            price = 20000,
//        ),
//        Food(
//            duration = 30,
//            title = "CORDON BLUE",
//            description = "LONCHA DE CERDO CON TOCINETA QUESO, PIÑA PAPITAS A LA FRANCESA Y ENSALADA PRIMAVERA",
//            price = 20000,
//        ),
//        Food(
//            duration = 30,
//            title = "SOLOMITO EN SALSA DE CHAMPIÑONES",
//            description = "CON PAPA A LA FRANCESA Y ENSALADA PRIMAVERA",
//            price = 22000,
//        ),
//        Food(
//            duration = 30,
//            title = "CREPES CON POLLO DESMECHADO",
//            description = "TOCINETA, MAICITOS, LECHUGA,TOMATE Y PAPITAS A LA FRANCESA",
//            price = 18000,
//        ),
//
//        Food(
//            duration = 30,
//            title = "CARNE DE RES",
//            description = "200 GR DE CARNE, PAPA A LA FRANCESA, AREPA Y ENSALADA",
//            price = 20000,
//        ),
//        Food(
//            duration = 30,
//            title = "CHULETÓN DE CERDO",
//            description =  "CON PAPITAS A LA FRANCESA Y ENSALADA PRIMAVERA",
//            price = 22000,
//        ),
//        Food(
//            duration = 30,
//            title = "CARNE DE CERDO",
//            description = " 200 GR DE CARNE, PAPA A LA FRANCESA, AREPA Y ENSALADA",
//            price = 19000,
//        ),
//        Food(
//            duration = 30,
//            title = "ARROZ DE LA ABUELA",
//            description = "200 GR DE CARNE, PAPA A LA FRANCESA, AREPA Y ENSALADA",
//            price = 20000,
//        ),
//        Food(
//            duration = 30,
//            title = "FILETE DE POLLO",
//            description = " 200 GR DE CARNE, PAPA A LA FRANCESA, AREPA Y ENSALADA",
//            price = 20000,
//        ),
//        Food(
//            duration = 30,
//            title = "ARROZ MIXTO",
//            description = "CON PAPAS A LA FRANCESA",
//            price = 22000,
//        ),
//        Food(
//            duration = 30,
//            title = " ENSALADA MIXTA",
//            description = " DE POLLO CON TOCINETA, PURÉ DE AGUACATE, TOMATE, LECHUGA, MAICITOS, HUEVO",
//            price = 18000,
//        ),
//        Food(
//            duration = 30,
//            title = " CHICHARRON ",
//            description = "ON AREPA, LIMON , TOMATE Y PAPA A LA FRANCESA",
//            price = 18000,
//        ),
//        Food(
//            duration = 30,
//            title = "PATACON",
//            description = "CON CARNE O POLLO DESMECHADO, TOCINETA, MAICITOS, PAPA FRANCESA Y QUESO GRATINADO",
//            price = 18000,
//        ),
//
       )
}