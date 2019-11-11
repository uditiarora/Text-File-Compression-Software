package huffman;
//Binary tree node
public class HuffmanTreeNode {
	char ch;
	int freq;
	HuffmanTreeNode left,right;
	public HuffmanTreeNode(char ch,int freq) {
		this.ch = ch;
		this.freq = freq;
		this.left = this.right=null;
	}
}
