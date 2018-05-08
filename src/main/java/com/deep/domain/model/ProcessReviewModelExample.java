package com.deep.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProcessReviewModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProcessReviewModelExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andHealthRecordIsNull() {
            addCriterion("health_record is null");
            return (Criteria) this;
        }

        public Criteria andHealthRecordIsNotNull() {
            addCriterion("health_record is not null");
            return (Criteria) this;
        }

        public Criteria andHealthRecordEqualTo(Integer value) {
            addCriterion("health_record =", value, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordNotEqualTo(Integer value) {
            addCriterion("health_record <>", value, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordGreaterThan(Integer value) {
            addCriterion("health_record >", value, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("health_record >=", value, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordLessThan(Integer value) {
            addCriterion("health_record <", value, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordLessThanOrEqualTo(Integer value) {
            addCriterion("health_record <=", value, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordIn(List<Integer> values) {
            addCriterion("health_record in", values, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordNotIn(List<Integer> values) {
            addCriterion("health_record not in", values, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordBetween(Integer value1, Integer value2) {
            addCriterion("health_record between", value1, value2, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andHealthRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("health_record not between", value1, value2, "healthRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordIsNull() {
            addCriterion("disinfection_record is null");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordIsNotNull() {
            addCriterion("disinfection_record is not null");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordEqualTo(Integer value) {
            addCriterion("disinfection_record =", value, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordNotEqualTo(Integer value) {
            addCriterion("disinfection_record <>", value, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordGreaterThan(Integer value) {
            addCriterion("disinfection_record >", value, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("disinfection_record >=", value, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordLessThan(Integer value) {
            addCriterion("disinfection_record <", value, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordLessThanOrEqualTo(Integer value) {
            addCriterion("disinfection_record <=", value, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordIn(List<Integer> values) {
            addCriterion("disinfection_record in", values, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordNotIn(List<Integer> values) {
            addCriterion("disinfection_record not in", values, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordBetween(Integer value1, Integer value2) {
            addCriterion("disinfection_record between", value1, value2, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andDisinfectionRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("disinfection_record not between", value1, value2, "disinfectionRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordIsNull() {
            addCriterion("immunization_record is null");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordIsNotNull() {
            addCriterion("immunization_record is not null");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordEqualTo(Integer value) {
            addCriterion("immunization_record =", value, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordNotEqualTo(Integer value) {
            addCriterion("immunization_record <>", value, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordGreaterThan(Integer value) {
            addCriterion("immunization_record >", value, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("immunization_record >=", value, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordLessThan(Integer value) {
            addCriterion("immunization_record <", value, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordLessThanOrEqualTo(Integer value) {
            addCriterion("immunization_record <=", value, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordIn(List<Integer> values) {
            addCriterion("immunization_record in", values, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordNotIn(List<Integer> values) {
            addCriterion("immunization_record not in", values, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordBetween(Integer value1, Integer value2) {
            addCriterion("immunization_record between", value1, value2, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andImmunizationRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("immunization_record not between", value1, value2, "immunizationRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordIsNull() {
            addCriterion("deworm_record is null");
            return (Criteria) this;
        }

        public Criteria andDewormRecordIsNotNull() {
            addCriterion("deworm_record is not null");
            return (Criteria) this;
        }

        public Criteria andDewormRecordEqualTo(Integer value) {
            addCriterion("deworm_record =", value, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordNotEqualTo(Integer value) {
            addCriterion("deworm_record <>", value, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordGreaterThan(Integer value) {
            addCriterion("deworm_record >", value, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("deworm_record >=", value, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordLessThan(Integer value) {
            addCriterion("deworm_record <", value, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordLessThanOrEqualTo(Integer value) {
            addCriterion("deworm_record <=", value, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordIn(List<Integer> values) {
            addCriterion("deworm_record in", values, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordNotIn(List<Integer> values) {
            addCriterion("deworm_record not in", values, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordBetween(Integer value1, Integer value2) {
            addCriterion("deworm_record between", value1, value2, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andDewormRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("deworm_record not between", value1, value2, "dewormRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordIsNull() {
            addCriterion("nutrition_record is null");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordIsNotNull() {
            addCriterion("nutrition_record is not null");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordEqualTo(Integer value) {
            addCriterion("nutrition_record =", value, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordNotEqualTo(Integer value) {
            addCriterion("nutrition_record <>", value, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordGreaterThan(Integer value) {
            addCriterion("nutrition_record >", value, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("nutrition_record >=", value, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordLessThan(Integer value) {
            addCriterion("nutrition_record <", value, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordLessThanOrEqualTo(Integer value) {
            addCriterion("nutrition_record <=", value, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordIn(List<Integer> values) {
            addCriterion("nutrition_record in", values, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordNotIn(List<Integer> values) {
            addCriterion("nutrition_record not in", values, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordBetween(Integer value1, Integer value2) {
            addCriterion("nutrition_record between", value1, value2, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andNutritionRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("nutrition_record not between", value1, value2, "nutritionRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIsNull() {
            addCriterion("breeding_record is null");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIsNotNull() {
            addCriterion("breeding_record is not null");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordEqualTo(Integer value) {
            addCriterion("breeding_record =", value, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordNotEqualTo(Integer value) {
            addCriterion("breeding_record <>", value, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordGreaterThan(Integer value) {
            addCriterion("breeding_record >", value, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("breeding_record >=", value, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordLessThan(Integer value) {
            addCriterion("breeding_record <", value, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordLessThanOrEqualTo(Integer value) {
            addCriterion("breeding_record <=", value, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIn(List<Integer> values) {
            addCriterion("breeding_record in", values, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordNotIn(List<Integer> values) {
            addCriterion("breeding_record not in", values, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordBetween(Integer value1, Integer value2) {
            addCriterion("breeding_record between", value1, value2, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("breeding_record not between", value1, value2, "breedingRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordIsNull() {
            addCriterion("prevention_record is null");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordIsNotNull() {
            addCriterion("prevention_record is not null");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordEqualTo(Integer value) {
            addCriterion("prevention_record =", value, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordNotEqualTo(Integer value) {
            addCriterion("prevention_record <>", value, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordGreaterThan(Integer value) {
            addCriterion("prevention_record >", value, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("prevention_record >=", value, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordLessThan(Integer value) {
            addCriterion("prevention_record <", value, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordLessThanOrEqualTo(Integer value) {
            addCriterion("prevention_record <=", value, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordIn(List<Integer> values) {
            addCriterion("prevention_record in", values, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordNotIn(List<Integer> values) {
            addCriterion("prevention_record not in", values, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordBetween(Integer value1, Integer value2) {
            addCriterion("prevention_record between", value1, value2, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andPreventionRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("prevention_record not between", value1, value2, "preventionRecord");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultIsNull() {
            addCriterion("comprehensive_result is null");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultIsNotNull() {
            addCriterion("comprehensive_result is not null");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultEqualTo(Integer value) {
            addCriterion("comprehensive_result =", value, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultNotEqualTo(Integer value) {
            addCriterion("comprehensive_result <>", value, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultGreaterThan(Integer value) {
            addCriterion("comprehensive_result >", value, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("comprehensive_result >=", value, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultLessThan(Integer value) {
            addCriterion("comprehensive_result <", value, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultLessThanOrEqualTo(Integer value) {
            addCriterion("comprehensive_result <=", value, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultIn(List<Integer> values) {
            addCriterion("comprehensive_result in", values, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultNotIn(List<Integer> values) {
            addCriterion("comprehensive_result not in", values, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultBetween(Integer value1, Integer value2) {
            addCriterion("comprehensive_result between", value1, value2, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andComprehensiveResultNotBetween(Integer value1, Integer value2) {
            addCriterion("comprehensive_result not between", value1, value2, "comprehensiveResult");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNull() {
            addCriterion("reviewer is null");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNotNull() {
            addCriterion("reviewer is not null");
            return (Criteria) this;
        }

        public Criteria andReviewerEqualTo(String value) {
            addCriterion("reviewer =", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotEqualTo(String value) {
            addCriterion("reviewer <>", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThan(String value) {
            addCriterion("reviewer >", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThanOrEqualTo(String value) {
            addCriterion("reviewer >=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThan(String value) {
            addCriterion("reviewer <", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThanOrEqualTo(String value) {
            addCriterion("reviewer <=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLike(String value) {
            addCriterion("reviewer like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotLike(String value) {
            addCriterion("reviewer not like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerIn(List<String> values) {
            addCriterion("reviewer in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotIn(List<String> values) {
            addCriterion("reviewer not in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerBetween(String value1, String value2) {
            addCriterion("reviewer between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotBetween(String value1, String value2) {
            addCriterion("reviewer not between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterionForJDBCDate("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterionForJDBCDate("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterionForJDBCDate("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andEartagAddressIsNull() {
            addCriterion("eartag_address is null");
            return (Criteria) this;
        }

        public Criteria andEartagAddressIsNotNull() {
            addCriterion("eartag_address is not null");
            return (Criteria) this;
        }

        public Criteria andEartagAddressEqualTo(String value) {
            addCriterion("eartag_address =", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressNotEqualTo(String value) {
            addCriterion("eartag_address <>", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressGreaterThan(String value) {
            addCriterion("eartag_address >", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressGreaterThanOrEqualTo(String value) {
            addCriterion("eartag_address >=", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressLessThan(String value) {
            addCriterion("eartag_address <", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressLessThanOrEqualTo(String value) {
            addCriterion("eartag_address <=", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressLike(String value) {
            addCriterion("eartag_address like", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressNotLike(String value) {
            addCriterion("eartag_address not like", value, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressIn(List<String> values) {
            addCriterion("eartag_address in", values, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressNotIn(List<String> values) {
            addCriterion("eartag_address not in", values, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressBetween(String value1, String value2) {
            addCriterion("eartag_address between", value1, value2, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andEartagAddressNotBetween(String value1, String value2) {
            addCriterion("eartag_address not between", value1, value2, "eartagAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLikeInsensitive(String value) {
            addCriterion("upper(company_name) like", value.toUpperCase(), "companyName");
            return (Criteria) this;
        }

        public Criteria andReviewerLikeInsensitive(String value) {
            addCriterion("upper(reviewer) like", value.toUpperCase(), "reviewer");
            return (Criteria) this;
        }

        public Criteria andEartagAddressLikeInsensitive(String value) {
            addCriterion("upper(eartag_address) like", value.toUpperCase(), "eartagAddress");
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