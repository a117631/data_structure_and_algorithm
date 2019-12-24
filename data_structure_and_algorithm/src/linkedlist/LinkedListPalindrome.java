package linkedlist;

import bean.SingleLinked;

/**
 * @author shenpeng
 * @data 2019/12/24
 * 链表实现回文判断
 */
public class LinkedListPalindrome {

//    方法一：半栈法
//　　　　　１．用快慢两个指针遍历，同时用栈copy慢指针指向的data。
//            　　　　　２．完成后，慢指针指向中间节点，耗时为N/2.
//            　　　　　３．最后用pop栈中的data和慢指针指向的data比较，耗时也是N/2.
//    所以时间复杂度为：Ｏ(N)，空间复杂度因栈额外存储了一半的data，故为O(N/2)

    /**
     * 单向链表半栈法实现回文
     */
    public static boolean halfStack(String pal) {

        // 实现思路：
        // 模拟栈的模型，使用快慢指针确定中间值，然后以慢指针的点为中间点开始进行计算

        SingleLinked<Integer> linkedList = strToLinkedList(pal);

        // 过滤空字符串
        if (null == linkedList.getNode()) {
            return false;
        }

        // 单个默认为回文
        int size = linkedList.getSize();
        if (1 == size) {
            return true;
        }

        // 定义一个链表
        SingleLinked<Integer> descLinked = new SingleLinked();

        // 定义当前头指针
        SingleLinked<Integer>.Node node = linkedList.getNode();

        // 定义快慢指针链表
        SingleLinked<Integer>.Node fastNode = node;
        SingleLinked<Integer>.Node slowNode = node;

        for (int i = 0; i < linkedList.getSize(); i++) {

            // 慢指针的数据增加到链表头
            descLinked.addFirst(slowNode.getT());

            // 快指针是否到头(判断回文的单数和双数问题)
            if (null == fastNode.getNext() || null == fastNode.getNext().getNext()) {
                break;
            }
            fastNode = fastNode.getNext().getNext();
            slowNode = slowNode.getNext();

        }

        SingleLinked<Integer>.Node descNode = descLinked.getNode();
        if (size % 2 == 0) {
            slowNode = slowNode.getNext();
        }

        for (int i = 0; i < descLinked.getSize(); i++) {

            if (!(descNode.getT().equals(slowNode.getT()))) {
                return false;
            }

            descNode = descNode.getNext();
            slowNode = slowNode.getNext();
        }

        return true;
    }
//
//    方法二：全栈法
//　　　　　１．全部遍历，data压栈，额外空间消耗N
//                    ２．再次全部遍历取data，同时pop栈取data, 二者比较，时间消耗2N
//    所以时间复杂度为O(3N)，空间复杂度为O(N)
//    该法算法最简单，但复杂度高。可以用栈存储节点指针，而非data来改进。
//
//    方法三：硬干法
//　　　　　1. 一个指针从头取data，另一个指针遍历到底取data，比较二者
//　　　　　２．删除尾部节点，重复１．
//            　　　时间复杂度高达　O(N^2)，空间复杂度却最低Ｏ(1)

    /**
     * 字符串转单链表
     *
     * @param str
     * @return
     */
    public static SingleLinked<Integer> strToLinkedList(String str) {

        SingleLinked<Integer> linkedList = new SingleLinked();

        char[] chars = str.toCharArray();

        for (char ca : chars) {
            linkedList.addFirst(ca - '0');
        }

        return linkedList;
    }

    public static void main(String[] args) {
        System.out.println(halfStack("0110"));
    }

}
