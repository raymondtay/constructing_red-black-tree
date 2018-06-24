package structures

sealed trait Digit[A]
case class One[A](v: A, t: RBTree[A]) extends Digit[A]
case class Two[A](v: A, l: RBTree[A], u: A, r: RBTree[A]) extends Digit[A]

//
// From Section 4 of "Constructing Red-Black Trees", we see that we can improve
// the "topDown" approach
//
trait RBTOps2 {

  private
  def incr[A](d: Digit[A])(ds: List[Digit[A]]) : List[Digit[A]] = {
    ((d, ds) : @unchecked) match {
      case (One(a,t), Nil) ⇒ List(One(a, t))
      case (One(a1, t1), One(a2, t2) :: ps) ⇒ Two(a1, t1, a2, t2) :: ps
      case (One(a1, t1), Two(a2, t2, a3, t3) :: ps) ⇒ One(a1, t1) :: incr(One(a2, (N(Black, t2, a3, t3))))(ps)
    }
  }

  private
  def add[A](e: A, ps: List[Digit[A]]) = incr(One(e, E[A]()))(ps)

  private
  def link[A](l: RBTree[A], d: Digit[A]) : RBTree[A] = d match {
    case One(a, t) ⇒ N(Black, l, a, t)
    case Two(a1, t1, a2, t2) ⇒ N(Black, N(Red, l, a1, t1), a2, t2)
  }

  private
  def link2[A](l: RBTree[A], d: Digit[A]) : RBTree[A] = d match {
    case One(a, t) ⇒ N(Black, l, a, t)
    case Two(a1, t1, a2, t2) ⇒ N(Black, l, a1, N(Red, t1, a2, t2))
  }

  private
  def linkAll[A] = (es: List[Digit[A]]) ⇒ es.foldLeft[RBTree[A]](E[A]())(link)

  private
  def linkAll2[A] = (es: List[Digit[A]]) ⇒ es.foldLeft[RBTree[A]](E[A]())(link2)

  def bottomUp[A](es: List[A]) : RBTree[A] =
    linkAll(es.foldRight(List.empty[Digit[A]])(add))

  def bottomUp2[A](es: List[A]) : RBTree[A] =
    linkAll2(es.foldRight(List.empty[Digit[A]])(add))

}
