package test.notification.com.notification.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import test.notification.com.notification.DatabaseHelper;
import test.notification.com.notification.Quote;
import test.notification.com.notification.R;

public class Main2Activity extends AppCompatActivity {
    private int id;
    private String name;
    private String more;
    private String content;
    private String imgAddress;
    private int layoutId;
    private String pageName;
    private Quote mQuote;

    private TextView txtContent;
    private TextView txtMore;
    private ImageView imgAvatar;
    private ImageView imgCopy;
    private ImageView imgShare;
    private FloatingActionButton floatingActionButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        layoutId = Integer.parseInt(bundle.getString("id"));
        pageName = bundle.getString("name");
        if (bundle != null) {
            if (pageName.equals("BigPerson")) {
                mQuote = databaseHelper.selectPersonById(layoutId);
                id = mQuote.getId();
                name = mQuote.getName();
                content = mQuote.getContent();
                more = mQuote.getMore();
                imgAddress = mQuote.getImgAddress();


            } else if (pageName.equals("favorite")) {
                mQuote = databaseHelper.selectPersonById(layoutId);
                id = mQuote.getId();
                name = mQuote.getName();
                content = mQuote.getContent();
                more = mQuote.getMore();
                imgAddress = mQuote.getImgAddress();

            }

        }

        txtContent = (TextView) findViewById(R.id.txtContent);
        txtMore = (TextView) findViewById(R.id.txtMore);
        imgAvatar = (ImageView) findViewById(R.id.avatar);
        imgShare = (ImageView) findViewById(R.id.imgShare);
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, content);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, name);
                startActivity(Intent.createChooser(shareIntent, "اشتراک"));

            }
        });
        imgCopy = (ImageView) findViewById(R.id.imgCopy);
        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) Main2Activity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                    final android.content.ClipData clipData = android.content.ClipData.newPlainText(more, content);
                    clipboardManager.setPrimaryClip(clipData);


                } else {
                    final android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) Main2Activity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboardManager.setText(more);
                }
                Snackbar.make(v, " متن کپی شد", Snackbar.LENGTH_LONG).show();
            }
        });
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collAps);
        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        txtContent.setText(content);
        txtMore.setText(more);
        int imgId = getResources().getIdentifier(imgAddress,"drawable",getPackageName());
        imgAvatar.setImageResource(imgId);

        if (databaseHelper.selectFavoriteState(id)){
            floatingActionButton.setImageResource(R.drawable.heart);
        }else {
            floatingActionButton.setImageResource(R.drawable.heart_outline);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.selectFavoriteState(id)){
                    floatingActionButton.setImageResource(R.drawable.heart_outline);
                    databaseHelper.updateUnFavorite(id);
                }else {
                    floatingActionButton.setImageResource(R.drawable.heart);
                    databaseHelper.updateFavorite(id);
                }
            }
        });

    }
}
