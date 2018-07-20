package com.king.Model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

// Class represents Search model
public class SearchModel implements Serializable {
    private String word;
    private Date searchAt;

    public SearchModel(String word, Date searchAt) {
        this.word = word;
        this.searchAt = searchAt;
    }
    
    public static Comparator<SearchModel> OrderBySearch = (SearchModel m1, SearchModel m2) -> m1.getSearchAt().compareTo(m2.getSearchAt());

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getSearchAt() {
        return searchAt;
    }

    public void setSearchAt(Date searchAt) {
        this.searchAt = searchAt;
    }
    
    
}
