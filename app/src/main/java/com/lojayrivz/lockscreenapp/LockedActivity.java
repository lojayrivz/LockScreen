package com.lojayrivz.lockscreenapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.icu.util.ULocale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import io.github.homelocker.app.HomeKeyLocker;

public class LockedActivity extends AppCompatActivity implements View.OnClickListener{

   // private ToggleButton mTbLock;
    //private HomeKeyLocker mHomeKeyLocker;
  //  public int x;

    private Button lock,disable,enable,override;
    public static final int RESULT_ENABLE = 1;
    private DevicePolicyManager devicePolicyManager;
    private ActivityManager activityManager;
    private ComponentName compName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked);

        devicePolicyManager=(DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        activityManager=(ActivityManager) getSystemService(ACTIVITY_SERVICE);
        compName=new ComponentName(this, MyAdmin.class);

        lock= (Button) findViewById(R.id.button5);
        enable = (Button) findViewById(R.id.override);
        disable=(Button) findViewById(R.id.tb_lock);
        override=(Button) findViewById(R.id.disable);

        lock.setOnClickListener(this);
        enable.setOnClickListener(this);
        disable.setOnClickListener(this);
        override.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isActive = devicePolicyManager.isAdminActive(compName);
    }

    @Override
    public void onClick(View v) {
        if(v==lock){
            boolean active = devicePolicyManager.isAdminActive(compName);

            if(active){
                devicePolicyManager.lockNow();
            }else{
                Toast.makeText(this, "You need to enable Admin", Toast.LENGTH_SHORT).show();
            }
        }else if (v==enable){

            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"It is needed to lock the screen remotely");
            startActivityForResult(intent, RESULT_ENABLE);

        }else if (v==disable){
            devicePolicyManager.removeActiveAdmin(compName);
            disable.setVisibility(View.GONE);
            disable.setVisibility(View.VISIBLE);
        }else if (v==override){
            openUnlocking();
        }
    }

    public void openUnlocking(){
        Intent intent =new Intent(this,LoginActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case RESULT_ENABLE:
                if(resultCode== Activity.RESULT_OK){
                    Toast.makeText(LockedActivity.this,"You have enabled",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LockedActivity.this,"You have not enabled",Toast.LENGTH_SHORT).show();
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_APP_SWITCH)) {
            Intent nextFlow = new Intent(this, LockedActivity.class);
            startActivity(nextFlow);
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent nextFlow = new Intent(this, LockedActivity.class);
            startActivity(nextFlow);
            return true;
        }else if ((keyCode == KeyEvent.KEYCODE_SETTINGS)) {
            Intent nextFlow = new Intent(this, LockedActivity.class);
            startActivity(nextFlow);
            return true;
        }else if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            Intent nextFlow = new Intent(this, LockedActivity.class);
            startActivity(nextFlow);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void onAttachedToWindow() {
        // TODO Auto-generated method stub
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }

}
