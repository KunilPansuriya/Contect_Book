package com.example.contact_book;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    MainActivity mainActivity;
    ArrayList<ContactModal> contactList;

//    ArrayList<Integer> idList;
//    ArrayList<String> nameList;
//    ArrayList<String> emailList;
//    ArrayList<String> numberList;

//    public ContactAdapter(MainActivity mainActivity, ArrayList<Integer> idList, ArrayList<String> nameList, ArrayList<String> emailList, ArrayList<String> numberList) {
//        this.mainActivity = mainActivity;
//        this.idList = idList;
//        this.nameList = nameList;
//        this.emailList = emailList;
//        this.numberList = numberList;
//    }

    public ContactAdapter(MainActivity mainActivity, ArrayList<ContactModal> contactList) {
        this.mainActivity = mainActivity;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.activity_main_item,parent,false);
        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(""+contactList.get(position).getName());
        holder.img.setImageURI(Uri.parse(contactList.get(position).getImgURI()));
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(mainActivity,holder.button1);
                mainActivity.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId()==R.id.menuupdate){

                            Dialog dialog = new Dialog(mainActivity);
                            dialog.setContentView(R.layout.update_layout);
                            EditText txt1,txt2,txt3;
                            TextView button2;
                            txt1 = dialog.findViewById(R.id.updatenametextview);
                            txt2 = dialog.findViewById(R.id.updatenumbertextview);
                            txt3 = dialog.findViewById(R.id.updateemailtextview);
                            button2 = dialog.findViewById(R.id.updatesavebutton);

                            txt1.setText(""+contactList.get(position).getName());
                            txt2.setText(""+contactList.get(position).getNumber());
                            txt3.setText(""+contactList.get(position).getEmail());


                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MydataBase1 mydataBase1 = new MydataBase1(mainActivity.getApplicationContext());
                                    mydataBase1.updateData(contactList.get(position).getId(),txt1.getText().toString(),txt2.getText().toString(),txt3.getText().toString());

                                    Intent intent = new Intent(mainActivity,MainActivity.class);
                                    mainActivity.startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                        if (menuItem.getItemId()==R.id.menudalete){
                            MydataBase1 mydataBase1 = new MydataBase1(mainActivity);
                            mydataBase1.delete(contactList.get(holder.getAdapterPosition()).getId());
                            contactList.remove(position);
//                            contactList.remove(holder.getAdapterPosition()).getId();
//                            contactList.remove(holder.getAdapterPosition()).getName();
//                            contactList.remove(holder.getAdapterPosition()).getNumber();
//                            contactList.remove(holder.getAdapterPosition()).getEmail();
                            notifyDataSetChanged();
                        }
//                        if (menuItem.getItemId()==R.id.menudalete){
//                            MydataBase1 mydataBase1 = new MydataBase1(mainActivity);
//                            mydataBase1.deleteData(contactList.get(holder.getAdapterPosition()).getId());
//                            contactList.remove(holder.getAdapterPosition()).getName();
//                            contactList.remove(holder.getAdapterPosition()).getNumber();
//                            contactList.remove(holder.getAdapterPosition()).getEmail();
//                            notifyDataSetChanged();
//                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView button1,img;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemnametextview);
            button1 = itemView.findViewById(R.id.itemnextbutton);
            img=itemView.findViewById(R.id.itemImg);
        }
    }
}




//
//RecyclerView.Adapter<ContactAdapter.ContactViewHolder>
//        {
//        MainActivity mainActivity;
//        ArrayList<ContactModel> contactList;
//
////    ArrayList<Integer> idList;
////    ArrayList<String> nameList;
////    ArrayList<String> emailList;
////    ArrayList<String> numberList;
//
////    public ContactAdapter(MainActivity mainActivity, ArrayList<Integer> idList, ArrayList<String> nameList, ArrayList<String> emailList, ArrayList<String> numberList) {
////        this.mainActivity = mainActivity;
////        this.idList = idList;
////        this.nameList = nameList;
////        this.emailList = emailList;
////        this.numberList = numberList;
////    }
//
//public ContactAdapter(MainActivity mainActivity, ArrayList<ContactModel> contactList) {
//        this.mainActivity=mainActivity;
//        this.contactList=contactList;
//        }
//
//@NonNull
//@Override
//public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(mainActivity).inflate(R.layout.contact_item,parent,false);
//        ContactViewHolder holder=new ContactViewHolder(view);
//        return holder;
//        }
//
//@Override
//public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.textView.setText(""+contactList.get(position).getName());
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        Intent intent=new Intent(mainActivity,Detalils_Activity.class);
//        intent.putExtra("name",contactList.get(position).getName());
//        intent.putExtra("number",contactList.get(position).getNumber());
//        intent.putExtra("email",contactList.get(position).getEmail());
//        mainActivity.startActivity(intent);
//        }
//        });
//        }
//
//@Override
//public int getItemCount() {
//        return contactList.size();
//        }
//
//public class ContactViewHolder extends RecyclerView.ViewHolder {
//    TextView textView;
//    ImageButton Next;
//
//    public ContactViewHolder(@NonNull View itemView) {
//        super(itemView);
//        textView=itemView.findViewById(R.id.nametxt);
//        Next=itemView.findViewById(R.id.next);
//        Next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PopupMenu popupMenu=new PopupMenu(mainActivity,Next);
//                mainActivity.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if(item.getItemId()==R.id.update)
//                        {
//                            Toast.makeText(mainActivity,"Update",Toast.LENGTH_SHORT).show();
//                            Dialog dialog=new Dialog(mainActivity);
//                            dialog.setContentView(R.layout.more_activity);
//                            EditText txtname,txtNumber,txtemail;
//                            Button btnupdate;
//                            txtname=dialog.findViewById(R.id.txtname);
//                            txtNumber=dialog.findViewById(R.id.txtnumber);
//                            txtemail=dialog.findViewById(R.id.txtemail);
//                            btnupdate=dialog.findViewById(R.id.btnupdate);
//                            txtname.setText(""+contactList.get(getAdapterPosition()).getName());
//                            txtemail.setText(""+contactList.get(getAdapterPosition()).getEmail());
//                            txtNumber.setText(""+contactList.get(getAdapterPosition()).getNumber());
//                            btnupdate.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    MydataBase mydataBase=new MydataBase(mainActivity.getApplicationContext());
//                                    mydataBase.updateData(contactList.get(getAdapterPosition()).getId(),contactList.get(getAdapterPosition()).getName(),contactList.get(getAdapterPosition()).getNumber(),contactList.get(getAdapterPosition()).getEmail());
//                                    String name=txtname.getText().toString();
//                                    String email=txtemail.getText().toString();
//                                    txtname.setText(""+name);
//                                    dialog.dismiss();
//                                }
//                            });
//                            dialog.show();
//                        }
//                        if(item.getItemId()==R.id.delete)
//                        {
//                            Toast.makeText(mainActivity,"Delete",Toast.LENGTH_SHORT).show();
//                            MydataBase mydataBase=new MydataBase(mainActivity);
//                            mydataBase.deleteData(contactList.get(getAdapterPosition()).getId());
//                            contactList.remove(getAdapterPosition()).getId();
//                            contactList.remove(getAdapterPosition()).getName();
//                            contactList.remove(getAdapterPosition()).getNumber();
//                            contactList.remove(getAdapterPosition()).getEmail();
//                            notifyDataSetChanged();
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        });
//    }
//}
//}