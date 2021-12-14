package com.masterandroid.myapplication;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {
    DatabaseHelper myDb;
    Context context;
    ArrayList<Todo> todos;
    MainActivity ma;

    public RecyclerViewAdapter(Context context, ArrayList<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyHolder myHolder, int position) {
        myHolder.title.setText(todos.get(position).getTitle());
        myHolder.id.setText(todos.get(position).getId());


    }


    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView title, id;
        ImageButton  editButton;
        ImageButton deleteButton;
        public MyHolder(@NonNull View view) {
            super(view);
            title = (TextView) itemView.findViewById(R.id.title);
            id = (TextView) itemView.findViewById(R.id.id);

            editButton = (ImageButton) itemView.findViewById(R.id.EditButton);
            editButton.setOnClickListener(this::onClick);

            deleteButton = (ImageButton) itemView.findViewById(R.id.DeleteButton);
            deleteButton.setOnClickListener(this::onClick);

        }

        public void onClick(View v) {
            if(v.getId()==R.id.EditButton){
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("title", todos.get(getAdapterPosition()).getTitle());
                intent.putExtra("status", todos.get(getAdapterPosition()).getStatus());
                intent.putExtra("id", todos.get(getAdapterPosition()).getId());
                context.startActivity(intent);
            }
              else  if(v.getId()==R.id.DeleteButton){
                    String del_id = todos.get(getAdapterPosition()).getId();
                    try{
                        ma.del(del_id);
                    }
                    catch (Exception e){
                        System.out.println("");
                    }


                }
        }
    }
}
