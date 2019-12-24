package linkedlist;

import bean.SingleLinked;

/**
 * @author shenpeng
 * @data 2019/12/23
 * 使用java实现单向链表的LRU算法：最近最少使用淘汰策略
 */
public class LRULinkedList {


    // 声明链表的长度（即缓存大小）为10
    private static final int limitSize = 10;

    /**
     * 思路：我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。
     * 当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
     * <p>
     * 1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，
     * 并将其从原来的位置删除，然后再插入到链表的头部。
     * <p>
     * 2. 如果此数据没有在缓存链表中，又可以分为两种情况：如果此时缓存未满，
     * 则将此结点直接插入到链表的头部；如果此时缓存已满，则链表尾结点删除，
     * 将新的数据结点插入链表的头部。
     */
    public static void LRUTest() {


        SingleLinked<Integer> linked = new SingleLinked();

        // 最近最少使用淘汰策略:LRU
        // 1.先按顺序浏览10个地址
        for (int i = 0; i < 10; i++) {
            linked.addFirst(i);
        }

        System.out.println(linked.getSize());

        // 2.目前的顺序是:System.out.println(linked);
        System.out.println(linked);

        // 3.假设重新浏览了 6，2，8（按顺序）

        check(6, linked);
        check(2, linked);
        check(8, linked);

    }


    public static void check(int t, SingleLinked<Integer> linked) {
        // 3.1：判断6是否存在（浏览过）
        if (null != linked.get(t)) {
            linked.del(t);
            linked.addFirst(t);
        } else {
            // 3.2：链表的容量判断,
            // 如果当前容量已经满了
            if (linked.getSize() >= limitSize) {
                linked.delEnd();
            }
            linked.addFirst(t);
        }
        System.out.println(linked);
    }

    public static void main(String[] args) {
        LRUTest();
    }
}
