import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** this test is to verify the correctness of isempty() method */
    public void isemptyTest() {
        Deque61B<Integer> a = new LinkedListDeque61B<>();
        assertThat(a.isEmpty()).isTrue();
        a.addFirst(5);
        assertThat(a.isEmpty()).isFalse();
    }


    @Test
    /** this test is to verify the correctness of size() method */
    public void sizeTest() {
        Deque61B<Integer> a = new LinkedListDeque61B<>();
        assertThat(a.size()).isEqualTo(0);
        a.addFirst(1);
        assertThat(a.size()).isEqualTo(1);
    }


    @Test
    /** this test is to see if get() method works correctly */
    public void getTest() {
        Deque61B<Integer> a = new LinkedListDeque61B<>();
        assertThat(a.get(0)).isEqualTo(null);
        a.addFirst(9);
        assertThat(a.get(0)).isEqualTo(9);
        assertThat(a.get(1999)).isEqualTo(null);
    }

    @Test
    /** this test is to see if getrecursive() method works correctly */
    public void getrecursiveTest() {
        Deque61B<Integer> a = new LinkedListDeque61B<>();
        assertThat(a.get(0)).isEqualTo(null);
        a.addFirst(9);
        assertThat(a.get(0)).isEqualTo(9);
        assertThat(a.get(1999)).isEqualTo(null);
    }

    @Test
    /** this test is to see if removelast() and  removefirst() behave correctly */
    public void removelastandfirstTest(){
        Deque61B<Integer> a = new LinkedListDeque61B<>();
        a.addFirst(1);
        a.addFirst(4);
        a.addLast(9);
        a.addLast(3);

        a.removeFirst();
        a.removeLast();
        assertThat(a.toList()).containsExactly(1,9).inOrder();
    }
}
