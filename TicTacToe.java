import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/** 
 * Игра крестики-нолики на доске 3х3
 * @author Uhtenberg
 *
 */

public class TicTacToe extends Applet implements ActionListener {
	
Button squares[];
Button newGameButton;
Label score;
int emptySquaresLeft=9;

/**
 * метод init - это конструктор апплета
 */

	public void init() {
		// устанавливаем менеджер расположения апплета, его шрифт и цвет
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		
		// Изменяем шрифт апплета так, чтобы он был жирным
		// и имел размер 20
		
		Font appletFont=new Font("Monospaced",Font.BOLD, 20);
		this.setFont(appletFont);
		
		// Создаем кнопку New Game и регистрируем в ней слушатель действия
		
		newGameButton = new Button("New Game");
		
		newGameButton.addActionListener(this);
		
		Panel topPanel = new Panel();
		topPanel.add(newGameButton);
		
			this.add(topPanel,"North");
			
			Panel centralPanel = new Panel();
			centralPanel.setLayout(new GridLayout(3, 3));
			this.add(centralPanel,"Center");
			
			score=new Label("Yout turn!");
			this.add(score,"South");
			
			// создаем массив, чтобы хранить в нем ссылки на 9 кнопок
			
			squares=new Button[9];
			
			// Создаем кнопки, сохраняем ссылки на них в массиве
			// регистрируем в них слушатель, красим в оранжевый цвет
			// и добавляем их на панель
			
			for (int i=0; i<squares.length; i++) {
				squares[i]=new Button();
				squares[i].addActionListener(this);
				squares[i].setBackground(Color.ORANGE);
				centralPanel.add(squares[i]);
				
			}
			
	}
	
	// Этот метод будет обрабатывать все события
	
	public void actionPerformed(ActionEvent e) {
		
		Button theButton = (Button) e.getSource();
		
		// Это кнопка New Game?
		
		if (theButton == newGameButton) {
			
			for (int i = 0; i<squares.length; i++) {
				squares[i].setEnabled(true);
				squares[i].setLabel("");
				squares[i].setBackground(Color.ORANGE);
					
			}
			
		emptySquaresLeft=9;
		score.setText("Your turn!");
		newGameButton.setEnabled(false);
		
		return; // выходим из метода
		
		}
		
		String winner = "";
		
		// Это одна из клеток?
		
		for (int i=0; i<squares.length; i++) {
			
			if (theButton == squares[i]) {
				
				squares[i].setLabel("X");
				winner = lookForWinner();
				
				if (!"".equals(winner)) {
					
					endTheGame();
					
				} else {
					
					computerMove();
					winner = lookForWinner();
					
					if (!"".equals(winner)) {
						endTheGame();
					}
				}
				
				break;
			}
		}
		
		// конец цикла for
		
		if (winner.equals("X")) {
			score.setText("You win!");
			
		} else if (winner.equals("O")) {
			score.setText("You lost!");
			
		} else if (winner.equals("T")) {
			score.setText("It's a tie!");
			
		}
	} // конец метода actionPerformed 
			
				/** Этот метод вызывается после каждого хода,
				 * чтобы узнать, есть ли  победитель.
				 * Он проверяет каждый ряд, колонку и диагональ,
				 * чтобы найти три клетки с одинаковыми надписями (не пустые)
				 * "Х", "О", "Т" (ничья), "" - еще нет победителя
				 */
	
