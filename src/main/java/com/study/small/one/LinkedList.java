package com.study.small.one;

//单链表
public class LinkedList<T> {

    Node list; //链表的头节点
    int size; //链表有多少个节点

    public LinkedList() {
        size = 0;
    }

    //添加节点
    //在头部添加节点
    public void put(T data) {
        Node head = list;
        Node curNode = new Node(data, list); //构建新节点，同时将该节点的下一个节点指向list
        list = curNode; //更新头节点为新节点
        size++;
    }

    //在链表的index 位置插入一个新的数据data
    public void put(int index,T data) {
        checkPositionIndex(index);
        Node cur = list;
        Node head = list;
        for(int i = 0; i < index; i++) {
            head = cur;
            cur = cur.next;
        }
        Node node = new Node(data, cur); //构建新节点，下一个节点指向原来的index节点
        head.next = node; //将index-1节点的下一个节点指向新构建的节点
        size++;

    }

    //删除节点
    //删除头部节点
    public T remove() {
        if (list != null) {
            Node node = list; //获取头节点
            list = list.next; //将头节点指向原本头节点的下一个节点
            node.next = null; // GC 回收   将原本的头节点的下一个节点指向设为null
            size--;
            return node.data;
        }
        return null;
    }

    public T remove(int index) { //删除指定下标位置的节点
        checkPositionIndex(index);
        Node head = list;
        Node cur = list;
        for(int i = 0; i < index; i++) {
            head = cur;
            cur = cur.next;
        }
        head.next = cur.next;
        cur.next = null;//GC
        size--;
        return cur.data;
    }

    public T removeLast() { //删除最后一个节点
        if (list != null) {
            Node node = list;
            Node cur = list;
            while(cur.next != null) {
                node = cur;
                cur = cur.next;
            }
            node.next = null;
            size--;
            return cur.data;

        }
        return null;
    }
    //修改index位置的节点数据
    public void set(int index,T newData) {
        checkPositionIndex(index);
        Node head = list;
        for(int i = 0; i < index; i++) {
            head = head.next;
        }
        head.data = newData;
    }

    //查询节点
    //get 头部节点
    public T get() {
        Node node = list;
        if (node != null) {
            return node.data;
        } else {
            return null;
        }
    }

    public T get(int index) { //获取下标index处的节点
        checkPositionIndex(index);
        Node node = list;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    //检测index是否在链表范围以内
    public void checkPositionIndex(int index) {
        if(!(index >=0 && index <=size)) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

    }

    //节点的信息
    class Node {
        T data;
        Node next;

        public Node(T data,Node node) {
            this.data = data;
            this.next = node;
        }
    }
}