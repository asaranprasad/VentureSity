package misc;

import java.util.Scanner;

public class Cypher {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Key: ");
		String key = scan.nextLine().toLowerCase().replace(" ", "");
		System.out.print("Is message plaintext (Y/N): ");
		String bool = scan.nextLine();
		boolean isPlainText = (bool.equalsIgnoreCase("Y")) ? true : false;
		if (isPlainText)
			System.out.print("Plaintext: ");
		else
			System.out.print("Cyphertext: ");
		String input = scan.nextLine().toLowerCase().replace(" ", "");
		scan.close();

		Cypher c = new Cypher();
		if (isPlainText)
			System.out.println("Cyphertext: ");
		else
			System.out.println("Plaintext: ");
		System.out.print(c.CypherIt(input.toCharArray(), key.toCharArray(), isPlainText));
	}

	private char[] CypherIt(char[] input, char[] key, boolean isPlainText) {
		int keyCounter = 0;
		for (int i = 0; i < input.length; i++) {
			char curr = input[i];
			if (curr < 'a' || curr > 'z') {
				input[i] = curr;
				continue;
			}

			int inNum = alphaNum(curr);
			int keyNum = alphaNum(key[keyCounter++]);
			if (keyCounter >= key.length)
				keyCounter = 0;
			int outNum;
			if (isPlainText)
				outNum = (inNum + keyNum) % 26 + 1;
			else {
				outNum = (inNum - keyNum) % 26 - 1;
				while (outNum < 0)
					outNum += 26;
			}

			char outChar = numAlpha(outNum);
			if (i == 0)
				input[i] = Character.toUpperCase(outChar);
			else
				input[i] = outChar;
		}
		return input;
	}

	private char numAlpha(int outNum) {
		return (char) (outNum + 96);
	}

	private int alphaNum(char curr) {
		return ((int) curr) - 96;
	}

}
