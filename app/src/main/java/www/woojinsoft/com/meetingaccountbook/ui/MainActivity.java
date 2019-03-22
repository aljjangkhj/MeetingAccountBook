package www.woojinsoft.com.meetingaccountbook.ui;

import android.os.Bundle;

import www.woojinsoft.com.meetingaccountbook.BaseActivity;
import www.woojinsoft.com.meetingaccountbook.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_autoscreen(1);
    }
}
