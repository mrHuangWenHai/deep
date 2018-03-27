package com.deep.api.authorization.interceptor;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.token.TokenManager;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.authorization.tools.RoleAndPermit;
import com.deep.domain.service.ServiceConfiguration;
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
    // static修饰的代码块在类进行初始化的时候肯定会执行

    // 用户角色
    private static Map<Long, String> map = new HashMap<>();
    // 用户权限

    public static Map<Long, String> permitMap = new HashMap<>();

    static {
        // 将角色的代码和角色名称进行一一对应, 便于设置拦截器
        map.put(new Long(262144) , "dongxiang_factory_administrator");
        map.put(new Long(131072) , "dongxiang_factory_expert");
        map.put(new Long(65536) , "dongxiang_factory_technician");
        map.put(new Long(32768) , "province_agent_total_administrator");
        map.put(new Long(524288) , "total_system_administrator");
        map.put(new Long(16384) , "province_agent_administrator");
        map.put(new Long(8192) , "province_agent_expert");
        map.put(new Long(4096) , "province_agent_technician");
        map.put(new Long(2048) , "city_agent_total_administrator");
        map.put(new Long(1024) , "city_agent_administrator");
        map.put(new Long(512) , "city_agent_expert");
        map.put(new Long(256) , "city_agent_technician");
        map.put(new Long(128) , "county_agent_total_administrator");
        map.put(new Long(64) , "county_agent_administrator");
        map.put(new Long(32) , "county_agent_expert");
        map.put(new Long(16) , "county_agent_technician");
        map.put(new Long(8) , "sheep_farm_administrator");
        map.put(new Long(4) , "sheep_farm_operator");
        map.put(new Long(2) , "sheep_farm_supervisor");
        map.put(new Long(1) , "tourist");
        map.put(new Long(0) , "others");

        // 将权限的代码和拦截器一一对应,便于设置拦截器
        permitMap.put(new Long(1) , "create_dongxiang_factory_administrator");
        permitMap.put(new Long(2) , "delete_dongxiang_factory_administrator");
        permitMap.put(new Long(3) , "update_user_password");
        permitMap.put(new Long(4) , "select_dongxiang_factory_administrator");
        permitMap.put(new Long(5) , "create_community_activities");
        permitMap.put(new Long(6) , "delete_community_activities");
        permitMap.put(new Long(7) , "update_community_activities");
        permitMap.put(new Long(8) , "select_community_activities");
        permitMap.put(new Long(9) , "create_live_information");
        permitMap.put(new Long(10) , "delete_live_information");
        permitMap.put(new Long(11) , "update_live_information");
        permitMap.put(new Long(12) , "select_live_information");
        permitMap.put(new Long(13) , "create_sanitary_disinfection_scheme");
        permitMap.put(new Long(14) , "delete_sanitary_disinfection_scheme");
        permitMap.put(new Long(15) , "update_sanitary_disinfection_scheme");
        permitMap.put(new Long(16) , "select_sanitary_disinfection_scheme");
        permitMap.put(new Long(17) , "create_immunization_program");
        permitMap.put(new Long(18) , "delete_immunization_program");
        permitMap.put(new Long(19) , "update_immunization_program");
        permitMap.put(new Long(20) , "select_immunization_program");
        permitMap.put(new Long(21) , "create_stage_nutrition_scheme");
        permitMap.put(new Long(22) , "delete_stage_nutrition_scheme");
        permitMap.put(new Long(23) , "update_stage_nutrition_scheme");
        permitMap.put(new Long(24) , "select_stage_nutrition_scheme");
        permitMap.put(new Long(25) , "create_seed_production_scheme");
        permitMap.put(new Long(26) , "delete_seed_production_scheme");
        permitMap.put(new Long(27) , "update_seed_production_scheme");
        permitMap.put(new Long(28) , "select_seed_production_scheme");
        permitMap.put(new Long(29) , "create_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(30) , "delete_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(31) , "update_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(32) , "select_archives_of_disease_prevention_and_control");
        permitMap.put(new Long(33) , "create_production_materials");
        permitMap.put(new Long(34) , "delete_production_materials");
        permitMap.put(new Long(35) , "update_production_materials");
        permitMap.put(new Long(36) , "select_production_materials");
        permitMap.put(new Long(37) , "create_order");
        permitMap.put(new Long(38) , "delete_order");
        permitMap.put(new Long(39) , "update_order");
        permitMap.put(new Long(40) , "select_order");
        permitMap.put(new Long(41) , "create_agent");
        permitMap.put(new Long(42) , "delete_agent");
        permitMap.put(new Long(43) , "update_agent");
        permitMap.put(new Long(44) , "select_agent");
        permitMap.put(new Long(45) , "create_professor");
        permitMap.put(new Long(46) , "delete_professor");
        permitMap.put(new Long(47) , "update_professor");
        permitMap.put(new Long(48) , "select_professor");
        permitMap.put(new Long(49) , "create_technician");
        permitMap.put(new Long(50) , "delete_technician");
        permitMap.put(new Long(51) , "update_technician");
        permitMap.put(new Long(52) , "select_technician");
        permitMap.put(new Long(53) , "create_operator");
        permitMap.put(new Long(54) , "delete_operator");
        permitMap.put(new Long(55) , "update_operator");
        permitMap.put(new Long(56) , "select_operator");
        permitMap.put(new Long(57) , "create_factory");
        permitMap.put(new Long(58) , "delete_factory");
        permitMap.put(new Long(59) , "update_factory");
        permitMap.put(new Long(60) , "select_factory");
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 判断handler是否是HandlerMethod的实例
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        // 将handler强制转换为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取要调用的方法
        Method method = handlerMethod.getMethod();

        // 从请求头中获取用户的ID
        String authorization = request.getHeader(Constants.AUTHORIZATION);
        if (authorization == null) {
            return false;
        }
        TokenModel model = tokenManager.getToken(authorization);

        // 取出方法上的Permit注解
        Permit permit = method.getAnnotation(Permit.class);

        if (permit == null) {
            // 如果注解Permit不为null, 标明不需要拦截, 直接放过
            return true;
        } else {
            if (permit.authorities().length > 0) {
                // 如果权限配置不为空, 则需要取出配置的值
                String[] modules = permit.modules();

                Set<String> authSet = new HashSet<>();
                for (String module : modules) {
                    // 将角色加到一个Set集合当中
                    authSet.add(module);
                }
                // 从参数中获取用户的ID
                // 从数据库的权限表中查询用户所拥有的权限集合, 与Set集合中的权限进行比对完成权限校验
                Long userID = model.getUserId();
                // 查找用户表以获取用户的用户的角色以及权限
                RoleAndPermit userRoleAndPermit = userService.findRoleByUserID(userID);
                Long roleInt = userRoleAndPermit.getRole();
                // TODO 需要判断用户是否有拓展权限从而判断是否属于其它的用户从而决定是否进行拦截操作
                // 根据roleInt去查询角色的名称, 需要实现roleInt与角色名称的一一对应
                // 但是目前的数据库设置中没有这一项目
                // 所以通过静态的方法去处理相关的关系

                // 这是相应的roleString角色
                String roleString = map.get(roleInt);

                if (authSet.contains(roleString)) {
                    // 校验通过返回true, 否则拦截请求
                    return true;
                } else {
                    if (authSet.contains(map.get(new Long(0))) && userRoleAndPermit.getExtended() != 0) {
                        // TODO 当用户拥有拓展权限的时候
                        // TODO 需要将用户当前的请求与数据库中权限表的请求作比对
                        // TODO 如果拥有此请求, 则返回true, 否则返回false
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("this is the afterCompletion of PermitInterceptor");
    }
}
