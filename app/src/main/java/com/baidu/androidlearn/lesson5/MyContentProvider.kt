package com.baidu.androidlearn.lesson5

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri


class MyContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        // 初始化ContentProvider，例如创建数据库等
        return false
    }
    override fun query(uri: Uri, projection: Array<out String>?, selection: String?,
        selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        // 根据URI查询数据
        return null
    }
    override fun getType(uri: Uri): String? {
        // 返回当前URI对应的数据的MIME类型
        return null
    }
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // 根据URI插入数据
        return null
    }
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        // 根据URI删除数据
        return 0
    }
    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<out String>?): Int {
        // 根据URI更新数据
        return 0
    }

//    fun test() {
//        // 获取ContentResolver对象
//        val contentResolver: ContentResolver = getContentResolver()
//        // 构造ContentProvider的URI
//        val uri: Uri = Uri.parse("content://com.baidu.myapp.provider/your_table_name")
//        // 使用ContentResolver进行数据查询
//        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
//    }
}