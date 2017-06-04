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

        //Create Matcher
        Pattern pattern = Pattern.compile("([A-Z]{1}[a-z]*[0-9]*)");
        Matcher matcher = pattern.matcher(input);

        Pattern finalPattern = Pattern.compile("([A-Z]{1}[a-z]*)([0-9]+)");

        //Find elements with numbers
        while (matcher.find()) {
            String semiRes = matcher.group();
            Matcher finalMatcher = finalPattern.matcher(semiRes);

            //Check if single element
            if (semiRes.length() == 1) {
                //add to results
                insertElements(semiRes, 1);
            } else {
                while (finalMatcher.find()) {
                    String element = finalMatcher.group(1);
                    Integer count = Integer.parseInt(finalMatcher.group(2));
                    insertElements(element, count);

                }
            }

        }
        return results;
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
