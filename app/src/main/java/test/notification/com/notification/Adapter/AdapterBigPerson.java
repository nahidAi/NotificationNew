package test.notification.com.notification.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import test.notification.com.notification.Activity.Main2Activity;
import test.notification.com.notification.Quote;
import test.notification.com.notification.R;

public class AdapterBigPerson extends RecyclerView.Adapter<AdapterBigPerson.myViewHolder> {
    public Context context;
    private  final List<Quote>persons;


    public AdapterBigPerson(Context context,List<Quote>persons) {
        this.context = context;
        this.persons = persons;

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        final  Quote q = persons.get(position);
        holder.personName.setText(q.getName());
        String imgAddress = q.getImgAddress();
        int id = context.getResources().getIdentifier(imgAddress,"drawable",context.getPackageName());
        holder.avatar.setImageResource(id);
        holder.layoutItem.setId(position);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = q.getId();
                Intent intent = new Intent(context, Main2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name","BigPerson");
                intent.putExtra("id",position+"");
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView personName;
        LinearLayout layoutItem;


        public myViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.avatar);
            personName = (TextView)itemView.findViewById(R.id.PersonName);
            layoutItem = (LinearLayout)itemView.findViewById(R.id.layout_item);

        }
    }


}