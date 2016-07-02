package sudoku.validator

import scala.collection.mutable

object SolutionValidator
{
  type Board = Vector[Vector[Int]]
  type Group = mutable.Set[Int]

  val range = 0 until 9

  def validate(board: Board): Boolean = {
    var isValid = true
    val zones = mutable.Map[(Int, Int), Group]()
    var count = 0
    for (i <- range if isValid) {
      val row = mutable.Set[Int]()
      val col = mutable.Set[Int]()
      for (j <- range if isValid) {
        val zone = (i / 3, j / 3)
        isValid = isValid &&
          isValidInGroup(i, j, row) &&
          isValidInGroup(j, i, col) &&
          isValidInGroup(i, j, zones.getOrElseUpdate(zone, mutable.Set()))
        count += 1
      }
    }

    def isValidInGroup(i: Int, j: Int, g: Group): Boolean = {
      val e = board(i)(j)
      valid(e) && g.add(e)
    }

    def valid(x: Int) = 0 <= x && x < 10

    isValid
  }

  def boardToString(board: Board): String = {
    val wrap = "+----------+-----------+----------+\n"
    board.map { row =>
      s"$wrap|${row.grouped(3).map(_.mkString("   ")).mkString(" | ")}|"
    }.mkString("", "\n", s"\n$wrap")
  }
}
