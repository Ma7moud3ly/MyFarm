package myfarm.ma7moud3ly.com;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Expenses extends AppCompatActivity {

    private Cursor cursor;
    private TextView name, total_expenses;
    private String id;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);
        name = findViewById(R.id.cultivation_name);
        total_expenses = findViewById(R.id.total_expenses);

        Intent intent = getIntent();
        if (!intent.hasExtra("id") || !intent.hasExtra("name")) finish();
        name.setText(getResources().getString(R.string.expenses) + " : " + intent.getStringExtra("name"));
        id = intent.getStringExtra("id");

        Cultivation.expensesDB.execSQL("CREATE TABLE IF NOT EXISTS table_" + id + "(id integer primary key autoincrement,date VARCHAR,fixed_labor NUMERIC," +
                "dynamic_labour NUMERIC,machines NUMERIC,service NUMERIC,fertilization NUMERIC,insecticides NUMERIC," +
                "irrigation NUMERIC,electricity NUMERIC,fuels NUMERIC,seeds NUMERIC,transportation NUMERIC,extras NUMERIC);");
        fetch();
    }

    private void fetch() {
        try {
            cursor = Cultivation.expensesDB.rawQuery("select * from 'table_" + id + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        table = findViewById(R.id.expenses_table);

        Double total = 0.0;
        while (cursor.moveToNext()) {
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.expenses_item, null);
            String date = cursor.getString(1);
            ((TextView) row.findViewById(R.id.date_item)).setText(date);

            Double x2 = cursor.getDouble(2);
            ((TextView) row.findViewById(R.id.fixed_labour_item)).setText("" + x2);

            Double x3 = cursor.getDouble(3);
            ((TextView) row.findViewById(R.id.dynamic_labour_item)).setText("" + x3);

            Double x4 = cursor.getDouble(4);
            ((TextView) row.findViewById(R.id.machines_item)).setText("" + x4);

            Double x5 = cursor.getDouble(5);
            ((TextView) row.findViewById(R.id.service_item)).setText("" + x5);

            Double x6 = cursor.getDouble(6);
            ((TextView) row.findViewById(R.id.fertilization_item)).setText("" + x6);

            Double x7 = cursor.getDouble(7);
            ((TextView) row.findViewById(R.id.insecticides_item)).setText("" + x7);

            Double x8 = cursor.getDouble(8);
            ((TextView) row.findViewById(R.id.irrigation_item)).setText("" + x8);

            Double x9 = cursor.getDouble(9);
            ((TextView) row.findViewById(R.id.electricity_item)).setText("" + x9);

            Double x10 = cursor.getDouble(10);
            ((TextView) row.findViewById(R.id.fuels_item)).setText("" + x10);

            Double x11 = cursor.getDouble(11);
            ((TextView) row.findViewById(R.id.seeds_item)).setText("" + x11);

            Double x12 = cursor.getDouble(12);
            ((TextView) row.findViewById(R.id.transportation_item)).setText("" + x12);

            Double x13 = cursor.getDouble(13);
            ((TextView) row.findViewById(R.id.extras_item)).setText("" + x13);

            Double x = x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13;
            total += x;
            ((TextView) row.findViewById(R.id.sub_total_item)).setText("" + x);

            TextView tv = ((TextView) row.findViewById(R.id.delete_item));
            tv.setTag(cursor.getDouble(0));
            tv.setVisibility(View.VISIBLE);
            tv.setOnClickListener(del_expense);
            table.addView(row);
        }

        total_expenses.setText("المصروفات الكلية : " + total);

    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    public void add_expenses(View v) {
        Intent intent = new Intent(this, AddExpenses.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void del_cultivation(View v) {
        Cultivation.expensesDB.execSQL("DELETE FROM tables WHERE id='" + id + "'");
        Cultivation.expensesDB.execSQL("DROP TABLE IF EXISTS table_" + id);
        Cultivation.revenuesDB.execSQL("DROP TABLE IF EXISTS table_" + id);

        finish();
    }

    private final View.OnClickListener del_expense = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String item_id = ((TextView) view).getTag().toString();
            Cultivation.expensesDB.execSQL("DELETE FROM table_" + id + " WHERE id='" + item_id + "'");
            recreate();
        }
    };

}