package com.example.demop.server.param;

/**
 * 这个类封装了停止游戏时所需要的参数
 * 参数详情请参考：https://cloud.tencent.com/document/product/1162/40739
 */
public class GameStopParam {
    public String ExperienceCode;
    public String UserId;

    /**
     * @param experienceCode 游戏代码
     * @param userId 用户id
     */
    public GameStopParam(String experienceCode, String userId) {
        ExperienceCode = experienceCode;
        UserId = userId;
    }
}