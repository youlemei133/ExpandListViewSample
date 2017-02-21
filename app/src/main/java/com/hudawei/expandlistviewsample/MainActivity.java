package com.hudawei.expandlistviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandListView;
    List<String> groups;
    List<List<String>> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandListView=(ExpandableListView)findViewById(R.id.expandListView);

        initDate();
        ExpandableAdapter adapter=new MyExpandableAdapter(this,groups,items);
        expandListView.setAdapter(adapter);

        expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this,"OnChildClick\n"+"groupPosition:"+groupPosition+
                        "\nchildPosition="+childPosition+
                        "\nid="+id
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MainActivity.this,"OnGroupClick\n"+"groupPosition:"+groupPosition+
                                "\nid="+id
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this,"OnGroupCollapse\n"+"groupPosition:"+groupPosition+
                                "\n收起"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        expandListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this,"OnGroupExpand\n"+"groupPosition:"+groupPosition+
                                "\n展开"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void processClick(View view){
        //收起指定组，如果指定组是收起状态，那么返回false
//        boolean result=expandListView.collapseGroup(0);
//        log("result:"+result);

        //展开指定组，如果指定组是展开状态，那么返回false
//        boolean result=expandListView.expandGroup(0);
//        log("result:"+result);

        //指定组是否展开
//        boolean result=expandListView.isGroupExpanded(0);
//        log("result:"+result);

    }

    public void initDate(){
        groups=new ArrayList<>();
        groups.add("我的设备");
        groups.add("我的好友");
        groups.add("朋友");
        groups.add("陌生人");
        groups.add("黑名单");

        List<String> groupOne=new ArrayList<>();
        groupOne.add("Iphone设备");
        groupOne.add("Android设备");
        List<String> groupTwo=new ArrayList<>();
        groupTwo.add("阿毛");
        groupTwo.add("阿狗");
        groupTwo.add("阿猪");
        List<String> groupThree=new ArrayList<>();
        groupThree.add("小喵");
        groupThree.add("小红");
        groupThree.add("小黄");
        groupThree.add("小蓝");
        List<String> groupFour=new ArrayList<>();
        groupFour.add("东邪");
        groupFour.add("吸毒");
        groupFour.add("北丐");
        List<String> groupFive=new ArrayList<>();
        groupFive.add("张三");
        groupFive.add("李四");
        groupFive.add("王五");
        groupFive.add("赵六");

        items=new ArrayList<>();
        items.add(groupOne);
        items.add(groupTwo);
        items.add(groupThree);
        items.add(groupFour);
        items.add(groupFive);
    }

    public void log(String msg){
        Log.e("MainActivity",msg);
    }

}
