package ru.tinkoff.variance

object Covariance extends App {

  def printMyList(list: MyList[Animal]): Unit = println(list)
  sealed trait MyList[+A] {
    def contains[A1 >: A](a: A1): Boolean
  }
  case object Nil extends MyList[Nothing] {
    override def contains[A1 >: Nothing](a: A1): Boolean = false
  }
  case class Cons[A](head: A, tail: MyList[A]) extends MyList[A] {
    override def contains[A1 >: A](a: A1): Boolean =
      if (a == head) true else tail match {
        case Nil => false
        case tail => tail.contains(a)
      }
  }


  class Box[A](var a: A)

  sealed trait Animal
  case class Cat() extends Animal
  case class Dog() extends Animal

  val myList: MyList[Animal] = Cons(Cat(), Cons(Dog(), Nil))

  println(myList.contains(1))

  printMyList(myList)
}
