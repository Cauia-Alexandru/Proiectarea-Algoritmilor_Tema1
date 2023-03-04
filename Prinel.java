import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prinel {
	int[] targetList;
	int[] pList;
	int K;
	int N;

	public void readInput() throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader("prinel.in"));
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		this.K = K;
		this.N = N;
		this.targetList = new int[N];
		this.pList = new int[N];
		int i = 0, j = 0;
		while (i < N) {
			targetList[i++] = scanner.nextInt();
		}
		while (j < N) {
			pList[j++] = scanner.nextInt();
		}
	}

	/**
	 *
	 * @param number numarul a carui divizori ii aflu
	 * @return un arraylist de divizori ai nr-ului dat ca parametru
	 */
	//Complexitatea = O(sqrt(n))
	public ArrayList<Integer> divisors(int number) {
		ArrayList<Integer> divisorsOfNumber = new ArrayList<>();
		for (int i = 1; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				if (number / i == i) {
					divisorsOfNumber.add(i);
				} else {
					divisorsOfNumber.add(i);
					divisorsOfNumber.add(number / i);
				}
			}
		}
		return divisorsOfNumber;
	}

	/**
	 * metoda returneaza un int[] care reprezinta nr de pasi minimi
	 * in care ajung la fiecare nr pana la 10^5. La fiecare pas adunand
	 * divizorul potrivit.
	 */
	public int[] nrOfOperations() {
		int max = (int) Math.pow(10, 5);
		int[] vect = new int[max + 1];

		for (int i = 1; i <= max; i++) {
			ArrayList<Integer> divisorsOfIndex = divisors(i);
			for (int j : divisorsOfIndex) {
				//adun nr cu divizorii lui
				int k = j + i;
				if (k <= max) {
					//daca la pozitia k e 0 sau nu s-a gasit o cale mai
					//decat actuala, o inlocuiesc
					if (vect[k] == 0 || vect[k] > vect[i] + 1) {
						vect[k] = vect[i] + 1;
					}
				}
			}
		}
		return vect;
	}

	public int task3() {
		int[] operationPerNumber = nrOfOperations();
		for (int i = 0; i < targetList.length; i++) {
			int nr = targetList[i];
			targetList[i] = operationPerNumber[nr];
		}

		int res = maxNrOfPoints(K, targetList, pList, N);
		return res;
	}

	/**
	 *
	 * @param K reprezinta capacitatea maxima a rucsacului
	 * @param opreations              vectorul de greutati
	 * @param p            vectorul care contine preturile
	 * @param N               lungimea listei "targetList"
	 * @return pretul maxim care se poate obtine fara a depasi capacitatea sacului
	 */
	public int maxNrOfPoints(int K, int[] opreations, int[] p, int N) {
		int[] dp = new int[K + 1];

		for (int i = 1; i <= N; i++) {
			for (int k = K; k >= 0; k--) {
				if (opreations[i - 1] <= k) {
					dp[k] = Math.max(dp[k], dp[k - opreations[i - 1]] + p[i - 1]);
				}
			}
		}
		return dp[K];
	}

	public static void main(String[] args) throws IOException {
		Prinel pirinel = new Prinel();
		pirinel.readInput();
		int rezultat = pirinel.task3();
		FileWriter writer = new FileWriter("prinel.out");
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write("" + rezultat);
		bw.write('\n');
		bw.close();
	}
}
