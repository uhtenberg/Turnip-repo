import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/** 
 * ���� ��������-������ �� ����� 3�3
 * @author Uhtenberg
 *
 */

public class TicTacToe extends Applet implements ActionListener {
	
Button squares[];
Button newGameButton;
Label score;
int emptySquaresLeft=9;
int W=0;
int L=0;
Label win;
Label lost;

/**
 * ����� init - ��� ����������� �������
 */

	public void init() {
		// ������������� �������� ������������ �������, ��� ����� � ����
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		
		// �������� ����� ������� ���, ����� �� ��� ������
		// � ���� ������ 20
		
		Font appletFont=new Font("Monospaced",Font.BOLD, 20);
		this.setFont(appletFont);
		
		// ������� ������ New Game � ������������ � ��� ��������� ��������
		
		newGameButton = new Button("New Game");
		
		newGameButton.addActionListener(this);
		
		win = new Label("W :" + W);
		lost = new Label("L :" + L);
		Panel topPanel = new Panel();
		topPanel.setLayout(new GridLayout(2,2));
		topPanel.add(win);
		topPanel.add(lost);
		topPanel.add(newGameButton);
		
			this.add(topPanel,"North");
			
			Panel centralPanel = new Panel();
			centralPanel.setLayout(new GridLayout(3, 3));
			this.add(centralPanel,"Center");
			
			score=new Label("Yout turn!");
			this.add(score,"South");
			
			// ������� ������, ����� ������� � ��� ������ �� 9 ������
			
			squares=new Button[9];
			
			// ������� ������, ��������� ������ �� ��� � �������
			// ������������ � ��� ���������, ������ � ��������� ����
			// � ��������� �� �� ������
			
			for (int i=0; i<squares.length; i++) {
				squares[i]=new Button();
				squares[i].addActionListener(this);
				squares[i].setBackground(Color.ORANGE);
				centralPanel.add(squares[i]);
				
			}
			
	}
	
	// ���� ����� ����� ������������ ��� �������
	
	public void actionPerformed(ActionEvent e) {
		
		Button theButton = (Button) e.getSource();
		
		// ��� ������ New Game?
		
		if (theButton == newGameButton) {
			
			for (int i = 0; i<squares.length; i++) {
				squares[i].setEnabled(true);
				squares[i].setLabel("");
				squares[i].setBackground(Color.ORANGE);
					
			}
			
		emptySquaresLeft=9;
		score.setText("Your turn!");
		newGameButton.setEnabled(false);
		
		return; // ������� �� ������
		
		}
		
		String winner = "";
		
		// ��� ���� �� ������?
		
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
		
		// ����� ����� for
		
		if (winner.equals("X")) {
			score.setText("You win!");
			
		} else if (winner.equals("O")) {
			score.setText("You lost!");
			
		} else if (winner.equals("T")) {
			score.setText("It's a tie!");
			
		}
	} // ����� ������ actionPerformed 
			
				/** ���� ����� ���������� ����� ������� ����,
				 * ����� ������, ���� ��  ����������.
				 * �� ��������� ������ ���, ������� � ���������,
				 * ����� ����� ��� ������ � ����������� ��������� (�� ������)
				 * "�", "�", "�" (�����), "" - ��� ��� ����������
				 */
	
