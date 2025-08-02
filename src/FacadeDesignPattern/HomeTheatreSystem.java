package src.FacadeDesignPattern;

public class HomeTheatreSystem {
    public static void main(String[] args) {
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();
        SoundSystem sound = new SoundSystem();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvd, projector, sound);

        homeTheater.watchMovie("Inception");
        System.out.println();
        homeTheater.endMovie();

    }

}

class DvdPlayer {
    public void on() {
        System.out.println("DVD Player on");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void off() {
        System.out.println("DVD Player off");
    }
}

class Projector {
    public void on() {
        System.out.println("Projector on");
    }

    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode");
    }

    public void off() {
        System.out.println("Projector off");
    }
}

class SoundSystem {
    public void on() {
        System.out.println("Sound system on");
    }

    public void setVolume(int level) {
        System.out.println("Setting volume to " + level);
    }

    public void off() {
        System.out.println("Sound system off");
    }
}

class HomeTheaterFacade {
    private DvdPlayer dvd;
    private Projector projector;
    private SoundSystem sound;

    public HomeTheaterFacade(DvdPlayer dvd, Projector projector, SoundSystem sound) {
        this.dvd = dvd;
        this.projector = projector;
        this.sound = sound;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        dvd.on();
        dvd.play(movie);
        projector.on();
        projector.wideScreenMode();
        sound.on();
        sound.setVolume(20);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        dvd.off();
        projector.off();
        sound.off();
    }
}
