package com.example.fitkit

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.android.synthetic.main.activity_foot.*
import java.io.*
import java.util.*

class FootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foot)



    }

    val getImgCam = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val bitmap = it?.data?.extras?.get("data") as Bitmap
        foot_iv.setImageBitmap(bitmap)
    }

    val getImgGal = registerForActivityResult(ActivityResultContracts.GetContent(),
        ActivityResultCallback {
            var inputStream = contentResolver.openInputStream(it)
            var bitmap = BitmapFactory.decodeStream(inputStream)
            foot_iv.setImageBitmap(bitmap)



            if(!Python.isStarted())
                Python.start(AndroidPlatform(this))
            var py = Python.getInstance()
            var pyObj  = py.getModule("measure") //python Script name

            val fileAbsPath = getImageAbsPath(bitmap)
            var obj = pyObj.callAttr("main",fileAbsPath) //(method name, argument input (img_path))
            foot_length_tv.text = obj.toString() + " cm" //return value
        })

    fun getImageAbsPath(bitmap:Bitmap): String {
        val wrapper = ContextWrapper(applicationContext)
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
            stream.flush()
            stream.close()
        }catch (e: IOException){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
//        Toast.makeText(this, file.absolutePath, Toast.LENGTH_LONG).show()

        return file.absolutePath
    }


    fun pickImageCamera(view: View){

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null)
            getImgCam.launch(intent)

        //TODO: add foot measurement func

    }

    fun pickImageGallery(view: View){
        getImgGal.launch("image/*")
    }

}