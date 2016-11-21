# GreenDAO
GreenDAO简单的增删该查
使用方法：
1、在包里建一个gen目录
2、在项目的build.gradle里面添加
1）compile 'org.greenrobot:greendao:3.0.1'
   compile 'org.greenrobot:greendao-generator:3.0.0'
2） 
apply plugin: 'org.greenrobot.greendao'
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'org.greenrobot:greendao-gradle-plugin:3.0.0'
    }
}

greendao {
    schemaVersion 1
    daoPackage 'com.techstar.greendao.gen'
    targetGenDir 'src/main/java'
}
3、在Application里设置greenDao
public class MyApplication extends Application{

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }

    public static MyApplication getInstances(){
        return instances;
    }
    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes.db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

4、在包里建一个entity目录，里面放需要的实体类。然后Build->Make Project 会在gen目录下生产工具类
5、在Activitys里获取想要的Dao然后执行增删改查

