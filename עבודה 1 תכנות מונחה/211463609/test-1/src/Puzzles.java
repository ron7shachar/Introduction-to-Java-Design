
public class Puzzles {
	public int[][] bord; 					// ���� �� ����� ����� �� ����� ����
	private int size; 						// ����� ����

	public Puzzles(int size) {
		bord = new int[size][size];
		this.size = size;
		resetTheBord();
	}

	private void resetTheBord() { 								// ���� �� ���� ����� �������
		int plac;
		for (int i = 1; i < Math.pow(size, 2); i += 1) { 		// ���� ����
			plac = randomPlace(); 								// ����� ���������
			for (int j = 1; j < Math.pow(size, 2); j += 1) {    // ���� �� ������ ����� �����
				if (bord[(plac % size)][(plac / size)] == 0) { 	// �� ��� �� �� ���� i
					bord[(plac % size)][(plac / size)] = i;
					break;
				}
				plac = (plac + 1) % (int) (Math.pow(size, 2));	// �� �� ����� ��� ���,������� ������ ������
			}
		}
	}

	public String toString() { 							// ���� string �� ���� ���� ������
		String stringBord = "";
		for (int y = 0; y < size; y += 1) {
			for (int x = 0; x < size; x += 1) {
				if (bord[x][y] == 0) { 					// �� ������ �� 0
					stringBord = stringBord.concat("  ");
				} else {
					stringBord = stringBord.concat(bord[x][y] + " ");
				}
				if (bord[x][y] < 10) { 					// ���� ������� �����
					stringBord = stringBord.concat(" ");
				}
			}
			stringBord = stringBord.concat("\n"); 		// ���� �� ���� ������ ����� ���
		}
		return (stringBord);
	}

	public boolean puzzleOrdered() { 							// ��� �� ��� ����� ���
		for (int i = 0; i < Math.pow(size, 2) - 1; i += 1) {
			if (bord[(i % size)][(i / size)] != i + 1) {		// ����� ���� �� ����� �� ����� false
				return false;
			}
		}
		return true;
	}

	public boolean moveNumber(int number) {	// ����� ����
		if (nearEmpty(number)) { 			// ������ �����?
			moveingNumber(number); 			// ����� �� �����
			return true;
		}
		return (false);

	}

	private void moveingNumber(int number) { 	// ����� �� �����
		int xnum = numberToX_Y(number)[0];
		int ynum = numberToX_Y(number)[1];
		int x0 = numberToX_Y(0)[0];
		int y0 = numberToX_Y(0)[1];
		bord[x0][y0] = number; 					// ��� ��� ��� �� number
		bord[xnum][ynum] = 0; 					// "����" �� number ���� ����
	}					

	public int[] numberToX_Y(int number) { 					// get number and return the coordinates,[0]=x,[1]=y
		int[] x_y = new int[2];
		for (int i = 0; i < Math.pow(size, 2); i += 1) { 	// �� �� ����� �� ����� ��� �� number
			if (bord[(i % size)][(i / size)] == number) {
				x_y[0] = i % size;
				x_y[1] = i / size;
				break;
			}

		}
		return x_y;
	}

	private int randomPlace() { // ���� ���� �� ����� �����
		return (int) (Math.random() * (Math.pow(size, 2)));

	}

	private boolean nearEmpty(int number) { // ��� ��� ���� ���� �� ��
		int xnum = numberToX_Y(number)[0];
		int ynum = numberToX_Y(number)[1];
		int x0 = numberToX_Y(0)[0];
		int y0 = numberToX_Y(0)[1];

		if (xnum == x0) {
			if (ynum + 1 == y0 || ynum - 1 == y0) {
				return true;
			}
		}
		if (ynum == y0) {
			if (xnum + 1 == x0 || xnum - 1 == x0) {
				return true;
			}
		}
		System.out.println("Invalid move!");
		return false;
	}

}
