package sudoku

import sudoku.validator.SolutionValidator

object Main extends App
{

  import SolutionValidator._

  println(boardToString(Boards.VALID))
  val valid = SolutionValidator.validate(Boards.VALID)
  println(s"solution is ${validationResult(valid)}")

  def validationResult(valid: Boolean) = {
    if (valid) "valid"
    else "invalid"
  }
}
