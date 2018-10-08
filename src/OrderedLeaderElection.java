import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class OrderedLeaderElection {
    public static void main(String[] args) {
        // N elected official threads
        Phaser p1 = new Phaser();
        // One rank thread
        Phaser p2 = new Phaser(1);

        final int numThreads = 10;

        for (int i = 0; i < numThreads; i++) {
            new OrderedLeaderElection().electedOfficialThread(p1);
        }

        Thread rankThread;

    }

    private void electedOfficialThread(Phaser ph1) {
        ph1.register();

        new Thread(new Runnable() {
            @Override
            public void run() {
               
                int rank = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                Thread leader = Thread.currentThread();
                 String name = Thread.currentThread().getName();
                System.out.println("Name: " + name + " Rank: " + rank + " Boss: " + leader.getName());
                

                ph1.arriveAndDeregister();
            }
        }).start();
    }
}