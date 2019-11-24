package ua.edu.ucu.utils;

public class ImmutableLinkedList {
    private Node head;

    public ImmutableLinkedList() {
        this.head = new Node();
    }

    public ImmutableLinkedList add(Object e) {
        ImmutableLinkedList result = new ImmutableLinkedList();
        if (this.head.value == null) {
            result.head.value = e;
        } else {
            Node current_node = this.head;
            result.head.value = this.head.value;
            Node new_node = result.head;
            new_node = this.copyNodes(current_node, new_node);
            new_node.next = new Node(e);
        }
        return result;
    }

    public ImmutableLinkedList add(int index, Object e) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList result = new ImmutableLinkedList();
        Node current_node = this.head;
        if (index == 0) {
            result.head.value = e;
            result.head.next = new Node(this.head.value);
            Node new_node = result.head.next;
            this.copyNodes(current_node, new_node);
        } else {
            Node new_node = result.head;
            new_node.value = this.head.value;
            for (int i = 1; i < index; i++) {
                if (current_node.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                current_node = current_node.next;
                new_node.next = new Node(current_node.value);
                new_node = new_node.next;
            }
            new_node.next = new Node(e);
            new_node = new_node.next;
            this.copyNodes(current_node, new_node);
        }
        return result;
    }


    public ImmutableLinkedList addAll(Object[] c) {
        ImmutableLinkedList result = new ImmutableLinkedList();
        if (this.head.value == null) {
            Node new_node = new Node();
            this.insertArray(c, new_node);
            result.head = new_node.next;
        } else {
            Node current_node = this.head;
            result.head.value = this.head.value;
            Node new_node = result.head;
            new_node = this.copyNodes(current_node, new_node);
            this.insertArray(c, new_node);
        }
        return result;
    }

    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList result = new ImmutableLinkedList();
        Node current_node = this.head;
        if (index == 0) {
            Node new_node = new Node();
            this.insertArray(c, new_node);
            result.head = new_node.next;
        } else {
            Node new_node = result.head;
            new_node.value = this.head.value;
            for (int i = 1; i < index; i++) {
                if (current_node.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                current_node = current_node.next;
                new_node.next = new Node(current_node.value);
                new_node = new_node.next;
            }
            new_node = this.insertArray(c, new_node);
            this.copyNodes(current_node, new_node);
        }
        return result;
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

    public ImmutableLinkedList remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node current_node = this.head;
        ImmutableLinkedList result = new ImmutableLinkedList();
        result.head.value = current_node.value;
        Node new_node = result.head;
        for (int i = 0; i < index - 1; i++) {
            if (current_node.next == null) {
                throw new IndexOutOfBoundsException();
            }
            current_node = current_node.next;
            new_node.next = new Node(current_node.value);
            new_node = new_node.next;
        }
        if (current_node.next == null) {
            throw new IndexOutOfBoundsException();
        }
        current_node = current_node.next;
        this.copyNodes(current_node, new_node);
        return result;
    }

    public ImmutableLinkedList set(int index, Object e) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node current_node = this.head;
        ImmutableLinkedList result = new ImmutableLinkedList();
        result.head.value = current_node.value;
        Node new_node = result.head;
        if (index == 0) {
            new_node.value = e;
        } else {
            for (int i = 0; i < index - 1; i++) {
                if (current_node.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                current_node = current_node.next;
                new_node.next = new Node(current_node.value);
                new_node = new_node.next;
            }
            if (current_node.next == null) {
                throw new IndexOutOfBoundsException();
            }
            current_node = current_node.next;
            new_node.next = new Node(e);
            new_node = new_node.next;
        }
        this.copyNodes(current_node, new_node);
        return result;
    }

    public int indexOf(Object e) {
        Node current_node = this.head;
        int index = 0;
        while (current_node.next != null) {
            if (current_node.value.equals(e)) {
                return index;
            }
            index++;
            current_node = current_node.next;
        }
        if (current_node.value.equals(e)) {
            return index;
        }
        return -1;
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

    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Object[] toArray() {
        if (this.head.value == null) {
            return new Object[]{};
        }
        Object[] result = new Object[this.size()];
        Node current_node = this.head;
        for (int i = 0; i < result.length; i++) {
            result[i] = current_node.value;
            current_node = current_node.next;
        }
        return result;
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

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList result = new ImmutableLinkedList();
        result.head.value = e;
        if (this.head.value != null) {
            Node current_node = this.head;
            result.head.next = new Node(current_node.value);
            Node new_node = result.head.next;
            this.copyNodes(current_node, new_node);
        }
        return result;
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

    public Object getLast() {
        Node current_node = this.head;
        while (current_node.next != null) {
            current_node = current_node.next;
        }
        return current_node.value;
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

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList result = new ImmutableLinkedList();
        if (this.head.next == null) {
            return result;
        }
        Node current_node = this.head;
        Node new_node = new Node(current_node.value);
        result.head = new_node;
        for (int i = 0; i < this.size() - 2; i++) {
            current_node = current_node.next;
            new_node.next = new Node(current_node.value);
            new_node = new_node.next;
        }
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

    private Node insertArray(Object[] c, Node new_node) {
        for (Object o : c) {
            new_node.next = new Node(o);
            new_node = new_node.next;
        }
        return new_node;
    }
}

