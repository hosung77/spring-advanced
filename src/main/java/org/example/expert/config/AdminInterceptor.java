package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        // 권한이 필요한 URL 경로 확인
        if (url.startsWith("/admin/comments/**")) {  // 댓글 삭제 요청
            // 여기서 ADMIN 권한 체크
            if (!hasAdminRole(request)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자만 삭제가 가능합니다.");
                return false;
            }
        } else if (url.startsWith("/admin/users/**")) {  // 유저 역할 변경 요청
            // 여기서 ADMIN 권한 체크
            if (!hasAdminRole(request)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자만 변경이 가능합니다.");
                return false;
            }
        }
        return true;  // 권한이 맞으면 요청을 처리하도록 허용
    }

    // 권한이 ADMIN인지 확인하는 메서드
    private boolean hasAdminRole(HttpServletRequest request) {
        // HttpServletRequest에 저장된 userRole을 가져옴
        UserRole userRole = (UserRole) request.getAttribute("userRole");
        // ADMIN 권한을 가지고 있는지 검사
        return userRole != null && userRole.equals(UserRole.ADMIN);
    }
}