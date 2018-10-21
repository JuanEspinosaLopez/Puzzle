package puzzle.dotjar.com.puzzle.Admin;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TableLayout;
import android.widget.TableRow;

import puzzle.dotjar.com.puzzle.AdministradorFuentes;
import puzzle.dotjar.com.puzzle.R;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);
        Typeface iconFont = AdministradorFuentes.getTypeface(getApplicationContext(), AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(findViewById(R.id.admin_table_layout), iconFont);





    }
}
