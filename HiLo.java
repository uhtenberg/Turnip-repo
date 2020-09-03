import java.util.Scanner;


public class HiLo {

   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      String playAgain = "";
      int numberOfTries = 0;
      do {
         // Генерация случайного числа, угадываемого пользователем
         int theNumber = (int) (Math.random() * 100 + 1);
         //System.out.println(theNumber);
         int guess = 0;
         while (guess != theNumber) {
            System.out.println("Guess a number between 1 and 100:");
            guess = scan.nextInt();
            if (guess < theNumber) {
               System.out.println(guess + " is too low. Try again.");
               numberOfTries++;
            }
            else if (guess > theNumber) {
               System.out.println(guess + " is too high. Try again.");
               numberOfTries++;
            }
            else {
               System.out.println(guess + " is correct. You win!");
               numberOfTries++;
               System.out.println("It only took you " + numberOfTries + " tries! Good work!");
            }

         }
         System.out.println("Would you like to play again? (y/n)");
         playAgain = scan.next();
      } while (playAgain.equalsIgnoreCase("y"));
      System.out.println("Thank you for playing and Goodbye!");
      scan.close();
   }

}
