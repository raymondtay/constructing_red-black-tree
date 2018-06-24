
Here's a simple example:

```tut
import structures._
object rbt extends RBTOps
object rbt2 extends RBTOps2

val tree1 = rbt.topDown(List(1, 6, 8, 11, 13, 15, 17, 22, 25, 27))
val tree2 = rbt.topDown(1 to 9 toList)
val tree3 = rbt.topDown(List(1, 6, 8, 11, 13, 15, 17, 22, 25, 27).reverse)
val radix2T1 = rbt2.bottomUp(List(1,2,2,2))
val radix2T2 = rbt2.bottomUp(List(2,1,1,1))

val leftleaning = rbt2.bottomUp(3 :: List.fill(11)(2))
val balanced = rbt2.bottomUp2(3 :: List.fill(11)(2))
val leftleaning2 = rbt2.bottomUp(List.fill(11)(2))
val balanced2 = rbt2.bottomUp2(List.fill(11)(2))

```

