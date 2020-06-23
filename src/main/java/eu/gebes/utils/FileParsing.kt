package eu.gebes.utils

fun stripIntend(line: String): String {
    return line.substring(getIntend(line), line.length)
}

fun getIntend(line: String): Int {

    var count = 0

    for (char in line.toCharArray())
        if (char == ' ' && count != 2)
            count++
        else return count

    return count
}