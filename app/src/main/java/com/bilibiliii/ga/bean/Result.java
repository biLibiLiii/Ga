package com.bilibiliii.ga.bean;

import java.util.List;

/**
 * @author No.47 create at 2017/11/2.
 */
public class Result<E> {

    /**
     * error_code : 0
     * reason : 请求成功！
     * result : [{"day":1,"des":"1907年11月1日 电影导演吴永刚诞生 　　吴永刚，1907年11月1日生于江苏吴县。1932年后参加影片《三个摩登女性》、《母性之光》的拍摄工作。1934年在联华影片公司编导处女作《神女》，一举成名，...","id":9000,"lunar":"丁未年九月廿六","month":11,"pic":"","title":"电影导演吴永刚诞生","year":1907},{"day":1,"des":"1902年11月1日 挪威作家格里格诞生 　　格里格，1902年11月1日生于卑尔根。挪威作家。 　　青年时代在奥斯陆和牛津求学，好旅行。1924年出版描写海员生活的小说《航船在前进》。1926年至1927年在...","id":9010,"lunar":"壬寅年十月初二","month":11,"pic":"","title":"挪威作家格里格诞生","year":1902},{"day":1,"des":"1911年11月1日，清廷宣布解散皇族内阁，任命袁世凯为内阁总理大臣，要他赶快从前线回京，筹组\u201c责任内阁\u201d。11月13日，袁世凯抵达北京，16日组织新内阁，以梁郭彦为外务大臣，赵秉钧为民政大臣，...","id":3577,"lunar":"辛亥年九月十一","month":11,"pic":"","title":"袁世凯出任清朝内阁总理大臣","year":1911}]
     */

    private int error_code;
    private String reason;
    private List<E> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }
}
