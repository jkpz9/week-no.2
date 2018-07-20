package com.king.Model;

import java.util.Collections;
import java.util.List;

// Class represents favorite list
public class FavoriteWord {
    private List<String> words;
    public FavoriteWord() {}
    public FavoriteWord(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
    // use built-in sort
    public void order_ACS() {
        Collections.sort(words);
    }
    
     // use built-in reverse sort 
     public void order_DES() {
        Collections.reverse(words);
    }

    
}
 