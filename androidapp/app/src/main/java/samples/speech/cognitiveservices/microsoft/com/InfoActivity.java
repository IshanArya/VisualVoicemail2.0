package samples.speech.cognitiveservices.microsoft.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InfoActivity extends AppCompatActivity {

    private String voicemailMessage;
    private String voicemailCategory;
    Intent backtrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle extras = getIntent().getExtras();
        voicemailMessage = extras.getString("text");
        voicemailCategory = extras.getString("category");

        TextView categoryView = (TextView) findViewById(R.id.category);
        TextView messageView = (TextView) findViewById(R.id.messageView);
        messageView.setText(voicemailMessage);
        categoryView.setText(voicemailCategory);


        backtrack = new Intent(this, MainActivity.class);

    }


    public void onSetSpamClicked(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://128.61.15.61:5000/api/retrain/?text=";
        try {
            String urlText = URLEncoder.encode(voicemailMessage, "UTF-8");
            String urlText2 = URLEncoder.encode("spam", "UTF-8");
            url = url + urlText + "&category=" + urlText2;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            startActivity(backtrack);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    startActivity(backtrack);
                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.d("Debug", e.getMessage());
        }
    }
    public void onSetNotSpamClicked(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://128.61.15.61:5000/api/retrain/?text=";
        try {
            String urlText = URLEncoder.encode(voicemailMessage, "UTF-8");
            String urlText2 = URLEncoder.encode("not-spam", "UTF-8");
            url = url + urlText + "&category=" + urlText2;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            startActivity(backtrack);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    startActivity(backtrack);
                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.d("Debug", e.getMessage());
        }
    }
    public void onSetUrgentClicked(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://128.61.15.61:5000/api/retrain/?text=";
        try {
            String urlText = URLEncoder.encode(voicemailMessage, "UTF-8");
            String urlText2 = URLEncoder.encode("urgent", "UTF-8");
            url = url + urlText + "&category=" + urlText2;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            startActivity(backtrack);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    startActivity(backtrack);
                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.d("Debug", e.getMessage());
        }
    }
    public void onSetLowPriorityClicked(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://localhost:5000/api/retrain/?text=";
        try {
            String urlText = URLEncoder.encode(voicemailMessage, "UTF-8");
            String urlText2 = URLEncoder.encode("low-priority", "UTF-8");
            url = url + urlText + "&category=" + urlText2;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            startActivity(backtrack);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    startActivity(backtrack);
                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.d("Debug", e.getMessage());
        }
    }
}
