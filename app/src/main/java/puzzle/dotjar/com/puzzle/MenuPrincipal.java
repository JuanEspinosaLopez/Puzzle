package puzzle.dotjar.com.puzzle;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.LinkedList;

public class MenuPrincipal extends AppCompatActivity
{
    FrameLayout contenedor;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }
        contenedor=findViewById(R.id.contenedor_principal);
        btnRegresar=findViewById(R.id.menu_principal_back);

        btnRegresar.setVisibility(View.INVISIBLE);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.contenedor_principal, new Menu()).commit();
    }
    void cambiarFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contenedor_principal, fragment).addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount()>0)
            getSupportFragmentManager().popBackStack();

    }
    void aplicarFontAwesome(int id)
    {
        Typeface iconFont = AdministradorFuentes.getTypeface(this, AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(findViewById(id), iconFont);
    }


}
