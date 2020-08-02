package myfarm.ma7moud3ly.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class AddExpenses extends AppCompatActivity {

    private EditText date, fixed_labor, dynamic_labour, machines, service,
            fertilization, insecticides, irrigation, electricity, fuels, seeds,
            transportation, extras;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);
        Intent intent = getIntent();
        if (!intent.hasExtra("id")) finish();
        id = intent.getStringExtra("id");
        if (id.equals("")) finish();

        date = findViewById(R.id.date);
        fixed_labor = findViewById(R.id.fixed_labor);
        dynamic_labour = findViewById(R.id.dynamic_labour);
        machines = findViewById(R.id.machines);
        service = findViewById(R.id.service);
        fertilization = findViewById(R.id.fertilization);
        insecticides = findViewById(R.id.insecticides);
        irrigation = findViewById(R.id.irrigation);
        electricity = findViewById(R.id.electricity);
        fuels = findViewById(R.id.fuels);
        seeds = findViewById(R.id.seeds);
        transportation = findViewById(R.id.transportation);
        extras = findViewById(R.id.extras);


    }

    public void back(View v) {
        finish();
    }

    public void save_expenses(View v) {
        Cultivation.expensesDB.execSQL("INSERT INTO table_" + id + "(date,fixed_labor,dynamic_labour,machines," +
                "service,fertilization,insecticides,irrigation,electricity,fuels,seeds,transportation,extras)" +
                " VALUES(" + "'" + date.getText() + "'," + "'" + fixed_labor.getText() + "'," + "'" + dynamic_labour.getText() +
                "'," + "'" + machines.getText() + "'," + "'" + service.getText() + "'," + "'" + fertilization.getText() + "'," +
                "'" + insecticides.getText() + "'," + "'" + irrigation.getText() + "'," + "'" + electricity.getText() + "'," +
                "'" + fuels.getText() + "'," + "'" + seeds.getText() + "'," + "'" + transportation.getText() + "'," +
                "'" + extras.getText() + "'" + ");");
        finish();
    }

}