//package com.example.myapplication

/*
fun main() {
    println("Hello World")
}
*/

/*
fun main() {
    println(sayHello("Budi"))
}

fun sayHello(name: String): String{
    val result = "Hello $name"
    return result
}

fun sayHello2(name: String) : String = "Hello $name"

 */

fun greetings(greeating: String = "Hello", name:String) : String{
    return "$greeating $name"
}

fun main() {
    println(greetings("Pagi",name = """
        Budi
        Doni
        Mimin
    """.trimIndent()))
}
