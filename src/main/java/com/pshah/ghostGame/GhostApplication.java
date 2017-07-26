package com.pshah.ghostGame;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.pshah.ghostGame.models.GhostGameModel;
import com.pshah.ghostGame.services.GameService;

public class GhostApplication {

  private GameService gameService;
  private Scanner input;
  private GhostGameModel model;
  private static final String CHALLENGE = "challenge";

  public GhostApplication() {
    model = new GhostGameModel();
    gameService = new GameService(model);
    try {
      //Read the file for the words
      input = new Scanner(new File("src/main/resources/words.txt"));
      gameService.createDictionary(input);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void playGhost() {
    input = new Scanner(System.in);
    String playAgain = "Y";

    // For Continuous Playing
    while ("Y".equalsIgnoreCase(playAgain)) {
      StringBuilder word = new StringBuilder();
      System.out.println("You can enter either single character or word "+ CHALLENGE + " to challenge the computer.");
      String userInput = gameService.getUserInput(input);

      //Play until play give challenge word
      while (!CHALLENGE.equalsIgnoreCase(userInput)) {
        word.append(userInput);

        //Check player is bluffing, then challenge the player
        if (gameService.checkIfPlayerBluffing(word.toString())) {
          gameService.challengeToPlayer(input);
          break;
        }

        //Check player created the word by putting last letter, then set the computer win
        if (gameService.checkIfPlayerMadeWord(word.toString())) {
          gameService.setWinForComputer(word.toString());
          break;
        }

        //get the optimal next letter for the computer 
        gameService.printNextLetterForComputer(word);
        //Get the use input
        userInput = gameService.getUserInput(input);
      }

      // If Player Challenged the Computer
      if (CHALLENGE.equalsIgnoreCase(userInput)) {
        gameService.playerChallenged(word.toString());
      }
      
      //This is an end of the game, now determine the results and declare it
      gameService.determineResult();

      System.out.println("\n Play it again?? Y or N");
      playAgain = gameService.getUserInput(input);
    }
    System.out.println("\n Thank you !!");

  }

  public static void main(String args[]) {
    GhostApplication game = new GhostApplication();
    System.out.println("\n **************Welcome to Ghost Word Game*****************\n");
    game.playGhost();
  }
}
