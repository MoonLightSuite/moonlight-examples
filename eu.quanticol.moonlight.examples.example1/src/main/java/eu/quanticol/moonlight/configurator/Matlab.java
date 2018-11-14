package eu.quanticol.moonlight.configurator;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

public class Matlab {

    public static MatlabEngine startMatlab(){
        MatlabEngine eng = null;
        try {
            eng = MatlabEngine.startMatlab();
            eng.evalAsync( String.format("addpath(genpath('%s'))",Configurator.STALIRO_PATH));
            eng.evalAsync( String.format("addpath(genpath('%s'))",Configurator.BREACH_PATH));
            eng.evalAsync( String.format("addpath(genpath('%s'))",Configurator.BREACH_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eng;
    }
}
