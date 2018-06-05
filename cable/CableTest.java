import edu.duke.*;
import java.io.*;

public class CableTest{
    public void parser(String s) {
        int start = s.indexOf("—");
        int end = -1; // index after "."
        int mark; // index of next "—"

        String sub;
        String name;
        
        while (start != -1) {
            // Town name
            name = s.substring(end + 1, start);
            System.out.print(name + "\t");
            
            
            // Mark the next "—"
            mark = s.indexOf("—", start + 1);
            
            // Substring
            if (mark == -1) {
                end = s.length() - 1;
            }
            else {
                end = s.lastIndexOf("\n", mark);
            }
            sub = s.substring(start, end);

            
            // Case of See...
            if (s.substring(start + 1, start + 4).equals("See") ||
            s.substring(start + 2, start + 5).equals("See")) {
                System.out.print("1" + "\t");
                // end = s.indexOf(".", start + 1);
                // while (s.indexOf(".", end + 1) < mark ) {
                    // if (
                    // s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TWP") ||
                    // s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("Twp") ||
                    // s.substring(s.indexOf(".", end + 1) - 2, s.indexOf(".", end + 1)).equals("ST")
                    // ) {
                        // break;
                    // }
                    // else {
                        // end = s.indexOf(".", end + 1);
                    // }
                // }
                
                String seeTemp = s.substring(start + 1, end - 1);
                if (seeTemp.indexOf("\n") != -1) {
                    System.out.print(seeTemp.substring(seeTemp.indexOf("\n") + 1, seeTemp.length()));
                }
                else {
                    System.out.print(seeTemp);
                }
                start = s.indexOf("—", start + 1);
                System.out.println();
                continue;
            }
            else {
                System.out.print("0" + "\t" + "" + "\t");
            }

            
            // Substring
            // if (subTemp.indexOf("Note:") != -1) {
                // end = s.indexOf(".", s.indexOf("Note:", start)) + 1;
                // while (s.indexOf(".", end) < mark ) {
                    // if (
                    // s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TWP") ||
                    // s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("Twp") ||
                    // s.substring(s.indexOf(".", end + 1) - 2, s.indexOf(".", end + 1)).equals("ST")
                    
                    // ) {
                        // break;
                    // }
                    // else {
                        // end = s.indexOf(".", end + 1) + 1;
                    // }
                // }
            // }
            // else if(subTemp.indexOf("Ownership") != -1) {
                // end = s.indexOf(".", s.indexOf("Ownership:", start)) + 1;
                // while (s.indexOf(".", end) < mark ) {
                    // if (
                    // s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TWP") ||
                    // s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TOP") ||
                    // s.substring(s.indexOf(".", end + 1) - 2, s.indexOf(".", end + 1)).equals("ST")
                    
                    // ) {
                        // break;
                    // }
                    // else {
                        // end = s.indexOf(".", end + 1) + 1;
                    // }
                // }
            // }
            
            // Population
            if (sub.indexOf("Population") != -1) {
                int popStartIndex = s.indexOf("Population", start) + 12;
                int popEndIndex = s.indexOf(".", popStartIndex);
                
                String popTemp = s.substring(popStartIndex, popEndIndex);
                
                // number fix
                if (Character.isDigit(s.charAt(popEndIndex + 1))) {
                    int newPopEndIndex = s.indexOf(".", popEndIndex + 1);
                    popTemp = s.substring(popStartIndex, popEndIndex) + "," +
                    s.substring(popEndIndex + 1, newPopEndIndex);
                }
                
                if (popTemp.indexOf("TV") != -1) {
                    popTemp = popTemp.substring(0, popTemp.indexOf("TV") - 1);
                }
                
                System.out.print(popTemp + "\t");
                
                
                }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Began
            if (sub.indexOf("Began") != -1) {
                int dateStartIndex = s.indexOf("Began", start) + 7;
                int dateEndIndex = s.indexOf(".", dateStartIndex);
                int commaIndex = s.indexOf(",", dateStartIndex) + 1;
                String dateTemp;

                if (s.charAt(commaIndex) == '1') {
                    dateTemp = s.substring(dateStartIndex, commaIndex) + " " + s.substring(commaIndex, dateEndIndex) + "\t";
                }
                else {
                    dateTemp = s.substring(dateStartIndex, dateEndIndex) + "\t";
                }
                
                if (dateTemp.indexOf("\n") != -1) {
                    dateTemp = dateTemp.substring(0, dateTemp.indexOf("\n")) + " " + 
                    dateTemp.substring(dateTemp.indexOf("\n") + 1, dateTemp.length());
                }
                
                
                System.out.print(dateTemp);
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
     
            // Basic Subsccribers
            if (sub.indexOf("Subscribers") != -1) {
                int subscribersStartIndex = s.indexOf("Subscribers", s.indexOf("Basic", start)) + 13;
                int subscribersEndIndex = s.indexOf("(", subscribersStartIndex) - 1;
                String subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                
                // commercial/program included
                if (subscribersTemp.indexOf("Commer") != -1) {
                    subscribersEndIndex = s.indexOf("Commer", subscribersStartIndex) - 2;
                    subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                }
                
                else if (subscribersTemp.indexOf("Program") != -1){
                    subscribersEndIndex = s.indexOf("Program", subscribersStartIndex) - 2;
                    subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                }
                
                // number fix
                if (Character.isDigit(s.charAt(subscribersEndIndex + 1))) {
                    int newSubscribersEndIndex = s.indexOf(".", subscribersEndIndex + 1);
                    subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex) + "," +
                    s.substring(subscribersEndIndex + 1, newSubscribersEndIndex);
                }
                
                System.out.print(subscribersTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            //Basic 1
            if (sub.indexOf("Basic 1") != -1) {
                int basicOneStart = s.indexOf("Basic 1", start);
                int basicOneStartIndex = s.indexOf("Subscribers", basicOneStart) + 13;
                int basicOneEndIndex = s.indexOf("(", basicOneStartIndex) - 1;
                String basicOneTemp = s.substring(basicOneStartIndex, basicOneEndIndex);
                
                if (basicOneTemp.indexOf("N.A") != -1) {
                    basicOneTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(basicOneEndIndex + 1))) {
                    int newBasicOneEndIndex = s.indexOf(".", basicOneEndIndex + 1);
                    basicOneTemp = s.substring(basicOneStartIndex, basicOneEndIndex) + "," +
                    s.substring(basicOneEndIndex + 1, newBasicOneEndIndex);
                }
                
                System.out.print(basicOneTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            //Basic 2
            if (sub.indexOf("Basic 2") != -1) {
                int basicTwoStart = s.indexOf("Basic 2", start);
                int basicTwoStartIndex = s.indexOf("Subscribers", basicTwoStart) + 13;
                int basicTwoEndIndex = s.indexOf("(", basicTwoStartIndex) - 1;
                String basicTwoTemp = s.substring(basicTwoStartIndex, basicTwoEndIndex);
                
                if (basicTwoTemp.indexOf("N.A") != -1) {
                    basicTwoTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(basicTwoEndIndex + 1))) {
                    int newBasicTwoEndIndex = s.indexOf(".", basicTwoEndIndex + 1);
                    basicTwoTemp = s.substring(basicTwoStartIndex, basicTwoEndIndex) + "," +
                    s.substring(basicTwoEndIndex + 1, newBasicTwoEndIndex);
                }
                
                System.out.print(basicTwoTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            //Basic 3
            if (sub.indexOf("Basic 3") != -1) {
                int basicThreeStart = s.indexOf("Basic 3", start);
                int basicThreeStartIndex = s.indexOf("Subscribers", basicThreeStart) + 13;
                int basicThreeEndIndex = s.indexOf("(", basicThreeStartIndex) - 1;
                String basicThreeTemp = s.substring(basicThreeStartIndex, basicThreeEndIndex);
                
                if (basicThreeTemp.indexOf("N.A") != -1) {
                    basicThreeTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(basicThreeEndIndex + 1))) {
                    int newBasicThreeEndIndex = s.indexOf(".", basicThreeEndIndex + 1);
                    basicThreeTemp = s.substring(basicThreeStartIndex, basicThreeEndIndex) + "," +
                    s.substring(basicThreeEndIndex + 1, newBasicThreeEndIndex);
                }
                
                System.out.print(basicThreeTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Tier 1
            if (sub.indexOf("Tier 1") != -1) {
                int tierStart = s.indexOf("Tier 1", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 2
            if (sub.indexOf("Tier 2") != -1) {
                int tierStart = s.indexOf("Tier 2", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N.A";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 3
            if (sub.indexOf("Tier 3") != -1) {
                int tierStart = s.indexOf("Tier 3", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N.A";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 4
            if (sub.indexOf("Tier 4") != -1) {
                int tierStart = s.indexOf("Tier 4", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 5
            if (sub.indexOf("Tier 5") != -1) {
                int tierStart = s.indexOf("Tier 5", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N.A";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 6
            if (sub.indexOf("Tier 6") != -1) {
                int tierStart = s.indexOf("Tier 6", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N.A";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 7
            if (sub.indexOf("Tier 7") != -1) {
                int tierStart = s.indexOf("Tier 7", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N.A";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 8
            if (sub.indexOf("Tier 8") != -1) {
                int tierStart = s.indexOf("Tier 8", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N.A";
                }
                
                if (Character.isDigit(s.charAt(tierEndIndex + 1))) {
                    int newTierEndIndex = s.indexOf(".", tierEndIndex + 1);
                    tierTemp = s.substring(tierStartIndex, tierEndIndex) + "," +
                    s.substring(tierEndIndex + 1, newTierEndIndex);
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                System.out.print(tierTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // HBO & MTV
            if (sub.indexOf("HBO") != -1) {
                System.out.print("1" + "\t");
            }
            else {
                System.out.print("0" + "\t");
            }
            
            if (sub.indexOf("MTV") != -1) {
                System.out.print("1" + "\t");
            }
            else {
                System.out.print("0" + "\t");
            }
            
            
            //Home passed
            if (sub.indexOf("passed") != -1) {
                int homeStartIndex = s.indexOf("passed", s.indexOf("Basic", start)) + 8;
                int homeEndIndex = s.indexOf(".", homeStartIndex);
                String passedTemp = s.substring(homeStartIndex, homeEndIndex);
                
                if (passedTemp.indexOf("cluded") != -1) {
                    passedTemp = "I" + s.substring(s.indexOf("ncluded", homeStartIndex), homeEndIndex);
                }
                
                if (passedTemp.indexOf(";") != -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf(";"));
                }
                
                if (passedTemp.indexOf("Manag")!= -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf("Manag") -1);
                }
                
                
                System.out.print(passedTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Homes in Franchised area
            if (sub.indexOf("ised area") != -1) {
                int areaStartIndex = s.indexOf("ised area", s.indexOf("Basic", start)) + 11;
                int areaEndIndex = s.indexOf(".", areaStartIndex);
                String areaTemp = s.substring(areaStartIndex, areaEndIndex);
                
                if (areaTemp.indexOf("cluded") != -1) {
                    areaTemp = "I" + s.substring(s.indexOf("ncluded", areaStartIndex), areaEndIndex);
                }
                
                System.out.print(areaTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Capacity
            if (sub.indexOf("capacity") != -1) {
                int capStartIndex = s.indexOf("capacity", start) + 10;
                int capEndIndex = s.indexOf(".", capStartIndex);
                String capTemp = s.substring(capStartIndex, capEndIndex);
                
                if (capTemp.indexOf("(") != -1) {
                    capEndIndex = s.indexOf("(", capStartIndex);
                    capTemp = s.substring(capStartIndex, capEndIndex);
                }
                
                System.out.print(capTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Not in use
            if (sub.indexOf("n use") != -1) {
                int notStartIndex = s.indexOf("n use", start) + 7;
                int notEndIndex = s.indexOf(".", notStartIndex);
                
                System.out.print(s.substring(notStartIndex, notEndIndex) + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Note
            if (sub.indexOf("Note") != -1 && sub.indexOf("Note") < sub.indexOf("Basic")) {
                int noteStartIndex = s.indexOf("Note", start) + 6;
                int noteEndIndex = s.indexOf("Basic", start);
                System.out.print(s.substring(noteStartIndex, noteEndIndex));
            }
                
            // Start next
            start = s.indexOf("—", end);
            System.out.println();
        }
    }
        

    
    public void test() {
        System.out.print("Name\tCopied\tLocation Copied\tPopulation\tWhen service began\tBasic Subscribers\t"
        + "Expanded Basic 1 Subscribers\tExpanded Basic 2 Subscribers\tExpanded Basic 3 Subscribers\t"
        + "Tier 1 Subscribers\tTier 2 Subscribers\tTier 3 Subscribers\tTier 4 Subscribers\tTier 5 Subscribers\t"
        + "Tier 6 Subscribers\tTier 7 Subscribers\tTier 8 Subscribers\t"
        + "HBO\tMTV\tHomes passed\tHomes in franchised area\tChannels capicity\tChannels available but not in use\t"
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
