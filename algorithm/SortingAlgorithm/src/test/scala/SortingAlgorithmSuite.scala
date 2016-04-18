import org.scalatest._
import collection.mutable.stack

class SortingAlgorithmSuite extends FunSuite {
  test("An empty Set should have size 0") {
    assert(Set.empty.size == 1)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }

}
// class SortingAlgorithmSuite extends FlatSpec {

  
//   "A Stack" should "pop values in last-in-first-out order" in {
//     val stack = new Stack[Int]
//     stack.push(1)
//     stack.push(2)
//     assert(stack.pop() === 2)
//     assert(stack.pop() === 1)
//   }

//   it should "throw NoSuchElementException if an empty stack is popped" in {
//     val emptyStack = new Stack[String]
//     emptyStack.push("1")
//     intercept[NoSuchElementException] {
//       emptyStack.pop()
//     }
//   }
// }
