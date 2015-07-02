package app1.helloworld;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import de.hansinator.automation.lab.Labor;

public class LabControl extends ActionBarActivity {

    final String LOG_TAG = "LabControl";
    final Labor lab = Labor.getInstance();
    private SeekBar lightControl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foo);

        lab.start(10000, true);

        lightControl = (SeekBar) findViewById(R.id.loungeSeek);

        lightControl.setProgress(lab.lm.bastelControl.getPwmVals()[0]);

        lightControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                lab.lm.bastelControl.dimBastelAll(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


        final Button buttonOn = (Button) findViewById(R.id.button_on);
        buttonOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lab.lm.bastelControl.switchBastelAll(true);
            }
        });

        final Button buttonOff = (Button) findViewById(R.id.button_off);
        buttonOff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lab.lm.bastelControl.switchBastelAll(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Labor.getInstance().stop();
    }
}
