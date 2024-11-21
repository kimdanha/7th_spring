package umc.study.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.study.validation.annotation.CheckPage;

@Component
public class PagingValidator implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // @CheckPage 어노테이션이 붙은 파라미터만 처리
        return parameter.getParameterAnnotation(CheckPage.class) != null &&
                parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String pageParam = webRequest.getParameter("page");

        // 쿼리 파라미터가 없는 경우 예외 처리
        if (pageParam == null || pageParam.isEmpty()) {
            throw new IllegalArgumentException("Page parameter is missing.");
        }

        int page;
        try {
            // 쿼리 파라미터를 정수로 변환
            page = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Page parameter must be a valid integer.");
        }

        // page 값이 1 미만인 경우 예외 처리
        if (page < 1) {
            throw new IllegalArgumentException("Page parameter must be greater than or equal to 1.");
        }

        // 0 기반 페이지 번호로 변환
        return page - 1;
    }
}

