package com.king.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringFormating {
    public  static List<String> splitByCharacter(String src, String candidate)
    {
        return new ArrayList<String>(Arrays.asList(src.split(candidate)));
    }
    
    public static String formating(List<String> arr) 
    {
        //String newline = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        arr.stream().map((s) -> {
            String tmp="";
            if (0 <= "@".indexOf(s.charAt(0)))
            {
                tmp = ("<font color=red>" + s) + ("</font>"+"<br/>");
            }
            else if (0 <= "-".indexOf(s.charAt(0)))
            {
                tmp = ("<font color=green>" + s) +("</font>"+"<br/>");
            }
            else if (0 <= "*".indexOf(s.charAt(0))) {
                tmp = ("<font color=blue>" + s) +("</font>"+"<br/>");
            }
            else if (0 <= "=".indexOf(s.charAt(0)))
            {
                tmp = ("<font color=red>" + s) +("</font>"+"<br/>");
            }
            return tmp;
        }).forEachOrdered((tmp) -> {
            sb.append(tmp);
        });
        return sb.toString();
    }
}
