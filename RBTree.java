package com.ysm.offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;


/**
 * 对整数进行红黑树排序
 * 当前实现添加的操作，之后实现删除的操作
 * 红黑树,0代表红,1代表黑
 */
public class RBTree {

    Node root = null;
    // 构造器
    public RBTree() {

    }

    @Test
    public  void test() {
        RBTree rbTree = new RBTree();
//        for (int i = 0; i < 10; i++) {
//            rbTree.add(i);
//        }
        // 测试左右情况
        rbTree.add(6);
        rbTree.add(4);
        rbTree.add(5);
        System.out.println(rbTree);
    }
    @Test
    public  void test1() {
        RBTree rbTree = new RBTree();
//        for (int i = 0; i < 10; i++) {
//            rbTree.add(i);
//        }
        // 测试右左情况
        rbTree.add(4);
        rbTree.add(6);
        rbTree.add(5);
        System.out.println(rbTree);
    }
    @Test
    public  void test2() {
        RBTree rbTree = new RBTree();
        for (int i = 0; i < 10; i++) {
            rbTree.add(i);
        }
        System.out.println(rbTree);
    }


    @Test
    public  void test3() {
        RBTree rbTree = new RBTree();
        //创建一个对象
        Random df = new Random();
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //引用nextInt()方法，随机加入到树中
            int number = df.nextInt(101);
            integers.add(number);
            rbTree.add(number);
        }


