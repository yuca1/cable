import edu.duke.*;
import java.io.*;

public class CableTest{
    public void parser(String s) {
        int start = s.indexOf("—");
        int end = -1; // index after "."
        int mark; // index of next "—"
        String subTemp;
        String sub;
        String name;
        
        while (start != -1) {
            // Town name
            name = s.substring(end + 1, start);
            System.out.print(name + "\t");
            
            
            // Mark the next "—"
            mark = s.indexOf("—", start + 1);
            if (mark == -1) {
                end = s.length() - 1;
                subTemp = s.substring(start, end);
            }
            else {
                subTemp = s.substring(start, mark);
            }

            
            // Case of See...
            if (s.substring(start + 1, start + 4).equals("See") ||
            s.substring(start + 2, start + 5).equals("See")) {
                System.out.print("1" + "\t");
                end = s.indexOf(".", start + 1);
                while (s.indexOf(".", end + 1) < mark ) {
                    if (
                    s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TWP") ||
                    s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("Twp") ||
                    s.substring(s.indexOf(".", end + 1) - 2, s.indexOf(".", end + 1)).equals("ST")
                    ) {
                        break;
                    }
                    else {
                        end = s.indexOf(".", end + 1);
                    }
                }
                String seeTemp = s.substring(start + 1, end);
                if (seeTemp.indexOf("\n") != -1) {
                    System.out.print(seeTemp.substring(seeTemp.indexOf("\n") + 1, seeTemp.length()));
                }
                else {
                    System.out.print(seeTemp);
                }
                start = s.indexOf("—", start + 1);
                continue;
            }
            else {
                System.out.print("0" + "\t" + "" + "\t");
            }

            
            // Substring
            if (subTemp.indexOf("Note:") != -1) {
                end = s.indexOf(".", s.indexOf("Note:", start)) + 1;
                while (s.indexOf(".", end) < mark ) {
                    if (
                    s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TWP") ||
                    s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("Twp") ||
                    s.substring(s.indexOf(".", end + 1) - 2, s.indexOf(".", end + 1)).equals("ST")
                    
                    ) {
                        break;
                    }
                    else {
                        end = s.indexOf(".", end + 1) + 1;
                    }
                }
            }
            else if(subTemp.indexOf("Ownership") != -1) {
                end = s.indexOf(".", s.indexOf("Ownership:", start)) + 1;
                while (s.indexOf(".", end) < mark ) {
                    if (
                    s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TWP") ||
                    s.substring(s.indexOf(".", end + 1) - 3, s.indexOf(".", end + 1)).equals("TOP") ||
                    s.substring(s.indexOf(".", end + 1) - 2, s.indexOf(".", end + 1)).equals("ST")
                    
                    ) {
                        break;
                    }
                    else {
                        end = s.indexOf(".", end + 1) + 1;
                    }
                }
            }
            sub = s.substring(start, end);
            
            
            // Population
            if (sub.indexOf("Population") != -1) {
                int popStartIndex = s.indexOf("Population", start) + 12;
                int popEndIndex = s.indexOf(".", popStartIndex);
                System.out.print(s.substring(popStartIndex, popEndIndex) + "\t");
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
                String dateOutput;
                if (s.charAt(commaIndex) == '1') {
                    dateTemp = s.substring(dateStartIndex, commaIndex) + " " + s.substring(commaIndex, dateEndIndex) + "\t";
                }
                else {
                    dateTemp = s.substring(dateStartIndex, dateEndIndex) + "\t";
                }
                
                if (dateTemp.indexOf("\n") != -1) {
                    dateOutput = dateTemp.substring(0, dateTemp.indexOf("\n")) + " " +
                    dateTemp.substring(dateTemp.indexOf("\n") + 1, dateTemp.length());
                }
                else {
                    dateOutput = dateTemp;
                }
                
                System.out.print(dateOutput);
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
     
            // Basic Subsccribers
            if (sub.indexOf("Subscribers") != -1) {
                int subscribersStartIndex = s.indexOf("Subscribers", s.indexOf("Basic", start)) + 13;
                int subscribersEndIndex = s.indexOf("(", subscribersStartIndex) - 1;
                String subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
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
            
            //Basic 1
            if (sub.indexOf("Basic 1") != -1) {
                int basicOneStart = s.indexOf("Basic 1", start);
                int basicOneStartIndex = s.indexOf("Subscribers", basicOneStart) + 13;
                int basicOneEndIndex = s.indexOf("(", basicOneStartIndex) - 1;
                String basicOneTemp = s.substring(basicOneStartIndex, basicOneEndIndex);
                if (basicOneTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(basicOneTemp + "\t");
                }
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
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(basicTwoTemp + "\t");
                }
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
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(basicThreeTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Tier 1
            if (sub.indexOf("Tier 1") != -1) {
                int tierOneStart = s.indexOf("Tier 1", start);
                int tierOneStartIndex = s.indexOf("Subscribers", tierOneStart) + 13;
                int tierOneEndIndex = s.indexOf("(", tierOneStartIndex) - 1;
                String tierOneTemp = s.substring(tierOneStartIndex, tierOneEndIndex);
                if (tierOneTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierOneTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 2
            if (sub.indexOf("Tier 2") != -1) {
                int tierTwoStart = s.indexOf("Tier 2", start);
                int tierTwoStartIndex = s.indexOf("Subscribers", tierTwoStart) + 13;
                int tierTwoEndIndex = s.indexOf("(", tierTwoStartIndex) - 1;
                String tierTwoTemp = s.substring(tierTwoStartIndex, tierTwoEndIndex);
                if (tierTwoTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierTwoTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 3
            if (sub.indexOf("Tier 3") != -1) {
                int tierThreeStart = s.indexOf("Tier 3", start);
                int tierThreeStartIndex = s.indexOf("Subscribers", tierThreeStart) + 13;
                int tierThreeEndIndex = s.indexOf("(", tierThreeStartIndex) - 1;
                String tierThreeTemp = s.substring(tierThreeStartIndex, tierThreeEndIndex);
                if (tierThreeTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierThreeTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 4
            if (sub.indexOf("Tier 4") != -1) {
                int tierFourStart = s.indexOf("Tier 4", start);
                int tierFourStartIndex = s.indexOf("Subscribers", tierFourStart) + 13;
                int tierFourEndIndex = s.indexOf("(", tierFourStartIndex) - 1;
                String tierFourTemp = s.substring(tierFourStartIndex, tierFourEndIndex);
                if (tierFourTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierFourTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 5
            if (sub.indexOf("Tier 5") != -1) {
                int tierFiveStart = s.indexOf("Tier 5", start);
                int tierFiveStartIndex = s.indexOf("Subscribers", tierFiveStart) + 13;
                int tierFiveEndIndex = s.indexOf("(", tierFiveStartIndex) - 1;
                String tierFiveTemp = s.substring(tierFiveStartIndex, tierFiveEndIndex);
                if (tierFiveTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierFiveTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 6
            if (sub.indexOf("Tier 6") != -1) {
                int tierSixStart = s.indexOf("Tier 6", start);
                int tierSixStartIndex = s.indexOf("Subscribers", tierSixStart) + 13;
                int tierSixEndIndex = s.indexOf("(", tierSixStartIndex) - 1;
                String tierSixTemp = s.substring(tierSixStartIndex, tierSixEndIndex);
                if (tierSixTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierSixTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 7
            if (sub.indexOf("Tier 7") != -1) {
                int tierSevenStart = s.indexOf("Tier 7", start);
                int tierSevenStartIndex = s.indexOf("Subscribers", tierSevenStart) + 13;
                int tierSevenEndIndex = s.indexOf("(", tierSevenStartIndex) - 1;
                String tierSevenTemp = s.substring(tierSevenStartIndex, tierSevenEndIndex);
                if (tierSevenTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierSevenTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            // Tier 8
            if (sub.indexOf("Tier 8") != -1) {
                int tierEightStart = s.indexOf("Tier 8", start);
                int tierEightStartIndex = s.indexOf("Subscribers", tierEightStart) + 13;
                int tierEightEndIndex = s.indexOf("(", tierEightStartIndex) - 1;
                String tierEightTemp = s.substring(tierEightStartIndex, tierEightEndIndex);
                if (tierEightTemp.indexOf("N.A") != -1) {
                    System.out.print("N.A\t");
                }
                else {
                    System.out.print(tierEightTemp + "\t");
                }
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
                    System.out.print("I" + s.substring(s.indexOf("ncluded", homeStartIndex), homeEndIndex) + "\t");
                }
                else {
                    System.out.print(passedTemp + "\t");
                }
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
                    System.out.print("I" + s.substring(s.indexOf("ncluded", areaStartIndex), areaEndIndex) + "\t");
                }
                else {
                    System.out.print(areaTemp + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Capacity
            if (sub.indexOf("capacity") != -1) {
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
