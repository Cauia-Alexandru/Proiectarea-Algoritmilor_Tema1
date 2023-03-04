
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Statistics {
	ArrayList<String> words = new ArrayList<>();

	public void readWords() throws IOException {
		Scanner scanner = new Scanner(new FileReader("statistics.in"));
		int N = scanner.nextInt();
		String word;
		word = scanner.nextLine();
		while (scanner.hasNext()) {
			word = scanner.nextLine();
			words.add(word);
		}
	}

	/**
	 *
	 * @param character fiecare cuvant(caracter cu caracter) il compar
	 *                    cu acest parametru
	 * @return un arraylist cu (nr de aparitii a "character") - (nr de caractere != de "characater")
	 * 					per fiecare cuvant
	 */
	public ArrayList<Integer> nrOfApparitions(int character) {
		ArrayList<Integer> vect = new ArrayList<>();

		for (String s : words) {
			int differenceOfApp = 0;
			for (char el : s.toCharArray()) {
				if (el == character) {
					differenceOfApp++;
				} else {
					differenceOfApp--;
				}
			}
			vect.add(differenceOfApp);
		}
		return vect;
	}

	/**
	 * functia calculeaza numarul maxim de cuvinte din lista, care
	 * in urma concatenarii lor, o litera este dominanta
	 */
	public void maxNrWordsConcat() throws IOException {
		int maxWords = 0;
		//pentru fiecare litera din alfabel apelez functia nrOfApparitions
		for (char character = 'a'; character <= 'z'; character++) {
			ArrayList<Integer> nrOfAppar = nrOfApparitions(character);

			//sortez descrescator
			Collections.sort(nrOfAppar);
			Collections.reverse(nrOfAppar);

			//de cate ori adun cifrele din lista sortata si am rezultat>0, incrementez un acumulator
			int sumOfApp = 0, res = 0;
			for (int i : nrOfAppar) {
				sumOfApp += i;

				if (sumOfApp > 0) {
					res++;
				}
			}
			maxWords = Math.max(maxWords, res);
		}
		FileWriter writer = new FileWriter("statistics.out");
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write("" + maxWords);
		bw.write('\n');
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		Statistics statistics = new Statistics();
		statistics.readWords();
		statistics.maxNrWordsConcat();
	}
}
