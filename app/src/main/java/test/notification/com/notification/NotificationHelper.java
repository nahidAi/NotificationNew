package test.notification.com.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.util.Random;

public class NotificationHelper {
    private final Context mContext;
    Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    public NotificationHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void ListQuoteAndSendNotification() {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        databaseHelper.readQuoteFromDatabase();
        int quoteSize = databaseHelper.readQuoteFromDatabase().size();

        Random rand = new Random();
        int n = rand.nextInt(quoteSize);
        databaseHelper.readQuoteFromDatabase().get(n);

        Toast.makeText(mContext, "size is :" + databaseHelper.readQuoteFromDatabase().size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, "" + n, Toast.LENGTH_SHORT).show();

        setNotification(databaseHelper.readQuoteFromDatabase().get(n));


    }

    public void setNotification(Quote quote) {
        Intent intent = new Intent(mContext, Main3Activity.class);
        Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://nikandroid.com/"));

        PendingIntent pi = PendingIntent.getActivity(mContext, 0, intent, 0);
        PendingIntent pi1 = PendingIntent.getActivity(mContext, 0, intent1, 0);


        Notification notification = new NotificationCompat.Builder(mContext)
                .setTicker("نوتیف")
                .setContentTitle(quote.getName())
                .setContentText("این یک تست است")
                .setSmallIcon(R.drawable.information)
                .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText(quote.getMore()))
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.arrow_right))
                // .setStyle(new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.image_1)))
                .setAutoCancel(true)
                .setSound(path)
                .addAction(R.drawable.arrow_right, "Open Activity", pi)
                .addAction(R.drawable.arrow_right, "Open Site", pi1)
                .build();
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);


    }
}
