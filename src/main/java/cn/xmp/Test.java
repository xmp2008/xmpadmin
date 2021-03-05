package cn.xmp;


import cn.xmp.modules.system.entity.User;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/12/9
 */
public class Test {
    public static void main(String[] args) {
//        ZI zi = new ZI();
//        String s = zi.getS();
//        loveYou(10);
//        int n = 10;
//        System.out.println("调用函数前n=" + n);
//        test(n);
//        System.out.println("调用函数后n=" + n);
//        int[] n = {1};
//        System.out.println("调用函数前n[1]=" + n[0]);
//        test1(n);
//        System.out.println("调用函数后n[1]=" + n[0]);
        User user = new User();
        user.setUsername("lisi");
        System.out.println("调用函数前user=" + user);
        test2(user);
        System.out.println("调用函数后user=" + user);
    }

    static void test2(User user) {
        user.setUsername("zhangsan");
        System.out.println("函数内user = " + user);
    }

    static void test1(int[] n) {
        n[0] = 1024;
        System.out.println("函数内n[0] = " + n[0]);
    }

    static void test(int n) {
        n = 1024;
        System.out.println("函数内n=" + n);
    }

    public static void loveYou(int n) {
        int a, b, c;
        if (n > 1) {
            loveYou(n - 1);
        }
        System.out.println("i love you" + n);
    }


}

class Sq {
    private static int MAX_SIZE = 10;
    int[] data = new int[MAX_SIZE];
    int length;

    void InitList(Sq sq) {
        sq.length = 0;
    }

    public int[] getData() {
        return data;
    }

    public static void main(String[] args) {
        Sq sq = new Sq();
        sq.InitList(sq);
        for (int i = 0; i < sq.getData().length; i++) {
            System.out.println("sq.getData()[i] = " + sq.getData()[i] + "----" + i);
        }
    }
}

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];

        // 建立k-v ，一一对应的哈希表
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target - nums[i], i);
        }
        // // 双重循环 循环极限为(n^2-n)/2
        // for(int i = 0; i < nums.length; i++){
        //     for(int j = nums.length - 1; j > i; j --){
        //         if(nums[i]+nums[j] == target){
        //            indexs[0] = i;
        //            indexs[1] = j;
        //            return indexs;
        //         }
        //     }
        // }
        return indexs;
    }

    public static void main(String[] args) {
        //创建一个原始的二维数组，0表示没有棋子，1表示黑棋子，2表示蓝棋子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][1] = 1;
        chessArr1[2][2] = 2;
        System.out.println("输出原始二维数组");
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        // 将二维数组 转 稀疏数组的思
        // 1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum++;
                }
            }

        }
        System.out.println(sum);
        // 2. 创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0; // count 用于记录是第几个非0数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("输出稀疏数组");
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
//        int sum = 0;
//        for (int i = 0; i < chessArr1.length; i++) {
//            for (int j = 0; j < chessArr1[i].length; j++) {
//                if (chessArr1[i][j] != 0) {
//                    sum++;
//                }
//            }
//        }
//        System.out.println(sum);
        int[] t1 = new int[]{1, 2, 3};
        int[] t2 = new int[t1.length + 1];


    }
}

class Quece {
    public static void main(String[] args) {
        System.out.println("17模以3的值为：" + Math.floorMod(17, 3));
        System.out.println("17除以3的余为：" + 17 % 3);
        System.out.println("-17模以-3的值为：" + Math.floorMod(-17, -3));
        System.out.println("-17除以-3的余为：" + -17 % -3);
        System.out.println("-17模以3的值为：" + Math.floorMod(-17, 3));
        System.out.println("-17除以3的余为：" + -17 % 3);
        System.out.println("17模以-3的值为：" + Math.floorMod(17, -3));
        System.out.println("17除以-3的余为：" + 17 % -3);
    }

}

/**
 * 二分查找
 */
class Test1 {

    public static void main(String[] args) {
//        List list = new ArrayList(10);
        List list = new LinkedList<>();
        list.add(1);
        List list1 = new ArrayList();
        list1.add(2);
        System.out.println("list1.get(0) = " + list1.get(0));
        System.out.println("list.get(0) = " + list.get(0));
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        //查找的目标元素
        int target = 10;
        //数组起始位置
        int begin = 0;
        //数组结束位置
        int end = 9;
        //数组中间位置
        int mid = (begin + end) / 2;
        int index = -1;
        while (true) {
            //没有找到元素
            if (begin >= end) {
                break;
            }
            //如果中间元素和目标元素相等，说明找到目标元素
            if (arr[mid] == target) {
                index = mid;
                break;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
            mid = (begin + end) / 2;
        }
        System.out.println(index);
        int i = 1;
        int i1 = i++;
        System.out.println(i1);
    }
}

class AA {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList();
        ArrayList arrayList1 = new ArrayList<String>();
        arrayList.add("ad");
//        arrayList.add(12);
        arrayList1.add("ads");
        arrayList1.add(12);
        List<String> strings = new ArrayList<>();
        if (arrayList instanceof ArrayList) {
            System.out.println("adad");
        }
    }
}

