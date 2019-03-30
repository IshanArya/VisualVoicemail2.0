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
