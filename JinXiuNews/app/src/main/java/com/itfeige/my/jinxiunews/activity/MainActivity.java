package com.itfeige.my.jinxiunews.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itfeige.my.jinxiunews.R;
import com.itfeige.my.jinxiunews.fragment.FindFragment;
import com.itfeige.my.jinxiunews.fragment.HotFragment;
import com.itfeige.my.jinxiunews.fragment.NewsFragment;
import com.itfeige.my.jinxiunews.fragment.SettingFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.fl_content)
    FrameLayout mFlContent;
    @Bind(R.id.iv_news)
    ImageView mIvNews;
    @Bind(R.id.tv_news)
    TextView mTvNews;
    @Bind(R.id.ll_news)
    LinearLayout mLlNews;
    @Bind(R.id.iv_hot)
    ImageView mIvHot;
    @Bind(R.id.tv_hot)
    TextView mTvHot;
    @Bind(R.id.ll_hot)
    LinearLayout mLlHot;
    @Bind(R.id.iv_find)
    ImageView mIvFind;
    @Bind(R.id.tv_find)
    TextView mTvFind;
    @Bind(R.id.ll_find)
    LinearLayout mLlFind;
    @Bind(R.id.iv_setting)
    ImageView mIvSetting;
    @Bind(R.id.tv_setting)
    TextView mTvSetting;
    @Bind(R.id.ll_setting)
    LinearLayout mLlSetting;
    private NewsFragment mNewsFragment;
    private HotFragment mHotFragment;
    private FindFragment mFindFragment;
    private SettingFragment mSettingFragment;
    private FragmentTransaction mFt;
    private FragmentManager mFm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();

        //Android4.4及以上版本才能设置此效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Android5.0版本
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏颜色
                getWindow().setStatusBarColor(getResources().getColor(R.color.app_main_color));
                //设置导航栏颜色
                getWindow().setNavigationBarColor(getResources().getColor(R.color.app_main_color));
            }else {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                //创建状态栏的管理实例
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                //激活状态栏设置
                tintManager.setStatusBarTintEnabled(true);
                //设置状态栏颜色
                tintManager.setTintResource(R.color.app_main_color);
                //激活导航栏设置
                tintManager.setNavigationBarTintEnabled(true);
                //设置导航栏颜色
                tintManager.setNavigationBarTintResource(R.color.app_main_color);
            }
        }
    }


    private void initData() {
        setSelect(0);
    }

    @OnClick({R.id.ll_news, R.id.ll_hot, R.id.ll_find, R.id.ll_setting})
    //更换页面
    public void chageTab(View view) {
        switch (view.getId()) {
            case R.id.ll_news:
                setSelect(0);
                break;

            case R.id.ll_hot:
                setSelect(1);
                break;

            case R.id.ll_find:
                setSelect(2);
                break;

            case R.id.ll_setting:
                setSelect(3);
                break;
        }
    }

    private void setSelect(int i) {
        mFm = getSupportFragmentManager();
        mFt = mFm.beginTransaction();
        reSetTab();
        //在添加Fragment之前隐藏掉已经存在的,只显示一个
        hideFragment();
        switch (i) {
            case 0:
                //新闻页
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                    mFt.add(R.id.fl_content, mNewsFragment);
                }
                mFt.show(mNewsFragment);
                mIvNews.setImageResource(R.mipmap.news_selected);
                mTvNews.setTextColor(getResources().getColor(R.color.selected));
                break;

            case 1:
                //热点页
                if (mHotFragment == null) {
                    mHotFragment = new HotFragment();
                    mFt.add(R.id.fl_content, mHotFragment);
                }
                mFt.show(mHotFragment);
                mIvHot.setImageResource(R.mipmap.hot_selected);
                mTvHot.setTextColor(getResources().getColor(R.color.selected));

                break;

            case 2:
                //发现页
                if (mFindFragment == null) {
                    mFindFragment = new FindFragment();
                    mFt.add(R.id.fl_content, mFindFragment);
                }
                mFt.show(mFindFragment);
                mIvFind.setImageResource(R.mipmap.find_selected);
                mTvFind.setTextColor(getResources().getColor(R.color.selected));
                break;

            case 3:
                //设置页
                if (mSettingFragment == null) {
                    mSettingFragment = new SettingFragment();
                    mFt.add(R.id.fl_content, mSettingFragment);
                }
                mFt.show(mSettingFragment);
                mIvSetting.setImageResource(R.mipmap.setting_selected);
                mTvSetting.setTextColor(getResources().getColor(R.color.selected));
                break;
        }
        mFt.commit();
    }

    //设置标签页的默认图片和默认文字颜色
    private void reSetTab() {
        mIvNews.setImageResource(R.mipmap.news_normal);
        mIvHot.setImageResource(R.mipmap.hot_normal);
        mIvFind.setImageResource(R.mipmap.find_normal);
        mIvSetting.setImageResource(R.mipmap.setting_normal);

        mTvNews.setTextColor(getResources().getColor(R.color.unselected));
        mTvHot.setTextColor(getResources().getColor(R.color.unselected));
        mTvFind.setTextColor(getResources().getColor(R.color.unselected));
        mTvSetting.setTextColor(getResources().getColor(R.color.unselected));

    }

    private void hideFragment() {
        if (mNewsFragment != null) {
            mFt.hide(mNewsFragment);
        }

        if (mHotFragment != null) {
            mFt.hide(mHotFragment);
        }

        if (mFindFragment != null) {
            mFt.hide(mFindFragment);
        }

        if (mSettingFragment != null) {
            mFt.hide(mSettingFragment);
        }
    }


}
