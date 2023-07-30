package com.baidu.androidlearn.lesson9

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import java.io.*


class LessonSevenActivity : Activity() {

    val TAG = "LessonSevenActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lesson7_activity_main)
        Log.i(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    fun spPut(view: View) {
        // 步骤1：创建一个SharedPreferences对象
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        // 步骤2：实例化SharedPreferences.Editor对象
        val editor = sharedPreferences.edit()
        // 步骤3：将获取过来的值放入文件
        editor.putString("userName", "野比")
        editor.putInt("age", 17)
        // 步骤4：提交（异步写入）
        editor.apply()
        // 步骤4：提交（异步写入）
        editor.commit()

        Toast.makeText(this, "已写入", Toast.LENGTH_SHORT).show()
    }

    fun spGet(view: View) {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        // 读取数据
        val name = sharedPreferences.getString("userName", "")
        val age = sharedPreferences.getInt("age", 0)
        Toast.makeText(this, "userName = " + name + " ,age = " + age, Toast.LENGTH_SHORT).show()

        // 删除数据
        // val editor = sharedPreferences.edit()
        // editor.remove("userName")
        // editor.apply()
        // 清空数据，一般不建议使用
        // editor.clear()
        // editor.apply()
    }

    fun fileWirte(view: View) {
        // 使用 openFileOutput() 写入文件
        try {
            // 创建一个 FileOutputStream 对象
            val fos: FileOutputStream = openFileOutput("filename.txt", MODE_PRIVATE)
            // 写入文件内容
            val data = "Hello, world!"
            val bytes = data.toByteArray()
            fos.write(bytes)

            Toast.makeText(this, "已写入文件", Toast.LENGTH_SHORT).show()
            // 关闭文件流
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun fileRead(view: View) {
        // 使用 openFileInput() 以信息流的形式读取文件
        try {
            // 创建一个 FileInputStream 对象
            val fis: FileInputStream = openFileInput("filename.txt")
            // 读取文件内容
            val isr = InputStreamReader(fis)
            val bufferedReader = BufferedReader(isr)
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                Log.d(TAG, line!!)
                Toast.makeText(this, line, Toast.LENGTH_SHORT).show()
            }

            // 关闭文件流
            bufferedReader.close()
            isr.close()
            fis.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun dbInsert(view: View) {
        // 访问数据库，先实例化 MyDBHelper 的子类
        val dbhelper = MyDBHelper(this)
        // 以写入模式获取数据存储库
        val db: SQLiteDatabase = dbhelper.getWritableDatabase()
        val values = ContentValues()
        // 插入第一行数据
        values.put("author", "郭霖")
        values.put("name", "第一行代码")
        db.insert("Book", null, values)
        // 插入其它数据前先清空
        values.clear()
        // 插入第二条数据
        values.put("author", "张三")
        values.put("name", "操作系统原理")
        db.insert("Book", null, values)

        Toast.makeText(this, "已写入数据库", Toast.LENGTH_SHORT).show()
    }

    fun dbQuery(view: View) {
        val dbhelper = MyDBHelper(this)
        // 以读取模式获取数据存储库
        val db: SQLiteDatabase = dbhelper.getReadableDatabase()
        val cursor = db.query("Book", null, null, null, null, null, null)
        val data = StringBuilder()
        var i = 1
        with(cursor) {
            while (moveToNext()) {
                val author = getString(getColumnIndexOrThrow("author"))
                val name = getString(getColumnIndexOrThrow("name"))
                data.append("第" + i + "行数据， author：" + author + "， name：" + name + "\n\n")
                i++
            }
        }
        cursor.close()

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }

    fun dbUpdate(view: View) {
        val dbhelper = MyDBHelper(this)
        // 以写入模式获取数据存储库
        val db: SQLiteDatabase = dbhelper.getWritableDatabase()
        val values = ContentValues().apply {
            put("name", "第二行代码")
        }
        // ？是一个占位符
        // 第四个参数提供一个字符串数组，为第三个参数中的每个占位符指定相应的内容
        db.update("Book", values, "author = ?", arrayOf("郭霖"))

        Toast.makeText(this, "已更新数据库行", Toast.LENGTH_SHORT).show()
    }

    fun dbDelete(view: View) {
        val dbhelper = MyDBHelper(this)
        // 以写入模式获取数据存储库
        val db: SQLiteDatabase = dbhelper.getWritableDatabase()

        // ？是一个占位符
        // 第四个参数提供一个字符串数组，为第三个参数中的每个占位符指定相应的内容
        db.delete("Book", "author = ?", arrayOf("郭霖"))

        Toast.makeText(this, "已删除数据库行", Toast.LENGTH_SHORT).show()
    }
}