//表示一个雇员
class Emp {
    private int id;
    private String name;
    public Emp next;

    public Emp(int id, String name) {
//        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//表示一个链表
class EmpLinkedList {
    //指向第一个雇员，默认为空
    private Emp head = new Emp(0, "");

    //假设雇员id是自增长的，添加雇员直接在链表末尾添加
    public void add(Emp emp) {
        //如果是第一个雇员，直接放在链表首位
        if (null == head) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，使用辅助变量，定位到下一个雇员
        Emp curEmp = head;
        while (true) {
            if (null == curEmp.next) {
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出时将新雇员加入链表末尾
        curEmp.next = emp;
    }

    //展示列表雇员
    public void list(int no) {
        if (null == head) {
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为");
        //如果不是第一个雇员，使用辅助变量，定位到下一个雇员
        Emp curEmp = head;
        while (true) {
            if (0 != curEmp.getId()) {
                System.out.print("=>" + curEmp);
            }
            if (null == curEmp.next) {
                break;
            }

            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    public Emp getEmpById(int id) {
        //如果是第一个雇员，直接放在链表首位
        if (null == head) {
            System.out.println("链表为空");
            return null;
        }
        //如果不是第一个雇员，使用辅助变量，定位到下一个雇员
        Emp curEmp = head;
        while (true) {
            if (id == curEmp.getId()) {
                break;
            }
            curEmp = curEmp.next;//后移
            if (null == curEmp) {
                break;
            }
        }
        return curEmp;
    }

    public void delEmpById(int id) {
        Emp temp = head;
        boolean flag = false;//标记是否找到删除的节点
        while (true) {
            if (null == head.next) {//已经到链表最后
                break;
            }
            if (temp.getId() == id) {
                flag = true;
                break;
            }
            if (temp.next.getId() == id) {
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到需要删除的节点");
        }
    }
}

//创建HashTable，管理多条链表
class HashTable {
    private EmpLinkedList[] empLinkedListArr;
    private int size;

    public HashTable(int size) {
        this.size = size;
        //初始化数组大小
        this.empLinkedListArr = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        //获取id散列后的序号
        int no = this.hashCode(emp.getId());
        //往对应序号链表添加雇员
        empLinkedListArr[no].add(emp);
    }

    public void list() {
        //遍历所有链表，展示雇员
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i].list(i);
        }
    }

    public Emp getById(int id) {
        //获取id散列后的序号
        int no = this.hashCode(id);
        //
        Emp emp = empLinkedListArr[no].getEmpById(id);
        return emp;
    }

    public void delById(int id) {
        //获取id散列后的序号
        int no = this.hashCode(id);
        //
        empLinkedListArr[no].delEmpById(id);
    }

    //散列函数
    public int hashCode(int id) {
        return id % size;
    }

    public static void main(String[] args) {
        Emp zhangsan = new Emp(1, "zhangsan");
        Emp lisi = new Emp(2, "lisi");
        Emp wangwu = new Emp(3, "wangwu");
        Emp wangwu1 = new Emp(11, "wangwu1");
        Emp wangwu0 = new Emp(10, "wangwu0");

        HashTable hashTable = new HashTable(10);
        hashTable.add(zhangsan);
        hashTable.add(lisi);
        hashTable.add(wangwu);
        hashTable.add(wangwu1);
        hashTable.add(wangwu0);
        hashTable.list();
        Emp emp = hashTable.getById(11);
        System.out.println("emp = " + emp);
        System.out.println("==============");
        hashTable.delById(0);
        System.out.println("删除后");
        hashTable.list();
    }
}

//class Node {
//    private int id;
//    private String name;
//    public Node next;
//
//    public Node(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Node() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    @Override
//    public String toString() {
//        return "Node{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
//
//class NodeList {
//    private Node head = new Node();//头链表，默认为空
//
//
//    public void add(Node node) {
//        Node temp = head;
//        while (true) {
//            //找到链表最后
//            if (null == temp.next) {
//                break;
//            }
//            temp = temp.next;//后移迭代
//        }
//        temp.next = node;
//    }
//
//    public void list() {
//        if (null == head.next) {
//            throw new RuntimeException("链表为空");
//        }
//        Node temp = head.next;
//        while (true) {
//            if (null == temp) {
//                break;
//            }
//            System.out.println("temp = " + temp);
//            temp = temp.next;//后移迭代
//        }
//    }
//
//    public Node getById(int id) {
//        boolean flag = false;//标记是否找到节点
//        Node temp = head;
//        while (true) {
//            if (null == temp.next) {
//                break;
//            }
//            if (id == temp.getId()) {//找到元素，跳出循环
//                flag = true;
//                break;
//            }
//            temp = temp.next;//后移，迭代
//        }
//        if (flag) {
//            return temp;
//        } else {
//            return null;
//        }
//    }
//
//    public void delNodeById(int id) {
//        boolean flag = false;//标记是否找到节点
//        Node temp = head.next;
//        while (true) {
//            if (null == temp) {
//                break;
//            }
//            if (id == temp.getId()) {
//                flag = true;
//                break;
//            }
//            temp = temp.next;
//        }
//        if (flag) {
//            temp.next = temp.next.next;
//        } else {
//            throw new RuntimeException("编码" + id + "不存在");
//        }
//    }
//
//    //逆向打印链表
//    public static void reversePrint(NodeList nodeList) {
//        Node head = nodeList.head;
//        //空链表，直接返回
//        if (null == head.next) {
//            return;
//        }
//        Stack<Node> nodes = new Stack<>();
//        Node cur = head.next;
//        while (cur != null) {
//            nodes.push(cur);
//            cur = cur.next;//后移
//        }
//        while (nodes.size() > 0) {
//            System.out.println("nodes.pop() = " + nodes.pop());
//        }
//    }
//
//    public static void main(String[] args) {
//        Node node = new Node(1, "ad1");
//        Node node1 = new Node(2, "ad2");
//        Node node2 = new Node(3, "ad3");
//        Node node3 = new Node(4, "ad4");
//        Node node4 = new Node(5, "ad5");
//        NodeList nodeList = new NodeList();
////        System.out.println("nodeList.getById(1) = " + nodeList.getById(1));
//        nodeList.add(node);
//        nodeList.add(node1);
//        nodeList.add(node2);
//        nodeList.add(node3);
//        nodeList.add(node4);
//        nodeList.list();
//        System.out.println("nodeList.getById(3) = " + nodeList.getById(3));
//        nodeList.delNodeById(4);
//        System.out.println("删除后");
//        nodeList.list();
//        System.out.println("逆向打印");
//        reversePrint(nodeList);
//    }
//}

//双向链表
class DoubleNode {
    private int id;
    private String name;
    private DoubleNode next;
    private DoubleNode pre;

    public DoubleNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public DoubleNode getPre() {
        return pre;
    }

    public void setPre(DoubleNode pre) {
        this.pre = pre;
    }

    public DoubleNode() {
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//管理双向链表
class DoubleLinkedList {
    private DoubleNode head = new DoubleNode();//链表头，默认为空

    //添加节点到末尾节点
    public void add(DoubleNode node) {
        DoubleNode temp = head;
        while (true) {
            if (null == temp.getNext()) {//找到末尾节点
                break;
            }
            temp = temp.getNext();//后移，遍历
        }
        temp.setNext(node);
        node.setPre(temp);
    }

    public void list() {
        if (null == head.getNext()) {
            throw new RuntimeException("链表为空");
        }
        DoubleNode cur = head.getNext();
        while (null != cur) {
            System.out.println(cur);
            cur = cur.getNext();
        }
    }

    public void delNodeById(int id) {
        if (null == head.getNext()) {
            throw new RuntimeException("链表为空");
        }
        DoubleNode cur = head.getNext();
        while (null != cur) {
            if (id == cur.getId()) {//找到节点，跳出循环
                break;
            }
            cur = cur.getNext();//后移遍历
        }
        if (null != cur.getNext()) {
            cur.getNext().setPre(cur.getPre());
        }
        cur.getPre().setNext(cur.getNext());
    }

    public static void main(String[] args) {
        DoubleNode node = new DoubleNode(1, "abc");
        DoubleNode node1 = new DoubleNode(2, "abc1");
        DoubleNode node2 = new DoubleNode(3, "abc2");
        DoubleNode node3 = new DoubleNode(4, "abc3");
        DoubleNode node4 = new DoubleNode(5, "abc4");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node);
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        doubleLinkedList.list();
        doubleLinkedList.delNodeById(3);
        System.out.println("删除3后");
        doubleLinkedList.list();
        doubleLinkedList.delNodeById(4);
        System.out.println("删除4后");
        doubleLinkedList.list();
        doubleLinkedList.delNodeById(5);
        System.out.println("删除5后");
        doubleLinkedList.list();
    }
}


//单向链表
class Node {
    private int id;
    private String data;
    private Node next;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}

//管理单向链表
class NodeLinkedList {
    private Node head = new Node();


    //添加
    public void add(Node node) {
        Node temp = head;
        while (null != temp.getNext()) {
            temp = temp.getNext();//后移
        }
        temp.setNext(node);
    }

    public void list() {
        Node temp = head.getNext();
        while (null != temp && null != head.getNext()) {
            System.out.println(temp);
            temp = temp.getNext();//后移
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, "qqq1");
        Node node2 = new Node(2, "qqq2");
        Node node3 = new Node(3, "qqq3");
        Node node4 = new Node(4, "qqq4");
        Node node5 = new Node(5, "qqq5");
        Node node6 = new Node(6, "qqq6");
        NodeLinkedList nodeLinkedList = new NodeLinkedList();
        nodeLinkedList.add(node1);
        nodeLinkedList.add(node2);
        nodeLinkedList.add(node3);
        nodeLinkedList.add(node4);
        nodeLinkedList.add(node5);
        nodeLinkedList.add(node6);

        nodeLinkedList.list();
    }
}

class TestRef {
    public static void main(String[] args) {
        Emp emp1 = new Emp(1, "zs");
        Emp emp2 = new Emp(2, "ls");
        swap(emp1, emp2);
        System.out.println("emp1 = " + emp1);
        System.out.println("emp2 = " + emp2);
    }

    public static void swap(Emp emp1, Emp emp2) {
        Emp temp = emp1;
        emp1 = emp2;
        emp2 = temp;
        System.out.println("emp1 = " + emp1);
        System.out.println("emp2 = " + emp2);
    }
}

class Hero {
    public String name() {
        return "超级英雄";
    }
}

class SuperMan extends Hero {
    @Override
    public String name() {
        return "超人";
    }

    public Hero hero() {
        return new Hero();
    }
}

class SuperSuperMan extends SuperMan {
    public String name() {
        return "超级超级英雄";
    }

    @Override
    public SuperMan hero() {
        return new SuperMan();
    }

    public static void main(String[] args) {
        Hero hero = new Hero();
        System.out.println("hero.name() = " + hero.name());
        SuperMan superMan = new SuperMan();
        System.out.println("superMan.name() = " + superMan.name());//重写name方法
        System.out.println("superMan.hero().name() = " + superMan.hero().name());
        SuperSuperMan superSuperMan = new SuperSuperMan();
        System.out.println("superSuperMan.name() = " + superSuperMan.name());
        System.out.println("superSuperMan.hero().name() = " + superSuperMan.hero().name());
    }
}

class Fu {
    private String data;

    public Fu(String data) {
        this.data = data;
    }

    public Fu() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fu fu = (Fu) o;

        return data != null ? data.equals(fu.data) : fu.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Fu{" +
                "data='" + data + '\'' +
                '}';
    }
//    @Override
//    public String toString() {
//        return super.toString();
//    }

    public static void main(String[] args) {
        String a = Zi.a;
        Fu fu = new Fu("ada");
        System.out.println("fu = " + fu);
    }
}

class Zi extends Fu {
    static String a = "adasd";
    public String a1 = "adasd";

    public static void main(String[] args) {
        Zi zi = new Zi();
        final String a = "adad";
        System.out.println("Zi.a = " + Zi.a);
    }

}

//逆波兰表达式
class ReversePolishNotation {
    public static void main(String[] args) {
        String expression = "4 5 * 8 - 60 + 8 2 / +";
        String[] split = expression.split(" ");
        List<String> stringList = Arrays.asList(split);
        System.out.println("stringList = " + stringList);
        int calculate = calculate(stringList);
        System.out.println(calculate);

        String exp = "1+((2+3)*4)-5";
        List<String> expressionList = toInfixExpressionList(exp);
        System.out.println("expressionList = " + expressionList);
        List<String> parseSuffixExpreesionList = parseSuffixExpreesionList(expressionList);
        System.out.println("parseSuffixExpreesionList = " + parseSuffixExpreesionList);
    }


    private static int calculate(List<String> stringList) {
        Deque<String> stack = new ArrayDeque<>();
        for (String item : stringList) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num2 / num1;
                } else if (item.equals("-")) {
                    res = num2 - num1;
                } else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //中缀表达式转后缀表达式
    //将中缀表达式转换为对应的list
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char c;

        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 59) {
                ls.add("" + c);//非数字放入list
                i++;//后移
            } else {
                str = "";
                //考虑多位数字
                while (i < s.length() && ((c = s.charAt(i)) > 48 && (c = s.charAt(i)) < 59)) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将中缀表达式list转为后缀表达式list
    public static List<String> parseSuffixExpreesionList(List<String> expressionList) {
        Deque<String> operStack = new ArrayDeque<>();//符号栈
        List<String> stack = new ArrayList<>();//第二栈不做出栈操作，用list替代
        for (String item : expressionList) {
            if (item.matches("\\d+")) {
                stack.add(item);//匹配数字，直接入栈
            } else if ("(".equals(item)) {
                operStack.push(item);//(直接入符号栈
            } else if (")".equals(item)) {
                //如果是)，将operStack符号栈里面的符号弹出放入第二个栈，直到遇到(,将括号丢弃
                while (!operStack.peek().equals("(")) {
                    stack.add(operStack.pop());
                }
                operStack.pop();//丢弃(
            } else {
                //比较运算符优先级
                while (operStack.size() != 0 && (Operation.getValue(operStack.peek()) >= Operation.getValue(item))) {
                    stack.add(operStack.pop());
                }
                operStack.push(item);
            }
        }
        while (operStack.size() != 0) {
            stack.add(operStack.pop());
        }
        return stack;
    }
}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int LEFT_BRACKET = 0;
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    // 写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "(":
                result = LEFT_BRACKET;
                break;
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}

//逆波兰表达式计算
class ReversePolishNotation1 {
    public static void main(String[] args) {
        String ex = "4 5 * 8 - 60 + 8 2 / +";
        String[] s = ex.split(" ");
        int result = calculate(s);
        System.out.println("result = " + result);
    }

    private static int calculate(String[] s) {
        Deque<String> stack = new ArrayDeque<>();
        for (String item : s) {
            if (item.matches("\\d+")) {
                stack.push(item);//匹配数字，放入栈
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("*".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)) {
                    res = num2 / num1;
                } else if ("+".equals(item)) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num2 - num1;
                } else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//冒泡排序
class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -2, 10, -1};
        //测试多条数据排序
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
//        long start = System.currentTimeMillis();
//        String start = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = dateFormat.format(date);
        System.out.println("排序前" + start);

        bubbleSortInt(arr);
        Date date1 = new Date();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end = dateFormat1.format(date1);
        System.out.println("排序后" + end);
//        long end = System.currentTimeMillis();
//        System.out.println("排序耗时" + (end - start) + "毫秒");
//        String end = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
//        System.out.println("排序后" + end);
//        //第二次排序将最大的排在最后
//        for (int i = 0; i < arr.length - 1 - 1; i++) {
//            //相邻两数比较，大的后移
//            if (arr[i] > arr[i + 1]) {
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//        }
//        System.out.println("第二次排序后" + Arrays.toString(arr));
//        //第三次排序将最大的排在最后
//        for (int i = 0; i < arr.length - 1 - 2; i++) {
//            //相邻两数比较，大的后移
//            if (arr[i] > arr[i + 1]) {
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//        }
//        System.out.println("第三次排序后" + Arrays.toString(arr));
//        //第四次排序将最大的排在最后
//        for (int i = 0; i < arr.length - 1 - 3; i++) {
//            //相邻两数比较，大的后移
//            if (arr[i] > arr[i + 1]) {
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//        }
//        System.out.println("第四次排序后" + Arrays.toString(arr));
    }

    private static void bubbleSortInt(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识相邻两元素是否交换
        for (int j = 0; j < arr.length - 1; j++) {
            //第一次排序将最大的排在最后
            for (int i = 0; i < arr.length - 1 - j; i++) {
                //相邻两数比较，大的后移
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            if (!flag) {
                break;//没有交换元素，说明已经完成排序，跳出循环
            } else {
                flag = false;//重置标识
            }
//            System.out.println("第" + (j + 1) + "次排序后" + Arrays.toString(arr));
        }
    }
}

//冒泡排序 双层嵌套for循环，时间复杂度T(n) = O(n^2)
class Bubble {
    public static void main(String[] args) {
        int[] arr = {3, 9, -2, 10, -1};
        int temp = 0;
        boolean flag = false;//标识相邻两数是否交换
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //相邻两数比较大小，大的后移
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;//无相邻元素交换，跳出循环
            } else {
                flag = false;//重置标识
            }
            System.out.println("第" + (i + 1) + "次排序后" + Arrays.toString(arr));
        }
        System.out.println("排序后的数组" + Arrays.toString(arr));
    }
}

//选择排序
class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -2, 10, -1};
        int temp = 0;
        boolean flag = false;//标识相邻两数是否交换
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    //相邻两数比较，大的后移
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;//重置标识
            }
            System.out.println("第" + (j + 1) + "次排序后" + Arrays.toString(arr));
        }

    }

}

