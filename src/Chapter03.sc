object Chapter03 {
  
  /*** NOTES ***/
  
  // 3.1 Fixed-length arrays
  
  // use the Array type
  // val myArray = new Array[type](size)
  // initialized to zero
  val arrayOfInts = new Array[Int](20)            //> arrayOfInts  : Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
                                                  //| 0, 0, 0, 0, 0, 0)
  
  var arrayOfLongs = new Array[Long](20)          //> arrayOfLongs  : Array[Long] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                                                  //| , 0, 0, 0, 0, 0, 0)
  arrayOfLongs = Array(10,20,30,40,50,60,70)
  arrayOfLongs                                    //> res0: Array[Long] = Array(10, 20, 30, 40, 50, 60, 70)
  arrayOfLongs(2)                                 //> res1: Long = 30
  
  // type is inferred
  val arrayOfStrings = Array("first", "second")   //> arrayOfStrings  : Array[String] = Array(first, second)
  
  // 3.2 Variable-length arrays: ArrayBuffers
  
  import scala.collection.mutable.ArrayBuffer
  
  // constructing a new ArrayBuffer
  val arrayBuffer1 = new ArrayBuffer[Int]()       //> arrayBuffer1  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  val arrayBuffer2 = new ArrayBuffer[Int]         //> arrayBuffer2  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  
  // add a single element
  arrayBuffer1 += 2                               //> res2: Chapter03.arrayBuffer1.type = ArrayBuffer(2)
  arrayBuffer1                                    //> res3: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2)
  // add a collection of elements
  arrayBuffer1 ++= Array(4,6,8,20,400,2000)       //> res4: Chapter03.arrayBuffer1.type = ArrayBuffer(2, 4, 6, 8, 20, 400, 2000)
  arrayBuffer1                                    //> res5: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 4, 6, 8, 20
                                                  //| , 400, 2000)
  // remove last 2 elements
  arrayBuffer1.trimEnd(2)
  arrayBuffer1                                    //> res6: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 4, 6, 8, 20
                                                  //| )
  
  // insert in the middle, before index 2 in this case
  arrayBuffer1.insert(2, 66)
  arrayBuffer1.insert(2, 666, 777, 888)
  arrayBuffer1                                    //> res7: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 4, 666, 777
                                                  //| , 888, 66, 6, 8, 20)
  // remove in the middle, remove index 2 in this case
  arrayBuffer1.remove(2)                          //> res8: Int = 666
  arrayBuffer1                                    //> res9: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 4, 777, 88
                                                  //| 8, 66, 6, 8, 20)
  // remove multiple elements
  arrayBuffer1.remove(2, 3)
  arrayBuffer1                                    //> res10: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(2, 4, 6, 8, 
                                                  //| 20)
  // ArrayBuffer to Array
  arrayBuffer1.toArray                            //> res11: Array[Int] = Array(2, 4, 6, 8, 20)
  // Array to ArrayBuffer
  arrayOfInts.toBuffer                            //> res12: scala.collection.mutable.Buffer[Int] = ArrayBuffer(0, 0, 0, 0, 0, 0,
                                                  //|  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
  
  // 3.3 Traversing Arrays and ArrayBuffers
  
  for(i <- 0 until arrayOfLongs.length)
    println(i + ": " + arrayOfLongs (i))          //> 0: 10
                                                  //| 1: 20
                                                  //| 2: 30
                                                  //| 3: 40
                                                  //| 4: 50
                                                  //| 5: 60
                                                  //| 6: 70
  // every second element
  for(i <- 0 until (arrayOfLongs.length, 2))
    println(i + ": " + arrayOfLongs(i))           //> 0: 10
                                                  //| 2: 30
                                                  //| 4: 50
                                                  //| 6: 70
  // ranges can be reversed
  0 to 10                                         //> res13: scala.collection.immutable.Range.Inclusive = Range(0, 1, 2, 3, 4, 5,
                                                  //|  6, 7, 8, 9, 10)
  (0 to 10).reverse                               //> res14: scala.collection.immutable.Range = Range(10, 9, 8, 7, 6, 5, 4, 3, 2,
                                                  //|  1, 0)
  // count down
  for(i <- (0 until arrayOfLongs.length).reverse)
    println(i + ": " + arrayOfLongs(i))           //> 6: 70
                                                  //| 5: 60
                                                  //| 4: 50
                                                  //| 3: 40
                                                  //| 2: 30
                                                  //| 1: 20
                                                  //| 0: 10
  // for-each loop in Java equivalent
  // note that you don't have an index variable
  for(elem <- arrayOfLongs)
    println(elem)                                 //> 10
                                                  //| 20
                                                  //| 30
                                                  //| 40
                                                  //| 50
                                                  //| 60
                                                  //| 70
    
  // ...but you can zip with the index
  for(zipped <- arrayOfLongs.zipWithIndex) {
  	val index = zipped._2
  	val value = zipped._1
  	println(index + ": " + value)
  }                                               //> 0: 10
                                                  //| 1: 20
                                                  //| 2: 30
                                                  //| 3: 40
                                                  //| 4: 50
                                                  //| 5: 60
                                                  //| 6: 70
  // FizzBuzz
  for(i <- 1 to 100)
  	print (
  		(if(i % 3 == 0 && i % 5 == 0) "FizzBuzz"
  		else if(i % 3 == 0) "Fizz"
  		else if(i % 5 == 0) "Buzz"
  		else i) + " "
  	)                                         //> 1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 Buz
                                                  //| z Fizz 22 23 Fizz Buzz 26 Fizz 28 29 FizzBuzz 31 32 Fizz 34 Buzz Fizz 37 38
                                                  //|  Fizz Buzz 41 Fizz 43 44 FizzBuzz 46 47 Fizz 49 Buzz Fizz 52 53 Fizz Buzz 5
                                                  //| 6 Fizz 58 59 FizzBuzz 61 62 Fizz 64 Buzz Fizz 67 68 Fizz Buzz 71 Fizz 73 74
                                                  //|  FizzBuzz 76 77 Fizz 79 Buzz Fizz 82 83 Fizz Buzz 86 Fizz 88 89 FizzBuzz 91
                                                  //|  92 Fizz 94 Buzz Fizz 97 98 Fizz Buzz 
  // 3.4 Transforming Arrays
  
  val a = Array(1,2,3,4,5,6)                      //> a  : Array[Int] = Array(1, 2, 3, 4, 5, 6)
  // transforming using a for comprehension
  for(elem <- a) yield elem * elem                //> res15: Array[Int] = Array(1, 4, 9, 16, 25, 36)
  
  // guards
  for(elem <- a if elem % 2 != 0) yield elem * 2  //> res16: Array[Int] = Array(2, 6, 10)
  
  // 3.5 Common algorithms
  
  // sums
  Array(1,2,3,4,5).sum                            //> res17: Int = 15
  // min/max
  val array = Array(555,654,11,9001,666)          //> array  : Array[Int] = Array(555, 654, 11, 9001, 666)
  array.min                                       //> res18: Int = 11
  array.max                                       //> res19: Int = 9001
  // sort
  array.sorted                                    //> res20: Array[Int] = Array(11, 555, 654, 666, 9001)
  array.sortWith(_ > _)                           //> res21: Array[Int] = Array(9001, 666, 654, 555, 11)
  // in-place sorting
  scala.util.Sorting.quickSort(array)
  
  // gluing strings
  array.mkString(" and also ")                    //> res22: String = 11 and also 555 and also 654 and also 666 and also 9001
  array.mkString("It all starts with ", ", and then ", ". The end.")
                                                  //> res23: String = It all starts with 11, and then 555, and then 654, and then
                                                  //|  666, and then 9001. The end.
  // 3.6 Deciphering ScalaDoc
  
  // 3.7 Multidimensional arrays
  val matrix = Array.ofDim[Int](3, 4)             //> matrix  : Array[Array[Int]] = Array(Array(0, 0, 0, 0), Array(0, 0, 0, 0), A
                                                  //| rray(0, 0, 0, 0))
  matrix(2)(2) = 22
  matrix                                          //> res24: Array[Array[Int]] = Array(Array(0, 0, 0, 0), Array(0, 0, 0, 0), Arra
                                                  //| y(0, 0, 22, 0))
  // 3.8 Interoperating with Java
  
  // Scala arrays are implemented as Java arrays
  // implicit conversion options are in scala.collection.JavaConversions
  
  // most conversions are automatic
    
  /*** EXERCICES ***/
  
  print("That's all folks!")
}