	String lookForWinner() {
		String theWinner = "";
		emptySquaresLeft--;
		
		if (emptySquaresLeft==0) {
			return "T"; // Это ничья. Т от английского слова tie (ничья)
		}
		
		// проверяем ряд 1 - элементы массива 0,1,2
		if (!squares[0].getLabel().equals("") && 
				squares[1].getLabel().equals(squares[0].getLabel()) &&
				squares[2].getLabel().equals(squares[0].getLabel())) {
			
			theWinner = squares[0].getLabel();
			highlightWinner(0,1,2);
		
		// проверяем ряд 2 - элементы массива 3,4,5
		} else if (!squares[3].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[3].getLabel()) &&
				squares[5].getLabel().equals(squares[3].getLabel())) {
			theWinner = squares[3].getLabel();
			highlightWinner(3,4,5);
			
		// проверяем ряд 3 - элементы массива 6,7,8
		} else if (!squares[6].getLabel().equals("") &&
				squares[7].getLabel().equals(squares[6].getLabel()) &&
				squares[8].getLabel().equals(squares[6].getLabel())) {
			theWinner = squares[6].getLabel();
			highlightWinner(6,7,8);
			
		// проверяем колонку 1 - элементы массива 0,3,6
		} else if (!squares[0].getLabel().equals("") &&
				squares[3].getLabel().equals(squares[0].getLabel()) &&
				squares[6].getLabel().equals(squares[0].getLabel())) {
			theWinner = squares[0].getLabel();
			highlightWinner(0,3,6);
			
		// проверяем колонку 2 - элементы массива 1,4,7
		} else if (!squares[1].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[1].getLabel()) &&
				squares[7].getLabel().equals(squares[1].getLabel())) {
			theWinner = squares[1].getLabel();
			highlightWinner(1,4,7);
			
		// проверяем колонку 3 - элементы массива 2,5,8
		} else if (!squares[2].getLabel().equals("") &&
				squares[5].getLabel().equals(squares[2].getLabel()) &&
				squares[8].getLabel().equals(squares[2].getLabel())) {
			theWinner = squares[2].getLabel();
			highlightWinner(2,5,8);
			
		// проверяем диагональ 1 - элементы массива 0,4,8
		} else if (!squares[0].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[0].getLabel()) &&
				squares[8].getLabel().equals(squares[0].getLabel())) {
			theWinner = squares[0].getLabel();
			highlightWinner(0,4,8);
			
		// проверяем диагональ 2 - элементы массива 2,4,6
		} else if (!squares[2].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[2].getLabel()) &&
				squares[6].getLabel().equals(squares[2].getLabel())) {
			theWinner = squares[2].getLabel();
			highlightWinner(2,4,6);
			
		}
				
		return theWinner;		
				
		}
		
		/** этот метод будет искать лучший ход
		 * для комьютера. если такого хода нет,
		 * компьютер выбирает случайную клетку.
		 */
	
	void computerMove() {
		
		int selectedSquare;
		
		// Сначала компьютер попытается найти пустую клетку рядом с двумя О чтобы выиграть
		
		selectedSquare= findEmptySquare("O");
		
		// Если он не сможет найти такую клетку, он попытается хотя бы помешать игроку, поставив О рядом с двумя Х
		
		if (selectedSquare == -1) {
			
			selectedSquare = findEmptySquare("X");
			
		}
		
		// Если selectedSquare по-прежнему равен -1, то компьютер попытается занять центральную клетку
		
		if ( (selectedSquare == -1) && squares[4].getLabel().contentEquals("")) {
			
			selectedSquare = 4;
	
		}
		
		// если не повезло с центральной клеткой - занимает случайную
		
		if (selectedSquare == -1) {
			
			selectedSquare = getRandomSquare();
			
		}
		
		squares[selectedSquare].setLabel("O");
		
	}
	
	/**  Этот метод проверяет каждую колонку, ряд и диагональ, 
	 * чтобы узнать, есть ли в ней две клетки с одинаковыми надписями и пустой клеткой
	 * в качестве параметров игроку передается Х, компьютеру О
	 * возвращает метод количество свободных клеток или -1, если не найдено
	 * две клетки с одинаковыми надписями
	 */
	
	int findEmptySquare(String player) {
		
		int weight[] = new int[9];
		
		for (int i = 0; i<weight.length; i++) {
			
			if (squares[i].getLabel().equals("O")) {
				weight[i]=-1;
			} else if (squares[i].getLabel().equals("X")) {
				weight[i]=1;
			} else {
				weight[i]=0;
			}
		}
			
		int twoWeight = player.equals("O") ? -2 : 2;
			
			// Проверим, есть ли в ряду 1 две одинаковых клетки и одна пустая
			
		if (weight[0] + weight[1] + weight[2] == twoWeight) {
				if (weight[0]== 0) {
					return 0;
				} else if (weight[1]== 0) {
					return 1;
				} else if (weight[2]==0) {
					return 2;
				}
			}
				
			// Проверим, есть ли в ряду 2 две одинаковых клетки и одна пустая
				
		if (weight[3] + weight[4] + weight[5]==twoWeight) {
				if (weight[3]==0) {
					return 3;
				} else if (weight[4]==0) {
					return 4;
				} else if (weight[5]==0) {
					return 5;
				}
			}
				
			// Проверим, есть ли в ряду 3 две одинаковых клетки и одна пустая
				
		if (weight[6] + weight[7] + weight[8]==twoWeight) {
				if (weight[6]==0) {
					return 6;
				} else if (weight[7]==0) {
					return 7;
				}else if (weight[8]==0) {
					return 8;
				}
			}
				
			// Проверим, есть ли в колонке 1 две одинаковых клетки и одна пустая
				
		if (weight[0] + weight[3] + weight[6]==twoWeight) {
				if (weight[0]==0) {
					return 0;
				} else if (weight[3]==0) {
					return 3;
				}else if (weight[6]==0) {
					return 6;
				}
			}
				
			// Проверим, есть ли в колонке 2 две одинаковых клетки и одна пустая
				
		if (weight[1] + weight[4] + weight[7]==twoWeight) {
				if (weight[1]==0) {
					return 1;
				} else if (weight[4]==0) {
					return 4;
				} else if (weight[7]==0) {
					return 7;
				}
			}
				
			// Проверяем колонку номер 3 на две одинаковых клетки и одну пустую
				
		if (weight[2] + weight[5] + weight[8]==twoWeight) {
				if (weight[2]==0) {
					return 2;
				} else if (weight[5]==0) {
					return 5;
				} else if (weight[8]==0) {
					return 8;
				}
			}
				
			// Проверяем обе диагонали на две одинаковых и одну пустую
				
		if (weight[0] + weight[4] + weight[8]==twoWeight) {
				if (weight[0]==0) {
					return 0;
				} else if (weight[4]==0) {
					return 4;
				} else if (weight[8]==0) {
					return 8;
				}
			}
				
		if (weight[2] + weight[4] + weight[6]==twoWeight) {
				if (weight[2]==0) {
					return 2;
				} else if (weight[4]==0) {
					return 4;
				} else if (weight[6]==0) {
					return 6;
				}
			}
			
			// Если не найдено двух одинаковых соседних клеток
			
		return -1;
				
		} // Конец метода findEmptySquare
	
		/** Этот метод будет
		 * выбирать любую
		 * пустую клетку
		 */
		
		int getRandomSquare() {
			
			boolean gotEmptySquare = false;
			
			int SelectedSquare = -1;
			
			do {
				
				SelectedSquare = (int) (Math.random() * 9);
				
				if (squares[SelectedSquare].getLabel().equals("")) {
					
					gotEmptySquare = true; // чтобы закончить цикл
				}
			} while (!gotEmptySquare);
			
			return SelectedSquare;
			
		} // конец метода getRandomSquare
		
		/** Этот метод выделяет выигравшую линию
		 * параметрами служат первая, вторая и третья клетки для выделения
		 */
		
		void highlightWinner(int win1, int win2, int win3) {
			squares[win1].setBackground(Color.CYAN);
			squares[win2].setBackground(Color.CYAN);
			squares[win3].setBackground(Color.CYAN);
			
		}
		
		// Делаем недоступными клетки и доступной кнопку "New Game"
		
		void endTheGame() {
			
			newGameButton.setEnabled(true);
			
			for(int i = 0; i<squares.length; i++) {
				squares[i].setEnabled(false);
			}
		}
		
} // конец класса
				