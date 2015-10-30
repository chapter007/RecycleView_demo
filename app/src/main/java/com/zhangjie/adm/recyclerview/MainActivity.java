package com.zhangjie.adm.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycle;
    private List<String> mDatas;
    private List<Integer> mHeight;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initData();
        mRecycle= (RecyclerView) findViewById(R.id.myView);
        //mRecycle.setLayoutManager(new LinearLayoutManager(this));
        //mRecycle.setLayoutManager(new GridLayoutManager(this,4));//gridview
        mRecycle.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecycle.setAdapter(mAdapter = new MyAdapter(MainActivity.this, mDatas, mHeight));
        mRecycle.addItemDecoration(new DividerGridItemDecoration(this));
        mRecycle.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"insert one",Toast.LENGTH_SHORT).show();
                mAdapter.addData(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"delete this",Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
    }

    protected void initData(){
        mDatas=new ArrayList<>();
        mHeight=new ArrayList<>();
        for (int i='A';i<'z';i++){
            mDatas.add(""+(char)i);
            mHeight.add( (int) (100 + Math.random() * 300));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
