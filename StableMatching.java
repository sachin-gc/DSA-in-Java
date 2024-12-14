
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/*
* worst case : O(n^3)
* avg case : O(n^2)
* best case : O(n) */
public class StableMatching {
    public void match() {
        Scanner sc = new Scanner(System.in);
        HashMap<Character, ArrayList<Character>> menList = new HashMap<>();
        HashMap<Character, ArrayList<Character>> womenList = new HashMap<>();
        HashMap<Character, Character> menpartner = new HashMap<>();
        HashMap<Character, ArrayList<Character>> menpropasals = new HashMap<>();
        char[] mens = {'A', 'B', 'C', 'D'};
        char[] womens = {'W', 'X', 'Y', 'Z'};
        //for mens preferences
        System.out.println("MEN'S PREFERENCES LIST : ");
        for (char m : mens) {
            System.out.println("enter " + m + " prefernce list");
            ArrayList<Character> preferences = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                preferences.add(sc.next().charAt(0));
            }
            menList.put(m, preferences);
            menpropasals.put(m, new ArrayList<>());
        }
        //for womens preferences
        System.out.println("WOMEN'S PREFERENCES LIST : ");
        for (char w : womens) {
            System.out.println("enter " + w + " preference list");
            ArrayList<Character> preferences = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                preferences.add(sc.next().charAt(0));
            }
            womenList.put(w, preferences);

        }

        while (menpartner.size() < 4) {  //until all get womens
            for (char man : mens) {
                if (!menpartner.containsKey(man)) { //if he is free
                    ArrayList<Character> proposals = menpropasals.get(man);  //take his proposal list
                    for (char women : menList.get(man)) { //take first women in his preference list
                        if (!proposals.contains(women)) {  //she should not yet praposed by him
                            proposals.add(women);  // he praposed her
                            if (!menpartner.containsValue(women)) { //to check whether she is engeged already with someone
                                menpartner.put(man, women);
                                break;
                            } else {
                                char currentpartner = getpartner(women, menpartner);
                                if (womenList.get(women).indexOf(currentpartner) < womenList.get(women).indexOf(man)) {
                                    menpartner.put(man, null);
                                } else {
                                    menpartner.put(man, women);
                                    menpartner.put(currentpartner, null);
                                    break;
                                }

                            }
                        }
                    }
                }
            }
        }
        //print the matchings
        System.out.println("\nFinal matching:");
        for (char man : mens) {
            System.out.println(man + " is matched with " + menpartner.get(man));


        }
    }
    public char getpartner(char women,HashMap<Character,Character> menpartner) {
        for(char m : menpartner.keySet()) {
            if(women == menpartner.get(m)) {
                return m;
            }
        }
        return ' ';
    }
    public static void main(String []args) {
   new StableMatching().match();
}
}
