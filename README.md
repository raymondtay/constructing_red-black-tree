# constructing_red-black-tree

Created this project because i was a little bored on one saturday morning and
decided to read a paper entitled "Constructing Red-Black Trees" by Ralf Hinze
and thought it was an interesting exercise.

Why i found it interesting was because i wanted to read some interesting ideas
of the past (preferably in Haskell) and translate them over to Scala, learn a few
things about red-black trees and see whether i could stay faithful to the 
entire process translating Haskell to Scala.

I have found it quite instructive to go over the translation which largely examines
how the function `topDown` could be optimized by leveraging observations about the
ADT and re-structuring the approach which is epitomized in the function `bottomUp`.

# Examples and simple benchmarks

I have included [Rob Norris's tut plugin](https://github.com/tpolecat/tut) with examples
(see `src/main/tut/Examples.md`) as well [Konrad's sbt-jmh plugin](https://github.com/ktoso/sbt-jmh)
with a few benchmarks; see `src/main/scala/RBTBench.scala`.

There's no fan fare about using these plugins and i advise you to visit the respective sites
and learn how to use them and apply them here; see for yourself - `bottomUp` definitely works
much better versus `topDown` like how it is in the paper.


If you find any errors in the translation, do raise a pull request.

