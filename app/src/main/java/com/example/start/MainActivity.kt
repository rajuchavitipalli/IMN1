package com.example.start

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var todos  = ArrayList<Todo>();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //for(i in 1..9)
         todos.add(Todo("Thank U i","Text"))

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = TodosAdapter(todos)
        recyclerView.adapter = adapter
        button.setOnClickListener{
            val intent = Intent(this, add::class.java)
            intent.putExtra("index","-1")
            startActivity(intent)
        }
    }
}
