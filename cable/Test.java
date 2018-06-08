
/**
 * Write a description of Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Test {
    public void hello(String s) {
        int l = s.length();
        int pos = s.indexOf("\n");
        System.out.println(l);
        System.out.println(pos);
        
    }
    
    
    public void test() {
        // FileResource fr = new FileResource();
        // String s = fr.asString();
        // hello(s);
        
        String s = "abc123";
        boolean boo =  s.contains("1") ||
        s.contains("2");
        System.out.println(boo);
    }
}
