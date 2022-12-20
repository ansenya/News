package com.example.news;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.databinding.ListItemBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ListItemBinding binding;


    private String[] newsPool = {"Что-важное", "Что-то еще важнее", "ОГО, КАК ВАЖНО", "Не важно", "Вообще скука", "Ну пойдет", "AAAAAAA", "Нет, блин, ББББ"};
    private Integer[] picPool = {R.drawable.one, R.drawable.secnd, R.drawable.three, R.drawable.fourth, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight};
    private ListView listView;
    private ArrayAdapter<News> adapter;

    private News[] news = new News[10];

    final Random random = new Random();

    private int random() {
        return random.nextInt(7) + 1;
    }

    int k = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ListItemBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        binding.likes.setText(1+"");
        setContentView(R.layout.activity_main);
        setter();
        listView = findViewById(R.id.list_view);
        adapter = new MyAdapter(this, news);
        listView.setAdapter(adapter);
        Log.v("zxc", binding.likes.getText()+"");
    }

    private void setter() {
        for (int i = 0; i < 10; i++) {
            news[i] = new News("Text", "Title");
        }
    }


    class MyAdapter extends ArrayAdapter<News> {

        final News[] news;

        public MyAdapter(@NonNull Context context, News[] news) {
            super(context, R.layout.list_item);
            this.news = news;
        }

        public int getCount() {
            return news.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            News news1 = news[position];

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            }

            TextView titleNews = convertView.findViewById(R.id.title_news);
            titleNews.setText(newsPool[random()]);

            TextView someNews = convertView.findViewById(R.id.some_news);
            someNews.setText("какой-то текст новости " + random() + "(циферка типа вариативность есть)"); // я решил не запариваться

            ImageView imageView = convertView.findViewById(R.id.image_view);
            imageView.setImageResource(picPool[random()]);

            return convertView;
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.likes:
                binding.likes.setText((k++)+"");
                Toast.makeText(this, binding.likes.getText(), Toast.LENGTH_SHORT).show();
                Log.v("zxc", binding.likes.getText()+"");
        }

    }
}