package com.deep.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiagnosisPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiagnosisPlanExample() {
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

        public Criteria andDiagnosisTIsNull() {
            addCriterion("diagnosis_t is null");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTIsNotNull() {
            addCriterion("diagnosis_t is not null");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTEqualTo(Date value) {
            addCriterion("diagnosis_t =", value, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTNotEqualTo(Date value) {
            addCriterion("diagnosis_t <>", value, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTGreaterThan(Date value) {
            addCriterion("diagnosis_t >", value, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTGreaterThanOrEqualTo(Date value) {
            addCriterion("diagnosis_t >=", value, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTLessThan(Date value) {
            addCriterion("diagnosis_t <", value, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTLessThanOrEqualTo(Date value) {
            addCriterion("diagnosis_t <=", value, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTIn(List<Date> values) {
            addCriterion("diagnosis_t in", values, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTNotIn(List<Date> values) {
            addCriterion("diagnosis_t not in", values, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTBetween(Date value1, Date value2) {
            addCriterion("diagnosis_t between", value1, value2, "diagnosisT");
            return (Criteria) this;
        }

        public Criteria andDiagnosisTNotBetween(Date value1, Date value2) {
            addCriterion("diagnosis_t not between", value1, value2, "diagnosisT");
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

        public Criteria andEtBIsNull() {
            addCriterion("et_b is null");
            return (Criteria) this;
        }

        public Criteria andEtBIsNotNull() {
            addCriterion("et_b is not null");
            return (Criteria) this;
        }

        public Criteria andEtBEqualTo(String value) {
            addCriterion("et_b =", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBNotEqualTo(String value) {
            addCriterion("et_b <>", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBGreaterThan(String value) {
            addCriterion("et_b >", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBGreaterThanOrEqualTo(String value) {
            addCriterion("et_b >=", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBLessThan(String value) {
            addCriterion("et_b <", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBLessThanOrEqualTo(String value) {
            addCriterion("et_b <=", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBLike(String value) {
            addCriterion("et_b like", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBNotLike(String value) {
            addCriterion("et_b not like", value, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBIn(List<String> values) {
            addCriterion("et_b in", values, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBNotIn(List<String> values) {
            addCriterion("et_b not in", values, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBBetween(String value1, String value2) {
            addCriterion("et_b between", value1, value2, "etB");
            return (Criteria) this;
        }

        public Criteria andEtBNotBetween(String value1, String value2) {
            addCriterion("et_b not between", value1, value2, "etB");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andProfessorIsNull() {
            addCriterion("professor is null");
            return (Criteria) this;
        }

        public Criteria andProfessorIsNotNull() {
            addCriterion("professor is not null");
            return (Criteria) this;
        }

        public Criteria andProfessorEqualTo(String value) {
            addCriterion("professor =", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorNotEqualTo(String value) {
            addCriterion("professor <>", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorGreaterThan(String value) {
            addCriterion("professor >", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorGreaterThanOrEqualTo(String value) {
            addCriterion("professor >=", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorLessThan(String value) {
            addCriterion("professor <", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorLessThanOrEqualTo(String value) {
            addCriterion("professor <=", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorLike(String value) {
            addCriterion("professor like", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorNotLike(String value) {
            addCriterion("professor not like", value, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorIn(List<String> values) {
            addCriterion("professor in", values, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorNotIn(List<String> values) {
            addCriterion("professor not in", values, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorBetween(String value1, String value2) {
            addCriterion("professor between", value1, value2, "professor");
            return (Criteria) this;
        }

        public Criteria andProfessorNotBetween(String value1, String value2) {
            addCriterion("professor not between", value1, value2, "professor");
            return (Criteria) this;
        }

        public Criteria andSupervisorIsNull() {
            addCriterion("supervisor is null");
            return (Criteria) this;
        }

        public Criteria andSupervisorIsNotNull() {
            addCriterion("supervisor is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisorEqualTo(String value) {
            addCriterion("supervisor =", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorNotEqualTo(String value) {
            addCriterion("supervisor <>", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorGreaterThan(String value) {
            addCriterion("supervisor >", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorGreaterThanOrEqualTo(String value) {
            addCriterion("supervisor >=", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorLessThan(String value) {
            addCriterion("supervisor <", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorLessThanOrEqualTo(String value) {
            addCriterion("supervisor <=", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorLike(String value) {
            addCriterion("supervisor like", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorNotLike(String value) {
            addCriterion("supervisor not like", value, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorIn(List<String> values) {
            addCriterion("supervisor in", values, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorNotIn(List<String> values) {
            addCriterion("supervisor not in", values, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorBetween(String value1, String value2) {
            addCriterion("supervisor between", value1, value2, "supervisor");
            return (Criteria) this;
        }

        public Criteria andSupervisorNotBetween(String value1, String value2) {
            addCriterion("supervisor not between", value1, value2, "supervisor");
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

        public Criteria andIsPassIsNull() {
            addCriterion("is_pass is null");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNotNull() {
            addCriterion("is_pass is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassEqualTo(Byte value) {
            addCriterion("is_pass =", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotEqualTo(Byte value) {
            addCriterion("is_pass <>", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThan(Byte value) {
            addCriterion("is_pass >", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_pass >=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThan(Byte value) {
            addCriterion("is_pass <", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThanOrEqualTo(Byte value) {
            addCriterion("is_pass <=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassIn(List<Byte> values) {
            addCriterion("is_pass in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotIn(List<Byte> values) {
            addCriterion("is_pass not in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassBetween(Byte value1, Byte value2) {
            addCriterion("is_pass between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotBetween(Byte value1, Byte value2) {
            addCriterion("is_pass not between", value1, value2, "isPass");
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

        public Criteria andIsPass1IsNull() {
            addCriterion("is_pass1 is null");
            return (Criteria) this;
        }

        public Criteria andIsPass1IsNotNull() {
            addCriterion("is_pass1 is not null");
            return (Criteria) this;
        }

        public Criteria andIsPass1EqualTo(Byte value) {
            addCriterion("is_pass1 =", value, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1NotEqualTo(Byte value) {
            addCriterion("is_pass1 <>", value, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1GreaterThan(Byte value) {
            addCriterion("is_pass1 >", value, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1GreaterThanOrEqualTo(Byte value) {
            addCriterion("is_pass1 >=", value, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1LessThan(Byte value) {
            addCriterion("is_pass1 <", value, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1LessThanOrEqualTo(Byte value) {
            addCriterion("is_pass1 <=", value, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1In(List<Byte> values) {
            addCriterion("is_pass1 in", values, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1NotIn(List<Byte> values) {
            addCriterion("is_pass1 not in", values, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1Between(Byte value1, Byte value2) {
            addCriterion("is_pass1 between", value1, value2, "isPass1");
            return (Criteria) this;
        }

        public Criteria andIsPass1NotBetween(Byte value1, Byte value2) {
            addCriterion("is_pass1 not between", value1, value2, "isPass1");
            return (Criteria) this;
        }

        public Criteria andBuildingLikeInsensitive(String value) {
            addCriterion("upper(building) like", value.toUpperCase(), "building");
            return (Criteria) this;
        }

        public Criteria andEtBLikeInsensitive(String value) {
            addCriterion("upper(et_b) like", value.toUpperCase(), "etB");
            return (Criteria) this;
        }

        public Criteria andOperatorLikeInsensitive(String value) {
            addCriterion("upper(operator) like", value.toUpperCase(), "operator");
            return (Criteria) this;
        }

        public Criteria andProfessorLikeInsensitive(String value) {
            addCriterion("upper(professor) like", value.toUpperCase(), "professor");
            return (Criteria) this;
        }

        public Criteria andSupervisorLikeInsensitive(String value) {
            addCriterion("upper(supervisor) like", value.toUpperCase(), "supervisor");
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