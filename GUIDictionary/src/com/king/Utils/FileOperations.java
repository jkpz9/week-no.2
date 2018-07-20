package com.king.Utils;

import com.king.Model.SearchModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileOperations {
    
    public static void WriteLine(String filePath, String line)
	{	
            try 
            {
                File file = new File(filePath);
                try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"))) 
                {
                     out.append(line);
                     out.write(System.getProperty("line.separator"));
                    //out.flush();
                }

            }
            catch (UnsupportedEncodingException UnsuppEx)
            {
                UnsuppEx.printStackTrace();
            }
            catch (IOException IOEx)
            {
               IOEx.printStackTrace();
            }
	}
    
	public static <T> void writeList(String filePath, List<T> arrayL, Class<T> clazz)
	{
            
            FileWriter rd = null; // not support UTF-8
            BufferedWriter buffer = null;
            try {
                     rd = new FileWriter(new File(filePath));
                     buffer = new BufferedWriter(rd);
                     Iterator<T> it = arrayL.iterator();
                    while(it.hasNext()){
                            T word = (T)it.next();
                            buffer.write(word.toString());
                            if (it.hasNext()) buffer.newLine(); 
                    }
            } catch(IOException ex){
            } finally {
                    try {
                            if (buffer != null) buffer.close();
                            if (rd != null) rd.close();
                    } catch(IOException ex2) {
                    }

            }
	}
        
        public static ArrayList<String> readLineByLineAndBuildList(String filePath) throws IOException
	{	
             ArrayList<String> words = new ArrayList<>();
        try {
	     File fileDir = new File(filePath);
			
                try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"))) 
                {
                    String str;

                    while ((str = in.readLine()) != null) 
                    {
                        words.add(str);
                    }
                }
	  } 
        catch (UnsupportedEncodingException e) 
        {
                    System.out.println(e.getMessage());
        } 
        catch (IOException e) 
        {
                    System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
                    System.out.println(e.getMessage());
        }

        return words;
	}
        public static void saveObj(String desPath, SearchModel obj) throws FileNotFoundException, IOException
        {
            ObjectOutputStream oos;
            File f = new File(desPath);
            // If file existed, use the output stream without header
            if(f.exists() && !f.isDirectory()) { 
                oos = new OOStream(new FileOutputStream(desPath, true));
            } else {
            // If file not existed, use the output stream with header
             oos = new ObjectOutputStream(new FileOutputStream(desPath, true));
            }

            oos.writeObject(obj);
            oos.flush();
            oos.close();
        }
        // ReWrite Favorite File content
        public static void remakeFromRemove(String file, String removeLine) throws IOException 
        {
                    
            File fileDir = new File(file);
            
            // check if it's a file
            if (!fileDir.isFile()) 
            {
                System.out.println("This is not a file");
                return;
            }

            // Read current Favorite file
             BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
            
            // create new tmp file to save save updating favorite
            File tempFile = new File(fileDir.getAbsolutePath() + ".tmp");
            
             // Write to tmp file
             Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile, true), "UTF8"));

                 String line;

                while ((line = in.readLine()) != null) 
                {
                   if (!line.trim().equalsIgnoreCase(removeLine)) {
                         out.append(line);
                        out.write(System.getProperty("line.separator"));
                       //out.flush();
                    }
                }	
                
        out.close();
        in.close();
        // try to delete old favorite file
        if (!fileDir.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        
        // try to rename new favorite file
        if (!tempFile.renameTo(fileDir)) {
             System.out.println("Could not rename file");
        }
   

    }
}
