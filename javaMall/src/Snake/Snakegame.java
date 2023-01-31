
package Snake;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Snake {
	static int size = 10;
	static int count = 4;
	static int x;
	static int y;
	static int idx1;
	static int idx2;
	static int check;
}

class SnakeDAO {

	void outOfRange() {
		System.out.println("�װ����δ� �� �� ����");

	}

	void cantGo() {
		System.out.println("�׷��Դ� �̵��� �� ����");
	}

	int gameEnd() {
		System.out.println("���뿡 �Ӹ��� ��� ���� ��");
		return -1;
	}

	void getTail(int[][] snake) {

		Snake.count++;

		snake[Snake.idx1][Snake.idx2] = Snake.count;
	}

	void snakeMove(int[][] snake) {
		// ������������ �ִ� ��ǥ�� ����
		for (int i = 0; i < Snake.size; i++) {
			for (int j = 0; j < Snake.size; j++) {
				if (snake[i][j] == Snake.count) {
					Snake.idx1 = i;
					Snake.idx2 = j;
					break;
				}
			}
		}
//�Ӹ��� �����ϰ� ��ĭ�� ������ �̵�
		for (int i = Snake.count; i >= 2; i--) {
			for (int j = 0; j < Snake.size * Snake.size; j++) {
				if (snake[j / Snake.size][j % Snake.size] == i - 1) {
					snake[j / Snake.size][j % Snake.size] = i;
				}

			}

		}
	}

	void goUp(int[][] snake) {

		if (Snake.y == 0) {
			outOfRange();
			return;
		}

		if (snake[Snake.y - 1][Snake.x] == -1) {
			snakeMove(snake);

			snake[Snake.y - 1][Snake.x] = 1;
			getTail(snake);
		}

		else if (snake[Snake.y - 1][Snake.x] == 0) {
			snakeMove(snake);

			snake[Snake.y - 1][Snake.x] = 1;
			snake[Snake.idx1][Snake.idx2] = 0;
		}
//�Ӹ��� 2��° 3��° �������� �̵��Ұ�
		else if (snake[Snake.y - 1][Snake.x] == 2 || snake[Snake.y - 1][Snake.x] == 3) {
			cantGo();
			return;
		} // 3��° ���� ���뿡 �Ӹ��� ������
		else {
			int b = gameEnd();
			Snake.check = b;

			return;
		}
	}

	void goDown(int[][] snake) {
		if (Snake.y == Snake.size - 1) {
			outOfRange();
			return;
		}

		if (snake[Snake.y + 1][Snake.x] == -1) {
			snakeMove(snake);

			snake[Snake.y + 1][Snake.x] = 1;
			getTail(snake);
			return;
		}

		else if (snake[Snake.y + 1][Snake.x] == 0) {
			snakeMove(snake);

			snake[Snake.y + 1][Snake.x] = 1;
			snake[Snake.idx1][Snake.idx2] = 0;
		}

		else if (snake[Snake.y + 1][Snake.x] == 2 || snake[Snake.y + 1][Snake.x] == 3) {
			cantGo();
			return;
		} else {
			int b = gameEnd();
			Snake.check = b;

			return;
		}
	}

	void goLeft(int[][] snake) {
		if (Snake.x == 0) {
			outOfRange();
			return;
		}

		if (snake[Snake.y][Snake.x - 1] == -1) {
			snakeMove(snake);

			snake[Snake.y][Snake.x - 1] = 1;
			getTail(snake);
		}

		else if (snake[Snake.y][Snake.x - 1] == 0) {
			snakeMove(snake);

			snake[Snake.y][Snake.x - 1] = 1;
			snake[Snake.idx1][Snake.idx2] = 0;
		}

		else if (snake[Snake.y][Snake.x - 1] == 2 || snake[Snake.y][Snake.x - 1] == 3) {
			cantGo();
			return;
		} else {
			int b = gameEnd();
			Snake.check = b;

			return;
		}
	}

