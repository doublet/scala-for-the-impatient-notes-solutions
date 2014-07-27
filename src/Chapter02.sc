import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException
import java.io.IOException

object Chapter02 {
  /*** NOTES ***/
  
  // 2.1 Conditional expressions
  
  // if constructs return a value
  if(1 > 2) 100 else 101                          //> res0: Int = 101
  // the return type is the lowest common denominator
  if(1 > 2) 100 else "Hello"                      //> res1: Any = Hello
  // must always return a value, it's possible that this doesn't return anything
  if(1 > 2) 100                                   //> res2: AnyVal = ()
  // () is the one value of class Unit, it means "no useful value", it's like the void type in Java
  // this if statement and the one above are equal
  if(1 > 2) 100 else ()                           //> res3: AnyVal = ()
  // since they return a value, you can use that to assign variables
  val s = if(20 < 15) "Yes" else "No"             //> s  : String = No
  
  // 2.2 Statement termination
  
  // Semicolons are not required
  //  * At the end of a line
  //  * Before an }, an else, and the like
  
  
  // semicolons can be used to have more than one statement on a line
  if(1 == 1) { print("One statement "); print("and another") } else { print("Hi there") }
                                                  //> One statement and another
  // the parser will continue on to the next line if the line cannot be seen as complete (warning: that's not the case in the REPL, unlike node.js' REPL for example)
  5 + 9 *
  3                                               //> res4: Int = 32
	// 2.3 Block expressions and assignments
	
	// a block is enclosed with { and }, and contains a sequence of exressions (not statements)
	// its value is the value of the last expression
	val name = {val firstName = "John"; val lastName = "Doe"; val middleName = "J."; firstName + " " + middleName + " " + lastName}
                                                  //> name  : String = John J. Doe
  // value of an assignment is ()
  val returnOfAssignment = {val num = 3}          //> returnOfAssignment  : Unit = ()

  // 2.4 Input and output
  
  print("print without a newline")                //> print without a newline
  println("print with a newline")                 //> print with a newline
  printf("%s-style %s function", "C", "printf")   //> C-style printf function
  
  //val readName = readLine("Read something from the console")
  //val readInt = readInt // takes no arguments
  
  // 2.5 Loops
  
  // while loops like C++ and Java
  var n = 0                                       //> n  : Int = 0
  while(n < 5) {
  	print(n + " ")
    n+=1
  }                                               //> 0 1 2 3 4 

  // no for(..;..;..) equivalent
  // basic for loop:
	for(i <- 0 to 3) // no val or var before the i, scope is the for loop
		print(i + " ")                    //> 0 1 2 3 
  
  for(i <- "Hello")
    print(i + " ")                                //> H e l l o 
  
  // to vs. until
  // n to m: m inclusive ([n, m])
  for(i <- 0 to 5)
    print(i + " ")                                //> 0 1 2 3 4 5 
  // n until m: m exclusive ([n, m[)
  for(i <- 0 until 5)
    print(i + " ")                                //> 0 1 2 3 4 
  
  // multiple generators
  for(i <- 0 to 3; j <- 0 to 3)
  	print(i*10 + j + " ")                     //> 0 1 2 3 10 11 12 13 20 21 22 23 30 31 32 33 
  	
  // intemediary values (definitions)
  for(i <- 0 to 3; intermediaryValue = i * 2; j <- 0 to intermediaryValue)
  	print(i*10 + j + " ")                     //> 0 10 11 12 20 21 22 23 24 30 31 32 33 34 35 36 
  
  // for comprehension: yield a collection
  for(i <- 0 to 5) yield i * i                    //> res5: scala.collection.immutable.IndexedSeq[Int] = Vector(0, 1, 4, 9, 16, 2
                                                  //| 5)

  // simple encryption
  val encrypted = for(i <- "Hello") yield (i + 7).toChar
                                                  //> encrypted  : String = Olssv
  val decrypted = for(i <- encrypted) yield (i - 7).toChar
                                                  //> decrypted  : String = Hello
  
  // 2.7 Functions
  
  // types of all parameters must be specified
  // return type is inferred, except for recursive function
  def abs(x: Double) = if(x >= 0) x else -x       //> abs: (x: Double)Double
  abs(-5)                                         //> res6: Double = 5.0
  abs(99)                                         //> res7: Double = 99.0
  
  def fac(n: Int): Int = if(n == 1) 1 else n * fac(n - 1)
                                                  //> fac: (n: Int)Int
  fac(5)                                          //> res8: Int = 120
  fac(3)                                          //> res9: Int = 6

	// 2.8 Default and named arguments
	
