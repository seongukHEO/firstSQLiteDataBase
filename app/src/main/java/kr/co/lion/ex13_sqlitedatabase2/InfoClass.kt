package kr.co.lion.ex13_sqlitedatabase2

//idx -> 구분을 주기 위한 번호 꼭 필요하다!! 마치 동물원 클래스 만들때 동물의 타입처럼!
data class InfoClass(var idx:Int,var name:String, var age:Int, var kor:Int) {
}