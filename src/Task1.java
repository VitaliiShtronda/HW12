public class Task1 {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        Thread timeThread = new Thread(() -> {

            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime) / 1000;
                System.out.println("час який минув: " + " сек.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread customThread = new Thread(() -> {
            while (true) {
                System.out.println("Минуло 5 секунд");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timeThread.start();
        customThread.start();



    }

}
