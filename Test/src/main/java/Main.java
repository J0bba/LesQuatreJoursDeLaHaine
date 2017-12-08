import aspects.Aspect;
import interfaces.ICrawler;
import providers.SingletonProvider;
import services.CrawlerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        TropLeSummer tropLeSummer = new TropLeSummer();

        try {
            tropLeSummer.addProvider(
                    ICrawler.class,
                    new SingletonProvider<ICrawler>(new CrawlerService(),
                    new ArrayList<>(Arrays.asList(
                            new Aspect(
                                    Aspect.AspectType.BEFORE_INVOKE,
                                    o -> {System.out.println("bite");},
                                    null,
                                    ICrawler.class.getMethod("crawl", String.class)
                            )
                    ))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        tropLeSummer.getInstanceOf(ICrawler.class).crawl("https://pastebin.com/i2F3T8ad");
    }
}