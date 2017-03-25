package com.example.cice.downimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cice on 25/3/17.
 */

public class AsyncTask extends android.os.AsyncTask<String, Void, Bitmap> {
    private Activity actividad;
    private String destino;

    public AsyncTask(Activity datosActividad){
        this.actividad = datosActividad;
    }

    @Override
    protected void onPreExecute() {
        Log.d("MENSAJE", "Se inicia la descarga...");
    }

    @Override
    protected Bitmap doInBackground(String... parametros) {
        Bitmap bitmap = null;
        HttpURLConnection http = null;

        try {
            String urlImagen = parametros[0].toString();
            destino = parametros[1].toString();

            URL datosUrl = new URL(urlImagen);
            http = (HttpURLConnection)datosUrl.openConnection();

            int codigoRespuesta = http.getResponseCode();

            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                Log.d("MENSAJE", "Respuesta satisfactoria");
                InputStream respuestaServer = http.getInputStream();
                bitmap = BitmapFactory.decodeStream(respuestaServer);
            }

        } catch (Throwable t) {
            Log.e("EROOOOR", t.getMessage());
        } finally {
            http.disconnect();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.d("MENSAJE", "DESCARGA FINALIZADA");
        ((MainActivity)actividad).publicarImagen(bitmap, destino);
    }
}
