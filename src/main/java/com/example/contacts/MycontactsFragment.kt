package com.example.contacts

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.ClassCastException


class MycontactsFragment : Fragment(),contactadapter.viewclicklistener{
    private lateinit var listener:mycontacts
    interface mycontacts{
        fun mycontactview():ArrayList<newcontacts>
        fun dial(pos:Int)
        fun delete(str:String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mycontacts, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val m=MainActivity()
        var l=ArrayList<newcontacts>()
        l=listener.mycontactview()
        l.sortBy {
            it.name
        }
        val adapter=contactadapter(l,this)
        val r=view.findViewById<RecyclerView>(R.id.recyclerview)
        r.layoutManager=LinearLayoutManager(m,RecyclerView.VERTICAL,false)
        r.adapter=adapter
        val del_b=view.findViewById<Button>(R.id.b1)
        del_b.setOnClickListener {
            val t=view.findViewById<EditText>(R.id.enter1)
            val del=t.text.toString()
            t.text=null
            listener.delete(del)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is mycontacts){
            listener =context
        }
        else{
            throw ClassCastException(context.toString()+"must implement listener")
        }
    }

    override fun onclick(pos: Int) {
      listener.dial(pos)
    }
}