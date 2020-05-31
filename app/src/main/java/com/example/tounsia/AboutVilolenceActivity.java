
package com.example.tounsia;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.ImageButton;
        import android.widget.ImageView;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
public class AboutVilolenceActivity extends AppCompatActivity {
    RecyclerView recycleView;
    Adapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_vilolence);
        // get list of viloecce title and content in string array
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String[] titles = getResources().getStringArray(R.array.stories_title);
        String[] contents = getResources().getStringArray(R.array.stories_content);


        recycleView = findViewById(R.id.storiesListsView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, titles,contents);
        recycleView.setAdapter(adapter);

        back =findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AboutVilolenceActivity.this, HomeActivity.class);
                startActivity(a);
            }
        });

    }}


