package eu.quanticol.moonlight.matlab;

import eu.quanticol.moonlight.configurator.Matlab;
import eu.quanticol.moonlight.formula.Formula;
import eu.quanticol.moonlight.io.FormulaToTaliro;
import eu.quanticol.moonlight.signal.SignalCreator;
import eu.quanticol.moonlight.signal.SignalCreatorDouble;
import eu.quanticol.moonlight.signal.VariableArraySignal;
import eu.quanticol.moonlight.util.FormulaGenerator;
import eu.quanticol.moonlight.util.FutureFormulaGenerator;
import org.n52.matlab.control.MatlabInvocationException;
import org.n52.matlab.control.MatlabProxy;
import org.n52.matlab.control.extensions.MatlabNumericArray;
import org.n52.matlab.control.extensions.MatlabTypeConverter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class MatlabProva {
    private static FormulaToTaliro toTaliro = new FormulaToTaliro();



    public static void main2(String[] args) throws MatlabInvocationException {

        MatlabProxy proxy = Matlab.startMatlab();
        proxy.setVariable("a", 5);
        proxy.eval("a+1");
        System.out.println("done");
    }

    public static void main3(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        MatlabProxy eng = Matlab.startMatlab();
        InputStream resourceAsStream = classLoader.getResourceAsStream("prova.m");
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                eng.eval(line);
            }
        }
        br.close();
        eng.eval("taliroRes = taliro(X,T);");
        double[] Z = (double[] )eng.getVariable("taliroRes");
        System.out.println("Taliro Robustness: " +Z[0]);
        long before = System.currentTimeMillis();
        int nReps = 30;
        for (int i = 0; i < nReps; i++) {
            eng.eval("taliroRes = taliro(X,T);");
        }
        long after = System.currentTimeMillis();
        System.out.println("Taliro Avg. Time (msec) ("+ nReps +" repetitions): " +(after-before)/1000.);


        resourceAsStream = classLoader.getResourceAsStream("prova4.m");
        br = new BufferedReader(new InputStreamReader(resourceAsStream));
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                eng.eval(line);
            }
        }
        br.close();

        eng.eval("breachRes = robBreach(X,T);");
        Z =   (double[]) eng.getVariable("breachRes");
        System.out.println("Breach Robustness: " +Z[0]);
        before = System.currentTimeMillis();
        nReps = 30;
        for (int i = 0; i < nReps; i++) {
            eng.eval("breachRes = robBreach(X,T);");
        }
        after = System.currentTimeMillis();
        System.out.println("Breach Avg. Time (msec) ("+ nReps +" repetitions): " +(after-before)/1000.);
    }

    public static void main(String[] args) throws Exception {
        Map<String, Function<Double, Double>> functionalMap = new HashMap<>();
        functionalMap.put("a", t -> Math.pow(t, 2.));
        functionalMap.put("b", Math::cos);
        functionalMap.put("c", Math::sin);
        SignalCreatorDouble signalCreator = new SignalCreatorDouble(functionalMap);
        double[] time = signalCreator.generateTime(0, 100, 0.1);
        double[][] values = signalCreator.generateValues(time);
        VariableArraySignal signal = signalCreator.generate(0, 100, 0.1);
        FormulaGenerator formulaGenerator = new FutureFormulaGenerator(new Random(1), signal.getEnd()/3, signalCreator.getVariableNames());
        Formula generatedFormula = formulaGenerator.getFormula(4);
        System.out.println(generatedFormula.toString());
        System.out.println(toTaliro.toTaliro(generatedFormula));
        System.out.println(toTaliro.createPrefix(signalCreator));
        MatlabProxy eng = Matlab.startMatlab();
        eng.setVariable("T",time);
        MatlabTypeConverter processor = new MatlabTypeConverter(eng);
        processor.setNumericArray("M", new MatlabNumericArray(values, null));
        eng.eval("M = transpose(M);");
        eng.eval("T = transpose(T);");
        eng.eval(toTaliro.toTaliro(generatedFormula));
        eng.eval(toTaliro.createPrefix(signalCreator));
        eng.eval("taliroRes = taliro(M,T);");
        double[] Z = (double[] )eng.getVariable("taliroRes");
        System.out.println("Taliro Robustness: " +Z[0]);
        long before = System.currentTimeMillis();
        int nReps = 30;
        for (int i = 0; i < nReps; i++) {
            eng.eval("taliroRes = taliro(M,T);");
        }
        long after = System.currentTimeMillis();
        System.out.println("Taliro Avg. Time (msec) ("+ nReps +" repetitions): " +(after-before)/1000.);
    }


}