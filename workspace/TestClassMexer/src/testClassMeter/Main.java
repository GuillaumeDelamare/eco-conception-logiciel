package testClassMeter;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.javamex.classmexer.MemoryUtil;


public class Main {
	public static void main(String[] args) {
		String str = "";
		
		
		try{
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			for(int i=0;i<100;i++){
				out.write(Long.toString(MemoryUtil.deepMemoryUsageOf(str))+"\n");
				str+="a";
			}
			
			//Close the output stream
			out.close();
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
}
