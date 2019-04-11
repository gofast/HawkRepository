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
	 * ����������
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
		System.out.println("node��"+node);
		return node;
	}
	
	/**
	 * ����������
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
		System.out.println("node��"+node);
		return node;
	}
	
	/**
	 * ������ȫ������
	 * �ؼ�ԭ��
	 * 1����FIFO���п��ƣ�Ϊ�ڵ�����ӽڵ��˳�򣬿���ȷ��������I������нڵ㶼�ܼ���I+1��Ľڵ�󣬲Żῼ��I+2��Ľڵ㣻
	 * 2��������е���Ҫ�߱�������������һ����û���ӽڵ㣻�ڶ����ո����Ϊ�����ڵ�Ķ��ӽڵ㣻
	 * @param numString
	 * @return
	 */
	public static Node<Integer> createFullTwoForkTree(Integer[] numString){
		int count = 0;
		int index = 0;
		// �ö��������Ƹ���FIFO��ԭ��������Ϊ�ڵ�����ӽڵ��˳�򣬴Ӷ��ﵽ��������ȫ��������Ŀ�ꡣ
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
	 * ����С�ѣ�����node.data<node.leftNode.data && node.data<node.rightNode.data
	 * ���һ�����������: 
	 * ��ѭ����˼·����-��-��
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
	 * �Ƚϲ�����
	 * @param node1
	 * @param node2
	 * @param isLittle  true:С�ѣ�false:���
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
		// 1��������ڵ�Ƚ�
		if(node.data.intValue() > node.leftNode.data.intValue() ){
			nodeSwape(node,node.leftNode);
		}
		
		// 1��������ڵ�Ƚ�
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
