package com.example.yodo1.yodo1advert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yodo1.advert.callback.BannerCallback;
import com.yodo1.advert.callback.InterstitialCallback;
import com.yodo1.advert.callback.VideoCallback;
import com.yodo1.advert.entity.AdErrorCode;
import com.yodo1.advert.entity.BannerAlign;
import com.yodo1.advert.open.Yodo1Advert;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//初始化SDK
        Yodo1Advert.initSDK(this, "LFo9FuZSz");
        Yodo1Advert.setOnLog(true);
    }

    private void initView(){
        Button btn_advert_show_interstitial = (Button) findViewById(R.id.btn_advert_show_interstitial);
        Button btn_advert_show_video = (Button) findViewById(R.id.btn_advert_show_video);


        btn_advert_show_interstitial.setOnClickListener(this);
        btn_advert_show_video.setOnClickListener(this);


        Button btn_advert_show_banner = (Button) findViewById(R.id.btn_advert_show_banner);
        Button btn_advert_setBannerAlign = (Button) findViewById(R.id.btn_advert_setBannerAlign);
        Button btn_advert_hideBanner = (Button) findViewById(R.id.btn_advert_hideBanner);


        btn_advert_setBannerAlign.setOnClickListener(this);
        btn_advert_show_banner.setOnClickListener(this);
        btn_advert_hideBanner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_advert_show_interstitial: //显示插屏广告

//                boolean hsaAds = Yodo1Advert.interstitialIsReady(this);

                Yodo1Advert.showInterstitial(MainActivity.this, new InterstitialCallback() {


                    @Override
                    public void onInterstitialClosed() {
                        Toast.makeText(MainActivity.this,"插屏广告播放完成",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onInterstitialShowSucceeded() {

                    }

                    @Override
                    public void onInterstitialShowFailed(AdErrorCode adErrorCode) {
                        Toast.makeText(MainActivity.this,"插屏广告播放失败",Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onInterstitialClicked() {

                    }

                });


                break;


            case R.id.btn_advert_show_video: //显示视频广告
                if (Yodo1Advert.videoIsReady(MainActivity.this)){


                    boolean b = Yodo1Advert.showVideo(MainActivity.this, new VideoCallback() {


                        @Override
                        public void onVideoClosed(boolean isFinished) {
                            Toast.makeText(MainActivity.this,"视屏广告关闭 " + isFinished,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onVideoShow() {

                        }

                        @Override
                        public void onVideoShowFailed(AdErrorCode adErrorCode) {
                            Toast.makeText(MainActivity.this,"视频屏广告播放失败",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onVideoClicked() {

                        }

                    });
                }else {
                    Toast.makeText(MainActivity.this, "播放失败, 没有可播放放的视频", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn_advert_show_banner: //显示Banner广告
                Yodo1Advert.ShowBanner(MainActivity.this, new BannerCallback() {

                    @Override
                    public void onBannerClosed() {

                    }

                    @Override
                    public void onBannerShow() {
                        Toast.makeText(MainActivity.this,"banner show",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBannerShowFailed(AdErrorCode errorCode) {
                        Toast.makeText(MainActivity.this, "Banner播放失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBannerClicked() {

                    }
                });
                break;
            case R.id.btn_advert_hideBanner: //隐藏Banner广告
                Yodo1Advert.HideBanner(MainActivity.this);
                break;

            case R.id.btn_advert_setBannerAlign: //设置Banner展示位置
                Yodo1Advert.SetBannerAlign(MainActivity.this, BannerAlign.HorizontalCenter|BannerAlign.Bottom);

                break;
        }
    }

    /* 实现全部生命周期方法 */
    @Override
    protected void onResume() {
        super.onResume();
        Yodo1Advert.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Yodo1Advert.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Yodo1Advert.onDestroy(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Yodo1Advert.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Yodo1Advert.onStop(this);
    }
}
