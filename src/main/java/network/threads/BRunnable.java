package network.threads;

import java.util.stream.IntStream;

public class BRunnable implements Runnable{
    int i1, result;
    BRunnable(int i1){ this.i1 = i1; }
    public int getCurrentResult(){ return this.result; }

    @Override
    public void run() {
        for(int i = i1; i < i1 + 6; i++){
            //Do something useful here
            this.result = i;
            try{
                Thread.sleep(1000);
            } catch(InterruptedException ex){}
        }
    }

    public static void main(String[] args) {
        BRunnable r1 = new BRunnable(1);
        Thread thr1 = new Thread(r1);
        thr1.start();
        IntStream.range(21, 29)
                .peek(i -> thr1.interrupt())
                .filter(i -> {
                    int res = r1.getCurrentResult();
                    System.out.print(res + " => ");
                    return res % 2 == 0;
                })
                .forEach(System.out::println);
    }
}
