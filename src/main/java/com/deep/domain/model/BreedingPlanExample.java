package com.deep.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BreedingPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BreedingPlanExample() {
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

        public Criteria andMEtIIsNull() {
            addCriterion("m_et_i is null");
            return (Criteria) this;
        }

        public Criteria andMEtIIsNotNull() {
            addCriterion("m_et_i is not null");
            return (Criteria) this;
        }

        public Criteria andMEtIEqualTo(String value) {
            addCriterion("m_et_i =", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtINotEqualTo(String value) {
            addCriterion("m_et_i <>", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtIGreaterThan(String value) {
            addCriterion("m_et_i >", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtIGreaterThanOrEqualTo(String value) {
            addCriterion("m_et_i >=", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtILessThan(String value) {
            addCriterion("m_et_i <", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtILessThanOrEqualTo(String value) {
            addCriterion("m_et_i <=", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtILike(String value) {
            addCriterion("m_et_i like", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtINotLike(String value) {
            addCriterion("m_et_i not like", value, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtIIn(List<String> values) {
            addCriterion("m_et_i in", values, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtINotIn(List<String> values) {
            addCriterion("m_et_i not in", values, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtIBetween(String value1, String value2) {
            addCriterion("m_et_i between", value1, value2, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtINotBetween(String value1, String value2) {
            addCriterion("m_et_i not between", value1, value2, "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtBIsNull() {
            addCriterion("m_et_b is null");
            return (Criteria) this;
        }

        public Criteria andMEtBIsNotNull() {
            addCriterion("m_et_b is not null");
            return (Criteria) this;
        }

        public Criteria andMEtBEqualTo(String value) {
            addCriterion("m_et_b =", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBNotEqualTo(String value) {
            addCriterion("m_et_b <>", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBGreaterThan(String value) {
            addCriterion("m_et_b >", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBGreaterThanOrEqualTo(String value) {
            addCriterion("m_et_b >=", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBLessThan(String value) {
            addCriterion("m_et_b <", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBLessThanOrEqualTo(String value) {
            addCriterion("m_et_b <=", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBLike(String value) {
            addCriterion("m_et_b like", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBNotLike(String value) {
            addCriterion("m_et_b not like", value, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBIn(List<String> values) {
            addCriterion("m_et_b in", values, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBNotIn(List<String> values) {
            addCriterion("m_et_b not in", values, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBBetween(String value1, String value2) {
            addCriterion("m_et_b between", value1, value2, "mEtB");
            return (Criteria) this;
        }

        public Criteria andMEtBNotBetween(String value1, String value2) {
            addCriterion("m_et_b not between", value1, value2, "mEtB");
            return (Criteria) this;
        }

        public Criteria andFEtIIsNull() {
            addCriterion("f_et_i is null");
            return (Criteria) this;
        }

        public Criteria andFEtIIsNotNull() {
            addCriterion("f_et_i is not null");
            return (Criteria) this;
        }

        public Criteria andFEtIEqualTo(String value) {
            addCriterion("f_et_i =", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtINotEqualTo(String value) {
            addCriterion("f_et_i <>", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtIGreaterThan(String value) {
            addCriterion("f_et_i >", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtIGreaterThanOrEqualTo(String value) {
            addCriterion("f_et_i >=", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtILessThan(String value) {
            addCriterion("f_et_i <", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtILessThanOrEqualTo(String value) {
            addCriterion("f_et_i <=", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtILike(String value) {
            addCriterion("f_et_i like", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtINotLike(String value) {
            addCriterion("f_et_i not like", value, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtIIn(List<String> values) {
            addCriterion("f_et_i in", values, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtINotIn(List<String> values) {
            addCriterion("f_et_i not in", values, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtIBetween(String value1, String value2) {
            addCriterion("f_et_i between", value1, value2, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtINotBetween(String value1, String value2) {
            addCriterion("f_et_i not between", value1, value2, "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtBIsNull() {
            addCriterion("f_et_b is null");
            return (Criteria) this;
        }

        public Criteria andFEtBIsNotNull() {
            addCriterion("f_et_b is not null");
            return (Criteria) this;
        }

        public Criteria andFEtBEqualTo(String value) {
            addCriterion("f_et_b =", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBNotEqualTo(String value) {
            addCriterion("f_et_b <>", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBGreaterThan(String value) {
            addCriterion("f_et_b >", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBGreaterThanOrEqualTo(String value) {
            addCriterion("f_et_b >=", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBLessThan(String value) {
            addCriterion("f_et_b <", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBLessThanOrEqualTo(String value) {
            addCriterion("f_et_b <=", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBLike(String value) {
            addCriterion("f_et_b like", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBNotLike(String value) {
            addCriterion("f_et_b not like", value, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBIn(List<String> values) {
            addCriterion("f_et_b in", values, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBNotIn(List<String> values) {
            addCriterion("f_et_b not in", values, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBBetween(String value1, String value2) {
            addCriterion("f_et_b between", value1, value2, "fEtB");
            return (Criteria) this;
        }

        public Criteria andFEtBNotBetween(String value1, String value2) {
            addCriterion("f_et_b not between", value1, value2, "fEtB");
            return (Criteria) this;
        }

        public Criteria andBreedingTIsNull() {
            addCriterion("Breeding_t is null");
            return (Criteria) this;
        }

        public Criteria andBreedingTIsNotNull() {
            addCriterion("Breeding_t is not null");
            return (Criteria) this;
        }

        public Criteria andBreedingTEqualTo(Date value) {
            addCriterion("Breeding_t =", value, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTNotEqualTo(Date value) {
            addCriterion("Breeding_t <>", value, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTGreaterThan(Date value) {
            addCriterion("Breeding_t >", value, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTGreaterThanOrEqualTo(Date value) {
            addCriterion("Breeding_t >=", value, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTLessThan(Date value) {
            addCriterion("Breeding_t <", value, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTLessThanOrEqualTo(Date value) {
            addCriterion("Breeding_t <=", value, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTIn(List<Date> values) {
            addCriterion("Breeding_t in", values, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTNotIn(List<Date> values) {
            addCriterion("Breeding_t not in", values, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTBetween(Date value1, Date value2) {
            addCriterion("Breeding_t between", value1, value2, "breedingT");
            return (Criteria) this;
        }

        public Criteria andBreedingTNotBetween(Date value1, Date value2) {
            addCriterion("Breeding_t not between", value1, value2, "breedingT");
            return (Criteria) this;
        }

        public Criteria andGestationTIsNull() {
            addCriterion("gestation_t is null");
            return (Criteria) this;
        }

        public Criteria andGestationTIsNotNull() {
            addCriterion("gestation_t is not null");
            return (Criteria) this;
        }

        public Criteria andGestationTEqualTo(Date value) {
            addCriterion("gestation_t =", value, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTNotEqualTo(Date value) {
            addCriterion("gestation_t <>", value, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTGreaterThan(Date value) {
            addCriterion("gestation_t >", value, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTGreaterThanOrEqualTo(Date value) {
            addCriterion("gestation_t >=", value, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTLessThan(Date value) {
            addCriterion("gestation_t <", value, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTLessThanOrEqualTo(Date value) {
            addCriterion("gestation_t <=", value, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTIn(List<Date> values) {
            addCriterion("gestation_t in", values, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTNotIn(List<Date> values) {
            addCriterion("gestation_t not in", values, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTBetween(Date value1, Date value2) {
            addCriterion("gestation_t between", value1, value2, "gestationT");
            return (Criteria) this;
        }

        public Criteria andGestationTNotBetween(Date value1, Date value2) {
            addCriterion("gestation_t not between", value1, value2, "gestationT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITIsNull() {
            addCriterion("Prenatal_i_t is null");
            return (Criteria) this;
        }

        public Criteria andPrenatalITIsNotNull() {
            addCriterion("Prenatal_i_t is not null");
            return (Criteria) this;
        }

        public Criteria andPrenatalITEqualTo(Date value) {
            addCriterion("Prenatal_i_t =", value, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITNotEqualTo(Date value) {
            addCriterion("Prenatal_i_t <>", value, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITGreaterThan(Date value) {
            addCriterion("Prenatal_i_t >", value, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITGreaterThanOrEqualTo(Date value) {
            addCriterion("Prenatal_i_t >=", value, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITLessThan(Date value) {
            addCriterion("Prenatal_i_t <", value, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITLessThanOrEqualTo(Date value) {
            addCriterion("Prenatal_i_t <=", value, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITIn(List<Date> values) {
            addCriterion("Prenatal_i_t in", values, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITNotIn(List<Date> values) {
            addCriterion("Prenatal_i_t not in", values, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITBetween(Date value1, Date value2) {
            addCriterion("Prenatal_i_t between", value1, value2, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andPrenatalITNotBetween(Date value1, Date value2) {
            addCriterion("Prenatal_i_t not between", value1, value2, "prenatalIT");
            return (Criteria) this;
        }

        public Criteria andCubTIsNull() {
            addCriterion("cub_t is null");
            return (Criteria) this;
        }

        public Criteria andCubTIsNotNull() {
            addCriterion("cub_t is not null");
            return (Criteria) this;
        }

        public Criteria andCubTEqualTo(Date value) {
            addCriterion("cub_t =", value, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTNotEqualTo(Date value) {
            addCriterion("cub_t <>", value, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTGreaterThan(Date value) {
            addCriterion("cub_t >", value, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTGreaterThanOrEqualTo(Date value) {
            addCriterion("cub_t >=", value, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTLessThan(Date value) {
            addCriterion("cub_t <", value, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTLessThanOrEqualTo(Date value) {
            addCriterion("cub_t <=", value, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTIn(List<Date> values) {
            addCriterion("cub_t in", values, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTNotIn(List<Date> values) {
            addCriterion("cub_t not in", values, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTBetween(Date value1, Date value2) {
            addCriterion("cub_t between", value1, value2, "cubT");
            return (Criteria) this;
        }

        public Criteria andCubTNotBetween(Date value1, Date value2) {
            addCriterion("cub_t not between", value1, value2, "cubT");
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

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
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

        public Criteria andProfessorNameIsNull() {
            addCriterion("professor_name is null");
            return (Criteria) this;
        }

        public Criteria andProfessorNameIsNotNull() {
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

        public Criteria andIspassCheckIsNull() {
            addCriterion("ispass_check is null");
            return (Criteria) this;
        }

        public Criteria andIspassCheckIsNotNull() {
            addCriterion("ispass_check is not null");
            return (Criteria) this;
        }

        public Criteria andIspassCheckEqualTo(Byte value) {
            addCriterion("ispass_check =", value, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckNotEqualTo(Byte value) {
            addCriterion("ispass_check <>", value, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckGreaterThan(Byte value) {
            addCriterion("ispass_check >", value, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckGreaterThanOrEqualTo(Byte value) {
            addCriterion("ispass_check >=", value, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckLessThan(Byte value) {
            addCriterion("ispass_check <", value, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckLessThanOrEqualTo(Byte value) {
            addCriterion("ispass_check <=", value, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckIn(List<Byte> values) {
            addCriterion("ispass_check in", values, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckNotIn(List<Byte> values) {
            addCriterion("ispass_check not in", values, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckBetween(Byte value1, Byte value2) {
            addCriterion("ispass_check between", value1, value2, "ispass_check");
            return (Criteria) this;
        }

        public Criteria andIspassCheckNotBetween(Byte value1, Byte value2) {
            addCriterion("ispass_check not between", value1, value2, "ispass_check");
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

        public Criteria andIspassSupIsNull() {
            addCriterion("ispass_sup is null");
            return (Criteria) this;
        }

        public Criteria andIspassSupIsNotNull() {
            addCriterion("ispass_sup is not null");
            return (Criteria) this;
        }

        public Criteria andIspassSupEqualTo(Byte value) {
            addCriterion("ispass_sup =", value, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupNotEqualTo(Byte value) {
            addCriterion("ispass_sup <>", value, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupGreaterThan(Byte value) {
            addCriterion("ispass_sup >", value, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupGreaterThanOrEqualTo(Byte value) {
            addCriterion("ispass_sup >=", value, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupLessThan(Byte value) {
            addCriterion("ispass_sup <", value, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupLessThanOrEqualTo(Byte value) {
            addCriterion("ispass_sup <=", value, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupIn(List<Byte> values) {
            addCriterion("ispass_sup in", values, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupNotIn(List<Byte> values) {
            addCriterion("ispass_sup not in", values, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupBetween(Byte value1, Byte value2) {
            addCriterion("ispass_sup between", value1, value2, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andIspassSupNotBetween(Byte value1, Byte value2) {
            addCriterion("ispass_sup not between", value1, value2, "ispass_sup");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Integer value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Integer value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Integer value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Integer value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Integer> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Integer> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdIsNull() {
            addCriterion("professor_id is null");
            return (Criteria) this;
        }

        public Criteria andProfessorIdIsNotNull() {
            addCriterion("professor_id is not null");
            return (Criteria) this;
        }

        public Criteria andProfessorIdEqualTo(Integer value) {
            addCriterion("professor_id =", value, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdNotEqualTo(Integer value) {
            addCriterion("professor_id <>", value, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdGreaterThan(Integer value) {
            addCriterion("professor_id >", value, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("professor_id >=", value, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdLessThan(Integer value) {
            addCriterion("professor_id <", value, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdLessThanOrEqualTo(Integer value) {
            addCriterion("professor_id <=", value, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdIn(List<Integer> values) {
            addCriterion("professor_id in", values, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdNotIn(List<Integer> values) {
            addCriterion("professor_id not in", values, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdBetween(Integer value1, Integer value2) {
            addCriterion("professor_id between", value1, value2, "professorId");
            return (Criteria) this;
        }

        public Criteria andProfessorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("professor_id not between", value1, value2, "professorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIsNull() {
            addCriterion("supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIsNotNull() {
            addCriterion("supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdEqualTo(Integer value) {
            addCriterion("supervisor_id =", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotEqualTo(Integer value) {
            addCriterion("supervisor_id <>", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdGreaterThan(Integer value) {
            addCriterion("supervisor_id >", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("supervisor_id >=", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLessThan(Integer value) {
            addCriterion("supervisor_id <", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLessThanOrEqualTo(Integer value) {
            addCriterion("supervisor_id <=", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIn(List<Integer> values) {
            addCriterion("supervisor_id in", values, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotIn(List<Integer> values) {
            addCriterion("supervisor_id not in", values, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdBetween(Integer value1, Integer value2) {
            addCriterion("supervisor_id between", value1, value2, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("supervisor_id not between", value1, value2, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andBuildingLikeInsensitive(String value) {
            addCriterion("upper(building) like", value.toUpperCase(), "building");
            return (Criteria) this;
        }

        public Criteria andMEtILikeInsensitive(String value) {
            addCriterion("upper(m_et_i) like", value.toUpperCase(), "mEtI");
            return (Criteria) this;
        }

        public Criteria andMEtBLikeInsensitive(String value) {
            addCriterion("upper(m_et_b) like", value.toUpperCase(), "mEtB");
            return (Criteria) this;
        }

        public Criteria andFEtILikeInsensitive(String value) {
            addCriterion("upper(f_et_i) like", value.toUpperCase(), "fEtI");
            return (Criteria) this;
        }

        public Criteria andFEtBLikeInsensitive(String value) {
            addCriterion("upper(f_et_b) like", value.toUpperCase(), "fEtB");
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