package oncall.dell.oncallapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoView = new VideoView(this);
        setContentView(videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashsecreen);
        videoView.setVideoURI(uri);

        videoView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent a=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(a);
                finish();
            }
        },1000);
    }
}
