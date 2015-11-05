package taghere.project.helloworld.taghere;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;


import taghere.project.helloworld.taghere.CameraSources.*;

public class ScanActivity extends AppCompatActivity {
    private CameraSurface cameraSurface;
    private TextView qrCodeDataView;
    private boolean scanQR;
    private boolean linkPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        cameraSurface = (CameraSurface) findViewById(R.id.camera_surdface);
        qrCodeDataView = (TextView) findViewById(R.id.qr_code_data);

        scanQR = false;
        linkPreview = false;
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        cameraSurface.getCamera().stopPreview();
        cameraSurface.getCamera().release();
        super.onStop();
    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
        }
    }

    public void onCameraPreviewClick(View v) {
        scanQR = true;

        if(!linkPreview) {
            cameraSurface.getCamera().setPreviewCallback(mPreview);
            linkPreview = true;
        }
        cameraSurface.getCamera().autoFocus(mAutoFocus);
    }

    Camera.AutoFocusCallback mAutoFocus = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                scanQR = true;
            }
        }
    };

    Camera.PreviewCallback mPreview = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(data,
                    camera.getParameters().getPreviewSize().width,
                    camera.getParameters().getPreviewSize().height, 0, 0,
                    camera.getParameters().getPreviewSize().width,
                    camera.getParameters().getPreviewSize().height, false);

            BinaryBitmap b = new BinaryBitmap(new HybridBinarizer(source));
            QRCodeReader reader = new QRCodeReader();

            try {
                Result result = reader.decode(b);

                // When QR code read Success
                if (result.getText() != null) {
                    Log.i("FIND QR!", ">>" + result.getText());
                    qrCodeDataView.setText(result.getText());
                }

            } catch (NotFoundException e) {
                Log.i("err QR", "Cannot find QR code");
            } catch (ChecksumException e) {
                Log.i("err QR", "Checksum error in QR code");
            } catch (FormatException e) {
                Log.i("err QR", "Format error in QR code");
            }
        }
    };
}
