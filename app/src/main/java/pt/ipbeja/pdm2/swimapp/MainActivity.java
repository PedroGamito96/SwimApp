package pt.ipbeja.pdm2.swimapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity implements
        TitlesFragment.OnTitleSelectedListener {

    public int pos;

    public void onProofSelected(int position) {

        if (findViewById(R.id.fragment_container) != null){


            SwimFragment musicFragment = new SwimFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            musicFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, musicFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        else{

            SwimFragment swimFrag = (SwimFragment)
                    getSupportFragmentManager().findFragmentById(R.id.swim_fragment);
            swimFrag.updateDescriptionView(position);

        }

        pos = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            if( savedInstanceState == null) {
                //Initialize();
                //NewsData.ListArticles();

            /*Log.d("MainActivity:",
                    Integer.toString(NewsData.GetNumberOfArticles()));*/
            }

            // Create a new Fragment to be placed in the activity layout
            TitlesFragment firstFragment = new TitlesFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }

    }

    //public void Initialize(){
        //DBHelper db = new DBHelper(this);
        //db.insertProofs(SwimData.Titles, SwimData.Description, );
    //}

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void btnGps_onClick(View view) {
        Intent map = new Intent(MainActivity.this, GpsActivity.class);
        map.putExtra("gps", SwimData.Gps[pos]);
        startActivity(map);
    }

    public void btnCall_onClick(View view) {
        Uri number = Uri.parse("tel:214 158 190");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void btnEmail_onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","secretaria@fpnatacao.pt", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Inscrição numa prova de natação");
        intent.putExtra(Intent.EXTRA_TEXT, "Vim por este meio inscrever-me numa prova.");

        startActivity(Intent.createChooser(intent, "Report Problem"));
    }
}
