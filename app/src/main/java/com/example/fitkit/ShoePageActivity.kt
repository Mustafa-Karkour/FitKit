package com.example.fitkit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_shoe_page.*

class ShoePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoe_page)

        Glide.with(this).load(intent.getStringExtra("shoe img")).placeholder(R.drawable.placeholder).into(shoe_img)
        shoe_name_tv.text = intent.getStringExtra("shoe name")
        shoe_price_tv.text = intent.getStringExtra("shoe price")
        shoe_desc_tv.text = "•Colors: "+intent.getStringExtra("shoe colors")+"\n•"+intent.getStringExtra("shoe desc")

    }

    fun toTryItOn(v: View){
        val kivi_link = intent.getStringExtra("kivi link")
        val intent = Intent(this,ShoeWebActivity::class.java)
        intent.putExtra("shoe try on link",kivi_link)
//        Log.d("kivi",kivi_link.toString())
        startActivity(intent)
    }

}