
public class Sort {
	public static int[] iterations = new int[6];
	
	public static <E> void swap(E[] list, int i, int j) {
		E temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}
	public static <E extends Comparable<E>> void selectionSort(E[] list) {
		int minIndex;
		for (int i = 0; i < list.length - 1; i++) {
			iterations[0]++;
			E min = list[i];
			minIndex = i;
			for (int j = i; j < list.length; j++) {
				iterations[0]++;
				if (list[j].compareTo(min) < 0) {
					min = list[j];
					minIndex = i;
				}
			}
			E temp = list[i];
			list[i] = list[minIndex];
			list[minIndex] = temp;
		}
	}

	public static <E extends Comparable<E>> void insertionSort(E[] list) {
		for (int i = 1; i < list.length; i++) {
			iterations[1]++;
			// Insert element i in the sorted sub-list
			E currentVal = list[i];
			int j = i;
			while (j > 0 && currentVal.compareTo(list[j - 1]) < 0) {
				iterations[1]++;
				// Shift element (j-1) into element (j)
				list[j] = list[j - 1];
				j--;
			}
			// Insert currentVal at position i
			list[j] = currentVal;
		}
	}

	public static <E extends Comparable<E>> void bubbleSort(E[] list) {
		boolean sorted = false;
		for (int k = 1; k < list.length && !sorted; k++) {
			sorted = true;
			iterations[2]++;
			for (int i = 0; i < list.length - k; i++) {
				iterations[2]++;
				if (list[i].compareTo(list[i + 1]) > 0) {
					// swap
					swap(list, i, i+1);
					sorted = false;
				}
			}
		}
	}

	public static <E extends Comparable<E>> void mergeSort(E[] list) {
		iterations[3]++;
		if (list.length > 1) { // base case
			Comparable[] firstHalf = new Comparable[list.length / 2];
			Comparable[] secondHalf = new Comparable[list.length - list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			System.arraycopy(list, list.length / 2, secondHalf, 0, list.length - list.length / 2);
			mergeSort(firstHalf);
			mergeSort(secondHalf);
			merge(firstHalf, secondHalf, list);
		}
	}

	public static <E extends Comparable<E>> void merge(Comparable[] list1, Comparable[] list2, Comparable[] list) {
		int list1Index = 0;
		int list2Index = 0;
		int listIndex = 0;
		while (list1Index < list1.length && list2Index < list2.length) {
			iterations[3]++;
			if (list1[list1Index].compareTo(list2[list2Index]) < 0)
				list[listIndex++] = list1[list1Index++];
			else
				list[listIndex++] = list2[list2Index++];
		}
		while (list1Index < list1.length) {
			iterations[3]++;
			list[listIndex++] = list1[list1Index++];
		}
		while (list2Index < list2.length) {
			iterations[3]++;
			list[listIndex++] = list2[list2Index++];
		}
	}

	public static <E extends Comparable<E>> void quickSort(E[] list) {
		quickSort(list, 0, list.length - 1);
	}

	public static <E extends Comparable<E>> void quickSort(E[] list, int first, int last) {
		iterations[4]++;
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	public static <E extends Comparable<E>> int partition(E list[], int first, int last) {
		E pivot;
		int index, pivotIndex;
		pivot = list[first];// pivot is the first element
		pivotIndex = first;
		for (index = first + 1; index <= last; index++) {
			iterations[4]++;
			if (list[index].compareTo(pivot) < 0) {
				pivotIndex++;
				swap(list, pivotIndex, index);
			}
		}
		swap(list, first, pivotIndex);
		return pivotIndex;
	}
	
	public static <E extends Comparable<E>> void heapSort(E[] list) {
		Heap<E> heap = new Heap<>(list);// add()
		for (int i = list.length - 1; i >= 0; i--) {
			iterations[5]++;
			list[i] = heap.remove();
		}
	}
}
