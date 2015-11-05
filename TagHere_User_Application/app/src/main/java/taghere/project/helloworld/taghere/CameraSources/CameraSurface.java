package taghere.project.helloworld.taghere.CameraSources;

import android.content.Context;

import android.util.AttributeSet;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by DongKyu on 2015-10-11.
 */
public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public Camera getCamera() {
        return mCamera;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            mCamera.release();
            mCamera = null;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
        Parameters params = mCamera.getParameters();
        params.setPreviewSize(1920, 1080);
        params.setPictureSize(1920, 1080);
        params.setRotation(90);
        try {
            mCamera.setParameters(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
    }
}