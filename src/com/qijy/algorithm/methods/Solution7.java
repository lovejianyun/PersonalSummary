package com.qijy.algorithm.methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @ Description   :  将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/15 8:58
 */
public class Solution7 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       if(l1 == null){
           return l2;
       }else if(l2 == null){
           return l1;
       }else if(l1.val>=l2.val){
           l2.next = mergeTwoLists(l1,l2.next);
           return l2;
       }else{
           l1.next = mergeTwoLists(l1.next,l2);
           return l1;
       }
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2){
        List<Integer> list = new ArrayList<>();
        while(l1 != null){
            list.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            list.add(l2.val);
            l2 = l2.next;
        }
        Collections.sort(list);
        if (list.size()>0){
            ListNode root = new ListNode(list.get(0));
            ListNode other = root;
            for (int i=1;i<list.size();i++){
                ListNode temp = new ListNode(list.get(i));
                other.setNext(temp);
                other = temp;
            }
            return root;
        }else{
            return null;
        }
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.setVal(2);
        ListNode node1 = new ListNode();
        l1.next=node1;
        node1.setVal(2);
        ListNode node2 = new ListNode();
        node1.setNext(node2);
        node2.setVal(4);
        node2.setNext(null);
        ListNode l2 = new ListNode();
        l2.setVal(1);
        ListNode nodel1 = new ListNode();
        l2.next=nodel1;
        nodel1.setVal(3);
        ListNode nodel2 = new ListNode();
        nodel1.setNext(nodel2);
        nodel2.setVal(4);
        nodel2.setNext(null);

        Solution7 solution7 = new Solution7();
        ListNode node = solution7.mergeTwoLists1(l1, l2);

        while (node!=null){
            System.out.print(node.getVal());
            node = node.getNext();
        }

    }
}
