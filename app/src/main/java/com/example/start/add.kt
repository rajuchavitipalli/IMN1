package com.example.start

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class add : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        var defaultValuesIndex: Int =  intent.getStringExtra("index").toInt(10);
        var actions = arrayOf("Select Type","Text","Image","Audio","Video");
        var actionType: String = ""
        var titleField = findViewById(R.id.title) as EditText
        sp1.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,actions)
        if(defaultValuesIndex!=-1){
            titleField.setText(MainActivity.todos[defaultValuesIndex].title)
            sp1.setSelection(actions.indexOf(MainActivity.todos[defaultValuesIndex].content));
        }
        sp1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                actionType = "notselected"
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                actionType = actions[position];
            }
        }
        submit.setOnClickListener({
            if(titleField.text.toString().trim()=="" ){
                Toast.makeText(this,"Enter The Title",Toast.LENGTH_LONG).show()
            } else if(actionType.equals("Select Type")){
                Toast.makeText(this,"Select The Type",Toast.LENGTH_LONG).show()
            }
            else{
                if(defaultValuesIndex!=-1) MainActivity.todos[defaultValuesIndex] = Todo(titleField.text.toString(),actionType)
                else MainActivity.todos.add(Todo(titleField.text.toString(),actionType))
                val intent = Intent(this,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        })
    }
}
