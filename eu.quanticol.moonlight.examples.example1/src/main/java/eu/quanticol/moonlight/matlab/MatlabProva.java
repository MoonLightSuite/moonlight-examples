package eu.quanticol.moonlight.matlab;

import eu.quanticol.moonlight.configurator.Matlab;
import org.n52.matlab.control.MatlabConnectionException;
import org.n52.matlab.control.MatlabInvocationException;
import org.n52.matlab.control.MatlabProxy;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MatlabProva {



    public static void main(String[] args) throws MatlabConnectionException, MatlabInvocationException {

        MatlabProxy proxy = Matlab.startMatlab();
        proxy.setVariable("a", 5);
        proxy.eval("a+1");
        System.out.println("done");
    }

//    public static void main2(String[] args) throws Exception {
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        MatlabEngine eng = Matlab.getProxy();
//        InputStream resourceAsStream = classLoader.getResourceAsStream("prova.m");
//        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
//        String line;
//        while ((line = br.readLine()) != null) {
//            if (!line.isEmpty()) {
//                eng.eval(line);
//            }
//        }
//        br.close();
//        eng.eval("taliroRes = taliro(X,T);");
//        double Z = eng.getVariable("taliroRes");
//        System.out.println("Taliro Robustness: " +Z);
//        long before = System.currentTimeMillis();
//        int nReps = 1000;
//        for (int i = 0; i < nReps; i++) {
//            eng.eval("taliroRes = taliro(X,T);");
//        }
//        long after = System.currentTimeMillis();
//        System.out.println("Taliro Avg. Time (msec) ("+ nReps +" repetitions): " +(after-before)/1000.);
//
//
//        resourceAsStream = classLoader.getResourceAsStream("prova.m");
//        br = new BufferedReader(new InputStreamReader(resourceAsStream));
//        while ((line = br.readLine()) != null) {
//            if (!line.isEmpty()) {
//                eng.eval(line);
//            }
//        }
//        br.close();
//
//        eng.eval("breachRes = breach(X,T);");
//        Z = eng.getVariable("breachRes");
//        System.out.println("Breach Robustness: " +Z);
//        before = System.currentTimeMillis();
//        nReps = 1000;
//        for (int i = 0; i < nReps; i++) {
//            eng.eval("breachRes = breach(X,T);");
//        }
//        after = System.currentTimeMillis();
//        System.out.println("Breach Avg. Time (msec) ("+ nReps +" repetitions): " +(after-before)/1000.);
//
//
//        eng.close();
//    }


}
