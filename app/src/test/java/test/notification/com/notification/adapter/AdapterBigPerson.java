package test.notification.com.notification.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import test.notification.com.notification.R;

public class AdapterBigPerson extends RecyclerView.Adapter<AdapterBigPerson.myViewHolder> {
    public Context context;
    private final List<Quote> persons;


    public AdapterBigPerson(Context context, List<Quote> persons) {
        this.context = context;
        this.persons = persons;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {



    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        ImageView avatar;
        LinearLayout layout_item;

        public myViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.name);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            layout_item = (LinearLayout) itemView.findViewById(R.id.layout_item);
        }
    }


}