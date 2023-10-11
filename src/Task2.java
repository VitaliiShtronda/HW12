import java.util.*;


public class Task2 {

    private static final Object MONITOR_1 = new Object();
    private static final Object MONITOR_2 = new Object();
    private static final Object MONITOR_3 = new Object();

    public static void main(String[] args) {


        int n = inputCheck();


        Thread threadA = new Thread(() -> {
            synchronized (MONITOR_1) {
                try {
                    MONITOR_1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 1; i <= n; i++) {
                    if (isFizz(i)) {
                        System.out.println("fizz");
                        notifyAllAndWait(MONITOR_1);
                    }
                }

            }
        });


        Thread threadB = new Thread(() -> {
            synchronized (MONITOR_2) {
                try {
                    MONITOR_2.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 1; i <= n; i++) {
                    if (isBuzz(i)) {
                        System.out.println("buzz");
                        notifyAllAndWait(MONITOR_2);
                    }
                }

            }
        });


        Thread threadC = new Thread(() -> {
            synchronized (MONITOR_3) {
                try {
                    MONITOR_3.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 1; i <= n; i++) {
                    if (isFizzBuzz(i)) {
                        System.out.println("fizzbuzz");
                        notifyAllAndWait(MONITOR_3);
                    }
                }

            }
        });


        Thread threadD = new Thread(() -> {

            for (int i = 1; i <= n; i++) {
                if (isFizz(i)) {
                    notifyAllAndWait(MONITOR_1);
                } else if (isBuzz(i)) {
                    notifyAllAndWait(MONITOR_2);
                } else if (isFizzBuzz(i)) {
                    notifyAllAndWait(MONITOR_3);
                } else {
                    System.out.println(i);
                }
            }
        });


        threadA.setDaemon(true);
        threadB.setDaemon(true);
        threadC.setDaemon(true);


        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

    }


    private static void notifyAllAndWait(Object monitor) {
        synchronized (monitor) {
            monitor.notifyAll();
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


    private static boolean isFizz(int i) {
        return i % 3 == 0 && i % 5 != 0;
    }


    private static boolean isBuzz(int i) {
        return i % 3 != 0 && i % 5 == 0;
    }


    private static boolean isFizzBuzz(int i) {
        return i % 3 == 0 && i % 5 == 0;
    }

    
    private static int inputCheck() {
        int res;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter positive integer: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Wrong input!");
                System.out.print("Enter positive integer: ");
                scanner.next();
            }
            res = scanner.nextInt();
        } while (res <= 0);
        return res;
    }
}


