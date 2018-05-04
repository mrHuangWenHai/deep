package com.deep.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NutritionPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NutritionPlanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedIsNull() {
            addCriterion("gmt_supervised is null");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedIsNotNull() {
            addCriterion("gmt_supervised is not null");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedEqualTo(Date value) {
            addCriterion("gmt_supervised =", value, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedNotEqualTo(Date value) {
            addCriterion("gmt_supervised <>", value, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedGreaterThan(Date value) {
            addCriterion("gmt_supervised >", value, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_supervised >=", value, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedLessThan(Date value) {
            addCriterion("gmt_supervised <", value, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_supervised <=", value, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedIn(List<Date> values) {
            addCriterion("gmt_supervised in", values, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedNotIn(List<Date> values) {
            addCriterion("gmt_supervised not in", values, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedBetween(Date value1, Date value2) {
            addCriterion("gmt_supervised between", value1, value2, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andGmtSupervisedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_supervised not between", value1, value2, "gmtSupervised");
            return (Criteria) this;
        }

        public Criteria andFactoryNumIsNull() {
            addCriterion("factory_num is null");
            return (Criteria) this;
        }

        public Criteria andFactoryNumIsNotNull() {
            addCriterion("factory_num is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryNumEqualTo(Long value) {
            addCriterion("factory_num =", value, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumNotEqualTo(Long value) {
            addCriterion("factory_num <>", value, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumGreaterThan(Long value) {
            addCriterion("factory_num >", value, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumGreaterThanOrEqualTo(Long value) {
            addCriterion("factory_num >=", value, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumLessThan(Long value) {
            addCriterion("factory_num <", value, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumLessThanOrEqualTo(Long value) {
            addCriterion("factory_num <=", value, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumIn(List<Long> values) {
            addCriterion("factory_num in", values, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumNotIn(List<Long> values) {
            addCriterion("factory_num not in", values, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumBetween(Long value1, Long value2) {
            addCriterion("factory_num between", value1, value2, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andFactoryNumNotBetween(Long value1, Long value2) {
            addCriterion("factory_num not between", value1, value2, "factoryNum");
            return (Criteria) this;
        }

        public Criteria andBuildingIsNull() {
            addCriterion("building is null");
            return (Criteria) this;
        }

        public Criteria andBuildingIsNotNull() {
            addCriterion("building is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingEqualTo(String value) {
            addCriterion("building =", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotEqualTo(String value) {
            addCriterion("building <>", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingGreaterThan(String value) {
            addCriterion("building >", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingGreaterThanOrEqualTo(String value) {
            addCriterion("building >=", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingLessThan(String value) {
            addCriterion("building <", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingLessThanOrEqualTo(String value) {
            addCriterion("building <=", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingLike(String value) {
            addCriterion("building like", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotLike(String value) {
            addCriterion("building not like", value, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingIn(List<String> values) {
            addCriterion("building in", values, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotIn(List<String> values) {
            addCriterion("building not in", values, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingBetween(String value1, String value2) {
            addCriterion("building between", value1, value2, "building");
            return (Criteria) this;
        }

        public Criteria andBuildingNotBetween(String value1, String value2) {
            addCriterion("building not between", value1, value2, "building");
            return (Criteria) this;
        }

        public Criteria andNutritionTIsNull() {
            addCriterion("nutrition_t is null");
            return (Criteria) this;
        }

        public Criteria andNutritionTIsNotNull() {
            addCriterion("nutrition_t is not null");
            return (Criteria) this;
        }

        public Criteria andNutritionTEqualTo(Date value) {
            addCriterion("nutrition_t =", value, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTNotEqualTo(Date value) {
            addCriterion("nutrition_t <>", value, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTGreaterThan(Date value) {
            addCriterion("nutrition_t >", value, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTGreaterThanOrEqualTo(Date value) {
            addCriterion("nutrition_t >=", value, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTLessThan(Date value) {
            addCriterion("nutrition_t <", value, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTLessThanOrEqualTo(Date value) {
            addCriterion("nutrition_t <=", value, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTIn(List<Date> values) {
            addCriterion("nutrition_t in", values, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTNotIn(List<Date> values) {
            addCriterion("nutrition_t not in", values, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTBetween(Date value1, Date value2) {
            addCriterion("nutrition_t between", value1, value2, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andNutritionTNotBetween(Date value1, Date value2) {
            addCriterion("nutrition_t not between", value1, value2, "nutritionT");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Long value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Long value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Long value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Long value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Long value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Long value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Long> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Long> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Long value1, Long value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Long value1, Long value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andAverageIsNull() {
            addCriterion("average is null");
            return (Criteria) this;
        }

        public Criteria andAverageIsNotNull() {
            addCriterion("average is not null");
            return (Criteria) this;
        }

        public Criteria andAverageEqualTo(String value) {
            addCriterion("average =", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageNotEqualTo(String value) {
            addCriterion("average <>", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageGreaterThan(String value) {
            addCriterion("average >", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageGreaterThanOrEqualTo(String value) {
            addCriterion("average >=", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageLessThan(String value) {
            addCriterion("average <", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageLessThanOrEqualTo(String value) {
            addCriterion("average <=", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageLike(String value) {
            addCriterion("average like", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageNotLike(String value) {
            addCriterion("average not like", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageIn(List<String> values) {
            addCriterion("average in", values, "average");
            return (Criteria) this;
        }

        public Criteria andAverageNotIn(List<String> values) {
            addCriterion("average not in", values, "average");
            return (Criteria) this;
        }

        public Criteria andAverageBetween(String value1, String value2) {
            addCriterion("average between", value1, value2, "average");
            return (Criteria) this;
        }

        public Criteria andAverageNotBetween(String value1, String value2) {
            addCriterion("average not between", value1, value2, "average");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(String value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(String value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(String value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(String value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(String value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLike(String value) {
            addCriterion("period like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotLike(String value) {
            addCriterion("period not like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<String> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<String> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(String value1, String value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(String value1, String value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andWaterIsNull() {
            addCriterion("water is null");
            return (Criteria) this;
        }

        public Criteria andWaterIsNotNull() {
            addCriterion("water is not null");
            return (Criteria) this;
        }

        public Criteria andWaterEqualTo(String value) {
            addCriterion("water =", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotEqualTo(String value) {
            addCriterion("water <>", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterGreaterThan(String value) {
            addCriterion("water >", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterGreaterThanOrEqualTo(String value) {
            addCriterion("water >=", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterLessThan(String value) {
            addCriterion("water <", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterLessThanOrEqualTo(String value) {
            addCriterion("water <=", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterLike(String value) {
            addCriterion("water like", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotLike(String value) {
            addCriterion("water not like", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterIn(List<String> values) {
            addCriterion("water in", values, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotIn(List<String> values) {
            addCriterion("water not in", values, "water");
            return (Criteria) this;
        }

        public Criteria andWaterBetween(String value1, String value2) {
            addCriterion("water between", value1, value2, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotBetween(String value1, String value2) {
            addCriterion("water not between", value1, value2, "water");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNull() {
            addCriterion("operator_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNotNull() {
            addCriterion("operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameEqualTo(String value) {
            addCriterion("operator_name =", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotEqualTo(String value) {
            addCriterion("operator_name <>", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThan(String value) {
            addCriterion("operator_name >", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_name >=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThan(String value) {
            addCriterion("operator_name <", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("operator_name <=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(String value) {
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotLike(String value) {
            addCriterion("operator_name not like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIn(List<String> values) {
            addCriterion("operator_name in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotIn(List<String> values) {
            addCriterion("operator_name not in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameBetween(String value1, String value2) {
            addCriterion("operator_name between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotBetween(String value1, String value2) {
            addCriterion("operator_name not between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andProfessorIsNull() {
            addCriterion("professor_name is null");
            return (Criteria) this;
        }

        public Criteria andProfessorIsNotNull() {
            addCriterion("professor_name is not null");
            return (Criteria) this;
        }

        public Criteria andProfessorNameEqualTo(String value) {
            addCriterion("professor_name =", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameNotEqualTo(String value) {
            addCriterion("professor_name <>", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameGreaterThan(String value) {
            addCriterion("professor_name >", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameGreaterThanOrEqualTo(String value) {
            addCriterion("professor_name >=", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameLessThan(String value) {
            addCriterion("professor_name <", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameLessThanOrEqualTo(String value) {
            addCriterion("professor_name <=", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameLike(String value) {
            addCriterion("professor_name like", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameNotLike(String value) {
            addCriterion("professor_name not like", value, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameIn(List<String> values) {
            addCriterion("professor_name in", values, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameNotIn(List<String> values) {
            addCriterion("professor_name not in", values, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameBetween(String value1, String value2) {
            addCriterion("professor_name between", value1, value2, "professorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameNotBetween(String value1, String value2) {
            addCriterion("professor_name not between", value1, value2, "professorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameIsNull() {
            addCriterion("supervisor_name is null");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameIsNotNull() {
            addCriterion("supervisor_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameEqualTo(String value) {
            addCriterion("supervisor_name =", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotEqualTo(String value) {
            addCriterion("supervisor_name <>", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameGreaterThan(String value) {
            addCriterion("supervisor_name >", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameGreaterThanOrEqualTo(String value) {
            addCriterion("supervisor_name >=", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameLessThan(String value) {
            addCriterion("supervisor_name <", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameLessThanOrEqualTo(String value) {
            addCriterion("supervisor_name <=", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameLike(String value) {
            addCriterion("supervisor_name like", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotLike(String value) {
            addCriterion("supervisor_name not like", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameIn(List<String> values) {
            addCriterion("supervisor_name in", values, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotIn(List<String> values) {
            addCriterion("supervisor_name not in", values, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameBetween(String value1, String value2) {
            addCriterion("supervisor_name between", value1, value2, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotBetween(String value1, String value2) {
            addCriterion("supervisor_name not between", value1, value2, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckIsNull() {
            addCriterion("ispass_check is null");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckIsNotNull() {
            addCriterion("ispass_check is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckEqualTo(Byte value) {
            addCriterion("ispass_check =", value, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckNotEqualTo(Byte value) {
            addCriterion("ispass_check <>", value, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckGreaterThan(Byte value) {
            addCriterion("ispass_check >", value, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckGreaterThanOrEqualTo(Byte value) {
            addCriterion("ispass_check >=", value, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckLessThan(Byte value) {
            addCriterion("ispass_check <", value, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckLessThanOrEqualTo(Byte value) {
            addCriterion("ispass_check <=", value, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckIn(List<Byte> values) {
            addCriterion("ispass_check in", values, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckNotIn(List<Byte> values) {
            addCriterion("ispass_check not in", values, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckBetween(Byte value1, Byte value2) {
            addCriterion("ispass_check between", value1, value2, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andIsPassCheckNotBetween(Byte value1, Byte value2) {
            addCriterion("ispass_check not between", value1, value2, "ispassCheck");
            return (Criteria) this;
        }

        public Criteria andUpassReasonIsNull() {
            addCriterion("upass_reason is null");
            return (Criteria) this;
        }

        public Criteria andUpassReasonIsNotNull() {
            addCriterion("upass_reason is not null");
            return (Criteria) this;
        }

        public Criteria andUpassReasonEqualTo(String value) {
            addCriterion("upass_reason =", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonNotEqualTo(String value) {
            addCriterion("upass_reason <>", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonGreaterThan(String value) {
            addCriterion("upass_reason >", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonGreaterThanOrEqualTo(String value) {
            addCriterion("upass_reason >=", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonLessThan(String value) {
            addCriterion("upass_reason <", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonLessThanOrEqualTo(String value) {
            addCriterion("upass_reason <=", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonLike(String value) {
            addCriterion("upass_reason like", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonNotLike(String value) {
            addCriterion("upass_reason not like", value, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonIn(List<String> values) {
            addCriterion("upass_reason in", values, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonNotIn(List<String> values) {
            addCriterion("upass_reason not in", values, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonBetween(String value1, String value2) {
            addCriterion("upass_reason between", value1, value2, "upassReason");
            return (Criteria) this;
        }

        public Criteria andUpassReasonNotBetween(String value1, String value2) {
            addCriterion("upass_reason not between", value1, value2, "upassReason");
            return (Criteria) this;
        }

        public Criteria andIsPassSupIsNull() {
            addCriterion("ispass_sup is null");
            return (Criteria) this;
        }

        public Criteria andIsPassSupIsNotNull() {
            addCriterion("ispass_sup is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassSupEqualTo(Byte value) {
            addCriterion("ispass_sup =", value, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupNotEqualTo(Byte value) {
            addCriterion("ispass_sup <>", value, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupGreaterThan(Byte value) {
            addCriterion("ispass_sup >", value, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupGreaterThanOrEqualTo(Byte value) {
            addCriterion("ispass_sup >=", value, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupLessThan(Byte value) {
            addCriterion("ispass_sup <", value, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupLessThanOrEqualTo(Byte value) {
            addCriterion("ispass_sup <=", value, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupIn(List<Byte> values) {
            addCriterion("ispass_sup in", values, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupNotIn(List<Byte> values) {
            addCriterion("ispass_sup not in", values, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupBetween(Byte value1, Byte value2) {
            addCriterion("ispass_sup between", value1, value2, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andIsPassSupNotBetween(Byte value1, Byte value2) {
            addCriterion("ispass_sup not between", value1, value2, "ispassSup");
            return (Criteria) this;
        }

        public Criteria andBuildingLikeInsensitive(String value) {
            addCriterion("upper(building) like", value.toUpperCase(), "building");
            return (Criteria) this;
        }

        public Criteria andAverageLikeInsensitive(String value) {
            addCriterion("upper(average) like", value.toUpperCase(), "average");
            return (Criteria) this;
        }

        public Criteria andPeriodLikeInsensitive(String value) {
            addCriterion("upper(period) like", value.toUpperCase(), "period");
            return (Criteria) this;
        }

        public Criteria andWaterLikeInsensitive(String value) {
            addCriterion("upper(water) like", value.toUpperCase(), "water");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikeInsensitive(String value) {
            addCriterion("upper(operator_name) like", value.toUpperCase(), "operatorName");
            return (Criteria) this;
        }

        public Criteria andProfessorNameLikeInsensitive(String value) {
            addCriterion("upper(professor_name) like", value.toUpperCase(), "professorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameLikeInsensitive(String value) {
            addCriterion("upper(supervisor_name) like", value.toUpperCase(), "supervisorName");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andUpassReasonLikeInsensitive(String value) {
            addCriterion("upper(upass_reason) like", value.toUpperCase(), "upassReason");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}