class StreamTest {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        Collections.addAll(integerList, 1, 2, 3, 4, 5, 6);
//        integerList.stream().filter(integer -> integer > 5).forEach(System.out::println);//6
        Integer integer1 = getInteger(integerList);
        System.out.println("integer1 = " + integer1);
    }

    private static Integer getInteger(List<Integer> integerList) {
        Integer integer1 = null;
        try {
            integer1 = integerList.stream().filter(integer -> integer == 7).findFirst().get();
        } catch (Exception e) {
//            e.printStackTrace();
//            if (e instanceof NoSuchElementException){
//            System.out.println("元素为空");
//            }
        } finally {
            System.out.println("元素为空");
            return integer1;
        }
//        return integer1;
    }
}


class Ttest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "D").start();

    }
}

class Ticket {
    private int number = 50;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出前" + (number--) + "，剩余票" + number);
        }
    }
}

class Ticket1 {
    private int number = 50;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();

        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出前" + (number--) + "，剩余票" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "D").start();
    }
}

//多线程通信问题:生产者和消费者
//判断等待，业务，通知
class Data {
    int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//多线程通信问题:生产者和消费者
//判断等待，业务，通知
//使用JUC版 Lock
class Data1 {
    int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //+1
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Data1 data = new Data1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//面试题，使用多线程，依次打印A、B、C
class Data3 {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;

