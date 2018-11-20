package puzzle.dotjar.com.puzzle;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
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
    LinkedList<Fragment> fragmentLinkedList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        contenedor=findViewById(R.id.contenedor_principal);
        btnRegresar=findViewById(R.id.menu_principal_back);
        fragmentLinkedList=new LinkedList<>();
        btnRegresar.setVisibility(View.INVISIBLE);
        cambiarFragment(new Menu());
    }
    void cambiarFragment(Fragment fragment)
    {
        fragmentLinkedList.add(fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_principal, fragment).commit();
        if(fragmentLinkedList.size()>1)
            btnRegresar.setVisibility(View.INVISIBLE);
    }


    public void regresarFragment(View view)
    {

        Animation animation= new AlphaAnimation(1, 0);
        animation.setDuration(600);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                if(fragmentLinkedList.size()>1)
                {
                    fragmentLinkedList.pop();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_principal, fragmentLinkedList.getLast()).commit();
                }
                if(fragmentLinkedList.size()<=1)
                    btnRegresar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        contenedor.setAnimation(animation);
        animation=new AlphaAnimation(0, 1);
        animation.setDuration(600);
        animation.setFillAfter(true);
        contenedor.setAnimation(animation);



    }

}
