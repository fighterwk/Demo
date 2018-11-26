package com.model_algorithm.tree;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/11/13
 */
public class AVLTree<E extends Comparable<? super E>> {

    private TreeNode<E> root;  // 树的根

    /**
     * 插入数据
     *
     * @param element
     */
    public void insert(E element) {
        root = insert(root, element);
    }

    private TreeNode<E> insert(TreeNode<E> node, E element) {
        if (node == null) {
            node = new TreeNode<>(element, null, null);
        } else {
            // 先插入
            int compare = element.compareTo(node.element);
            if (compare < 0) {
                node.left = insert(node.left, element);

                // 调整树
                node = ajustTree(node);

//                // 节点插入树后,如果树失去平衡对数进行校正
//                // 因为数据是插入到左节点，如果树失去平衡，那么一定是左节点的树高于右节点的树
//                int diff = height(node.left) - height(node.right);
//                // 如果左节点的树高于右节点的树>1 那么进行旋转
//                if (diff == 2) {
//                    // 如果插入的节点小于当前节点, 那么进行左旋转即可,否则就需要进行左右双旋
//                    if (element.compareTo(node.left.element) < 0) {
//                        node = leftSingleRotation(node);
//                    } else {
//                        node = leftRightRotation(node);
//                    }
//                }

            } else if (compare > 0) {
                node.right = insert(node.right, element);

                // 调整
                node = ajustTree(node);

//                int diff = height(node.right) - height(node.left);
//                if (diff == 2) {
//                    if (element.compareTo(node.right.element) > 0) {
//                        node = rightSingleRotation(node);
//                    } else {
//                        node = rightLeftRotation(node);
//                    }
//                }

            } else {
//                // 数据相同，不插入新的数据.
//                return node;
            }
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

    /**
     * 移除数据
     *
     * @param element
     */
    public void remove(E element) {
        if (element == null) {
            return;
        }
        if (contains(element)) {
            root = remove(root, element);
        }
    }

    private TreeNode<E> remove(TreeNode<E> node, E e) {
        if (node == null) {
            return node;
        }

        int compare = e.compareTo(node.element);
        if (compare < 0) {
            // 删除left 节点下的
            node.left = remove(node.left, e);

            // 调整树
            node = ajustTree(node);
        } else if (compare > 0) {
            // 删除right 节点下的
            node.right = remove(node.right, e);

            // 调整树
            node = ajustTree(node);

        } else if (node.left != null && node.right != null) {
            // 懒惰删除
            if (height(node.left) > height(node.right)) {
                // 如果左边树比右边树高, 删除左边树的数据
                node.element = findMax(node.left).element;
                node.left = remove(node.left, node.element);
            } else {
                node.element = findMin(node.right).element;
                node.right = remove(node.right, node.element);
            }

        } else {
            // 删除
            node = node.left == null ? node.right : null;
        }

        // 重新计算高度
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }

        return node;
    }

    // 调整树
    private TreeNode<E> ajustTree(TreeNode<E> node) {
        if (node == null) {
            return node;
        }

        int diff = height(node.left) - height(node.right);
        // 左节点高于右节点
        if (diff == 2) {
            final TreeNode<E> l = node.left;
            if (height(l.left) < height(l.right)) {
                // 如果left 高度小于 right节点，需要进行 左右双旋
                node = leftRightRotation(node);
            } else {
                // 如果 left 高度 高于 right 节点, 进行左旋
                node = leftSingleRotation(node);
            }
        } else
            // 右节点高于左节点
            if (diff == -2) {
                final TreeNode<E> r = node.right;
                if (height(r.right) < height(r.left)) {
                    // 如果 right 高度 小于 left 高度，需要进行 右左双旋
                    node = rightLeftRotation(node);
                } else {
                    node = rightSingleRotation(node);
                }
            } else {
                // 不需要进行调整
            }

        return node;
    }


    /**
     * 是否包含数据
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return false;
        }

        return contains(root, element);
    }

    private boolean contains(TreeNode<E> node, E element) {
        if (node == null || element == null) {
            return false;
        }

        TreeNode<E> tNode = node;

        while (true) {
            int compare = element.compareTo(tNode.element);
            if (compare > 0) {
                if (tNode.right == null) {
                    return false;
                }
                tNode = tNode.right;
            } else if (compare < 0) {
                if (tNode.left == null) {
                    return false;
                }
                tNode = tNode.left;
            } else {
                return true;
            }
        }
    }

    /**
     * 左旋
     * <p>
     * 1. 当前节点的左节点成为自己的父节点
     * eg: node.left.right = node
     * 2. 当前节点的左节点的右节点成为自己的左节点
     * eg: node.left = node.left.right
     */
    private TreeNode<E> leftSingleRotation(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        // 当前节点
        TreeNode<E> currNode = node;
        // 当前节点的左节点
        TreeNode<E> currNodeLeft = node.left;
        // 当前节点左节点的右节点
        TreeNode<E> currNodeLeftRight = currNodeLeft.right;

        // 当前节点的左节点成为自己的父节点
        currNodeLeft.right = currNode;

        // 当前节点的左节点的右节点成为自己的左节点
        currNode.left = currNodeLeftRight;

        // 树的平衡性发生了变化，那么树的高度也会发生变化
        currNode.height = Math.max(height(currNode.left), height(currNode.right)) + 1;  // 当前树的高度发生变化
        currNodeLeft.height = Math.max(height(currNodeLeft.left),
                height(currNodeLeft.right)) + 1;  // 当前树的父节点高度发生变化

        // 返回新的父节点
        return currNodeLeft;
    }

    /**
     * 右旋
     * 与左旋相反
     */
    private TreeNode<E> rightSingleRotation(TreeNode<E> node) {

        TreeNode<E> n2 = node.right;
        node.right = n2.left;
        n2.left = node;

        node.height = calcHeight(node);
        n2.height = calcHeight(n2);

        return n2;


//        TreeNode<E> currNode = node;
//        TreeNode<E> currNodeRight = currNode.right;
//        TreeNode<E> currNodeRightLeft = currNodeRight.left;
//
//        currNodeRight.left = currNode;
//        currNode.right = currNodeRightLeft;
//
//
//        currNode.height = Math.max(height(currNode.left), height(currNode.right)) + 1;
//        currNodeRight.height = Math.max(height(currNodeRight.left), height(currNodeRight.right)) + 1;
//
//        return currNodeRight;
    }

    /**
     * 左右双旋
     */
    private TreeNode<E> leftRightRotation(TreeNode<E> node) {
        // 将节点的左节点进行右旋转
        node.left = rightSingleRotation(node.left);

        return leftSingleRotation(node);
    }


    /**
     * 右左双旋
     */
    private TreeNode<E> rightLeftRotation(TreeNode<E> node) {
        node.right = leftRightRotation(node.right);

        return rightSingleRotation(node);
    }

    /**
     * 树的高度
     */
    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> node) {
        if (node != null) {
            return node.height;
        }

        return 0;
    }

