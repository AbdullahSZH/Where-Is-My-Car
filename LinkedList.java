package StudentCode;

public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> current;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
        current = null;
    }

    @Override
    public boolean empty() {
        return head == null;
    }

    @Override
    public boolean full() {
        // A linked list implementation is typically not limited by capacity,
        // so it is assumed to never be full.
        return false;
    }

    @Override
    public void findFirst() {
        current = head;
    }

    @Override
    public void findNext() {
        if (current != null) {
            current = current.next;
        }
    }

    @Override
    public boolean last() {
        return current == null;
    }

    @Override
    public T retrieve() {
        if (current != null) {
            return current.data;
        }
        return null;
    }

    @Override
    public void update(T e) {
        if (current != null) {
            current.data = e;
        }
    }

    @Override
    public void insert(T e) {
        Node<T> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            current = newNode;
        } else {
            newNode.next = current.next;
            current.next = newNode;
            current = newNode;
        }
    }

    @Override
    public void remove() {
        if (current != null) {
            if (current == head) {
                head = head.next;
                current = head;
            } else {
                Node<T> temp = head;
                while (temp != null && temp.next != current) {
                    temp = temp.next;
                }
                if (temp != null) {
                    temp.next = current.next;
                    current = temp;
                }
            }
        }
    }
}