	def surround(str: String, left: String = "[", right: String = "]") = left + str + right
                                                  //> surround: (str: String, left: String, right: String)String
  // not using any optional arguments
  // left and right use their default values
  surround("dev")                                 //> res10: String = [dev]
  
  // specifying just one optional argument
  // left uses the specified value, right keeps it optional value
  surround("dev", "~~>")                          //> res11: String = ~~>dev]
  
  // specifying both optional values
  surround("dev", "~~>", "<~~")                   //> res12: String = ~~>dev<~~
  
  // using named arguments
  surround(str="dev", left="{", right="}")        //> res13: String = {dev}
  
  // order doesn't matter with named arguments
  surround(left="{", str="dev", right="}")        //> res14: String = {dev}
  
  // mixing named and normal arguments
  surround("dev", left="{")                       //> res15: String = {dev]
  surround("dev", "{", right="}")                 //> res16: String = {dev}
  
  // surround("dev", "{", "}", left="(", right=")") won't work
  
  // 2.9 Variable arguments
  
  // note the *
  def sum(args: Int*) = {
  	var result = 0
  	for(arg <- args) result += arg
  	result
  }                                               //> sum: (args: Int*)Int
  
  sum(5,5,2,3,9)                                  //> res17: Int = 24
 
 	def avg(args: Int*) = {
 		var result = 0.0
 		for(arg <- args) result += arg
 		result / args.length
 	}                                         //> avg: (args: Int*)Double
  
  avg(5,3)                                        //> res18: Double = 4.0
  avg(2,2,2,1)                                    //> res19: Double = 1.75
  
  // ranges cannot be used as arguments to variable arguments functions
  //avg(0 to 15)
  // ...unless we ask Scala to consider them as an argument sequence
  avg(0 to 15: _*)                                //> res20: Double = 7.5
 
  // recursive functions can also have variable arguments, with some caveats
  def recursiveSum(args: Int*): Int = { // note that we specified the return type
  	val result = if(args.length == 0) 0 // almost feels like JavaScript
    else {
      // args.head: the first argument
      // args.tail: the tail, rest of the arguments ( args.head + args.tail == args)
    	println("Args: " + args + ", head: " + args.head + ", tail: " + args.tail)
    	args.head + recursiveSum(args.tail: _*)
    }
    result
  }                                               //> recursiveSum: (args: Int*)Int
  
  recursiveSum(0 to 5: _*)                        //> Args: Range(0, 1, 2, 3, 4, 5), head: 0, tail: Range(1, 2, 3, 4, 5)
                                                  //| Args: Range(1, 2, 3, 4, 5), head: 1, tail: Range(2, 3, 4, 5)
                                                  //| Args: Range(2, 3, 4, 5), head: 2, tail: Range(3, 4, 5)
                                                  //| Args: Range(3, 4, 5), head: 3, tail: Range(4, 5)
                                                  //| Args: Range(4, 5), head: 4, tail: Range(5)
                                                  //| Args: Range(5), head: 5, tail: Range()
                                                  //| res21: Int = 15
  
  // 2.10 Procedures
  
  // procedures are functions that return no value
  // they produce side effects (that or they do nothing at all)
  // they don't return anything, so the "=" is omitted
  
  def procedureExample() { // note that there isn't a "=" here!
  	print("I'm a happy side effect")
  }                                               //> procedureExample: ()Unit
  procedureExample                                //> I'm a happy side effect
  
  // another way is to set the return type to Unit (like void) and use a =
  
  def anotherProcedureExample(): Unit = {
  	print("I'm a happy side effect")
  }                                               //> anotherProcedureExample: ()Unit
  anotherProcedureExample                         //> I'm a happy side effect
  
  // 2.11 Lazy values
  
  // lazy values are not initialized until the first time they're used
  // this way, costly initializations are deferred
  lazy val words = scala.io.Source.fromFile("/usr/share/dict/cracklib-small").mkString
                                                  //> words: => String
  
  // 2.12 Exceptions
  
  // mostly the same like java, except that checked exceptions don't exist
  
  def root(number: Double) = {
  	if(number > 0)
  		math.sqrt(number)
  	else
  	  throw new IllegalArgumentException("Argument must be bigger than 10")
  }                                               //> root: (number: Double)Double
  
  // the return type of root is Double
  // this is because the return type of a throw == Nothing
  // the first if branch has type Double, the other Nothing, thus the return type is Double
  val squareRoot = root(16)                       //> squareRoot  : Double = 4.0
  
  // catching exceptions
  try {
    val myRoot = root(-17)
  } catch {
    // note the "_", we use it when we don't care about the value
  	case _: IllegalArgumentException => println("Can't be negative")
  	case ex: IOException => ex.printStackTrace()
  }                                               //> Can't be negative
  
