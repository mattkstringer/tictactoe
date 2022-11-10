// Remaining issues:
// -Game accepts Yriuwcnsj or Yh or Y1 as a Y entry for Y/N queries
// -Game crashes if inputting a letter instead of a number for xTurn or oTurn
// -Keyboards are not closed in xTurn or oTurn but VSCode seems to want them to be. If I add keyboard.close(), game will crash on 2nd player's turn

import java.util.Scanner;
public class Tictactoe {
    public static void main(String[] args) {
        // Set starting table state, gameActive state variable
        // gameActive = 0 means game is over, 1 means game is active, 2 is the default mode before the game starts
        int[] tableState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int gameActive = 2;
        String response1;
        String response2;
        Scanner keyboard = new Scanner(System.in);

        // Ask user to play, looping accounts for failed inputs
        while (gameActive == 2) {
            System.out.print("Would you like to play a game of Tictactoe? Y/N: ");
            response1 = keyboard.nextLine();
            if (response1.charAt(0) == 'Y') {
                gameActive = 1;

                // Print demo game state to show what number a player should enter to claim an unclaimed space
                System.out.println("Note the number value of each spot. Claim a spot by entering the corresponding number");
                System.out.printf("%s\n", "_________________");
                System.out.printf("%s\n", "     |     |");
                System.out.printf("%s\n", "  1  |  2  |  3");
                System.out.printf("%s\n", "_____|_____|_____");
            
                System.out.printf("%s\n", "     |     |");
                System.out.printf("%s\n", "  4  |  5  |  6");
                System.out.printf("%s\n", "_____|_____|_____");
            
                System.out.printf("%s\n", "     |     |");
                System.out.printf("%s\n", "  7  |  8  |  9");
                System.out.printf("%s\n", "_____|_____|_____");
                System.out.println();

            } else if (response1.charAt(0) == 'N') {
                gameActive = 0;
            } else {
                System.out.println();
                System.out.println("Please enter Y or N and nothing else");
            }
        }

        // Enter game in a while loop, checking for gameActive state. This allow for repeat games
        while (gameActive == 1) {
            // xTurn method
            tableState = xTurn(tableState);

            // Check win condition method - if wincond = true, reset array. Draw check included
            // If array is reset need to do a looping gameActive check with the user
            tableState = checkWinCond(tableState);
            int a = 0;
            for (int b=0; b < 9; b++) {
                if (tableState[b] == 0) {
                    a++;
                }
            }

            if (a == 9) {
                int winCondVar = 0;
                while (winCondVar == 0) {
                    System.out.print("Play again? Y/N: ");
                    response2 = keyboard.nextLine();
                    if (response2.charAt(0) == 'Y') {
                        gameActive = 1;
                        winCondVar++;
                    } else if (response2.charAt(0) == 'N') {
                        gameActive = 0;
                        winCondVar++;
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Please enter Y or N and nothing else");
                    }
                }
            }

            // Print game state
            printGameState(tableState);

            // oTurn method
            tableState = oTurn(tableState);

            // Check win condition method - if wincond = true, reset array. Draw check included
            // If array is reset need to do a looping gameActive check with the user
            tableState = checkWinCond(tableState);
            int c = 0;
            for (int d=0; d < 9; d++) {
                if (tableState[d] == 0) {
                    c++;
                }
            }

            if (c == 9) {
                int winCondVar = 0;
                while (winCondVar == 0) {
                    System.out.print("Play again? Y/N: ");
                    response2 = keyboard.nextLine();
                    if (response2.charAt(0) == 'Y') {
                        gameActive = 1;
                        winCondVar++;
                    } else if (response2.charAt(0) == 'N') {
                        gameActive = 0;
                        winCondVar++;
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Please enter Y or N and nothing else");
                    }
                }
            }

            // Print game state
            printGameState(tableState);
        }
        System.out.println("Game end!");
        keyboard.close();
    }

    // printGameState method that takes in array and prints out the gamestate in between turns or at the end of the game
    public static void printGameState(int inputArray[]) {
        System.out.printf("%s\n", "_________________");
        System.out.printf("%s\n", "     |     |");
        System.out.printf("%s%s%s%s%s%s\n", "  ", printXOrO(inputArray[0]), "  |  ", printXOrO(inputArray[1]), "  |  ", printXOrO(inputArray[2]));
        System.out.printf("%s\n", "_____|_____|_____");
    
        System.out.printf("%s\n", "     |     |");
        System.out.printf("%s%s%s%s%s%s\n", "  ", printXOrO(inputArray[3]), "  |  ", printXOrO(inputArray[4]), "  |  ", printXOrO(inputArray[5]));
        System.out.printf("%s\n", "_____|_____|_____");
    
        System.out.printf("%s\n", "     |     |");
        System.out.printf("%s%s%s%s%s%s\n", "  ", printXOrO(inputArray[6]), "  |  ", printXOrO(inputArray[7]), "  |  ", printXOrO(inputArray[8]));
        System.out.printf("%s\n", "_____|_____|_____");
        System.out.println();
        }

