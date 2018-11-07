package puzzle.dotjar.com.puzzle.PuzzleOrdenamiento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.JavascriptInterface;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle.dotjar.com.puzzle.Database;
import puzzle.dotjar.com.puzzle.Red;

public class Comunicador
{
    private Context contexto;
    private int numeroNivel;
    private SQLiteDatabase sqLiteDatabase;
    private Database database;
    private JSONObject puzzle;
    Comunicador(Context contexto, int numeroNivel)
    {
        this.contexto = contexto;
        this.numeroNivel=numeroNivel;
        this.database= new Database(contexto, "puzzle", null, 1);
        this.sqLiteDatabase=database.getReadableDatabase();
        this.puzzle =new JSONObject();
    }
    
    @JavascriptInterface
    public String createPuzzle()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this.contexto);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Red.DIRECCION_IP + "/", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0; i<jsonArray.length(); i++)
                        agregarLinea(i, jsonArray.getString(i));
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
        });
        requestQueue.add(stringRequest);

        return getPuzzle();
    }
    @JavascriptInterface
    public String getTitulo()
    {
        //TODO: Usar el objeto SqliteDatabase para acceder al titulo del puzzle con ID: this.numeroNivel
        return "Puzzle de ordenamiento numero 2";
    }
    @JavascriptInterface
    public String getSalida()
    {
        //TODO: Usar el objeto SqliteDatabase para acceder a la salida del puzzle con ID: this.numeroNivel
        return  "<p>1</p>\n" +
                "<p>2</p>\n" +
                "<p>3</p>\n" +
                "<p>4</p>\n" +
                "<p>5</p>";
    }

    public void limpiarPuzzle()
    {
        this.puzzle=new JSONObject();
    }
    public int agregarLinea(int lugar, String linea)
    {
        try
        {
            this.puzzle.put("set_Name"+lugar, linea);
            return 1;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    public String getPuzzle()
    {
        return puzzle.toString();
    }
}
