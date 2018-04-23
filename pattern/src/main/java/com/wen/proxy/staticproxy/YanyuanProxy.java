package com.wen.proxy.staticproxy;

/**
 * Created by Administrator on 2017/5/14.
 */
class YanyuanProxy implements yanchu{

    private yanchu yanchu;

    public YanyuanProxy (yanchu yanchu){
        this.yanchu = yanchu;
    }

    public void qianyue() {
        System.out.println(" proxy daili qianyue");
    }

    public void bujing() {
        System.out.println(" proxy daili bujing");
    }

    public void yanchu() {
        yanchu.yanchu();;
    }

    public void shouqian() {
        System.out.println(" proxy daili shouqian");
    }
}
