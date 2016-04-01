package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var07Service extends Service {

    private ProcessingThread procThread = null;

    public PracticalTest01Var07Service() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String NTextViewContent = intent.getStringExtra("NTextView");
        String GTextViewContent = intent.getStringExtra("GTextView");
        procThread = new ProcessingThread(this, NTextViewContent, GTextViewContent);
        procThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        procThread.stopThread();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}
