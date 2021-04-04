package test_data.post_types;

import by.bivis.telegram_bot.post_types.attachments.attachable.*;

import java.util.Arrays;
import java.util.List;

public class AttachableAttachmentsExamples {
    private static final String FILE_PATH = "src/test/resources/attachments_example/";
    private static final String VIDEO_PATH = FILE_PATH + "video.mp4";
    private static final String ALBUM_COVER_PATH = FILE_PATH + "album_cover.jpg";
    private static final String AUDIO_PATH = FILE_PATH + "audio.mp3";
    private static final String PHOTO_PATH = FILE_PATH + "photo.jpg";
    private static final String DOCUMENT_PATH = FILE_PATH + "document.pdf";
    private static final String GOOD_PHOTO_PATH = FILE_PATH + "good_photo.jpg";

    private static final String PHOTO_URL = "https://sun9-40.userapi.com/impg/DGWbhG3UIoeQ7AOJaHtBUW7YIF4nuDNtzQ9JBw/KuyCCVyHAwU.jpg?size=1047x1047&quality=96&proxy=1&sign=6e64dd1b3b647282cc40cc38781639c0&type=album";
    private static final String VIDEO_URL = "https://r4---sn-4g5edn7y.googlevideo.com/videoplayback?expire=1610957553&ei=ke4EYMKrLsi2mLAPxeWj6A0&ip=103.73.101.126&id=o-AL2ldND5fPzvMwrOAwftLEVsDnndBaAAmUqfWzCne9Oy&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=RDwKsrwP7XgOcHYvAmoVA34F&gir=yes&clen=751184&ratebypass=yes&dur=24.380&lmt=1610542544522645&fvip=5&c=WEB&txp=5530434&n=EVHbZP6mvcaFck&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgc20D5z_fR69cPqJivfra2MUCOpCF4mM0Cm9uqtlZeZICIGfaFG-HqCksanZQMtQP1gYTsYlO15t7917Jcv6RDeYw&rm=sn-po4poput-aixe7e,sn-2uja-3ipd76,sn-4wgk76&req_id=15855634a6a1a3ee&redirect_counter=3&cms_redirect=yes&ipbypass=yes&mh=aW&mip=87.252.225.95&mm=30&mn=sn-4g5edn7y&ms=nxu&mt=1610935225&mv=m&mvi=5&pl=24&usequic=no&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,usequic&lsig=AG3C_xAwRAIgVACpss5KGAy3n-snP6A7LL8JADc-gYsmWOH8jbMRg_YCIFKgeAnyrgQ-B1DZuypP5GpX_jjkJ1nYIMglayLb5qg_&ir=1&rr=12";
    private static final String DOCUMENT_URL = "";
    private static final String AUDIO_URL = "";

    private static final Photo PHOTO_EXAMPLE = (new Photo()).setPhoto(PHOTO_URL);
    private static final Video VIDEO_EXAMPLE = (new Video())
            .setVideo(VIDEO_URL)
            .setTitle("Похороны ежа ржака")
            .setDescription("Вы будете ржать")
            .setDuration(67);

    public static Attachable getAudio() {
        return new Audio()
                .setAudio(AUDIO_PATH)
                .setArtist("Моргенштерн")
                .setTitle("ПОСОСИ")
                .setDuration(306);
    }

    public static Attachable getDocument() {
        return new Document()
                .setDocument(DOCUMENT_PATH)
                .setExt("pdf")
                .setSize(233445)
                .setTitle("Java 8. Руководство для начинающих. Герберт Шилдт");
    }

    public static Attachable getGoodSelection() {
        return new GoodSelection()
                .setPhoto(GOOD_PHOTO_PATH)
                .setName("Трусы")
                .setCount(5)
                .setPrice("$14.99");
    }

    public static Attachable getPhoto() {
        return new Photo()
                .setPhoto(PHOTO_PATH);
    }

    public static Attachable getPhotoAlbum() {
        return new PhotoAlbum()
                .setPhoto(ALBUM_COVER_PATH)
                .setSize(34)
                .setTitle("Пьяный угар 2007");
    }

    public static Attachable getVideo() {
        return new Video()
                .setVideo(VIDEO_PATH)
                .setDescription("Мои друзья: отдыхают, развлекаются, весело проводят время\nЯ: окей, ютуб, видео ёжик чихает и попадает под бит")
                .setDuration(24000)
                .setTitle("Ёжик чихнул но попал в бит");
    }

    public static Attachable getVideoWithoutDescription() {
        return new Video()
                .setVideo("src/test/resources/attachments_example/video.mp4")
                .setDuration(24000)
                .setTitle("Ёжик чихнул но попал в бит");
    }

    public static List<Attachable> get2Photos() {
        return Arrays.asList(
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE
        );
    }

    public static List<Attachable> get10Photos() {
        return Arrays.asList(
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE
        );
    }

    public static List<Attachable> get11Photos() {
        return Arrays.asList(
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE,
                PHOTO_EXAMPLE
        );
    }

    public static List<Attachable> get1Photo1Video() {
        return Arrays.asList(
                PHOTO_EXAMPLE,
                VIDEO_EXAMPLE
        );
    }

    public static List<Attachable> get5Photo5Video() {
        return Arrays.asList(
                PHOTO_EXAMPLE,
                VIDEO_EXAMPLE,
                PHOTO_EXAMPLE,
                VIDEO_EXAMPLE,
                PHOTO_EXAMPLE,
                VIDEO_EXAMPLE,
                PHOTO_EXAMPLE,
                VIDEO_EXAMPLE,
                PHOTO_EXAMPLE,
                VIDEO_EXAMPLE
        );
    }
}
