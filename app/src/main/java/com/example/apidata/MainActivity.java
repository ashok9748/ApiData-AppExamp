package com.example.apidata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid=findViewById(R.id.grid);

        listingdata();
    }

    private void listingdata() {
        ApiInterface apiInterface=Retrofit.getRetrofit().create(ApiInterface.class);
        Call<Pojo> listingdata=apiInterface.getdata();
        listingdata.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                gridAdapter adapter=new gridAdapter(response.body().getData());
                grid.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    class gridAdapter extends BaseAdapter{
        List<Pojo.Datum> list;

        public gridAdapter(List<Pojo.Datum> list) {
            this.list=list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View v=getLayoutInflater().inflate(R.layout.gridlayout,viewGroup,false);

            ImageView pic=v.findViewById(R.id.pic);
            TextView name=v.findViewById(R.id.name);

           name.setText(list.get(i).getFirstName());
            Picasso.with(getApplicationContext())
                    .load(list.get(i).getAvatar())
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .into(pic);
            return v;
        }
    }
}