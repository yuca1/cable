import edu.duke.*;
import java.io.*;

public class Cable{
    public void parser(String s) {
        int start = s.indexOf("—");
        int end = -1; // index after "."
        int mark; // index of next "—"

        String sub;
        String name;
        String symbol;
        
        while (start != -1) {
            // Town name
            name = s.substring(end + 1, start);
            
            // number in name
            boolean nameNumAlert = name.contains("1") ||
                                   name.contains("2") ||
                                   name.contains("3") ||
                                   name.contains("4") ||
                                   name.contains("5") ||
                                   name.contains("6") ||
                                   name.contains("7") ||
                                   name.contains("8") ||
                                   name.contains("9") ||
                                   name.contains("0");
            
            // name starts with symbol
            if (! Character.isLetter(name.charAt(0))) {
                symbol = "*";
                name = name.substring(1, name.length());
            }
            else {
                symbol = "";
            }
            
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
                String seeTemp = s.substring(start + 1, end - 1);
                
                if (seeTemp.indexOf("\n") != -1) {
                    seeTemp =  seeTemp.substring(0, seeTemp.indexOf("\n")) + 
                    seeTemp.substring(seeTemp.indexOf("\n") + 1, seeTemp.length());
                }
                
                seeTemp = seeTemp + "\t";
                
                if (seeTemp.indexOf("See", 5) != -1) {
                    seeTemp = seeTemp + "seeAlert"; 
                }
                
                if (nameNumAlert) {
                    seeTemp = seeTemp + "nameNumAlert";
                }
                
                if (seeTemp.contains("1") ||
                    seeTemp.contains("2") ||
                    seeTemp.contains("3") ||
                    seeTemp.contains("4") ||
                    seeTemp.contains("5") ||
                    seeTemp.contains("6") ||
                    seeTemp.contains("7") ||
                    seeTemp.contains("8") ||
                    seeTemp.contains("9") ||
                    seeTemp.contains("0")) {
                    seeTemp = seeTemp + "seeNumAlert";
                }
                
                if (seeTemp.indexOf("-") != -1) {
                    seeTemp = seeTemp + "wordAlert";
                }
                
                System.out.print(seeTemp);
                
                start = s.indexOf("—", start + 1);
                System.out.println();
                continue;
            }
            else {
                System.out.print("0" + "\t" + "" + "\t");
            }

            
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
                    dateTemp = s.substring(dateStartIndex, commaIndex) + " " + s.substring(commaIndex, dateEndIndex);
                }
                else {
                    dateTemp = s.substring(dateStartIndex, dateEndIndex);
                }
                
                if (dateTemp.indexOf("\n") != -1) {
                    dateTemp = dateTemp.substring(0, dateTemp.indexOf("\n")) + " " + 
                    dateTemp.substring(dateTemp.indexOf("\n") + 1, dateTemp.length());
                }
                
                if (dateTemp.indexOf("Channel") != -1) {
                    dateTemp = dateTemp.substring(0, dateTemp.indexOf("Chan") - 1);
                }
                
