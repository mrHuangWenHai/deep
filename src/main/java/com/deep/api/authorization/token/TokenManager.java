package com.deep.api.authorization.token;

/**
 * 对token进行操作的interface接口
 */
public interface TokenManager {
    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的ID
     * @return 生成的token
     */
    public TokenModel createToken(long userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的ID
     */
    public void deleteToken(long userId);
}
