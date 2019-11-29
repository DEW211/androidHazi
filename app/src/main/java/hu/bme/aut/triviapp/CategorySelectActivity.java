package hu.bme.aut.triviapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import adapter.CategoryRecyclerAdapter;

public class CategorySelectActivity extends AppCompatActivity {

    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_selector);

        final RecyclerView categoryList = findViewById(R.id.recyclerView);
        categoryRecyclerAdapter = new CategoryRecyclerAdapter();
        categoryList.setLayoutManager(new LinearLayoutManager(this));
        categoryList.setHasFixedSize(true);

        categoryList.setAdapter(categoryRecyclerAdapter);

        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                categoryRecyclerAdapter.getItemCount();
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                startActivity(intent);
            }
        });



    }

}
