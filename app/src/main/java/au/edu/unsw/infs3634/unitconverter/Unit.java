package au.edu.unsw.infs3634.unitconverter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Unit {
    public Unit(String name,String symbol,String category) {
        this.name = name;
        this.symbol = symbol;
        this.category = category;
    }

    private String name;
    private String symbol;
    private String category;

    public String getName() {
        return name;
    }
    public String getSymbol(){return symbol;}
    public String getCategory(){return category;}

    public void setName(String name) {
        this.name = name;
    }
    public void setSymbol(String symbol){this.symbol=symbol;}
    public void setCategory(String category){this.category=category;}

    //set ArrayList of Units
    public static ArrayList<Unit> getWeightUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        units.add(new Unit("Gram", "g", "WEIGHT"));
        units.add(new Unit("Kilogram", "kg", "WEIGHT"));
        units.add(new Unit("Pound", "lb", "WEIGHT"));
        units.add(new Unit("Ounce", "oz", "WEIGHT"));
        units.add(new Unit("Stone", "st", "WEIGHT"));
        return units;
    }
    public static ArrayList<Unit> getLengthUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        units.add(new Unit("Decimeter", "dm", "LENGTH"));
        units.add(new Unit("Meter", "m", "LENGTH"));
        units.add(new Unit("Kilometer", "km", "LENGTH"));
        return units;
    }


    public static ArrayList<Unit> getTimeUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        units.add(new Unit("Second", "s", "TIME"));
        units.add(new Unit("Minute", "min", "TIME"));
        units.add(new Unit("Hour", "h", "TIME"));
        return units;
    }

    public static ArrayList<Unit> getVelocityUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        units.add(new Unit("Meters/Second", "m/s", "VELOCITY"));
        units.add(new Unit("Meters/Minute", "m/min", "VELOCITY"));
        units.add(new Unit("Kilometers/Hour", "km/h", "VELOCITY"));
        return units;
    }



    //Convert strategy
    public static BigDecimal ConvertStrategy(String unitName1, String unitName2) {

        //Use BigDecimal increase accuracy of multiplier
        BigDecimal multiplier = new BigDecimal("1.00000");

        //Pound and Gram
        if (unitName1.equals("Pound") && unitName2.equals("Gram")) {multiplier = new BigDecimal("453.59237");}
        if (unitName1.equals("Gram") && unitName2.equals("Pound")) {multiplier = new BigDecimal("0.0022046"); }

        //Pound and Kilogram
        if (unitName1.equals("Kilogram") && unitName2.equals("Pound")) {multiplier = new BigDecimal("2.2046226"); }
        if (unitName1.equals("Pound") && unitName2.equals("Kilogram")) {multiplier = new BigDecimal("0.4535924"); }

        //Pound and ounces
        if (unitName1.equals("Ounce") && unitName2.equals("Pound")) {multiplier = new BigDecimal("0.062500"); }
        if (unitName1.equals("Pound") && unitName2.equals("Ounce")) {multiplier = new BigDecimal("16.00000"); }

        //Pound and Stone
        if (unitName1.equals("Stone") && unitName2.equals("Pound")) {multiplier = new BigDecimal("14.0000"); }
        if (unitName1.equals("Pound") && unitName2.equals("Stone")) {multiplier = new BigDecimal("0.071429"); }

        //Kilogram and Gram
        if (unitName1.equals("Kilogram") && unitName2.equals("Gram")) {multiplier = new BigDecimal("1000.000000"); }
        if (unitName1.equals("Gram") && unitName2.equals("Kilogram")) {multiplier = new BigDecimal("0.000100"); }

        //Kilogram and Ounce
        if (unitName1.equals("Kilogram") && unitName2.equals("Ounce")) {multiplier = new BigDecimal("35.2739619"); }
        if (unitName1.equals("Ounce") && unitName2.equals("Kilogram")) {multiplier = new BigDecimal("0.028349523125"); }

        //Kilogram and Stone
        if (unitName1.equals("Kilogram") && unitName2.equals("Stone")) {multiplier = new BigDecimal("0.1763698097479"); }
        if (unitName1.equals("Stone") && unitName2.equals("Kilogram")) {multiplier = new BigDecimal("5.669904625"); }

        //Gram and Ounce
        if (unitName1.equals("Gram") && unitName2.equals("Ounce")) {multiplier = new BigDecimal("0.03527397"); }
        if (unitName1.equals("Ounce") && unitName2.equals("Gram")) {multiplier = new BigDecimal("28.34952314877"); }

        //Gram and Stone
        if (unitName1.equals("Gram") && unitName2.equals("Stone")) {multiplier = new BigDecimal("0.0001763698097479"); }
        if (unitName1.equals("Stone") && unitName2.equals("Gram")) {multiplier = new BigDecimal("5669.904625"); }

        //Ounce and Stone
        if (unitName1.equals("Ounce") && unitName2.equals("Stone")) {multiplier = new BigDecimal("0.0044643"); }
        if (unitName1.equals("Stone") && unitName2.equals("Ounce")) {multiplier = new BigDecimal("224.00"); }

        //Decimeter
        if (unitName1.equals("Decimeter") && unitName2.equals("Meter")) {multiplier = new BigDecimal("0.1"); }
        if (unitName1.equals("Meter") && unitName2.equals("DeciMeter")) {multiplier = new BigDecimal("10"); }
        if (unitName1.equals("Decimeter") && unitName2.equals("Kilometer")) {multiplier = new BigDecimal("0.0001"); }
        if (unitName1.equals("Kilometer") && unitName2.equals("Decimeter")) {multiplier = new BigDecimal("10000"); }
        //Meter
        if (unitName1.equals("Meter") && unitName2.equals("Kilometer")) {multiplier = new BigDecimal("0.001"); }
        if (unitName1.equals("Kilometer") && unitName2.equals("Meter")) {multiplier = new BigDecimal("1000"); }

        //Second
        if (unitName1.equals("Second") && unitName2.equals("Minute")) {multiplier = new BigDecimal("0.01666666"); }
        if (unitName1.equals("Minute") && unitName2.equals("Second")) {multiplier = new BigDecimal("60"); }
        if (unitName1.equals("Second") && unitName2.equals("Hour")) {multiplier = new BigDecimal("0.0002777777"); }
        if (unitName1.equals("Hour") && unitName2.equals("Second")) {multiplier = new BigDecimal("3600"); }
        //Minute
        if (unitName1.equals("Minute") && unitName2.equals("Hour")) {multiplier = new BigDecimal("0.0166666666"); }
        if (unitName1.equals("Hour") && unitName2.equals("Minute")) {multiplier = new BigDecimal("60"); }

        //Meters/Second
        if (unitName1.equals("Meters/Second") && unitName2.equals("Meters/Minute")) {multiplier = new BigDecimal("0.016666666"); }
        if (unitName1.equals("Meters/Minute") && unitName2.equals("Meters/Second")) {multiplier = new BigDecimal("60");}
        if (unitName1.equals("Meters/Second") && unitName2.equals("Kilometers/Hour")) {multiplier = new BigDecimal("3.6"); }
        if (unitName1.equals("Kilometers/Hour") && unitName2.equals("Meters/Second")) {multiplier = new BigDecimal("0.27777777");}
        //Meters/Minute
        if (unitName1.equals("Meters/Minute") && unitName2.equals("Kilometers/Hour")) {multiplier = new BigDecimal("216");}
        if (unitName1.equals("Kilometers/Hour") && unitName2.equals("Meters/Minute")) {multiplier = new BigDecimal("0.0046296296");}

        return  multiplier;
    }


    //Initialise Units randomly
    public static Unit randomUnit(String message) {
        ArrayList<Unit> units = new ArrayList<>();
        Unit unit = new Unit("","","");

        if (message.equals("WEIGHT")) {
            units = Unit.getWeightUnits();
            Random r = new Random();
            int i = r.nextInt(units.size());
            unit = units.get(i);

        } else if (message.equals("LENGTH")) {
            units = Unit.getLengthUnits();
            Random r = new Random();
            int i = r.nextInt(units.size());
            unit = units.get(i);

        } else if (message.equals("TIME")) {
            units = Unit.getTimeUnits();
            Random r = new Random();
            int i = r.nextInt(units.size());
            unit = units.get(i);

        } else if (message.equals("VELOCITY")) {
            units = Unit.getVelocityUnits();
            Random r = new Random();
            int i = r.nextInt(units.size());
            unit = units.get(i);

        }

        return unit;
    }


}
