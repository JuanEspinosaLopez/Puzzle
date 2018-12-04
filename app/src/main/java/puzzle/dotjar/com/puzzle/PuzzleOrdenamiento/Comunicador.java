package puzzle.dotjar.com.puzzle.PuzzleOrdenamiento;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import puzzle.dotjar.com.puzzle.Database;
import puzzle.dotjar.com.puzzle.Red;
import puzzle.dotjar.com.puzzle.SQLiteHelper;

public class Comunicador
{
    private Context contexto;

    private SQLiteDatabase sqLiteDatabase;
    private SQLiteHelper database;
    private String numeroNivel;
    private String tituloPR, salidaPR, puzzle;

    Comunicador(Context contexto, String numeroNivel, String tituloPR, String salidaPR)
    {
        this.contexto = contexto;
        this.numeroNivel=numeroNivel;
        this.tituloPR=tituloPR;
        this.salidaPR=salidaPR;
        this.database= new SQLiteHelper(contexto, "plaython.db", null, 1);
        this.sqLiteDatabase=database.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("SELECT * FROM instruccion_pr WHERE instruccion_pr.idPuzzleRecepcion='"+numeroNivel+"'", null);

        if(c.moveToFirst())
            this.puzzle=c.getString(1);

    }

    @JavascriptInterface
    public String createPuzzle()
    {

        /*        final boolean[] yaTermino = {false};
        RequestQueue requestQueue= Volley.newRequestQueue(this.contexto);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Red.DIRECCION_IP + "/plaython/webservices.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        String x = agregarLinea(i, jsonArray.getJSONArray(i).getString(1));
                    }
                    yaTermino[0] =true;

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parametros=new HashMap<>();
                parametros.put("action","puzzleOrdenamiento");
                parametros.put("numeroNivel", String.valueOf(numeroNivel));
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
        while(!yaTermino[0])
        {

        }*/

        return getPuzzle();
    }
    @JavascriptInterface
    public String getTitulo()
    {
        return this.tituloPR;
        /*final boolean[] yaTermino = {false};
        final String[] salida = {""};
        RequestQueue requestQueue= Volley.newRequestQueue(this.contexto);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Red.DIRECCION_IP + "/plaython/webservices.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                salida[0] =response;
                yaTermino[0] =true;
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parametros=new HashMap<>();
                parametros.put("action","puzzleOrdenamientoTitulo");
                parametros.put("numeroNivel", String.valueOf(numeroNivel));
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
        while(!yaTermino[0])
        {

        }
        return salida[0];*/
    }
    @JavascriptInterface
    public String getSalida()
    {
        return this.salidaPR;
        /*final boolean[] yaTermino = {false};
        final String[] salida = {""};
        RequestQueue requestQueue= Volley.newRequestQueue(this.contexto);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Red.DIRECCION_IP + "/plaython/webservices.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                salida[0] =response;
                yaTermino[0] =true;
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parametros=new HashMap<>();
                parametros.put("action","puzzleOrdenamientoSalida");
                parametros.put("numeroNivel", String.valueOf(numeroNivel));
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
        while(!yaTermino[0])
        {

        }
        return salida[0];*/
    }

    public String agregarLinea(int lugar, String linea)
    {
        /*try
        {
            return this.puzzle.put("set_Name"+lugar, linea).toString();

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "nada";*/
        return null;
    }
    public String getPuzzle()
    {
        return this.puzzle;
    }
}
