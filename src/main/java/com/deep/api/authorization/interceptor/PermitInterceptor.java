package com.deep.api.authorization.interceptor;

import com.deep.api.Utils.JedisUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.token.TokenManagerRealization;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.authorization.tools.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(PermitInterceptor.class);

    @Resource
    private TokenManagerRealization tokenManagerRealization;

    // 用户权限
    private static Map<Integer, String> permitMap = new HashMap<>();

    static {
        // 将权限的代码和拦截器一一对应
        permitMap.put(1, "increase_breeding_maternity_file");
        permitMap.put(2, "delete_breeding_child_files");
        permitMap.put(3, "search_for_breeding_child_files");
        permitMap.put(4, "modify_breeding_seed_file");
        permitMap.put(5, "supervise_auditing_breeding_child_files");
        permitMap.put(6, "experts_review_mating_and_childbirth_files");
        permitMap.put(7, "increase_disease_prevention_files");
        permitMap.put(8, "delete_disease_prevention_files");
        permitMap.put(9, "check_disease_prevention_files");
        permitMap.put(10, "modify_disease_prevention_files");
        permitMap.put(11, "supervise_and_verify_disease_prevention_files");
        permitMap.put(12, "experts_review_disease_prevention_files");
        permitMap.put(13, "increase_sanitation_files");
        permitMap.put(14, "delete_sanitation_files");
        permitMap.put(15, "view_sanitation_files");
        permitMap.put(16, "modify_sanitation_files");
        permitMap.put(17, "surveillance_audit_sanitation_files");
        permitMap.put(18, "experts_review_sanitation_files");
        permitMap.put(19, "increase_the_immunization_implementation_file");
        permitMap.put(20, "delete_the_immunization_implementation_file");
        permitMap.put(21, "query_the_immunization_implementation_file");
        permitMap.put(22, "modify_the_immunization_implementation_file");
        permitMap.put(23, "surveillance_audit_of_immunization_implementation_files");
        permitMap.put(24, "experts_review_immune_implementation_files");
        permitMap.put(25, "increase_phase_nutritional_profile");
        permitMap.put(26, "delete_stage_nutrition_file");
        permitMap.put(27, "inquiry_phase_nutrition_file");
        permitMap.put(28, "modification_phase_nutrition_file");
        permitMap.put(29, "supervision_review_phase_nutrition_answer");
        permitMap.put(30, "expert_review_nutrition_file");
        permitMap.put(31, "increase_health_and_animal_welfare");
        permitMap.put(32, "remove_health_and_animal_welfare");
        permitMap.put(33, "inquiries_on_hygiene_and_animal_welfare");
        permitMap.put(34, "modify_health_and_animal_welfare");
        permitMap.put(35, "supervise_and_audit_health_and_animal_welfare");
        permitMap.put(36, "experts_review_health_and_animal_welfare");
        permitMap.put(37, "increase_plan");
        permitMap.put(38, "delete_the_plan");
        permitMap.put(39, "query_plan");
        permitMap.put(40, "amendment");
        permitMap.put(41, "add_agent");
        permitMap.put(42, "deleting_an_agent");
        permitMap.put(43, "query_agent");
        permitMap.put(44, "modify_the_proxy");
        permitMap.put(45, "increase_customer");
        permitMap.put(46, "delete_customer");
        permitMap.put(47, "customer_inquiry");
        permitMap.put(48, "modify_customer");
        permitMap.put(49, "increase_expert_evaluation");
        permitMap.put(50, "delete_expert_evaluation");
        permitMap.put(51, "query_expert_evaluation");
        permitMap.put(52, "modify_expert_evaluation");
        permitMap.put(53, "view_overall_rating");
        permitMap.put(54, "front_view_organic_environment");
        permitMap.put(55, "rear_view_organic_environment");
        permitMap.put(56, "send_messages");
        permitMap.put(57, "add_live");
        permitMap.put(58, "delete_live");
        permitMap.put(59, "view_live");
        permitMap.put(60, "ban_live");
        permitMap.put(61, "add_community_activities");
        permitMap.put(62, "delete_community_activity");
        permitMap.put(63, "check_community_activities");
        permitMap.put(64, "modify_community_activities");
        permitMap.put(65, "add_release");
        permitMap.put(66, "delete_release");
        permitMap.put(67, "query_publishing");
        permitMap.put(68, "modify_release");
        permitMap.put(69, "add_pedigree_files");
        permitMap.put(70, "delete_pedigree_files");
        permitMap.put(71, "query_pedigree_files");
        permitMap.put(72, "modify_pedigree_files");
        permitMap.put(73, "add_video");
        permitMap.put(74, "delete_video");
        permitMap.put(75, "see_video");
        permitMap.put(76, "edit_video");
        permitMap.put(77, "add_pictures");
        permitMap.put(78, "delete_picture");
        permitMap.put(79, "view_image");
        permitMap.put(80, "edit_picture");
        permitMap.put(81, "add_expansion_module");
        permitMap.put(82, "delete_extension_module");
        permitMap.put(83, "modify_the_extension_module");
        permitMap.put(84, "query_expansion_module");
        permitMap.put(85, "add_extension_module_information");
        permitMap.put(86, "delete_extension_module_information");
        permitMap.put(87, "modify_the_extension_module_information");
        permitMap.put(88, "query_expansion_module_information");
        permitMap.put(89, "add_user");
        permitMap.put(90, "delete_users");
        permitMap.put(91, "query_user");
        permitMap.put(92, "modify_user");
        permitMap.put(93, "add_expert");
        permitMap.put(94, "delete_expert");
        permitMap.put(95, "query_expert");
        permitMap.put(96, "modify_expert");
        permitMap.put(97, "add_technician");
        permitMap.put(98, "remove_technician");
        permitMap.put(99, "query_technician");
        permitMap.put(100, "modify_technician");
        permitMap.put(101, "add_an_administrator");
        permitMap.put(102, "remove_administrator");
        permitMap.put(103, "query_administrator");
        permitMap.put(104, "modify_administrator");
        permitMap.put(105, "add_role");
        permitMap.put(106, "remove_role");
        permitMap.put(107, "query_role");
        permitMap.put(108, "modify_role");
        permitMap.put(109, "query_permit");
        permitMap.put(110, "download_database");
        permitMap.put(111, "send_messages");
        permitMap.put(112, "edit_messages");
        permitMap.put(113, "add_sheep_type");
        permitMap.put(114, "delete_sheep_type");
        permitMap.put(115, "select_sheep_type");
        permitMap.put(116, "modify_sheep_type");
        permitMap.put(117, " download_product_file_action");
        permitMap.put(118, " check_message_of_all_user");
        permitMap.put(119, " increase_pest_repellent_implementation_control_files");
        permitMap.put(120, " deworming_implementation_control_files");
        permitMap.put(121, " check_insecticide_implementation_control_files");
        permitMap.put(122, " modify_the_insect_repellent_implementation_control_file");
        permitMap.put(123, " supervise_and_verify_the_implementation_of_pest_control_files");
        permitMap.put(124, " experts_review_deworming_implementation_control_files");

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("invoke preHandle of PermitInterceptor", request, response, handler);
        System.out.println("3333333333333333333333333333333");
        System.out.println("this is the preHandle of PermitInterceptor");

        if (request.getMethod().equals("OPTIONS")) {
            logger.info("PermitInterceptor:request type is OPTIONS");
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
            logger.info("PermitInterceptor:don't need to interceptor");
            return true;
        }

        if (! (handler instanceof HandlerMethod)) {
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
            logger.info("authorization == null");
            response.setStatus(403);
            return false;
        }
        tokenManagerRealization = new TokenManagerRealization();
        // 取出authorization中的用户ID
        TokenModel tokenModel = tokenManagerRealization.getToken(authorization);
        // 取出方法上的Permit注解
        Permit permit = method.getAnnotation(Permit.class);
        if (permit == null) {
            logger.info("no permit message");
            return true;
        } else {
            logger.info("permit authorities length {}", permit.authorities(), permit.authorities().length);
            // 权限Permit注解的authorization部分
            if (permit.authorities().length > 0) {
                String[] authorities = permit.authorities();
                Set<String> authorSet = new HashSet<>();
                for (String authority : authorities) {
                    authorSet.add(authority);
                    System.out.println(authority);
                }

                // 获取Redis数据库的用户权限信息
                String allPermits = JedisUtil.getValue("defaultPermit"+tokenModel.getUserId());
                if (allPermits == null) {
                    response.setStatus(403);
                    return false;
                }
                System.out.println("allPermits.length() = " + allPermits.length());

                for (int i = 0; i < allPermits.length(); i++) {
                    if (allPermits.charAt(i) == '1') {
                        String authority = permitMap.get(i+1);
                        System.out.println("authority is " + authority + ", i = " + i);
                        if (authorSet.contains(authority)) {
                            logger.info("PermitInterceptor success {}", i, authority);
                            return true;
                        }
                    }
                }
            }
        }
      //  return true;
        System.out.println("000000000000000000000000000000000000000000000000000000");
        response.setStatus(403);
        return false;
    }

}
