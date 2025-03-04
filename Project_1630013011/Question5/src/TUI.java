import java.util.InputMismatchException;
import java.util.Scanner;

public class TUI {
	public static void main(String[] args) {
		String str1 = readLine("Type some text: ");
		System.out.println("Text read is: " + str1);
		int i = readPosInt("Type an integer: ");
		System.out.println("Integer read is: " + i);
		String str2 = readLine("Type some text again: ");
		System.out.println("Text read is: " + str2);
	}
	
	//Print out and read input
	private static String readLine(String msg) {
		System.out.print(msg);
		Scanner s = new Scanner(System.in);
		return s.nextLine();
	}
	//Print out and read input
	private static int readPosInt(String msg) {
		int n = 0;
		
		while(true) {
			System.out.print(msg);
			Scanner s = new Scanner(System.in);
			try {
				n = s.nextInt();
				s.nextLine();
				if(n <= 0) {
					System.out.println("Positive integers only!");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("You must type an integer!");
				s.nextLine();
			}
		}
		return n;
	}
}