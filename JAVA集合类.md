### JAVA集合类：

#### Collection接口：
* 定义的是所有单列集合中共性的方法
* 所有的单列集合都可以使用共性的方法
* 没有带索引的方法
    ##### List接口：
    * 有序的集合（存储和取出元素顺序相同）
    * 允许存储重复的元素
    * 有索引，可以使用普通的for循环
        ###### ArrayList集合：
        * 底层是数组实现的，在声明时尽量指定长度，可动态的增加容量。默认长度（容量）为10。非常适合用于查找，因为数组每个元素的内存空间是固定的，每次查询时，只需要去查询对应位置的内存空间，就可以很快找到相应的值。而数组不擅长的是添加和删除。线程不安全的，如果不考虑线程安全问题，一般使用ArrayList。
        ###### LinkedList集合：
        * 底层是是基于链表实现的，是一个双向循环列表。可以被当做堆栈使用。
        ###### Vector集合：
        * 底层也是数组实现的，和ArrayList一样都是封装了一个Object[]，但Vector是一个比较古老的集合，JDK1.0就已经存在，建议不要使用这个集合，Vector是线程安全的，Vector的很多方法都加入了synchronized同步语句，来确保线程安全。不过效率比较低。
    ##### Set接口：
    * 不允许存储重复元素
    * 没有索引
    * 无序的集合（存储和取出元素的顺序有可能不一致）
        ###### TreeSet集合：
        * 一个排序的集合，实现了NavigableSet、SortedSet、Set接口，底层基于 TreeMap 来实现。TreeSet 利用 TreeMap 中的key元素来存放元素。
        ###### HashSet集合：
        * 一个输入输出无序的集合，底层基于 HashMap 来实现，HashSet 利用 HashMap 中的key元素来存放元素。
    ##### Queue接口：
    * 用于模拟队列这种数据结构，队列通常是指“先进先出”（FIFO）的容器。新元素插入（offer）到队列的尾部，访问元素（poll）操作会返回队列头部的元素。通常，队列不允许随机访问队列中的元素。
        ##### Deque接口：
        * 双端队列，顾名思义，就是可以在队头和队尾进行插入或删除操作。(Queue的子接口)
            ###### LinkedList集合：
            * 底层是是基于链表实现的，是一个双向循环列表。可以被当做堆栈使用。
![Collection](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Collection%E6%8E%A5%E5%8F%A3.png)

#### Map接口：
* Map 是一种键-值对（key-value）集合，Map 集合中的每一个元素都包含一个键对象和一个值对象。其中，键对象不允许重复，而值对象可以重复，并且值对象还可以是 Map 类型的，就像数组中的元素还可以是数组一样。
    ###### HashMap集合：
    * 基于哈希表的 Map 接口的实现。此实现提供所有可选的映射操作，并允许使用 null 值和 null 键。（除了非同步和允许使用 null 之外，HashMap 类与 Hashtable 大致相同。）此类不保证映射的顺序，特别是它不保证该顺序恒久不变。
    ###### TreeMap集合：
    * 基于红黑树(一种平衡的二叉树)实现。TreeMap没有调优选项，因为该树总处于平衡状态。
![Map](https://raw.githubusercontent.com/JackyST0/Java-Technology-Stack/master/%E7%9B%B8%E5%85%B3%E5%9B%BE%E7%89%87/Map%E6%8E%A5%E5%8F%A3.png)


