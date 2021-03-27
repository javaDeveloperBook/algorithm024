//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
///**
// * @author JackWu
// * @version 1.0
// */
//public class Test {
//    public static void main(String[] args) {
//        String startUrl = "http://www.baidu.com/wqwq";
//        String hostName = ;
//        System.out.println(hostName);
//    }
//
//    class Solution {
//
//        private BlockingQueue<String> toDoCrawl = new LinkedBlockingDeque<>();
//        private List<String> result = new CopyOnWriteArrayList<String>();
//        private ExecutorService executorService = Executors.newFixedThreadPool(100);
//        private AtomicInteger count = new AtomicInteger(0);
//
//        public List<String> crawl(String startUrl, HtmlParser htmlParser) {
//            String hostName = extractHostName(startUrl);
//            result.add(startUrl);
//            toDoCrawl.add(startUrl);
//            while (!toDoCrawl.isEmpty() || count.get() != 0) {
//                try {
//                    String url = toDoCrawl.take();
//
//                    Future<List<String>> future = executorService.submit(new UrlCrawl(url, hostName, htmlParser, toDoCrawl));
//
//                    count.getAndIncrement();
//
//                    result.addAll(future.get());
//
//                }catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            executorService.shutdown();
//
//            while (true) {
//                if (executorService.isShutdown()) {
//                    break;
//                }
//            }
//
//            return result;
//        }
//
//        private String extractHostName(String startUrl) {
//            return !startUrl.substring(7).contains("/") ? startUrl.substring(7) :
//                    startUrl.substring(7).substring(0,startUrl.substring(7).indexOf("/"));
//        }
//
//        class UrlCrawl implements Callable<List<String>> {
//
//            private String url;
//
//            private String hostName;
//
//            private HtmlParser htmlParser;
//
//            private BlockingQueue<String> toDoCrawl;
//
//            public UrlCrawl(String url, String hostName, HtmlParser htmlParser, BlockingQueue<String> toDoCrawl) {
//                this.url = url;
//                this.hostName = hostName;
//                this.htmlParser = htmlParser;
//                this.toDoCrawl = toDoCrawl;
//            }
//
//            @Override
//            public List<String> call() throws Exception {
//                List<String> urls = htmlParser.getUrls(url);
//                toDoCrawl.addAll(urls);
//                count.decrementAndGet();
//                return urls.stream().filter((u) -> {
//                    if (hostName.equals(extractHostName(u))) return true;
//                    return false;
//                }).collect(Collectors.toList());
//            }
//        }
//    }
//}
