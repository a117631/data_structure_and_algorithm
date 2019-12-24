package bean;

/**
 * @author shenpeng
 * @data 2019/12/24
 * <p>
 * 单向有序链表
 */
public class SingleLinked<T> {

    public class Node {

        private T t;
        private Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t) {
            this(t, null);
        }

        public T getT() {
            return this.t;
        }


        public Node getNext() {
            return this.next;
        }
    }

    // 头结点
    private Node node;
    // 链表元素个数
    private int size;

    // 构造函数
    public SingleLinked() {
        this.node = null;
        this.size = 0;
    }

    // 链表头新增数据
    public void addFirst(T t) {
        // 1.定义当前链表的数据T;
        Node node = new Node(t);
        // 2.将未插入前的链表数据作为准备新增的链表数据的nextNode;
        node.next = this.node;
        // 3.next操作完成后将头数据修改为新增的node
        this.node = node;
        // 4.链表大小增加
        size++;
    }

    // 链表尾部新增数据
    public void addEnd(T t) {
        // 1.初始化需要新增的链表数据
        Node node = new Node(t);
        // 2.定义上一个链表上一个的数据
        Node before = this.node;
        // 3.搜索上一个链表的node数据
        for (int i = 0; i < size; i++) {
            before = before.next;
        }
        // 4.将上一个链表的next数据改为新增的链表的数据
        before.next = node;
        size++;
    }

    // 向链表指定位置新增数据
    public void add(T t, int index) {

        // 1.判断越界
        if (this.size < 0 || size < index) {
            System.out.println("error index");
            return;
        }
        // 2.判断是否是链表头新增
        if (0 == index) {
            addFirst(t);
        }
        // 3.声明需要新增的node数据
        Node node = new Node(t);
        // 4.声明上一个node的数据
        Node before = this.node;
        // 5.确定上一个node数据的内容
        for (int i = 0; i < index - 1; i++) {
            System.out.println(i + ":" + before.t);
            before = before.next;
        }
        // 6.将新增node的next数据用before替代
        node.next = before.next;
        // 7.上一个node的next数据用当前node替代
        before.next = node;
        // 8.长度++
        size++;

    }

    // 删除链表的第一个元素
    public void delFirst() {

        // 如果链表为空
        if (null == node) {
            System.out.println("null linkeds");
        }
        Node delNode = this.node;
        this.node = this.node.next;
        delNode.next = null;
        size--;

    }

    // 删除链表末尾node数据
    public void delEnd() {

        if (null == node) {
            System.out.println("null node");
            return;
        }

        if (1 == size) {
            this.delFirst();
        }

        Node delNode = this.node;
        Node preDel = this.node;
        while (null != delNode) {
            preDel = delNode;
            delNode = delNode.next;
        }

        preDel.next = null;
        size--;
    }

    public Node getNode() {
        return this.node;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("->");

        Node node = this.node;

        while (null != node) {
            sb.append(node.t + "->");
            node = node.next;
        }

        sb.append("null");
        return sb.toString();
    }

    // 删除（优化：使用虚拟头数据）
    public void del(T t) {
        // 1.声明虚拟头
        Node temNode = new Node(t, this.node);

        // 2.创建虚拟头的临时数据
        Node tmp = temNode;

        // 3.从非虚拟头的数据开始
        while (null != tmp.next) {

            // 4.如果数据相同，将改数据清理（清理的逻辑：需要删除的node数据的next变为他的下一个，把删除的数据后面的数据往前移动）
            if (tmp.next.t.equals(t)) {
                tmp.next = tmp.next.next;
                this.size--;
            } else {
                tmp = tmp.next;
            }
        }

        this.node = temNode.next;

    }


    public T get(T t) {
        Node node = this.node;
        while (null != node) {
            if (node.t.equals(t)) {
                return node.t;
            }
            node = node.next;
        }
        return null;
    }
}
