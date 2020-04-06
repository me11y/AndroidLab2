package com.example.lr2resh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<String> comments; //комментарии к расходам
    private List<String> spents; //расходы
    private LayoutInflater inflater;

    public ListAdapter(Context context, List<String> comments, List<String> spents) {
        this.comments = comments;
        this.spents = spents;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public String getItem(int position) {
        return spents.get(position) + " / " + comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Преобразуем макет в дерево объектов
        View view = inflater.inflate(R.layout.list_item, parent, false);

        // Получаем доступ к виджетам дерева объектов
        TextView number = view.findViewById(R.id.number);
        TextView text = view.findViewById(R.id.text);

        // Меняем содержимое виджетов
        number.setText(spents.get(position));
        text.setText(comments.get(position));

        // Возвращаем модифицированное дерево объектов
        return view;
    }

    public void addItem(String spent, String comment){ //добавление нового элемента в список
        spents.add(spent);
        comments.add(comment);
    }

    public List<String> getAllItems(){
        List<String> list = new ArrayList<>();
        for(int i=0; i<spents.size(); i++){
            list.add(getItem(i));
        }
        return list;
    }
}
