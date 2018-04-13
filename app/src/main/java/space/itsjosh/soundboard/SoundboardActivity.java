package space.itsjosh.soundboard;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoundboardActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private enum Sounds {
        audio1(),
        audio2(),
        audio3(),
        audio4(),
        audio5()
    }

    Toolbar toolbar;

    ArrayList<SoundObject> soundList = new ArrayList<>();

    RecyclerView SoundView;
    SoundboardRecyclerAdapter SoundAdapter = new SoundboardRecyclerAdapter(soundList);
    RecyclerView.LayoutManager SoundLayoutManager;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        View mLayout = findViewById(R.id.activity_soundboard);

        toolbar = findViewById(R.id.soundboard_toolbar);

        setSupportActionBar(toolbar);

        //Grab our sound string resources from the string.xml file
        List<String> nameList = Arrays.asList(getResources().getStringArray(R.array.soundNames));

        // Parse mp3's and resource names into a tidy object passed system

        int cIndex = 0;
        int tIndex = Sounds.values().length;

        ArrayList<SoundObject> soundItems = new ArrayList<>();

        while (cIndex != tIndex - 1) {
            int aIdInt = cIndex + 1;
            String aId = String.valueOf(aIdInt);
            if (aIdInt < 10)
                aId = "0" + aId;
            soundItems.add(new SoundObject(nameList.get(cIndex), getResources().getIdentifier("raw/audio" + aId, "raw", this.getPackageName())));
            Log.i("SoundLoader", "Loaded Sound: " + aId + ". With Name: " + nameList.get(cIndex));
            cIndex++;
        }


//        SoundObject[] soundItems = {
//                new SoundObject(nameList.get(0), R.raw.audio01),
//                new SoundObject(nameList.get(1), R.raw.audio01),
//                new SoundObject(nameList.get(2), R.raw.audio02),
//                new SoundObject(nameList.get(3), R.raw.audio04),
//                new SoundObject(nameList.get(4), R.raw.audio03),
//                new SoundObject(nameList.get(5), R.raw.audio04),
//                new SoundObject(nameList.get(6), R.raw.audio05),
//                new SoundObject(nameList.get(7), R.raw.audio08)};

        soundList.addAll(soundItems);

        SoundView = findViewById(R.id.soundboardRecyclerView);

        SoundLayoutManager = new GridLayoutManager(this, 3);

        SoundView.setLayoutManager(SoundLayoutManager);

        SoundView.setAdapter(SoundAdapter);

        final Snackbar snackbar = Snackbar.make(mLayout, "Thanks for using the Soundboard!", Snackbar.LENGTH_INDEFINITE);
        snackbar.show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                snackbar.dismiss();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventHandlerClass.releaseMediaPlayer();
    }
}
