package fousfous;
import fousfous.PlateauFousfous;
import fousfous.MoveHistory;

import java.util.ArrayList; 
import java.util.Arrays;  

class Main {
   public static void main (String[] args)
   {
        //New game
        PlateauFousfous game = new PlateauFousfous();
        MoveHistory.setGame(game);

        //Printing game
        System.out.println(game);

        //Getting possible move list for player white
        String [] moveList = game.mouvementPossibles("white");

        //Printing possible move list
        for(int i=0;i<moveList.length;i++)
            System.out.println(moveList[i]);

        //Playing
        game.play(moveList[1],"white");
        System.out.println(game);

        //Printing play history
        System.out.println(MoveHistory.getString());  

        //Popping last move 
        MoveHistory.pop();
        System.out.println(game);            
   }
}