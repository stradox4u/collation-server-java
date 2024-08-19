package pro.arcodeh.collation_server.seeder;

public interface SeederInterface {
    public boolean shouldSeed();
    public void run();
}