    public void printA() {
        lock.lock();
        try {
            //业务代码  业务判断，执行，通知
            while (number != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "打印:AAAAA");
            //唤醒指定的线程
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB() {
        lock.lock();
        try {
            //业务代码
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "打印:BBBBB");
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printC() {
        lock.lock();
        try {
            //业务代码
            while (number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "打印:CCCCC");
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }
}

class CollectoinTest {
    //java.util.ConcurrentModificationException 并发修改异常
    public static void main(String[] args) {
        //并发下ArrayList不安全？ synchronized
        /**
         *解决方案
         * 1.使用Vector，List<String> list = new Vector<>();(不推荐)，Vector是JDK1.0的产物，使用的synchronized，效率低
         * 2.List<String> list = Collections.synchronizedList(new LinkedList<>());
         * 3.List<String> list = new CopyOnWriteArrayList<>();推荐使用，底层使用的是新版JUC下的lock方式，效率相对要快
         */
        //CopyOnWrite 写入时复制，读写分离  COW 计算机程序设计领域的一种优化策略
        //CopyOnWriteArrayList比Vector好在哪里？
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(Thread.currentThread().getName() + list);
            }, "" + i).start();

        }
    }
}

class SetTest {
    //   多线程下会抛出java.util.ConcurrentModificationException 并发修改异常
    public static void main(String[] args) {
        /**
         * 解决方案
         * 1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 2.Set<String> set = new CopyOnWriteArraySet<>();(写时复制，用在读多写少的场景)
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }).start();

        }
    }
}

class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        MyThread myThread = new MyThread();
//        //适配类，Thread无法直接接受Callable，使用FutureTask适配，使用了适配器模式
//        FutureTask task = new FutureTask(myThread);
//        new Thread(task, "A").start();//怎么启动Callable？
//        new Thread(task, "B").start();//结果会被缓存，效率高
//        String s = (String) task.get();//这个get方法可能会发送阻塞，放在最后
//        //或者使用异步通信来处理
//        System.out.println(s);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            FutureTask futureTask = new FutureTask(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(Thread.currentThread().getName() + "====" + list);
                return list;
            });
            new Thread(futureTask, "" + i).start();
        }
//        new Thread(futureTask,"C").start();
//        System.out.println("futureTask.get() = " + futureTask.get());

    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "----call()");
        return "str";
    }
}

//计数器
class CountTest {
    public static void main(String[] args) throws InterruptedException {
        //总数是6，必须在执行任务的时候使用
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + finalI + "   GO OUT");
                count.countDown();//-1
            }, "" + i).start();
        }
        count.await();//等待计数器归零，再向下执行
        System.out.println("Close Door");
    }
}

class CyclicBarrierTest {
    public static void main(String[] args) {
        /**
         * 集齐7颗龙珠召唤神龙
         */
        CyclicBarrier barrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });
        for (int i = 0; i < 7; i++) {
            int finalI = i;//lamba能直接操作i吗？
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "收集第" + finalI + "颗龙珠");
                    barrier.await();//等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

class SemaphoreTest {


