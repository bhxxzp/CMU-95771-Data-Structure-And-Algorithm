package LZW;
import java.io.*;
//copy a binary or text file
public class CopyBytes {

	public static void main(String[] args) throws IOException{
		DataInputStream in =
				new DataInputStream(
				  new BufferedInputStream(
				    new FileInputStream(args[0])));
				DataOutputStream out =
				  new DataOutputStream(
				    new BufferedOutputStream(
				      new FileOutputStream(args[1])));
				byte byteIn; 
				try {
				   while(true) {
				       byteIn = in.readByte(); 
				       out.writeByte(byteIn);
				    } 
				 }
				catch(EOFException e) { 
					in.close();
				    out.close(); 
				 }
		}
}