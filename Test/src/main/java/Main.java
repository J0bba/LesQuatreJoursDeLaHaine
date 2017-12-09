import aspects.AfterInvokeAspect;
import aspects.Aspect;
import aspects.BeforeInvokeAspect;
import aspects.PostCreateAspect;
import interfaces.ICrawler;
import providers.SingletonProvider;
import services.CrawlerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        TropLeSummer tropLeSummer = new TropLeSummer();

        tropLeSummer.addProvider(
                ICrawler.class,
                new SingletonProvider<ICrawler>(
                        new ArrayList<>(Arrays.asList(
                                new BeforeInvokeAspect(
                                        o -> {System.out.println("before invoke");},
                                        ICrawler.class.getMethod("crawl", String.class),
                                        null
                                ),
                                new AfterInvokeAspect(
                                        o -> {System.out.println("after invoke");},
                                        ICrawler.class.getMethod("crawl", String.class),
                                        null
                                ),
                                new PostCreateAspect(
                                        o -> {System.out.println("post create");},
                                        null
                                )
                        )),
                        new CrawlerService()
                ));

        tropLeSummer.getInstanceOf(ICrawler.class).crawl("https://pastebin.com/i2F3T8ad");
    }
}