package src.FacadeDesignPattern;

// 1. Subsystem Classes
// 

class AudioPlayer {
    void playAudio(String filename) {
        System.out.println("Playing audio file: " + filename);
    }
}

class VideoPlayer {
    void playVideo(String filename) {
        System.out.println("Playing video file: " + filename);
    }
}

class ImageLoader {
    void loadImage(String filename) {
        System.out.println("Loading image file: " + filename);
    }
}

class MultimediaFacade {
    private AudioPlayer audioPlayer;
    private VideoPlayer videoPlayer;
    private ImageLoader imageLoader;

    public MultimediaFacade() {
        this.audioPlayer = new AudioPlayer();
        this.videoPlayer = new VideoPlayer();
        this.imageLoader = new ImageLoader();
    }

    public void playMedia(String filename, String mediaType) {
        switch (mediaType.toLowerCase()) {
            case "audio":
                audioPlayer.playAudio(filename);
                break;
            case "video":
                videoPlayer.playVideo(filename);
                break;
            case "image":
                imageLoader.loadImage(filename);
                break;
            default:
                System.out.println("Unsupported media type: " + mediaType);
        }
    }
}

public class MultimediaApp {
    public static void main(String[] args) {
        MultimediaFacade facade = new MultimediaFacade();

        facade.playMedia("song.mp3", "audio");
        facade.playMedia("movie.mp4", "video");
        facade.playMedia("picture.jpg", "image");
        facade.playMedia("document.pdf", "document"); // Unsupported type
    }

}
