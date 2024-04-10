package com.example.to_do;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private Button button;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list= findViewById(R.id.list);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addItem(view);
            }
        });
        items=new ArrayList<>();
        itemsAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);
        setupListViewListener();

    }

    private void setupListViewListener() {
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context =getApplicationContext();
                Toast.makeText(context, "Item Removed",Toast.LENGTH_LONG).show();
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;

            }
        });
    }
    private void addItem(View view) {
        EditText input=findViewById(R.id.editTextText);
        String itemText= input.getText().toString();

        if(!(itemText.isEmpty())) {
            itemsAdapter.add(itemText);
            input.setText("");

        }else
            Toast.makeText(getApplicationContext(),"Please Enter Task!",Toast.LENGTH_LONG).show();
    }
}