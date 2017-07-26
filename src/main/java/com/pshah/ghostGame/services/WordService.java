package com.pshah.ghostGame.services;

import java.util.List;
import java.util.Scanner;

import com.pshah.ghostGame.models.GhostGameModel;
import com.pshah.ghostGame.services.helpers.WordServiceHelper;

public class WordService {

  private WordServiceHelper helper;
  private static final int MIN_WORD_LENGTH = 4;

  public WordService(GhostGameModel model) {
    helper = new WordServiceHelper(model);
  }
  
  public void createDictionary(Scanner input) {
    int count = 0;
    while (input.hasNextLine()) {
      String word = input.nextLine().trim().replaceAll("[^A-Za-z]+", "");
      if (word.length() >= MIN_WORD_LENGTH) {
        count++;
        helper.addWord(word.toLowerCase());
      }
    }
    System.out.println("Dictionary is loaded with " + count + " Words.");
  }

  public String getNextPredictiveLetter(String key) {
    List<String> wordList = getListOfWordsWithPrefix(key);

    // First Get the word with odd length
    String nextLetter = helper.getNextLetterForOddLengthWord(key, wordList);

    // Else Get next char for even length words if there is no odd length word.
    if (nextLetter.isEmpty()) {
      nextLetter = helper.getNextLetterForEvenLengthWord(key, wordList);
    }
    return nextLetter;
  }

  public List<String> getListOfWordsWithPrefix(String key) {
    return helper.getAllWordsByKey(key);
  }

  public boolean isWord(String key) {
    return key.length() > MIN_WORD_LENGTH && helper.searchWord(key);
  }
}
