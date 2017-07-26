package com.pshah.ghostGame.services;

import java.util.List;
import java.util.Scanner;

import com.pshah.ghostGame.models.GhostGameModel;

public class GameService {
  private WordService wordService;
  private GhostGameModel model;
  
  public GameService(GhostGameModel model){
    this.model = model;
    wordService = new WordService(this.model);
  }
  
  public void createDictionary(Scanner input) {
    wordService.createDictionary(input);    
  }
  
  //Is player is bluffing? OR Is player is bluffing since computer already bluffed the word?
  public boolean checkIfPlayerBluffing(String word){
    return wordService.getListOfWordsWithPrefix(word).isEmpty() || model.isComputerBluffing();
  }
  
  //Is Player's Input is word??
  public boolean checkIfPlayerMadeWord(String word) {
    return wordService.isWord(word);
  }
  
  public void setWinForComputer(String word) {
    model.setComputerWin(true);
    System.out.println("\n Sorry !!! "+ word.toString() + " is a Word");    
  }
  
  public String getUserInput(Scanner input) {
    System.out.print("\nYour Letter: ");
    //Check input is only alphabetic
    while (!input.hasNext("[A-Za-z]+")) {
      System.out.println("Wrong Letter, Please enter only Alphabetic Letter: ");
      input.nextLine();
  }
    return input.nextLine().toLowerCase();
  }
  
  public void challengeToPlayer(Scanner input) {
    System.out.println("\n You are CHALLENGED for this word. \n Please enter your word. If you are bluffing then don't enter anything and hit enter.");
    boolean computerWin = input.nextLine().isEmpty() ? true : false;
    model.setComputerWin(computerWin);
  }
  
  public void printNextLetterForComputer(StringBuilder word) {
    String cmpLetter = "";
    cmpLetter = wordService.getNextPredictiveLetter(word.toString());
    word.append(cmpLetter);
    System.out.println("Computer Letter: " + cmpLetter);
    System.out.println("\nCurrent Word:  " + word.toString());
  }
  
  public void determineResult() {
    if (model.isComputerWin()) {
      System.out.println("\n Computer Win!!");
    } else {
      System.out.println("\n Player Win!!");
    }
  }
  
  public void playerChallenged(String word) {
     System.out.println("\n You have challenged the computer");
     if(!model.isComputerBluffing()){
       model.setComputerWin(true);
       showChallengedWord(word);
     }
  }

  private void showChallengedWord(String word) {
    List<String> wordList = wordService.getListOfWordsWithPrefix(word);
    if(!wordList.isEmpty()){
      System.out.println("\n Computer's Challenged Word : " + wordList.get(0));
    } else {
      model.setComputerWin(false);
    }
  }
}
