package puzzle.dotjar.com.puzzle;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class Menu extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.menu_principal_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MenuPrincipal menuPrincipal=(MenuPrincipal)getActivity();
        menuPrincipal.aplicarFontAwesome(R.id.menu_principal_fragment);

        //accion de botones
        Button verPuzzles=getActivity().findViewById(R.id.verPuzzles);
        Button verEstadisticas=getActivity().findViewById(R.id.verEstadisticas);
        Button verAjustes=getActivity().findViewById(R.id.verAjustes);

        verAjustes.setOnClickListener(new ButtonClick(new Ajustes()));


        //animar

        Animation animation=menuPrincipal.obtenerAnimacion(-.5f, .5f);

        verPuzzles.setAnimation(animation);
        verAjustes.setAnimation(animation);

        animation=menuPrincipal.obtenerAnimacion(.5f, -.5f);
        verEstadisticas.setAnimation(animation);

    }


    class ButtonClick implements View.OnClickListener
    {
        Fragment fragment;

        public ButtonClick(Fragment fragment)
        {
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v)
        {
            MenuPrincipal actividad= (MenuPrincipal) getActivity();
            actividad.cambiarFragment(this.fragment);
        }
    }

}
