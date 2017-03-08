package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
public class Sqliteopenhelper extends SQLiteOpenHelper {
    private static final String DBNAME="test.db";  //���ݿ�����
    private static final String TABLENAME="student";
//    private static final String GOODSNAME="goods";
    private static final int TESTVERSION=1;
    private Context context;            
    public Sqliteopenhelper(Context context) {
        super(context, DBNAME, null, TESTVERSION);
    }

                  //��ʼ����������
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
   String sql1="create table"+" "
        +TABLENAME+"(id varchar,name varchar)";
//String sql2="create table"+" "
//        +GOODSNAME+"(LF varchar,name varchar,miaoshu varchar)";
          db.execSQL(sql1);
//          db.execSQL(sql2);
          Toast.makeText(context,"�ɹ�������",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
           if(newVersion>oldVersion)
           {
               String sql1="drop table if exists"+TABLENAME;
//               String sql2="drop table if exists"+GOODSNAME;
               db.execSQL(sql1);
//               db.execSQL(sql2);
               this.onCreate(db);
           }
    }

}