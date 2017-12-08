import aspects.Aspect;
import aspects.PostCreateAspect;
import interfaces.ICrawler;
import providers.SingletonProvider;
import services.CrawlerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        TropLeSummer tropLeSummer = new TropLeSummer();

        tropLeSummer.addProvider(
                ICrawler.class,
                new SingletonProvider<ICrawler>(
                        new ArrayList<>(Arrays.asList(
                                new PostCreateAspect(
                                        o -> {System.out.println("bite");},
                                        null
                                ),
                                new PostCreateAspect(
                                        o -> {System.out.println("chatte");},
                                        null
                                )
                        )),
                        new CrawlerService()
                ));

        tropLeSummer.getInstanceOf(ICrawler.class).crawl("https://pastebin.com/i2F3T8ad");
    }
}