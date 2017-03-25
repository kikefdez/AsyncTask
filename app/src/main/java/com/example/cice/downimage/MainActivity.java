package com.example.cice.downimage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String urlImagen1 = "http://orig02.deviantart.net/85d5/f/2016/102/b/d/blood_bowl_2___icon_by_blagoicons-d9yoc3d.png";
    private static final String urlImagen2 = "https://1d4chan.org/images/7/76/Jim_and_Bob_Art.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void descargaImagen(View v){
        Log.d("MENSAJE", "Autorizada descarga");

        AsyncTask downImagen1 = new AsyncTask(this);
        downImagen1.execute(urlImagen1, "downImage1");

        AsyncTask downImagen2 = new AsyncTask(this);
        downImagen2.execute(urlImagen2, "downImage2");

        /*

        SI NECESITO QUE LA DESCARGA FUESE SINCRONA DEBERÍA USAR ESTE CÓDIGO

        try{
            Bitmap miImagen = downImagen.execute(urlImagen).get();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        */

        Log.d("MENSAJE", "Descarga iniciada...");
        v.setEnabled(false); // deshabilitamos la vista para evitar que se pulse repetidas veces
    }

    public void publicarImagen(Bitmap miImagen, String destino){
        ((ImageView)findViewById(getResources().getIdentifier(destino, "id", this.getPackageName()))).setImageBitmap(miImagen);
        /*
        if(destino == "downImage1") {

            ((ImageView) findViewById(R.id.downImage1)).setImageBitmap(miImagen);
        } else {
            ((ImageView) findViewById(R.id.downImage2)).setImageBitmap(miImagen);
        }
        */
        Log.d("MENSAJE", "IMAGEN CARGADA");
        (findViewById(R.id.btnDescargar)).setEnabled(true);
    }
}
