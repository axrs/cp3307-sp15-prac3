package ninja.terrorbyte.cp3307sp15_prac3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class GuessActivity extends ActionBarActivity {

    //Aims of the GuessActivity
    //1. Generate a random number
    //2. Get a users guess value
    //3. Compare users value to generated number
    //4. Open settings

    //Lets start with Random number.

    private Random random;
    private int randomNumber;
    private EditText userInput;

    //Works because of interface.
    private RangeSettings settings = Settings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        generateRandomNumber();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guess, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Random getRandom() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }

    private EditText getUserInput() {
        if (userInput == null) {
            userInput = (EditText) findViewById(R.id.userGuessInput);
        }
        if (userInput == null) {
            //Break the application on purpose!
            throw new NullPointerException();
        }
        return userInput;
    }


    private void generateRandomNumber() {

        randomNumber = settings.getLowerBounds() +
                getRandom().nextInt(settings.getUpperBounds() - settings.getLowerBounds());

        Log.d("GuessActivity", String.format("\tNew Random Number is: %d", randomNumber));
    }

    public void clearTextForEntry(View view) {
        //Picky android... EditText is an extension of view...
        ((EditText) view).setText("");
    }

    public void submitGuess(View view) {
        //Get user guess as integer
        //Compare to random number
        //If win, Buttery toast!
        //If loss, dry toast.

        int userGuess = Integer.valueOf(getUserInput().getText().toString());

        Toast t = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        if (userGuess == randomNumber) {
            t.setText("Congratulations!");
            //Generate a new random number as they won!
            generateRandomNumber();
        } else {
            t.setText("Nope. Nup. Nada.");
        }
        t.show();
    }


    public void onSettingsClicked(MenuItem item) {
        //No bundle passed.
        Intent i = new Intent(this, SettingsActivity.class);

        //Set the request code to 0 to use the 'onActivityResult' method.
        startActivityForResult(i, 0);
    }

    //What happens when we leave the settings? We need to update the range if changed.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("GuessActivity", "Activity Result Returned");
        if (resultCode == RESULT_OK) {
            //User changes settings correctly
            Log.d("GuessActivity", "Generating new number for changed settings");
            generateRandomNumber();
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
