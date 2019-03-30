package samples.speech.cognitiveservices.microsoft.com;

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

    private String transcript;
    private int id;
    //private String typeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            transcript = extras.getString("text");
            id = extras.getInt("id");
            //typeString = extras.getString("type");
        }

        TextView transc = (TextView) findViewById(R.id.transcript);
        TextView typ = (TextView) findViewById(R.id.type);
        transc.setText(transcript);
        //typ.setText(typeString);


        Button recat = (Button) findViewById(R.id.recat);
        recat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="http://localhost:5000/api/categorizeText/?text=";
                try {
                    String urlText = URLEncoder.encode(transcript, "UTF-8");
                    url = url + urlText;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    transc.setText("Response is: "+ response.substring(0,500));
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            transc.setText("That didn't work!");
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    Log.d("Debug", e.getMessage());
                }
                Voicemail addMail = new Voicemail(transcript);
                VoicemailLibrary.addVoiceMail(addMail.getId(), addMail);
            }
        });



    }
}