        System.out.println(rbTree);
    }



    /**
     * @param val 根据值进行添加，若val已经存在，也显示添加成功
     *            红黑树,0代表红,1代表黑
     * @return
     */
    public boolean add(int val) {

        /*
        寻找添加的位置，若存在相等的数据则直接返回添加成功

         */

        Node currentNode = new Node(val);
        // 将该节点的颜色变为红色
        currentNode.color = 0;
        // 空树的情况
        if (root == null) {
            root = currentNode;
            root.color = 1;
        }

        // 查找插入的位置
        Node temp = root;
        // 根位置的值先判断
        if (temp.val == currentNode.val) {
            return true;
        }
        while (temp.val != currentNode.val) {
            if (temp.val < currentNode.val) {
                if (temp.rightSub == null) {
                    temp.rightSub = currentNode;
                    // temp的右孩子
                    currentNode.position = 2;
                    currentNode.parent = temp;
                    break;
                } else {
                    temp = temp.rightSub;
                }
            } else if (temp.val > currentNode.val) {
                if (temp.leftSub == null) {
                    temp.leftSub = currentNode;
                    // temp 的左孩子
                    currentNode.position = 1;
                    currentNode.parent = temp;
                    break;
                } else {
                    temp = temp.leftSub;
                }
            }
            if (temp.val == currentNode.val) {
                return true;
            }
        }

        /*
        fix 修正当前树的形状或者颜色
         */
        Node cParent = currentNode.parent;
        // 情况一：当前父节点为黑色的节点，直接返回添加成功
        if (cParent.color == 1) {
            return true;
        }

        // 情况2：涉及多种情况包括变色，旋转，是一种递归的过程

        // 当前节点的父节点是红色，并且该currentNode不是根节点
        while (currentNode != root && cParent.color == 0) {

            Node uncle = cParent.getAnotherSub(cParent);
            // 必然有grandParent
            Node grandPa = cParent.parent;
            // 符合判定条件下的颜色的转换
            if (uncle != null && uncle.color == 0) {
                cParent.color = 1;
                uncle.color = 1;
                currentNode = grandPa;
                if (currentNode == root) {
                    return true;
                }
                currentNode.color = 0;

            } else if (uncle == null || uncle.color == 1) {
                // uncle 为null 或者 uncle的颜色为黑色
                // 需要判断属于哪一种情况左左，左右，右右，右左
                // 用0、1、2、3来表示
                int situation = currentNode.parentSubRelation(currentNode);

                if (situation == 0) {
                    // 情况为左左，祖父节点需要右旋
                    if (grandPa.parent == null) {
                        // 此时grandPa是根节点root，此时父节点变为root节点
                        root = cParent;
                    }

                    rightRotate(grandPa);

                } else if (situation == 1) {
                    // 情况为左右
                    // 1.父节点先左旋，并变为当前节点
                    // 2.当前节点的祖父节点再右旋
                    // 3.current节点不需要动，仍指向原来节点
                    Node current1 = currentNode.parent;
                    leftRotate(current1);
                    // 此时颜色不应改变
                    current1.parent.color = 0;
                    // 祖父节点必为黑色
                    Node grandPa1 = current1.parent.parent;
                    if(grandPa1.parent==null){
                        root = grandPa1.leftSub;
                    }
                    rightRotate(grandPa1);


                } else if (situation == 2) {
                    // 情况为右右，父节点需要左旋
                    if (grandPa.parent == null) {
                        // 此时grandPa是根节点root，此时父节点变为root节点
                        root = cParent;
                    }
                    leftRotate(grandPa);

                } else if (situation == 3) {
                    // 情况为右左
                    // 1.父节点先右旋，并变为当前节点
                    // 2.当前节点的祖父节点再右旋
                    // 3.current节点不需要动，仍指向原来节点
                    Node current1 = currentNode.parent;
                    rightRotate(current1);
                    // 此时颜色不应改变
                    current1.parent.color = 0;
                    // 祖父节点必为黑色
                    Node grandPa1 = current1.parent.parent;
                    if(grandPa1.parent==null){
                        root = grandPa1.rightSub;
                    }
                    leftRotate(grandPa1);

                }
            }
            cParent = currentNode.parent;
        }


        return true;
    }

    // 左旋
    public void leftRotate(Node currentNode) {
        currentNode.color = 0;
        Node rightSub = currentNode.rightSub;
        rightSub.color = 1;
        Node parent = currentNode.parent;

        if (parent != null) {
            // 1 代表原节点是原来的左孩子
            if (currentNode.position == 1) {
                parent.leftSub = rightSub;
                rightSub.position = 1;
                rightSub.parent = parent;
            } else {
                // 2 代表源节点是原来的右孩子
                parent.rightSub = rightSub;
                rightSub.position = 2;
                rightSub.parent = parent;
            }
        } else {
            // 此时该节点为root根节点，没有父节点
            rightSub.parent = null;
            rightSub.position = 0;
        }


        if(rightSub.leftSub != null){
            rightSub.leftSub.position = 2;
            rightSub.leftSub.parent = currentNode;
            currentNode.rightSub = rightSub.leftSub;
        }else{
            // 此时已不存在左孩子
            currentNode.rightSub = null;
        }

        // 成对出现
        // 更改当前右旋节点的位置
        currentNode.position = 1;
        rightSub.leftSub = currentNode;
        currentNode.parent = rightSub;
    }

    // 右旋
    public void rightRotate(Node currentNode) {

        currentNode.color = 0;
        Node leftSub = currentNode.leftSub;
        leftSub.color = 1;
        Node parent = currentNode.parent;

        if (parent != null) {
            // 1 代表原节点是原来的左孩子
            if (currentNode.position == 1) {
                parent.leftSub = leftSub;
                leftSub.position = 1;
                leftSub.parent = parent;
            } else {
                // 2 代表源节点是原来的右孩子
                parent.rightSub = leftSub;
                leftSub.position = 2;
                leftSub.parent = parent;
            }
        } else {
            // 此时该节点为root根节点，没有父节点
            leftSub.parent = null;
            leftSub.position = 0;
        }


        if(leftSub.rightSub != null){
            leftSub.rightSub.position = 1;
            leftSub.rightSub.parent = currentNode;
            currentNode.leftSub = leftSub.rightSub;
        }else{
            // 此时已不存在左孩子
            currentNode.leftSub = null;
        }

        // 成对出现
        // 更改当前右旋节点的位置
        currentNode.position = 2;
        leftSub.rightSub = currentNode;
        currentNode.parent = leftSub;


    }


    // 根据值来进行删除
    public boolean delete(int val) {
        return true;
    }


    // 成员内部类
    class Node {
        // 节点的值
        int val;
        // 节点的两个子节点
        // 节点的夫节点
        Node leftSub = null;
        Node rightSub = null;
        Node parent = null;

        // 红黑树,0代表红,1代表黑
        int color;

        // 在父节点中的位置
        // 0,1,2分别代表无父节点，1代表是父节点的左孩子，2代表是父节点的右孩子
        int position;

        public Node(int val) {
            this.val = val;
        }

        /**
         * @param node 获取兄弟节点
         * @return
         */
        public Node getAnotherSub(Node node) {
            Node parent = node.parent;
            if (parent != null) {
                if (parent.leftSub != node) {
                    return parent.leftSub;
                } else {
                    return parent.rightSub;
                }
            }

            return null;
        }

        /**
         * @param subNode 0、1、2、3分别代表左左、左右、右右、右左
         * @return
         */
        public int parentSubRelation(Node subNode) {

            Node parent = subNode.parent;
            Node grandPa = parent.parent;

            if (grandPa.leftSub == parent) {
                if (parent.leftSub == subNode) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if (parent.rightSub == subNode) {
                    return 2;
                } else {
                    return 3;
                }

            }
        }

        public Node(int val, Node leftSub, Node rightSub, Node parent) {
            this.val = val;
            this.leftSub = leftSub;
            this.rightSub = rightSub;
            this.parent = parent;
        }

        public Node() {

        }
    }

}
