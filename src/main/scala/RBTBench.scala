package structures


import org.openjdk.jmh.annotations.Benchmark

object td extends RBTOps
object bu extends RBTOps2

//
// Some base comparisons
//
class Comparison {

  @Benchmark
  def BottomUp256 = bu.bottomUp((1 to 256).toList)
  
  @Benchmark
  def BottomUp1024 = bu.bottomUp((1 to 1024).toList)

  @Benchmark
  def BottomUp2048 = bu.bottomUp((1 to 2048).toList)

  @Benchmark
  def BottomUp16384 = bu.bottomUp((1 to 16384).toList)

  @Benchmark
  def BottomUp2_256 = bu.bottomUp2((1 to 256).toList)
  
  @Benchmark
  def BottomUp2_1024 = bu.bottomUp2((1 to 1024).toList)

  @Benchmark
  def BottomUp2_2048 = bu.bottomUp2((1 to 2048).toList)

  @Benchmark
  def BottomUp2_16384 = bu.bottomUp2((1 to 16384).toList)

  @Benchmark
  def TopDown256 = td.topDown((1 to 256).toList)

  @Benchmark
  def TopDown1024 = td.topDown((1 to 1024).toList)

  @Benchmark
  def TopDown2048 = td.topDown((1 to 2048).toList)

  @Benchmark
  def TopDown16384 = td.topDown((1 to 16384).toList)

}
