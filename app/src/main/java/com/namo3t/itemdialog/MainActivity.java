package com.namo3t.itemdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMultiChoiceDialog();
            }
        });
    }

    private void createMultiChoiceDialog() {
        final String[] items = { "Nexus", "Samsung", "HTC", "Sony"};
        final boolean[] checkedItems = new boolean[items.length];
        checkedItems[2] = true;  //ให้สมาชิกลำดับที่ 2 ถูกเลือกไว้ล่วงหน้า (ลำดับที่ไม่กำหนดจะไม่ถูกเลือก)

        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Android Phone ที่ท่านสนใจ")
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked; //เปลี่ยนสถานะของสมาชิกในอาร์เรย์ตามการเลือก
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //เอาเฉพาะรายการที่ถูกเลือก เก็บพักไว้ใน ArrayList
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < items.length; i++) {
                    if (checkedItems[i] == true) {
                        arrayList.add(items[i]);
                    }
                }
                //เราจะนำ ArrayList ไปใช้งานโดยตรงเลยก็ได้ แต่ในที่นี้จะแแปลงจาก ArrayList ไปเป็น String Array

                String[] list = arrayList.toArray(new String[arrayList.size()]);
                getMultiChoiceDialogCheckedItems(list);
            }

            private void getMultiChoiceDialogCheckedItems(String[] checkedItems) {
                String str = "";
                for(String s: checkedItems) {
                    if(!str.equals("")) {
                        str += ", ";
                    }
                    str += s;
                }
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
            }
        })
                .show();
    }

}