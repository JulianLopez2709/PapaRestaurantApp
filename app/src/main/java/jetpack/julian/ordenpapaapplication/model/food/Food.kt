package jetpack.julian.ordenpapaapplication.model.food

data class Food(
    val id: Int = 0,
    val title:String,
    val description:String,
    val price:Int,
    val img:String? = null,
    val isprocess : Boolean = true
)
