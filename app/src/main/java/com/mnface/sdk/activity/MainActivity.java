package com.mnface.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.immomo.mncertification.MNFCService;
import com.immomo.mncertification.callback.OnCompareResultCallback;
import com.immomo.mncertification.callback.OnSearchResultCallback;
import com.immomo.mncertification.constance.MNFCResultCode;
import com.immomo.mncertification.resultbean.CertificationResult;
import com.immomo.mncertification.resultbean.CompareResult;
import com.immomo.mncertification.resultbean.SearchResult;
import com.mnface.sdk.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String TAG = "mnfc_test_main";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    boolean isLoaded;

    @Override
    protected void onResume() {
        super.onResume();
        if (!isLoaded) {
            isLoaded = true;
        }

    }

    public void cooCertification(View view) {
        MNFCService.Config config = MNFCService.Config.obtain();
        config.actionCount = 5;
        config.title = "人脸扫描";
        MNFCService.getInstance().startInteractiveCertification(this, REQUEST_CODE, config);
    }

    public void normalCertification(View view) {
        MNFCService.getInstance().startSilentCertification(this, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode) {
            CertificationResult result = MNFCService.getInstance().fetchResult(data);
            if (result.resultCode == MNFCResultCode.SUCCESS) {
                Toast.makeText(this, "success" + result.personId, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "error:" + result.resultCode, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void matchTest(View view) {
        //replace your image file path here
        MNFCService.getInstance().comparePersonWithImg(new File("/sdcard/me.jpg"), "1327731",
                new OnCompareResultCallback() {
                    @Override
                    public void onSuccess(CompareResult result) {
                        Log.d(TAG, "onResult:  code: " + result.resultCode
                                + "\npersonId" + result.personId
                                + "\nscore:" + result.score);
                    }

                    @Override
                    public void onFailure(int errorCode) {
                        Log.d(TAG, "onFailure:   " + errorCode);

                    }
                });
    }

    public void matchUrlTest(View view) {
        MNFCService.getInstance().comparePersonWithUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559312662571&di=b633e2aee7989fc607791af5fcc204c6&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201503%2F22%2F20150322094814_UEmhZ.jpeg",
                "5618", new OnCompareResultCallback() {
                    @Override
                    public void onSuccess(CompareResult result) {
                        Log.d(TAG, "onResult:    personId:"
                                + result.personId
                                + ",score:" + result.score);
                    }

                    @Override
                    public void onFailure(int errorCode) {
                        Log.d(TAG, "onFailure: " + errorCode);
                    }
                });
    }

    public void search(View view) {
        //replace your image file path here
        MNFCService.getInstance().searchPersonByImg("1022", new File("/sdcard/me.jpg"), 10, 0.4f, new OnSearchResultCallback() {
            @Override
            public void onSuccess(SearchResult results) {
                Log.d(TAG, "onSuccess: 共返回:"
                        + results.persons.size() + " 条数据");
            }

            @Override
            public void onFailure(int code) {
                Log.d(TAG, "onFailure: code:" + code);
            }
        });
    }

    public void searchUrl(View view) {
        MNFCService.getInstance().searchPersonByUrl("1022", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559312662571&di=b633e2aee7989fc607791af5fcc204c6&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201503%2F22%2F20150322094814_UEmhZ.jpeg",
                10, 0.4f, new OnSearchResultCallback() {
                    @Override
                    public void onSuccess(SearchResult results) {
                        Log.d(TAG, "onSuccess: 共返回:" + results.persons.size() + " 条数据");
                    }

                    @Override
                    public void onFailure(int code) {
                        Log.d(TAG, "onFailure: code:" + code);
                    }
                });
    }

    public void personSearch(View view) {
        MNFCService.getInstance().searchPersonByPersonId("13", "5618", 10, 0.4f, new OnSearchResultCallback() {
            @Override
            public void onSuccess(SearchResult results) {
                Log.d(TAG, "onSuccess: 共返回:"
                        + results.persons.size() + " 条数据");
            }

            @Override
            public void onFailure(int code) {
                Log.d(TAG, "onFailure: code:" + code);
            }
        });
    }
}
