package com.techstar.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.techstar.greendao.entity.Temp;
import com.techstar.greendao.gen.TempDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mAdd,mDelete,mUpdate,mFind;
    private EditText mContext;
    private TempDao mTempDao;
    private EditText mText_edt;

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
        mFind.setOnClickListener(this);
    }

    private void initView() {
        mContext = (EditText) findViewById(R.id.textView);
        mText_edt = (EditText) findViewById(R.id.etd_text);
        mAdd = (Button) findViewById(R.id.btn_add);
        mDelete = (Button) findViewById(R.id.btn_delete);
        mUpdate = (Button) findViewById(R.id.btn_update);
        mFind = (Button) findViewById(R.id.btn_find);
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
            case R.id.btn_find:
                findDate();
                break;
        }
    }

    /**
     * 增加数据
     */
    private void addDate() {
        //id这里传值为Null是因为id自动增涨
        SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Temp temp = new Temp(null,myFmt2.format(new Date()),10);
        mTempDao.insert(temp);
        mContext.setText(""+temp.getData());
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        deleteTempById(2);
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

        String str = mContext.getText().toString();
        long id = Long.parseLong(str);

        String s = mText_edt.getText().toString();
        int value = Integer.parseInt(s);

        Temp mTemp = findDataById(id);
        mTemp.setValue(value);
        mTempDao.update(mTemp);

    }

    /**
     * 查找数据
     */
    private void findDate() {

        StringBuffer stringBuffer = new StringBuffer();
        List<Temp> temps = mTempDao.loadAll();
        for (int i = 0; i < temps.size(); i++) {
            stringBuffer.append(temps.get(i).getData()+":"+temps.get(i).getValue()+"---");
        }
        mContext.setText("查询全部数据==>"+stringBuffer);
    }

    private Temp findDataById(long id){
        return mTempDao.loadByRowId(id);
    }
}
