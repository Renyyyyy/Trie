package org.example;

public class TrieNode {
    SimpleHashMap<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new SimpleHashMap<>();
        isEndOfWord = false;
    }
}