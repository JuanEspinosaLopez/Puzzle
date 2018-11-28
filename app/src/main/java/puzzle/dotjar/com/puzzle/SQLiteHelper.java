package puzzle.dotjar.com.puzzle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper
{
    final String FULL_SCHEMA= "CREATE TABLE IF NOT EXISTS tema(idTema TEXT PRIMARY KEY, tituloTema TEXT NOT NULL, complejidad INTEGER NOT NULL); " +
                            "CREATE TABLE IF NOT EXISTS subtema(idSubtema TEXT PRIMARY KEY, idTema TEXT NOT NULL,titulo TEXT NOT NULL, FOREIGN KEY (idSubtema) REFERENCES tema(idTema) ON DELETE CASCADE ON UPDATE CASCADE); " +
                            "CREATE TABLE IF NOT EXISTS puzzleRecepcion(idPuzzleRecepcion TEXT PRIMARY KEY, idSubtema TEXT NOT NULL, salidaPR TEXT NOT NULL, tituloPR TEXT NOT NULL, FOREIGN KEY (idSubtema) REFERENCES subtema(idSubtema) ON DELETE CASCADE ON UPDATE CASCADE); " +
                            "CREATE TABLE IF NOT EXISTS instruccion(idInstruccion TEXT PRIMARY KEY, complejidadInstruccion INTEGER NOT NULL, nombreInstruccion TEXT NOT NULL); " +
                            "CREATE TABLE IF NOT EXISTS instruccion_pr(idInstruccionPr TEXT PRIMARY KEY, puzzle TEXT NOT NULL, idPuzzleRecepcion TEXT NOT NULL, FOREIGN KEY (idPuzzleRecepcion) REFERENCES puzzleRecepcion(idPuzzleRecepcion) ON DELETE CASCADE ON UPDATE CASCADE); " +
                            "CREATE TABLE IF NOT EXISTS articulo(idArticulo TEXT PRIMARY KEY, idSubtema TEXT NOT NULL, tituloArticulo TEXT NOT NULL, urlArticulo TEXT NOT NULL, FOREIGN KEY (idSubtema) REFERENCES subtema(idSubtema) ON DELETE CASCADE ON UPDATE CASCADE );";

/*
* TABLAS SINCRONIZADAS: Tema, Subtema, puzzleRecepcion, articulo
* TODO: Definir si la tabla de instruccion se cargará desde firestore o estará precargada
*
* */

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String[] tablas=FULL_SCHEMA.split(";");
        for(String tabla: tablas)
            db.execSQL(tabla);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

}
