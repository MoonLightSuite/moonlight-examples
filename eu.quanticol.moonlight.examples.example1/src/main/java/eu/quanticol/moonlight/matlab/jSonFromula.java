package eu.quanticol.moonlight.matlab;


import eu.quanticol.moonlight.formula.Formula;
import eu.quanticol.moonlight.io.FormulaJSonIO;
import eu.quanticol.moonlight.util.FormulaGenerator;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class jSonFromula {


    public static void main(String[] args) throws Exception {
        FormulaGenerator generator = new FormulaGenerator("a","b","c");
        FormulaJSonIO fJSonIO = FormulaJSonIO.getInstance();
        Formula f1 = generator.getFormula();
        String code = fJSonIO.toJson( f1 );
        System.out.print(code);
    }


}