    public static void main(String[] args) {
        //3线程数量，停车位，限流!
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //acquire 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放
                }
            }, "" + i).start();

        }
    }
}

class ReadWriteLockTest {
    //如果不使用读写锁，在写入的时候，多个线程会同时写入
    public static void main(String[] args) {
        MapCache cache = new MapCache();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.put("" + finalI, finalI);
            }, "写线程" + i).start();
        }
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.get("" + finalI);
            }, "读线程" + i).start();
        }
    }
}

/**
 * ReadWriteLock
 * 独占锁(写锁) 一次只能被一个线程占有
 * 共享锁(读锁) 多个线程可以同时占有
 * 读-读 能共享
 * 读-写 不能共享
 * 写-读 不能共享
 */
//自定义缓存
class MapCache {
    private volatile Map<String, Object> mapCache = new HashMap<>();
    //读写锁，更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存，写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            mapCache.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    //取，读
    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            o = mapCache.get(key);
            System.out.println(Thread.currentThread().getName() + "读取ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

        return o;
    }
}

/**
 * 超时等待
 */
class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(3);
//        test1(queue);
//        test2(queue);
        test3(queue);
//        test4(queue);
//        test5(queue);
//        test6(queue);
//        test7(queue);
//        test8(queue);
    }

    private static void test1(BlockingQueue queue) {
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("b"));
//        System.out.println(queue.add("c"));
//        System.out.println(queue.add("d"));//队列满，抛出异常Queue full
        System.out.println(queue.element());//获取首元素,无元素，抛出异常NoSuchElementException
    }

    private static void test2(BlockingQueue queue) {
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());//队列空了，抛出异常java.util.NoSuchElementException
    }

    private static void test3(BlockingQueue queue) {
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("b"));
//        System.out.println(queue.offer("c"));
//        System.out.println(queue.offer("d"));//队列满，不抛出抛出异常,返回false
        System.out.println(queue.peek());//获取首元素,队列空，不抛出抛出异常,返回null
    }

    private static void test4(BlockingQueue queue) {
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());//队列空了，不抛出异常，返回null
    }

    private static void test5(BlockingQueue queue) throws InterruptedException {
        queue.put("a");
        queue.put("b");
        queue.put("c");
//        queue.put("d");//队列满，不抛出抛出异常,阻塞等待
    }

    private static void test6(BlockingQueue queue) throws InterruptedException {
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());//队列空了，不抛出异常，阻塞等待
    }

    private static void test7(BlockingQueue queue) throws InterruptedException {
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d", 2, TimeUnit.SECONDS));//队列满，不抛出抛出异常,超时等待，返回false
    }

    private static void test8(BlockingQueue queue) throws InterruptedException {
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll(2, TimeUnit.SECONDS));//队列空了，不抛出异常，超时等待,返回null
    }
}

