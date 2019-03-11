package eu.quanticol.moonlight.examples.example1;

import eu.quanticol.moonlight.formula.BooleanDomain;
import eu.quanticol.moonlight.formula.DoubleDistance;
import eu.quanticol.moonlight.formula.SignalDomain;
import eu.quanticol.moonlight.signal.DistanceStructure;
import eu.quanticol.moonlight.signal.GraphModel;
import eu.quanticol.moonlight.signal.SpatialModel;
import eu.quanticol.moonlight.signal.SpatialSignal;
import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;


public class mainSp {
    public static void main(String[] argv) {
        Integer size = 4;
        GraphModel<Double> city = new GraphModel<Double>(size);
        city.add(0, 3.0, 1);
        city.add(0, 7.0, 3);
        city.add(2, 5.0, 1);
        city.add(2, 4.0, 3);
        city.add(3, 7.0, 0);
        city.add(1, 5.0, 2);
        city.add(3, 4.0, 2);

        SpatialSignal<Boolean> stop = new SpatialSignal<>(size);
        ArrayList<Boolean> Stop=new ArrayList<Boolean>();
        Stop.add(Boolean.TRUE);
        Stop.add(Boolean.FALSE);
        Stop.add(Boolean.FALSE);
        Stop.add(Boolean.TRUE);
        stop.add(0,Stop);

        SpatialSignal<Boolean> restaurant = new SpatialSignal<>(size);
        ArrayList<Boolean> Restaurant=new ArrayList<Boolean>();
        Restaurant.add(Boolean.FALSE);
        Restaurant.add(Boolean.TRUE);
        Restaurant.add(Boolean.FALSE);
        Restaurant.add(Boolean.FALSE);
        restaurant.add(0,Restaurant);

        SpatialSignal<Boolean> hospital = new SpatialSignal<>(size);
        ArrayList<Boolean> Hospital=new ArrayList<Boolean>();
        Hospital.add(Boolean.FALSE);
        Hospital.add(Boolean.FALSE);
        Hospital.add(Boolean.TRUE);
        Hospital.add(Boolean.FALSE);
        hospital.add(0,Hospital);



        double range = 10;
        DistanceStructure<Double, Double> minutes = new DistanceStructure<>(x -> x, new DoubleDistance(), d -> d<range, city);

        ArrayList<Boolean> result = minutes.somewhere(new BooleanDomain(), i -> i<2 );
        System.out.print(result);


        //ArrayList<Boolean> result = minutes.somewhere(new BooleanDomain(), i -> hospital.getSignals().get(i).getIterator(Boolean.TRUE);
    }


    }