    // printXOrO method to help the printGameState method
    public static String printXOrO(int inputInt) {
        if (inputInt == -1) {
            return "X";
        } else if (inputInt == 0) {
            return " ";
        } else {
            return "O";
        }
    }

    // xTurn method receiving an array and returning it modified. Checks for filled spaces
    public static int[] xTurn(int[] inputArray) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Player X take your turn! Please choose an empty space: ");
        int xResponse = keyboard.nextInt();

        while (1 > xResponse || 9 < xResponse || inputArray[(xResponse-1)] != 0) {
            System.out.println();
            if (1 > xResponse || 9 < xResponse) {
                System.out.print("Error, please enter a value between 1 and 9: ");
            } else {
                System.out.print("Error, please enter a value that has not already been claimed: ");
            }
            xResponse = keyboard.nextInt();
        }

        inputArray[(xResponse-1)] = -1;
        return inputArray;
    }

    // oTurn method receiving an array and returning it modified. Checks for filled spaces
    public static int[] oTurn(int[] inputArray) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Player O take your turn! Please choose an empty space: ");
        int oResponse = keyboard.nextInt();

        while (1 > oResponse || 9 < oResponse || inputArray[(oResponse-1)] != 0) {
            System.out.println();
            if (1 > oResponse || 9 < oResponse) {
                System.out.print("Error, please enter a value between 1 and 9: ");
            } else {
                System.out.print("Error, please enter a value that has not already been claimed: ");
            }
            oResponse = keyboard.nextInt();
        }

        inputArray[(oResponse-1)] = 1;
        return inputArray;
    }

    // checkWinCond method to check if game is won, print out who won, and resets array if true. Also checks for draw and resets array if true
    // Checks 3 rows, 3 columns, and 2 diagonals
    public static int[] checkWinCond(int[] inputArray) {
        //x win check
        if ((inputArray[0] + inputArray[1] + inputArray[2]) == -3 ||
        (inputArray[3] + inputArray[4] + inputArray[5]) == -3 ||
        (inputArray[6] + inputArray[7] + inputArray[8]) == -3 ||
        (inputArray[0] + inputArray[3] + inputArray[6]) == -3 ||
        (inputArray[1] + inputArray[4] + inputArray[7]) == -3 ||
        (inputArray[2] + inputArray[5] + inputArray[8]) == -3 ||
        (inputArray[0] + inputArray[4] + inputArray[8]) == -3 ||
        (inputArray[2] + inputArray[4] + inputArray[6]) == -3) {
            printGameState(inputArray);
            System.out.println("X wins!");
            for (int a=0; a < 9; a++) {
                inputArray[a] = 0;
            }
        }

        // O win check
        if ((inputArray[0] + inputArray[1] + inputArray[2]) == 3 ||
        (inputArray[3] + inputArray[4] + inputArray[5]) == 3 ||
        (inputArray[6] + inputArray[7] + inputArray[8]) == 3 ||
        (inputArray[0] + inputArray[3] + inputArray[6]) == 3 ||
        (inputArray[1] + inputArray[4] + inputArray[7]) == 3 ||
        (inputArray[2] + inputArray[5] + inputArray[8]) == 3 ||
        (inputArray[0] + inputArray[4] + inputArray[8]) == 3 ||
        (inputArray[2] + inputArray[4] + inputArray[6]) == 3) {
            printGameState(inputArray);
            System.out.println("O wins!");
            for (int b=0; b < 9; b++) {
                inputArray[b] = 0;
            }
        }

        // Draw check
        int c = 0;
        for (int d=0; d < 9; d++) {
            if (inputArray[d] == 0) {
                c++; // if array is filled, c will be 0. If any of the previous win conditions happened, the array has been reset and c will be 9
            }
        }
        if (c == 0) {
            printGameState(inputArray);
            System.out.println("Draw!");
            for (int e=0; e < 9; e++) {
                inputArray[e] = 0;
            }
        }
        return inputArray;
    }
}