  // ARM / try/finally
  try {
    // ...
  } finally {
    // ...
  }
  
  try {
    val myRoot = root(-17)
  } catch {
  	case _: IllegalArgumentException => println("Can't be negative")
  	case ex: IOException => ex.printStackTrace()
  } finally {
    // ... cleanup
  }                                               //> Can't be negative
  
  /*** Exercices ***/
  
  // 1. The signum of a number is 1 if the number is positive, –1 if it is negative, and 0
  // if it is zero. Write a function that computes this value.
  def signum(num: Int) = {
    if(num == 0) 0
    else if(num < 0) -1
    else 1
  }                                               //> signum: (num: Int)Int
  
  signum(0)                                       //> res22: Int = 0
  signum(-20)                                     //> res23: Int = -1
  signum(63)                                      //> res24: Int = 1
  
  // 2. What is the value of an empty block expression {} ? What is its type?
  val v = {}                                      //> v  : Unit = ()
  // the value is (), it type is Unit
  
  // 3. Come up with one situation where the assignment x = y = 1 is valid in Scala.
  // (Hint: Pick a suitable type for x .)
  
  // Remember that (val y = 1) will return (): Unit
  // using vars because we can't reassign vals
  var x: Unit = ()                                //> x  : Unit = ()
  var y = 0                                       //> y  : Int = 0
  x = y = 1
  x.toString                                      //> res25: String = ()
  y                                               //> res26: Int = 1
  
  // 4. Write a Scala equivalent for the Java loop
  // for (int i = 10; i >= 0; i--) System.out.println(i);
  
  // remember definitions? (intermediary values)
  for(i <- 0 to 10; j = 10 - i)
  	println(j)                                //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
  
  // 5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.
  def countdown(n: Int) {
  	for(i <- 0 to n; j = n - i)
  	  print(j + " ")
  }                                               //> countdown: (n: Int)Unit
  
  countdown(5)                                    //> 5 4 3 2 1 0 
  countdown(12)                                   //> 12 11 10 9 8 7 6 5 4 3 2 1 0 
  
  // 6. Write a for loop for computing the product of the Unicode codes of all letters
  // in a string. For example, the product of the characters in "Hello" is 9415087488L .
  def unicodeProduct(input: String): Long = {
    var result: Long = 1
    for(c <- input) result *= c
    result
  }                                               //> unicodeProduct: (input: String)Long
  
  unicodeProduct("Hello")                         //> res27: Long = 9415087488
  
  // 7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps
  // Scaladoc.)
  
  //"Hello".reduce( (acc: Int, n: Int) => { acc * n } )
  var result: Long = 1                            //> result  : Long = 1
  "Hello".foreach( (n) => result *= n )
  result                                          //> res28: Long = 9415087488
  
  //8. Write a function product(s : String) that computes the product, as described
  // in the preceding exercises.
  def product(input: String): Long = {
    var result: Long = 1
    input.foreach( (n) => result *= n )
    input.foreach( (n) => print(" Numeric: " + n.toInt) )
    result
  }                                               //> product: (input: String)Long
  
  product("Hello")                                //>  Numeric: 72 Numeric: 101 Numeric: 108 Numeric: 108 Numeric: 111res29: Long
                                                  //|  = 9415087488
  
  // 9. Make the function of the preceding exercise a recursive function.
  def recursiveProduct(input: String): Long = {
  	input.head * (if(input.length == 1) 1 else recursiveProduct(input.tail))
  }                                               //> recursiveProduct: (input: String)Long
  
  recursiveProduct("Hello")                       //> res30: Long = 9415087488
  
  // 10. Write a function that computes x n , where n is an integer. Use the following
  // recursive definition:
  // • x^n = y^2 if n is even and positive, where y = x^(n/2)
  // • x^n = x· x^(n-1) if n is odd and positive.
  // • x^0 = 1
  // • x^n = 1/(x^-n) if n is negative.
  // Don’t use a return statement.
  def power(x: Double, n: Int): Double = {
    if(n < 0) 1 / power(x, -n)
    else if(n == 0) 1
    else //x * power(x, n - 1)
      if(n % 2 == 0) power(x*x, n/2) // n is even and positive
      else x * power(x, n - 1)
      
  }                                               //> power: (x: Double, n: Int)Double
  
  power(2, 4)                                     //> res31: Double = 16.0
  math.pow(2, 4)                                  //> res32: Double = 16.0
  
  power(-2, 5)                                    //> res33: Double = -32.0
  math.pow(-2, 5)                                 //> res34: Double = -32.0
  
  
  
  print("That's all, folks!")

}