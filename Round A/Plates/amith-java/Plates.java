import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int testCases = input.nextInt();
		int caseNum = 1;
		Solution plates = new Solution();

		while (caseNum <= testCases) {
			int n = input.nextInt();
			int k = input.nextInt();
			int p = input.nextInt();

			int[][] values = new int[n][k];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					values[i][j] = input.nextInt();
				}
			}

			System.out.println(String.format("Case #%d: %d", caseNum, plates.maxPlatesBeautyValue(values, p)));
			caseNum++;
		}
		input.close();
	}

	private int maxPlatesBeautyValue(int[][] values, int numberOfPlates) {
		int numberOfStacks = values.length;
		int platesPeerStack = values[0].length;
		int platesToPick = numberOfPlates;
		
		int[][] prefixSum = new int[numberOfStacks + 1][platesPeerStack + 1];
		int[][] lookup = new int[numberOfStacks + 1][platesToPick + 1];

		for (int i = 1; i <= numberOfStacks; i++) {
			for (int j = 1; j <= platesPeerStack; j++) {
				prefixSum[i][j] = prefixSum[i][j - 1] + values[i - 1][j - 1];
			}
		}

		for (int i = 1; i <= numberOfStacks; i++) {
			for (int j = 1; j <= platesToPick; j++) {
				lookup[i][j] = 0;

				for (int x = 0; x <= Math.min(j, platesPeerStack); x++) {
					lookup[i][j] = Math.max(lookup[i][j], prefixSum[i][x] + lookup[i - 1][j - x]);
				}
			}
		}

		return lookup[numberOfStacks][platesPeerStack];
	}

}
