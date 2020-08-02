package myfarm.ma7moud3ly.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCultivation extends AppCompatActivity {

    private EditText type, acres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cultivation);
        type = findViewById(R.id.type);
        acres = findViewById(R.id.acres);

    }

    public void save_cultivation(View v) {
        Cultivation.expensesDB.execSQL("INSERT INTO tables(type,acres) VALUES('" + type.getText() + "','" + acres.getText() + "');");
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.done_saving), Toast.LENGTH_SHORT).show();
        finish();
    }

    public void back(View v) {
        finish();
    }


}