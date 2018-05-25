import edu.duke.*;
import java.io.*;

public class Cable1968{
    public void parser(String s) {
        int start = s.indexOf("—");
        int end = -1; // index after "."
        int mark; // index of next "—"
        String subTemp;
        String sub;
        String name;
        String note;
        
        while (start != -1) {
            note = "";
            
            // Town name †
            name = s.substring(end + 1, start);
            if (name.indexOf("\t") != -1) {
                System.out.print(name.substring(0, name.indexOf("\t")) + 
                name.substring(name.indexOf("\t") + 1, name.length()) + "\t");
            }
            else {
                System.out.print(name + "\t");
            }
            
            
            // Mark the next "—"
            mark = s.indexOf("—", start + 1);
            
            
            // Substring
            if (mark == -1) {
                end = s.length();
            }
            else {
                end = s.lastIndexOf("\n", mark);
            }
            sub = s.substring(start, end);

            
            // Copied
            if (s.substring(start + 1, start + 4).equals("See") ||
            s.substring(start + 2, start + 5).equals("See")) {
                System.out.print("1" + "\t");
                String seeTemp = s.substring(start + 1, end);
                if (seeTemp.indexOf("\n") != -1) {
                    System.out.print(seeTemp.substring(seeTemp.indexOf("\n") + 1, seeTemp.length()) + "\n");
                }
                else {
                    System.out.print(seeTemp + "\n");
                }
                start = s.indexOf("—", start + 1);
                continue;
            }
            else {
                System.out.print("0" + "\t" + "" + "\t");
            }

            
            // Population
            int populationIndex = sub.indexOf("Population");
            if (populationIndex != -1) {
                int popStartIndex = s.indexOf("Population", start) + 12;
                int popEndIndex = s.indexOf(".", popStartIndex);
                
                // N -> N.A
                // if (s.charAt(popEndIndex + 1) == 'A') {
                    // popEndIndex = s.indexOf(".", popEndIndex + 1);
                // }
                
                // NumberFix

                
                // including
                if (s.substring(popStartIndex, popEndIndex).indexOf("cluding") != -1) {
                    note += "Population " + s.substring(popStartIndex, s.indexOf(")", popStartIndex)) + " ";
                    popStartIndex = s.indexOf(":", popStartIndex) + 2;
                }
                
                System.out.print(s.substring(popStartIndex, popEndIndex) + "\t");
                }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Began
            int beganIndex = sub.indexOf("Began");
            if (beganIndex != -1) {
                int dateStartIndex = s.indexOf("Began", start) + 7;
                int dateEndIndex = s.indexOf(".", dateStartIndex);
                
                // "." in dates
                while (Character.isDigit(s.charAt(dateEndIndex + 2))) {
                    dateEndIndex = s.indexOf(".", dateEndIndex + 1);
                }

                String dateOutput;
                
                // comma + space
                int commaIndex = s.indexOf(",", dateStartIndex) + 1;
                if (s.charAt(commaIndex) == '1') {
                    dateOutput = s.substring(dateStartIndex, commaIndex) + " " + s.substring(commaIndex, dateEndIndex) + "\t";
                }
                else {
                    dateOutput = s.substring(dateStartIndex, dateEndIndex) + "\t";
                }
                
                // new line in dates
                if (dateOutput.indexOf("\n") != -1) {
                    dateOutput = dateOutput.substring(0, dateOutput.indexOf("\n")) + " " +
                    dateOutput.substring(dateOutput.indexOf("\n") + 1, dateOutput.length());
                }
                
                // delete "."
                while (dateOutput.indexOf(".") != -1) {
                    dateOutput = dateOutput.substring(0, dateOutput.indexOf(".")) + 
                    dateOutput.substring(dateOutput.indexOf(".") + 1, dateOutput.length());
                }
                
                System.out.print(dateOutput);
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
     
            // Subsccribers
            int subscribersIndex = sub.indexOf("Subscribers");
            if (subscribersIndex != -1) {
                int subscribersStartIndex = s.indexOf("Subscribers", start) + 13;
                int subscribersEndIndex = s.indexOf(".", subscribersStartIndex);
                String subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                
                // Commercial/Program
                if (subscribersTemp.indexOf("Commer") != -1) {
                    subscribersEndIndex = s.indexOf("Commer", subscribersStartIndex) - 2;
                    System.out.print(s.substring(subscribersStartIndex, subscribersEndIndex) + "\t");
                }
                else if (subscribersTemp.indexOf("Program") != -1){
                    subscribersEndIndex = s.indexOf("Program", subscribersStartIndex) - 2;
                    System.out.print(s.substring(subscribersStartIndex, subscribersEndIndex) + "\t");
                }
                else {
                    System.out.print(subscribersTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Potential
            int potentialIndex = sub.indexOf("Potential");
            if (potentialIndex != -1) {
                int potentialStartIndex = s.indexOf("Potential", start) + 11;
                int potentialEndIndex = s.indexOf(".", potentialStartIndex);
                
                // including
                if (s.substring(potentialStartIndex, potentialEndIndex).indexOf("cluding") != -1) {
                    note += "Potential " + s.substring(potentialStartIndex, s.indexOf(")", potentialStartIndex)) + " ";
                    potentialStartIndex = s.indexOf(":", potentialStartIndex) + 2;
                }
                
                System.out.print(s.substring(potentialStartIndex, potentialEndIndex) + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            //Homes
            int homeIndex = sub.indexOf("t of plant");
            if (homeIndex != -1) {
                int homeStartIndex = s.indexOf("t of plant", start) + 12;
                int homeEndIndex = s.indexOf(".", homeStartIndex);
                System.out.print(s.substring(homeStartIndex, homeEndIndex) + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Capacity
            int capacityIndex = sub.indexOf("capacity");
            if (capacityIndex != -1) {
                int capStartIndex = s.indexOf("capacity", start) + 10;
                int capEndIndex = s.indexOf(".", capStartIndex);
                if (s.substring(capStartIndex, capEndIndex).indexOf("(") != -1) {
                    capEndIndex = s.indexOf("(", capStartIndex);
                    System.out.print(s.substring(capStartIndex, capEndIndex) + "\t");
                }
                else {
                    System.out.print(s.substring(capStartIndex, capEndIndex) + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Top-100
            int topIndex = sub.indexOf("Top-");
            if (topIndex != -1) {
                int topStartIndex = s.indexOf("Top-", start);
                int topEndIndex = s.indexOf(".", topStartIndex);
                if (s.substring(topStartIndex, topEndIndex).indexOf("Yes") != -1) {
                    System.out.print("1" + "\t");
                }
                else {
                    System.out.print("0" + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Note
            if (populationIndex == -1 &&
            beganIndex == -1 &&
            subscribersIndex == -1 &&
            potentialIndex == -1 &&
            homeIndex == -1 &&
            capacityIndex == -1 &&
            topIndex == -1) {
                System.out.print("Empty");
            }
            else {
                System.out.print(note);
            }
            
                
            // Start next
            start = s.indexOf("—", end);
            System.out.println();
        }
    }
    
    
    public void test() {
        System.out.print("Name\tCopied\tLocation copied\tPopulation\tWhen service began\tSubscribers\t"
        + "Potential\t"
        + "Home in front of plant\tChannels capicity\tTop-100 market\t"
        + "Note");
        System.out.println();
        FileResource fr = new FileResource();
        String s = fr.asString();
        parser(s);
        //for (String word: fr.words()) {
            //System.out.println(word);
        //}
    }
}
