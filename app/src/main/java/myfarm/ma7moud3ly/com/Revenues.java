package myfarm.ma7moud3ly.com;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Revenues extends AppCompatActivity {

    private Cursor cursor;
    private TextView name, total;
    private String id;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revenues);
        name = findViewById(R.id.cultivation_name);
        total = findViewById(R.id.total_revenues);

        Intent intent = getIntent();
        if (!intent.hasExtra("id") || !intent.hasExtra("name")) finish();
        name.setText(getResources().getString(R.string.revenues) + " : " + intent.getStringExtra("name"));
        id = intent.getStringExtra("id");

        Cultivation.revenuesDB.execSQL("CREATE TABLE IF NOT EXISTS table_" + id +
                "(id integer primary key autoincrement,date VARCHAR,kilo NUMERIC,quantity NUMERIC,discounts NUMERIC);");
        fetch();
    }

    private void fetch() {
        try {
            cursor = Cultivation.revenuesDB.rawQuery("select * from 'table_" + id + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        table = findViewById(R.id.revenues_table);

        Double _total = 0.0;
        while (cursor.moveToNext()) {
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.revenues_item, null);
            String date = cursor.getString(1);
            ((TextView) row.findViewById(R.id.date_item)).setText(date);

            Double _kilo = cursor.getDouble(2);
            ((TextView) row.findViewById(R.id.kilo_item)).setText("" + _kilo);

            Double _quantity = cursor.getDouble(3);
            ((TextView) row.findViewById(R.id.quantity_item)).setText("" + _quantity);

            Double _discounts = cursor.getDouble(4);
            ((TextView) row.findViewById(R.id.discounts_item)).setText("" + _discounts);

            Double x = (_kilo * _quantity) - _discounts;
            _total += x;
            ((TextView) row.findViewById(R.id.sub_total_item)).setText("" + x);

            TextView tv = ((TextView) row.findViewById(R.id.delete_item));
            tv.setTag(cursor.getDouble(0));
            tv.setVisibility(View.VISIBLE);
            tv.setOnClickListener(del);
            table.addView(row);
        }

        total.setText("الإيرادات الكلية : " + _total);

    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    public void add_revenues(View v) {
        Intent intent = new Intent(this, AddRevenues.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void del_cultivation(View v) {
        Cultivation.expensesDB.execSQL("DELETE FROM tables WHERE id='" + id + "'");
        Cultivation.expensesDB.execSQL("DROP TABLE IF EXISTS table_" + id);
        Cultivation.revenuesDB.execSQL("DROP TABLE IF EXISTS table_" + id);
        finish();
    }

    private final View.OnClickListener del = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String item_id = ((TextView) view).getTag().toString();
            Cultivation.revenuesDB.execSQL("DELETE FROM table_" + id + " WHERE id='" + item_id + "'");
            recreate();
        }
    };

}