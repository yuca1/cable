import edu.duke.*;
import java.io.*;
import java.util.Scanner;


public class Cable1973{
    public void parser(String s) {
        // input state
        Scanner scanner = new Scanner(System.in);
        String state = scanner.nextLine();
        
        int start = s.indexOf("—");
        int end = -1; // index after "."
        int mark; // index of next "—"

        String sub;
        String name;
        String symbol;
        String stateList = "ALAKAZARCACOCTDEDCFLGAHIIDILINIAKSKYLAMEMDMAMIMNMSMOMTNENVNHNJNMNYNCNDOHOKORPARISCSDTNTXUTVTVAWAWVWIWYASGUVI";
        
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
                if (Character.toString(name.charAt(0)).equals("*")) {
                    symbol = "*";
                }
                else if (Character.toString(name.charAt(0)).equals("†")) {
                    symbol = "†";
                }
                else {
                    symbol = "?";
                }
                name = name.substring(2, name.length());
            }
            else {
                symbol = "";
            }
            
            // when "†" is recognized as "t"
            if (name.substring(0, 2).equals("t ")) {
                symbol = "†";
                name = name.substring(2, name.length());
            }
            
            System.out.print(state + "\t");
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
                
                /*
                if (! stateList.contains(seeTemp.substring(seeTemp.length() - 3, seeTemp.length() - 1))) {
                    seeTemp = seeTemp + "stateAlert";
                }
                */
                
                System.out.print(seeTemp);
                
