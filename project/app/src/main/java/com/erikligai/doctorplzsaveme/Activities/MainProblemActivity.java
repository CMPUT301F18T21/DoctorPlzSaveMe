package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Adapters.ProblemAdapter;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;
import java.util.Date;

public class MainProblemActivity extends AppCompatActivity {
    public ProblemAdapter adapter;
    private ArrayList<Problem> problems = null;
    private RecyclerView problemRView;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Backend.getProblems(this);

        problems = Backend.getInstance().getPatientProblems();

        setContentView(R.layout.activity_main_problem);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.problems);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fab", "add problems!");
                //calls AddProblemActivity
                Intent intent = new Intent(view.getContext(), AddProblemActivity.class);
                startActivity(intent);
            }
        });

        problemRView = findViewById(R.id.problems_recyclerview);
        problemRView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        problemRView.setLayoutManager(layoutManager);
        problemRView.setNestedScrollingEnabled(false);

        emptyView = findViewById(R.id.empty_problem_view);

        problems = Backend.getInstance().getPatientProblems();

        adapter = new ProblemAdapter(problems);
        problemRView.setAdapter(adapter);
        adapter.setOnEntryClickListener(new ProblemAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(int position) {
                Intent intent = new Intent(getApplicationContext(), MainRecordActivity.class);
                intent.putExtra("Pos", position);
                Log.d("rview", Integer.toString(position));
                startActivity(intent);
            }
        });

        problemRView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search_toolbar, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
////
////            case R.id.action_search:
////                // User chose the "Settings" item, show the app settings UI...
////                Log.d("toolbar", "search function!");
////                //calls ProblemSearchActivity
////                Intent intent = new Intent(this, ProblemSearchActivity.class);
////                startActivity(intent);
////                return true;
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        adapter.notifyDataSetChanged();
        checkEmpty();
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("abc","onresume");
        adapter.notifyDataSetChanged();
    }

    public void setProblems(ArrayList<Problem> _problems)
    {
        problems = _problems;
    }

    public void checkEmpty(){
        if (problems.isEmpty()) {
            problemRView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            problemRView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}
