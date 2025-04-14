/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: MenuInterceptor.java
* @author		: kkang
* @date			: 2025.04.14
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.14        kkang       최초 생성
*/
package egovframework.example.cmmn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;

import egovframework.example.system.service.TreeMenuService;
import egovframework.example.system.vo.TreeMenuVo;

public class MenuInterceptor implements HandlerInterceptor{
    @Autowired
    TreeMenuService treeMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<TreeMenuVo> result = treeMenuService.getList();
        Gson gson = new Gson();
        request.setAttribute("treeList", gson.toJson(result));
        return true;
    }
}
