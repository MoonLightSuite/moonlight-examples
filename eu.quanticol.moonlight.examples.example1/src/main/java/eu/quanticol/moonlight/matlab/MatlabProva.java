package eu.quanticol.moonlight.matlab;

import com.mathworks.engine.MatlabEngine;
import eu.quanticol.moonlight.configurator.Configurator;
import eu.quanticol.moonlight.configurator.Matlab;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class MatlabProva {



    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();


        MatlabEngine eng = Matlab.startMatlab();
        File taliro = new File(classLoader.getResource("test_taliro.m").getFile());
        eng.eval(String.format("%s",taliro.getAbsolutePath()));
        double Z = eng.getVariable("rob10");
        System.out.println(Z);
        //double[] a = {2.0, 4.0, 6.0};
        //double[] roots = eng.feval("sqrt", a);

        eng.close();
    }


}
