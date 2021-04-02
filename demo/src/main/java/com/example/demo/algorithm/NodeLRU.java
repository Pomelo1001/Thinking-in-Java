package com.example.demo.algorithm;

/**
 * @author：cp
 * @time：2021-3-17
 * @Description: 通过链表实现LRU缓存淘汰算法
 */
public class NodeLRU<T> {

    public static class Node<T> {
        private T data;
        private Node next;

        public Node(Node next) {
            this.next = next;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    //默认链表容量
    private int DEFAULT_CAPACITY = 10;
    //链表长度
    private int count;
    //链表头结点
    private Node head;
    //链表容量
    private int capacity;

    public NodeLRU() {
        this.count = 0;
        this.head = new Node(null);
        this.capacity = DEFAULT_CAPACITY;
    }

    public NodeLRU(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.head = new Node(null);
    }

    public int getDEFAULT_CAPACITY() {
        return DEFAULT_CAPACITY;
    }

    public void setDEFAULT_CAPACITY(int DEFAULT_CAPACITY) {
        this.DEFAULT_CAPACITY = DEFAULT_CAPACITY;
    }

    public Node getHead() {
        return head;
    }


    public void add(T data) {
        //找到元素的前一个结点
        Node prevNode = findPrevNode(data);
        if (prevNode != null) {
            remove(prevNode);
            insertNodeAtHead(data);
        } else {
            if (count >= capacity) {
                deleteNodeAtEnd();
            }
            insertNodeAtHead(data);
        }


    }

    //头插法
    private void insertNodeAtHead(T data) {
        Node node = head.getNext();
        head.setNext(new Node(data, node));
        count++;

    }

    //删除prevNode结点的下一个元素
    private void remove(Node prevNode) {
        Node node = prevNode.getNext();
        prevNode.setNext(node.getNext());
        node = null;
        count--;
    }

    //删除链表中最后一个元素
    private void deleteNodeAtEnd() {
        Node node = head;
        if (node.getNext() == null) {
            return;
        }
        while (node.getNext().getNext() != null) {
            node = node.getNext();
        }
        Node endNode = node.getNext();
        node.setNext(null);
        endNode = null;
        count--;

    }

    //查找元素的前一个节点
    private Node findPrevNode(T data) {
        Node node = head;
        while (node.getNext() != null) {
            if (node.getNext().getData().equals(data)) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }


    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        NodeLRU lru = new NodeLRU();
        for (int i = 1; i < 11; i++) {
            lru.add(i);
        }
        printLinkElement(lru);
        lru.add(5);
        printLinkElement(lru);
        lru.add(3);
        printLinkElement(lru);


    }

    public static void printLinkElement(NodeLRU lru) {
        Node head = lru.getHead();
        StringBuilder stringBuilder = new StringBuilder();
        while (head.getNext().getNext() != null) {
            stringBuilder.append(head.getNext().getData()).append("->");
            head = head.getNext();
        }
        stringBuilder.append(head.getNext().getData());
        System.out.println(stringBuilder.toString());
    }

}
