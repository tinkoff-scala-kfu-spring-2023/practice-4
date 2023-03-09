package ru.tinkoff.variance

object ContraVariance extends App {
  trait Formatter[A] {
    def format(a: A): String
  }

  class ToStringFormatter[A] extends Formatter[A] {
    override def format(a: A): String = a.toString
  }

  trait ToJsonFormatter[A] extends Formatter[A] {
    override def format(a: A): String
  }

  sealed trait Pet {
    def name: String
  }
  case class Dog(name: String, food: String, treat: String) extends Pet
  case class Cat(name: String, food: String, toy: String) extends Pet

  val catJsonFormatter = new ToJsonFormatter[Cat] {
    override def format(a: Cat): String =
      s"""{
         |"cat_name": "${a.name}"
         |}""".stripMargin
  }

  val catToString = new ToStringFormatter[Cat] {
    override def format(a: Cat): String = a.name
  }

  class Reporter[A](formatter: Formatter[A]) {
    def report(a: A): Unit = {
      println(formatter.format(a))
    }
  }
  val cat = Cat("Steven", "fish", "mice")
  val petToString = new ToStringFormatter[Pet]

  val catReporter = new Reporter[Cat](petToString)
  catReporter.report(cat)

  val petReporter = new Reporter[Cat](catToString)
  petReporter.report(cat)

}
