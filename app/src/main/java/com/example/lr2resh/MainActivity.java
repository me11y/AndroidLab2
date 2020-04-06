package com.example.lr2resh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_CODE = 100; //реквест код
    ListAdapter listAdapter; //адаптер для listview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button); //находим кнопку
        btn.setOnClickListener(v -> {                                               //устанавливаем слушатель
            Intent intent = new Intent(this, SecondActivity.class); //вызывающий явное намерение (SecondActivity)
            startActivityForResult(intent, REQUEST_CODE); //запускаем активити до получения результата
        });

        Button btnSend = findViewById(R.id.buttonSend);
        btnSend.setOnClickListener(v -> {
            sendData();
        });

        // Создаём списки, в которых будет храниться информация о расходах
        List<String> spents = new ArrayList<>();
        List<String> comments = new ArrayList<>();

        // Виджет ListView
        ListView list = findViewById(R.id.list);
        //устанавливаем адаптер на listview (чтоб расходы выводились на экран первой активити)
        listAdapter = new ListAdapter(this, comments, spents);
        list.setAdapter(listAdapter);

    }

    public void sendData(){ //отправка данных на почту/куда-либо ещё
        String data = listAdapter.getAllItems().toString();
        Intent i = new Intent(Intent.ACTION_SEND); //неявное намерение

        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,"Мои расходы");
        i.putExtra(Intent.EXTRA_TEXT,data);

        startActivity(i);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) { //если пользователь нажал на кнопку "назад"
                Toast.makeText(this, "Расход не сохранён", Toast.LENGTH_SHORT).show(); //оообщаем, что расход не сохранён
            } else if (resultCode == RESULT_OK) { //если активити продолжилась до конца

                if (data != null) { //и если данные успешно получены
                    String spent = data.getStringExtra("spent"); //загружаем их в отдельные переменные для более удобной работы
                    String comment = data.getStringExtra("comment");
                    String currency = data.getStringExtra("currency");

                    if (!spent.isEmpty()) { //если расход указан
                        Toast.makeText(this, "Расход успешно добавлен", Toast.LENGTH_SHORT).show(); //сообщаем об этом
                        if(comment.isEmpty()){ //если пользователь не указал комментарий к расходу, то пишем его сами)
                            comment = "no info";
                        }
                        listAdapter.addItem("-" + spent + " " + currency , comment); //добавляем в listview данные о новом элементе (расходе)
                        listAdapter.notifyDataSetChanged(); //уведомляем адаптер о том, что данные были добавлены
                    }else{
                        Toast.makeText(this, "Расход не сохранён", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

}
