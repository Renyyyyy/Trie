package org.example;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.computeIfAbsent(c, k -> new TrieNode());//если у текущего узла нет ребёнка то создаём новый узел и добавляем его к детям
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    public void delete(String word) {
        deleteNode(root, word, 0);
    }

    private boolean deleteNode(TrieNode node, String word, int depth) {
        if (depth == word.length()) {
            if (!node.isEndOfWord) {
                return false;
            }
            node.isEndOfWord = false;
            return node.children.isEmpty();
        }

        char c = word.charAt(depth);
        TrieNode child = node.children.get(c);

        if (child == null) {
            return false;
        }

        boolean shouldDeleteNode = deleteNode(child, word, depth + 1);

        if (shouldDeleteNode) {
            node.children.remove(c);
            return node.children.isEmpty();
        }

        return false;
    }

    private TrieNode searchNode(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    public int countWords() {
        return countWords(root);
    }

    private int countWords(TrieNode node) {
        int count = 0;
        if (node.isEndOfWord) {
            count++;
        }
        for (TrieNode child : node.children.values()) {
            count += countWords(child);
        }
        return count;
    }
}
