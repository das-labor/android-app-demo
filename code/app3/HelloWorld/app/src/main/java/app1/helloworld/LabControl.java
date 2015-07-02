package app1.helloworld;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import de.hansinator.automation.lab.Labor;

public class LabControl extends ActionBarActivity {

    final String LOG_TAG = "LabControl";
    final Labor lab = Labor.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foo);

        lab.start(10000, true);

        final Button buttonOn = (Button) findViewById(R.id.button_on);
        buttonOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lab.lm.powerCommander.switchLectureAll(true);
            }
        });

        final Button buttonOff = (Button) findViewById(R.id.button_off);
        buttonOff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lab.lm.powerCommander.switchLectureAll(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Labor.getInstance().stop();
    }
}
