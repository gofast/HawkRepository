package com.jt.datastructure;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

public class Tree {
	
	static Logger logger = Logger.getLogger(Tree.class.getName());
	
	public static void main(String[] args) {
		Integer[] numString = {11,33,66,44,3,98,43,54,56,43,23,34,13};
		Node<Integer> root = createFullTwoForkTree(numString);
		sortFullTwoForkTree(root);
		System.out.println(root);
		logger.info("log:"+root);
	}
	
	/**
	 * 创建左子树
	 * @param numString
	 * @return
	 */
	public static Node<Integer> createLeftTree(Integer[] numString){
		Node<Integer> node = new Node<Integer>(numString[0]);
		Node<Integer> tmpNode = node;
		for(int i=1;i<numString.length;i++){
			createLeftNode(tmpNode,numString[i]);
			if(++i >=numString.length){
				tmpNode = null;
				break;
			}
			createRightNode(tmpNode,numString[i]); 
			tmpNode = tmpNode.leftNode;
		}
		System.out.println("node："+node);
		return node;
	}
	
	/**
	 * 创建右子树
	 * @param numString
	 * @return
	 */
	public static Node<Integer> createRightTree(Integer[] numString){
		Node<Integer> node = new Node<Integer>(numString[0]);
		Node<Integer> tmpNode = node;
		for(int i=1;i<numString.length;i++){
			createRightNode(tmpNode,numString[i]);
			if(++i >=numString.length){
				tmpNode = null;
				break;
			} 
			createLeftNode(tmpNode,numString[i]);
			tmpNode = tmpNode.rightNode;
		}
		System.out.println("node："+node);
		return node;
	}
	
	/**
	 * 创建完全二叉树
	 * 关键原理：
	 * 1、用FIFO队列控制：为节点添加子节点的顺序，可以确保给树中I层的所有节点都能加上I+1层的节点后，才会考虑I+2层的节点；
	 * 2、进入队列的需要具备两个条件：第一，它没有子节点；第二，刚刚与成为其他节点的儿子节点；
	 * @param numString
	 * @return
	 */
	public static Node<Integer> createFullTwoForkTree(Integer[] numString){
		int count = 0;
		int index = 0;
		// 用队列来控制根据FIFO的原理，来控制为节点添加子节点的顺序，从而达到，构造完全二叉树的目标。
		ArrayBlockingQueue<Node<Integer>> queue = new ArrayBlockingQueue<Node<Integer>>(numString.length); 
		Node<Integer> root = new Node<Integer>(numString[count++]);  
		Node<Integer> curNode = root;
		while(count<numString.length){
			if(index == 2){
				curNode = queue.poll();
				index = 0;
			}
			
			if(curNode.leftNode==null){
				curNode.leftNode = new  Node<Integer>(numString[count++]);
				queue.offer(curNode.leftNode);
				index ++;
			}
			
			if(curNode.rightNode==null && count<numString.length){
				curNode.rightNode = new  Node<Integer>(numString[count++]);
				queue.offer(curNode.rightNode);
				index ++;
			}
		}
		System.out.println("queue:"+queue.size()+","+queue.poll().data);
		return root;
	}
	
	/**
	 * 创建小堆：即：node.data<node.leftNode.data && node.data<node.rightNode.data
	 * 添加一个二外的条件: 
	 * 遵循遍历思路：左-右-根
	 * @param node
	 */
	public static void sortFullTwoForkTree(Node<Integer> node){
		
		if(node==null){
			return;
		}
		if(node.leftNode!=null){
			sortFullTwoForkTree(node.leftNode);
			compareAndswap(node,node.leftNode,true);
		}
		if(node.rightNode!=null){
			sortFullTwoForkTree(node.leftNode);
			compareAndswap(node,node.rightNode,true);
		}
		if(node.leftNode!=null&&node.rightNode!=null){
			compareAndswap(node.leftNode,node.rightNode,true);
		}
	}
	
	/**
	 * 比较并交换
	 * @param node1
	 * @param node2
	 * @param isLittle  true:小堆，false:大堆
	 */
	public static void compareAndswap(Node<Integer> node1,Node<Integer> node2,boolean isLittle){
		if(node1==null||node2==null){
			return;
		}
		if(isLittle){
			if(node1.data>node2.data){
				nodeSwape(node1,node2);
			}
		}else{
			if(node1.data<node2.data){
				nodeSwape(node1,node2);
			}
		}
	}
	
	
	public static void createLeftNode(Node<Integer> node,Integer  data){
		if(data == null){
			return;
		}
		node.leftNode=new Node<Integer>(data); 
	}

	public static void createRightNode(Node<Integer> node,Integer  data){
		if(data == null){
			return;
		}
		node.rightNode=new Node<Integer>(data);
	}
	

	public static Node<Integer> sortTwoForkTree(Node<Integer> node){
		if(node==null){
			return null;
		}
		if(node.leftNode==null){
			return null;
		}
		// 1：根于左节点比较
		if(node.data.intValue() > node.leftNode.data.intValue() ){
			nodeSwape(node,node.leftNode);
		}
		
		// 1：根于左节点比较
		if(node.data.intValue() > node.leftNode.data.intValue() ){
			nodeSwape(node,node.leftNode);
		}
		
		return null;
	}
	
	public static void nodeSwape(Node<Integer> node1,Node<Integer> node2){
		if(node1==null||node2==null){
			return;
		}
		int tmpData = node1.data;   
		node1.data=node2.data.intValue(); 
		node2.data=tmpData;
		return  ;
	}
	
	 
	
}
