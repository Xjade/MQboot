package com.study.small.one;

import lombok.Data;

//单链表
public class OwnLinkedList<T> {


    /**
     * 单链表反转
     * 链表中环的检测
     * 两个有序的链表合并删除
     * 链表倒数第 n 个结点
     * 求链表的中间结点
     */

    @Data
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

    }

    //第一步找到中点
    //从中点开始将后续结点反转
    //两遍开始next比较直到相遇
    public static boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null) return true;
        if(head.next.next==null){
            return head.val == head.next.val;
        }
        ListNode one = head.next;
        ListNode two = head.next.next;

        while(two.next!=null&&two.next.next!=null){
            one = one.next;
            two = two.next.next;
        }
        //链表倒转
        ListNode pre = null;
        ListNode temp = null;
        while(one!=null){
            temp = one.next;
            one.next = pre;
            pre=one;
            one = temp;
        }
        ListNode tail = pre;
        //头尾比较
        while(head!=null){
            if(head.val!=tail.val) return false;
            head = head.next;
            tail = tail.next;
        }
        return true;
    }


    /**
     * 单链表反转
     * @param head 链表头节点
     */
    public ListNode revertLinked(ListNode head){
        ListNode temp = null;
        ListNode pre = null;
        while (head != null){
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return head;
    }

    // 1.就地反转法
    public ListNode reverseList1(ListNode head) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy.next;
        ListNode pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = prev.next;
        }
        return dummy.next;
    }

    // 2.新建链表,头节点插入法
    public ListNode reverseList2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pCur = head;
        while (pCur != null) {
            ListNode pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        return dummy.next;
    }


    /**
     * 判断是否是环形链表
     * @param header
     * @return
     */
    public boolean isCircle(ListNode header) {
        // 边界
        if (header == null) {
            return false;
        }
        // 相同的起跑线
        ListNode faster = header, lower = header;
        // 如果跑快的人到达了终点，说明这条跑道没有环形部分
        while (faster != null && faster.getNext() != null) {
            // 跑慢的人一次跑一步
            lower = lower.getNext();
            // 跑快的人一次跑两步
            faster = faster.getNext().getNext();
            // 如果两人相遇，表示有环形部分
            if (lower == faster) {
                return true;
            }
            // 两人未相遇则继续跑（根据前边的简单说明，有环形部分则一定会在某时某刻相遇，没有环形部分，则跑快的人肯定会先跑完，所以不会死循环）
        }
        return false;
    }
}