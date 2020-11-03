package com.example.contacts

import android.content.Context
import android.net.MailTo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.cardview.*
import java.lang.ClassCastException


class AddFragment : Fragment() {

    private lateinit var listener:clicklistener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    interface clicklistener{
        fun button_click_listener(str:String,str1:String)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val b=view.findViewById<Button>(R.id.add)
        val n=view.findViewById<EditText>(R.id.enteredname)
        val p=view.findViewById<EditText>(R.id.enteredphone)
        b.setOnClickListener{
            val str=n.text.toString()
            val str1=p.text.toString()
            n.text=null
            p.text=null
            listener.button_click_listener(str,str1)


        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is clicklistener){
            listener=context
        }
        else{
            throw ClassCastException(context.toString()+"must implement listener")
        }
    }


}