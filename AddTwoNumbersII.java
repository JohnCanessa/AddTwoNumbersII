import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 
 */
public class AddTwoNumbersII {


    /*
    * Definition for singly-linked list.
    *
    * !!! NOT PART OF THE SOLUTION !!!
    */
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int x) { val = x; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        @Override
        public String toString() {
            return "" + this.val;
        }
    }


    /**
     * Add the two numbers and return the sum as a linked list.
     * 
     * Using 2 stacks.
     * 
     * 1563 / 1563 test cases passed.
     * Status: Accepted
     * Runtime: 3 ms
     * Memory Usage: 39.4 MB
     */
    static public ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
        
        // **** sanity check(s) ****
        if (l1.val == 0 && l2.val == 0) return new ListNode(0);

        // **** initialization ****
        Stack<Integer> s1   = new Stack<>();
        Stack<Integer> s2   = new Stack<>();
        ListNode ll         = null;
        int carry           = 0;
        
        // **** push l1 into s1 - O(n) ****
        for (ListNode p = l1; p != null; p = p.next)
            s1.push(p.val);

        // **** push l2 into s2 - O(n) ****
        for (ListNode p = l2; p != null; p = p.next)
            s2.push(p.val);

        // **** loop adding ditigs from the stacks and populating linked list - O(n) ****
        while (!s1.isEmpty() || !s2.isEmpty()) {

            // **** compute sum ****
            int sum = carry;
            if (!s1.isEmpty()) sum += s1.pop();
            if (!s2.isEmpty()) sum += s2.pop();
            
            // **** add digit to linked list ****
            ll = new ListNode(sum % 10, ll);

            // **** update carry ****
            carry = sum / 10;
        }

        // **** take care of carry - O(1) ****
        if (carry != 0) ll = new ListNode(carry, ll);

        // **** return linked list sum ****
        return ll;
    }


    /**
     * Add the two numbers and return the sum as a linked list.
     * 
     * 1563 / 1563 test cases passed.
     * Status: Accepted
     * Runtime: 2 ms
     * Memory Usage: 39.6 MB
     */
    static public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // **** sanity check(s) ****
        if (l1.val == 0 && l2.val == 0) return new ListNode(0);

        // **** initialization ****
        ListNode ll = null;
        int carry   = 0;

        // **** reverse l1 - O(n) ****
        l1 = reverse(l1);

        // **** reverse l2 - O(n) ****
        l2 = reverse(l2);

        // **** traverse both lists - O(n)****
        while (l1 != null || l2 != null) {

            // **** for ease of use ****
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;

            // **** sum carry and digits ****
            int sum = carry + a + b;

            // **** add digit to linked list ****
            ll = new ListNode(sum % 10, ll);

            // **** compute carry ****
            carry = sum / 10;

            // **** update list references ****
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        // **** add carry to linked list (if needed) ****
        if (carry != 0) ll = new ListNode(carry, ll);

        // **** return sum linked list ****
        return ll;
    }

    
    /**
     * Reverse linked list.
     * Iterative call.
     * Auxiliary function.
     */
    private static ListNode reverse(ListNode ll) {

        // **** initialization ****
        ListNode next = null;
        ListNode prev = null;
        ListNode curr = ll;

        // **** traverse the linked list - O(n) ****
        while (curr != null) {

            // **** point to next node in the list ****
            next = curr.next;

            // **** point to previous node in the list ****
            curr.next = prev;

            // **** update previous ****
            prev = curr;

            // **** move to the next node in the list ****
            curr = next;
        }

        // **** new head of linked list ****
        return ll = prev;
    }


    /**
     * Dump contents of linked list.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    private static String dump(ListNode ll) {

        // **** initialization ****
        StringBuilder sb = new StringBuilder();

        // **** populate string builder ****
        for (ListNode p = ll; p != null; p = p.next) {
            sb.append(p.val);
            if (p.next != null)
                sb.append(",");
        }

        // **** return string ****
        return sb.toString();
    }


    /**
     * Test scaffold.
     * 
     * !!! NOT PART OF SOLUTION !!!
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        // **** read node values for l1 ****
        int[] arr1 = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
    
        // **** read node values for l2 ****
        int[] arr2 = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
    
        // **** ****
        br.close();
    
        // ???? ????
        System.out.println("main <<< arr1: " + Arrays.toString(arr1));

        // **** initialization ****
        ListNode l1     = null;
        ListNode l2     = null;
        ListNode sum    = null;

        ListNode tail   = null;
    
        // **** populate l1 ****
        for (int val : arr1) {
    
            // **** create new node ****
            ListNode n = new ListNode(val);
    
            // **** add node to linked list ****
            if (l1 == null) {
                l1 = n;
                tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
        }
    
        // ???? ????
        System.out.println("main <<< l1: " + dump(l1));

        // **** populate l2 ****
        for (int val : arr2) {
        
            // **** create new node ****
            ListNode n = new ListNode(val);

            // **** add node to linked list ****
            if (l2 == null) {
                l2 = n;
                tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
        }

        // ???? ????
        System.out.println("main <<< l2: " + dump(l2));

        // **** call the function of interest ****
        // sum = addTwoNumbers0(l1, l2);
        sum = addTwoNumbers(l1, l2);
    
        // **** display output ****
        System.out.println("main <<< output: " + dump(sum));
    }
}