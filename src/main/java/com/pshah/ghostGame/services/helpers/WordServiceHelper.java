package com.pshah.ghostGame.services.helpers;

import java.util.List;

import com.pshah.ghostGame.models.GhostGameModel;
import com.pshah.library.tries.Trie;

public class WordServiceHelper {

  private GhostGameModel model;
  private Trie trie;

  public WordServiceHelper(GhostGameModel model) {
    this.model = model;
    this.trie = new Trie();
  }

  public void addWord(String word) {
    trie.insert(word);
  }

  public List<String> getAllWordsByKey(String key) {
    return trie.allWordsWithPrefix(key);
  }

  public String getNextLetterForOddLengthWord(String key, List<String> wordList) {
    String nextWord = wordList
                          .stream()
                          .filter(word -> ((word.length() % 2 != 0) && validNextCharacter(word, key)))
                          .findFirst().orElse("");
    
   return (nextWord.length() > 0) ? getNextCharacter(nextWord, key) : "";
  }

  public String getNextLetterForEvenLengthWord(String key, List<String> wordList) {
    String longestWord = wordList
                           .stream()
                           .sorted((word1, word2) -> word1.length() > word2.length() ? -1 : 1)
                           .findFirst().orElse("");
   
    boolean condition = !wordList.isEmpty() && longestWord.length() > 0 && validNextCharacter(longestWord, key);
    
    //If there is no word in the list, then bluff the next letter
    return condition ? getNextCharacter(longestWord, key) : bluffNextLetter();
  }

  // Check if by next character, word becomes a valid word.
  private boolean validNextCharacter(String word, String key) {
    String nextWord = key + getNextCharacter(word, key);
    return !searchWord(nextWord);
  }

  private String bluffNextLetter() {
    model.setComputerBluffing(true);
    return getRandomLetter();
  }

  public boolean searchWord(String key) {
    return trie.searchForWord(key);
  }

  // This will give you random alphabetic letter
  public String getRandomLetter() {
    char randomChar = (char) ('a' + Math.random() * ('z' - 'a' + 1));
    return String.valueOf(randomChar);
  }

  /* This will give you next letter after key in a word.
   * Example: if your word is "papaya" and key is "pap" then it'll return "a"
   */
  public String getNextCharacter(String word, String key) {
    int len = key.length();
    return word.substring(len, len + 1);
  }
}
