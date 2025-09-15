package Philosophers;

import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private final Semaphore leftFork;
    private final Semaphore rightFork;

    public Philosopher(Semaphore leftFork, Semaphore rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        leftFork.acquire();
        rightFork.acquire();
        System.out.println(Thread.currentThread().getName() + " is eating.");

        Thread.sleep((long) (Math.random() * 1000));

        rightFork.release();
        leftFork.release();
        System.out.println(Thread.currentThread().getName() + " has finished eating.");
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {
        final int PHILOSOPHER_COUNT = 5;
        Semaphore[] forks = new Semaphore[PHILOSOPHER_COUNT];
        
        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            forks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[PHILOSOPHER_COUNT];
        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            // Используем семафоры для вилок. Философи берут сначала левую вилку, затем правую.
            philosophers[i] = new Philosopher(forks[i], forks[(i + 1) % PHILOSOPHER_COUNT]);
            philosophers[i].start();
        }
    }
}
