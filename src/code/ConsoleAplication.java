package code;

import java.io.Console;
import java.util.Scanner;

public class ConsoleAplication {

	public static void main(String[] args) {
		Console console = System.console();
		Scanner scanner = new Scanner(console.reader());

		System.out.println("Insira a quantidade de blocos e depois os comandos:");
		Integer blocks = Integer.parseInt(scanner.nextLine());
		int matrix[][] = new int[blocks][blocks];
		matrix = create(matrix, blocks);
		String response;
		String[] comand;

		do {
			response = scanner.nextLine();
			comand = response.split(" ");

			if(comand[0].equals("move")) {

				

				int a = Integer.parseInt(comand[1]);
				int b = Integer.parseInt(comand[3]);

				
					if (comand[2].equals("onto")) {
						matrix = onto(a, b, matrix);
					} else if (comand[2].equals("over")) {
						matrix = over(a, b, matrix);
					} else if (comand[2].equals("over2")) {
						matrix = over2(a, b, matrix);
					} else {
						System.out.println("comando não reconhecido.");
					}
			};

		}while(!response.equals("quit"));

		list(matrix);

	}

	public static int[][] onto(Integer a, Integer b, int[][] matrix) {

		int x = 0;
		int y = 0;
		int xa = 0;
		int ya = 0;
		int xb = 0;
		int yb = 0;
		while (x < matrix.length) {
			y = 0;
			while (y < matrix[0].length) {
				if (matrix[x][y] == a) {
					xa = x;
					ya = y;
				} else if (matrix[x][y] == b) {
					xb = x;
					yb = y;
				}
				y = y + 1;
			}
			;
			x = x + 1;
		}
		;

		int i = 1;

		// retorna todos os valores depois de B
		i = 1;

		while (yb + i < matrix[0].length) {
			if (matrix[xb][yb + i] >= 0) {
				matrix[matrix[xb][yb + i]][0] = matrix[xb][yb + i];
				matrix[xb][yb + i] = -1;
			}
			i = i + 1;
		}
		;

		// coloca A na fila de B
		i = 1;
		while (yb + i < matrix[0].length) {
			if (matrix[xb][yb + i] < 0) {
				matrix[xb][yb + i] = matrix[xa][ya];
				i = matrix[0].length;
			}
			i = i + 1;
		}
		;

		// retorna todos os valores depois de A
		i = 1;
		matrix[xa][ya] = -1;

		while (ya + i < matrix[0].length) {
			if (matrix[xa][ya + i] >= 0) {
				matrix[matrix[xa][ya + i]][0] = matrix[xa][ya + i];
				matrix[xa][ya + i] = -1;
			}
			i = i + 1;
		}
		;

		return matrix;

	}

	public static int[][] over(Integer a, Integer b, int[][] matrix) {
		int x = 0;
		int y = 0;
		int xa = 0;
		int ya = 0;
		int xb = 0;
		int yb = 0;
		while (x < matrix.length) {
			y = 0;
			while (y < matrix[0].length) {
				if (matrix[x][y] == a) {
					xa = x;
					ya = y;
				} else if (matrix[x][y] == b) {
					xb = x;
					yb = y;
				}
				y = y + 1;
			}
			;
			x = x + 1;
		}
		;

		int i = 0;
		x = 0;
		y = 0;

		while (yb + i < matrix[0].length) {
			if (matrix[xb][yb + i] < 0) {
				matrix[xb][yb + i] = matrix[xa][ya]; // colocando bloco A na fila do bloco B
				i = matrix[0].length;
			}
			i = i + 1;
		}
		;

		// retornar para a posição inicial qualquer bloco que esteja posicionado após o
		// bloco A
		i = 1;
		matrix[xa][ya] = -1;

		while (ya + i < matrix[0].length) {
			if (matrix[xa][ya + i] >= 0) {
				matrix[matrix[xa][ya + i]][0] = matrix[xa][ya + i];
				matrix[xa][ya + i] = -1;
			}
			i = i + 1;
		}
		;

		return matrix;

	}

	public static int[][] over2(Integer a, Integer b, int[][] matrix) {
		int x = 0;
		int y = 0;
		int xa = 0;
		int ya = 0;
		int xb = 0;
		int yb = 0;
		while (x < matrix.length) {
			y = 0;
			while (y < matrix[0].length) {
				if (matrix[x][y] == a) {
					xa = x;
					ya = y;
				} else if (matrix[x][y] == b) {
					xb = x;
					yb = y;
				}
				y = y + 1;
			}
			;
			x = x + 1;
		}
		;

		int i = 0;

		while (i < matrix[0].length) {
			if (matrix[b][i] < 0) {
				matrix[b][i] = matrix[xa][ya]; // colocando bloco A na fila do coluna B
				i = matrix[0].length;
			}
			i = i + 1;
		}
		;

		// retornar para a posição inicial qualquer bloco que esteja posicionado após o
		// bloco A
		i = 1;
		matrix[xa][ya] = -1;

		while (ya + i < matrix[0].length) {
			if (matrix[xa][ya + i] >= 0) {
				matrix[matrix[xa][ya + i]][0] = matrix[xa][ya + i];
				matrix[xa][ya + i] = -1;
			}
			i = i + 1;
		}
		;

		return matrix;

	}

	public static int[][] create(int[][] matrix, Integer number) {
		int x = 0;

		do {
			int y = 0;
			do {
				if (y == 0) {
					matrix[x][0] = x;
				} else {
					matrix[x][y] = -1;
				}
				y = y + 1;
			} while (y < number);
			x = x + 1;
		} while (x < number);

		return matrix;
	}

	public static void list(int[][] matrix) {
		int x = 0;
		do {
			String string = "";
			int y = 0;
			do {
				if (matrix[x][y] >= 0) {
					string = string + " " + matrix[x][y];
				}
				y = y + 1;
			} while (y < matrix[0].length);

			System.out.println(x + ":" + string);
			x = x + 1;
		} while (x < matrix.length);
	}

}
