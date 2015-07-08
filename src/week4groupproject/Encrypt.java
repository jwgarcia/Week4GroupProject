package week4groupproject;

import java.util.Scanner;

public class Encrypt {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String choice = "y";

		while (choice.equalsIgnoreCase("y")) {
			determineShift(sc);

			Encrypt encryptor = new Encrypt(shift);

			getEncryptedMessage(sc, encryptor);
			System.out.println();
			System.out.print("Would you like to decrypt a message? (y/n): ");
			String decryptChoice = sc.next();
			if (decryptChoice.equalsIgnoreCase("y")) {
				getDecryptedMessage(sc, encryptor);
			}
			System.out.println();
			choice = getContinueChoice(sc);
		}
		System.out.println("Good bye!");
		sc.close();
	}

	private static String getContinueChoice(Scanner sc) {
		String choice;
		System.out.print("Would you like to code another message? (y/n): ");
		choice = sc.next();
		return choice;
	}

	private static void getDecryptedMessage(Scanner sc, Encrypt encryptor) {
		System.out.print("Enter message to be decrypted:  ");
		String decrypt = sc.next();
		String decryptedMessage = encryptor.decode(decrypt);
		System.out.print("Your decrypted message is: " + decryptedMessage);
	}

	private static void determineShift(Scanner sc) {
		System.out.print("Enter a shift value: ");
		shift = sc.nextInt();
	}

	private static void getEncryptedMessage(Scanner sc, Encrypt inputMessage) {
		System.out.print("Input message for ENCRYPTION: ");
		String messageEntered = sc.next();
		String encryptedMessage = inputMessage.encode(messageEntered);
		System.out.println("Your encrypted message:  " + encryptedMessage);
	}

	private static int shift;

	public Encrypt(int shift) {
		shiftAmount(shift);
	}

	// Adjust shift amount
	public void shiftAmount(int shift) {
		int superShift = (shift * shift * 49);
		if (superShift < 0) {
			superShift = -superShift;
		}
		this.shift = superShift % 26;
	}

	public String encode(String encoder) {
		if (encoder == null)
			return "";

		char[] letters = encoder.toLowerCase().toCharArray();
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] < 'a' || letters[i] > 'z')
				continue;
			letters[i] = Character.toUpperCase(letters[i]);
			letters[i] += shift;
			if (letters[i] > 'Z') {
				letters[i] -= 'Z';
				letters[i] += ('A' - 1);
			}
		}
		return new String(letters);
	}

	public String decode(String decoder) {
		if (decoder == null)
			return "";

		char[] letters = decoder.toUpperCase().toCharArray();
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] < 'A' || letters[i] > 'Z')
				continue;
			letters[i] = Character.toUpperCase(letters[i]);
			letters[i] -= shift;
			if (letters[i] < 'A') {
				letters[i] += 'Z';
				letters[i] -= ('A' - 1);
			}
		}
		return new String(letters);
	}
}
