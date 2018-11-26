package com.model_algorithm.tree;

/**
 * @Description描述: 二叉排序树
 * @Author作者: Kyle
 * @Date日期: 2018/11/6
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    // 每一棵树都有一个根
    private BinaryNode<E> root;

    /**
     * 树中是否包含指定对象
     *
     * @param x
     * @return
     */
    public boolean contains(E x) {
        return true;
    }

    /**
     * 查找树中最小值
     *
     * @return
     */
    public E findMin() {
        if (isEmpty()) {
            return null;
        }

        return findMin(root).element;
    }

    private BinaryNode<E> findMin(BinaryNode<E> node) {
        if (node == null) {
            return null;
        }

        BinaryNode minNode = node;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
    }

    /**
     * 查找树种最大值
     *
     * @return
     */
    public E findMax() {
        if (isEmpty()) {
            return null;
        }

        return findMax(root).element;
    }

    private BinaryNode<E> findMax(BinaryNode<E> node) {
        if (node == null) {
            return null;
        }

        BinaryNode<E> maxNode = node;
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }

        return maxNode;
    }

    /**
     * 插入
     *
     * @param e
     * @return
     */
    public boolean insert(E e) {
        if (isEmpty()) {
            root = new BinaryNode<E>(e);
            return true;
        }

        root = insert(e, root);

        return true;
    }

    private BinaryNode<E> insert(E e, BinaryNode<E> node) {
        if (node == null) {
            return null;
        }

        int compare = e.compareTo(node.element);

        /**
         * 插入2
         *          6
         *      3
         *  2
         */

        // 如果e小于节点，将e存放在left节点,否则将e存放在right节点
        if (compare < 0) {
            if (node.left == null) {
                node.left = new BinaryNode<E>(e);
            } else {
                node.left = insert(e, node.left);
            }
        } else if (compare > 0) {
            if (node.right == null) {
                node.right = new BinaryNode<E>(e);
            } else {
                node.right = insert(e, node.right);
            }
        } else {
            // 如果对象已经存在不进行插入
        }

        return node;
    }

    /**
     * 删除数据
     *
     * @param e
     */
    public void remove(E e) {
        if (e == null || isEmpty()) {
            return;
        }

        root = remove(e, root);
    }

    private BinaryNode<E> remove(E e, BinaryNode<E> node) {
        if (node == null) {
            return null;
        }

        int compare = e.compareTo(node.element);
        // 先找节点
        if (compare < 0) {
            // 如果数据小于节点，向left节点查找
            node.left = remove(e, node.left);
        } else if (compare > 0) {
            // 如果数据大于节点，向right节点查找
            node.right = remove(e, node.right);
        } else if (node.left != null && node.right != null) {// 查找数据了,并且是节点树
            // 查找当前节点下的right节点下的最小值
            // 因为 当前节点right下的所有节点都比当前节点大
            // 保留节点，将当前节点下的right节点的最小值赋值给当前节点
            node.element = findMin(node.right).element;
            // 继续遍历删除right下的最小值节点
            node.right = remove(node.element, node.right);
        } else {
            node = node.left == null ? node.right : null;
        }

        return node;
    }


    /**
     * 是否是空树
     *
     * @return
     */
    public boolean isEmpty() {
        return null == root;
    }

    /**
     * 打印树中所有的节点
     */
    public void printTree(int traverse) {
        if (isEmpty()) {
            return;
        }

        System.out.println();

        switch (traverse) {
            case 0:
                preOrder(root);
                break;
            case 1:
                inOrder(root);
                break;
            case 2:
                afterOrder(root);
                break;
            default:
                inOrder(root);
                break;
        }
    }

    /**
     * 先序遍历
     * VLR
     */
    private void preOrder(BinaryNode<E> node) {
        if (node == null) {
            return;
        }

        System.out.print(node.element + "  ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     * LVR
     */
    private void inOrder(BinaryNode<E> node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.element + "  ");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     * LRV
     */
    private void afterOrder(BinaryNode<E> node) {
        if (node == null) {
            return;
        }

        afterOrder(node.left);
        afterOrder(node.right);
        System.out.print(node.element + "  ");
    }


    /**
     * 二叉树节点
     *
     * @param <E>
     */
    private static final class BinaryNode<E> {
        E element;
        BinaryNode<E> left;
        BinaryNode<E> right;

        public BinaryNode(E element) {
            this(element, null, null);
        }

        public BinaryNode(E element, BinaryNode<E> left, BinaryNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        Random random = new Random();
        int[] arr = new int[]{6, 8, 1, 2, 10, 4, 3, 7, 9, 5, 20, 50, 12, 15, 30, 25};
//        for (int i = 0; i < 10; i++) {
//            int e = random.nextInt(10);
//            bst.insert(e);
//            System.out.print(e + ",");
//        }

        for (int i : arr) {
            bst.insert(i);
        }

        bst.printTree(0);
        bst.printTree(1);
        bst.printTree(2);

        System.out.println();
        System.out.println("Min:" + bst.findMin());
        System.out.println("Max:" + bst.findMax());

        bst.remove(8);

        bst.printTree(0);
        bst.printTree(1);
        bst.printTree(2);
    }
}
