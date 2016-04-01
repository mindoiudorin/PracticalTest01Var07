package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    boolean serviceStatus = false;

    EditText TextView1 = null;
    EditText TextView2 = null;

    private CkeckBoxListener button_click_listener = new CkeckBoxListener();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private myBroadcastReceiver broadcastReceiver = new myBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        TextView1 = (EditText) findViewById(R.id.textView);
        TextView2 = (EditText) findViewById(R.id.textView2);

        ((CompoundButton) findViewById(R.id.checkBox)).setOnCheckedChangeListener(button_click_listener);
        ((CompoundButton) findViewById(R.id.checkBox2)).setOnCheckedChangeListener(button_click_listener);

        findViewById(R.id.button).setOnClickListener(buttonClickListener);

        intentFilter.addAction(Intent.ACTION_SEND);
        intentFilter.addAction(Intent.ACTION_PICK);
    }

    @Override
    protected void onResume () {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause () {
        unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        //super.onSaveInstanceState(savedInstanceState);
        Log.d("onSaveInstanceState", " AHAAAA ");
        savedInstanceState.putString("TextView1", TextView1.getText().toString());
        savedInstanceState.putString("TextView2", TextView2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        Log.d("onRestoreInstanceState", " AHAAAA ");
        if (savedInstanceState.containsKey("TextView1")) {
            TextView1.setText(savedInstanceState.getString("leftNo", ""));
        }
        if (savedInstanceState.containsKey("TextView2")) {
            TextView2.setText(savedInstanceState.getString("rightNo", ""));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_FIRST_USER) {
            Toast.makeText(this, "result code : " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    private class CkeckBoxListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            switch (buttonView.getId()) {
                case R.id.checkBox:
                    if(isChecked)
                        TextView1.setEnabled(true);
                    else
                        TextView1.setEnabled(false);
                    break;
                case R.id.checkBox2:
                    if(isChecked)
                        TextView2.setEnabled(true);
                    else
                        TextView2.setEnabled(false);
                    break;
            }
            if(!TextView1.isEnabled() && !TextView2.isEnabled() && TextView1.getText().toString() != "" && TextView2.getText().toString() != "") {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
                intent.putExtra("NTextView", TextView1.getText().toString());
                intent.putExtra("GTextView", TextView2.getText().toString());
                startService(intent);
                serviceStatus = true;
            }
        }
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                    intent.putExtra("NTextView", TextView1.getText().toString());
                    intent.putExtra("GTextView", TextView2.getText().toString());
                    startActivityForResult(intent, RESULT_FIRST_USER);
                    break;
            }
        }
    }
    private class myBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[BroadcastReceiver]", intent.getStringExtra("message"));
        }
    }

}
