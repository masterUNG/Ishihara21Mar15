package appewtc.masterung.ishihara21mar15;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ShowScoreActivity extends ActionBarActivity {

    private TextView txtShowScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        //Initial Widget
        txtShowScore = (TextView) findViewById(R.id.txtShowScore);

        //Setup TextView
        setUpTextView();

    }   // onCreate

    public void clickPlay(View view) {
        Intent objIntent = new Intent(ShowScoreActivity.this, MainActivity.class);
        startActivity(objIntent);
        finish();
    }

    public void clickExit(View view) {
        finish();
    }

    private void setUpTextView() {
        int intMyScore = getIntent().getExtras().getInt("Score");
        txtShowScore.setText(Integer.toString(intMyScore) + "/10");
    }   // setUpTextView


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_score, menu);
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
}   // Main Class
