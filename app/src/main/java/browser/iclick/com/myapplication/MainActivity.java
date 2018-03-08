package browser.iclick.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity_TAG";

    private Button gaidBtn;
    private Button purchase;

    private TextView attributeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gaidBtn = findViewById(R.id.gaid_btn);
        gaidBtn.setOnClickListener(this);

        purchase = findViewById(R.id.purchase);
        purchase.setOnClickListener(this);

        attributeContent = findViewById(R.id.attribute_content);

        TracingTool.deepLinkTracing(this);
        TracingTool.appsFlayerEventTag(getApplicationContext(), "main_activity_create");

        initAttributeContent();
    }

    private void initAttributeContent() {
        Map<String, String> map = AttributeTool.getInstance().getAttributeMap();
        StringBuilder sb = new StringBuilder();
        for (String attrName : map.keySet()) {
            sb.append(attrName);
        }
        attributeContent.setText(sb.toString());
    }

    private void getGoogleAdvertisingId() {
        Thread thread = new Thread () {
            @Override
            public void run() {
                super.run();
                AdvertisingIdClient.Info adInfo = null;
                try {
                    adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                    Log.d(TAG, "adInfo: " + adInfo.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gaid_btn:
                getGoogleAdvertisingId();
                break;

            case R.id.purchase:
                TracingTool.revenueEventTracing(getApplicationContext(), 200,
                        "category_a", "1234567", "USD");
                break;


            default:
                break;
        }
    }

}










