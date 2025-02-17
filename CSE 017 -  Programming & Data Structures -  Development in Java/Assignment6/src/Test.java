/**
 * Worked on Assigment 6 with Tyler Hagmann 
 * Alexander Spivey
 * 11/13/2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ListIterator;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		DoublyLinkedList<String> countryList = new DoublyLinkedList<>();
		File file = new File("countries.txt");
		Scanner readFile = null;
		try {
			readFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		while (readFile.hasNext()) {
			String name = readFile.nextLine();
			countryList.add(name);
		}
		System.out.println("Forward Print:");
		printForward(countryList);
		System.out.println("Backward Print:");
		printBackward(countryList);
	}
	//O(n)
	public static <E> void printForward(DoublyLinkedList<E> list) {
		ListIterator<E> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println("\t" + iterator.next());
		}
	}

	//O(n)
	public static <E> void printBackward(DoublyLinkedList<E> list) {
		for(int i = 1; i < list.size(); i++) { //I can't believe this works
			ListIterator<E> backwardIterator = list.iterator(list.size()-i);
			if (backwardIterator.hasPrevious()) {
				System.out.println("\t" + backwardIterator.previous());
			}
		}
	}
}
