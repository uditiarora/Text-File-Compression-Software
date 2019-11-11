package huffman;
import java.util.*;
import java.io.*;
//Main file 

public class Main {
	//This function counts the occurrence of each character in the file
	public static String fillCountArray(int count[],String fileName)throws IOException{
		BufferedReader br =null;   
		String data="";   	// stores the contents of the file
		try{
			br = new BufferedReader(new FileReader(fileName));	// opening the file using exact location
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();			//reading file line by line but ignoring the new line characters

	        while (line != null) {
	            sb.append(line+" ");
	            line = br.readLine();
	        }
	         data = sb.toString();
		}catch (IOException e){
			e.printStackTrace();
		}
		int n = data.length();
		for(int i=0;i<n;i++){
			count[data.charAt(i)]++;		//Incrementing count of character encountered
		}
		br.close();		//closing the file
		
		return data;
		
	}
	// This function writes the new representations in a temporary file named temp.txt
	public static String writeToTemp(String[] bits,String data) throws IOException{
		File file = new File("temp.txt");	// making file temp.txt
		BufferedWriter bw=null;
		try{
			bw = new BufferedWriter(new FileWriter(file));
		}catch(IOException e){
			e.printStackTrace();
		}
		int n = data.length();
		for(int i=0;i<n;i++){
			bw.write(bits[(int)data.charAt(i)]);			// Writes the new representations of the characters into the temp file
		}
		bw.close();
		return file.getAbsolutePath();			// This is returned so that we have path to the temp.txt file that we have created
	}
	public static void main(String[] args) throws IOException{
		int count[] = new int[256];				// To store count of all characters
		String[] bits = new String[256];		// To store bit represntation of all characters in string format
		String filePath = "C:/Users/uditi/workspace/TextFileCompressionSoftware/file1.txt";			// Our source file named file1.txt
		String data = fillCountArray(count,filePath);			//Calling to read our source file and create our count array
		HuffmanTree ht = new HuffmanTree();					//Class which creates the Huffman Tree
		HuffmanTreeNode root = ht.constructTree(count);		// Calling the function to create Huffman tree
		ht.getCodes(root, "", bits);						// Calling the function to get codes of each character from the huffman tree(codes are stored in an array named bits)
		String tempFilePath = writeToTemp(bits, data);		//Writing codes to temporary file
		Encode en = new Encode();							//Encoding the file so as to reduce the size
		en.encode("C:/Users/uditi/workspace/TextFileCompressionSoftware/file.txt", "C:/Users/uditi/workspace/TextFileCompressionSoftware/temp.txt", bits);
		//Encoding groups 7 characters into 1 
		//This is done by converting the 7 character binary string to a decimal number and the number surely ranges from 0 to 128 and we can have
		//a character for all these values according to ascii code
		//Thus we can easily convert 7 characters to 1 
		//Note: These characters are the codes which we have calculates according to huffman tree
		Decode de = new Decode();							//Class to decode the encoded file
		de.decode("","C:/Users/uditi/workspace/TextFileCompressionSoftware/file.txt");
		
	}

}
