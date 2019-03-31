package samples.speech.cognitiveservices.microsoft.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VoicemailLibrary {
    public static ArrayList<Voicemail> voicemails;
    public VoicemailLibrary() {
        voicemails = null;
    }
    public static void initializeLibrary() {
        voicemails = new ArrayList<Voicemail>();
        voicemails.add(new Voicemail("Yeet the rahulio. Bruh.", "low-priority"));
        voicemails.add(new Voicemail("You just won a lifetime supply of free waffles. Call this number today to enroll.", "spam"));
        voicemails.add(new Voicemail("Hey, Sally, can you call me back when you're available? Thanks.", "not-spam"));
        voicemails.add(new Voicemail("The code isn't working and the entire server crashed. Call me back as soon as possible.", "urgent"));
        voicemails.add(new Voicemail("This is the US Treasury calling you about a hack on your account. Please send your social security number to this number.", "spam"));

    }

}
