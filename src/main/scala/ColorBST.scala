package structures

// Coloring Binary search trees s.t. a Red-black tree emerges
// or an Error is returned as part of the \/[Exception,RBTree] value.
trait ColorBST {
  
  import scalaz.{Tree ⇒ _, Node ⇒ _, _}, Scalaz._

  def rbtree2[A](h: Level) : Reader[Tree[A], \/[Exception, RBTree[A]]] =
    Reader{(tree: Tree[A]) ⇒
      def color(x: Level) : Color = if (x == h) Red else Black
      tree match {
        case e@Empty() if h == 1 ⇒ \/-(E[A]())
        case e@Empty() if h != 1 ⇒ -\/(new Exception("not a red-black tree"))
        case Node(h2, l, a, r) ⇒
          for {
            ll ← rbtree2(scala.math.max(h2, h))(l)
            rr ← rbtree2(scala.math.max(h2, h))(r)
          } yield N[A](color(h2), ll, a, rr)
      } 
    }

  def rbtree[A] : Reader[Tree[A], \/[Exception, RBTree[A]]] = Reader{ (tree: Tree[A]) ⇒
    tree match {
      case e@Empty() ⇒ \/-(E[A]())
      case Node(h, l, a, r) ⇒
        for {
          ll ← rbtree2(h)(l)
          rr ← rbtree2(h)(r)
        } yield N[A](Black, ll, a, rr)
    } 
  }

}
