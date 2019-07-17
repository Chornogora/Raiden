package ua.nure.bulhakov.summay.controller.chat;

import org.junit.Before;
import org.junit.Test;
import ua.nure.bulhakov.summary.controller.chat.SocketDispatcher;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ChatTest {

    private static final String HOSTNAME = "localhost";

    @Before
    public void setUp(){
        SocketDispatcher.init(HOSTNAME);
    }

    @Test(timeout = 1500)
    public void doChat() throws URISyntaxException, InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(2);

        int port = SocketDispatcher.createChatSocket();
        URI temp = new URI(String.format("ws://%s:%d/point", HOSTNAME, port));
        ClientSocket client = new ClientSocket(temp, barrier);
        client.connect();

        port = SocketDispatcher.getChatPort();
        URI temp2 = new URI(String.format("ws://%s:%d/point", HOSTNAME, port));
        ClientSocket admin = new ClientSocket(temp2, null);
        admin.connect();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                admin.send("Test");
            }
        };
        timer.schedule(task, 1000);

        barrier.await();
    }

}
