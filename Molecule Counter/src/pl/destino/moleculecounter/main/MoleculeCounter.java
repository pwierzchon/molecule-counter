/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.destino.moleculecounter.main;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Destino
 */
public class MoleculeCounter {

    //Results collection
    HashMap<String, Integer> results = new HashMap();

    public Map<String, Integer> calculateMolecules(String input) {

        matchStrings(input);
        
        return results;
    }
    
    private void matchStrings(String input, int multip){
        //Create Matcher
        Pattern pattern = Pattern.compile("([A-Z]{1}[a-z]*[0-9]*)");
        Pattern multPat = Pattern.compile("([A-Z]{1}[a-z]*[0-9]*)|([(]{1}([A-Z]{1}[a-z]*[0-9]*)+[)]{1}[0-9]+)");
        Pattern multiplierPattern = Pattern.compile("([)]{1}([0-9]))");
        Pattern innerPattern = Pattern.compile("([A-Z]+[0-9]*)+");
        Matcher multMacher = multPat.matcher(input);

        //Find elements with numbers
        while (multMacher.find()) {
            int multiplier = multip;

            String semiRes = multMacher.group();
            String multGroup = multMacher.group(2);

            //check if there's a 2 group
            if (null != multGroup) {
                Matcher multiplierMacher = multiplierPattern.matcher(multGroup);
                Matcher innerMatcher = innerPattern.matcher(multGroup);
                while (multiplierMacher.find()) {
                    multiplier = Integer.parseInt(multiplierMacher.group(2));
                    while (innerMatcher.find()) {
                        matchStrings(innerMatcher.group(), multiplier);
                    }

                }
            } else {
                //Check if single element
                if (semiRes.length() == 1) {
                    //add to results
                    insertElements(semiRes, multiplier);
                } else {
                    finalMatch(semiRes,multiplier);
                }
            }

        }
    }

    private void matchStrings(String input) throws NumberFormatException {
        matchStrings(input, 1);
    }

    private void finalMatch(String semiResult) throws NumberFormatException {
        finalMatch(semiResult, 1);
    }

    private void finalMatch(String semiResult, int multiplier) {
        System.out.println("semi res: " + semiResult);
        Pattern finalPattern = Pattern.compile("([A-Z]{1}[a-z]*)([0-9]+)");
        Matcher finalMatcher = finalPattern.matcher(semiResult);

        while (finalMatcher.find()) {
            String element = finalMatcher.group(1);
            Integer count = Integer.parseInt(finalMatcher.group(2)) * multiplier;
            insertElements(element, count);

        }
    }

    private void insertElements(String element, Integer count) {
        if (results.containsKey(element)) {
            Integer finalCount = Integer.sum(results.get(element), count);
            results.put(element, finalCount);
        } else {
            results.put(element, count);
        }
    }

}
