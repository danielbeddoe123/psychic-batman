package com.monitise.beddoed.Validator

object CLIValidator extends Validator {

  def validateCLIArgs(args: Array[String]) {
    require(args.length >= 0)
    args foreach (isValidValue(_))
  }

  def isValidValue(s: String) {
    s match {
      case Int(x) => sys.error("Cannot have numbers: " + x)
      case _ =>
    }
  }
}

/**
 * Defined extractor to test the existence of an Integer in a String
 */
object Int {
  def unapply(s: String): Option[Int] = try {
    Some(s.toInt)
  } catch {
    case _ : NumberFormatException => None
  }
}