                start = s.indexOf("—", start + 1);
                System.out.println();
                continue;
            }
            else {
                System.out.print("0" + "\t" + "" + "\t");
            }

            
            // Subsccribers
            if (sub.indexOf("Subscribers") != -1) {
                int subscribersStartIndex = s.indexOf("Subscribers", start) + 13;
                int subscribersEndIndex = s.indexOf("(", subscribersStartIndex) - 1;
                String subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                
                // N.A
                if (subscribersTemp.indexOf("N.A") != -1) {
                    subscribersTemp = "N";
                }
                
                // potential included
                if (subscribersTemp.indexOf("Potent") != -1) {
                    subscribersEndIndex = s.indexOf("Potent", subscribersStartIndex) - 2;
                    subscribersTemp = s.substring(subscribersStartIndex, subscribersEndIndex);
                }

                // number fix
                if (subscribersTemp.indexOf(".") != -1 && Character.isDigit(subscribersTemp.charAt(subscribersTemp.indexOf(".") + 1))) {
                    subscribersTemp = subscribersTemp.substring(0, subscribersTemp.indexOf(".")) + "," + 
                                      subscribersTemp.substring(subscribersTemp.indexOf(".") + 1, subscribersTemp.length());
                }
                
                System.out.print(subscribersTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Potential
            if (sub.indexOf("Potential") != -1) {
                int potStartIndex = s.indexOf("Potential", start) + 11;
                int potEndIndex = s.indexOf(".", potStartIndex);
                
                // make sure the period marks the end of the number
                while (Character.isDigit(s.charAt(potEndIndex + 1))) {
                    potEndIndex = s.indexOf(".", potStartIndex + 1);
                }
                
                String potTemp = s.substring(potStartIndex, potEndIndex);
                
                // replace periods with commas
                while (potTemp.indexOf(".") != -1) {
                    potTemp = potTemp.substring(0, potTemp.indexOf(".")) + "," +
                    potTemp.substring(potTemp.indexOf(".") + 1, potTemp.length());
                }
                
                // exclude top-100 market
                if (potTemp.indexOf("Top") != -1) {
                    potTemp = potTemp.substring(0, potTemp.indexOf("Top") - 2);
                }
                
                System.out.print(potTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Top-100 Market
            if (sub.indexOf("Top-") != -1) {
                int topStartIndex = s.indexOf("Top-", start) + 6;
                int topEndIndex = s.indexOf(".", topStartIndex);
                String topTemp = s.substring(topStartIndex, topEndIndex);
                
                if (topTemp.indexOf("Yes") != -1) {
                    System.out.print("1" + "\t");
                }
                else if (topTemp.indexOf("-") != -1) {
                    System.out.print("-" + "\t");
                }
                else {
                    System.out.print("0" + "\t");
                }
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Population
            if (sub.indexOf("Population") != -1) {
                int popStartIndex = s.indexOf("Population", start) + 12;
                int popEndIndex = s.indexOf(".", popStartIndex);
                
                // make sure the period marks the end of the number
                while (Character.isDigit(s.charAt(popEndIndex + 1))) {
                    popEndIndex = s.indexOf(".", popEndIndex + 1);
                }
                
                String popTemp = s.substring(popStartIndex, popEndIndex);
                
                // replace periods with commas
                while (popTemp.indexOf(".") != -1) {
                    popTemp = popTemp.substring(0, popTemp.indexOf(".")) + "," +
                    popTemp.substring(popTemp.indexOf(".") + 1, popTemp.length());
                }
                
                // exclude began date
                if (popTemp.indexOf("Began") != -1) {
                    popTemp = popTemp.substring(0, popTemp.indexOf("Began") - 2);
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
                
                // make sure the period marks the end of the date
                while (Character.isDigit(s.charAt(dateEndIndex + 2)) ||
                Character.isDigit(s.charAt(dateEndIndex + 1))) {
                    dateEndIndex = s.indexOf(".", dateEndIndex + 1);
                }
                
                int commaIndex = s.indexOf(",", dateStartIndex) + 1;
                String dateTemp;
                
                // add a space if necessary
                if (s.charAt(commaIndex) == '1' && commaIndex < dateEndIndex) {
                    dateTemp = s.substring(dateStartIndex, commaIndex) + " " + s.substring(commaIndex, dateEndIndex);
                }
                else {
                    dateTemp = s.substring(dateStartIndex, dateEndIndex);
                }
                
                // get rid of a new line
                if (dateTemp.indexOf("\n") != -1) {
                    dateTemp = dateTemp.substring(0, dateTemp.indexOf("\n")) + " " + 
                    dateTemp.substring(dateTemp.indexOf("\n") + 1, dateTemp.length());
                }
                
                // get rid of channel capacity
                if (dateTemp.indexOf("Channel") != -1) {
                    dateTemp = dateTemp.substring(0, dateTemp.indexOf("Chan") - 2);
                }
                
                // get rid of period
                if (dateTemp.indexOf(".") != -1) {
                    dateTemp = dateTemp.substring(0, dateTemp.indexOf(".")) + 
                    dateTemp.substring(dateTemp.indexOf(".") + 1, dateTemp.length());
                }
                
                System.out.print(dateTemp + "\t");
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
                
                // exclude TV stations
                if (capTemp.indexOf("TV") != -1) {
                    capTemp = capTemp.substring(0, capTemp.indexOf("TV") - 2);
                }
                
                System.out.print(capTemp + "\t"); 
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            //Homes in front of plant
            if (sub.indexOf("t of plant") != -1) {
                int homeStartIndex = s.indexOf("t of plant", start) + 12;
                int homeEndIndex = s.indexOf(".", homeStartIndex);

                // make sure the period marks the end of the number
                while (Character.isDigit(s.charAt(homeEndIndex + 1))) {
                    homeEndIndex = s.indexOf(".", homeEndIndex + 1);
                }
                
                String passedTemp = s.substring(homeStartIndex, homeEndIndex);
                
                // replace periods with commas
                while (passedTemp.indexOf(".") != -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf(".")) + "," +
                    passedTemp.substring(passedTemp.indexOf(".") + 1, passedTemp.length());
                }
                
                // in case the number is included in another place's number
                if (passedTemp.indexOf("cluded") != -1) {
                    passedTemp = "I" + s.substring(s.indexOf("ncluded", homeStartIndex), homeEndIndex);
                }
                
                if (passedTemp.indexOf(";") != -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf(";"));
                }
                
                // exclude Homes in franchised area
                if (passedTemp.indexOf("Homes") != -1) {
                    passedTemp = passedTemp.substring(0, passedTemp.indexOf("Homes") - 2);
                }
                
                System.out.print(passedTemp + "\t");
                
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Homes in Franchised area
            if (sub.indexOf("ised area") != -1) {
                int areaStartIndex = s.indexOf("ised area", start) + 11;
                int areaEndIndex = s.indexOf(".", areaStartIndex);
                
                // make sure the period marks the end of the number
                while (Character.isDigit(s.charAt(areaEndIndex + 1))) {
                    areaEndIndex = s.indexOf(".", areaEndIndex + 1);
                }
                
                String areaTemp = s.substring(areaStartIndex, areaEndIndex);
                
                // replace periods with commas
                while (areaTemp.indexOf(".") != -1) {
                    areaTemp = areaTemp.substring(0, areaTemp.indexOf(".")) + "," +
                    areaTemp.substring(areaTemp.indexOf(".") + 1, areaTemp.length());
                }
                
                // in case the number is included in another place's number
                if (areaTemp.indexOf("cluded") != -1) {
                    areaTemp = "I" + s.substring(s.indexOf("ncluded", areaStartIndex), areaEndIndex);
                }
                
                System.out.print(areaTemp + "\t");
            }
            else {
                System.out.print("N/A" + "\t");
            }
            
            
            // Note
            System.out.print(symbol + "\t");
            
            
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
        System.out.print("State\t");
        System.out.print("Name\tCopied\tLocation Copied\tSubscribers\tPotential\tTop-100 market\t" + 
        "Population\tBegan\tChannel capacity\t" + 
        "Homes in front of plant\tHomes in franchised area\t" + 
        "Note\tAlert");
        System.out.println();
        FileResource fr = new FileResource();
        String s = fr.asString();
        parser(s);
    }
}
