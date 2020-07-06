package com.together.util.utli;

import com.together.enun.TipMsgEnum;
import com.together.util.P;
import org.springframework.util.StringUtils;

public class ValidateUtli {

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
            System.out.println(msg);
            throw new Exception(TipMsgEnum.PARAMETER_NULL_Excption.getMsg());
        }
    }
}
