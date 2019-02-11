package com.example.start

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import kotlinx.android.synthetic.main.list_layout.view.*

class TodosAdapter(private val todosList: ArrayList<Todo>) : RecyclerView.Adapter<TodosAdapter.ViewHolder>(){

    lateinit var recyclerView : RecyclerView

    override fun onBindViewHolder(eachItem: TodosAdapter.ViewHolder, itemNumber: Int) =  eachItem.bind(itemNumber)
    override fun onCreateViewHolder(eachItem: ViewGroup, itemNumber: Int): TodosAdapter.ViewHolder {
        val view = LayoutInflater.from(eachItem?.context).inflate(R.layout.list_layout, eachItem, false)
        val popup = PopupMenu(eachItem.context,view.btn_show_popup)
        popup.inflate(R.menu.edit_delete_popup)
        return  ViewHolder(view,popup)
    }
    override fun getItemCount(): Int = todosList.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView

    }

    inner class ViewHolder( itemView: View,var  popup : PopupMenu): RecyclerView.ViewHolder(itemView){
        val textViewTitle = itemView.textViewTitle as TextView
        val textViewContent = itemView.textViewContent as TextView
        val context = itemView.getContext()
        val btnShowPopup = itemView.btn_show_popup as Button

        fun bind(itemNumber: Int){
            var todo:Todo = todosList[itemNumber]
            textViewTitle.text = todo.title
            textViewContent.text = todo.content
            btnShowPopup.setOnClickListener {
                popup.show()
                popup.setOnMenuItemClickListener {
                    if(it.title.equals("Edit")){
                        val intent = Intent(recyclerView.context,add::class.java)
                        intent.putExtra("index",itemNumber.toString(10))
                        recyclerView.context.startActivity(intent)
                    }else{
                        MainActivity.todos.remove(todo)
                        val intent = Intent(context,MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        recyclerView.context.startActivity(intent)
                    }

                    true
                }
            }
        }
    }
}

