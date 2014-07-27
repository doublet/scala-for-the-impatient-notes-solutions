object Chapter01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 1. In the Scala REPL, type 3. followed by the Tab key. What methods can be
  // applied?
  println("Try typing '3.' in the interpreter, then press <tab>")
                                                  //> Try typing '3.' in the interpreter, then press <tab>
  
  // 2. In the Scala REPL, compute the square root of 3, and then square that value.
  // By how much does the result differ from 3? (Hint: The res variables are your friend.)
  import math._
  println(sqrt(3) * sqrt(3))                      //> 2.9999999999999996

  // 3. Are the res variables val or var ?
  println("They are val. To illustrate, try reassing a res* val, you'll get the following error:")
                                                  //> They are val. To illustrate, try reassing a res* val, you'll get the followi
                                                  //| ng error:
  println("<console>:11: error: reassignment to val")
                                                  //> <console>:11: error: reassignment to val
  println("     res0 = 1")                        //>      res0 = 1
  println("          ^")                          //>           ^

  // 4. Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL.
  // What does this operation do? Where can you find it in Scaladoc?
  println("crazy" * 3)                            //> crazycrazycrazy
  println("You can find this in the StringOps class: http://www.scala-lang.org/api/current/#scala.runtime.RichInt")
                                                  //> You can find this in the StringOps class: http://www.scala-lang.org/api/curr
                                                  //| ent/#scala.runtime.RichInt
  println("def *(n: Int): String")                //> def *(n: Int): String
  println("Return the current string concatenated n times. ")
                                                  //> Return the current string concatenated n times. 
  
  // 5. What does 10 max 2 mean? In which class is the max method defined?
  println(10 max 2)                               //> 10
  println(10.max(2))                              //> 10
  println("10 max 2 == 10.max(2)")                //> 10 max 2 == 10.max(2)
  println("max is defined on the RichInt class, which provides methods for ints")
                                                  //> max is defined on the RichInt class, which provides methods for ints
  println("Documentation for this class is here: http://www.scala-lang.org/api/current/#scala.runtime.RichInt")
                                                  //> Documentation for this class is here: http://www.scala-lang.org/api/current
                                                  //| /#scala.runtime.RichInt

  // 6. Using BigInt , compute 2 1024 .
  val aBigNumber: BigInt = 2                      //> aBigNumber  : scala.math.BigInt = 2
  aBigNumber.pow(1024)                            //> res0: scala.math.BigInt = 1797693134862315907729305190789024733617976978942
                                                  //| 306572734300811577326758055009631327084773224075360211201138798713933576587
                                                  //| 897688144166224928474306394741243777678934248654852763022196012460941194530
                                                  //| 829520850057688381506823424628814739131105408272371633505106845862982399472
                                                  //| 45938479716304835356329624224137216
  aBigNumber pow 1024                             //> res1: scala.math.BigInt = 1797693134862315907729305190789024733617976978942
                                                  //| 306572734300811577326758055009631327084773224075360211201138798713933576587
                                                  //| 897688144166224928474306394741243777678934248654852763022196012460941194530
                                                  //| 829520850057688381506823424628814739131105408272371633505106845862982399472
                                                  //| 45938479716304835356329624224137216

  // 7. What do you need to import so that you can get a random prime as
  // probablePrime(100, Random) , without any qualifiers before probablePrime and Random ?
  println("Normally, we'd use BigInt.probablePrime(100, scala.util.Random)")
                                                  //> Normally, we'd use BigInt.probablePrime(100, scala.util.Random)
  BigInt.probablePrime(100, scala.util.Random)    //> res2: scala.math.BigInt = 822372260572592026348507621783
  
  import BigInt.probablePrime
  import util.Random
  println("But by importing the companion objects/methods, we can use their methods (which are like static methods) directly")
                                                  //> But by importing the companion objects/methods, we can use their methods (w
                                                  //| hich are like static methods) directly
  println("We're importing BigInt.probablePrime, which is a function, not a companion object according to ScalaDoc. Not sure how that works")
                                                  //> We're importing BigInt.probablePrime, which is a function, not a companion 
                                                  //| object according to ScalaDoc. Not sure how that works
  probablePrime(100, Random)                      //> res3: scala.math.BigInt = 641268587367894032542971864803

  // 8. One way to create random file or directory names is to produce a random
  // BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul" .
  // Poke around Scaladoc to find a way of doing this in Scala.
  probablePrime(100, Random).toString(36)         //> res4: String = 2avk1aioy6f7gpmj4w2d
  println("This generates a random value, then displays it as a string in base 36. toString(radix: Int) is defined in the BigInt class")
                                                  //> This generates a random value, then displays it as a string in base 36. toS
                                                  //| tring(radix: Int) is defined in the BigInt class
  println("def toString(radix: Int): String")     //> def toString(radix: Int): String
  println("Returns the String representation in the specified radix of this BigInt.")
                                                  //> Returns the String representation in the specified radix of this BigInt.

  // 9. How do you get the first character of a string in Scala? The last character?
  "Last character".last                           //> res5: Char = r
  "First character".take(1)                       //> res6: String = F
  println("This serves to illustrate that Scala has much more methods than Java")
                                                  //> This serves to illustrate that Scala has much more methods than Java
  
  // 10. What do the take , drop , takeRight , and dropRight string functions do? What
  // advantage or disadvantage do they have over using substring ?
  println("def take(n: Int): String   Selects first n elements.")
                                                  //> def take(n: Int): String   Selects first n elements.
  println("def drop(n: Int): String   Selects all elements except first n ones.")
                                                  //> def drop(n: Int): String   Selects all elements except first n ones.
  println("takeRight and dropRight do the same thing but from the right side")
                                                  //> takeRight and dropRight do the same thing but from the right side
   
   // the first 2 characters of "Hello"
  "Hello".take(2)                                 //> res7: String = He
  // everything but the first 2 characters of "Hello"
  "Hello".drop(2)                                 //> res8: String = llo
  // take the last 2 characters of "Hello", like "Hello"[-2:] in Python
  "Hello".takeRight(2)                            //> res9: String = lo
  
  
}