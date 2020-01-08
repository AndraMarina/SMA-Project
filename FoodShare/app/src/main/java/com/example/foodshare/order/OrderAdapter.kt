package com.example.foodshare.order

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.foodshare.R


class OrderAdapter(private val mContext : Context,
                   private  val  layoutResID: Int,
                   private val orderList: List<Order>,
                   private val onClick: (Order) -> (Unit)
) : ArrayAdapter<Order>(mContext, layoutResID, orderList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mContext)
        val view : View = layoutInflater.inflate(layoutResID, null)
        val order = orderList[position]

        //from view we get text view
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewMealType = view.findViewById<TextView>(R.id.textViewMealType)
        val textViewPortionNo = view.findViewById<TextView>(R.id.textViewPortionNo)

        textViewName.text = order.name
        textViewMealType.text = "Meal Type:" + order.mealType
        textViewPortionNo.text = "Portion No:" + order.portionNo

        if(layoutResID == R.layout.orders) {
            val textViewAddress = view.findViewById<TextView>(R.id.editTextAdress)
            val textViewPhoneNo = view.findViewById<TextView>(R.id.textViewPhoneNo)
            val buttonContact = view.findViewById<Button>(R.id.buttonContact)

            textViewAddress.text = order.address
            textViewPhoneNo.text = order.phone

            buttonContact.setOnClickListener {
                onClick(order)

            }
        } else {
            val buttonDelete = view.findViewById<Button>(R.id.buttonDelete)
            buttonDelete.setOnClickListener {
                onClick(order)

            }

        }


        return view
    }



}