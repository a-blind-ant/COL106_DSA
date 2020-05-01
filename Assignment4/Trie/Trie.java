package Trie;
import java.util.ArrayList;
import java.util.List;


public class Trie<T> implements TrieInterface {

    TrieNode<T> root = new TrieNode();
    List<Character> l = new ArrayList<Character>(); 
    @Override
    public boolean delete(String word) {
        TrieNode<T> temp = root;
        //boolean ans = true;
        for(int i=0; i<word.length(); i++)
        {
            if(temp.a[(int)word.charAt(i)-32] == null)
            {
        //        System.out.println("ERROR DELETING");      
                return false;
            }
            else
            {
                temp = temp.a[(int)word.charAt(i)-32];
            }        
        }
        if(temp.end_of_word)
        {
            temp.end_of_word = false;
        //    System.out.println("DELETED");
            int counter = word.length()-1;
            while(temp!=root)
            {
                for(int i=0; i<95; i++)
                {
                    if(temp.a[i] != null)
                    {
                        return true;
                    }                    
                }          
                temp = temp.parent;
                temp.a[(int)word.charAt(counter)-32] = null;
                counter--;
            }
            return true;
        }
        else
        {
        //    System.out.println("ERROR DELETING");
            return false;
        }
    }

    @Override
    public TrieNode search(String word) {
        TrieNode<T> temp = root;
        for(int i=0; i<word.length(); i++)
        {
            if(temp.a[(int)word.charAt(i)-32] == null)
            {
                return null;
            }
            else
            {
                temp = temp.a[(int)word.charAt(i)-32];
            }  
        }
        if(temp.end_of_word)
        {
            return temp;
        }
        else
        {
            return null;
        }
    }

    @Override
    public TrieNode startsWith(String prefix) {
        TrieNode<T> temp = root;
        for(int i=0; i<prefix.length(); i++)
        {
            if(temp.a[(int)prefix.charAt(i)-32] == null)
            {
                return null;
            }
            else
            {
                temp = temp.a[(int)prefix.charAt(i)-32];
            }    
        }
        return temp;    
    }

    @Override
    public void printTrie(TrieNode trieNode) {
        TrieNode<T> temp = trieNode;
        if(temp.end_of_word)
        {
            System.out.println(temp.value);
        }
        for(int i=0;i<95;i++)
        {
            if(temp.a[i] != null)
            {
                printTrie(temp.a[i]);
            }
        }        
    }

    @Override
    public boolean insert(String word, Object value) {
        TrieNode<T> temp = root;
        for(int i = 0; i<word.length(); i++)
        {                                                                   
            if(temp.a[(int)word.charAt(i)-32] != null)
            {
                temp = temp.a[(int)word.charAt(i)-32];                
            }
            else
            {
                temp.a[(int)word.charAt(i)-32] = new TrieNode();
                temp.a[(int)word.charAt(i)-32].ch = word.charAt(i);
                temp.a[(int)word.charAt(i)-32].parent = temp;
                temp.a[(int)word.charAt(i)-32].tillHere = temp.tillHere + word.charAt(i);
                temp = temp.a[(int)word.charAt(i)-32];          
            }
        }
        if(temp.end_of_word)
        {
            return false;
        }
        else
        {
            temp.end_of_word = true;
            temp.value = (T)value;
            return true;
        }
    }

    public void dfs(TrieNode temp, int level)   
    {
        if(temp.tillHere.length() == level)    
            l.add(temp.ch);
        //boolean leaf = true;
        for(int i = 0; i<95; i++)
        {
           if(temp.a[i]!=null)
           {
                dfs(temp.a[i], level);         
           }    
        }     
    }

    @Override
    public void printLevel(int level) {
        System.out.print("Level " + level + ": ");
        l.clear();
        dfs(root, level);    
        //Collections.sort(l);
        l.sort(null);
        for(int i=0; i<l.size(); i++)
        {
            if(!l.get(i).equals(' ') && i!=l.size()-1)
                {System.out.print(l.get(i)); System.out.print(',');}
            else if(!l.get(i).equals(' '))
                System.out.print(l.get(i));
        }
        System.out.println();
    }

    @Override
    public void print() {
        System.out.println("-------------");
        System.out.println("Printing Trie");
        int i = 1;
        //System.out.println("hi");
        l.add('c');
        //System.out.println(l.size());
        while(l.size()!=0)
        {
        //    System.out.println("here");
            printLevel(i);
            i++;            
        }
        System.out.println("-------------");
    }
}