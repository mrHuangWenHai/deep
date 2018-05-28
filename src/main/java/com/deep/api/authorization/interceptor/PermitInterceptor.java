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

        permitMap.put(37, "increase_pest_repellent_implementation_control_files");
        permitMap.put(38, "deworming_implementation_control_files");
        permitMap.put(39, "check_insecticide_implementation_control_files");
        permitMap.put(40, "modify_the_insect_repellent_implementation_control_file");
        permitMap.put(41, "supervise_and_verify_the_implementation_of_pest_control_files");
        permitMap.put(42, "experts_review_deworming_implementation_control_files");

        permitMap.put(43, "increase_expert_evaluation");
        permitMap.put(44, "delete_expert_evaluation");
        permitMap.put(45, "query_expert_evaluation");
        permitMap.put(46, "modify_expert_evaluation");
        permitMap.put(47, "view_overall_rating");

        permitMap.put(48, "increase_plan");
        permitMap.put(49, "delete_the_plan");
        permitMap.put(50, "query_plan");
        permitMap.put(51, "amendment");

        permitMap.put(52, "add_agent");
        permitMap.put(53, "deleting_an_agent");
        permitMap.put(54, "query_agent");
        permitMap.put(55, "modify_the_proxy");

        permitMap.put(56, "increase_customer");
        permitMap.put(57, "delete_customer");
        permitMap.put(58, "customer_inquiry");
        permitMap.put(59, "modify_customer");

        permitMap.put(60, "add_live");
        permitMap.put(61, "delete_live");
        permitMap.put(62, "view_live");
        permitMap.put(63, "ban_live");

        permitMap.put(64, "add_release");
        permitMap.put(65, "delete_release");
        permitMap.put(66, "query_publishing");
        permitMap.put(67, "modify_release");

        permitMap.put(68, "add_pedigree_files");
        permitMap.put(69, "delete_pedigree_files");
        permitMap.put(70, "query_pedigree_files");
        permitMap.put(71, "modify_pedigree_files");

        permitMap.put(72, "add_video");
        permitMap.put(73, "delete_video");
        permitMap.put(74, "see_video");
        permitMap.put(75, "edit_video");

        permitMap.put(76, "add_video");
        permitMap.put(77, "delete_video");
        permitMap.put(78, "see_video");
        permitMap.put(79, "edit_video");

        permitMap.put(80, "add_user");
        permitMap.put(81, "delete_users");
        permitMap.put(82, "query_user");
        permitMap.put(83, "modify_user");

        permitMap.put(84, "add_expert");
        permitMap.put(85, "delete_expert");
        permitMap.put(86, "query_expert");
        permitMap.put(87, "modify_expert");

        permitMap.put(88, "add_technician");
        permitMap.put(89, "remove_technician");
        permitMap.put(90, "query_technician");
        permitMap.put(91, "modify_technician");

        permitMap.put(92, "add_an_administrator");
        permitMap.put(93, "remove_administrator");
        permitMap.put(94, "query_administrator");
        permitMap.put(95, "modify_administrator");

        permitMap.put(96, "add_role");
        permitMap.put(97, "remove_role");
        permitMap.put(98, "query_role");
        permitMap.put(99, "modify_role");

        permitMap.put(100, "send_messages");
        permitMap.put(101, "edit_messages");

        permitMap.put(102, "download_database");

        permitMap.put(103, "add_sheep_type");
        permitMap.put(104, "delete_sheep_type");
        permitMap.put(105, "select_sheep_type");
        permitMap.put(106, "modify_sheep_type");

        permitMap.put(107, "download_product_file_action");

        permitMap.put(108, "check_message_of_all_user");
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
                request.getRequestURI().equals("/error") || request.getRequestURI().equals("/question") ||
                request.getRequestURI().equals("/messageBoard/insert") || request.getRequestURI().equals("/video/upload")
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
