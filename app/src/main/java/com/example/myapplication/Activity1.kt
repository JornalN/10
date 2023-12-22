package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class Activity1 : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var editTxt: EditText
    var colors = arrayOf(0xFFFDFBFC , 0xFF6A9A3A, 0xFF9A7A3A)
    var sheetNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)

        btn = findViewById(R.id.button)
        editTxt = findViewById(R.id.editText)

        sheetNumber = getIntent().getIntExtra("sheetNumber" , 0)
        btn.setOnClickListener(){
            if (sheetNumber < colors.size - 1){
                val intent = Intent (this,this::class.java )
                intent.putExtra("sheetNumber" , sheetNumber + 1 )
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, getString(R.string.text), Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onPause() {
        super.onPause()
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        prefs.putString("note" + sheetNumber ,editTxt.editableText.toString())
        prefs.apply()
    }

    override fun onResume() {
        super.onResume()
        val sheet : ConstraintLayout = findViewById(R.id.sheet)
        sheet.setBackgroundColor(colors[sheetNumber].toInt())
        val saveState = getPreferences(Context.MODE_PRIVATE).getString("note" + sheetNumber.toString(), null)
        if (saveState != null ) {
            editTxt.setText(saveState)
        }

    }
}