package object structures {
  type Level = Int

  sealed trait Color
  case object Red extends Color
  case object Black extends Color

  sealed trait RBTree[A]
  case class E[A]() extends RBTree[A]
  case class N[A](color: Color, left: RBTree[A], value : A , right : RBTree[A]) extends RBTree[A]

  sealed trait Tree[A]
  case class Empty[A]() extends Tree[A]
  case class Node[A](level : Level, l: Tree[A], v: A, r: Tree[A]) extends Tree[A]

}
