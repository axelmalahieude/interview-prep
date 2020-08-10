import java.util.Random

fun main() {
    val SIZE = 50
    val generator = Random()
    val input = Array<Int>(SIZE) { generator.nextInt() % 50 }
    solution(input, 30)
}

fun solution(array: Array<Int>, sum: Int) {
    // idea: loop through array, for each value calculate new sum
    // apply two sum to remaining elements

    array.forEach{
        
    }
}