package com.example.tripxpenses.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.tripxpenses.CardModel.BPersonModel;
import com.example.tripxpenses.CardModel.CardModel;
import com.example.tripxpenses.CardModel.TotalModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME="tripdatabase.db";           // Our database
    final static int DBVERSION=1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trips"+"(id integer primary key autoincrement,"+"tname text,"+"nop int)");     //Initially there is only one table named trips

    }

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME,null,DBVERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists trips");
        onCreate(db);

    }

    public boolean insertTrip(String tname,int nop,ArrayList<String> names)                     // when we add a new trip two more tables( tripname, tripnametotal)
    {                                                                                           // tripname contains person name and balance
        SQLiteDatabase database=getReadableDatabase();                                          // tripnametotal contains each expense by a person for a particular trip
        ContentValues values=new ContentValues();
        values.put("tname",tname);
        values.put("nop",nop);

        database.execSQL("create table "+tname+" (pnum integer primary key autoincrement,"+"pname text,"+"balance float)");

        database.execSQL("create table "+tname+"total "+"(sno integer primary key autoincrement,"+"expense float,"+"contributor text)");

        for(int i=1;i<=nop;i++)
        {
            ContentValues values1=new ContentValues();
            values1.put("pname",names.get(i-1));
            values1.put("balance",0);
            database.insert(tname,null,values1);
        }

        long id=database.insert("trips",null,values);

        if(id<=0)
            return false;
        else
            return true;
    }


    public ArrayList<CardModel> getTrips()
    {
        ArrayList<CardModel> trips=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select tname from trips",null);
        if(cursor.moveToFirst())
        {
            CardModel fmodel=new CardModel();
            fmodel.setTripname(cursor.getString(0));
            trips.add(fmodel);
            while(cursor.moveToNext())
            {
                CardModel model=new CardModel();
                model.setTripname(cursor.getString(0));
                trips.add(model);

            }

        }

        cursor.close();
        database.close();
        return trips;
    }


    public ArrayList<BPersonModel> getPersons(String tname)             // return person and balance for balance page
    {
        ArrayList<BPersonModel> persons=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select pname,balance from "+tname,null);
        if(cursor.moveToFirst())
        {
            BPersonModel bmodel=new BPersonModel();
            bmodel.setPnamebal(cursor.getString(0));
            bmodel.setPbalance(cursor.getString(1));
            persons.add(bmodel);

            while(cursor.moveToNext())
            {
                BPersonModel model=new BPersonModel();
                model.setPnamebal(cursor.getString(0));
                model.setPbalance(cursor.getString(1));
                persons.add(model);
            }
        }

        cursor.close();
        database.close();
        return persons;
    }


    public Cursor getnop(String tname)                      // return no. of persons in a particular trip
    {
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select nop from trips where tname='"+tname+"'",null);

        if(cursor!=null)
           cursor.moveToFirst();
        return cursor;

    }

    public void updatebalance(String tname,String contributor,double contribution,double other)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select balance from "+tname+" where pname='"+contributor+"'",null);

        double balc=0;
        if(cursor.moveToFirst())
        {
            balc = cursor.getFloat(0);
            balc = balc + contribution - other;
        }                                                                         // for contributor

        database.execSQL("update "+tname+" set balance="+balc+" where pname='"+contributor+"'");

        double balo;
        String person;

        Cursor cursor1=database.rawQuery("Select pname,balance from "+tname+" where NOT pname='"+contributor+"'",null);
        if(cursor1.moveToFirst())
        {
            person=cursor1.getString(0);
            balo=cursor1.getFloat(1);
            balo=balo-other;
            database.execSQL("update "+tname+" set balance="+balo+" where pname='"+person+"'");
            while(cursor1.moveToNext())
            {
                person=cursor1.getString(0);
                balo=cursor1.getFloat(1);
                balo=balo-other;
                database.execSQL("update "+tname+" set balance="+balo+" where pname='"+person+"'");
            }
        }
        cursor.close();

        ContentValues values=new ContentValues();
        values.put("expense",contribution);
        values.put("contributor",contributor);
        database.insert(tname+"total",null,values);

        cursor1.close();
        database.close();

    }

    public int deleteTrip(String tname)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("drop table if exists "+tname);
        database.execSQL("drop table if exists "+tname+"total");
        return database.delete("trips","tname='"+tname+"'",null);

    }


    public ArrayList<String> getpnames(String tname)
    {
        ArrayList<String> persons=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select pname from "+tname,null);

        if(cursor.moveToFirst())
        {
            String name=cursor.getString(0);
            persons.add(name);

            while(cursor.moveToNext())
            {
                String name1=cursor.getString(0);
                persons.add(name1);
            }
        }


        cursor.close();
        database.close();
        return persons;
    }


    public ArrayList<TotalModel> getTotal(String tname)
    {
        ArrayList<TotalModel> expenses=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        String trname=tname+"total";
        Cursor cursor=database.rawQuery("Select sno,expense,contributor from "+trname,null);
        double exp;
        if(cursor.moveToFirst())
        {
            TotalModel model1=new TotalModel();
            model1.setSno(cursor.getString(0));
//            exp=cursor.getFloat(1);
            model1.setTotalexp(cursor.getString(1));
            model1.setTotalpname(cursor.getString(2));
            expenses.add(model1);

            while(cursor.moveToNext())
            {
                TotalModel model=new TotalModel();
                model.setSno(cursor.getString(0));
//                exp=cursor.getFloat(1);
                model.setTotalexp(cursor.getString(1));
                model.setTotalpname(cursor.getString(2));
                expenses.add(model);
            }
        }

        cursor.close();
        database.close();
        return expenses;

    }


    public ArrayList<String> gettnames()
    {
        ArrayList<String> tnames=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select tname from trips",null);

        if(cursor.moveToFirst())
        {
            String name=cursor.getString(0);
            tnames.add(name);

            while(cursor.moveToNext())
            {
                String name1=cursor.getString(0);
                tnames.add(name1);
            }
        }


        cursor.close();
        database.close();
        return tnames;

    }



}
