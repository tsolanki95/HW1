/**
* Author: Tanmay Solanki
* Class: CS 1331- B1
* Purpose: Display a list of anagrams from the "words.txt" file that correspond as anagrams to a user input
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Anagrammer {
    
    public static void main(String[] args) throws IOException {
        boolean running = true;
        while(running) {
        
            //create necessary Scanner instances
            Scanner fileScan = new Scanner(new File("words.txt"));
            Scanner keyboard = new Scanner(System.in);
            
            //gets user input
            System.out.print("enter a word then press enter: ");
            String enteredWord = keyboard.nextLine();
            enteredWord = enteredWord.replaceAll("\\s","").toLowerCase(); //remove whitespace and to lowercase
            
            //stores any words from "words.txt" that is an anagram in the anagrams ArrayList
            System.out.println("Checking for any anagrams");
            ArrayList<String> anagrams = new ArrayList<String>();
            while(fileScan.hasNextLine()) {
                String possibleAnagram = fileScan.nextLine();
                String actualWord = possibleAnagram; //to make sure the original word is retained when all whitespaces are removed.
                possibleAnagram = possibleAnagram.replaceAll("\\s","").toLowerCase(); //remove whitespace and make it lowercase
                if (!possibleAnagram.equals("")) {
                    if (isAnagram(enteredWord, possibleAnagram)) {
                        anagrams.add(actualWord);
                    }
                }
            }
            
            //displays anagrams
            System.out.println("Scan complete.");
            int i = 1;
            if (anagrams.size() != 0) {
                System.out.println("\nList of anagrams:");
                for(String print : anagrams) {
                    System.out.println(i+". "+print);
                    i++;
                }
            } else {
                System.out.println("\nSorry, this word does not have any anagrams.");
            }
            
            //checks to either repeat or quit the program
            System.out.println("\nIs there another word that needs to be checked for anagrams? y/n");
            boolean waiting = true;
            while (waiting) {
                char first = keyboard.next().toLowerCase().charAt(0);
                switch(first) {
                    case 'y': 
                        waiting = false; 
                        break;
                    case 'n': 
                        waiting = false; 
                        running = false; 
                        break;
                    default: 
                        System.out.println("please enter either 'y' to represent yes or 'n' to represent no."); 
                        break;
                }
            }
        }
        
        System.out.println("Goodbye");
        System.exit(0);
    }
    
    /**
    * Method Name: isAnagram
    * Return Type: boolean
    * Parameters: String firstWord, String secondWord
    */
    private static boolean isAnagram(String firstWord, String secondWord) {
        
        //if the words are the exact same and length
        if (!firstWord.equals(secondWord) && (firstWord.length() == secondWord.length()) ) {
            
            //now, run algorithm to check for anagram
            char[] first = firstWord.toCharArray();
            char[] second = secondWord.toCharArray();
            
            //goes through the words and looks for commonalities, if there are any, it removes the letter from the second word
            for (int i = 0; i<first.length; i++) {
                boolean done = false;
                for (int j = 0; j<second.length; j++) {
                    if (first[i] == second[j] && !done) {
                        second[j] = 0;
                        done = true;
                    }
                }
            }
            
            //if all of the letters from the second word have been removed, it is an anagram
            for (int k = 0; k<second.length; k++) {
                if (second[k] != 0) {
                    return false;
                }
            }
            return true;
        }
        
        return false;
        
    }
    
}