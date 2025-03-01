package jetpack.julian.ordenpapaapplication.model.order.OrderPreparing

    data class  OrderPreparingRespondeItem(
        val createdAt: String,
        val order_foods: List<OrderFood>,
        val order_id: Int,
        val table : Int,
        val order_status: String,
        val total_price: Int,
        val updatedAt: String,
        val user_id: Int
    )