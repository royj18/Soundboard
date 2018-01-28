package space.itsjosh.soundboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SoundboardRecyclerAdapter extends RecyclerView.Adapter<SoundboardRecyclerAdapter.SoundboardViewHolder>{

    private ArrayList<SoundObject> soundObjects;

    SoundboardRecyclerAdapter(ArrayList<SoundObject> soundObjects){

        this.soundObjects = soundObjects;
    }

    @Override
    public SoundboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_item, parent, false);

        return new SoundboardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SoundboardViewHolder holder, int position) {

        final SoundObject object = soundObjects.get(position);

        final Integer soundID = object.getItemID();

        holder.itemTextView.setText(object.getItemName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            EventHandlerClass.startMediaPlayer(v, soundID);
            }
        });

        //May use in future
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//            }
//        });
    }

    // Tells the RecyclerView how many items are accessible to be displayed
    // Should return the size of the given content provider (here: SoundObject ArrayList)
    @Override
    public int getItemCount() {
        return soundObjects.size();
    }

    // Gets all accessible areas that are declared by you
    class SoundboardViewHolder extends RecyclerView.ViewHolder{

        // TextView to display the name of a sound button
        TextView itemTextView;

        SoundboardViewHolder(View itemView) {
            super(itemView);

            // Assign itemTextView to the TextView item declared in sound_item.xml
            itemTextView = itemView.findViewById(R.id.textViewItem);
        }
    }
}