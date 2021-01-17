package by.bivis.telegram_bot.post_types.attachments.attachable;

import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

//TODO ADD JAVADOC
public interface Attachable {
    InputFile getInputFile();
    InputMedia getInputMedia();
}
