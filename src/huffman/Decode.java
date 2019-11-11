package huffman;
import java.io.*;
import java.util.*;
public class Decode {
	//Returns string of zeroes
	public static String returnString(int n){
		String ans="";
		for(int i=0;i<n;i++){
			ans += "0";
		}
		return ans;
	}
	//Getting back the original data through this function
	//Called by decode function
	public static void getOriginal(String readFrom,HashMap<String,Character> map)throws IOException{
		BufferedReader br =null;
		String data="";			//Stores data to be decoded 
		try{
			br = new BufferedReader(new FileReader(readFrom));
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	         data = sb.toString();
		}catch (IOException e){
			e.printStackTrace();
		}
		int n = data.length();
		br.close();
		File file = new File("decoded.txt");
		BufferedWriter bw=null;
		try{
			bw = new BufferedWriter(new FileWriter(file));		//opening the file in which decoded data will be written
		}catch(IOException e){
			e.printStackTrace();
		}
		//Running the huffman algorithm for decoding
		for(int i=0;i<n;i++){
			String temp="";
			for(int j=i;j<n;j++){
				temp += data.charAt(j);
				if(map.containsKey(temp)){
					i = i + temp.length()-1;
					bw.write(map.get(temp));
					System.out.print(map.get(temp));
					break;
				}
			}
		}
		bw.close();
		
		
	}
	
	
	public void decode(String writeTo,String readFrom) throws IOException{
		HashMap<String,Character> bits = new HashMap<String,Character>();
		BufferedReader br =null;
		String data="";
		try{
			br = new BufferedReader(new FileReader(readFrom));		//Read encoded data
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	         data = sb.toString();
		}catch (IOException e){
			e.printStackTrace();
		}
		br.close();
		int n = data.length();
		//convert encoded data to string of 0s and 1s
		int bitsToBeLeft = Integer.parseInt(data.charAt(0)+"");		//First character is bitsToBeLeft
		int i=0,j=0;
		//Then we stored codes and now we are retrieving them
		for(i=2,j=0;i<n && j<256;i++,j++){
			String temp="";
			while(i<n && data.charAt(i)!=' '){
				temp += data.charAt(i);
				i++;
			}
			if(temp.charAt(0)=='0' || temp.charAt(0)=='1'){
				bits.put(temp, (char)j);
				//System.out.println(n+" "+i+" "+j+" "+temp+" "+(char)j);
			}
		}
		File file = new File("temp2.txt");
		BufferedWriter bw=null;
		try{
			bw = new BufferedWriter(new FileWriter(file));
		}catch(IOException e){
			e.printStackTrace();
		}
		data = data.substring(i, n);
		String decoded = "";
		n = data.length();
		//making 1 character equal to 7 characters
		for(i=0;i<n-1;i++){
			char ch = data.charAt(i);
			String temp =Integer.toBinaryString((int)ch);
			temp = returnString(7-temp.length())+temp;
			bw.write(temp);
		}
		char ch = data.charAt(n-1);
		decoded = Integer.toBinaryString((int)ch);
		decoded = returnString(7-decoded.length())+decoded;
		decoded = decoded.substring(0,decoded.length()-bitsToBeLeft);
		bw.write(decoded);
		bw.close();

		getOriginal("C:/Users/uditi/workspace/TextFileCompressionSoftware/temp2.txt",bits);
		
		
	}
}
