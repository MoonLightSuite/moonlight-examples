package eu.quanticol.moonlight.matlab;

import com.mathworks.engine.MatlabEngine;
import eu.quanticol.moonlight.configurator.Matlab;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MatlabProva {


    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        MatlabEngine eng = Matlab.startMatlab();
        InputStream resourceAsStream = classLoader.getResourceAsStream("prova.m");
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                eng.eval(line);
            }
        }
        br.close();
        eng.feval("taliroRes = taliro(X,T)");
        double Z = eng.getVariable("taliroRes");
        System.out.println(Z);

        //double[] a = {2.0, 4.0, 6.0};
        //double[] roots = eng.feval("sqrt", a);
        eng.close();
    }


}