	String lookForWinner() {
		String theWinner = "";
		emptySquaresLeft--;
		
		if (emptySquaresLeft==0) {
			return "T"; // ��� �����. � �� ����������� ����� tie (�����)
		}
		
		// ��������� ��� 1 - �������� ������� 0,1,2
		if (!squares[0].getLabel().equals("") && 
				squares[1].getLabel().equals(squares[0].getLabel()) &&
				squares[2].getLabel().equals(squares[0].getLabel())) {
			
			theWinner = squares[0].getLabel();
			highlightWinner(0,1,2);
		
		// ��������� ��� 2 - �������� ������� 3,4,5
		} else if (!squares[3].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[3].getLabel()) &&
				squares[5].getLabel().equals(squares[3].getLabel())) {
			theWinner = squares[3].getLabel();
			highlightWinner(3,4,5);
			
		// ��������� ��� 3 - �������� ������� 6,7,8
		} else if (!squares[6].getLabel().equals("") &&
				squares[7].getLabel().equals(squares[6].getLabel()) &&
				squares[8].getLabel().equals(squares[6].getLabel())) {
			theWinner = squares[6].getLabel();
			highlightWinner(6,7,8);
			
		// ��������� ������� 1 - �������� ������� 0,3,6
		} else if (!squares[0].getLabel().equals("") &&
				squares[3].getLabel().equals(squares[0].getLabel()) &&
				squares[6].getLabel().equals(squares[0].getLabel())) {
			theWinner = squares[0].getLabel();
			highlightWinner(0,3,6);
			
		// ��������� ������� 2 - �������� ������� 1,4,7
		} else if (!squares[1].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[1].getLabel()) &&
				squares[7].getLabel().equals(squares[1].getLabel())) {
			theWinner = squares[1].getLabel();
			highlightWinner(1,4,7);
			
		// ��������� ������� 3 - �������� ������� 2,5,8
		} else if (!squares[2].getLabel().equals("") &&
				squares[5].getLabel().equals(squares[2].getLabel()) &&
				squares[8].getLabel().equals(squares[2].getLabel())) {
			theWinner = squares[2].getLabel();
			highlightWinner(2,5,8);
			
		// ��������� ��������� 1 - �������� ������� 0,4,8
		} else if (!squares[0].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[0].getLabel()) &&
				squares[8].getLabel().equals(squares[0].getLabel())) {
			theWinner = squares[0].getLabel();
			highlightWinner(0,4,8);
			
		// ��������� ��������� 2 - �������� ������� 2,4,6
		} else if (!squares[2].getLabel().equals("") &&
				squares[4].getLabel().equals(squares[2].getLabel()) &&
				squares[6].getLabel().equals(squares[2].getLabel())) {
			theWinner = squares[2].getLabel();
			highlightWinner(2,4,6);
			
		}
				
		return theWinner;		
				
		}
		
		/** ���� ����� ����� ������ ������ ���
		 * ��� ���������. ���� ������ ���� ���,
		 * ��������� �������� ��������� ������.
		 */
	
	void computerMove() {
		
		int selectedSquare;
		
		// ������� ��������� ���������� ����� ������ ������ ����� � ����� � ����� ��������
		
		selectedSquare= findEmptySquare("O");
		
		// ���� �� �� ������ ����� ����� ������, �� ���������� ���� �� �������� ������, �������� � ����� � ����� �
		
		if (selectedSquare == -1) {
			
			selectedSquare = findEmptySquare("X");
			
		}
		
		// ���� selectedSquare ��-�������� ����� -1, �� ��������� ���������� ������ ����������� ������
		
		if ( (selectedSquare == -1) && squares[4].getLabel().contentEquals("")) {
			
			selectedSquare = 4;
	
		}
		
		// ���� �� ������� � ����������� ������� - �������� ���������
		
		if (selectedSquare == -1) {
			
			selectedSquare = getRandomSquare();
			
		}
		
		squares[selectedSquare].setLabel("O");
		
	}
	
	/**  ���� ����� ��������� ������ �������, ��� � ���������, 
	 * ����� ������, ���� �� � ��� ��� ������ � ����������� ��������� � ������ �������
	 * � �������� ���������� ������ ���������� �, ���������� �
	 * ���������� ����� ���������� ��������� ������ ��� -1, ���� �� �������
	 * ��� ������ � ����������� ���������
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
			
			// ��������, ���� �� � ���� 1 ��� ���������� ������ � ���� ������
			
		if (weight[0] + weight[1] + weight[2] == twoWeight) {
				if (weight[0]== 0) {
					return 0;
				} else if (weight[1]== 0) {
					return 1;
				} else if (weight[2]==0) {
					return 2;
				}
			}
				
			// ��������, ���� �� � ���� 2 ��� ���������� ������ � ���� ������
				
		if (weight[3] + weight[4] + weight[5]==twoWeight) {
				if (weight[3]==0) {
					return 3;
				} else if (weight[4]==0) {
					return 4;
				} else if (weight[5]==0) {
					return 5;
				}
			}
				
			// ��������, ���� �� � ���� 3 ��� ���������� ������ � ���� ������
				
		if (weight[6] + weight[7] + weight[8]==twoWeight) {
				if (weight[6]==0) {
					return 6;
				} else if (weight[7]==0) {
					return 7;
				}else if (weight[8]==0) {
					return 8;
				}
			}
				
			// ��������, ���� �� � ������� 1 ��� ���������� ������ � ���� ������
				
		if (weight[0] + weight[3] + weight[6]==twoWeight) {
				if (weight[0]==0) {
					return 0;
				} else if (weight[3]==0) {
					return 3;
				}else if (weight[6]==0) {
					return 6;
				}
			}
				
			// ��������, ���� �� � ������� 2 ��� ���������� ������ � ���� ������
				
		if (weight[1] + weight[4] + weight[7]==twoWeight) {
				if (weight[1]==0) {
					return 1;
				} else if (weight[4]==0) {
					return 4;
				} else if (weight[7]==0) {
					return 7;
				}
			}
				
			// ��������� ������� ����� 3 �� ��� ���������� ������ � ���� ������
				
		if (weight[2] + weight[5] + weight[8]==twoWeight) {
				if (weight[2]==0) {
					return 2;
				} else if (weight[5]==0) {
					return 5;
				} else if (weight[8]==0) {
					return 8;
				}
			}
				
			// ��������� ��� ��������� �� ��� ���������� � ���� ������
				
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
			
			// ���� �� ������� ���� ���������� �������� ������
			
		return -1;
				
		} // ����� ������ findEmptySquare
	
		/** ���� ����� �����
		 * �������� �����
		 * ������ ������
		 */
		
		int getRandomSquare() {
			
			boolean gotEmptySquare = false;
			
			int SelectedSquare = -1;
			
			do {
				
				SelectedSquare = (int) (Math.random() * 9);
				
				if (squares[SelectedSquare].getLabel().equals("")) {
					
					gotEmptySquare = true; // ����� ��������� ����
				}
			} while (!gotEmptySquare);
			
			return SelectedSquare;
			
		} // ����� ������ getRandomSquare
		
		/** ���� ����� �������� ���������� �����
		 * ����������� ������ ������, ������ � ������ ������ ��� ���������
		 */
		
		void highlightWinner(int win1, int win2, int win3) {
			squares[win1].setBackground(Color.CYAN);
			squares[win2].setBackground(Color.CYAN);
			squares[win3].setBackground(Color.CYAN);
			
		}
		
		// ������ ������������ ������ � ��������� ������ "New Game"
		
		void endTheGame() {
			
			newGameButton.setEnabled(true);
			
			for(int i = 0; i<squares.length; i++) {
				squares[i].setEnabled(false);
			}
		}
		
		public static void main (String[] args) {
			TicTacToe ttt = new TicTacToe();
		}
		
} // ����� ������
				