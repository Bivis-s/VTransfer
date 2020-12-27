package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.Attachment;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;

public class Audio extends Attachment implements Attachable {

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


    @Override
    public InputMedia getMedia() {
        return audio;
    }

    @Override
    public boolean hasText() {
        return true;
    }

    //TODO ADD JAVADOC
    @Override
    public String getText() {
        return String.format("\uD83D\uDD0A [%s] %s – %s", Tools.getFormattedDuration(duration), artist, title);
    }
}
