package com.example.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION

class contactadapter(var list:ArrayList<newcontacts>,val context: viewclicklistener):RecyclerView.Adapter<contactadapter.ViewHolder>(){
    inner class ViewHolder(itemtype: View):RecyclerView.ViewHolder(itemtype),View.OnClickListener{
        val n=itemtype.findViewById<TextView>(R.id.name)
        val p=itemtype.findViewById<TextView>(R.id.phone)
        init{
            itemtype.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val i=adapterPosition
            context.onclick(i)
        }
    }

    interface viewclicklistener{
        fun onclick(pos:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val l=LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
        return ViewHolder(l)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val u=list[position]
        holder.n.text=u.name
        holder.p.text=u.phone
    }
}