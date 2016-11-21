package cn.edu.gdmec.s07150716.mycontacts;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindDialog extends Dialog {
    private Context l_context;
    public FindDialog(Context context) {
        super(context);
        l_context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        setTitle("查询联系人");
        final Button find= (Button) findViewById(R.id.find);
        Button cancel= (Button) findViewById(R.id.cancel);
        find.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText value= (EditText) findViewById(R.id.value);
                ContactsTable ct=new ContactsTable(l_context);

                User[] users=ct.findUserBykey(value.getText().toString());
                for (int i=0;i<users.length;i++){
                    System.out.println("姓名"+users[i].getName()+",电话"+users[i].getMobile());
                }
                ((MainActivity)l_context).setUsers(users);
                ((MainActivity)l_context).getListViewAdapter().notifyDataSetChanged();
                ((MainActivity)l_context).setSelectItem(0);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
