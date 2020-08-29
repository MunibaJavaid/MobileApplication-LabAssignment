package com.muniba3.note_tacking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActionMode mActionMode;
    static ArrayList<String> notes = new ArrayList<String>();
    static ArrayList<String> UserSelection = new ArrayList<String>();
    static ArrayAdapter<String> arrayAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note) {
            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(modeListener);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set == null) {
            notes.add("Example Note");
        } else {
            notes = new ArrayList<>(set);         // to bring all the already stored data in the set to the notes ArrayList
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                intent.putExtra("noteID", position);
                startActivity(intent);
            }
        });


//
//        listView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mActionMode != null) {
//                    return false;
//                }
//
//                mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
//                return true;
//            }
//        });
    }
//    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
//            mode.setTitle("Choose your option");
//            return true;
//        }
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.action_edit:
//                    Toast.makeText(MainActivity.this, "Option 1 selected", Toast.LENGTH_SHORT).show();
//                    mode.finish();
//                    return true;
//                case R.id.action_delete:
//                    Toast.makeText(MainActivity.this, "Option 2 selected", Toast.LENGTH_SHORT).show();
//                    mode.finish();
//                    return true;
//                default:
//                    return false;
//            }
//        }
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            mActionMode = null;
//        }
//    };



    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            if(UserSelection.contains(notes.get(position))){
                UserSelection.remove(notes.get(position));
            }
            else {
                UserSelection.add(notes.get(position));
            }
            mode.setTitle(UserSelection.size() +" Item Selected...");

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            MenuInflater menuInflater = mode.getMenuInflater();
            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_edit:
                    Toast.makeText(MainActivity.this, "Edit notes", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(MainActivity.this, "Delete notes", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

//    public void removeItem(List<String> items){
//        for (String item : items){
//            notes.remove(item);
//        }
//        arrayAdapter.notifyDataSetChanged();
//    }
}


//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
//            {
//                new AlertDialog.Builder(MainActivity.this)                   // we can't use getApplicationContext() here as we want the activity to be the context, not the application
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Delete?")
//                        .setMessage("Are you sure you want to delete this note?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which)                        // to remove the selected note once "Yes" is pressed
//                            {
//                                notes.remove(position);
//                                arrayAdapter.notifyDataSetChanged();
//
//                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE);
//                                HashSet<String> set = new HashSet<>(MainActivity.notes);
//                                sharedPreferences.edit().putStringSet("notes", set).apply();
//                            }
//                        })
//
//                        .setNegativeButton("No", null)
//                        .show();
//
//                return true;               // this was initially false but we change it to true as if false, the method assumes that we want to do a short click after the long click as well
//            }
//        });
//    }
    



