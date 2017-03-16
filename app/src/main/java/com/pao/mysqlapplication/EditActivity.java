package com.pao.mysqlapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    TodoList etodoList;
    Button ebutton;
    Button deletebut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etodoList = (TodoList) getIntent().getSerializableExtra("edittodoList");
        final EditText editText = (EditText) findViewById(R.id.editTextEditTask);
        editText.setText(etodoList.getTaskname());

        ebutton =(Button) findViewById(R.id.btnEditNewTask);
        ebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList edittodoList = new TodoList();
                edittodoList.setTaskid(etodoList.getTaskid());
                edittodoList.setTaskname(String.valueOf(editText.getText()));

                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.update(edittodoList);
                finish();
            }
        });
        deletebut = (Button) findViewById(R.id.btnDeleteNewTask);
        deletebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoListDAO todoListDAOdelete =new TodoListDAO(getApplicationContext());
                todoListDAOdelete.open();
                todoListDAOdelete.delete(etodoList);
                todoListDAOdelete.close();
                finish();
            }
        });
    }
}
