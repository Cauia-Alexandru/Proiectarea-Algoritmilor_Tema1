import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Walsh {

	/**
	 *
	 * @param x indice de pe linie
	 * @param y indice de pe coloana
	 * @param pos1_1 1a coordonata a primului punct
	 * @param pos1_2 a 2a coordonata a primului punct
	 * @param pos2_1 1a coordonata a lui 2lea punt
	 * @param pos2_2 a 2a coordonata a lui 2lea punct
	 * @return functia returneaza true daca punctul (x,y) se afla intre punctele
	 * 			      (pos1_1, pos1_2) si (pos2_1, pos2_2), adica in acel cadran,
	 * 											        sau fals daca nu se afla
	 */
	public boolean inCadran(int x, int y, int pos1_1, int pos1_2, int pos2_1, int pos2_2) {
		return (pos1_1 <= x && x <= pos2_1) && (pos1_2 <= y && y <= pos2_2);
	}

	/**
	 *
	 * @param x indice de pe linie
	 * @param y indice de pe coloana
	 * @param tlx parametru care initial e 1, il utilizez pentru a crea si alte
	 *            variabile pentru a sti pe ce cadran sa apelez metoda(pe linie)
	 * @param tly --------//-----(pe coloana)
	 * @param D dimensiunea patratului
	 * @param value aceasta valoare e 0 sau 1, la fiecare apel recursiv a metodei
	 *              pe partea dreapta-jos se schimba
	 * @return functia returneaza valoare de la pozitia (x,y)
	 */
	int walshValue(int x, int y, int tlx, int tly, int D, int value) {
		if (D == 1) {
			return value;
		}

		int downRightX = tlx + D - 1;
		int downRightY = tly + D - 1;

		int middleX = (tlx + downRightX) / 2;
		int middleY = (tly + downRightY) / 2;

		int halfD = D / 2;
		//stanga sus
		if (inCadran(x, y, tlx, tly, middleX, middleY)) {
			return walshValue(x, y, tlx, tly, halfD, value);
		}
		//dreapta sus
		if (inCadran(x, y, tlx, middleY + 1, middleX, downRightY)) {
			return walshValue(x, y, tlx, middleY + 1, halfD, value);
		}

		//stanga jos
		if (inCadran(x, y, middleX + 1, tly, downRightX, middleY)) {
			return walshValue(x, y, middleX + 1, tly, halfD, value);
		}

		//dreapta jos
		if (inCadran(x, y, middleX + 1, middleY + 1, downRightX, downRightY)) {
			if (value == 0) {
				return walshValue(x, y, middleX + 1, middleY + 1, halfD, 1);
			} else {
				return walshValue(x, y, middleX + 1, middleY + 1, halfD, 0);
			}

		}

		return -1;
	}


	public void task1() throws IOException {
		Scanner scanner = new Scanner(new FileReader("walsh.in"));
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		FileWriter writer = new FileWriter("walsh.out");
		BufferedWriter buffer = new BufferedWriter(writer);
		while (scanner.hasNextInt()) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int res = walshValue(x, y, 1, 1, N, 0);
			buffer.write("" + res);
			buffer.write('\n');
		}
		buffer.close();

	}

	public static void main(String[] args) throws IOException {
		Walsh walsh = new Walsh();
		walsh.task1();
	}
}
