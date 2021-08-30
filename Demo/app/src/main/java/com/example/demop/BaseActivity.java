package com.example.demop;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.demop.server.CloudGameApi;
import com.example.demop.server.param.ServerResponse;
import com.google.gson.Gson;
import org.json.JSONObject;

public abstract class BaseActivity extends AppCompatActivity {
    // 云游体验后台交互接口
    private CloudGameApi mCloudGameApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCloudGameApi.stopGame();
    }

    // 窗口全屏
    private void initWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // 获取到服务端server session的回调
    public abstract void onStartGame(String serverSession);

    // 开始游戏: 获取服务端server session
    protected void startGame(String gameCode, String clientSession) {
        Log.i(Constant.TAG, "start game");
        mCloudGameApi = new CloudGameApi(this);
        mCloudGameApi.startGame(gameCode, clientSession, new CloudGameApi.IServerSessionListener() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.i(Constant.TAG, "onSuccess: " + result);
                ServerResponse resp = new Gson().fromJson(result.toString(), ServerResponse.class);
                if (resp.Code == 0) {
                    onStartGame(resp.ServerSession);
                } else {
                    Toast.makeText(BaseActivity.this, result.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailed(String msg) {
                Log.i(Constant.TAG, msg);
            }
        });
    }
}
