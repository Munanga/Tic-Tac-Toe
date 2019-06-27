

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        game();



    }

    static void game(){

        Board board = new Board();

        displayMenu();
        board.displayHelp();
        System.out.println("---------- Game has Started ----------");
        board.displayBoard();


        // main program
        int playerNumber = 1;

        while(true){

            while (true){

                int position = getPlayerInput(playerNumber,board);
                board.subStractNumberOfSlots();

                if(board.getEmptySlots() == 0){
                    board.displayBoard();
                    System.out.println("Game Over it's a draw!");
                    break;
                }

                board.updateBoard(playerNumber,position);

                if(position == 0){
                    boolean check = postionCheck("zero","zero","left",board,playerNumber);

                    if(check){
                        break;
                    }
                }else if(position == 1){
                    boolean check = postionCheck("zero","one",board,playerNumber);

                    if(check){
                        break;
                    }
                }else if(position == 2){
                    boolean check = postionCheck("zero","two","right",board,playerNumber);

                    if(check){
                        break;
                    }
                }else if(position == 3){
                    boolean check = postionCheck("one","zero",board,playerNumber);

                    if(check){
                        break;
                    }

                }else if(position == 4){
                    boolean check = postionCheck("one","one",board,playerNumber);

                    if(check) {
                        break;
                    }
                }else if(position == 5){
                    boolean check = postionCheck("one","two",board,playerNumber);

                    if(check){
                        break;
                    }

                }else if(position == 6){
                    boolean check = postionCheck("two","zero","right",board,playerNumber);

                    if(check){
                        break;
                    }

                }else if(position == 7){
                    boolean check = postionCheck("two","one",board,playerNumber);

                    if(check){
                        break;
                    }

                }else if(position == 8){
                    boolean check = postionCheck("two","two","left",board,playerNumber);

                    if(check){
                        break;
                    }

                }

                playerNumber = changePlayerNumber(playerNumber);

            }

            board.updateGame();
            board.displayStatus();

            System.out.println("Player again? (yes/no)");
            Scanner input = new Scanner(System.in);
            String playAgain = input.nextLine();

            playAgain = playAgain.toLowerCase();

            if(playAgain.equals("no")){
                System.exit(0);
            }else {
                board.clearBoard();
                displayMenu();
                board.displayHelp();
                System.out.println("---------- Game has Started ----------");
                board.displayBoard();
                playerNumber = 1;
            }


        }

    }

    static void displayMenu(){
        System.out.println("+------------------------+");
        System.out.println("| Welcome to TIC TAC TOE |");
        System.out.println("+------------------------+");
        System.out.println("Enter a number that corresponds\n" +
                "to a point on the board as shown in example below.\n");
    }

    static boolean postionCheck(String positionRow, String positionColumn,Board board, int playerNumber){

        boolean row = board.rowWinCheck(positionRow);
        boolean col = board.columnWinCheck(positionColumn);

        boolean result = oddPostionCheckWin(row,col);

        if(result){
            board.displayBoard();
            board.updateWins(playerNumber-1);
            System.out.println("Game Over Player" + playerNumber + " wins!");
            return true;
        }
        board.displayBoard();
        return false;
    }


    static boolean postionCheck(String positionRow, String positionColumn, String dia,Board board, int playerNumber){
        boolean row = board.rowWinCheck(positionRow);
        boolean col = board.columnWinCheck(positionColumn);
        boolean diagonal = board.diagonalWinCheck(dia);

        boolean result = evenPostionCheckWin(row,col,diagonal);

        if(result){
            board.displayBoard();
            board.updateWins(playerNumber-1);
            System.out.println("Game Over Player" + playerNumber + " wins!");
            return true;
        }

        board.displayBoard();
        return false;

    }


    static int changePlayerNumber(int num){
        if(num == 1){
            return 2;
        }
        return 1;
    }


    static boolean oddPostionCheckWin(boolean row,boolean column){
        if(row || column){
            return true;
        }
        return false;
    }


    static boolean evenPostionCheckWin(boolean row, boolean column, boolean diagonal){
        if(row || column || diagonal){
            return true;
        }
        return false;
    }


    static public boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        if (i >= minValueInclusive && i <= maxValueInclusive)
            return true;
        else
            return false;
    }


    static public int getPlayerInput(int playerOneOrTwo, Board board){
        if(playerOneOrTwo != 1 && playerOneOrTwo != 2){
            throw new IllegalArgumentException("Enter only 0 or 1");
        }
        System.out.print("Player" + playerOneOrTwo +":");

        Scanner input = new Scanner(System.in);
        int enteredPosition = input.nextInt();

        while(!between(enteredPosition,0,8) || board.getSlot(enteredPosition) != '-'){
            System.out.println("Please reenter number from 0 to 8 and enter slot not taken");
            input = new Scanner(System.in);
            enteredPosition = input.nextInt();
        }

        return enteredPosition;
    }



}

