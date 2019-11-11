package huffman;
import java.util.*;
//class to create a comparator so as to use priority queue
class compareHelper implements Comparator<HuffmanTreeNode>{
	public int compare(HuffmanTreeNode temp1,HuffmanTreeNode temp2){
		return temp1.freq - temp2.freq;
	}
}
//Class creates Huffman Tree and frames the codes for each character
public class HuffmanTree {
	public static void getCodes(HuffmanTreeNode head,String ans,String[] bits){
		if(head==null){
			return;
		}
		if(head.ch!='\0'){
			bits[(int)head.ch] = ans;			
		}
		getCodes(head.right,ans+"1",bits);		// Add 1 to string when going right
		getCodes(head.left,ans+"0",bits);		// Add 0 to string when going left
	}
	public HuffmanTreeNode constructTree(int count[]){
		Comparator<HuffmanTreeNode> compare = new compareHelper();
		PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>(300,compare);	//Min heap which sorts according to frequency of character
		for(int i=0;i<256;i++){
			if(count[i]>0){
				HuffmanTreeNode node = new HuffmanTreeNode((char)i, count[i]);
				pq.add(node);				//Creating min heap
			}
		}
		
		HuffmanTreeNode first,second;		
		//making huffman tree
		while(pq.size()>1){
			first = pq.poll();		//min element (out left child)
			second = pq.poll();		//Second min element(our right child)
			HuffmanTreeNode temp = new HuffmanTreeNode('\0', first.freq + second.freq);		//new node with frequency = sum of frequency of other two
			temp.left = first;		
			temp.right = second;
			pq.add(temp);		//pushing this element 
			//repeat until only one element left
		}
		HuffmanTreeNode root = pq.poll();		//getting the last element and making it root
		return root;
		
		
	}
}
