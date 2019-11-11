package huffman;

import java.io.*;

public class Encode {
	//function for encoding data,that is, converting 7 characters to 1
	public void encode(String writeTo,String readFrom,String[] bits) throws IOException {
		BufferedReader br = null;
		String data="";
		try{
			br = new BufferedReader(new FileReader(readFrom));		//Opening the file to read from it
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
		int bitsToBeLeft = 7 - n%7;		// This variable stores the number of bits that have to be padded at the end so as to make length of data divisible ny 7
		for(int i=0;i<bitsToBeLeft;i++){
			data += "0";  				// Making len of data divisible by 7
		}
		br.close();
		n += bitsToBeLeft;
		File file = new File("file.txt");
		BufferedWriter bw=null;
		try{
			bw = new BufferedWriter(new FileWriter(file));		//opening file to write the encoded version
		}catch(IOException e){
			e.printStackTrace();
		}
		// We write the number of padded bits as the first character in our encoded file as we will be needing this data
		bw.write(bitsToBeLeft+" ");
		// This loop writes the codes of all the characters to our encoded file as there is no other of storing it for further use
		for(int i=0;i<256;i++){
			bw.write(bits[i]+" ");
		}
		
		bw.write("\n");
		for(int i=0;i<n;i=i+7){
			String str = "";
			// Get 7 characters
			for(int j=i;j<i+7;j++){
				str += data.charAt(j);	
			}
			int temp = Integer.parseInt(str,2);		//Convert Binary String to Decimal
			char ch = (char)temp;					//Convert Decimal number to character
			bw.write(ch);							//Write to file
		}
		bw.close();			
	
	}
}
