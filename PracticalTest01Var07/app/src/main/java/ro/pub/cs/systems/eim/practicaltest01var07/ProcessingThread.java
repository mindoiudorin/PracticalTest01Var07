package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by bordin on 4/1/16.
 */
public class ProcessingThread  extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private String NTextViewContent;
    private String GTextViewContent;
    boolean myBool = true;

    public ProcessingThread(Context context, String NTextViewContent, String GTextViewContent) {
        this.context = context;
        this.NTextViewContent = NTextViewContent;
        this.GTextViewContent = GTextViewContent;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "run: ");
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        if(myBool) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra("message", new Date(System.currentTimeMillis()) +
                    " " + NTextViewContent);
            myBool = false;
            context.sendBroadcast(intent);
        }
        else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.putExtra("message", new Date(System.currentTimeMillis()) +
                    " " + GTextViewContent);
            myBool = true;
            context.sendBroadcast(intent);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
