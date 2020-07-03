package com.together.util.utli;

import com.together.util.P;
import org.springframework.util.StringUtils;

public class ValidateUtli {
    /**
     * @Desc: 校验必传参数  空则抛出异常
     * @Param: HttpServletRequest mustParams
     * @Return: boolean
     * @Auther: lkw
     * @Date: 2018/6/27 9:41
     */
    public static void validateParams(P parameter, String... mustParams) throws Exception {
        StringBuffer info = new StringBuffer();
        int num = 0;
        if (mustParams != null && mustParams.length > 0) {
            for (String name : mustParams) {
                if (!parameter.containsKey(name) || StringUtils.isEmpty(parameter.get(name))) {
                    if (num == 0) {
                        info.append(name);
                    } else {
                        info.append("," + name);
                    }
                    num++;
                }
            }
        }
        if (num > 0) {
            String msg = "以下" + num + "个参数不能为空[ " + info.toString() + " ]";
            throw new Exception(msg);
        }
    }
}
