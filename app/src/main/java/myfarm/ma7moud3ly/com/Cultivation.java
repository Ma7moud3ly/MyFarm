package myfarm.ma7moud3ly.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Cultivation extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CultivationAdapter recyclerViewAdapter;
    private static ArrayList<ArrayList<String>> list = new ArrayList<>();
    private Intent expensesIntent, revenuesIntent;
    private Cursor cursor;
    public static SQLiteDatabase expensesDB, revenuesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cultivation);

        expensesIntent = new Intent(this, Expenses.class);
        revenuesIntent = new Intent(this, Revenues.class);

        expensesDB = openOrCreateDatabase("expenses_db", MODE_PRIVATE, null);
        revenuesDB = openOrCreateDatabase("revenues_db", MODE_PRIVATE, null);

        recyclerView = findViewById(R.id.cultivation_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter = new CultivationAdapter(list, this);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.addOnItemTouchListener(new
                RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (list == null) return;
                ArrayList<String> s = list.get(position);
                String id = s.get(0);
                String name = s.get(1) + " - " + s.get(2) + " فدان";
                show(id, name);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        expensesDB.execSQL("CREATE TABLE IF NOT EXISTS tables(id integer primary key autoincrement," +
                "type VARCHAR,acres int(255));");

    }

    @Override
    public void onResume() {
        try {
            cursor = expensesDB.rawQuery("select * from 'tables'", null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        list.clear();
        while (cursor.moveToNext()) {
            ArrayList<String> s = new ArrayList<>();
            s.add("" + cursor.getInt(0));
            s.add(cursor.getString(1));
            s.add("" + cursor.getInt(2));
            list.add(s);
        }
        recyclerViewAdapter.notifyDataSetChanged();
        super.onResume();
    }

    public void add_cultivation(View v) {
        startActivity(new Intent(this, AddCultivation.class));
    }

    private void show(final String id, final String name) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setMessage(name);
        ad.setPositiveButton(getResources().getString(R.string.expenses), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                expensesIntent.putExtra("id", id);
                expensesIntent.putExtra("name", name);
                startActivity(expensesIntent);
            }
        });
        ad.setNegativeButton(getResources().getString(R.string.revenues), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                revenuesIntent.putExtra("id", id);
                revenuesIntent.putExtra("name", name);
                startActivity(revenuesIntent);
            }
        });
        ad.show();
    }

}