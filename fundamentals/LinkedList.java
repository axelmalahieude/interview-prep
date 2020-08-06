public class LinkedList<K, V> {
    public static void main(String[] args) {
        LinkedList<Integer, String> list = new LinkedList<>();
        list.add(1, "one");
        list.add(2, "two");
        list.add(3, "three");
        list.add(4, "four");
        list.add(5, "five");
        list.add(6, "six");
        list.remove(4);
        list.remove(1);
        list.remove(6);
        list.remove(7);
        list.remove(2);
        list.print();
        System.out.println(list.get(5));
    }

    private Node<K, V> head;
    public LinkedList() {
        head = null;
    }

    public void add(K key, V value) {
        if (head == null) {
            head = new Node<>(key, value);
            return;
        }
        Node<K, V> lastNode = findLastNode();
        Node<K, V> newNode = new Node<>(key, value);
        lastNode.next = newNode;
    }

    public V get(K key) {
        Node<K, V> current = head;

        while (current != null && current.key != key) {
            current = current.next;
        }

        if (current == null) {
            return null;
        } else {
            return current.value;
        }
    }

    public void remove(K key) {
        if (head == null) {
            return;
        }

        Node<K, V> previous = null;
        Node<K, V> current = head;
        while (current != null && current.key != key) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return;
        } else if (current == head) {
            head = current.next;
        } else {
            previous.next = current.next;
        }
    }

    public void print() {
        Node<K, V> current = head;
        while (current != null) {
            System.out.println(current.key + " - " + current.value);
            current = current.next;
        }
    }

    private Node<K, V> findLastNode() {
        Node<K, V> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}