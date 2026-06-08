fun main() {
    println("What is your name?")
    var name = readLine()!!

    println("What is your age?")
    var age = readLine()!!.toInt()

    println("My name is $name and I am $age years old")
}