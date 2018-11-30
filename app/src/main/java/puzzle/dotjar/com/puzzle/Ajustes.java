package puzzle.dotjar.com.puzzle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class Ajustes extends Fragment
{
    SQLiteHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ajustes_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper=new SQLiteHelper(getContext(),"plaython.db", null, 1);
        MenuPrincipal menuPrincipal=(MenuPrincipal)getActivity();
        menuPrincipal.aplicarFontAwesome(R.id.ajustes_fragmento);

        Button configuracionUsuario=menuPrincipal.findViewById(R.id.ajustes_configuracion_usuario);
        Button buscarActualizaciones=menuPrincipal.findViewById(R.id.ajustes_buscar_actualizaciones);
        Button verLicencias=menuPrincipal.findViewById(R.id.ajustes_ver_licencias);

        //anim
        configuracionUsuario.setAnimation(menuPrincipal.obtenerAnimacion(.5f,-.5f));
        verLicencias.setAnimation(menuPrincipal.obtenerAnimacion(.5f,-.5f));
        buscarActualizaciones.setAnimation(menuPrincipal.obtenerAnimacion(-.5f,.5f));


        //listeners
        buscarActualizaciones.setOnClickListener(new BusquedaActualizaciones());
    }
    class BusquedaActualizaciones implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Dialog dialog=null;
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setTitle(getResources().getString(R.string.buscar_actualizaciones));
            builder.setMessage(getResources().getString(R.string.mensaje_dialogo_actualizaciones));
            builder.setPositiveButton(getResources().getString(R.string.confirmacion), new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    FirebaseFirestore database=FirebaseFirestore.getInstance();

                    SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
                    deleteTablas(sqLiteDatabase, new String[]{"tema","subtema", "puzzleRecepcion", "instruccion_pr", "articulo"});
                    new Sincronizacion().execute(database);

                }
            });
            builder.setNegativeButton(getResources().getString(R.string.negacion), null);
            dialog=builder.create();
            dialog.show();
        }


        private void deleteTablas(SQLiteDatabase database, String[] tablas)
        {
            for(String tabla: tablas)
            database.delete(tabla, null, null);
        }

    }
    private class Sincronizacion extends AsyncTask<FirebaseFirestore, Integer, Integer>
    {
        ProgressDialog progressDialog;
        Sincronizacion ()
        {
            this.progressDialog=new ProgressDialog(getContext());
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog.setTitle(getResources().getString(R.string.descargando));
            progressDialog.setMessage(getResources().getString(R.string.sePaciente));
            progressDialog.show();
            progressDialog.setMax(100);

        }

        @Override
        protected Integer doInBackground(FirebaseFirestore... firebaseFirestores) {

            FirebaseFirestore database=firebaseFirestores[0];
            obtenerTemas(database);
            obtenerSubTemas(database);
            obtenerPuzzleRecepcion(database);
            obtenerArticulos(database);
            obtenerPuzzleRecepcion_json(database);
            return 1;

        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer firebaseFirestore) {
            super.onPostExecute(firebaseFirestore);

        }

        private void obtenerTemas(FirebaseFirestore database)
        {
            database.collection("tema")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
                                String idTema;
                                Map<String, Object> tema;

                                for(DocumentSnapshot document: task.getResult())
                                {
                                    idTema=document.getId();
                                    tema=document.getData();
                                    ContentValues contentValues=new ContentValues();
                                    contentValues.put("idTema", idTema);
                                    contentValues.put("tituloTema", tema.get("tituloTema").toString());
                                    contentValues.put("complejidad", Integer.parseInt(tema.get("complejidadTema").toString()));
                                    sqLiteDatabase.insert("tema", null, contentValues);
                                }
                            }
                        }
                    });
        }
        private void obtenerSubTemas(FirebaseFirestore database)
        {
            database.collection("subtema")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
                                String idSubtema;
                                Map<String, Object> subtema;
                                for(DocumentSnapshot document: task.getResult())
                                {
                                    idSubtema=document.getId();
                                    subtema=document.getData();
                                    ContentValues contentValues=new ContentValues();
                                    contentValues.put("idSubtema", idSubtema);
                                    DocumentReference reference=(DocumentReference) subtema.get("idTema");
                                    String idTema=reference.getId();
                                    contentValues.put("idTema", idTema);
                                    contentValues.put("titulo", subtema.get("tituloSubtema").toString());
                                    sqLiteDatabase.insert("subtema", null, contentValues);

                                }
                            }
                        }
                    });
        }
        private void obtenerPuzzleRecepcion(FirebaseFirestore database)
        {
            database.collection("puzzleRecepcion")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
                                String idPuzzleRecepcion;
                                Map<String, Object> puzzle;
                                for(DocumentSnapshot document: task.getResult())
                                {
                                    idPuzzleRecepcion=document.getId();
                                    puzzle=document.getData();

                                    ContentValues contentValues=new ContentValues();
                                    contentValues.put("idPuzzleRecepcion", idPuzzleRecepcion);
                                    DocumentReference reference= (DocumentReference) puzzle.get("idSubtema");

                                    String idSubtema=reference.getId();

                                    contentValues.put("idSubtema", idSubtema);
                                    contentValues.put("salidaPR", puzzle.get("salidaPR").toString());
                                    contentValues.put("tituloPR", puzzle.get("tituloPR").toString());

                                    sqLiteDatabase.insert("puzzleRecepcion", null, contentValues);
                                }
                            }
                        }
                    });
        }
        private void obtenerPuzzleRecepcion_json(FirebaseFirestore database)
        {
            database.collection("instruccion_pr")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
                                String idInstruccionPr;

                                Map<String, Object> puzzle;
                                for(DocumentSnapshot document: task.getResult())
                                {
                                    idInstruccionPr=document.getId();
                                    puzzle=document.getData();

                                    ContentValues contentValues=new ContentValues();

                                    DocumentReference reference= (DocumentReference) puzzle.get("idPuzzleRecepcion");
                                    String idPuzzleRecepcion=reference.getId();

                                    contentValues.put("idInstruccionPr", idInstruccionPr);
                                    contentValues.put("puzzle", puzzle.get("puzzle").toString());
                                    contentValues.put("idPuzzleRecepcion", idPuzzleRecepcion);
                                    sqLiteDatabase.insert("instruccion_pr", null, contentValues);
                                }
                            }
                        }
                    });
        }
        private void obtenerArticulos(FirebaseFirestore database)
        {
            database.collection("articulo")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
                                String idArticulo;
                                Map<String, Object> puzzle;
                                for(DocumentSnapshot document: task.getResult())
                                {
                                    idArticulo=document.getId();
                                    puzzle=document.getData();

                                    ContentValues contentValues=new ContentValues();
                                    contentValues.put("idArticulo", idArticulo);
                                    DocumentReference reference= (DocumentReference) puzzle.get("idSubtema");

                                    String idSubtema=reference.getId();

                                    contentValues.put("idSubtema", idSubtema);
                                    contentValues.put("tituloArticulo", puzzle.get("tituloArticulo").toString());
                                    contentValues.put("urlArticulo", puzzle.get("urlArticulo").toString());


                                }
                                progressDialog.dismiss();
                            }
                        }
                    });
        }
    }
}
