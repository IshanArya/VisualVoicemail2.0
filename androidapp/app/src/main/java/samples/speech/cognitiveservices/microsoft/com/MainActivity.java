package samples.speech.cognitiveservices.microsoft.com;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognitionResult;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.util.concurrent.Future;

import static android.Manifest.permission.*;

public class MainActivity extends AppCompatActivity {

    // Replace below with your own subscription key
    private static String speechSubscriptionKey = "6bab225b74284c6c87d834ff6188a0cc";
    // Replace below with your own service region (e.g., "westus").
    private static String serviceRegion = "eastus";

    TextView txt;
    TextView analysis_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(VoicemailLibrary.getSize() == 0) {
            VoicemailLibrary.initializeLibrary();
        }
        analysis_text = findViewById(R.id.analysis_text);
        // Note: we need to request the permissions
        int requestCode = 5; // unique code for the permission request
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{RECORD_AUDIO, INTERNET}, requestCode);

        ButtonRectangle reeval = findViewById(R.id.button2);
        TextView sentiment = (TextView) this.findViewById(R.id.sentimText);
        reeval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentiment.setText("Actually it isn't.");
            }
        });


        Button voicemail = findViewById(R.id.voicemail);
        Intent voicemailIntent = new Intent(this, VoicemailActivity.class);
        voicemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(voicemailIntent);
            }
        });
    }

    public void onSpeechButtonClicked(View v) {
        txt = (TextView) this.findViewById(R.id.hello); // 'hello' is the ID of your text view
        TextView sentiment = (TextView) this.findViewById(R.id.sentimText); // 'sentimText' is the ID of our spam/ham filter
        String text = "";
        try {
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            assert(config != null);

            SpeechRecognizer reco = new SpeechRecognizer(config);
            assert(reco != null);

            Future<SpeechRecognitionResult> task = reco.recognizeOnceAsync();
            assert(task != null);

            // Note: this will block the UI thread, so eventually, you want to
            //        register for the event (see full samples)
            SpeechRecognitionResult result = task.get();
            assert(result != null);

            if (result.getReason() == ResultReason.RecognizedSpeech) {
                String resultFormatted = result.toString().substring(result.toString().indexOf("<") + 1, result.toString().lastIndexOf(">"));
                txt.setText(resultFormatted);
                sentiment.setText("This is spam for sure buddy.");
                text = resultFormatted;
            }
            else {
                txt.setText("Error recognizing. Did you update the subscription info?" + System.lineSeparator() + result.toString());
            }

            reco.close();
        } catch (Exception ex) {
            Log.e("SpeechSDKDemo", "unexpected " + ex.getMessage());
            assert(false);
        }

        Voicemail addMail = new Voicemail(text);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://localhost:5000/api/categorizeText/?text=";
        try {
            String urlText = URLEncoder.encode(text, "UTF-8");
            url = url + urlText;
            analysis_text.setText(url);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            addMail.setText(response);
                            // Display the first 500 characters of the response string.
                            analysis_text.setText("Response is: "+ response.substring(0,500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    analysis_text.setText("That didn't work!");
                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.d("Debug", e.getMessage());
        }

        VoicemailLibrary.addVoiceMail(addMail.getId(), addMail);



    }
}