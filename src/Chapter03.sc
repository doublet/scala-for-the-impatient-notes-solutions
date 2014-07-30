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
  for(i <- 1 to 100) yield
  	if(i % 3 == 0 && i % 5 == 0) "FizzBuzz"
  	else if(i % 3 == 0) "Fizz"
  	else if(i % 5 == 0) "Buzz"
  	else i                                    //> res15: scala.collection.immutable.IndexedSeq[Any] = Vector(1, 2, Fizz, 4, B
                                                  //| uzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, 
                                                  //| Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, FizzBuzz, 31, 32, Fizz, 3
                                                  //| 4, Buzz, Fizz, 37, 38, Fizz, Buzz, 41, Fizz, 43, 44, FizzBuzz, 46, 47, Fizz
                                                  //| , 49, Buzz, Fizz, 52, 53, Fizz, Buzz, 56, Fizz, 58, 59, FizzBuzz, 61, 62, F
                                                  //| izz, 64, Buzz, Fizz, 67, 68, Fizz, Buzz, 71, Fizz, 73, 74, FizzBuzz, 76, 77
                                                  //| , Fizz, 79, Buzz, Fizz, 82, 83, Fizz, Buzz, 86, Fizz, 88, 89, FizzBuzz, 91,
                                                  //|  92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, Buzz)
  // 3.4 Transforming Arrays
  
  val a = Array(1,2,3,4,5,6)                      //> a  : Array[Int] = Array(1, 2, 3, 4, 5, 6)
  // transforming using a for comprehension
  for(elem <- a) yield elem * elem                //> res16: Array[Int] = Array(1, 4, 9, 16, 25, 36)
  
  // guards
  for(elem <- a if elem % 2 != 0) yield elem * 2  //> res17: Array[Int] = Array(2, 6, 10)
  
  // 3.5 Common algorithms
  
  // sums
  Array(1,2,3,4,5).sum                            //> res18: Int = 15
  // min/max
  val array = Array(555,654,11,9001,666)          //> array  : Array[Int] = Array(555, 654, 11, 9001, 666)
  array.min                                       //> res19: Int = 11
  array.max                                       //> res20: Int = 9001
  // sort
  array.sorted                                    //> res21: Array[Int] = Array(11, 555, 654, 666, 9001)
  array.sortWith(_ > _)                           //> res22: Array[Int] = Array(9001, 666, 654, 555, 11)
  // in-place sorting
  scala.util.Sorting.quickSort(array)
  
  // gluing strings
  array.mkString(" and also ")                    //> res23: String = 11 and also 555 and also 654 and also 666 and also 9001
  array.mkString("It all starts with ", ", and then ", ". The end.")
                                                  //> res24: String = It all starts with 11, and then 555, and then 654, and then
                                                  //|  666, and then 9001. The end.
  // 3.6 Deciphering ScalaDoc
  
  // 3.7 Multidimensional arrays
  val matrix = Array.ofDim[Int](3, 4)             //> matrix  : Array[Array[Int]] = Array(Array(0, 0, 0, 0), Array(0, 0, 0, 0), A
                                                  //| rray(0, 0, 0, 0))
  matrix(2)(2) = 22
  matrix                                          //> res25: Array[Array[Int]] = Array(Array(0, 0, 0, 0), Array(0, 0, 0, 0), Arra
                                                  //| y(0, 0, 22, 0))
  // 3.8 Interoperating with Java
  
  // Scala arrays are implemented as Java arrays
  // implicit conversion options are in scala.collection.JavaConversions
  
  // most conversions are automatic
    
  /*** EXERCICES ***/
  
	// 1. Write a code snippet that sets a to an array of n random integers between 0
	// (inclusive) and n (exclusive).
	
	def someRandomIntegers(n: Int): Array[Int] = {
		val ret = new Array[Int](n)
		for(i <- 0 until n)
			ret(i) = util.Random.nextInt(n)
		
		ret
	}                                         //> someRandomIntegers: (n: Int)Array[Int]
	
	someRandomIntegers(5)                     //> res26: Array[Int] = Array(0, 3, 0, 2, 2)
	someRandomIntegers(6)                     //> res27: Array[Int] = Array(0, 0, 3, 2, 5, 5)
	
	// 2. Write a loop that swaps adjacent elements of an array of integers. For example,
	// Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5) .
	
	var toSwap = Array[Int](1,2,3,4,5)        //> toSwap  : Array[Int] = Array(1, 2, 3, 4, 5)
	for(i <- 0 to toSwap.length if i % 2 ==  0 && i < toSwap.length-1 ) {
		val tmp = toSwap(i+1)
		toSwap(i+1) = toSwap(i)
		toSwap(i) = tmp
	}
	toSwap                                    //> res28: Array[Int] = Array(2, 1, 4, 3, 5)
	
	// 3. Repeat the preceding assignment, but produce a new array with the swapped
	// values. Use for / yield .
	
	toSwap = Array[Int](1,2,3,4,5)
	for(i <- 0 until toSwap.length) yield {
	  if (i % 2 ==  0 && i < toSwap.length - 1) toSwap(i + 1) // the next element
		else if (i % 2 !=  0) toSwap(i - 1) // the previous element
		else toSwap(i) // happens on the last element of an array with an odd size
                                                  //> res29: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 1, 4, 3, 5)
	}
	
	// 4. Given an array of integers, produce a new array that contains all positive
	// values of the original array, in their original order, followed by all values that
	// are zero or negative, in their original order.
	
	def sortPositiveNegative(arr: Array[Int]): Array[Int] = arr.filter(p => p > 0) ++ arr.filter(p => p <= 0)
                                                  //> sortPositiveNegative: (arr: Array[Int])Array[Int]
	// first get all positive values, then all negative (the <= is a comparison operator)
	
	sortPositiveNegative(Array(1,-2,3,-4,-5,0,7,0,-7))
                                                  //> res30: Array[Int] = Array(1, 3, 7, -2, -4, -5, 0, 0, -7)
	
	// 5. How do you compute the average of an Array[Double] ?
	
	def average(arr: Array[Double]): Double = arr.sum / arr.length
                                                  //> average: (arr: Array[Double])Double
	
	average(Array(1.0,2.5,3.0))               //> res31: Double = 2.1666666666666665
	
	// 6. How do you rearrange the elements of an Array[Int] so that they appear in
	// reverse sorted order? How do you do the same with an ArrayBuffer[Int] ?
	
	Array[Int](1,5,2,3,4).sorted.reverse      //> res32: Array[Int] = Array(5, 4, 3, 2, 1)
	ArrayBuffer[Int](1,5,2,3,4).sorted.reverse//> res33: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(5, 4, 3, 2, 
                                                  //| 1)
	
	// 7. Write a code snippet that produces all values from an array with duplicates
	// removed. (Hint: Look at Scaladoc.)
	
	Array("Me","Myself", "Me", "I").distinct  //> res34: Array[String] = Array(Me, Myself, I)
	
	// 8. Rewrite the example at the end of Section 3.4, “Transforming Arrays,” on
	// page 32. Collect indexes of the negative elements, reverse the sequence, drop
	// the last index, and call a.remove(i) for each index. Compare the efficiency of
	// this approach with the two approaches in Section 3.4.
	
	
	
	// 9. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs
	// that are in America. Strip off the "America/" prefix and sort the result.
	
	// get all the time zones
	val zones = java.util.TimeZone.getAvailableIDs
                                                  //> zones  : Array[String] = Array(Etc/GMT+12, Etc/GMT+11, Pacific/Midway, Paci
                                                  //| fic/Niue, Pacific/Pago_Pago, Pacific/Samoa, US/Samoa, America/Adak, America
                                                  //| /Atka, Etc/GMT+10, HST, Pacific/Honolulu, Pacific/Johnston, Pacific/Raroton
                                                  //| ga, Pacific/Tahiti, SystemV/HST10, US/Aleutian, US/Hawaii, Pacific/Marquesa
                                                  //| s, AST, America/Anchorage, America/Juneau, America/Nome, America/Sitka, Ame
                                                  //| rica/Yakutat, Etc/GMT+9, Pacific/Gambier, SystemV/YST9, SystemV/YST9YDT, US
                                                  //| /Alaska, America/Dawson, America/Ensenada, America/Los_Angeles, America/Met
                                                  //| lakatla, America/Santa_Isabel, America/Tijuana, America/Vancouver, America/
                                                  //| Whitehorse, Canada/Pacific, Canada/Yukon, Etc/GMT+8, Mexico/BajaNorte, PST,
                                                  //|  PST8PDT, Pacific/Pitcairn, SystemV/PST8, SystemV/PST8PDT, US/Pacific, US/P
                                                  //| acific-New, America/Boise, America/Cambridge_Bay, America/Chihuahua, Americ
                                                  //| a/Creston, America/Dawson_Creek, America/Denver, America/Edmonton, America/
                                                  //| Hermosillo, America/Inu
                                                  //| Output exceeds cutoff limit.
	// for each zone, given as a string, split at "/" and take the last part
	val split = for(zone <- zones) yield zone.split("/").last
                                                  //> split  : Array[String] = Array(GMT+12, GMT+11, Midway, Niue, Pago_Pago, Sam
                                                  //| oa, Samoa, Adak, Atka, GMT+10, HST, Honolulu, Johnston, Rarotonga, Tahiti, 
                                                  //| HST10, Aleutian, Hawaii, Marquesas, AST, Anchorage, Juneau, Nome, Sitka, Ya
                                                  //| kutat, GMT+9, Gambier, YST9, YST9YDT, Alaska, Dawson, Ensenada, Los_Angeles
                                                  //| , Metlakatla, Santa_Isabel, Tijuana, Vancouver, Whitehorse, Pacific, Yukon,
                                                  //|  GMT+8, BajaNorte, PST, PST8PDT, Pitcairn, PST8, PST8PDT, Pacific, Pacific-
                                                  //| New, Boise, Cambridge_Bay, Chihuahua, Creston, Dawson_Creek, Denver, Edmont
                                                  //| on, Hermosillo, Inuvik, Mazatlan, Ojinaga, Phoenix, Shiprock, Yellowknife, 
                                                  //| Mountain, GMT+7, MST, MST7MDT, BajaSur, Navajo, PNT, MST7, MST7MDT, Arizona
                                                  //| , Mountain, Bahia_Banderas, Belize, Cancun, Chicago, Costa_Rica, El_Salvado
                                                  //| r, Guatemala, Knox, Tell_City, Knox_IN, Managua, Matamoros, Menominee, Meri
                                                  //| da, Mexico_City, Monterrey, Beulah, Center, New_Salem, Rainy_River, Rankin_
                                                  //| Inlet, Regina, Resolute
                                                  //| Output exceeds cutoff limit.
  // return a new, sorted array
  split.sorted                                    //> res35: Array[String] = Array(ACT, ACT, AET, AGT, ART, AST, AST4, AST4ADT, A
                                                  //| bidjan, Accra, Acre, Adak, Addis_Ababa, Adelaide, Aden, Alaska, Aleutian, A
                                                  //| lgiers, Almaty, Amman, Amsterdam, Anadyr, Anchorage, Andorra, Anguilla, Ant
                                                  //| ananarivo, Antigua, Apia, Aqtau, Aqtobe, Araguaina, Arizona, Aruba, Ashgaba
                                                  //| t, Ashkhabad, Asmara, Asmera, Asuncion, Athens, Atikokan, Atka, Atlantic, A
                                                  //| uckland, Azores, BET, BST, Baghdad, Bahia, Bahia_Banderas, Bahrain, BajaNor
                                                  //| te, BajaSur, Baku, Bamako, Bangkok, Bangui, Banjul, Barbados, Beirut, Belem
                                                  //| , Belfast, Belgrade, Belize, Berlin, Bermuda, Beulah, Bishkek, Bissau, Blan
                                                  //| c-Sablon, Blantyre, Boa_Vista, Bogota, Boise, Bratislava, Brazzaville, Bris
                                                  //| bane, Broken_Hill, Brunei, Brussels, Bucharest, Budapest, Buenos_Aires, Bue
                                                  //| nos_Aires, Bujumbura, Busingen, CAT, CET, CNT, CST, CST6, CST6CDT, CST6CDT,
                                                  //|  CTT, Cairo, Calcutta, Cambridge_Bay, Campo_Grande, Canary, Canberra, Cancu
                                                  //| n, Cape_Verde, Caracas,
                                                  //| Output exceeds cutoff limit.
	
	// 10. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with
	// the call
	// val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
	// Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
	// and get the return value as a Scala buffer. (Why this obscure class? It’s hard
	// to find uses of java.util.List in the standard Java library.)
	
	import java.awt.datatransfer._
  val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
                                                  //> flavors  : java.awt.datatransfer.SystemFlavorMap = java.awt.datatransfer.Sy
                                                  //| stemFlavorMap@42545658
  // use collection.JavaConversions to convert Java Lists
  val toUse = collection.JavaConversions.asScalaBuffer(flavors.getNativesForFlavor(DataFlavor.imageFlavor))
                                                  //> toUse  : scala.collection.mutable.Buffer[String] = Buffer(image/jpeg, image
                                                  //| /png, image/x-png, image/gif, PNG, JFIF)
  
  print("That's all folks!")
}