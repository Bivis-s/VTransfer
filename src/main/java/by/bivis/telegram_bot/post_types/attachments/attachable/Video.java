package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.printable.Printable;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;

import java.io.File;

public class Video implements Attachable, Printable {
    private File video;
    private String title;
    private String description;
    private int duration;

    public Video() {
    }

    public Video setVideo(File video) {
        this.video = video;
        return this;
    }

    public Video setVideo(String videoPath) {
        this.video = new File(videoPath);
        return this;
    }

    public Video setTitle(String title) {
        this.title = title;
        return this;
    }

    public Video setDescription(String description) {
        this.description = description;
        return this;
    }

    public Video setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public InputFile getInputFile() {
        return new InputFile(video);
    }

    @Override
    public InputMedia getInputMedia() {
        return new InputMediaVideo(video.getPath());
    }

    //TODO ADD JAVADOC
    @Override
    public String getFormattedText() {
        if (description != null) {
            return String.format("\uD83C\uDFA5 [%s] %s\n%s", Tools.getFormattedDuration(duration), title, description);
        } else {
            return String.format("\uD83C\uDFA5 [%s] %s", Tools.getFormattedDuration(duration), title);
        }
    }
}
