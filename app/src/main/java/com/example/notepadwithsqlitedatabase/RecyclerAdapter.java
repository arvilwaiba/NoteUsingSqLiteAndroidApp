package com.example.notepadwithsqlitedatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListHolder> {
    ArrayList<Data> arrayList= new ArrayList<>();
    Context mContext;
    SqlLiteHelper myDb;


    public RecyclerAdapter(ArrayList<Data> arrayList,Context mContext,SqlLiteHelper myDb) {
        this.arrayList=arrayList;
        this.mContext=mContext;
        this.myDb=myDb;
//        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_display,viewGroup,false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, final int i) {
        listHolder.id.setText(String.valueOf(arrayList.get(i).getId()));
        listHolder.name.setText(arrayList.get(i).getFirstname()+" "+arrayList.get(i).getLastname());
        listHolder.marks.setText(String.valueOf(arrayList.get(i).getMark()));

        listHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dataPassIntent=new Intent(mContext,AddDataActivity.class);
                dataPassIntent.putExtra("id",arrayList.get(i).getId());
                dataPassIntent.putExtra("firstname",arrayList.get(i).getFirstname());
                dataPassIntent.putExtra("lastname",arrayList.get(i).getLastname());
                dataPassIntent.putExtra("mark",arrayList.get(i).getMark());
                mContext.startActivity(dataPassIntent);
            }
        });

        listHolder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Delete")
                        .setMessage("Do you really want to delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
//                        Toast.makeText(MainActivity.this, "Yaay", Toast.LENGTH_SHORT).show();
                                Editable id=Editable.Factory.getInstance().newEditable(arrayList.get(i).getId());
                                Integer isDelete =myDb.deleteDta(id);
                                if (isDelete > 0){
                                    Toast.makeText(mContext, "Data is deleted", Toast.LENGTH_LONG).show();
                                    mContext.startActivity(new Intent(mContext,MainActivity.class));

                                }


                                else
                                    Toast.makeText(mContext, "Data is not deleted", Toast.LENGTH_LONG).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        TextView id, name, marks;
        ConstraintLayout mainLayout;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            marks = itemView.findViewById(R.id.mark);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }



}
