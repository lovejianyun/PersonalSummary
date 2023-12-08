package com.qijy.algorithm.interview;
/**
 * @description: 链表反转
 * @author qijy
 * @date 2023/12/5 10:28
 * @version 1.0
 */
public class LinkedListReverse {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
    /**
     * @description: 迭代反转链表
     * @author qijy
     * @date 2023/12/5 16:12
     * @version 1.0
     */
    public static ListNode reverse(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            // 三指针方法中的数据处理 后指针 继续往下一个
            ListNode next = current.next;
            // 改变当前指针的方向，将当前指针改成指向前一个
            current.next = previous;
            // 然后将前一个指针以及当前指针，以及next指针全部后移一位
            // 前一个指针指向当前指针
            previous = current;
            // 当前指针指向下一个
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        ListNode head = init();
        // 反转链表
        ListNode reversedHead = reverse4(head);

        // 输出反转后的链表节点的值
        while (reversedHead != null) {
            System.out.print(reversedHead.val + " ");
            reversedHead = reversedHead.next;
        }
    }

    private static ListNode init() {
        // 创建一个链表 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return head;
    }

    public static ListNode reverse2(ListNode head){
        ListNode pre = null;
        ListNode cru = head;
        while (cru != null){
            ListNode next = cru.next;
            cru.next = pre;
            pre = cru;
            cru = next;
        }
        return pre;
    }
    /**
     * @description: 递归算法
     * @author qijy
     * @date 2023/12/5 16:12
     * @version 1.0
     */
    public static ListNode reverse3(ListNode head){
        //递归的出口
        if (head == null || head.next == null){ // 空链或只有一个结点，直接返回头指针
            return head;
        }else{
            //一直递归，找到链表中最后一个节点
            ListNode new_head = reverse3(head.next);
            //当逐层退出时，new_head 的指向都不变，一直指向原链表中最后一个节点；
            //递归每退出一层，函数中 head 指针的指向都会发生改变，都指向上一个节点。
            //每退出一层，都需要改变 head->next 节点指针域的指向，同时令 head 所指节点的指针域为 NULL。
            head.next.next = head;
            head.next = null;
            //每一层递归结束，都要将新的头指针返回给上一层。由此，即可保证整个递归过程中，能够一直找得到新链表的表头。
            return new_head;
        }
    }
    /**
     * @description: 头插法
     * @author qijy
     * @date 2023/12/5 17:01
     * @version 1.0
     */
    public static ListNode reverse4(ListNode head){
        ListNode temp = null;
        ListNode new_head = null;
        if (head == null || head.next == null) {
            return head;
        }
        while (head != null)
        {
            temp = head;
            //将 temp 从 head 中摘除
            head = head.next;
            //将 temp 插入到 new_head 的头部
            temp.next = new_head;
            new_head = temp;
        }
        return new_head;
    }

    public static ListNode reverse5(ListNode head){
        ListNode temp = null;
        ListNode newHead = null;
        while(head != null){
            temp = head;
            head = head.next;
            temp.next = newHead;
            newHead = temp;
        }
        return newHead;
    }
    /**
     * @description: 就地反转
     * @author qijy
     * @date 2023/12/5 17:55
     * @version 1.0
     */
    public static ListNode reverse6(ListNode head){
        ListNode beg = null;
        ListNode end = null;
        if (head == null || head.next == null) {
            return head;
        }
        beg = head;
        end = head.next;
        while (end != null) {
            //将 end 从链表中摘除
            beg.next = end.next;
            //将 end 移动至链表头
            end.next = head;
            head = end;
            //调整 end 的指向，另其指向 beg 后的一个节点，为反转下一个节点做准备
            end = beg.next;
        }
        return head;
    }

}
