package runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple tester to test stuff. Nothing fancy, uses console line for input and output.
 * @author aliu
 *
 */
public abstract class SimpleTester implements Runnable {
	
	private BufferedReader reader;
	
	public SimpleTester() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run() {
		while(true) {
			try {
				String input = reader.readLine();
				System.out.println(execute(input).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Takes input directly from command line, and outputs object result. Object result is printed using its toString() method.
	 * @param input String line from System.in
	 * @return object result
	 */
	public abstract Object execute(String input);
	
	

}
