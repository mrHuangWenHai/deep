package com.deep.api.authorization.interceptor;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.token.TokenManager;
import com.deep.api.authorization.token.TokenManagerRealization;
import com.deep.api.authorization.tools.Constants;
import com.deep.domain.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class PermitInterceptor extends HandlerInterceptorAdapter{
    @Resource
    private TokenManager tokenManager;
    @Resource
    private UserService userService;
    @Resource
    private TokenManagerRealization tokenManagerRealization;
    // static修饰的代码块在类进行初始化的时候肯定会执行

    // 用户角色
    private static Map<Long, String> map = new HashMap<>();

    // 用户权限
    public static Map<Long, String> permitMap = new HashMap<>();

    static {
        // 将角色的代码和角色名称进行一一对应, 便于设置拦截器
        map.put(new Long(1) , "total_system_administrator");
        map.put(new Long(3) , "dongxiang_factory_administrator");
        map.put(new Long(4) , "dongxiang_factory_expert");
        map.put(new Long(5) , "dongxiang_factory_technician");
        map.put(new Long(6) , "province_agent_total_administrator");
        map.put(new Long(7) , "province_agent_administrator");
        map.put(new Long(8) , "province_agent_expert");
        map.put(new Long(9) , "province_agent_technician");
        map.put(new Long(10) , "city_agent_total_administrator");
        map.put(new Long(11) , "city_agent_administrator");
        map.put(new Long(12) , "city_agent_expert");
        map.put(new Long(13) , "city_agent_technician");
        map.put(new Long(14) , "county_agent_total_administrator");
        map.put(new Long(15) , "county_agent_administrator");
        map.put(new Long(16) , "county_agent_expert");
        map.put(new Long(17) , "county_agent_technician");
        map.put(new Long(18) , "sheep_farm_administrator");
        map.put(new Long(19) , "sheep_farm_operator");
        map.put(new Long(20) , "sheep_farm_supervisor");
        map.put(new Long(21) , "tourist");
        map.put(new Long(0) , "others");

        // 将权限的代码和拦截器一一对应,便于设置拦截器
        permitMap.put(new Long(1) , "create_delete_select_dongxiang_factory_administrator");
        permitMap.put(new Long(2) , "update_user_password");
        permitMap.put(new Long(3) , "create_delete_community_activities");
        permitMap.put(new Long(4) , "update_community_activities");
        permitMap.put(new Long(5) , "select_community_activities");
        permitMap.put(new Long(6) , "create_delete_live_information");
        permitMap.put(new Long(7) , "update_live_information");
        permitMap.put(new Long(8) , "create_delete_update_live");
        permitMap.put(new Long(9) , "watch_live");
        permitMap.put(new Long(10) , "create_sanitary_disinfection_scheme");
        permitMap.put(new Long(11) , "delete_sanitary_disinfection_scheme");
        permitMap.put(new Long(12) , "update_sanitary_disinfection_scheme");
        permitMap.put(new Long(13) , "select_sanitary_disinfection_scheme");
        permitMap.put(new Long(14) , "create_immunization_program");
        permitMap.put(new Long(15) , "delete_immunization_program");
        permitMap.put(new Long(16) , "update_immunization_program");
        permitMap.put(new Long(17) , "select_immunization_program");
        permitMap.put(new Long(18) , "create_stage_nutrition_scheme");
        permitMap.put(new Long(19) , "delete_stage_nutrition_scheme");
        permitMap.put(new Long(20) , "update_stage_nutrition_scheme");
        permitMap.put(new Long(21) , "select_stage_nutrition_scheme");
        permitMap.put(new Long(22) , "create_seed_production_scheme");
        permitMap.put(new Long(23) , "delete_seed_production_scheme");
        permitMap.put(new Long(24) , "update_seed_production_scheme");
        permitMap.put(new Long(25) , "select_seed_production_scheme");
        permitMap.put(new Long(26) , "create_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(27) , "delete_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(28) , "update_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(29) , "select_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(30) , "create_production_materials");
        permitMap.put(new Long(31) , "delete_production_materials");
        permitMap.put(new Long(32) , "update_production_materials");
        permitMap.put(new Long(33) , "select_production_materials");
        permitMap.put(new Long(34) , "create_order");
        permitMap.put(new Long(35) , "delete_order");
        permitMap.put(new Long(36) , "update_order");
        permitMap.put(new Long(37) , "select_order");
        permitMap.put(new Long(38) , "create_delete_agent");
        permitMap.put(new Long(39) , "update_agent");
        permitMap.put(new Long(40) , "select_agent");
        permitMap.put(new Long(41) , "create_delete_professor");
        permitMap.put(new Long(42) , "update_professor");
        permitMap.put(new Long(44) , "select_professor");
        permitMap.put(new Long(45) , "create_delete_technician");
        permitMap.put(new Long(46) , "update_technician");
        permitMap.put(new Long(47) , "select_technician");
        permitMap.put(new Long(48) , "create_delete_operator");
        permitMap.put(new Long(49) , "update_operator");
        permitMap.put(new Long(50) , "select_operator");
        permitMap.put(new Long(51) , "create_delete_factory");
        permitMap.put(new Long(52) , "update_factory");
        permitMap.put(new Long(53) , "select_factory");
        permitMap.put(new Long(54) , "create_delete_extend_table");
        permitMap.put(new Long(55) , "create_delete_update_select_extend_table");
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register") ||
                request.getRequestURI().equals("/allfunction") ||request.getRequestURI().equals("/loginresult")||
                request.getRequestURI().equals("/userAdd") ||request.getRequestURI().equals("/ensurequestion")||
                request.getRequestURI().equals("/phonefind") ||request.getRequestURI().equals("/questionfind")||
                request.getRequestURI().equals("/ensureverify") ||request.getRequestURI().equals("/findpassword")||
                request.getRequestURI().equals("/phonefind")||request.getRequestURI().equals("/ensurequestion") ||
                request.getRequestURI().equals("/error") || request.getRequestURI().equals("/question")
                ) {
            return true;
        }
        // 将handler强制转换为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取要调用的方法
        Method method = handlerMethod.getMethod();
        // 从请求头中获取用户的ID
        String authorization = request.getHeader(Constants.AUTHORIZATION);
        System.out.println("Permit拦截器" + authorization);
        if (authorization == null) {
            System.out.println("无该权限");
            response.setStatus(401);
            return false;
        }
        tokenManagerRealization = new TokenManagerRealization();
        System.out.println("进入拦截器");
        // 取出方法上的Permit注解
        Permit permit = method.getAnnotation(Permit.class);
        if (permit == null) {
            System.out.println("没有设置权限");
            return true;
        } else {
            // 如果注解Permit不为null
            System.out.println(permit.modules().length);
            if (permit.modules().length > 0) {
                String[] modules = permit.modules();
                Set<String> authSet = new HashSet<>();
                for (String module : modules) {
                    authSet.add(module);
                }
                // 从参数中获取用户的ID
                // TODO 从参数中获取用户的RoleID
//                Long userID = model.getUserId();
//                System.out.println(userID);
//                // 从Redis数据库中获取用户的相应权限
//                Jedis jedis = new Jedis(ServiceConfiguration.redisServer);
//                String roleInt = jedis.get(String.valueOf(userID).split("-")[1]);
                Long roleInt = tokenManagerRealization.getRoleID(authorization);
                System.out.println("roleInt" + roleInt + ":roleInt");
                // TODO 需要判断用户是否有拓展权限从而判断是否属于其它的用户从而决定是否进行拦截操作(PASS--用户可以自定义角色, 所以此功能可以舍弃)
                // 根据roleInt去查询角色的名称, 需要实现roleInt与角色名称的一一对应, 所以通过静态的方法去处理相关的关系
                // 这是相应的roleString角色
                String roleString = map.get(roleInt);
                if (authSet.contains(roleString)) {
                    // 校验通过返回true, 否则拦截请求
                    return true;
                }
            }
        }
        response.setStatus(401);
        return false;
    }
}
