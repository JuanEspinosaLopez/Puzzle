package puzzle.dotjar.com.puzzle.PuzzleOrdenamiento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.JavascriptInterface;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle.dotjar.com.puzzle.Database;

public class Comunicador
{
    private Context contexto;
    private int numeroNivel;
    private SQLiteDatabase sqLiteDatabase;
    private Database database;

    Comunicador(Context contexto, int numeroNivel)
    {
        this.contexto = contexto;
        this.numeroNivel=numeroNivel;
        this.database= new Database(contexto, "puzzle", null, 1);
        this.sqLiteDatabase=database.getReadableDatabase();
    }
    
    @JavascriptInterface
    public String getPuzzle()
    {
        //TODO: Usar el objeto SqliteDatabase para acceder al puzzle con ID: this.numeroNivel
        JSONObject jsonObject=new JSONObject();
        try
        {
            jsonObject.put("setName_"+0, "print (cada_item)");
            jsonObject.put("setName_"+1, "print (cada_item2)");
            jsonObject.put("setName_"+2, "print (cada_item3)");
            jsonObject.put("setName_"+3, "print (cada_item4)");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonObject.toString();
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

}
