import aspects.AfterInvokeAspect;
import aspects.AroundInvokeAspect;
import aspects.BeforeInvokeAspect;
import aspects.PostCreateAspect;
import interfaces.ICrawler;
import providers.SingletonProvider;
import services.CrawlerService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        TropLeSummer tropLeSummer = new TropLeSummer();

        tropLeSummer.addProvider(
                ICrawler.class,
                new SingletonProvider<>(
                        new ArrayList<>(Arrays.asList(
                                new BeforeInvokeAspect(
                                        o -> System.out.println("before invoke"),
                                        ICrawler.class.getMethod("crawl", String.class),
                                        null
                                ),
                                new AfterInvokeAspect(
                                        o -> System.out.println("after invoke"),
                                        ICrawler.class.getMethod("crawl", String.class),
                                        null
                                ),
                                new PostCreateAspect(
                                        o -> System.out.println("post create"),
                                        null
                                ),
                                new AroundInvokeAspect(
                                        context -> {
                                            System.out.println("before around 1");
                                            Object res = null;
                                            try {
                                                res = context.execute();
                                            } catch (InvocationTargetException | IllegalAccessException e) {
                                                e.printStackTrace();
                                            }
                                            System.out.println("after around 1");
                                            return res;
                                        },
                                        ICrawler.class.getMethod("crawl", String.class)
                                ),
                                new AroundInvokeAspect(
                                        context -> {
                                            System.out.println("before around 2");
                                            Object res = null;
                                            try {
                                                res = context.execute();
                                            } catch (InvocationTargetException | IllegalAccessException e) {
                                                e.printStackTrace();
                                            }
                                            System.out.println("after around 2");
                                            return res;
                                        },
                                        ICrawler.class.getMethod("crawl", String.class)
                                )
                        )),
                        new CrawlerService()
                ));

        tropLeSummer.getInstanceOf(ICrawler.class).crawl("https://pastebin.com/i2F3T8ad");
    }
}