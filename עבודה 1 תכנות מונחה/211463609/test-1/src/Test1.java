
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzles bord = new Puzzles(getBoardSize());	// ����� ���
		play(bord); 								// ����� "����"

	}

	public static void play(Puzzles bord) {
		int number_Of_Moves = 0;					// ���� �� ���� ������
		int max_Moves = getMaxMoves(); 				// ���� ������ ��������
		while (!bord.puzzleOrdered()) {				// ���� ������ �� ��� ���� �� ����
			System.out.println(bord); 				// ����� �� ���� ������ �� ����
			number_Of_Moves += 1;
			if (number_Of_Moves >= max_Moves + 1) {	// ��� ����� ����� ������� �������� �� ����
				System.out.println("I Give Up!");
				return;
			}
			getNumber(bord);						// ���� ���� ����� �������

		}
	}

	public static int getBoardSize() { 		// ���� ���� ��� �������
		Scanner scan = new Scanner(System.in);
		int sc; // �����
		do {
			System.out.print("Board Size:");// ����� �� ������ �� ���� ����
			sc = scan.nextInt();			// �����

		} while (!sizeInRange(sc));			// �� ������ �� ����� �� ����� ��� ����
		return (sc);
	}

	public static int getMaxMoves() { 				// ���� ���� ������ ������� �������
		Scanner scan = new Scanner(System.in);
		int sc; // �����
		do {
			System.out.print("Number Of Moves:"); 	// ����� �� ������ �� ���� ������� ���������
			sc = scan.nextInt();					// �����

		} while (sc < 1);							// �� ������ �� ����� �� ����� ��� ����
		return (sc);

	}

	public static void getNumber(Puzzles bord) {	// ���� ������� ���� ���� ��� ���� �����
		Scanner scan = new Scanner(System.in);
		int sc;// �����
		do {
			System.out.print("Your move:");			// ����
			sc = scan.nextInt();// �����

		} while (!bord.moveNumber(sc));				// �� ������ �� ����� �� ����� ��� ����
	}

	public static boolean sizeInRange(int size) { 	// ���� �� ����� ����� ��� �� ����� ����
		if (size < 9 && size > 1) {
			return (true);
		}
		System.out.println("Invalid Size!");
		return (false);
	}

}
