package com.deep.infra.persistence.sql.mapper.product_chain;

import com.deep.api.request.ProfessorRequest;
import com.deep.api.request.SupervisorRequest;
import com.deep.domain.model.NutritionAnotherPlanModel;
import com.deep.infra.selective.NutritionAnotherPlanSelective;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface NutritionAnotherPlanMapper {
    @Insert("insert into nutrition_plan_another (\n" +
            "  gmt_supervised, factory_num, factory_name, " +
            "  building, ear_tag_file, nutrition_t, quantity, average, period, water, " +
            "  material_a, material_m, material_o, material_w_m, material_w_o, " +
            "  roughage_p, roughage_d, roughage_o, roughage_w_p, roughage_w_d, roughage_w_o, " +
            "  day_m, " +
            "  picking_m, picking_r, picking_o, " +
            "  remark, " +
            "  operator_id, operator_name, " +
            "  professor_id, professor_name, " +
            "  supervisor_id, supervisor_name, " +
            "  ispass_check, upass_reason, ispass_sup" + "\n" +
            ") values (\n" +
            " #{gmtSupervised}, " +
            " #{factoryNum}, #{factoryName}, " +
            " #{building}, #{earTagFile}, #{nutritionT}, #{quantity}, #{average}, #{period}, #{water}, " +
            " #{materialA}, #{materialM}, #{materialO}, #{materialWM}, #{materialWO}, " +
            " #{roughageP}, #{roughageD}, #{roughageO}, #{roughageWP}, #{roughageWD}, #{roughageWO}, " +
            " #{dayM}, " +
            " #{pickingM}, #{pickingR}, #{pickingO}, " +
            " #{remark}, " +
            " #{operatorId}, #{operatorName}, " +
            " #{professorId}, #{professorName}, " +
            " #{supervisorId}, #{supervisorName}, " +
            " 2, #{upassReason}, 2" + "\n" +
            ")")
    public Long insertRecord(NutritionAnotherPlanModel model);

    @Delete("delete from nutrition_plan_another where id = #{id} and ispass_check != 1")
    public Long deleteRecord(Long id);

    @Update("update nutrition_plan_another set " +
            "gmt_supervised = #{model.gmtSupervised}, " +
            "factory_num = #{model.factoryNum}, factory_name = #{model.factoryName}, " +

            "building = #{model.building}, ear_tag_file = #{model.earTagFile}, nutrition_t = #{model.nutritionT}, " +
            "quantity = #{model.quantity}, average = #{model.average}, period = #{model.period}, water = #{model.water}, " +

            "material_a = #{model.materialA}, material_m = #{model.materialM}, material_o = #{model.materialO}, " +
            "material_w_m = #{model.materialWM}, material_w_o = #{model.materialWO}, " +

            "roughage_p = #{model.roughageP}, roughage_d = #{model.roughageD}, roughage_o = #{model.roughageO}, " +
            "roughage_w_p = #{model.roughageWP}, roughage_w_d = #{model.roughageWD}, roughage_w_o = #{model.roughageWO}, " +

            "day_m = #{model.dayM}, " +

            "picking_m = #{model.pickingM}, picking_r = #{model.pickingR}, picking_o = #{model.pickingO}, " +

            "remark = #{model.remark}" +

            "operator_id = #{model.operatorId}, operator_name = #{model.operatorName}, " +
            "professor_id = #{model.professorId}, operator_name = #{model.professorName}, " +
            "supervisor_id = #{model.supervisorId}, operator_name = #{model.supervisorName}, " +

            "ispass_check = 2, upass_reason = #{upassReason}, ispass_sup = #{model.ispassSup} " +
            "where id = #{id} and ispass_check != 1")
    public Long updateAllTermsOfRecord(@Param("model") NutritionAnotherPlanModel model, @Param("id")Long id);

    @Update("update nutrition_plan_another set " +
            "ispass_check = #{ispassCheck}, " +
            "professor_id = #{professor}, " +
            "professor_name = #{name}," +
            "upass_reason = #{unpassReason} " +
            "where id = #{id}")
    public Long updateProfessorTermsOfRecord(@Param("request")ProfessorRequest request, @Param("id")Long id);

    @Update("update nutrition_plan_another set " +
            "ispass_sup = #{ispassSup}, " +
            "gmt_supervised = now(), " +
            "supervisor_id = #{supervisor}, " +
            "supervisor_name = #{name} " +
            "where id = #{id}")
    public Long updateSupervisorTermsOfRecord(@Param("request") SupervisorRequest request, @Param("id")Long id);

    @Select("select * from nutrition_plan_another where id = #{id}")
    public NutritionAnotherPlanModel selectRecord(Long id);

    @SelectProvider(type = NutritionAnotherPlanSelective.class, method = "selectRecords")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "gmtSupervised", column = "gmt_supervised"),
            @Result(property = "factoryNum", column = "factory_num"),
            @Result(property = "factoryName", column = "factory_name"),
            @Result(property = "building", column = "building"),
            @Result(property = "earTagFile", column = "ear_tag_file"),
            @Result(property = "nutritionT", column = "nutrition_t"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "average", column = "average"),
            @Result(property = "period", column = "period"),
            @Result(property = "water", column = "water"),
            @Result(property = "materialA", column = "material_a"),
            @Result(property = "materialM", column = "material_m"),
            @Result(property = "materialO", column = "material_o"),
            @Result(property = "materialWM", column = "material_w_m"),
            @Result(property = "materialWO", column = "material_w_o"),
            @Result(property = "roughageP", column = "roughage_p"),
            @Result(property = "roughageD", column = "roughage_d"),
            @Result(property = "roughageO", column = "roughage_o"),
            @Result(property = "roughageWP", column = "roughage_w_p"),
            @Result(property = "roughageWD", column = "roughage_w_d"),
            @Result(property = "roughageWO", column = "roughage_w_o"),
            @Result(property = "dayM", column = "day_m"),
            @Result(property = "pickingM", column = "picking_m"),
            @Result(property = "pickingR", column = "picking_r"),
            @Result(property = "pickingO", column = "picking_o"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "operatorId", column = "operator_id"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "professorId", column = "professor_id"),
            @Result(property = "professorName", column = "professor_name"),
            @Result(property = "supervisorId", column = "supervisor_id"),
            @Result(property = "supervisorName", column = "supervisor_name"),
            @Result(property = "ispassCheck", column = "ispass_check"),
            @Result(property = "upassReason", column = "upass_reason"),
            @Result(property = "ispassSup", column = "ispass_sup")
    })
    public List<NutritionAnotherPlanModel> selectRecords(@Param("start") Long start,
                                                         @Param("lists") List<Long> lists,
                                                         @Param("factoryName") String factoryName,
                                                         @Param("isPassCheck") Byte isPassCheck,
                                                         @Param("startTime") String startTime,
                                                         @Param("endTime") String endTime,
                                                         @Param("earTag") String earTag);

    @SelectProvider(type = NutritionAnotherPlanSelective.class, method = "countRecords")
    public Long countRecords(@Param("lists") List<Long> lists,
                             @Param("factoryName") String factoryName,
                             @Param("isPassCheck") Byte isPassCheck,
                             @Param("startTime") String startTime,
                             @Param("endTime") String endTime,
                             @Param("earTag") String earTag);

    @Select("select * from nutrition_another_plan where nutrition_t between #{startDate} and #{endDate}) and (factory_num = #{factory})")
    public List<NutritionAnotherPlanModel> selectRecordByNutritionT(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("factory") Long factory);
}
