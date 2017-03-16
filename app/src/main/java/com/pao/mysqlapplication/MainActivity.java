package com.pao.mysqlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.TodoListview);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_new_menu) {
            Intent addNewIntent = new Intent(this, AddDataActivity.class);
            startActivity(addNewIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView = (ListView) findViewById(R.id.TodoListview);
        TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
        todoListDAO.open();
        //ArrayList<String> myList = todoListDAO.getAlltodoList();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, myList);
        ArrayList<TodoList> myList = todoListDAO.getAlltodoList();
        final MyListView adapter = new MyListView(this, myList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getApplicationContext(),String.valueOf(adapter.getItemId(position)),
                //       Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(getApplicationContext(), EditActivity.class);
                editIntent.putExtra("edittodoList", (Serializable) adapter.getItem(position));
                startActivity(editIntent);
            }
        });
        todoListDAO.close();
    }

}
