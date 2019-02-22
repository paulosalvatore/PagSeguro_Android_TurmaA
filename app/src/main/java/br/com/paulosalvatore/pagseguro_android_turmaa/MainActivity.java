package br.com.paulosalvatore.pagseguro_android_turmaa;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String URL_IMAGE = "https://portal9677.blob.core.windows.net/portal/2017/07/RedacaoUOL_f_002.jpg";
//    Image too large gives an exception
//    private static final String URL_IMAGE = "https://eoimages.gsfc.nasa.gov/images/imagerecords/87000/87903/tyuleniy_oli_2016097_lrg.jpg";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnBusca:
                Toast.makeText(this, "Item Busca Clicado", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mnInfo:
                Toast.makeText(this, "Item Info Clicado", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickWorkerThread(View view) {
        Toast.makeText(this, "Worker Thread", Toast.LENGTH_LONG).show();

//        imageView.setImageResource(android.R.color.transparent);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(MainActivity.this, "Inside Thread", Toast.LENGTH_LONG).show();
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(android.R.color.transparent);
                    }
                });

                final Bitmap bitmap = loadImage(URL_IMAGE);

                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    private static Bitmap loadImage(String urlImage) {
        try {
            URL url = new URL(urlImage);
            return BitmapFactory.decodeStream(
                    url.openConnection().getInputStream()
            );
        } catch (Exception e) {
            Log.e("loadImage", "Error loading image", e);
        }

        return null;
    }

    public void clickAsyncTask(View view) {
        Toast.makeText(this, "Async Task", Toast.LENGTH_LONG).show();

        new MyAsyncTask(this).execute();
    }

    public static class MyAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        private ProgressDialog progressDialog;

        private WeakReference<MainActivity> activityReference;

        MyAsyncTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            MainActivity activity = activityReference.get();

            progressDialog = ProgressDialog.show(
                    activity,
                    "Aguarde",
                    "Carregando imagem"
            );

            activity.imageView.setImageResource(android.R.color.transparent);
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return loadImage(URL_IMAGE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            MainActivity activity = activityReference.get();

            if (activity == null || activity.isFinishing()) return;

            Toast.makeText(activity, "Imagem carregada.", Toast.LENGTH_LONG).show();
            activity.imageView.setImageBitmap(bitmap);

            progressDialog.dismiss();
        }
    }
}
