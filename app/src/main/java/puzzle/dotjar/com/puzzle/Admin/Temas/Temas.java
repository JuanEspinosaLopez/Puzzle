package puzzle.dotjar.com.puzzle.Admin.Temas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import puzzle.dotjar.com.puzzle.R;

public class Temas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_temas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Registrar tema").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {


                Dialog dialog=null;
                AlertDialog.Builder builder=new AlertDialog.Builder(Temas.this);
                builder.setView(R.layout.admin_temas_alta_tema);
                dialog=builder.create();
                dialog.show();

                return true;
            }
        });
        return true;
    }

}
