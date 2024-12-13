public class ReverseLinkedList {
    static  class Node{
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public Node insertFront(int data,Node head) {
        Node newnode = new Node(data);
        if(head == null) {
            head = newnode;
            return head;
        }
        newnode.next = head;
        head = newnode;
        return head;
    }
    public void printList(Node head) {
        if(head == null) {
            System.out.println("no list..!");
            return;
        }
        Node temp = head;
        while(temp!=null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public Node reverse(Node head) {
     if(head == null || head.next == null) {
         return head;
     }
     Node prevNode = head;
     Node currNode = head.next;
     while(currNode != null) {
         Node nextNode = currNode.next;
         currNode.next = prevNode;
         prevNode = currNode;
         currNode = nextNode;
     }
     head.next = null;
     head = prevNode;
     return head;
    }
    public Node reverseReccursive(Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        Node newNode = reverseReccursive(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
    public static void main(String []args) {
        ReverseLinkedList r = new ReverseLinkedList();
        Node head = null;
        head = r.insertFront(1,head);
        head = r.insertFront(2,head);
        head = r.insertFront(3,head);
        head = r.insertFront(4,head);
        System.out.println("before reversing : ");
        r.printList(head);
        head = r.reverse(head);
        System.out.println("after reversing iteratively : ");
        r.printList(head);
        head = r.reverseReccursive(head);
        System.out.println("after reversing recurrsively : ");
        r.printList(head);
    }

}
