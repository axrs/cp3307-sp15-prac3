package ninja.terrorbyte.cp3307sp15_prac3;

import android.util.Log;

public class Settings implements RangeSettings {
    private int lowerBounds = 1;
    private int upperBounds = 10;

    private static Settings instance = null;

    //Protected constructor to prevent overriding and extension
    protected Settings() {

    }

    //Singleton pattern
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
            Log.d("Settings", String.format("Creating Singleton"));
        }
        Log.d("Settings", String.format("Returning Singleton"));

        return instance;
    }

    @Override
    public boolean setRange(int min, int max) {

        //Compare min to max.  If min is lower, swap :)

        Log.d("Settings", String.format("Trying to set Range: %d-%d", min, max));

        if (min < max) {
            setLowerBounds(min);
            setUpperBounds(max);
            return true;
        } else if (max < min) {
            setUpperBounds(min);
            setLowerBounds(max);
            return true;
        }

        //values are equal, invalid range.
        return false;
    }

    public void setLowerBounds(int lowerBounds) {
        this.lowerBounds = lowerBounds;
    }

    public void setUpperBounds(int upperBounds) {
        this.upperBounds = upperBounds;
    }

    @Override
    public int getUpperBounds() {
        return upperBounds;
    }

    @Override
    public int getLowerBounds() {
        return lowerBounds;
    }
}
