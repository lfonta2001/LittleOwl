package online.theowlery.core;

import online.theowlery.types.annotations.CoreClass;

@CoreClass
public class Bot {
    // At this point the responsability of loading will be transferred to ReadyListener.
    // As of this moment this class has no use, but it will probably be used in the future so it stays.

//
//    private final Loader loader;
//    private final JDA client;
//
//    public Bot(JDA client, Loader loader) {
//        this.client = client;
//        this.loader = loader;
//    }
//
//    public void start() throws InterruptedException {
//        client.awaitReady();
//        loader.load();
//    }
}
