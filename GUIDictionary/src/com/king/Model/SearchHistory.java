package com.king.Model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class holds Search histories
public class SearchHistory {
    private final List<SearchModel> history;
    
    public List<SearchModel> getHistory()
    {
        return this.history;
    }
    public SearchHistory() {
        history = new ArrayList<>();
    }
    
    public void addHistory(SearchModel search) {
        history.add(search);
    }
    
    // build map contains statictis
    public Map<String, Integer> SearchStatictis(Date d1, Date d2)
    {
        Map<String, Integer> statics = new HashMap<>();
        history.stream().filter((s) -> ((d1.compareTo(s.getSearchAt()) <= 0) )).filter((s) -> (!(s.getSearchAt().compareTo(d2) > 0))).forEachOrdered((SearchModel s) -> {
            Integer c = statics.get(s.getWord());
            if (c == null) c = 0;
            c++;
            statics.put(s.getWord(), c);
        });
        return statics;
    }
    
    public void saveToFile(String outFilePath) throws IOException
    {
        File f = new File(outFilePath);
        f.createNewFile(); // itself atomically checks the existence of the file
        ObjectOutputStream outStream;
        outStream = new ObjectOutputStream(new FileOutputStream(f, true));
        
        Iterator<SearchModel> it = history.iterator();
        while(it.hasNext()) {
            SearchModel s = it.next();
            outStream.writeObject(s);
        }
        outStream.close();
    }
    
    public void readFromFile(String fileName)
    {
        FileInputStream inFile=null;
        ObjectInputStream inStream = null;

        try {
                inFile = new FileInputStream(fileName);
                inStream = new ObjectInputStream(inFile);
                for(;;)
                {
                        SearchModel s = (SearchModel)inStream.readObject();
                        history.add(s);
                }

        }

        catch(FileNotFoundException | ClassNotFoundException _404ex) 
        {
                System.out.println(_404ex.getMessage());
        } 

        catch(EOFException EOFex)
        {
           System.out.println(EOFex.getMessage());
        } 

        catch(IOException ioex)
        {
        } 
        finally {
                if (inFile != null) {
                        try {
                                inFile.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block

                        }
                }
                if (inStream != null) {
                        try {
                                inStream.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block

                        }
                }
        }
    }
    
    
}
