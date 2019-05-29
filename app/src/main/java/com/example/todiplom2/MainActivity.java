package com.example.todiplom2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.todiplom2.ui.main.SectionsPagerAdapter;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
  TextView textView;
  public String url = "http://screedwall.ru:3000";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
    ViewPager viewPager = findViewById(R.id.view_pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);

    textView = (TextView) findViewById(R.id.textView1);
    Requarer requarer = new Requarer();
    requarer.execute(url);

    String JSONObj = new Gson().toJson("Sergei");

    requarer.execute(url + "/registration", JSONObj, "SkoblinTest", "22");
  }
  public class Requarer extends AsyncTask<String, Void, String> {
    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params) {
      Request.Builder builder = new Request.Builder();
      builder.url(params[0]);
      Request request = builder.build();
      try {
        Response response = client.newCall(request).execute();
        return response.body().string();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(String res) {
      super.onPostExecute(res);
      textView.setText(res);
    }
  }
}