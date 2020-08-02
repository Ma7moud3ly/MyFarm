package myfarm.ma7moud3ly.com;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class About extends AppCompatActivity {
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about);

        findViewById(R.id.rate).setOnClickListener(view -> rate());
        findViewById(R.id.share).setOnClickListener(view -> share());
        findViewById(R.id.contact).setOnClickListener(view -> contact());

        version = findViewById(R.id.app_version);
        version.setText(" v " + version());

    }

    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + this.getPackageName());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.app_name)));
    }

    private void rate() {

        Uri uri = Uri.parse("FBD://details?id=" + this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }

    }

    private void contact() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        Uri d = Uri.parse("http://www.facebook.com/engma7moud3ly");
        i.setData(d);
        startActivity(i);
    }

    private String version() {
        String v = "1";
        PackageManager pm = getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(getPackageName(), 0);
            v = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return v;
    }

}