package com.example.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.intent.MoveForResult.Companion.RESULT_CODE

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        val btnMoveActivityWithData: Button = findViewById(R.id.btn_move_activity_with_data)
        val btnMoveActivityWithObject: Button = findViewById(R.id.btn_move_activity_with_object)
        val btnDialPhone: Button = findViewById((R.id.btn_dial_number))
        val btnMoveWithResult: Button = findViewById(R.id.btn_move_activity_with_result)


        btnMoveActivity.setOnClickListener(this)
        btnMoveActivityWithData.setOnClickListener(this)
        btnMoveActivityWithObject.setOnClickListener(this)
        btnDialPhone.setOnClickListener(this)
        btnMoveWithResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)

    }
override fun onClick(v: View){
    when (v.id) {
        R.id.btn_move_activity -> {
            val moveIntent = Intent (this@MainActivity,MoveActivity::class.java)
            startActivity(moveIntent)

        }
        R.id.btn_move_activity_with_data -> {
            val i = Intent(this@MainActivity, MoveWithDataActivity::class.java)
            i.putExtra(MoveWithDataActivity.EXTRA_NAME, "Alya")
            i.putExtra(MoveWithDataActivity.EXTRA_AGE, 17)
            startActivity(i)
        }
        R.id.btn_move_activity_with_object -> {
           val person = Person("alya","alyamaushily77@gmail.com","malang")
            val moveWithObject = Intent(this@MainActivity,MoveWithObject::class.java)
            moveWithObject.putExtra(MoveWithObject.EXTRA_PERSON,person)
            startActivity((moveWithObject))
        }
        R.id.btn_dial_number -> {
            val phoneNumber = "081332388427"
            val  dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialPhoneIntent)
        }
        R.id.btn_move_activity_with_result ->{
            val moveForResultIntent =
                Intent(this@MainActivity,MoveForResult::class.java)
            getResult.launch(moveForResultIntent)

        }
        }
    }
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_CODE){
                val value = it.data?.getIntExtra(
                    MoveForResult.EXTRA_SELECTED_VALUE,0)
                tvResult.setText("Hasil $value")
            }
        }
}
