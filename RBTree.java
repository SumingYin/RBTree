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

    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int num = random.nextInt(100);
            integers.add(num);
            rbTree.add(num);
        }
        int size = integers.size();
        for (int i = 0; i < size; i++) {
            System.out.println(rbTree.delete(integers.get(size - 1 - i)));
        }
        System.out.println(rbTree.root == null);
        System.out.println(rbTree);
    }


    Node root = null;

    // 构造器
    public RBTree() {

    }

    @Test
    public void test() {
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
    public void test1() {
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
    public void test2() {
        RBTree rbTree = new RBTree();
        for (int i = 0; i < 10; i++) {
            rbTree.add(i);
        }
        System.out.println(rbTree);
    }


    @Test
    public void test3() {
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

    @Test
    public void test4() {
        RBTree rbTree = new RBTree();
        // 测试删除操作，删除的节点为红色
        /*
                    69(黑)
                   /    \
                 29(红) 91(黑)
                /  \      /     \
           10(黑)  59(黑) 89(红) 92(红)
                   /   \
                  33(红) 68(红)
         */
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(69);
        integers.add(29);
        integers.add(92);
        integers.add(10);
        integers.add(68);
        integers.add(59);
        integers.add(91);
        integers.add(33);
        integers.add(89);
        integers.add(69);
        for (int i = 0; i < integers.size(); i++) {
            rbTree.add(integers.get(i));
        }

        System.out.println(rbTree.delete(91));
        System.out.println(rbTree);
    }

    @Test
    public void test5() {

        RBTree rbTree = new RBTree();
        // 测试删除操作，删除的节点为黑色节点、该黑色节点无孩子，且兄弟节点为红色,测试成功
        /*
                    69(黑)
                   /    \
                 29(红) 92(黑)
                /  \
           10(黑)  59(黑)
                   /   \
                  33(红) 68(红)
         */
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(69);
        integers.add(29);
        integers.add(92);
        integers.add(10);
        integers.add(68);
        integers.add(59);
//        integers.add(91);
        integers.add(33);
//        integers.add(89);
//        integers.add(69);
        for (int i = 0; i < integers.size(); i++) {
            rbTree.add(integers.get(i));
        }

        System.out.println(rbTree.delete(92));
        System.out.println(rbTree);
    }

    @Test
    public void test6() {

        RBTree rbTree = new RBTree();
        // 测试删除操作，删除所有节点
        /*
                    69(黑)
                   /    \
                 29(红) 92(黑)
                /  \
           10(黑)  59(黑)
                   /   \
                  33(红) 68(红)
         */
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(69);
        integers.add(29);
        integers.add(92);
        integers.add(10);
        integers.add(68);
        integers.add(59);
//        integers.add(91);
        integers.add(33);
//        integers.add(89);
//        integers.add(69);
        for (int i = 0; i < integers.size(); i++) {
            rbTree.add(integers.get(i));
        }
        int size = integers.size();
        for (int i = 0; i < size; i++) {
            System.out.println(rbTree.delete(integers.get(size - 1 - i)));
        }
        System.out.println(rbTree.root == null);
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
                    if (grandPa1.parent == null) {
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
                    if (grandPa1.parent == null) {
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
            root = rightSub;
        }


        if (rightSub.leftSub != null) {
            rightSub.leftSub.position = 2;
            rightSub.leftSub.parent = currentNode;
            currentNode.rightSub = rightSub.leftSub;
        } else {
            // 此时已不存在左孩子
            currentNode.rightSub = null;
        }

        // 成对出现
        // 更改当前右旋节点的位置
        currentNode.position = 1;
        rightSub.leftSub = currentNode;
        currentNode.parent = rightSub;
    }

    // 正常的左旋，不改变颜色,去掉对颜色的操作即可
    public void nomalLeftRotate(Node currentNode) {

        Node rightSub = currentNode.rightSub;
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
            root = rightSub;
        }


        if (rightSub.leftSub != null) {
            rightSub.leftSub.position = 2;
            rightSub.leftSub.parent = currentNode;
            currentNode.rightSub = rightSub.leftSub;
        } else {
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
            root = leftSub;
        }


        if (leftSub.rightSub != null) {
            leftSub.rightSub.position = 1;
            leftSub.rightSub.parent = currentNode;
            currentNode.leftSub = leftSub.rightSub;
        } else {
            // 此时已不存在左孩子
            currentNode.leftSub = null;
        }

        // 成对出现
        // 更改当前右旋节点的位置
        currentNode.position = 2;
        leftSub.rightSub = currentNode;
        currentNode.parent = leftSub;


    }

    // 正常右旋，不改变颜色，去掉对颜色的操作即可
    public void nomalRightRotate(Node currentNode) {
        Node leftSub = currentNode.leftSub;
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
            root = leftSub;
        }


        if (leftSub.rightSub != null) {
            leftSub.rightSub.position = 1;
            leftSub.rightSub.parent = currentNode;
            currentNode.leftSub = leftSub.rightSub;
        } else {
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

        // 首先找到需要删除的值
        Node deleteNode = search(val);
        // 删除的节点不存在
        if (deleteNode == null) {
            // 该值本来就不存在，返回true
            return true;
        } else {

            Node dNparent = null;
            Node dSub = null;
            // 外层循环最多执行一次
            while(true){
                // 好好整理一下逻辑，判断孩子的个数
                int subSituation = subSituation(deleteNode);
                if (subSituation == 0) {
                    // 删除节点无孩子，且为红色
                    if (deleteNode.color == 0) {
                        nomalDelete(deleteNode);
                        return true;
                    }
                    dNparent = deleteNode.parent;
                    if (dNparent == null) {
                        // 说明是根节点
                        nomalDelete(deleteNode);

                        return true;
                    }


                    // 循环解决特殊情况，该删除节点无孩子且为黑色
                    boolean isDeleted = false;
                    while (deleteNode != null && deleteNode != root) {
                        // 获取兄弟节点
                        dSub = deleteNode.getAnotherSub(deleteNode);
                        dNparent = deleteNode.parent;
                        // 此时可将该节点删除
                        if (!isDeleted) {
                            nomalDelete(deleteNode);
                            isDeleted = true;
                        }
                        if (deleteNode.color == 0) {
                            // 下一次循环出现红色的情况
                            deleteNode.color = 1;
                            return true;
                        }
                        // case 1 :删除节点的兄弟为红色，则该兄弟的孩子存在必然全为黑色，且不为null，父亲也为黑色
                        // 改变父亲和兄弟的颜色，同时对父亲进行左旋或右旋，看dSub的位置，case 1变为case 2、case 3、case 4的一种
                        if (dSub.color == 0) {
                            // 此时 只有兄弟节点发生了改变
                            if (dSub.position == 2) {
                                nomalLeftRotate(dNparent);
                                dSub.color = 1;
                                dNparent.color = 0;
                                dSub = dNparent.rightSub;
                            } else {
                                nomalRightRotate(dNparent);
                                dSub.color = 1;
                                dNparent.color = 0;
                                dSub = dNparent.leftSub;
                            }
                        }

                        int dSubSubSituation = subSituation(dSub);
                        // case 2: 删除节点的兄弟节点为黑色，且兄弟节点的孩子均为null或全为黑色
                        if (dSub.color == 1 && (dSubSubSituation == 0 || (dSubSubSituation == 2 && dSub.leftSub.color == 1 && dSub.rightSub.color == 1))) {
                            dSub.color = 0;
                            deleteNode = dSub.parent;
                            // 直接进入下一次循环
                            continue;
                        }

                        // 分情况讨论，左右操作是不一样的
                        if (dSub.position == 2) {
                            // 兄弟节点在右侧
                            // case 3: 兄弟节点为黑色，且左孩子为红色
                            // 右旋交换兄弟节点与其左孩子的颜色，即兄弟变红，孩子变黑，此时删除节点的兄弟节点发生变化，变为左孩子
                            // case 3 进入到 case 4
                            if (dSub.color == 1 && (dSub.leftSub != null && dSub.leftSub.color == 0)) {
                                Node dSubLeft = dSub.leftSub;
                                nomalRightRotate(dSub);
                                dSub.color = 0;
                                dSubLeft.color = 1;
                                dSub = dSubLeft;
                            }
                            // case 4: 兄弟节点为黑色，且右孩子为红色
                            // 交换兄弟节点与删除父节点的颜色，同时对父节点进行左旋，将右孩子置黑
                            // 此时必达到平衡，可直接返回
                            if (dSub.color == 1 && (dSub.rightSub != null && dSub.rightSub.color == 0)) {
                                dNparent = dSub.parent;
                                int tempColor = dNparent.color;
                                dNparent.color = dSub.color;
                                dSub.color = tempColor;
                                dSub.rightSub.color = 1;
                                nomalLeftRotate(dNparent);
                                return true;
                            }
                        } else {

                            // 兄弟节点在左侧
                            // case 3: 兄弟节点为黑色，且左孩子为红色
                            // 右旋交换兄弟节点与其左孩子的颜色，即兄弟变红，孩子变黑，此时删除节点的兄弟节点发生变化，变为左孩子
                            // case 3 进入到 case 4
                            if (dSub.color == 1 && (dSub.rightSub != null && dSub.rightSub.color == 0)) {
                                Node dSubRight = dSub.rightSub;
                                nomalLeftRotate(dSub);
                                dSub.color = 0;
                                dSubRight.color = 1;
                                dSub = dSubRight;
                            }
                            // case 4: 兄弟节点为黑色，且右孩子为红色
                            // 交换兄弟节点与删除父节点的颜色，同时对父节点进行左旋，将右孩子置黑
                            // 此时必达到平衡，可直接返回
                            if (dSub.color == 1 && (dSub.leftSub != null && dSub.leftSub.color == 0)) {
                                dNparent = dSub.parent;
                                int tempColor = dNparent.color;
                                dNparent.color = dSub.color;
                                dSub.color = tempColor;
                                dSub.leftSub.color = 1;
                                nomalRightRotate(dNparent);
                                return true;
                            }

                        }


                    }
                    if(deleteNode == null){
                        System.out.println("出错啦");
                        return false;
                    }else{
                        return true;
                    }

                }
                else if (subSituation == 1) {
                    // 该节点存在一个孩子，此时可判定该节点必为黑色，且孩子必为红色
                    // 返回替换的引用
                    Node node = nomalDelete(deleteNode);
                    node.color = 1;
                    return true;

                }
                else {
                    // 该节点存在两个孩子，找到替换节点，将值进行替换
                    // 之后右回到原来的if(subSituation == 0) 或 else if( subStiuation == 1 )的情况，
                    // 并且只能是这两种情况之一
                    if(deleteNode.rightSub != null){
                        Node tempRight = deleteNode.rightSub;
                        Node tempChange = tempRight;
                        while(tempChange.leftSub != null){
                            tempChange = tempChange.leftSub;
                        }
                        // 需要将值进行交换
                        deleteNode.val = tempChange.val;
                        deleteNode = tempChange;
                    }
                }
            }
        }
    }

    /**
     * @param node 传入的节点
     * @return 返回0、1、2分别代表该节点无孩子，有一个孩子，有两个孩子
     */
    public int subSituation(Node node) {

        if (node.leftSub == null && node.rightSub == null) {
            return 0;
        } else if (node.leftSub != null && node.rightSub != null) {
            return 2;
        } else {
            return 1;
        }

    }

    // 查找值是否存在，若存在返回true，不存在返回false
    public boolean isContain(int val) {

        Node temp = root;

        if (temp != null && temp.val == val) {
            return true;
        }

        while (temp != null && temp.val != val) {
            if (temp.val < val) {
                temp = temp.rightSub;
            } else if (temp.val > val) {
                temp = temp.leftSub;
            } else {
                // 相等
                return true;
            }
        }
        return false;
    }

    // 找到该节点，返回当前节点的引用
    public Node search(int val) {

        Node temp = root;

        if (temp != null && temp.val == val) {
            return root;
        }

        while (temp != null && temp.val != val) {
            if (temp.val < val) {
                temp = temp.rightSub;
            } else if (temp.val > val) {
                temp = temp.leftSub;
            }

        }
        if (temp == null) {
            return null;
        } else if (temp.val == val) {
            return temp;
        }

        return null;
    }

    // 普通的二叉排序树的删除操作

    /**
     * @param deleteNode 需要删除的节点
     * @return 返回替代删除节点的引用
     */
    public Node nomalDelete(Node deleteNode) {
        if (deleteNode == null) {
            return null;
        }
        // 右子树最小值替代，左子树最大值替代
        // 该删除节点的父节点、父节点的左孩子or右孩子
        Node parent = deleteNode.parent;
        int position = deleteNode.position;
        // 如果该节点无子孙可直接进行删除
        if (deleteNode.leftSub == null && deleteNode.rightSub == null) {

            if (parent != null) {
                if (position == 1) {
                    parent.leftSub = null;
                } else {
                    parent.rightSub = null;
                }
            }else {
                // 为根节点，没有可替代的节点存在，返回null
                root = null;
                return null;
            }

        } else if (deleteNode.rightSub != null) {
            // 要么右子树存在要么左子树存在，选择其中之一
            // 右子树存在，取右子树的最小值
            Node tempright = deleteNode.rightSub;
            Node tempChange = tempright;
            while (tempChange.leftSub != null) {
                tempChange = tempChange.leftSub;
            }
            // tempChange不是tempright，需要分类讨论
            if (tempChange != tempright) {
                Node parentChange = tempChange.parent;
                // 左子树必为null，但是右子树不一定为null
                if (tempChange.rightSub != null) {
                    Node tempChangeright = tempChange.rightSub;
                    tempChangeright.parent = parentChange;
                    parentChange.leftSub = tempChangeright;
                    tempChangeright.position = 1;
                    tempChange.rightSub = tempright;
                    tempright.parent = tempChange;
                } else {
                    tempChange.rightSub = tempright;
                    tempright.parent = tempChange;
                    parentChange.leftSub = null;
                }

            }

            tempChange.parent = parent;
            tempChange.position = position;

            if (position == 1) {
                parent.leftSub = tempChange;
            } else if (position == 2) {
                parent.rightSub = tempChange;
            }
            // 删除节点的左子树情况
            if (deleteNode.leftSub != null) {
                tempChange.leftSub = deleteNode.leftSub;
                deleteNode.leftSub.parent = tempChange;
            }
            deleteNode.parent = null;
            deleteNode.leftSub = null;
            deleteNode.rightSub = null;
            return tempChange;
        } else {
            // 要么右子树存在要么左子树存在，选择其中之一
            // 左子树存在，取左子树的最大值
            Node templeft = deleteNode.leftSub;
            Node tempChange = templeft;
            while (tempChange.rightSub != null) {
                tempChange = tempChange.rightSub;
            }

            if (tempChange != templeft) {
                Node parentChange = tempChange.parent;
                if (tempChange.leftSub != null) {
                    Node tempChangeleftSub = tempChange.leftSub;
                    tempChangeleftSub.parent = parentChange;
                    parentChange.rightSub = tempChangeleftSub;
                    tempChangeleftSub.position = 2;
                    tempChange.leftSub = templeft;
                    templeft.parent = tempChange;
                } else {
                    tempChange.leftSub = templeft;
                    templeft.parent = tempChange;
                    parentChange.rightSub = null;
                }

            }

            tempChange.parent = parent;
            tempChange.position = position;
            templeft.color = 0;
            if (position == 1) {
                parent.leftSub = tempChange;
            } else if (position == 2) {
                parent.rightSub = tempChange;
            }
            // 删除节点的右子树情况
            if (deleteNode.rightSub != null) {
                tempChange.rightSub = deleteNode.rightSub;
                deleteNode.rightSub.parent = tempChange;
            }
            deleteNode.parent = null;
            deleteNode.leftSub = null;
            deleteNode.rightSub = null;
            return tempChange;

        }
        return null;
    }

    // 普通的二叉排序树的删除操作，传入的参数不一样
    public boolean nomalDelete(Node root, int deleteVal) {
        Node deleteNode = search(deleteVal);
        if (deleteNode == null) {
            return false;
        }
        // 右子树最小值替代，左子树最大值替代
        // 该删除节点的父节点、父节点的左孩子or右孩子
        Node parent = deleteNode.parent;
        int position = deleteNode.position;
        // 如果该节点无子孙可直接进行删除
        if (deleteNode.leftSub == null && deleteNode.rightSub == null) {

            if (parent != null) {
                if (position == 1) {
                    parent.leftSub = null;
                } else {
                    parent.rightSub = null;
                }
            }else {
                // 为根节点，没有可替代的节点存在，返回null
                root = null;
                return true;
            }
        } else if (deleteNode.rightSub != null) {
            // 要么右子树存在要么左子树存在，选择其中之一
            // 右子树存在，取右子树的最小值
            Node tempright = deleteNode.rightSub;
            Node tempChange = tempright;
            while (tempChange.leftSub != null) {
                tempChange = tempChange.leftSub;
            }
            // tempChange不是tempright，需要分类讨论
            if (tempChange != tempright) {
                Node parentChange = tempChange.parent;
                // 左子树必为null，但是右子树不一定为null
                if (tempChange.rightSub != null) {
                    Node tempChangeright = tempChange.rightSub;
                    tempChangeright.parent = parentChange;
                    parentChange.leftSub = tempChangeright;
                    tempChangeright.position = 1;
                    tempChange.rightSub = tempright;
                    tempright.parent = tempChange;
                } else {
                    tempChange.rightSub = tempright;
                    tempright.parent = tempChange;
                    parentChange.leftSub = null;
                }

            }

            tempChange.parent = parent;
            tempChange.position = position;

            if (position == 1) {
                parent.leftSub = tempChange;
            } else if (position == 2) {
                parent.rightSub = tempChange;
            }
            // 删除节点的左子树情况
            if (deleteNode.leftSub != null) {
                tempChange.leftSub = deleteNode.leftSub;
                deleteNode.leftSub.parent = tempChange;
            }
            deleteNode.parent = null;
            deleteNode.leftSub = null;
            deleteNode.rightSub = null;
            return true;
        } else {
            // 要么右子树存在要么左子树存在，选择其中之一
            // 左子树存在，取左子树的最大值
            Node templeft = deleteNode.leftSub;
            Node tempChange = templeft;
            while (tempChange.rightSub != null) {
                tempChange = tempChange.rightSub;
            }

            if (tempChange != templeft) {
                Node parentChange = tempChange.parent;
                if (tempChange.leftSub != null) {
                    Node tempChangeleftSub = tempChange.leftSub;
                    tempChangeleftSub.parent = parentChange;
                    parentChange.rightSub = tempChangeleftSub;
                    tempChangeleftSub.position = 2;
                    tempChange.leftSub = templeft;
                    templeft.parent = tempChange;
                } else {
                    tempChange.leftSub = templeft;
                    templeft.parent = tempChange;
                    parentChange.rightSub = null;
                }

            }

            tempChange.parent = parent;
            tempChange.position = position;
            templeft.color = 0;
            if (position == 1) {
                parent.leftSub = tempChange;
            } else if (position == 2) {
                parent.rightSub = tempChange;
            }
            // 删除节点的右子树情况
            if (deleteNode.rightSub != null) {
                tempChange.rightSub = deleteNode.rightSub;
                deleteNode.rightSub.parent = tempChange;
            }
            deleteNode.parent = null;
            deleteNode.leftSub = null;
            deleteNode.rightSub = null;

        }
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
