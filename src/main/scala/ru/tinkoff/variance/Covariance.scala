package ru.tinkoff.variance

object Covariance extends App {
  sealed trait MyList[A] {
    def contains(a: A): Boolean
  }
  case class Nil[A]() extends MyList[A] {
    override def contains(a: A): Boolean = ???
  }
  case class Cons[A](head: A, tail: List[A]) extends MyList[A] {
    override def contains(a: A): Boolean = ???
  }


  sealed trait Pet
  case class Cat() extends Pet
  case class Dog() extends Pet

  val myList: MyList[Pet] = ???
  def printMyList[A](list: MyList[A]): Unit = ???

  printMyList(myList)
}
