package com.example.contacts

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.tabs.TabLayout
import java.lang.Exception

class MainActivity : AppCompatActivity(),TabLayout.OnTabSelectedListener,AddFragment.clicklistener,MycontactsFragment.mycontacts,UpdateFragment.updatelistener {


    var totallist=ArrayList<newcontacts>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.frame,AddFragment()).commit()
        val tab_layout=findViewById<TabLayout>(R.id.tab)
        tab_layout.addOnTabSelectedListener(this)
      /*  val b: Button =findViewById<Button>(R.id.b1)
        b.setOnClickListener{
           val d=contactsdatabase(this)
            var l=ArrayList<newcontacts>()
            l=d.readcontacts()
            Toast.makeText(this,l[0].name+" "+l[0].phone,Toast.LENGTH_LONG).show()
       }*/

    }

    override fun onBackPressed() {
        val builder=AlertDialog.Builder(this)
        builder.setMessage("Do You Want To Close?")
        builder.setCancelable(false).setPositiveButton("YES",DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })
            builder.setNegativeButton("NO",DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })

        val alertDialog=builder.create()
        alertDialog.setTitle("Contacts")
        alertDialog.setIcon(R.drawable.ic_baseline_phone_missed_24)
        alertDialog.show()

    }


    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
            when(tab?.position){
            0 ->
                supportFragmentManager.beginTransaction().replace(R.id.frame,AddFragment()).commit()
            1 ->
                supportFragmentManager.beginTransaction().replace(R.id.frame,MycontactsFragment()).commit()
            2 ->
                supportFragmentManager.beginTransaction().replace(R.id.frame,UpdateFragment()).commit()
            }
    }

    override fun button_click_listener(str: String, str1: String) {
        if(str1.length!=10){ Toast.makeText(this,"Phone Number Length Is Invalid",Toast.LENGTH_LONG).show()}
        else{
            val d=contactsdatabase(this)
            d.addcontact(str,str1)
            Toast.makeText(this,"New Contact Added Successfully",Toast.LENGTH_LONG).show()}
    }

    override fun mycontactview(): ArrayList<newcontacts> {
       val d=contactsdatabase(this)
        totallist=d.readcontacts()
        return d.readcontacts()

    }


    override fun delete(str: String) {
        val d=contactsdatabase(this)
        d.deletecontact(str)
    }

    override fun dial(pos:Int) {
      // Toast.makeText(this,"Position:"+totallist[pos].phone.toString(),Toast.LENGTH_LONG).show()
        totallist.sortBy {
            it.name
        }

            val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+totallist[pos].phone.toString()))
        try {
            startActivity(i)

        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }




    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if(currentFocus!=null){
            val i=this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            i.hideSoftInputFromWindow(currentFocus?.windowToken,0)
            }
            return super.dispatchTouchEvent(ev)
        }

    override fun update_contacts(str: String, str1: String) {
        if(str1.length!=10){Toast.makeText(this,"Phone Number Length Is Invalid",Toast.LENGTH_LONG).show()}
        else{
        val d=contactsdatabase(this)
        d.updatecontact(str,str1)
        Toast.makeText(this,"Contact Updated Successfully",Toast.LENGTH_LONG).show()}

    }
}


