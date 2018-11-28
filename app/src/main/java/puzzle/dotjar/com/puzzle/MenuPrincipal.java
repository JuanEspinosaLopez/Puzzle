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
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MenuPrincipal extends AppCompatActivity
{
    FrameLayout contenedor;

    FirebaseAuth firebaseAuth;

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
        //TODO: CAMBIAR EL EMAIL Y PASSWORD POR LOS DEL LOGIN
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword("pablito@mail.com", "123456");
        contenedor=findViewById(R.id.contenedor_principal);



        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.contenedor_principal, new Menu()).commit();
    }
    void cambiarFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction()
                .replace(R.id.contenedor_principal, fragment).addToBackStack(null);
        desvanecerMenu(R.id.contenedor_principal, 1, 0);

        fragmentTransaction.commit();
        desvanecerMenu(R.id.contenedor_principal, 0, 1);
    }

    void desvanecerMenu(int idContenedor, int fromAlpha, int toAlpha)
    {
        Animation animation=new AlphaAnimation(fromAlpha, toAlpha);
        animation.setDuration(400);
        findViewById(idContenedor).setAnimation(animation);
        animation.start();


    }
    @Override
    public void onBackPressed()
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount()>0)
        {
            desvanecerMenu(R.id.contenedor_principal, 1, 0);
            getSupportFragmentManager().popBackStack();
            desvanecerMenu(R.id.contenedor_principal, 0, 1);
        }


    }
    void aplicarFontAwesome(int id)
    {
        Typeface iconFont = AdministradorFuentes.getTypeface(this, AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(findViewById(id), iconFont);
    }
    void aplicarFontAwesome(View v)
    {
        Typeface iconFont = AdministradorFuentes.getTypeface(this, AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(v, iconFont);
    }
    Animation obtenerAnimacion(float fromDegrees, float toDegrees)
    {
        Animation an = new RotateAnimation(fromDegrees, toDegrees, RotateAnimation.RELATIVE_TO_PARENT, .5f , RotateAnimation.RELATIVE_TO_PARENT, .5f );
        an.setDuration(600);
        an.setRepeatCount(-1);
        an.setRepeatMode(Animation.REVERSE);
        an.setFillAfter(true);
        return an;
    }
    Animation obtenerAnimacion(float fromDegrees, float toDegrees, long duration)
    {
        Animation an = new RotateAnimation(fromDegrees, toDegrees, RotateAnimation.RELATIVE_TO_PARENT, .5f , RotateAnimation.RELATIVE_TO_PARENT, .5f );
        an.setDuration(duration);
        an.setRepeatCount(-1);
        an.setRepeatMode(Animation.REVERSE);
        an.setFillAfter(true);
        return an;
    }

}
