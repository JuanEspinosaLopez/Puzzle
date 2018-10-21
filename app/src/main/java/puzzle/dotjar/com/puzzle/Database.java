package puzzle.dotjar.com.puzzle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    final String TABLA_ORDENAMIENTO="PuzzleOrdenamiento";
    final String TABLA_SINTAXIS="PuzzleSintaxis";
    final String TABLA_UML="PuzzleUML";
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
