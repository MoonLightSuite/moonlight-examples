package eu.quanticol.moonlight.examples.example1;

import eu.quanticol.moonlight.formula.BooleanDomain;
import eu.quanticol.moonlight.formula.DoubleDistance;
import eu.quanticol.moonlight.formula.SignalDomain;
import eu.quanticol.moonlight.signal.DistanceStructure;
import eu.quanticol.moonlight.signal.GraphModel;
import eu.quanticol.moonlight.signal.SpatialModel;
import eu.quanticol.moonlight.signal.SpatialSignal;
import sun.font.TrueTypeFont;
import java.util.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class mainSp {
    public static void main(String[] argv) {
        Integer size = 7;
        GraphModel<Double> city = new GraphModel<Double>(size);
        city.add(0, 2.0, 1);
        city.add(1, 2.0, 0);
        city.add(0, 2.0, 5);
        city.add(5, 2.0, 0);
        city.add(1, 9.0, 2);
        city.add(2, 9.0, 1);
        city.add(2, 3.0, 3);
        city.add(3, 3.0, 2);
        city.add(3, 6.0, 4);
        city.add(4, 6.0, 3);
        city.add(4, 7.0, 5);
        city.add(5, 7.0, 4);
        city.add(6, 4.0, 1);
        city.add(1, 4.0, 6);
        city.add(6, 15.0, 3);
        city.add(3, 15.0, 6);


        ArrayList<String> place = new ArrayList<>(Arrays.asList("BusStop", "Hospital", "MetroStop", "MainSquare", "BusStop", "Museum", "MetroStop"));
        ArrayList<Boolean> taxi=new ArrayList<>(Arrays.asList(false,false,true,false,false,true,false));
        ArrayList<Integer> people=new ArrayList<>(Arrays.asList(3,145,67,243,22,103,6));

        SpatialSignal<Boolean> spCity = new SpatialSignal<>(size);
        spCity.add(0,taxi);


        double range = 10;
        DistanceStructure<Double, Double> minutes = new DistanceStructure<>(x -> x, new DoubleDistance(), d -> d<range, city);


        //// notHospital property
        ArrayList<Boolean> notHospital = new ArrayList<Boolean>();
        place.forEach(i -> notHospital.add(!i.equals("Hospital")));

        //// Somewere Taxi property
        ArrayList<Boolean> somewhereTaxy = minutes.somewhere(new BooleanDomain(), taxi::get );

        //// Hospital -> Somewere Taxi property
        ArrayList<Boolean> r1 = new ArrayList<Boolean>(size);
        for(int i=0; i<size; i++) {
            r1.add(notHospital.get(i)||somewhereTaxy.get(i));
        }

        System.out.print(r1);

    }


    }



