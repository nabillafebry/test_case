package com.feby.asyst.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.feby.asyst.listview.adapter.PersonAdapter;
import com.feby.asyst.listview.fragment.EditFragment;
import com.feby.asyst.listview.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, EditFragment.OnSubmitButtonListener {

    ListView listView;
    ArrayList<String> listNama = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    ArrayList<Person> listPerson = new ArrayList<>();
    EditText etInputName;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        etInputName = findViewById(R.id.input_name_edittext);
        btnAdd = findViewById(R.id.add_button);

        listNama.add("Nabilla");
        listNama.add("Budi");
        listNama.add("Ana");
        listNama.add("Rian");

        for (int i = 0; i < 10; i++) {
            Person person = new Person("Nama Ke " + i, "Alamat Ke" + i);
            listPerson.add(person);
        }

//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listNama);
        PersonAdapter personAdapter = new PersonAdapter(this, listPerson);

        listView.setAdapter(personAdapter);

//        listView.setOnItemClickListener(this);
//        btnAdd.setOnClickListener(this);
//        listView.setOnItemLongClickListener(this);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(MainActivity.this, listNama.get(position), Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:

                if (!etInputName.getText().toString().equalsIgnoreCase("")) {
                    listNama.add(etInputName.getText().toString());
                    etInputName.setText("");
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "ISI NAMA", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        EditFragment editFragment = EditFragment.newInstance(listNama.get(position), position);
        editFragment.show(getSupportFragmentManager(), "");
        return false;
    }


    @Override
    public void onSubmitButton(String nama, int position) {
        listNama.set(position, nama);
        arrayAdapter.notifyDataSetChanged();
    }
}
