package taghere.project.helloworld.taghere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.client.android.camera.CameraConfigurationUtils;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.scan_button:
                (new Toast(this)).makeText(this, "Hello World!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
