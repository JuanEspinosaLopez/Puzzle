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
        Typeface iconFont = AdministradorFuentes.getTypeface(getContext(), AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(getActivity().findViewById(R.id.menu_principal_fragment), iconFont);


        Button verPuzzles=getActivity().findViewById(R.id.verPuzzles);
        Button verEstadisticas=getActivity().findViewById(R.id.verEstadisticas);
        Button verAjustes=getActivity().findViewById(R.id.verAjustes);
        Animation animation=obtenerAnimacion(-.5f, .5f);

        verPuzzles.setAnimation(animation);
        verAjustes.setAnimation(animation);

        animation=obtenerAnimacion(.5f, -.5f);
        verEstadisticas.setAnimation(animation);

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
