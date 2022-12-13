package network.threads;

import java.util.stream.IntStream;

public class AThread extends Thread{
    int i1, i2;

    public AThread(int i1, int i2){
        this.i1 = i1;
        this.i2 = i2;
    }

    public static void main(String[] args) {
        Thread th1 = new AThread(4, 15);
        th1.start();

        Thread th2 = new AThread(11, 22);
        th2.start();

        IntStream.range(21, 24)
                .peek(AThread::doSomething)
                .forEach(System.out::println);

        // also possible as Runnable is a functional interface
        Thread thr1 = new Thread(() -> IntStream.range(1, 4)
                .peek(AThread::doSomething)
                .forEach(System.out::println), "second thread");
        thr1.start();

        System.out.println("Id=" + th1.getId() + ", " + th1.getName() + ", priority=" + th1.getPriority() +
                        ", state=" + th1.getState());
        System.out.println("Id=" + th2.getId() + ", " + th2.getName() + ", priority=" + th2.getPriority() +
                        ", state=" + th2.getState());

        System.out.println("Id=" + thr1.getId() + ", " + thr1.getName() + ", priority=" + thr1.getPriority() +
                ", state=" + thr1.getState());

    }

    @Override
    public void run() {
        IntStream.range(i1, i2)
                .peek(AThread::doSomething)
                .forEach(System.out::println);
    }

    static int doSomething(int i1){
        IntStream.range(i1, 100000).asDoubleStream().map(Math::sqrt).average();
        return i1;
    }


}
