package com.example.sqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Cursor cursor;
	private EditText et_id, et_name;
	private Button btn_test, btn_local;
	private Button clear;
	// 1,SQLite的声明
	SQLiteOpenHelper helper;
	private String _id;
	private String _name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 2，数据库的创建，及调用
		helper = new Sqliteopenhelper(this);
		helper.getWritableDatabase();

		et_id = (EditText) findViewById(R.id.editText1);
		et_name = (EditText) findViewById(R.id.editText2);
		btn_test = (Button) findViewById(R.id.button1);
		btn_local = (Button) findViewById(R.id.button2); // 登录按钮
		clear = (Button) findViewById(R.id.clear);

		btn_test.setOnClickListener(new testListener());
		btn_local.setOnClickListener(new localListener());
		clear.setOnClickListener(new clearListener());

		SQLiteDatabase student = helper.getReadableDatabase();
		String sql = "select * from student";
		Cursor cursor = student.query(sql, null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
			// 遍历Cursor对象，取出数据并打印
			String name = cursor.getString(cursor.getColumnIndex("id"));
			et_id.setText(name);
			
			} while (cursor.moveToNext());
			
		}
			cursor.close();
		}
	
		
		
// 登录按钮
class localListener implements OnClickListener {
	public void onClick(View v) {
		_id = et_id.getText().toString();
		_name = et_name.getText().toString();
		if (_name.equals("") || _id.equals("")) {
			Toast.makeText(getApplicationContext(), "请输入账号密....码！", Toast.LENGTH_SHORT).show();
		} else {
			sureuser(_id, _name);
		}
	}

}

// 注册按钮
class testListener implements OnClickListener {
	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this, Register_activity.class);
		startActivity(intent);
	}

}

// 清除按钮
class clearListener implements OnClickListener {
	public void onClick(View v) {
		et_id.setText("");
		et_name.setText("");
	}

	}

	private void sureuser(String userid, String username) { // 3,数据库的操作，查询
															// ，确认用户的输入
		SQLiteDatabase sdb = helper.getReadableDatabase();
		try {
			String sql = "select * from student where id=? and name=?";

			// 游标实现遍历id和name
			cursor = sdb.rawQuery(sql, new String[] { _id, _name });
			if (cursor.getCount() > 0) {
				Intent intent = new Intent(MainActivity.this, WelcomeAvtivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("name", _name);
				intent.putExtras(bundle);
				startActivity(intent);
			}

			else {
				Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
			}

			cursor.close();
			sdb.close();
		} catch (SQLiteException e) {
			Toast.makeText(getApplicationContext(), "亲，请注册！", Toast.LENGTH_SHORT).show();
		}
	}

}