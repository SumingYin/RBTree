# RBTree
java实现红黑树，添加、删除的值为整型，以后工作可能会需要。
主要方法：
add、delete、isContain、search

/**
 * @param val 根据值进行添加，若val已经存在，也显示添加成功
 *            红黑树,0代表红,1代表黑
 * @return
 */
 public boolean add(int val)
 
/**
 * 
 * @param val 选择需要删除的值
 * @return
 */
public boolean delete(int val)

/**
 * 
 * @param val 查找值是否存在，若存在返回true，不存在返回false
 * @return
 */
public boolean isContain(int val)

/**
 * 
 * @param val 根据值寻找节点
 * @return 找到该节点，返回当前节点的引用
 */
public Node search(int val)
