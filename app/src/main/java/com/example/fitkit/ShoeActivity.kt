package com.example.fitkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_shoe.*

class ShoeActivity : AppCompatActivity(), ShoeAdapter.onShoeItemClickListener {
    val list = ArrayList<ShoeModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoe)
//        supportActionBar?.hide()

//        kivi_WebView.webViewClient = WebViewClient()
        kivi_WebView.settings.javaScriptEnabled = true


        //Hard-coded values
        val item = ShoeModel(R.drawable.nike,
            "https://tryon.kivisense.com/viewer?user=908d701015d3&sku=5&action=view&lang=en-US&token=") //selected by default
        val item2 = ShoeModel(R.drawable.nike,
            "https://tryon.kivisense.com/viewer?user=908d701015d3&sku=1&action=view&lang=en-US&token=")

        list.add(item)
        list.add(item2)
        list.add(item)
        list.add(item2)
        list.add(item)
        list.add(item2)
        list.add(item)
        list.add(item2)

//        kivi_WebView.loadUrl(list.get(0).kiviLink) //selected by default


        shoes_RV.adapter = ShoeAdapter(list,this)
//        shoes_RV.layoutManager = GridLayoutManager(this,2)
        shoes_RV.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)

        shoes_RV.setHasFixedSize(true) //for optimization purposes

    }

    override fun onShoeClick(shoePosition: Int) {
        kivi_WebView.loadUrl(list.get(shoePosition).kiviLink)
//        val clickedItem = list.get(shoePosition)
//        if(isSelected){
//            isSelected = !isSelected
//
//            clickedItem.BD = BD_BLACK
//        }else{
//            clickedItem.BD = BD_TRANS
//        }
//
//        shoes_RV.adapter?.notifyItemChanged(shoePosition)

    }
}