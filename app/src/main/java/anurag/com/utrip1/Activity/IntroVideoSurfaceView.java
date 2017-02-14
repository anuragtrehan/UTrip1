package anurag.com.utrip1.Activity;

/**
 * Created by AnuragTrehan on 12/20/2016.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import anurag.com.utrip1.R;

public class IntroVideoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private MediaPlayer mp;
    private boolean has_started = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public IntroVideoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public IntroVideoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public IntroVideoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IntroVideoSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mp = new MediaPlayer();
        getHolder().addCallback(this);
    }

    @Override public void surfaceCreated(SurfaceHolder holder) {
        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.delhi);
        try {
            if (!has_started) {
                has_started = true;
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            }

            mp.prepare();
            android.view.ViewGroup.LayoutParams lp = getLayoutParams();
            lp.height = getHeight();
            lp.width = getWidth();

            setLayoutParams(lp);
            mp.setDisplay(getHolder());
            mp.setLooping(true);
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override public void surfaceDestroyed(SurfaceHolder holder) {
        mp.stop();
    }
}