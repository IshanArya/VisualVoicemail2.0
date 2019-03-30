package samples.speech.cognitiveservices.microsoft.com;

import java.util.HashMap;
import java.util.Map;

public class VoicemailLibrary {
    private static Map<Integer, Voicemail> voicemailMap;
    public VoicemailLibrary() {
        voicemailMap = null;
    }
    public static void initializeLibrary() {
        voicemailMap = new HashMap<Integer, Voicemail>();
        voicemailMap.put(0, new Voicemail("Yeet the rahulio. Bruh.", "low-priority"));
        voicemailMap.put(1, new Voicemail("You just won a lifetime supply of free waffles. Call this number today to enroll.", "spam"));
        voicemailMap.put(2, new Voicemail("Hey, Sally, can you call me back when you're available? Thanks.", "not-spam"));
        voicemailMap.put(3, new Voicemail("The code isn't working and the entire server crashed. Call me back as soon as possible.", "urgent"));
        voicemailMap.put(4, new Voicemail("This is the US Treasury calling you about a hack on your account. Please send your social security number to this number.", "spam"));

    }
    public static void addVoiceMail(Integer key, Voicemail value) {
        voicemailMap.put(key, value);
    }
    public static Map<Integer, Voicemail> getVoicemailMap() {
        return voicemailMap;
    }
    public static Voicemail removeVoicemail(int id) {
        return voicemailMap.remove(id);
    }
    public static int getSize() {
        if (voicemailMap == null) {
            return 0;
        }
        return voicemailMap.size();
    }

}