/**
 * 同步队列
 * 和其它的阻塞队列BlockingQueue不一样，
 * put进去一个元素后，必须先从里面take出一个元素后，才能再put放入元素!
 */
class SynchronousQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue();//同步队列
        new Thread(() -> {
            try {
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "   put a");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "   put b");
                blockingQueue.put("c");
                System.out.println(Thread.currentThread().getName() + "   put c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "   get=>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "   get=>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "   get=>" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}

//Executors工具类，3大方法
class ExecutorsTest {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);//创建一个固定大小的线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩，遇强则强，遇弱则弱
        //最大核心线程数量怎么去设置？
        //1.cpu密集型，几核就设置几，可以保持CPU的效率最高
        //2.io密集型 判断你的程序中十分耗费io的线程有多少，一般是线程数量的2倍
        //15个大型任务，io会十分占用资源
        //获取系统核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()//队列满了，尝试去和队列首位竞争，如果竞争失败不执行也不抛出异常，如果成功就执行
        );
        try {
            //最大承载：队列Queue+maxnumPoolSize
            for (int i = 0; i < 9; i++) {
                //使用线程池之后，使用线程池来创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "=>ok");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();//用完关闭线程池
        }
    }

}

/**
 * 1.id必须是偶数
 * 2.年龄大于23
 * 3.用户名转为大写
 * 4.用户名字母倒着排序
 * 5.只输出一个用户
 */
