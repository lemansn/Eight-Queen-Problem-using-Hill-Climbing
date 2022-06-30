/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nqueens;

/**
 *
 * @author leman
 */
import java.util.Random;



public class HillClimbingRandomRestart {
    
    
    private static int n ;
//    private static int stepsClimbedAfterLastRestart = 0;
    private static float stepsClimbed =0;
    private static float heuristic = 0;
    private static float randomRestarts = 0;

    //Method to create a new random board
    public static NQueen[] generateBoard() {
        NQueen[] startBoard = new NQueen[n];
        Random rndm = new Random();
        for(int i=0; i<n; i++){
            startBoard[i] = new NQueen(rndm.nextInt(n), i);
        }
        return startBoard;
    }

    

    // Method to find Heuristics of a state
    public static int findHeuristic (NQueen[] state) {
        int heuristic = 0;
        for (int i = 0; i< state.length; i++) {
            for (int j=i+1; j<state.length; j++ ) {
                if (state[i].ifConflict(state[j])) {
                    heuristic++;
                }
            }
        }
//        System.out.println("heuristic1 " +heuristic);
        return heuristic;
    }

    // Method to get the next board with lower heuristic
    public static NQueen[] nextBoard (NQueen[] presentBoard) {
        NQueen[] nextBoard = new NQueen[n];
        NQueen[] tmpBoard = new NQueen[n];
        int presentHeuristic = findHeuristic(presentBoard);
        int bestHeuristic = presentHeuristic;
        int tempH;

        for (int i=0; i<n; i++) {
            //  Copy present board as best board and temp board
            nextBoard[i] = new NQueen(presentBoard[i].getRow(), presentBoard[i].getColumn());
            tmpBoard[i] = nextBoard[i];
        }
        //  Iterate each column
        for (int i=0; i<n; i++) {
            if (i>0)
                tmpBoard[i-1] = new NQueen (presentBoard[i-1].getRow(), presentBoard[i-1].getColumn());
            tmpBoard[i] = new NQueen (0, tmpBoard[i].getColumn());
            //  Iterate each row
            for (int j=0; j<n; j++) {
                //Get the heuristic
                tempH = findHeuristic(tmpBoard);
                //Check if temp board better than best board
                if (tempH < bestHeuristic) {
                    bestHeuristic = tempH;
                    //  Copy the temp board as best board
                    for (int k=0; k<n; k++) {
                        nextBoard[k] = new NQueen(tmpBoard[k].getRow(), tmpBoard[k].getColumn());
                    }
                }
                //Move the queen
                if (tmpBoard[i].getRow()!=n-1)
                    tmpBoard[i].move();
            }
        }

        //When stuck at the local optimum start from a random position
        if (bestHeuristic == presentHeuristic) {
            randomRestarts++;
            nextBoard = generateBoard();
            heuristic = findHeuristic(nextBoard);
        } else
            heuristic = bestHeuristic;
        stepsClimbed++;
        return nextBoard;
    }

    
    
    public static void main(String[] args) {
        
        
float current_step=0;
float current_restarts=0;
float time =0;
float top_time =0;
        System.out.println("\n              Yer Değiştirme:               Random Restart:              İşlem Süresi:");

        for (int i = 0; i < 15; i++) {
        
        
  
      float presentHeuristic;

        n = 8;
        long startTime = System.nanoTime();



        NQueen[] presentBoard = generateBoard();

        presentHeuristic = findHeuristic(presentBoard);
        // test if the present board is the solution board

        
        while (presentHeuristic != 0) {

            presentBoard = nextBoard(presentBoard);
            presentHeuristic  = heuristic;
        }
        
            
        
      long stopTime = System.nanoTime();
      time = stopTime - startTime;
      
        

    
        System.out.printf("%29.0f  %27.0f   %25.0f%n", (stepsClimbed-current_step), (randomRestarts-current_restarts),time/1000 );
        
        top_time += time/1000;
        current_step = stepsClimbed;
        current_restarts = randomRestarts;
            
    }
        
    System.out.print("Ortalama:");
     System.out.printf("%20.2f  %27.2f   %25.2f%n", (stepsClimbed/15), (randomRestarts/15),(top_time)/15 );
    
    }}