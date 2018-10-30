package com.adnan.myresponsi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);


        new CheckConnectionStatus().execute("https://api.themoviedb.org/3/movie/now_playing?api_key=b6f5911494c13456dd97cb94f48d4744");
    }

    class CheckConnectionStatus extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try{
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try{
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String s = bufferedReader.readLine();
                bufferedReader.close();

                return s;
            }catch (IOException e) {
                Log.e("Error: ", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(s);

                ArrayList<DetailFilm> listFilm = new ArrayList<>();

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i=0; i<jsonArray.length();i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    DetailFilm detailFilm = new DetailFilm();
                    detailFilm.setOriginal_title(object.getString("original_title"));
                    detailFilm.setVote_average(object.getString("vote_average"));
                    detailFilm.setOverview(object.getString("overview"));
                    detailFilm.setRelease_date(object.getString("release_date"));
                    detailFilm.setPoster_path(object.getString("poster_path"));
                    listFilm.add(detailFilm);
                }
                MovieArrayAdapter movieArrayAdapter = new MovieArrayAdapter(MainActivity.this, R.layout.activity_list_film,listFilm);
                listView.setAdapter(movieArrayAdapter);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }


    }


}

