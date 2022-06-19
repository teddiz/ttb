package com.example.newttb.Activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newttb.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.Viewholder> {

    private Context context;
    private ArrayList<CourseModel> courseModelArrayList;

    // Constructor
    public CourseAdapter(Context context, ArrayList<CourseModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public CourseAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        return new Viewholder(view);
        

    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.Viewholder holder, final int position) {
        // to set data to textview and imageview of each card layout
        CourseModel model = courseModelArrayList.get(position);
        holder.courseNameTV.setText(model.getCourse_name());
        holder.courseRatingTV.setText("" + model.getCourse_rating());
        holder.courseIV.setImageResource(model.getCourse_image());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (model.getCourse_name()){
                    case "TTB RJ45":
                        context.startActivity(new Intent(context,AddRj45Activity.class));
                        break;
                }
                /*Toast.makeText(context,model.getCourse_name(),Toast.LENGTH_SHORT).show();


                /*if(model.getCourse_name().equals("TTB RJ45")){
                    intent=new Intent(context,AddRj45Activity.class);
                    context.startActivity(intent);

                }*/

                /*Intent intent;
                switch (position){
                    case 0:
                       intent=new Intent(context,AddRj45Activity.class);
                       context.startActivity(intent);
                        break; */


                }

        });




    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return courseModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView courseIV;
        private TextView courseNameTV, courseRatingTV;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourseImage);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseRatingTV = itemView.findViewById(R.id.idTVCourseRating);
        }
    }
}
