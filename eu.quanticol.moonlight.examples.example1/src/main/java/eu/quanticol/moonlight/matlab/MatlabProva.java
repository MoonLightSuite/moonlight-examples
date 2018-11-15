package eu.quanticol.moonlight.matlab;

import com.mathworks.engine.MatlabEngine;
public class MatlabProva {


    public static void main(String[] args) throws Exception {
        MatlabEngine eng = MatlabEngine.startMatlab();
        double[] a = {2.0, 4.0, 6.0};
        double[] roots = eng.feval("sqrt", a);
        for (double e : roots) {
            System.out.println(e);
        }
        eng.close();
    }


}
