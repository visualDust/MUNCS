package MockFormula.lib.Method;

public class RAMCleaner {
    public RAMCleaner() {
        cleaner cls = new cleaner();
        cls.start();
    }

    public class cleaner extends Thread {
        @Override
        public void run() {
            while (true) {
                System.gc();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
            }
        }
    }
}