    /**
     * 计算树的高度
     *
     * @param node
     * @return 节点树的高度
     */
    private int calcHeight(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }

        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 查找最大的数据
     *
     * @return
     */
    public E findMax() {
        if (isEmpty()) {
            return null;
        }
        return findMax(root).element;
    }

    private TreeNode<E> findMax(TreeNode<E> node) {
        if (node == null) {
            return null;
        }

        TreeNode<E> maxNode = node;
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }

        return maxNode;
    }

    /**
     * 查找最小的数据
     *
     * @return
     */
    public E findMin() {
        if (isEmpty())
            return null;

        return findMin(root).element;
    }

    private TreeNode<E> findMin(TreeNode<E> node) {
        if (node == null) {
            return null;
        }

        TreeNode<E> minNode = node;
        while (minNode.left != null) {
            minNode = minNode.left;
        }

        return minNode;
    }

    public boolean isEmpty() {
        return null == root;
    }


    /**
     * 遍历树
     */
    public void traverseTree() {
        System.out.println("\n先序遍历:");
        preOrder(root);
        System.out.println("\n中序遍历:");
        inOrder(root);
        System.out.println("\n后序遍历:");
        afterOrder(root);
    }

    /**
     * 先序遍历
     */
    private void preOrder(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        System.out.print(node.element + "  ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    private void inOrder(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.element + "  ");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    private void afterOrder(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        afterOrder(node.left);
        afterOrder(node.right);
        System.out.print(node.element + "  ");
    }

    /**
     * 树节点
     *
     * @param <E>
     */
    private static final class TreeNode<E> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;
        int height;  // 树的高度

        public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    public void printTree() {
        System.out.println();
        printTree(root, null, 0);
    }

    private void printTree(TreeNode<E> node, E e, int flag) {
        if (node == null) {
            return;
        }

        if (e == null) {
            System.out.println("根节点:" + node.element);
        } else {
            if (flag == -1) {
                System.out.println(node.element + " 是 " + e + " 的 left");
            } else {
                System.out.println(node.element + " 是 " + e + " 的 right");
            }
        }

        printTree(node.left, node.element, -1);
        printTree(node.right, node.element, 1);
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }

        tree.traverseTree();
        tree.printTree();


        System.out.println();
        System.out.println("tree contains 18:" + tree.contains(18));
        System.out.println("tree contains 7:" + tree.contains(7));
        System.out.println("树的高度:" + tree.height());
        System.out.println("最小值:" + tree.findMin());
        System.out.println("最大值:" + tree.findMax());


        int[] arr = {0, 1, 3, 9, 5, 7, 8};
        for (int i = 0; i < arr.length; i++) {
            tree.remove(arr[i]);

            System.out.println();
            System.out.println("树的高度:" + tree.height());
            tree.printTree();
        }
    }


}
