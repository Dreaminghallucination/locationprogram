package mainclass.liu.com.intentexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/28.
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

      Toast.makeText(context,"in using!",Toast.LENGTH_LONG).show();
        Log.w("mylog","in using!");
    }
}
