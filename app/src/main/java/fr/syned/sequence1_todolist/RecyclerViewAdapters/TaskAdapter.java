package fr.syned.sequence1_todolist.RecyclerViewAdapters;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

import fr.syned.sequence1_todolist.Activities.ToDoListActivity;
import fr.syned.sequence1_todolist.R;
import fr.syned.sequence1_todolist.Model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> mDataset;

    public TaskAdapter(List<Task> dataset) {
        mDataset = dataset;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_task, parent, false);
        return new TaskAdapter.TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        UUID uuid;

        public CardView cardView;
        public TextView textView;
        public CheckBox checkBox;

        public TaskViewHolder(@NonNull View v) {
            super(v);
            cardView = v.findViewById(R.id.card_view);
            textView = v.findViewById(R.id.text);
            checkBox = v.findViewById(R.id.checkbox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task selectedTask = ToDoListActivity.toDoList.getTask(uuid);
                    selectedTask.toggleCheckbox();
                    if (selectedTask.isDone()) textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    else textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    Log.i("TAG", "onClick on checkBox: " + selectedTask.isDone());
//                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
//                    intent.putExtra(EXTRA_TODOLIST, ToDoListActivity.toDoList);
//                    ((Activity) v.getContext()).setResult(Activity.RESULT_OK, intent);
                }
            });

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task selectedTask = ToDoListActivity.toDoList.getTask(uuid);
                    checkBox.setChecked(selectedTask.toggleCheckbox());
                    if (selectedTask.isDone()) textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    else textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    Log.i("TAG", "onClick on textView: " + selectedTask.isDone());
//                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
//                    intent.putExtra(EXTRA_TODOLIST, ToDoListActivity.toDoList);
//                    ((Activity) v.getContext()).setResult(Activity.RESULT_OK, intent);
                }
            });
        }

        public void bind(Task task) {
            textView.setText(task.getName());
            Log.i("TAG", "bind: " + task.isDone());
            checkBox.setChecked(task.isDone());
            if (task.isDone()) textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            else textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

            this.uuid = task.getId();
        }
    }
}