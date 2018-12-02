package puzzle.dotjar.com.puzzle;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MenuPrincipal extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    FrameLayout contenedor;

    private GoogleApiClient googleApiClient;

    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }
        //TODO: CAMBIAR EL EMAIL Y PASSWORD POR LOS DEL LOGIN
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);
                } else {
                    goLogInScreen();
                }
            }
        };

        contenedor=findViewById(R.id.contenedor_principal);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.contenedor_principal, new Menu()).commit();
    }
    void cambiarFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction()
                .replace(R.id.contenedor_principal, fragment).addToBackStack(null);
        desvanecerMenu(R.id.contenedor_principal, 1, 0);

        fragmentTransaction.commit();
        desvanecerMenu(R.id.contenedor_principal, 0, 1);
    }

    void desvanecerMenu(int idContenedor, int fromAlpha, int toAlpha)
    {
        Animation animation=new AlphaAnimation(fromAlpha, toAlpha);
        animation.setDuration(400);
        findViewById(idContenedor).setAnimation(animation);
        animation.start();


    }
    @Override
    public void onBackPressed()
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount()>0)
        {
            desvanecerMenu(R.id.contenedor_principal, 1, 0);
            getSupportFragmentManager().popBackStack();
            desvanecerMenu(R.id.contenedor_principal, 0, 1);
        }


    }
    void aplicarFontAwesome(int id)
    {
        Typeface iconFont = AdministradorFuentes.getTypeface(this, AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(findViewById(id), iconFont);
    }
    void aplicarFontAwesome(View v)
    {
        Typeface iconFont = AdministradorFuentes.getTypeface(this, AdministradorFuentes.FONTAWESOME);
        AdministradorFuentes.markAsIconContainer(v, iconFont);
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
    Animation obtenerAnimacion(float fromDegrees, float toDegrees, long duration)
    {
        Animation an = new RotateAnimation(fromDegrees, toDegrees, RotateAnimation.RELATIVE_TO_PARENT, .5f , RotateAnimation.RELATIVE_TO_PARENT, .5f );
        an.setDuration(duration);
        an.setRepeatCount(-1);
        an.setRepeatMode(Animation.REVERSE);
        an.setFillAfter(true);
        return an;
    }


    /* TODO: LOGIN */

    private void setUserData(FirebaseUser user) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logOut(View view) {
        firebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "No fue posible cerrar la sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Comprueba la conexión a Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuth != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
}
