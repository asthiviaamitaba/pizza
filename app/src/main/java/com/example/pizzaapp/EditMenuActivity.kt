package com.example.pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import model.MenuModel

class EditMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_menu)

        fun addMenu(menu: MenuModel){
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COLUMN_ID_MENU, menu.id)
            values.put(COLUMN_NAMA_MENU, menu.name)
            values.put(COLUMN_PRICE_MENU, menu.price)
            //prepare image
            val byteOutputStream = ByteArrayOutputStream()
            val imageInByte:ByteArray
            menu.image.compress(Bitmap.CompressFormat.JPEG, 100,byteOutputStream)
            imageInByte = byteOutputStream.toByteArray()
            values.put(COLUMN_IMAGE, imageInByte)

            val result = db.insert(TABLE_MENU, null, values)
            //show message
            if (result==(0).toLong()){
                Toast.makeText(context, "Add menu Failed", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Add menu Success",Toast.LENGTH_SHORT).show()
            }
            db.close()
        }
    }
}