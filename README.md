# Ghost-Game

This is Ghost Word game implemented in Java.

Requirements:
1) Java 8 needed to run this code as implementation contains java 8 code few places.
2) Word List or Dictionary provided in built in the code.

DataStructure used to store all the words from the List and search in optimal time: TRIE TREE 

Search a key / word in O(L) time - where L is length of key / word

General approch for computer to guess next Letter:
1) First check/ get odd length words and use that
2) if there is no odd length number then use even length longest word.
3) if next letter makes a word then bluff the next letter.
