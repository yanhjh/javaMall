
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
		System.out.println("그곳으로는 갈 수 없다");

	}

	void cantGo() {
		System.out.println("그렇게는 이동할 수 없다");
	}

	int gameEnd() {
		System.out.println("몸통에 머리가 닿아 게임 끝");
		return -1;
	}

	void getTail(int[][] snake) {

		Snake.count++;

		snake[Snake.idx1][Snake.idx2] = Snake.count;
	}

	void snakeMove(int[][] snake) {
		// 마지막꼬리가 있던 좌표값 저장
		for (int i = 0; i < Snake.size; i++) {
			for (int j = 0; j < Snake.size; j++) {
				if (snake[i][j] == Snake.count) {
					Snake.idx1 = i;
					Snake.idx2 = j;
					break;
				}
			}
		}
//머리를 제외하고 한칸씩 앞으로 이동
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
//머리는 2번째 3번째 몸통으로 이동불가
		else if (snake[Snake.y - 1][Snake.x] == 2 || snake[Snake.y - 1][Snake.x] == 3) {
			cantGo();
			return;
		} // 3번째 이후 몸통에 머리가 닿을때
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
		System.out.println("1~4번사이값을 입력해주세요");
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
//꼬리는 최대가 8이라서 9가됐을시 0으로 바꿔준다(0은 길)
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
					System.out.print("[♥]");
				} else {
					System.out.print("[■]");
				}
			}
			System.out.println();
		}

		for (int i = 0; i < snake.length; i++) {
			for (int j = 0; j < snake.length; j++) {
				if (snake[i][j] == 1) {
					// 머리좌표 전달
					Snake.x = j;
					Snake.y = i;
				}

			}
		}

	}

	void init() {

		Random rd = new Random();
		Scanner sc = new Scanner(System.in);
//스네이크 생성 [0][0]~[0][3] 스네이크는 머리가 1 꼬리가 2,3,4...최대8까지
		for (int i = 0; i < 4; i++) {

			snake[0][i] = i + 1;
		}
//아이템 랜덤으로 10개 생성 아이템의 값은 -1 길의 값은 0
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
		 * [스네이크게임] 1. 10x10 배열을 0으로 채운다. 2. 스네이크는 1234로 표시한다. 3. 머리 상하좌우로 이동이 가능하며, 꼬리가
		 * 따라온다. 4. 자기몸하고 부딪히면, 사망한다. 5. 랜덤으로 아이템을 생성해 아이템을 먹으면 꼬리 1개가 자란다. 6. 꼬리는 최대
		 * 8개까지 증가할 수 있다.
		 */

		Setting a = new Setting();
		a.run();

	}

}