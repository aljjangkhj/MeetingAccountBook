package www.woojinsoft.com.meetingaccountbook.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import www.woojinsoft.com.meetingaccountbook.BaseActivity;
import www.woojinsoft.com.meetingaccountbook.R;

public class IntroActivity extends BaseActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1; //권한 result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init_autoscreen(1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext,MainActivity.class);
                intent.putExtra("","");
                MoveToActivity(intent);
                finish();
            }
        },2000);
    }

    public void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission_group.STORAGE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
                //권한 묻기
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission_group.STORAGE, Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                //권한이 있을때
                goToMain();
            }
        }else
        {
            //버전 23 미만
            goToMain();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                for (int i = 0; i < permissions.length; i++)
                {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.CAMERA) ||
                            permission.equals(Manifest.permission_group.STORAGE) ||
                            permission.equals(Manifest.permission.READ_CONTACTS)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                            //권한이 있을때
                            goToMain();
                        } else {
                            //권한이 없을때
                            checkPermission();
                        }
                    }
                }
                break;
        }
    }
}
