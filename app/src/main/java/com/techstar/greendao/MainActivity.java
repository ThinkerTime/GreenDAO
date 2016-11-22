package com.techstar.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.techstar.greendao.entity.Temp;
import com.techstar.greendao.gen.TempDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mAdd,mDelete,mUpdate,mFindOne,mFindAll;
    private TextView mContext;
    private TempDao mTempDao;
    private EditText mDelete_etd;
    private EditText mFindOne_etd;
    private Button mDeleteAll_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        mTempDao = MyApplication.getInstances().getDaoSession().getTempDao();
    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFindOne.setOnClickListener(this);
        mFindAll.setOnClickListener(this);
        mDeleteAll_btn.setOnClickListener(this);
    }

    private void initView() {
        mContext = (TextView) findViewById(R.id.textView);
        mDelete_etd = (EditText) findViewById(R.id.etd_delete);
        mFindOne_etd = (EditText) findViewById(R.id.edt_findOne);


        mAdd = (Button) findViewById(R.id.btn_add);
        mDelete = (Button) findViewById(R.id.btn_delete);
        mUpdate = (Button) findViewById(R.id.btn_update);
        mFindOne = (Button) findViewById(R.id.btn_findOne);
        mFindAll = (Button) findViewById(R.id.btn_findAll);
        mDeleteAll_btn = (Button) findViewById(R.id.btn_deleteAll);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                addDate();
                break;
            case R.id.btn_delete:
                deleteDate();
                break;
            case R.id.btn_update:
                updateDate();
                break;
            case R.id.btn_findOne:
                findOneData();
                break;
            case R.id.btn_findAll:
                findAllData();
                break;
            case R.id.btn_deleteAll:
                deleteAllDate();
                break;
        }
    }

    /**
     * 删除全部数据
     */
    private void deleteAllDate() {
        mTempDao.deleteAll();
    }

    /**
     * 查询全部
     */
    private void findAllData() {
        StringBuffer stringBuffer = new StringBuffer();
        List<Temp> temps = mTempDao.loadAll();
        for (int i = 0; i < temps.size(); i++) {
            stringBuffer.append("id:"+ temps.get(i).getId()+"data:"+temps.get(i).getData()+"wendu:"+temps.get(i).getValue()+"|");
        }
        mContext.setText("查询全部数据:"+stringBuffer);
    }

    /**
     * 根据ID进行查询
     */
    private void findOneData() {
        String s = mFindOne_etd.getText().toString();
        long id = Long.parseLong(s);
        Temp temp = findOneDataById(id);
        mContext.setText("id:"+temp.getId()+"data:"+temp.getData()+"wendu:"+temp.getValue());
    }


    /**
     * 增加数据
     */
    private void addDate() {
        //id这里传值为Null是因为id自动增涨
        SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Random random = new Random();
        Temp temp = new Temp(null,myFmt2.format(new Date()),random.nextInt(50));
        mTempDao.insert(temp);
        mContext.setText("id:"+temp.getId()+"data:"+temp.getData()+"wendu:"+temp.getValue());
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        String id = mDelete_etd.getText().toString();
        Long Id = Long.parseLong(id);
        deleteTempById(Id);
    }

    /**
     * 根据ID删除数据
     * @param id
     */
    private void deleteTempById(long id) {
        mTempDao.deleteByKey(id);
    }


    /**
     * 更改数据
     */
    private void updateDate() {
        int value = 10;
        Temp mTemp = findOneDataById(1);
        mTemp.setValue(value);
        mTempDao.update(mTemp);

    }

    private Temp findOneDataById(long id){
        return mTempDao.loadByRowId(id);
    }
}