class StreamTest1 {
    public static void main(String[] args) {
        User user1 = new User(1L, "a", "男");
        User user2 = new User(2L, "b", "男");
        User user3 = new User(3L, "c", "女");
        User user4 = new User(4L, "d", "男");
        User user5 = new User(5L, "e", "女");
        User user = Arrays.asList(user1, user2, user3, user4, user5).stream().filter(
                user6 -> user6.getSsex().equals("男")
        ).map(user6 -> {
            user6.setUsername(user6.getUsername().toUpperCase());
            return user6;
        }).findFirst().get();
        System.out.println("user = " + user);
    }
}

class ForkJoinTest extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    //临界值
    private Long temp = 10000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    /**
     * 计算求和方法
     *
     * @return
     */
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            long sum = 0;
            for (long i = start; start <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //超过临界值，走分支合并计算
            Long middle = (start + end) / 2;//中间值
            ForkJoinTest task1 = new ForkJoinTest(start, middle);
            task1.fork();//拆分任务，把任务压入线程队列
            ForkJoinTest task2 = new ForkJoinTest(middle + 1, end);
            task2.fork();//拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();//耗时1411
//        test2();
//        test3();//耗时1129
    }

    /**
     * 普通做法
     */
    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (long i = 1; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "   耗时" + (end - start));
    }

    /**
     * ForkJoin分治法方式
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinTest(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = pool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "   耗时" + (end - start));
    }

    /**
     * 并行流
     */
    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "   耗时" + (end - start));
    }

}

