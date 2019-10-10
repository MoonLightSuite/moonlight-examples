package eu.quanticol.moonlight.matlab;




import com.mathworks.engine.*;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EngineMatalbProva {

    public static void main(String args[]) {
            try {
        MatlabEngine eng = MatlabEngine.startMatlab();

        eng.close();
    } catch (ExecutionException | InterruptedException e) {
        e.printStackTrace();
        }
    }
}

