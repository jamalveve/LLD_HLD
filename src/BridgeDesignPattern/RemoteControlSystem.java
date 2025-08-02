package src.BridgeDesignPattern;

interface Device {
    void turnOn();

    void turnOff();

    void setVolume(int volume);
}

class TV implements Device {
    private int volume = 20;

    @Override
    public void turnOn() {
        System.out.println("TV is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is turned OFF");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("TV volume set to " + volume);
    }
}

class Radio implements Device {
    private int volume = 30;

    @Override
    public void turnOn() {
        System.out.println("Radio is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is turned OFF");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Radio volume set to " + volume);
    }
}

abstract class RemoteControl {
    protected Device device;

    protected RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public abstract void setVolume(int volume);
}

class BasicRemoteControl extends RemoteControl {

    public BasicRemoteControl(Device device) {
        super(device);
    }

    @Override
    public void turnOn() {
        device.turnOn();
    }

    @Override
    public void turnOff() {
        device.turnOff();
    }

    @Override
    public void setVolume(int volume) {
        device.setVolume(volume);
    }

    // Additional method specific to remote
    public void mute() {
        device.setVolume(0);
        System.out.println("Device muted");
    }
}

public class RemoteControlSystem {

    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remoteForTV = new BasicRemoteControl(tv);

        remoteForTV.turnOn();
        remoteForTV.setVolume(25);
        // remoteForTV.mute();//check
        remoteForTV.turnOff();

        System.out.println();

        Device radio = new Radio();
        RemoteControl remoteForRadio = new BasicRemoteControl(radio);

        remoteForRadio.turnOn();
        remoteForRadio.setVolume(50);
        remoteForRadio.turnOff();
    }

}
