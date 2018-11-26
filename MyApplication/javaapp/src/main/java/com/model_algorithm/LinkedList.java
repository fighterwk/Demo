package com.model_algorithm;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/30
 */
public class LinkedList<E> {

    Node<E> first;
    Node<E> last;
    int size;

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int i = 0;
        E item = null;
        for (Node<E> node = first; node != null; node = node.next) {
            i++;
            if (index == i) {
                item = node.element;
                break;
            }
        }

        return item;
    }

    public boolean add(E o) {
        final Node<E> l = last;
        Node<E> newNode = new Node<>(l, o, null);

        last = newNode;

        // 如果链表最后一个节点为空，那么将新节点赋值给头节点
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        ++this.size;
        return true;
    }

    public boolean remove(E o) {
        if (isEmpty()) {
            return false;
        }

        // 遍历链表，查找数据(使用一个外部的比较，可以减少内部循环的比较，相对节约资源)
        if (o == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.element == null) {
                    // 将节点移除链表
                    unlink(node);
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next) {
                // 将节点移除链表
                if (o.equals(node.element)) {
                    unlink(node);
                }
            }
        }


        return true;
    }

    private void unlink(Node<E> node) {
        final Node<E> prev = node.prev;
        final Node<E> next = node.next;

        // 如果当前节点的上一个节点为空，那么这个节点为链表中的第一个节点
        if (prev == null) {
            this.first = next;
        } else {
            prev.next = next;
        }

        // 如果当前节点的下一个节点为空，那么这个节点为链表中的最后一个节点
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }


        --this.size;
    }

    public Iterator<E> getIterator() {
        return new Iterator<E>() {
            int nextIndex = 0;
            Node<E> next;
            Node<E> lastReturned;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = next = (next == null) ? first : next.next;
                nextIndex++;
                return lastReturned.element;
            }
        };
    }


    private class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(String.valueOf("index:" + i));
        }

        for (int i = 900; i < 1000; i++) {
            list.remove(String.valueOf("index:" + i));
        }
        list.remove("index:" + 900);

        Iterator<String> iterator = list.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        System.out.println(list.get(800));


    }
}
