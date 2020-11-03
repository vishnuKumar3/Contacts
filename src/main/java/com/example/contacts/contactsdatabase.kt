package com.example.contacts

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class contactsdatabase(context:Context):SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {
    companion object{
        private val DATABASE_NAME="contactdb"
        private val DB_table_name="dbtable"
        private val DATABASE_VERSION=1
        private val Key_name="Name"
        private val Key_phone_num="Phone"

    }

    override fun onCreate(p0: SQLiteDatabase) {
        val create=("CREATE TABLE "+DB_table_name+"("+Key_name+" STRING PRIMARY KEY,"+Key_phone_num+" STRING"+")")
        p0.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS "+DB_table_name)
        onCreate(p0)
    }

     fun addcontact(name:String,ph:String){
        val d=this.writableDatabase
        val c= contentValuesOf()
        c.put(Key_name,name)
        c.put(Key_phone_num,ph)
        val a=d.insert(DB_table_name,null,c)
        d.close()
    }
     fun readcontacts():ArrayList<newcontacts>{
        val d=this.readableDatabase
         val new=ArrayList<newcontacts>()
         val q="SELECT * FROM $DB_table_name"
         val c=d.rawQuery(q,null)
         c.moveToFirst()
         do{
            val n=c.getString(c.getColumnIndex("Name"))
             val p=c.getString(c.getColumnIndex("Phone"))
             new.add(newcontacts(n,p))
         }while(c.moveToNext())
        /* c.moveToLast()
         val n=c.getString(c.getColumnIndex("Name"))
         val p=c.getString(c.getColumnIndex("Phone"))
         new.add(newcontacts(n,p))*/
         return new
    }

    fun deletecontact(name:String){
        val d=this.writableDatabase
       val a= d.delete(DB_table_name, "Name=?", arrayOf(name))
        d.close()
    }

    fun updatecontact(name:String,ph: String){
        val d=this.writableDatabase
        val c= contentValuesOf()
        c.put(Key_name,name)
        c.put(Key_phone_num,ph)
       val a= d.update(DB_table_name,c, "Name=?", arrayOf(name))
        d.close()
    }
}