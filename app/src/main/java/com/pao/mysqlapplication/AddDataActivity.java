package com.pao.mysqlapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDataActivity extends AppCompatActivity {
    public
    EditText inputAddNewTask;
    Button btnAddNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        inputAddNewTask = (EditText) findViewById(R.id.editTextAddTask);
        btnAddNewTask = (Button) findViewById(R.id.btnAddNewTask);
        btnAddNewTask.setOnClickListener(new View.OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   TodoList todoList = new TodoList();
                                                   todoList.setTaskname(String.valueOf(inputAddNewTask.getText()));
                                                   TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                                                   todoListDAO.open();
                                                   todoListDAO.add(todoList);
                                                   todoListDAO.close();
                                                   finish();
                                               }
                                           }
        );
    }
}
