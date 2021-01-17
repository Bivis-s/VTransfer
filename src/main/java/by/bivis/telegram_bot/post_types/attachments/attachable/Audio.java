package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;

import java.io.File;

public class Audio implements Attachable, Printable {
    private File audio;
    private String artist;
    private String title;
    private int duration;

    public Audio() {
    }

    public Audio setAudio(File audio) {
        this.audio = audio;
        return this;
    }

    public Audio setAudio(String audioPath) {
        this.audio = new File(audioPath);
        return this;
    }

    public Audio setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public Audio setTitle(String title) {
        this.title = title;
        return this;
    }

    public Audio setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public InputFile getInputFile() {
        return new InputFile(audio);
    }

    @Override
    public InputMedia getInputMedia() {
        return new InputMediaAudio(audio.getPath());
    }

    //TODO ADD JAVADOC
    @Override
    public String getFormattedText() {
        return String.format("\uD83D\uDD0A [%s] %s – %s", Tools.getFormattedDuration(duration), artist, title);
    }
}
