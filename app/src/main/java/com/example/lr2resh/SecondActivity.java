package com.example.lr2resh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        CreateDropdown(); //для создания меню выбора валюты

        EditText et = findViewById(R.id.editText2); //получение ссылок на элементы интерфейса
        EditText et2 = findViewById(R.id.editText);
        Spinner sp = findViewById(R.id.spinner1);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(v -> { //устанавливаем слушатель на кнопку
            // Считываем значение полей
            String spent = et.getText().toString();
            String comment = et2.getText().toString();
            String currency = sp.getSelectedItem().toString();
            // Формируем "пустое" намерение
            Intent intent = new Intent();
            // Добавляем в намерение данные
            intent.putExtra("spent",spent);
            intent.putExtra("comment",comment);
            intent.putExtra("currency", currency);
            // Устанавливаем результат
            setResult(RESULT_OK,intent);

            // Закрываем активити
            finish();
        });
    }

    private void CreateDropdown(){ //создание меню выбора валюты
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"UAH", "USD", "EUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }

}
