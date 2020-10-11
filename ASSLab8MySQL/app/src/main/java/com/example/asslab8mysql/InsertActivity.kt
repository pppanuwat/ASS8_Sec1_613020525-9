package com.example.asslab8mysql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        onClickAddEmp()
    }
    fun onClickAddEmp(){

            btnAdd.setOnClickListener(){
                val radioGroupId: Int = radioGroup.checkedRadioButtonId
                val selected: RadioButton? = findViewById(radioGroupId)
                val server: EmployeeAPI = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(EmployeeAPI::class.java)

                server.insertEmp(
                    edt_name.text.toString(),
                    selected?.text.toString(),
                    edt_mail.text.toString(),
                    edt_salary.text.toString().toInt()).enqueue(object :
                    Callback<Employee> {
                    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                        if(response.isSuccessful()){
                            Toast.makeText(applicationContext, "Successfully Inserted", Toast.LENGTH_LONG).show()
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()

                        }
                    }
                    override fun onFailure(call: Call<Employee>, t: Throwable) {
                        Toast.makeText(applicationContext, "Error onFailure" + t.message, Toast.LENGTH_LONG).show()
                    }
                })

            }

            btnCancel.setOnClickListener(){
                finish()
            }
        }

}