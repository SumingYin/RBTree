# RBTree
java实现红黑树，添加、删除的值为整型，以后工作可能会需要。
主要方法：
add、delete、isContain、search

根据值进行添加，若val已经存在，也显示添加成功
红黑树,0代表红,1代表黑

public boolean add(int val)
 
选择需要删除的值

public boolean delete(int val)

查找值是否存在，若存在返回true，不存在返回false

public boolean isContain(int val)

根据值寻找节点,找到该节点，返回该节点的引用

public Node search(int val)
