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
        String sign;
        
        while (start != -1) {
            note = "";
            sign = "";
            
            // Town name †
            name = s.substring(end + 1, start);
            if (name.indexOf("\t") != -1) {
                name = name.substring(0, name.indexOf("\t")) + name.substring(name.indexOf("\t") + 1, name.length());
            }
            
            if (name.indexOf("t ") == 0) {
                if (name.indexOf("t ") != -1) {
                    name = name.substring(name.indexOf("t") + 2, name.length());
                }
                else {
                    name = name.substring(name.indexOf("t") + 1, name.length());
                }
                sign = sign + "†";
            }
            
            if (name.indexOf("f ") == 0) {
                if (name.indexOf("f ") != -1) {
                    name = name.substring(name.indexOf("f") + 2, name.length());
                }
                else {
                    name = name.substring(name.indexOf("f") + 1, name.length());
                }
                sign = sign + "†";
            }
            
            if (name.indexOf("+") != -1) {
                if (name.indexOf("+ ") != -1) {
                    name = name.substring(name.indexOf("+") + 2, name.length());
                }
                else {
                    name = name.substring(name.indexOf("+") + 1, name.length());
                }
                sign = sign + "†";
            }
            
            if (name.indexOf("*") != -1) {
                if (name.indexOf("* ") != -1) {
                    name = name.substring(name.indexOf("*") + 2, name.length());
                }
                else {
                    name = name.substring(name.indexOf("*") + 1, name.length());
                }
                sign = sign + "*";
            }
            
            if (name.indexOf("♦") != -1) {
                if (name.indexOf("♦ ") != -1) {
                    name = name.substring(name.indexOf("♦") + 2, name.length());
                }
                else {
                    name = name.substring(name.indexOf("♦") + 1, name.length());
                }
                sign = sign + "?";
            }
            
            
            if (name.indexOf("»") != -1) {
                if (name.indexOf("» ") != -1) {
                    name = name.substring(name.indexOf("»") + 2, name.length());
                }
                else {
                    name = name.substring(name.indexOf("»") + 1, name.length());
                }
                sign = sign + "?";
            }


            if (name.indexOf("•") != -1) {
                if (name.indexOf("• ") != -1) {
                    name = name.substring(name.indexOf("•") + 2, name.length());
                }
                else {
                    name = name.substring(name.indexOf("•") + 1, name.length());
                }
                sign = sign + "?";
            }
            System.out.print(name + "\t");

            
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
                //if (seeTemp.indexOf("\n") != -1) {
                    //System.out.print(seeTemp.substring(seeTemp.indexOf("\n") + 1, seeTemp.length()) + "\n");
                //}
                
                System.out.print(seeTemp + "\n");
                
                start = s.indexOf("—", start + 1);
                continue;
            }
            else {
                System.out.print("0" + "\t" + "" + "\t");
            }

            
            // Population
            int populationIndex = sub.indexOf("Population");
            String pop = "";
            if (populationIndex != -1) {
                int popStartIndex = s.indexOf("Population", start) + 12;
                int popEndIndex = s.indexOf(".", popStartIndex);
                pop = s.substring(popStartIndex, popEndIndex);
                
                // NumberFix
                if (Character.isDigit(s.charAt(popEndIndex + 1))) {
                    int newPopEndIndex = s.indexOf(".", popEndIndex + 1);
                    pop = s.substring(popStartIndex, popEndIndex) + "," +
                    s.substring(popEndIndex + 1, newPopEndIndex);
                }
                
                // including
                if (s.substring(popStartIndex, popEndIndex).indexOf("cluding") != -1) {
                    note += "Population " + s.substring(popStartIndex, s.indexOf(")", popStartIndex)) + " ";
                    popStartIndex = s.indexOf(":", popStartIndex) + 2;
                    pop = s.substring(popStartIndex, popEndIndex);
                }
                
                System.out.print(pop + "\t");
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
                
                // NumberFix
                if (Character.isDigit(s.charAt(subscribersEndIndex + 1))) {
                    int newSubscribersEndIndex = s.indexOf(".", subscribersEndIndex + 1);
                    subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex) + "," +
                    s.substring(subscribersEndIndex + 1, newSubscribersEndIndex);
                }
                
                // Commercial/Program
                if (subscribersTemp.indexOf("Commer") != -1) {
                    subscribersEndIndex = s.indexOf("Commer", subscribersStartIndex) - 2;
                    subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                }
                else if (subscribersTemp.indexOf("Program") != -1){
                    subscribersEndIndex = s.indexOf("Program", subscribersStartIndex) - 2;
                    subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                }
                
                System.out.print(subscribersTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Potential
            int potentialIndex = sub.indexOf("Potential");
            if (potentialIndex != -1) {
                int potentialStartIndex = s.indexOf("Potential", start) + 11;
                int potentialEndIndex = s.indexOf(".", potentialStartIndex);
                String potentialTemp = s.substring(potentialStartIndex, potentialEndIndex);
                
                // NumberFix
                if (Character.isDigit(s.charAt(potentialEndIndex + 1))) {
                    int newPotentialEndIndex = s.indexOf(".", potentialEndIndex + 1);
                    potentialTemp = s.substring(potentialStartIndex, potentialEndIndex) + "," +
                    s.substring(potentialEndIndex + 1, newPotentialEndIndex);
                }
                
                // including
                if (s.substring(potentialStartIndex, potentialEndIndex).indexOf("cluding") != -1) {
                    note += "Potential " + s.substring(potentialStartIndex, s.indexOf(")", potentialStartIndex)) + " ";
                    potentialStartIndex = s.indexOf(":", potentialStartIndex) + 2;
                    potentialTemp = s.substring(potentialStartIndex, potentialEndIndex);
                }
                
                System.out.print(potentialTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            //Homes
            int homeIndex = sub.indexOf("t of plant");
            if (homeIndex != -1) {
                int homeStartIndex = s.indexOf("t of plant", start) + 12;
                int homeEndIndex = s.indexOf(".", homeStartIndex);
                String homeTemp = s.substring(homeStartIndex, homeEndIndex);
                
                // Number Fix
                if (Character.isDigit(s.charAt(homeEndIndex + 1))) {
                    int newHomeEndIndex = s.indexOf(".", homeEndIndex + 1);
                    homeTemp = s.substring(homeStartIndex, homeEndIndex) + "," +
                    s.substring(homeEndIndex + 1, newHomeEndIndex);
                }
                
                System.out.print(homeTemp + "\t");
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
                System.out.print("Empty \t");
            }
            else {
                System.out.print(note + "\t");
            }
            
            
            // Sign
            System.out.print(sign);
                
            
            // Start next
            start = s.indexOf("—", end);
            System.out.println();
        }
    }
    
    
    public void test() {
        System.out.print("Name\tCopied\tLocation copied\tPopulation\tWhen service began\tSubscribers\t"
        + "Potential\t"
        + "Home in front of plant\tChannels capicity\tTop-100 market\t"
        + "Note\tSign");
        System.out.println();
        FileResource fr = new FileResource();
        String s = fr.asString();
        parser(s);
        //for (String word: fr.words()) {
            //System.out.println(word);
        //}
    }
}
