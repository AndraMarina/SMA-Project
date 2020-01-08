package com.example.foodshare.order

data class Order(val id : String,val name : String, val address : String, val phone : String, val mealType :String, val portionNo: String, val email: String) {

    constructor() : this("","","", "","","","")

}