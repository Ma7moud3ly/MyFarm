package myfarm.ma7moud3ly.com;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CultivationAdapter extends RecyclerView.Adapter<CultivationAdapter.MyViewHolder> {

    private ArrayList<ArrayList<String>> myList;
    private Context c;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.cultivation_name);

        }
    }


    public CultivationAdapter(ArrayList<ArrayList<String>> myList, Context c) {
        this.myList = myList;
        this.c = c;
    }

    @Override
    public CultivationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cultivation_item, parent, false);
        return new CultivationAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CultivationAdapter.MyViewHolder holder, int position) {
        ArrayList<String> s = myList.get(position);
        String type = s.get(1);
        String acres = s.get(2);
        holder.name.setText(type + " - " + acres + " فدان");
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

}