	void goRight(int[][] snake) {
		if (Snake.x == Snake.size - 1) {
			outOfRange();
			return;
		}

		if (snake[Snake.y][Snake.x + 1] == -1) {
			snakeMove(snake);

			snake[Snake.y][Snake.x + 1] = 1;
			getTail(snake);
		}

		else if (snake[Snake.y][Snake.x + 1] == 0) {
			snakeMove(snake);

			snake[Snake.y][Snake.x + 1] = 1;
			snake[Snake.idx1][Snake.idx2] = 0;
		}

		else if (snake[Snake.y][Snake.x + 1] == 2 || snake[Snake.y][Snake.x + 1] == 3) {
			cantGo();
			return;
		} else {
			int b = gameEnd();
			Snake.check = b;

			return;
		}
	}

}

class Setting {
	SnakeDAO a = new SnakeDAO();

	Scanner sc = new Scanner(System.in);
	int[][] snake = new int[10][10];

	void wrongChoice() {
		System.out.println("1~4�����̰��� �Է����ּ���");
	}

	void checkChoice(int choice) {

		if (choice == 1) {
			a.goLeft(snake);
		} else if (choice == 2) {
			a.goRight(snake);
		} else if (choice == 3) {
			a.goUp(snake);
		} else if (choice == 4) {
			a.goDown(snake);
		} else {
			wrongChoice();
		}
	}

	int showMsg() {
		System.out.println("[1.left] [2.right] [3.up] [4.down]");
		int choice = sc.nextInt();
		return choice;

	}

	void showMap() {

		for (int i = 0; i < snake.length; i++) {
			for (int j = 0; j < snake[i].length; j++) {
//������ �ִ밡 8�̶� 9�������� 0���� �ٲ��ش�(0�� ��)
				if (snake[i][j] == 9) {
					snake[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < snake.length; i++) {
			for (int j = 0; j < snake[i].length; j++) {
				if (snake[i][j] == 1) {
					System.out.print("[s]");
				} else if (snake[i][j] == 0) {
					System.out.print("[ ]");
				} else if (snake[i][j] == -1) {
					System.out.print("[��]");
				} else {
					System.out.print("[��]");
				}
			}
			System.out.println();
		}

		for (int i = 0; i < snake.length; i++) {
			for (int j = 0; j < snake.length; j++) {
				if (snake[i][j] == 1) {
					// �Ӹ���ǥ ����
					Snake.x = j;
					Snake.y = i;
				}

			}
		}

	}

	void init() {

		Random rd = new Random();
		Scanner sc = new Scanner(System.in);
//������ũ ���� [0][0]~[0][3] ������ũ�� �Ӹ��� 1 ������ 2,3,4...�ִ�8����
		for (int i = 0; i < 4; i++) {

			snake[0][i] = i + 1;
		}
//������ �������� 10�� ���� �������� ���� -1 ���� ���� 0
		for (int i = 0; i < 10; i++) {
			int num = rd.nextInt(100);
			if (snake[num / snake.length][num % snake.length] == 0) {
				snake[num / snake.length][num % snake.length] = -1;
			} else {
				i--;
				continue;
			}
		}

	}

	void run() {

		init();

		while (true) {

			showMap();

			checkChoice(showMsg());
			if (Snake.count > 8) {
				Snake.count--;
			}

			if (Snake.check == -1) {
				break;
			}

		}

	}
}

public class Snakegame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//TODO Auto-generated method stub

		/*
		 * [������ũ����] 1. 10x10 �迭�� 0���� ä���. 2. ������ũ�� 1234�� ǥ���Ѵ�. 3. �Ӹ� �����¿�� �̵��� �����ϸ�, ������
		 * ����´�. 4. �ڱ���ϰ� �ε�����, ����Ѵ�. 5. �������� �������� ������ �������� ������ ���� 1���� �ڶ���. 6. ������ �ִ�
		 * 8������ ������ �� �ִ�.
		 */

		Setting a = new Setting();
		a.run();

	}

}