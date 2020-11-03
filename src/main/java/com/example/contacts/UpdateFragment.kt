package com.example.contacts

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import java.lang.ClassCastException

class UpdateFragment : Fragment() {
    private lateinit var listener:updatelistener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val up_button=view.findViewById<Button>(R.id.update_button)
        up_button.setOnClickListener {
            val e1=view.findViewById<EditText>(R.id.edit1)
            val e2=view.findViewById<EditText>(R.id.edit2)
            val n=e1.text.toString()
            val p=e2.text.toString()
            listener.update_contacts(n,p)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is updatelistener){
            listener=context
        }
        else{
            throw ClassCastException(context.toString()+"interface not implemented")
        }
    }


    interface updatelistener{
        fun update_contacts(str:String,str1:String)
    }


}