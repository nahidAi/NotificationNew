package test.notification.com.notification.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

import co.ronash.pushe.Pushe;
import test.notification.com.notification.Adapter.AdapterFragment;
import test.notification.com.notification.DatabaseHelper;
import test.notification.com.notification.G;
import test.notification.com.notification.NotificationHelper;
import test.notification.com.notification.R;
import test.notification.com.notification.Task;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menuToolbar;
    DatabaseHelper databaseHelper;
    private FloatingActionButton floatingActionButton;
    private Button showNotification;
    private Button btnTapic;
    Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationHelper notificationHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_main);
        Pushe.initialize(this,true);


        Intent intent = new Intent(G.context, Task.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(G.context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2018);
        calendar.set(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,11);
        calendar.set(Calendar.HOUR,1);
        calendar.set(Calendar.MINUTE,32);
        calendar.set(Calendar.SECOND,00);

       // G.alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1*60*1000,pendingIntent);




        databaseHelper = new DatabaseHelper(MainActivity.this);
        context = getApplicationContext();
        setTabOption();
        setNavigationViewAndFloating();
        notificationHelper = new NotificationHelper(context);
       // notificationHelper.ListQuoteAndSendNotification();



    }

    private void setTabOption() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager()));

        TabLayout tabStrip = (TabLayout) findViewById(R.id.tabLayout);
        tabStrip.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();

        }

    }

    public void setNavigationViewAndFloating() {
       // showNotification = (Button) findViewById(R.id.showNotification);
       /* showNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://nikandroid.com/"));

                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                PendingIntent pi1 = PendingIntent.getActivity(MainActivity.this, 0, intent1, 0);


                Notification notification = new NotificationCompat.Builder(MainActivity.this)
                        .setTicker("نوتیف")
                        .setContentTitle("تایتل")
                        //.setContentText("این یک تست است")
                        .setSmallIcon(R.drawable.information)
                        .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText("لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. "))
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow_right))
                        // .setStyle(new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.image_1)))
                        .setAutoCancel(true)
                        .setSound(path)
                        .addAction(R.drawable.arrow_right, "Open Activity", pi)
                        .addAction(R.drawable.arrow_right, "Open Site", pi1)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notification);


            }
        });*/
       Button btnTapic = (Button)findViewById(R.id.btnTapic);
       btnTapic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (Pushe.isPusheInitialized(getApplicationContext())){
                   Pushe.subscribe(getApplicationContext(),"ozveeat dar site");
               }
           }
       });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(floatingActionButton, "کلیک شد", Snackbar.LENGTH_LONG).show();
            }
        });


        menuToolbar = (ImageView) findViewById(R.id.menu_toolbar);
        menuToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.setting) {
                    Toast.makeText(MainActivity.this, " کلیک شد", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

    }


}


