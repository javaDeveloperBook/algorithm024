import java.util.List;
import java.util.concurrent.*;

public class Test {
    private static String extractHostName(String url) {
        String host = url.substring(7);
        return host.indexOf("/") == -1 ? host : host.substring(0, host.indexOf("/"));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(extractHostName("http://news.yahoo.com/news/topics/"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<List<String>> listFuture = executorService.submit(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return null;
            }
        });
        List<String> strings = listFuture.get();

    }
}
