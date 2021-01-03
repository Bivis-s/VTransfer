package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.Attachment;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;

public class Audio extends Attachment implements Attachable, Printable {
    private final InputMediaAudio audio;
    private final String artist;
    private final String title;
    private final int duration;

    public Audio(InputMediaAudio audio, String artist, String title, int duration) {
        this.audio = audio;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
    }

    public Audio(String audioPath, String artist, String title, int duration) {
        this.audio = new InputMediaAudio(audioPath);
        this.artist = artist;
        this.title = title;
        this.duration = duration;
    }

    public InputMediaAudio getAudio() {
        return audio;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public InputMedia getMedia() {
        return audio;
    }

    //TODO ADD JAVADOC
    @Override
    public String getText() {
        return String.format("\uD83D\uDD0A [%s] %s – %s", Tools.getFormattedDuration(duration), artist, title);
    }
}