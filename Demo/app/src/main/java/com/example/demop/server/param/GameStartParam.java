package com.example.demop.server.param;

/**
 * 这个类封装了启动游戏所需要的参数
 * 参数详情请参考：https://cloud.tencent.com/document/product/1162/40740
 */
public class GameStartParam {
    public String ExperienceCode;
    public String ClientSession;
    public String UserId;

    /**
     * @param gameCode 游戏代码
     * @param clientSession 客户端请求参数
     * @param userId 用户id
     */
    public GameStartParam(String gameCode, String clientSession, String userId) {
        ExperienceCode = gameCode;
        ClientSession = clientSession;
        UserId = userId;
    }
}
