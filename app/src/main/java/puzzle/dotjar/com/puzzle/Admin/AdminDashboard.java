package puzzle.dotjar.com.puzzle.Admin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import puzzle.dotjar.com.puzzle.Admin.Temas.Temas;
import puzzle.dotjar.com.puzzle.AdministradorFuentes;
import puzzle.dotjar.com.puzzle.R;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);
        Typeface iconFont = AdministradorFuentes.getTypeface(getApplicationContext(), AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(findViewById(R.id.admin_table_layout), iconFont);
        findViewById(R.id.admin_temas).setOnClickListener(new ClickCard(new Intent(this, Temas.class)));
        findViewById(R.id.admin_articulos).setOnClickListener(new ClickCard(new Intent(this, AdminDashboard.class)));
        findViewById(R.id.admin_instrucciones).setOnClickListener(new ClickCard(new Intent(this, AdminDashboard.class)));
        findViewById(R.id.admin_po).setOnClickListener(new ClickCard(new Intent(this, AdminDashboard.class)));
        findViewById(R.id.admin_ps).setOnClickListener(new ClickCard(new Intent(this, AdminDashboard.class)));
        findViewById(R.id.admin_puml).setOnClickListener(new ClickCard(new Intent(this, AdminDashboard.class)));
        findViewById(R.id.admin_graficas).setOnClickListener(new ClickCard(new Intent(this, AdminDashboard.class)));
        findViewById(R.id.admin_reportes).setOnClickListener(new ClickCard(new Intent(this, AdminDashboard.class)));
    }

    class ClickCard implements View.OnClickListener
    {
        Intent intent;

        public ClickCard(Intent intent)
        {
            this.intent = intent;
        }

        @Override
        public void onClick(View v)
        {
            startActivity(intent);
        }
    }

}
