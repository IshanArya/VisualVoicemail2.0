package samples.speech.cognitiveservices.microsoft.com;

public class Voicemail {
    private int id;
    private String text;
    private static int numofMails = 0;
    public Voicemail(String text) {
        this.id = numofMails;
        this.text = text;
        numofMails++;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

}
