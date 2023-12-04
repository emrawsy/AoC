import scala.io.Source

object Day2 extends App {

  val txt = Source.fromResource("day2.txt").mkString

  val input = txt.split("\n").toList

  //part one
  val colourLimits = Map("red" -> 12, "green" -> 13, "blue" -> 14)
  def gameID(game: String): Int = {
    val Array(_, id) = game.trim.split(" ")
    id.toInt
  }
  def parsedGames(gamesList: List[String]): Seq[(String, Array[Array[(Int, String)]])] = {
    gamesList.map {
      games =>
        val Array(game, res) = games.trim.split(":")
        (game, cubeResults(res))
    }
  }
  def cubeResults(results: String): Array[Array[(Int, String)]] = {
    results.split(";").map(_.split(",").map {
          colourNumber =>
            val Array(number, colour) = colourNumber.trim.split(" ")
            (number.toInt, colour)
        })
  }
  def validResults(gamesList: List[String]) = {
    parsedGames(gamesList).map {
      case (game, results) =>
        val valid = results.flatten.filter {
          case (number, colour) =>
            number <= colourLimits(colour)
        }
        if (valid.length == results.flatten.length) (gameID(game), valid)
        else 0
    }
  }
  def sumOfValidIds(gamesList: List[String]): Int = validResults(gamesList).collect { case (gameId: Int, _) => gameId }.sum

  println(sumOfValidIds(input))

  //part two
  def minimumPossibleColours(gamesList: List[String]): Seq[(String, Map[String, Int])] =
    parsedGames(gamesList).map {
      case (game, results) =>
        val colourMaxMap = results.flatten.groupBy(_._2).map {
          case (colour, number) =>
            colour -> number.maxBy(_._1)._1
        }
        game -> colourMaxMap
    }
  def multiplyThePossibleValues(largestNumbers: Seq[(String, Map[String, Int])]): Seq[(String, Int)] = {
    largestNumbers.map {
      case (game, colorMaxMap) =>
        game -> colorMaxMap.values.product
    }
  }
  def totalPowerOfCubes(cubeTotal: Seq[(String, Int)]): Int = {
    cubeTotal.map(_._2).sum
  }

  println(totalPowerOfCubes(multiplyThePossibleValues(minimumPossibleColours(input))))

}
