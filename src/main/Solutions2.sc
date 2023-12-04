import scala.io.Source
import scala.util.matching.Regex


  def extractFirstAndLastNumbers(line: String): (String, String) = {
    val numberPattern = "(\\d+)|(one|two|three|four|five|six|seven|eight|nine)".r

    val numbers = numberPattern
      .findAllIn(line.toLowerCase)
      .matchData
      .map(matchData => wordToDigit(matchData.group(0)))
      .toList

    if (numbers.nonEmpty) {
      val firstNumber = numbers.head * 2
      val lastNumber = numbers.last * 2
      (firstNumber.toString, lastNumber.toString)
    } else {
      ("", "")
    }
  }

  def wordToDigit(word: String): Int = {
    val wordToDigitMap = Map(
      "one" -> 1,
      "two" -> 2,
      "three" -> 3,
      "four" -> 4,
      "five" -> 5,
      "six" -> 6,
      "seven" -> 7,
      "eight" -> 8,
      "nine" -> 9
    )
    wordToDigitMap.getOrElse(word, 0)
  }

def extractor(): Unit = {
  val input =
    """two1nine
      |eightwothree
      |abcone2threexyz
      |xtwone3four
      |4nineeightseven2
      |zoneight234
      |7pqrstsixteen""".stripMargin

  val lines = input.split("\n").toList
  lines.foreach { line =>
    val (firstNumber, lastNumber) = extractFirstAndLastNumbers(line)
    println(s"Line: $line, First Number: $firstNumber, Last Number: $lastNumber")
  }
}

  println(extractor)


//  val extractNumbers: String = {
//    val lines = input.split("\n")
//    lines.map(
//      numbersAndWords =>
//        numbersAndWords.contains {
//      case "one" => "1"
//      case "two" => "2"
//      case "three" => "3"
//      case "four" => "4"
//      case "five" => "5"
//      case "six" => "6"
//      case "seven" => "7"
//      case "eight" => "8"
//      case "nine" => "9"
//    }
//    ).mkString("\n")
//  }
//
//
//
//
//println(extractNumbers)
//
//
//def twoDigitNumbers(in: String): Int =
//  in
//    .split("\n")
//    .map { value =>
//      val justDigits = value.replaceAll("[^0-9]", "")
//      if (justDigits.length == 1) Array(justDigits, justDigits)
//      else Array(justDigits.head.toString, justDigits.last.toString)
//    }.map(arr => arr.mkString.toInt).sum