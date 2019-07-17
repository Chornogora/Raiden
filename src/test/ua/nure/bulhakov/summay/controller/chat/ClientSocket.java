package ua.nure.bulhakov.summay.controller.chat;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ClientSocket extends WebSocketClient {

    CyclicBarrier lock;

    public ClientSocket (URI serverUri, CyclicBarrier barrier) throws InterruptedException {
        super(serverUri);
        this.lock = barrier;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String s){
        try {
            if(s.equals("Test")) {
                lock.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {
        System.out.println("Something went wrong on a client: ");
        e.printStackTrace();
    }


}