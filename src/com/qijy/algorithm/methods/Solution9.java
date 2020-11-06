package com.qijy.algorithm.methods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * @ Description   :    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/16 8:53
 */
public class Solution9 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<BigDecimal> list1 = new ArrayList<>();
        List<BigDecimal> list2 = new ArrayList<>();
        List<Integer> listr = new ArrayList<>();
        while (l1!=null){
            list1.add(BigDecimal.valueOf(l1.val));
            l1=l1.next;
        }
        while (l2 != null){
            list2.add(BigDecimal.valueOf(l2.val));
            l2 = l2.next;
        }
        BigDecimal num1 = new BigDecimal(0);
        BigDecimal num2 = new BigDecimal(0);
        for (int i = list1.size()-1; i >=0 ; i--) {
            num1 =num1.add(list1.get(i).multiply(BigDecimal.valueOf(Math.pow(10,i))));

        }
        for (int j = list2.size()-1; j >=0 ; j--) {
            num2 =num2.add(list2.get(j).multiply(BigDecimal.valueOf(Math.pow(10,j))));
        }
        BigDecimal result = num1.add(num2);
        BigDecimal n = result;
        while(n.intValue() != 0){
            BigDecimal bigDecimal = n.divideAndRemainder(BigDecimal.valueOf(10))[1];
            listr.add(bigDecimal.intValue());
            n = n.divide(new BigDecimal(10));
        }
        ListNode root = null;
        if(result.intValue() == 0){
            root = new ListNode(0);
        }
        if(listr.size() > 0){
            root = new ListNode(listr.get(0));
            ListNode temp = root;
            for (int k=1;k<listr.size();k++){
                ListNode listNode = new ListNode();
                listNode.val=listr.get(k);
                temp.next = listNode;
                temp = listNode;
            }
        }
        while (root != null){
            System.out.println(root.val);
            root = root.next;
        }
        return root;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        while (dummyHead.next != null){
            System.out.println(dummyHead.next.val);
            dummyHead = dummyHead.next;
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        l1.next = l12;
        ListNode l13 = new ListNode(3);
        l12.next = l13;

        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        l2.next = l21;
        ListNode l22 = new ListNode(4);
        l21.next = l22;
        ListNode l23 = new ListNode(9);
        l22.next = l23;
        ListNode l24 = new ListNode(9);
        l23.next = l24;
        ListNode l25 = new ListNode(9);
        l24.next = l25;
        ListNode l26 = new ListNode(9);
        l25.next = l26;
        ListNode l27 = new ListNode(9);
        l26.next = l27;
        ListNode l28 = new ListNode(9);
        l27.next = l28;
        ListNode l29 = new ListNode(9);
        l28.next = l29;
        solution9.addTwoNumbers1(l1,l2);
    }
}
