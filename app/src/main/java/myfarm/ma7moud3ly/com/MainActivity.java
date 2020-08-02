package myfarm.ma7moud3ly.com;

import android.app.Activity;
import android.app.TabActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Html;
import android.view.View;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    private TabHost tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init_tabs_section();
    }

    private void init_tabs_section() {
        tab = getTabHost();
        TabHost.TabSpec a1 = tab.newTabSpec("HowTo");
        a1.setIndicator(getResources().getString(R.string.app_name));
        Intent i1 = new Intent(this, Cultivation.class);
        a1.setContent(i1);

        TabHost.TabSpec a2 = tab.newTabSpec("about");
        a2.setIndicator(getResources().getString(R.string.about));
        Intent i2 = new Intent(this, About.class);
        a2.setContent(i2);


        tab.addTab(a1);
        tab.addTab(a2);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.aehor);
        for (int i = 0; i < tab.getTabWidget().getChildCount(); i++) {
            TextView tv = tab.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.app_color2));
            tv.setTypeface(typeface);
        }
    }
}


