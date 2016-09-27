/**
 * Created by Денис on 26.09.2016.
 */
public abstract class Light implements Lighter


{
    @Override
    public void on() {
        System.out.println("Я загорелься");
    }

    @Override
    public void off() {
        System.out.println("Я потухьля");
    }

    @Override
    public abstract void brighten();

    @Override
    public abstract void dim();
}
