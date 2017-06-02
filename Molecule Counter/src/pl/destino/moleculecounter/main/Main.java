package pl.destino.moleculecounter.main;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Patryk.Wierzchon
 */
public class Main {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Input formula
        String input = "C6H12O6";
        
        MoleculeCounter counter = new MoleculeCounter();
        Map<String, Integer> results = counter.calculateMolecules(input);
        

        System.out.println("Result for: " + input);
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

}