/**
 * 异步调用CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        voidTest();
        supplyAsyncTest();
    }

    private static void supplyAsyncTest() throws InterruptedException, ExecutionException {
        //有返回值的异步回调supplyAsync
        CompletableFuture<Integer> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "   supplyAsync=>Integer");
            int i = 10 / 0;
            return 1000;
        });
        System.out.println("业务代码");
        supplyAsyncFuture.whenComplete((integer, throwable) -> {
            System.out.println(integer);//正常的返回结果
            System.out.println(throwable);//异常信息java.util.concurrent.CompletionException
        }).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return 1005;
        });
        System.out.println("supplyAsyncFuture.get() = " + supplyAsyncFuture.get());
    }

    private static void voidTest() throws InterruptedException, ExecutionException {
        //没有返回值的异步回调runAsync
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "   runAsync=>void");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("业务代码");
        future.get();//获取阻塞执行结果
    }
}

class JMMDemo {
    //volatile会保证共享变量的值会立即刷新回主存，保证线程A能够立刻知道共享变量的值改变了,保证可见性
    volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0) {
                //线程A不知道main线程将num的值改为1，线程A中共享变量num的值还是0会一直死循环
            }
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        num = 1;
    }
}

class VDDemo {
    //volatile不保证原子性
    private static volatile AtomicInteger num = new AtomicInteger();

    public static void add() {
        num.getAndIncrement();//AtomicInteger自增操作，类似++
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            //理论上num的值是2万
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) { //mian gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}

//死锁
class DeadLock {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            while (true) {
                synchronized (o1) {
                    synchronized (o2) {
                        System.out.println("1111");
                        System.out.println("2222");
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                synchronized (o2) {
                    synchronized (o1) {
                        System.out.println("aaaa");
                        System.out.println("bbbb");
                    }
                }
            }
        }).start();
    }
}

//饿汉式单例模式
class HungrySingleton {
    //可能会浪费空间
    private byte[] bytes1 = new byte[1024 * 1024];
    private byte[] bytes2 = new byte[1024 * 1024];
    private byte[] bytes3 = new byte[1024 * 1024];
    private byte[] bytes4 = new byte[1024 * 1024];

    public HungrySingleton() {
    }

    public final static HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}

//懒汉式单例模式
class LazySingleton {
    public LazySingleton() {
        synchronized (LazySingleton.class) {
            if (null != lazySingleton) {
                throw new RuntimeException("不要试图利用反射破坏单例模式");
            }
        }
    }

    private volatile static LazySingleton lazySingleton;

    public static LazySingleton getInstance() {
        //双重检查锁模式，懒汉式单例模式，DCL懒汉式
        if (null == lazySingleton) {
            synchronized (LazySingleton.class) {
                if (null == lazySingleton) {
                    lazySingleton = new LazySingleton();//不是原子性操作
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 期望1,2,3顺序执行，但是可能指令重排132执行
                     * 线程A执行到13，线程B突然进来，此时还没有完成初始化，线程B以为已经不为空直接返回空引用
                     */
                }
            }
        }

        return lazySingleton;
    }

    //多线程并发
    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                LazySingleton.getInstance();
//            }).start();
//        }
        //反射破坏单例模式
//        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        Constructor<LazySingleton> lazySingletonConstructor = LazySingleton.class.getDeclaredConstructor(null);
        lazySingletonConstructor.setAccessible(true);
        LazySingleton lazySingleton2 = lazySingletonConstructor.newInstance();
        LazySingleton lazySingleton3 = lazySingletonConstructor.newInstance();
//        System.out.println("lazySingleton1 = " + lazySingleton1);
        System.out.println("lazySingleton2 = " + lazySingleton2);
        System.out.println("lazySingleton3 = " + lazySingleton3);
    }
}

//静态内部类实现单例模式
class Holder {
    public Holder() {
    }

    private static class InnerClass {
        private static final Holder HOLDER = new Holder();
    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }
}

//枚举实现单例模式,枚举是什么，枚举本身是一个class类
enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance1);
        System.out.println(instance2);
    }
}

class CASDDemo {
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(10, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("A初始版本号 = " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A第一次是否更新成功" + atomicStampedReference.compareAndSet(10, 11,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A第一次更新后的版本号 = " + atomicStampedReference.getStamp());
            System.out.println("A第二次是否更新成功" + atomicStampedReference.compareAndSet(11, 10,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A第二次更新后的版本号 = " + atomicStampedReference.getStamp());
        }, "A").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("B初始版本 = " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B更新是否成功" + atomicStampedReference.compareAndSet(10, 11,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            int stamp1 = atomicStampedReference.getStamp();
            System.out.println("B更新后的版本号 = " + stamp1);
            System.out.println("B.getReference() = " + atomicStampedReference.getReference());
        }, "B").start();
    }
}

//栈溢出
class StackOver {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        a();
    }

    public static void a() {
        test();
    }
}
//-Xms设置初始化内存大小
//-Xmx设置最大分配内存
//-XX:+PrintGCDetails 打印GC垃圾回收信息
//-XX:+HeapDumpOnOutOfMemoryError OOM Dump
class JVMDemo {
    byte[] bytes = new byte[1024 * 1024];//1m

    public static void main(String[] args) {
        ArrayList<JVMDemo> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new JVMDemo());
                count += 1;
            }
        } catch (Exception e) {
            System.out.println("count = " + count);
            e.printStackTrace();
        }
    }
}

// polymorphism/PrivateOverride.java
// Trying to override a private method
// {java polymorphism.PrivateOverride}

 class PrivateOverride {
    private void f() {
        System.out.println("private f()");
    }

    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
        List<Integer> list = Arrays.asList(1, 2, 3, 4).stream().collect(Collectors.toList());
        list.add(5);
        System.out.println("list = " + list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(5)){
                list.remove(i);
            }
        }
        for (Integer integer : list) {
            if (integer==4){
                list.remove(integer);
            }
        }
        System.out.println("list = " + list);
    }
}

class Derived extends PrivateOverride {
    public void f() {
        System.out.println("public f()");
    }
}
