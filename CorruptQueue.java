//Name: Dubem Nnake, SPCA: THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DUBEM NNAKE
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class CorruptQue<Item> implements Iterable<Item>
{
    // Helpful Linked List for storing the queue
    private class Node {
        public Node next, prev;
        public Item item;

        public Node(Item it) {
            this.prev = null;
            this.next = null;
            this.item = it;
        }

        // Instantiate a node while setting both its prev and next pointers
        public Node(Item it, Node prev, Node next) {
            this.prev = prev;
            this.next = next;
            this.item = it;
        }
    }

    private int N; // Number of items in the queue
    private Node head, tail; // Back and front of the corrupt queue, respectively

    public CorruptQue() {
        this.N = 0;
        this.head = null;
        this.tail = null;
    }

    // return the number of items
    public int size() {
        return N;
    }

    // true if empty, false otherwise
    public boolean isEmpty() {
        return size() == 0;
    }

    // add Item x to the back of this queue
    public void enqueue(Item x) {
    Node newnode = new Node(x);
        // FILL ME IN
        if(isEmpty()){
        tail = newnode;
        head = newnode;
        N++;
        return;
        }

        head.prev = newnode;
        head = head.prev;
        //head.next = newnode;
        //head = newnode;
        this.N++;


    }

    // barge into the line, adding Item x to the second place from the front (or the front if they're alone)
    public void cut(Item x) {

        // FILL ME IN
        if(tail == null){
        //Node newnode = new Node(x, null, null);
        enqueue(x);
       // head = newnode;
        //tail = newnode;
        return;
        }
        Node newnode = tail.prev;

        //tail.prev = new Node(x, newnode, tail);
        if(newnode == null){
        tail.prev = new Node(x, null, tail);
        head = tail.prev;
        //newnode.next = tail.prev;
        }else{
        tail.prev = new Node(x, newnode, tail);
        newnode.next = tail.prev;
        }
        //newnode.next = newnode;
        //newnode.next = tail.next.next;
       // newnode.prev = tail;
        this.N++;


    }

    // return item removed from the front (end) of the queue
    public Item dequeue() throws NoSuchElementException {

    Item y;
        if (isEmpty() == true){
            throw new NoSuchElementException();
            }

            y = tail.item;
            if(tail.prev == null){
            head = tail = null;
            }else{
            tail = tail.prev;
            tail.next = null;
            }

         this.N--;
         return y;
       }


    // internal iterator implementation
    public class Iter implements Iterator<Item> {
        private Node where;

        public Iter() {
            where = tail; // Assumes tail has the front of the queue. You can turn this around if you desire.
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item it = where.item;
            where = where.prev;
            return it;
        }

        public boolean hasNext() {
            return (where != null);
        }

    }

    // teturn Iterator as required by Iterable (from front to back).
    public Iterator<Item> iterator() {
        return new Iter();
    }

    // print contents of queue from front to back
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item it : this) {
            s.append (it.toString() + " ");
        }
        s.append ("\n"); // newline
        return s.toString();
    }

    // this method is used by HackerRank to read in operations
    public void process(char op, Item val) {
        if (op == 'e') // enqueue
           enqueue(val);
        else if (op == 'c') // cut
           cut(val);
        else if (op == 'd') // dequeue
           System.out.println (dequeue()); // ignore val
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CorruptQue<Integer> cq = new CorruptQue<>();

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, n).forEach(nItr -> {
            try {
                char o = (char)bufferedReader.read();
                int k = 0;
                if (o != 'd') { // the enqueue operations 'e' and 'c' both take an argument
                    bufferedReader.skip(1); // eat the space
                    k = Integer.parseInt(bufferedReader.readLine().trim());
                } else {
                    bufferedReader.readLine();
                }
                cq.process(o, k);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
