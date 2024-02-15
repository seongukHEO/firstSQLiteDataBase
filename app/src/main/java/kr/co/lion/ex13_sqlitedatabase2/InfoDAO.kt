package kr.co.lion.ex13_sqlitedatabase2

import android.content.Context

class InfoDAO {
    companion object{

        //select one
        //데이터 1개를 가져온다
        fun selectOneInfo(context: Context, idx: Int) : InfoClass{
            //쿼리문
            var sql = """select idx, name, age, kor
                |from StudentTable
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(idx.toString())

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            //실행을 위해 행에 접근한다
            cursor.moveToNext()

            //1, 데이터 베이스에 접근하여 순서값을 가져온다
            var idx1 = cursor.getColumnIndex("idx")
            var idx2 = cursor.getColumnIndex("name")
            var idx3 = cursor.getColumnIndex("age")
            var idx4 = cursor.getColumnIndex("kor")

            //2, 데이터를 가져온다
            var idx = cursor.getInt(idx1)
            var name = cursor.getString(idx2)
            var age = cursor.getInt(idx3)
            var kor = cursor.getInt(idx4)

            //객체에 데이터를 받는다
            var studentList = InfoClass(idx, name, age, kor)

            //데이터 베이스를 닫아준다
            dbHelper.close()
            return studentList
        }

        //selectAll
        //모든 데이터를 가져온다
        fun selectAllInfo(context: Context) : MutableList<InfoClass>{
            //쿼리문
            // order by : 정렬
            // order by 기준컬럼 정렬방식
            // 정렬방식 : asc 나 생략 - 오름 차순, desc : 내림 차순
            var sql = """select idx, name, age, kor
                |from StudentTable
                |order by idx desc
            """.trimMargin()
            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            //데이터를 담을 리스트 왜냐면 리스트 형태로 반환하기 때문
            var studentList = mutableListOf<InfoClass>()

            //접근을 하는데 이건 모든 데이터를 받아오기 때문에 while안에 담아준다
            while (cursor.moveToNext()){
                var idx1 = cursor.getColumnIndex("idx")
                var idx2 = cursor.getColumnIndex("name")
                var idx3 = cursor.getColumnIndex("age")
                var idx4 = cursor.getColumnIndex("kor")

                var idx = cursor.getInt(idx1)
                var name = cursor.getString(idx2)
                var age = cursor.getInt(idx3)
                var kor = cursor.getInt(idx4)

                //객체에 데이터를 받는다
                var studentModel = InfoClass(idx, name, age, kor)

                //받은 객체를 리스트에 넣어준다
                studentList.add(studentModel)
            }
            //데이터 클래스를 닫는다
            dbHelper.close()
            return studentList
        }

        //insert
        //매개 변수로 들어온 값을 저장한다
        fun recordInsert(context: Context, infoClass: InfoClass){
            //쿼리 생성
            var sql = """insert into StudentTable
                |(name, age, kor)
                |values(?, ?, ?)
            """.trimMargin()

            //?에 들어갈 값을 구한다
            var args = arrayOf(infoClass.name, infoClass.age, infoClass.kor)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            //닫아준다
            dbHelper.close()

        }

        //upgrade
        //변경사항을 적용시킨다
        fun updateInfo(context: Context, infoClass: InfoClass){
            var sql = """update StudentTable
                |set name = ?, age = ?, kor = ?
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(infoClass.name, infoClass.age, infoClass.kor)
            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            //닫아준다
            dbHelper.close()

        }

        //delete
        //삭제한다
        fun deleteInfo(context: Context, idx: Int){
            var sql = """delete from StudentTable
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf("idx")
            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            //닫아준다
            dbHelper.close()

        }
    }
}