package appewtc.masterung.ishihara21mar15;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView txtQuestion;
    private ImageView imvIshihara;
    private RadioGroup ragChoice;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnAnswer;
    private int intRadio, intIndex, intScore;
    private MyModel objMyModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Widget
        initialWidget();

        //Create Button Controller
        buttonController();

        //Create Radio Controller
        radioController();

        //Receive Interface
        receiveInterface();

    }   // onCreate

    private void receiveInterface() {
        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                //Change View
                changeView(myModel.getIntButton());

            }   // event
        });
    }   // receiveInterface

    private void changeView(int intButton) {

        //Change Image
        int intImage[] = new int[]{R.drawable.ishihara_01, R.drawable.ishihara_02,
                R.drawable.ishihara_03, R.drawable.ishihara_04,
                R.drawable.ishihara_05, R.drawable.ishihara_06,
                R.drawable.ishihara_07, R.drawable.ishihara_08,
                R.drawable.ishihara_09, R.drawable.ishihara_10};

        imvIshihara.setImageResource(intImage[intButton]);

        //Change Choice
        int intTimes[] = new int[]{R.array.times1, R.array.times2,
                R.array.times3, R.array.times4,
                R.array.times5, R.array.times6,
                R.array.times7, R.array.times8,
                R.array.times9, R.array.times10};
        String strChoice[] = new String[4];
        strChoice = getResources().getStringArray(intTimes[intButton]);
        radChoice1.setText(strChoice[0]);
        radChoice2.setText(strChoice[1]);
        radChoice3.setText(strChoice[2]);
        radChoice4.setText(strChoice[3]);
    }   // changeView



    private void radioController() {
        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                MediaPlayer objMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                objMediaPlayer.start();

                //setup intRadio
                switch (i) {
                    case R.id.radioButton:
                        intRadio = 1;
                        break;
                    case R.id.radioButton2:
                        intRadio = 2;
                        break;
                    case R.id.radioButton3:
                        intRadio = 3;
                        break;
                    case R.id.radioButton4:
                        intRadio = 4;
                        break;
                    default:
                        intRadio = 0;
                        break;
                }   // switch

            }   // event
        });
    }   // radioController

    private void buttonController() {
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Sound Effect
                MediaPlayer objMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                objMediaPlayer.start();

                //Check Answer
                checkAnswer();


            }   // event
        });
    }   // buttonController

    private void checkAnswer() {
        if (intRadio == 0) {
            Toast.makeText(MainActivity.this, "กรุณาตอบคำถาม นะคะ", 5000).show();
        } else {

            //Check Score
            checkScore();

            //Check Times
            checkTimes();

        }
    }   // checkAnswer

    private void checkScore() {
        int intAnswer[] = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 4, 4};
        int intUser[] = new int[10];
        intUser[intIndex] = intRadio;
        if (intUser[intIndex] == intAnswer[intIndex]) {
            intScore++;
        }
    }   // checkScore

    private void checkTimes() {
        if (intIndex == 9) {
            //Intent to ShowScore
            Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
            objIntent.putExtra("Score", intScore);
            startActivity(objIntent);
            finish();
        } else {
            //Call View
            txtQuestion.setText(Integer.toString(intIndex + 2) + ". What is this ?");
            intIndex += 1;

            //Call Model
            objMyModel.setIntButton(intIndex);

        }
    }   // checkTimes

    private void initialWidget() {
        txtQuestion = (TextView) findViewById(R.id.textView2);
        imvIshihara = (ImageView) findViewById(R.id.imageView);
        ragChoice = (RadioGroup) findViewById(R.id.ragChoice);
        radChoice1 = (RadioButton) findViewById(R.id.radioButton);
        radChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        radChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        radChoice4 = (RadioButton) findViewById(R.id.radioButton4);
        btnAnswer = (Button) findViewById(R.id.button);
    }   // initialWidget


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemAboutMe:
                Intent aboutIntent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.itemHowTo:
                Intent howIntent = new Intent(MainActivity.this, HowToActivity.class);
                startActivity(howIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
