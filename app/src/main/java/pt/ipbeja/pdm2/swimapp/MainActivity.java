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

    public int pos = 0;
    static String phoneNumber = "tel:214 158 190";

    public void onProofSelected(int position) {

        if (findViewById(R.id.fragment_container) != null){


            SwimFragment swimFragment = new SwimFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            swimFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, swimFragment);
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

        if( savedInstanceState == null) {
            Initialize();
        }

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            TitlesFragment firstFragment = new TitlesFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
        if (findViewById(R.id.titles_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }
            TitlesFragment firstFragment = new TitlesFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.titles_fragment, firstFragment).commit();
        }
    }

    public void Initialize(){
        DBHelper db = new DBHelper(this);
        SwimData data = new SwimData();
        if(db.getAllNames().size() == 0) {
            for (int i = 0; i < SwimData.Titles.length - 1; i++) {
                db.insertProofs(SwimData.Titles[i], SwimData.Description[i], SwimData.Photos[i], SwimData.Gps[i]);
            }
        }

        SwimData.DBTitles = db.getAllNames();
        SwimData.DBDescription = db.getAllDescriptions();
        SwimData.DBPhotos = db.getAllPhotos();
        SwimData.DBGps = db.getAllGps();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void btnGps_onClick(View view) {

        Uri map = Uri.parse(SwimData.DBGps.get(pos));
        Intent callMap = new Intent(Intent.ACTION_VIEW, map);
        startActivity(callMap);
    }

    public void btnCall_onClick(View view) {
        Uri number = Uri.parse(phoneNumber);
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

        startActivity(Intent.createChooser(intent, "Inscrição de Prova"));
    }

    public static String testPhoneNumber(){
        return phoneNumber;
    }
}
