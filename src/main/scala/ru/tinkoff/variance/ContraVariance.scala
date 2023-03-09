package ru.tinkoff.variance

object ContraVariance extends App {
  trait Formatter[-A] {
    def format(a: A): String
  }

  class ToStringFormatter[A] extends Formatter[A] {
    override def format(a: A): String = a.toString
  }

  trait ToJsonFormatter[A] extends Formatter[A] {
    override def format(a: A): String
  }

  sealed trait Animal {
    def name: String
  }
  case class Dog(name: String, food: String, treat: String) extends Animal
  case class Cat(name: String, food: String, toy: String) extends Animal

  val catJsonFormatter = new ToJsonFormatter[Cat] {
    override def format(a: Cat): String =
      s"""{
         |"cat_name": "${a.name}"
         |}""".stripMargin
  }

  val catToString = new ToStringFormatter[Cat] {
    override def format(a: Cat): String = a.name
  }

  class Reporter(formatter: Formatter[Cat]) {
    def report(a: Cat): Unit = {
      println(formatter.format(a))
    }
  }
  val cat = Cat("Steven", "fish", "mice")
  val petToString = new ToStringFormatter[Animal]

  val catReporter = new Reporter(petToString)
  catReporter.report(cat)

  val petReporter = new Reporter(catToString)
  petReporter.report(cat)

}
