package puzzle.dotjar.com.puzzle;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Hashtable;

public class AdministradorFuentes
{
    //RUTA DE FONTAWESOME
    public static final String RAIZ="fonts/", FONTAWESOME = RAIZ+"fa-solid-900.ttf";
    //PARA INCREMENTAR EL RENDIMIENTO, USA CACHE
    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    //METODO PARA SACAR FUENTE
    public static Typeface getTypeface(Context context, String fuente)
    {
        Typeface tf = fontCache.get(fuente);
        if(tf == null)
        {
            try
            {
                tf = Typeface.createFromAsset(context.getAssets(), fuente);
            }
            catch (Exception e)
            {
                return null;
            }
            fontCache.put(fuente, tf);
        }
        return tf;
    }

    //METODO PARA ESTABLECER FUENTE
    public static void markAsIconContainer(View v, Typeface typeface) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                markAsIconContainer(child, typeface);
            }
        } else if (v instanceof TextView) {
            ((TextView) v).setTypeface(typeface);
        }
    }
}
