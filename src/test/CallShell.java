package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CallShell {
	public static void simple() {
		try {
			String commands = "ls -l";
			Process process = Runtime.getRuntime().exec(commands);
			process.waitFor(); 
			// for showing the info on screen
			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			BufferedReader input = new BufferedReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.err.println("IOException " + e.getMessage());
		}
	}

	public static void withParam() {
		try {
//			String[] commands = new String[] { "find", ".", "-name", "*java*","-print" };
			String[] commands = new String[] {"sh","/home/bigbug/temp/test.sh"};
			
			Process process = Runtime.getRuntime().exec(commands);

			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			BufferedReader input = new BufferedReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (java.io.IOException e) {
			System.err.println("IOException " + e.getMessage());
		}
	}

	public static void script() {
		try {
			String commands = "/home/bigbug/temp/test.sh";

			Process process = Runtime.getRuntime().exec(commands);
			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			BufferedReader input = new BufferedReader(ir);

			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}// end try
		} catch (java.io.IOException e) {
			System.err.println("IOException " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
//		 CallShell.simple();
		CallShell.withParam();
//		CallShell.script();
	}

}
