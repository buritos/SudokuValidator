package sudoku.validator

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import sudoku.Boards

class SolutionValidatorSpec extends FlatSpec with Matchers with TableDrivenPropertyChecks
{

  val cases = Table(
    ("input", "expected"),
    (Boards.VALID, true),
    (Boards.INCOMPLETE_VALID, true),
    (Boards.INVALID, false),
    (Boards.INVALID_SUBGROUP, false),
    (Boards.INVALID_ROW, false),
    (Boards.INVALID_COL, false)
  )

  "SolutionValidator" should "validate sudokus" in {
    forAll(cases) { (board, expected) =>
      SolutionValidator.validate(board) should be === expected
    }
  }

}
