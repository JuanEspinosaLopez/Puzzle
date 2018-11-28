package puzzle.dotjar.com.puzzle.PuzzleOrdenamiento;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import puzzle.dotjar.com.puzzle.R;

public class PuzzleOrdenamiento extends AppCompatActivity
{
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_puzzle_ordenamiento);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        webView=findViewById(R.id.puzzleOrdenamiento);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        webView.addJavascriptInterface(new Comunicador(this, intent.getStringExtra("idPuzzleRecepcion"), intent.getStringExtra("tituloPR"), intent.getStringExtra("salidaPR")), "Android");
        webView.loadUrl("file:///android_res/raw/puzzle_ordenamiento.html");

    }
    public class Callback extends WebViewClient
    {
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
        {
            Toast.makeText(getApplicationContext(), "Error al cargar la aplicación!", Toast.LENGTH_SHORT).show();
        }

    }
}
