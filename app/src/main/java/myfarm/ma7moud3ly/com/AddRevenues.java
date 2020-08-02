package myfarm.ma7moud3ly.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class AddRevenues extends AppCompatActivity {

    private EditText date, kilo, qunatity, discounts;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_revenues);
        Intent intent = getIntent();
        if (!intent.hasExtra("id")) finish();
        id = intent.getStringExtra("id");
        if (id.equals("")) finish();

        date = findViewById(R.id.date);
        kilo = findViewById(R.id.kilo);
        qunatity = findViewById(R.id.quantity);
        discounts = findViewById(R.id.discounts);

    }

    public void back(View v) {
        finish();
    }

    public void save_revenues(View v) {
        Cultivation.revenuesDB.execSQL("INSERT INTO table_" + id + "(date,kilo,quantity,discounts)" +
                " VALUES('" + date.getText() + "','" + kilo.getText() + "','" + qunatity.getText() +
                "','" + discounts.getText() + "' );");
        finish();
    }

}