package com.together.resolvers;

import com.together.annotation.Pmap;
import com.together.entity.ShopEntity;
import com.together.local.RequestThreadLocal;
import com.together.util.P;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Spring MVC参数注入时拦截@{@link Pmap} 注解
 * @author  Agu
 */
public class PmapResolver extends RequestParamMapMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Pmap.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        P p = new P((Map) super.resolveArgument(parameter, mavContainer, webRequest, binderFactory));
        HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        p.setRequest(nativeRequest);
        p.setResponse(nativeResponse);
        ShopEntity shopUserEntity = (ShopEntity) p.getRequest().getSession().getAttribute("shopUserEntity");
        p.setShopEntity(shopUserEntity);
        Integer limit = p.getInt("limit");
        Integer page = p.getInt("page");
        if (limit!=null&&page!=null)
        p.put("rowIndex",(page-1)*limit);
        RequestThreadLocal.REQUEST_THREAD_LOCAL.set(nativeRequest);
//        p.batchToInt("limit","page");
        return p;
    }
}
