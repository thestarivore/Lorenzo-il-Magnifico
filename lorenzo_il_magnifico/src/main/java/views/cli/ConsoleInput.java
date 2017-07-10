package views.cli;
import java.util.concurrent.*;

/**
 * Created by starivore on 7/10/17.
 */
public class ConsoleInput {
    private final int timeout;
    private final TimeUnit unit;

    public ConsoleInput(int timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    public String readLine() throws InterruptedException {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        String input = "TIMEOUT_OCCURRED!";
        try {
            //System.out.println(String.valueOf(i + 1) + ". loop");
            Future<String> result = ex.submit(new ConsoleInputReadTask());
            try {
                input = result.get(timeout, unit);
            } catch (ExecutionException e) {
                e.getCause().printStackTrace();
            } catch (TimeoutException e) {
                //System.out.println("Cancelling reading task");
                result.cancel(true);
                //System.out.println("\nThread cancelled. input is null");
            }
        } finally {
            ex.shutdownNow();
        }
        return input;
    }
}