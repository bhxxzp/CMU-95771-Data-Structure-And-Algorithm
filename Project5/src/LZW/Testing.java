package LZW;

public class Testing {
	public static void main(String[] args) {
		String output = "asd.txt"; 
		BinaryOut out = new BinaryOut(output);
		for(int i = 0; i < 100; i++) {
			out.write(i);
		}
		out.flush();
	}
}
