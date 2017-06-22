package kr.co.ipdisk.hp5959.intake;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private SharedPreferences pref = null;
    private String time1 = null;
    private String time2 = null;
    private String time3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView viewTime = (TextView)findViewById(R.id.viewTime);
        final Button btn        = (Button)findViewById(R.id.btnMeal);

        pref = getPreferences(MODE_PRIVATE);

        viewTime.setText(readTimes());

        View.OnClickListener e = new View.OnClickListener() {
            @Override
            public void onClick(final View view)
            {
                writeTimes();
                viewTime.setText(readTimes());
                Toast.makeText(getApplicationContext(), " 적용되었습니다.", Toast.LENGTH_SHORT).show();
            }
        };

        btn.setOnClickListener(e);
    }

    private String readTimes()
    {
        time1 = pref.getString("time1", "미입력");
        time2 = pref.getString("time2", "미입력");
        time3 = pref.getString("time3", "미입력");

        return time1 + "\n" + time2 + "\n" + time3;
    }

    private void writeTimes()
    {
        time1 = time2;
        time2 = time3;
        time3 = getCurrentTime();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("time1", time1);
        editor.putString("time2", time2);
        editor.putString("time3", time3);
        editor.apply();
    }

    private String getCurrentTime()
    {
        final Calendar cal = Calendar.getInstance();
        final int year     = cal.get(Calendar.YEAR);
        final int month    = cal.get(Calendar.MONTH)+1;
        final int day      = cal.get(Calendar.DAY_OF_MONTH);
        final int hour     = cal.get(Calendar.HOUR_OF_DAY);
        final int minute   = cal.get(Calendar.MINUTE);

        return year+"/"+month+"/"+day+" "+hour+":"+minute;
    }
}