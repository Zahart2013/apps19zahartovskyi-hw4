package ua.edu.ucu.utils;

public class ImmutableLinkedList {
    private Node head;

    public ImmutableLinkedList() {
        this.head = new Node();
    }

    public Object get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node current_node = this.head;
        for (int i = 0; i < index; i++) {
            if (current_node.next == null) {
                throw new IndexOutOfBoundsException();
            }
            current_node = current_node.next;
        }
        return current_node.value;
    }

    public int size() {
        Node current_node = this.head;
        if (current_node.value == null) {
            return 0;
        }
        int size = 1;
        while (current_node.next != null) {
            size++;
            current_node = current_node.next;
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node current_node = this.head;
        str.append(current_node.value.toString());
        current_node = current_node.next;
        while (current_node.next != null) {
            str.append(",").append(current_node.value.toString());
            current_node = current_node.next;
        }
        str.append(",").append(current_node.value.toString());
        return str.toString();
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList result = new ImmutableLinkedList();
        if (this.head.value != null) {
            Node current_node = this.head;
            Node new_node = result.head;
            new_node.value = this.head.value;
            new_node = this.copyNodes(current_node, new_node);
            new_node.next = new Node(e);
        } else {
            result.head.value = e;
        }
        return result;
    }

    public Object getFirst() {
        return this.head.value;
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList result = new ImmutableLinkedList();
        if (this.head.next == null) {
            return result;
        }
        Node current_node = this.head.next;
        Node new_node = new Node(current_node.value);
        result.head = new_node;
        this.copyNodes(current_node, new_node);
        return result;
    }

    private static class Node {
        private Object value;
        private Node next;

        private Node(Object value) {
            this.value = value;
            this.next = null;
        }

        private Node() {
            this.value = null;
            this.next = null;
        }
    }

    private Node copyNodes(Node current_node, Node new_node) {
        while (current_node.next != null) {
            current_node = current_node.next;
            new_node.next = new Node(current_node.value);
            new_node = new_node.next;
        }
        return new_node;
    }
}

