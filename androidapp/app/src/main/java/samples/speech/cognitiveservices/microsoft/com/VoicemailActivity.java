package samples.speech.cognitiveservices.microsoft.com;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;



import java.util.Arrays;
import java.util.Set;

public class VoicemailActivity extends AppCompatActivity {
    ListView voicemail_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voicemail);
        voicemail_list = findViewById(R.id.listview_voicemail);
        Set<Integer> voicemailNums = VoicemailLibrary.getVoicemailMap().keySet();
        String[] voicemailNumsArr = new String[voicemailNums.size()];
        int j = 0;
        for(Integer i : voicemailNums) {
            voicemailNumsArr[j] = "Voicemail " + i;
            j++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                Arrays.asList(voicemailNumsArr));
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        voicemail_list.setAdapter(adapter);
        Intent i = new Intent(this, InfoActivity.class);
        voicemail_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("debug", "position: " + position + voicemailNums.toArray(new Integer[0])[position]);
                i.putExtra("id", voicemailNums.toArray(new Integer[0])[position]);
                i.putExtra("category", VoicemailLibrary.getVoicemailMap().get(voicemailNums.toArray(new Integer[0])[position]).category);
                i.putExtra("text", VoicemailLibrary.getVoicemailMap().get(voicemailNums.toArray(new Integer[0])[position]).getText());
                Log.d("debug", VoicemailLibrary.getVoicemailMap().get(voicemailNums.toArray(new Integer[0])[position]).getText());
                startActivity(i);
            }
        });




    }


}
