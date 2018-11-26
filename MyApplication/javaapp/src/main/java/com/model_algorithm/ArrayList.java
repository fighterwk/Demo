package com.model_algorithm;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/29
 */
public class ArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = new Object[0];

    Object[] elementData;  // 存放数据用的数组
    int size;  // 已经存放了数据的刻度

    public ArrayList(int capacity) {
        elementData = new Object[capacity];
    }

    public ArrayList() {
        elementData = EMPTY_ELEMENTDATA;
    }

    /**
     * 集合数据的大小
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 集合数据是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return 0 == this.size;
    }


    /**
     * 添加数据到集合中
     *
     * @param value
     * @return
     */
    public boolean add(T value) {
        // 检查容量是否满足,如果不满足就进行扩容
        ensureCapacityInternal(this.size + 1);
        // 空间满足/扩容空间成功，将数据赋值到下一刻度
//        this.size ++;  // 提升刻度
        this.elementData[this.size++] = value;
        return true;
    }

    private void ensureCapacityInternal(int capacity) {
        // 如果容器还未设置容量大小，初始化容量大小
        if (this.elementData == EMPTY_ELEMENTDATA) {
            capacity = Math.max(DEFAULT_CAPACITY, capacity);
        }


        ensureExplicitCapacity(capacity);

    }

    private void ensureExplicitCapacity(int capacity) {
        if (capacity - this.elementData.length > 0) {
            // 扩容
            this.grow(capacity);
        }
    }

    private void grow(int capacity) {
        int len = this.elementData.length;
        int newCapacity = len + (len >> 1);
        newCapacity = Math.max(capacity, newCapacity);

//        this.elementData = Arrays.copyOf(this.elementData, newCapacity);
        Object[] tempElementData = new Object[newCapacity];
        System.arraycopy(this.elementData, 0, tempElementData, 0, this.elementData.length);
        this.elementData = tempElementData;
    }

    /**
     * 在集合中删除指定数据
     *
     * @param o
     * @return
     */
    public T remove(T o) {
        if (isEmpty()) {
            return null;
        }

        // 查找数据在集合中的位置
        int i = 0;
        Object element = null;
        for (i = 0; i < this.elementData.length; i++) {
            element = this.elementData[i];
            if (element == o) {
                break;
            }
        }

        if (i == this.elementData.length) {
            return null;
        }

        int len = this.size - i - 1;  // copy数据的长度
        int destPos = i + 1;

        // 找到数据的位置，删除数据并且调整集合中的数据位置
        System.arraycopy(this.elementData, destPos, this.elementData, destPos, len);
        this.elementData[--this.size] = null;

        return (T) element;
    }


    /**
     * 通过下标获取集合中的数据
     *
     * @param i
     * @return
     */
    public T get(int i) {
        if (i > size
                || i < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.elementData[i];
    }

    /**
     * 修改指定下标位置的数据
     *
     * @param i
     * @param value
     * @return
     */
    public void add(int i, T value) {
        if (i < 0 || i > this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        this.ensureCapacityInternal(this.size + 1);

        System.arraycopy(this.elementData, i, this.elementData, i + 1, this.size - i);
        this.elementData[i] = value;
        ++this.size;
    }


    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < 4000; i++) {
            list.add("String_" + i);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
