package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {

    char ch = '\0';
    String tillHere = "";
    TrieNode<T>[] a = new TrieNode[95];
    TrieNode<T> parent = null;
	T value = null;     
	boolean end_of_word = false;

    @Override
    public T getValue() {
        return value;
    }

}