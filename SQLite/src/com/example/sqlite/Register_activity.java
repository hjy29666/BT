package com.example.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_activity extends Activity {

	private EditText etid, etname;
	private Button btn_qu, btn_sure;

	SQLiteOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		helper = new Sqliteopenhelper(this);
		helper.getWritableDatabase();
		etid = (EditText) findViewById(R.id.etid);   //ÕÊºÅ±à¼­¿ò ºÍ¶ÔÏóµÄ°ó¶¨
		etname = (EditText) findViewById(R.id.etname);
		btn_qu = (Button) findViewById(R.id.btn_qu);
		btn_sure = (Button) findViewById(R.id.btn_sure);
		btn_sure.setOnClickListener(new sureListener());
		btn_qu.setOnClickListener(new quListener());

	}

	class sureListener implements OnClickListener {

		public void onClick(View v) {
			try {
				SQLiteDatabase sdb = helper.getWritableDatabase();
				ContentValues values = new ContentValues();

				values.put("id", etid.getText().toString());
				values.put("name", etname.getText().toString());
				
				if (values.equals("") || values.equals("")) {
					Toast.makeText(getApplicationContext(), "ÇëÊäÈë£¡", Toast.LENGTH_SHORT).show();
				}else{
				sdb.insert("student", null, values);
				Toast.makeText(getApplicationContext(), "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();}
				
				Intent intent = new Intent(Register_activity.this, MainActivity.class);
				Bundle bundle = new Bundle();
				 bundle.putString("name", etname.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);

			} catch (SQLiteException e) {
				Toast.makeText(getApplicationContext(), "×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	class quListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(Register_activity.this, MainActivity.class);
			startActivity(intent);
		}

	}

}