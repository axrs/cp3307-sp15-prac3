package ninja.terrorbyte.cp3307sp15_prac3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //On load. Populate last used range settings
        EditText lowerBounds = (EditText)findViewById(R.id.lowerBoundsInput);
        EditText upperBounds = (EditText)findViewById(R.id.upperBoundsInput);

        lowerBounds.setText(String.valueOf(Settings.getInstance().getLowerBounds()));
        upperBounds.setText(String.valueOf(Settings.getInstance().getUpperBounds()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void clearTextEntry(View view) {
        ((EditText)view).setText("");
    }

    public void saveSettings(View view) {

        EditText lowerBounds = (EditText)findViewById(R.id.lowerBoundsInput);
        EditText upperBounds = (EditText)findViewById(R.id.upperBoundsInput);

        int lower = Integer.valueOf(lowerBounds.getText().toString());
        int upper = Integer.valueOf(upperBounds.getText().toString());

        if (Settings.getInstance().setRange(lower,upper)){
            //All is good, finished with settings activity
            Log.d("SettingsActivity", "Setting Result");
            setResult(RESULT_OK, null);
            finish();
        }else{
            //invalid range set... Prompt to try again
            Toast.makeText(this,"Invalid range options, please try again.",Toast.LENGTH_LONG).show();
        }
    }

}
