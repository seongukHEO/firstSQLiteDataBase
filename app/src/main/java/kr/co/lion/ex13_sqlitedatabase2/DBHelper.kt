package kr.co.lion.ex13_sqlitedatabase2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Test.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //이건 뭐다? 테이블이 없을 때 테이블을 생성해준다
        // Int -> integer
        // Double -> real
        // String -> text
        // 날짜 -> date
        //autoincrement란 1씩 증가시키는 것
        //not null은 꼭 값이 들어와야 하는 것
        var seong = """create table StudentTable
            |(idx integer primary key autoincrement,
            |name text not null,
            |age integer not null,
            |kor integer not null)
        """.trimMargin()
        db?.execSQL(seong)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}