package structures

//
// Inspired from Ralf Hinze's paper "Constructing Red-Black Trees"
//
trait RBTOps {

  import scalaz._, Scalaz._

  private
  def insert[A : Order](v: A)(r: ⇒ RBTree[A]) : RBTree[A] = {

    def balance(color: Color, ll: RBTree[A], a: A, rr: RBTree[A]) : RBTree[A] = {
      def tryLeft : PartialFunction[RBTree[A], Option[RBTree[A]]] = {
        case N(Red, N(Red, t1, a1, t2), a2, t3) ⇒ N(Red, N(Black, t1, a1, t2), a2, N(Black, t3, a, rr)).some
        case N(Red, t1, a1, N(Red, t2, a2, t3)) ⇒ N(Red, N(Black, t1, a1, t2), a2, N(Black, t3, a, rr)).some
        case _ ⇒ none
      }
      def tryRight : PartialFunction[RBTree[A], Option[RBTree[A]]] = {
        case N(Red, N(Red, t2, a2, t3), a3, t4) ⇒ N(Red, N(Black, ll, a, t2), a2, N(Black, t3, a3, t4)).some
        case N(Red, t2, a2, N(Red, t3, a3, t4)) ⇒ N(Red, N(Black, ll, a, t2), a2, N(Black, t3, a3, t4)).some
        case _ ⇒ N(color, ll, a, rr).some
      }
      if (color == Black) {
        tryLeft(ll).getOrElse(tryRight(rr).get)
      } else N(color, ll, a, rr)
    }

    def blacken(rbt: ⇒ RBTree[A]) = (rbt : @unchecked) match {
      case n@N(_,_,_,_) ⇒ n.copy(color = Black)
    }

    def ins(_t : ⇒ RBTree[A]) : RBTree[A] = _t match {
      case e@E()                  ⇒ N(Red, E[A](), v, E[A]())
      case n@N(c,l,b,r) if v < b  ⇒ balance(c, ins(l), b, r)
      case n@N(c,l,b,r) if v == b ⇒ N(c, l, v, r)
      case n@N(c,l,b,r) if v > b  ⇒ balance(c, l, b, ins(r))
    }

    blacken(ins(r))
  }

  /**
    * The properties of this approach, is that it produces a left-complete tree
    * of a maximum height for values of "i" where i ∈ {set of values}
    * Another property is that "topDown" generates the least number of red
    * nodes.
    */
  def topDown(data: List[Int]) : RBTree[Int] =
    implicitly[Foldable[List]].foldr[Int,RBTree[Int]](data, E[Int]())(insert[Int])

}