                System.out.print(dateTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
     
            // Basic Subsccribers
            if (sub.indexOf("Subscribers") != -1) {
                int subscribersStartIndex = s.indexOf("Subscribers", s.indexOf("Basic", start)) + 13;
                int subscribersEndIndex = s.indexOf("(", subscribersStartIndex) - 1;
                String subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                
                // N.A
                if (subscribersTemp.indexOf("N.A") != -1) {
                    subscribersTemp = "N";
                }
                
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
                // if (Character.isDigit(s.charAt(subscribersEndIndex + 1))) {
                    // int newSubscribersEndIndex = s.indexOf(".", subscribersEndIndex + 1);
                    // subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex) + "," +
                    // s.substring(subscribersEndIndex + 1, newSubscribersEndIndex);
                // }
                if (subscribersTemp.indexOf(".") != -1 && Character.isDigit(subscribersTemp.charAt(subscribersTemp.indexOf(".") + 1))) {
                    subscribersTemp = subscribersTemp.substring(0, subscribersTemp.indexOf(".")) + "," + 
                                      subscribersTemp.substring(subscribersTemp.indexOf(".") + 1, subscribersTemp.length());
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
                
                if (basicOneTemp.indexOf("Began") != -1) {
                    basicOneTemp = basicOneTemp.substring(0, basicOneTemp.indexOf("Began") - 2);
                }
                
                if (basicOneTemp.indexOf(".") != -1 && Character.isDigit(basicOneTemp.charAt(basicOneTemp.indexOf(".") + 1))) {
                    basicOneTemp = basicOneTemp.substring(0, basicOneTemp.indexOf(".")) + "," + 
                                      basicOneTemp.substring(basicOneTemp.indexOf(".") + 1, basicOneTemp.length());
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
                
                if (basicTwoTemp.indexOf(".") != -1 && Character.isDigit(basicTwoTemp.charAt(basicTwoTemp.indexOf(".") + 1))) {
                    basicTwoTemp = basicTwoTemp.substring(0, basicTwoTemp.indexOf(".")) + "," + 
                                      basicTwoTemp.substring(basicTwoTemp.indexOf(".") + 1, basicTwoTemp.length());
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
                
                if (basicThreeTemp.indexOf(".") != -1 && 
                Character.isDigit(basicThreeTemp.charAt(basicThreeTemp.indexOf(".") + 1))) {
                    basicThreeTemp = basicThreeTemp.substring(0, basicThreeTemp.indexOf(".")) + "," + 
                                      basicThreeTemp.substring(basicThreeTemp.indexOf(".") + 1, basicThreeTemp.length());
                }
                
                System.out.print(basicThreeTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Tier 1
            if (sub.indexOf("Tier 1") != -1 || sub.indexOf("Tierl") != -1) {
                int tierStart = s.indexOf("Tier", start);
                int tierStartIndex = s.indexOf("Subscribers", tierStart) + 13;
                int tierEndIndex = s.indexOf("(", tierStartIndex) - 1;
                String tierTemp = s.substring(tierStartIndex, tierEndIndex);
                
                if (tierStartIndex > end) {
                    tierTemp = "N/A";
                }
                
                if (tierTemp.indexOf("N.A") != -1) {
                    tierTemp = "N";
                }
                
                if (tierTemp.indexOf("Began") != -1) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf("Began") - 2);
                }  
                
                if (tierTemp.indexOf("Program") != -1) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf("Program") - 2);
                }
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                
                if (tierTemp.indexOf(".") != -1 && Character.isDigit(tierTemp.charAt(tierTemp.indexOf(".") + 1))) {
                    tierTemp = tierTemp.substring(0, tierTemp.indexOf(".")) + "," + 
                                      tierTemp.substring(tierTemp.indexOf(".") + 1, tierTemp.length());
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
                int homeStartIndex = s.indexOf("passed", s.lastIndexOf("Basic", end)) + 8;
                int homeEndIndex = s.indexOf(".", homeStartIndex);
                String passedTemp = s.substring(homeStartIndex, homeEndIndex);
                
                if (Character.isDigit(s.charAt(homeEndIndex + 1))) {
                    int newHomeEndIndex = s.indexOf(".", homeEndIndex + 1);
                    passedTemp = s.substring(homeStartIndex, homeEndIndex) + "," +
                    s.substring(homeEndIndex + 1, newHomeEndIndex);
                }
                
                if (passedTemp.indexOf("cluded") != -1) {
                    passedTemp = "I" + s.substring(s.indexOf("ncluded", homeStartIndex), homeEndIndex);
                }
                
                if (passedTemp.indexOf(";") != -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf(";"));
                }
                
                if (passedTemp.indexOf("Manag")!= -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf("Manag") -1);
                }
                
                if (passedTemp.indexOf("Total") != -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf("Total") - 2);
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
                
                if (Character.isDigit(s.charAt(areaEndIndex + 1))) {
                    int newAreaEndIndex = s.indexOf(".", areaEndIndex + 1);
                    areaTemp = s.substring(areaStartIndex, areaEndIndex) + "," +
                    s.substring(areaEndIndex + 1, newAreaEndIndex);
                }
                
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
                
                if (capTemp.indexOf("Channel") != -1) {
                    capTemp = capTemp.substring(0, capTemp.indexOf("Chan") - 2);
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
                String notTemp = s.substring(notStartIndex, notEndIndex);
                
                if (notTemp.indexOf("Basic") != -1) {
                    notTemp = notTemp.substring(0, notTemp.indexOf("Basic") - 2);
                }
                
                System.out.print(notTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Ranking
            if (sub.indexOf("Ranking") != -1) {
                int rankingStartIndex = s.indexOf("Ranking", start) + 9;
                int rankingEndIndex = s.indexOf(".", rankingStartIndex);
                
                while (s.substring(rankingEndIndex - 2, rankingEndIndex).equals("wp")) {
                    rankingEndIndex = s.indexOf(".", rankingEndIndex + 2);
                }
                String rankingTemp = s.substring(rankingStartIndex, rankingEndIndex);
                
                if (rankingTemp.indexOf("Original") != -1) {
                    rankingTemp = rankingTemp.substring(0, rankingTemp.indexOf("Original") - 2);
                }
                
                if (rankingTemp.indexOf("\n") != -1) {
                    rankingTemp = rankingTemp.substring(0, rankingTemp.indexOf("\n")) +
                                  rankingTemp.substring(rankingTemp.indexOf("\n") + 1, rankingTemp.length());
                }
                
                System.out.print(rankingTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Note + symbol
            if (sub.indexOf("Note") != -1 && sub.indexOf("Note") < sub.indexOf("Basic")) {
                int noteStartIndex = s.indexOf("Note", start) + 6;
                int noteEndIndex = s.indexOf("Basic", noteStartIndex + 5);
                System.out.print(symbol + s.substring(noteStartIndex, noteEndIndex) + "\t");
            }
            else {
                System.out.print(symbol + "\t");
            }
                
            
            // Alert
            String alert = "";
            if (nameNumAlert) {
                alert = alert + "nameNumAlert";
            }
            System.out.print(alert + "\t");
            
            
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
        + "TV Market Ranking\tNote\tAlert");
        System.out.println();
        FileResource fr = new FileResource();
        String s = fr.asString();
        parser(s);
    }
}
