package com.pshah.library.tries;

import java.util.ArrayList;
import java.util.List;

public class Trie {

  private Node root;

  public Trie() {
    this.root = new Node("");
  }

  //Insert in to the tree
  public void insert(String key) {

    Node tempNode = root;

    for (int i = 0; i < key.length(); ++i) {

      char c = key.charAt(i);
      if(c == '-'){
        continue;
      }
      int asciiIndex = c - 'a';

      if (tempNode.getChild(asciiIndex) == null) {
        Node node = new Node(String.valueOf(c));
        tempNode.setChild(asciiIndex, node);
        tempNode = node;
      } else {
        tempNode = tempNode.getChild(asciiIndex);
      }
    }

    tempNode.setLeaf(true);
  }

  // Search in the tree
  public boolean searchForWord(String key) {

    Node trieNode = root;
    boolean flag = false;

    for (int i = 0; i < key.length(); ++i) {

      char c = key.charAt(i);
      int asciiIndex = c - 'a';

      if (trieNode.getChild(asciiIndex) == null) {
        flag = false;
      } else {
        trieNode = trieNode.getChild(asciiIndex);
      }
    }
    
    if(trieNode.isLeaf()){
      flag = true;
    }
    
    return flag;
  }
  
  public List<String> allWordsWithPrefix(String prefix) {
    
    Node trieNode = root;
    List<String> allWords = new ArrayList<>();
    
    for(int i = 0;i<prefix.length();++i) {
      char c = prefix.charAt(i);
      int asciiIndex = c-'a';
      if(trieNode != null){
        trieNode = trieNode.getChild(asciiIndex);
      }
    }
    
    collect(trieNode, prefix, allWords);
    
    return allWords;
  }
  
  private void collect(Node node, String prefix, List<String> allWords) {
    
    if( node == null ) return;
    
    if( node.isLeaf() ){
      allWords.add(prefix);
    }
    
    for(Node childNode : node.getChildren()) {
      if( childNode == null ) continue;
      String childCharacter = childNode.getCharacter();
      collect(childNode, prefix+childCharacter, allWords);
    }
  }
}