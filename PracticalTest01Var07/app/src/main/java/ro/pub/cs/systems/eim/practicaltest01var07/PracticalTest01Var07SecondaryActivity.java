package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    private ButtonClickListener button_click_listener = new ButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        Intent intent = getIntent();
        if(intent != null && intent.getExtras().containsKey("NTextView") && intent.getExtras().containsKey("GTextView")) {
            TextView NTextView = (TextView) findViewById(R.id.textView3);
            TextView GTextView = (TextView) findViewById(R.id.textView4);
            NTextView.setText(intent.getStringExtra("NTextView"));
            GTextView.setText(intent.getStringExtra("GTextView"));
        }
        findViewById(R.id.ok_button).setOnClickListener(button_click_listener);
        findViewById(R.id.cancel_button).setOnClickListener(button_click_listener);

    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED);
                    break;
            }
            finish();
        }
    }
}
