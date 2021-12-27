package com.example.s3823236_assignment1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.ViewHolder> {
    List<Event> eventList;
    Context context;

    //Constructor
    public MyEventAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }


    //Create view holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_event_in_event_list,parent,false);
        return new ViewHolder(view);
    }

    // Render each item in the recycler
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(eventList.get(position).getDate());
        holder.time.setText(eventList.get(position).getTime());
        holder.location.setText(eventList.get(position).getLocation());
        //allow horizontal scroll if the content is too long
        holder.location.setMovementMethod(new ScrollingMovementMethod());
        holder.location.setHorizontallyScrolling(true);
        //allow horizontal scroll if the content is too long
        holder.title.setText(eventList.get(position).getTitle());
        holder.title.setMovementMethod(new ScrollingMovementMethod());
        //allow horizontal scroll if the content is too long
        holder.title.setHorizontallyScrolling(true);
        holder.description.setText(eventList.get(position).getDescription());
        //allow horizontal scroll if the content is too long
        holder.description.setMovementMethod(new ScrollingMovementMethod());
        holder.description.setHorizontallyScrolling(true);
        //Sending the data of the key eventId in intent to the Add Or Edit Activity
        holder.editButton1.setOnClickListener(view -> {
           Intent intent = new Intent(context,AddMoreEvents.class);
           intent.putExtra("eventId",eventList.get(position).getId());
           intent.putExtra("text","Edit Event");
            // Handle activity not found exception
            try {
               context.startActivity(intent);
           }
           catch (ActivityNotFoundException e){
               Toast.makeText(context,"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
           }
        });
        // Remove method
        holder.removeButton1.setOnClickListener(view ->
        {
            //Announce that the item has been removed
            Toast.makeText(context,"You have successfully removed this event" ,Toast.LENGTH_LONG).show();
            eventList.remove(holder.getAdapterPosition());
            //Notify that the data is changed and re-render the whole recycler
            notifyDataSetChanged();
        });

    }

    // Get the size of eventList
    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView date;
        TextView location;
        TextView title;
        TextView description;
        ImageView editButton1;
        ImageView removeButton1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.oneDate);
            time = itemView.findViewById(R.id.oneTime);
            location = itemView.findViewById(R.id.oneLocation);
            title = itemView.findViewById(R.id.oneTitle);
            description = itemView.findViewById(R.id.oneDescription);
            editButton1 = itemView.findViewById(R.id.oneEdit1);
            removeButton1 = itemView.findViewById(R.id.oneRemove1);
        }
    }



}
