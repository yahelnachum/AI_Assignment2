
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] possibleNums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
		int[] seq1 = {1,2,3, 19};
		int[] seq2 = {2, 7, 1, 4, 8, 5, 19};
		int[] seq3 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		int[] seq4 = {2, 7, 1, 4, 8, 10, 19, 20};
		
		Clock c = new Clock();
		System.out.println("Is seq1 a valid sequence? " + NumberSequence.isSequenceValid(seq1, possibleNums) + " Found in " + c.delta() + " milliseconds");
		System.out.println("Is seq2 a valid sequence? " + NumberSequence.isSequenceValid(seq2, possibleNums) + " Found in " + c.delta() + " milliseconds");
		System.out.println("Is seq3 a valid sequence? " + NumberSequence.isSequenceValid(seq3, possibleNums) + " Found in " + c.delta() + " milliseconds");
		System.out.println("Is seq4 a valid sequence? " + NumberSequence.isSequenceValid(seq4, possibleNums) + " Found in " + c.delta() + " milliseconds");
		
	}

}
