package puzzle.dotjar.com.puzzle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import puzzle.dotjar.com.puzzle.PuzzleOrdenamiento.PuzzleOrdenamiento;

public class ListaPuzzle extends Fragment
{
    SQLiteHelper sqLiteHelper;
    MenuPrincipal menuPrincipal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        sqLiteHelper=new SQLiteHelper(getContext(),"plaython.db", null, 1);
        return inflater.inflate(R.layout.puzzle_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        menuPrincipal=(MenuPrincipal)getActivity();
        menuPrincipal.aplicarFontAwesome(R.id.puzzle_fragmento_tabla);
        SQLiteDatabase sqLiteDatabase=sqLiteHelper.getReadableDatabase();
        TableLayout tableLayout=menuPrincipal.findViewById(R.id.puzzle_fragmento_tabla);
        mostrarTodosPuzzles(sqLiteDatabase, tableLayout);
    }
    private void mostrarTodosPuzzles(SQLiteDatabase sqLiteDatabase, TableLayout tableLayout)
    {

        boolean par=true;
        Cursor cursor=sqLiteDatabase.query("puzzleRecepcion", null, null, null, null, null, null);
        TableLayout.LayoutParams layoutParamsRow=new TableLayout.LayoutParams();
        TableRow.LayoutParams layoutParamsButton=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 350);

        layoutParamsButton.weight=1;
        layoutParamsButton.setMargins(5, 0, 5, 0);
        layoutParamsRow.setMargins(10,20,10,20);


        TableRow tableRow=new TableRow(getContext());
        Button button, button1;

        while(cursor.moveToNext())
        {
            try
            {
                tableRow=new TableRow(getContext());
                tableRow.setLayoutParams(layoutParamsRow);
                button=new Button(getContext());
                button.setLayoutParams(layoutParamsButton);
                tableRow.addView(button);
                button.setBackground(getResources().getDrawable(R.drawable.btn_puzzle));
                button.setTextColor(getResources().getColor(R.color.blanco));
                button.setText(getResources().getString(R.string.fa_po));
                button.setOnClickListener(new verPuzzle(cursor.getString(cursor.getColumnIndex("idPuzzleRecepcion")), cursor.getString(cursor.getColumnIndex("tituloPR")), cursor.getString(cursor.getColumnIndex("salidaPR"))));
                button.setTextSize(30*TypedValue.COMPLEX_UNIT_SP);
                button1=new Button(getContext());
                button1.setLayoutParams(layoutParamsButton);
                tableRow.addView(button1);
                Animation animation=menuPrincipal.obtenerAnimacion(.2f, -.2f, 1000);
                button.setAnimation(animation);

                if(cursor.moveToNext())
                {
                    button1.setBackground(getResources().getDrawable(R.drawable.btn_puzzle));
                    button1.setText(getResources().getString(R.string.fa_po));
                    button1.setOnClickListener(new verPuzzle(cursor.getString(cursor.getColumnIndex("idPuzzleRecepcion")), cursor.getString(cursor.getColumnIndex("tituloPR")), cursor.getString(cursor.getColumnIndex("salidaPR"))));
                    button1.setTextColor(getResources().getColor(R.color.blanco));
                    button1.setTextSize(30*TypedValue.COMPLEX_UNIT_SP);
                    animation=menuPrincipal.obtenerAnimacion(.2f, -.2f, 1000);
                    button1.setAnimation(animation);
                }
                else
                    button1.setVisibility(View.INVISIBLE);
            }
            catch (Exception x)
            {
                x.printStackTrace();
                break;
            }
            finally
            {
                menuPrincipal.aplicarFontAwesome(tableRow);
                tableLayout.addView(tableRow);
            }
        }

    }
    class verPuzzle implements View.OnClickListener
    {
        String idPuzzleRecepcion, tituloPr, salidaPr;

        public verPuzzle(String idPuzzleRecepcion, String tituloPr, String salidaPr)
        {
            this.idPuzzleRecepcion = idPuzzleRecepcion;
            this.tituloPr = tituloPr;
            this.salidaPr = salidaPr;
        }

        @Override
        public void onClick(View v)
        {
            Intent intent=new Intent(getContext(), PuzzleOrdenamiento.class);
            intent.putExtra("idPuzzleRecepcion", this.idPuzzleRecepcion);
            intent.putExtra("tituloPR", this.tituloPr);
            intent.putExtra("salidaPR", this.salidaPr);
            startActivity(intent);

        }
    }



}
