package com.deep.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RecoveryModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecoveryModelExample() {
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

        public Criteria andSheepTypeIsNull() {
            addCriterion("sheep_type is null");
            return (Criteria) this;
        }

        public Criteria andSheepTypeIsNotNull() {
            addCriterion("sheep_type is not null");
            return (Criteria) this;
        }

        public Criteria andSheepTypeEqualTo(Integer value) {
            addCriterion("sheep_type =", value, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeNotEqualTo(Integer value) {
            addCriterion("sheep_type <>", value, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeGreaterThan(Integer value) {
            addCriterion("sheep_type >", value, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sheep_type >=", value, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeLessThan(Integer value) {
            addCriterion("sheep_type <", value, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeLessThanOrEqualTo(Integer value) {
            addCriterion("sheep_type <=", value, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeIn(List<Integer> values) {
            addCriterion("sheep_type in", values, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeNotIn(List<Integer> values) {
            addCriterion("sheep_type not in", values, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeBetween(Integer value1, Integer value2) {
            addCriterion("sheep_type between", value1, value2, "sheepType");
            return (Criteria) this;
        }

        public Criteria andSheepTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sheep_type not between", value1, value2, "sheepType");
            return (Criteria) this;
        }

        public Criteria andOverallIsNull() {
            addCriterion("overall is null");
            return (Criteria) this;
        }

        public Criteria andOverallIsNotNull() {
            addCriterion("overall is not null");
            return (Criteria) this;
        }

        public Criteria andOverallEqualTo(Integer value) {
            addCriterion("overall =", value, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallNotEqualTo(Integer value) {
            addCriterion("overall <>", value, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallGreaterThan(Integer value) {
            addCriterion("overall >", value, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallGreaterThanOrEqualTo(Integer value) {
            addCriterion("overall >=", value, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallLessThan(Integer value) {
            addCriterion("overall <", value, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallLessThanOrEqualTo(Integer value) {
            addCriterion("overall <=", value, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallIn(List<Integer> values) {
            addCriterion("overall in", values, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallNotIn(List<Integer> values) {
            addCriterion("overall not in", values, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallBetween(Integer value1, Integer value2) {
            addCriterion("overall between", value1, value2, "overall");
            return (Criteria) this;
        }

        public Criteria andOverallNotBetween(Integer value1, Integer value2) {
            addCriterion("overall not between", value1, value2, "overall");
            return (Criteria) this;
        }

        public Criteria andNeckIsNull() {
            addCriterion("neck is null");
            return (Criteria) this;
        }

        public Criteria andNeckIsNotNull() {
            addCriterion("neck is not null");
            return (Criteria) this;
        }

        public Criteria andNeckEqualTo(Integer value) {
            addCriterion("neck =", value, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckNotEqualTo(Integer value) {
            addCriterion("neck <>", value, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckGreaterThan(Integer value) {
            addCriterion("neck >", value, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckGreaterThanOrEqualTo(Integer value) {
            addCriterion("neck >=", value, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckLessThan(Integer value) {
            addCriterion("neck <", value, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckLessThanOrEqualTo(Integer value) {
            addCriterion("neck <=", value, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckIn(List<Integer> values) {
            addCriterion("neck in", values, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckNotIn(List<Integer> values) {
            addCriterion("neck not in", values, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckBetween(Integer value1, Integer value2) {
            addCriterion("neck between", value1, value2, "neck");
            return (Criteria) this;
        }

        public Criteria andNeckNotBetween(Integer value1, Integer value2) {
            addCriterion("neck not between", value1, value2, "neck");
            return (Criteria) this;
        }

        public Criteria andForeBodyIsNull() {
            addCriterion("fore_body is null");
            return (Criteria) this;
        }

        public Criteria andForeBodyIsNotNull() {
            addCriterion("fore_body is not null");
            return (Criteria) this;
        }

        public Criteria andForeBodyEqualTo(Integer value) {
            addCriterion("fore_body =", value, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyNotEqualTo(Integer value) {
            addCriterion("fore_body <>", value, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyGreaterThan(Integer value) {
            addCriterion("fore_body >", value, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyGreaterThanOrEqualTo(Integer value) {
            addCriterion("fore_body >=", value, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyLessThan(Integer value) {
            addCriterion("fore_body <", value, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyLessThanOrEqualTo(Integer value) {
            addCriterion("fore_body <=", value, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyIn(List<Integer> values) {
            addCriterion("fore_body in", values, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyNotIn(List<Integer> values) {
            addCriterion("fore_body not in", values, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyBetween(Integer value1, Integer value2) {
            addCriterion("fore_body between", value1, value2, "foreBody");
            return (Criteria) this;
        }

        public Criteria andForeBodyNotBetween(Integer value1, Integer value2) {
            addCriterion("fore_body not between", value1, value2, "foreBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyIsNull() {
            addCriterion("middle_body is null");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyIsNotNull() {
            addCriterion("middle_body is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyEqualTo(Integer value) {
            addCriterion("middle_body =", value, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyNotEqualTo(Integer value) {
            addCriterion("middle_body <>", value, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyGreaterThan(Integer value) {
            addCriterion("middle_body >", value, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyGreaterThanOrEqualTo(Integer value) {
            addCriterion("middle_body >=", value, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyLessThan(Integer value) {
            addCriterion("middle_body <", value, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyLessThanOrEqualTo(Integer value) {
            addCriterion("middle_body <=", value, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyIn(List<Integer> values) {
            addCriterion("middle_body in", values, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyNotIn(List<Integer> values) {
            addCriterion("middle_body not in", values, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyBetween(Integer value1, Integer value2) {
            addCriterion("middle_body between", value1, value2, "middleBody");
            return (Criteria) this;
        }

        public Criteria andMiddleBodyNotBetween(Integer value1, Integer value2) {
            addCriterion("middle_body not between", value1, value2, "middleBody");
            return (Criteria) this;
        }

        public Criteria andHindquartersIsNull() {
            addCriterion("hindquarters is null");
            return (Criteria) this;
        }

        public Criteria andHindquartersIsNotNull() {
            addCriterion("hindquarters is not null");
            return (Criteria) this;
        }

        public Criteria andHindquartersEqualTo(Integer value) {
            addCriterion("hindquarters =", value, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersNotEqualTo(Integer value) {
            addCriterion("hindquarters <>", value, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersGreaterThan(Integer value) {
            addCriterion("hindquarters >", value, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersGreaterThanOrEqualTo(Integer value) {
            addCriterion("hindquarters >=", value, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersLessThan(Integer value) {
            addCriterion("hindquarters <", value, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersLessThanOrEqualTo(Integer value) {
            addCriterion("hindquarters <=", value, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersIn(List<Integer> values) {
            addCriterion("hindquarters in", values, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersNotIn(List<Integer> values) {
            addCriterion("hindquarters not in", values, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersBetween(Integer value1, Integer value2) {
            addCriterion("hindquarters between", value1, value2, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andHindquartersNotBetween(Integer value1, Integer value2) {
            addCriterion("hindquarters not between", value1, value2, "hindquarters");
            return (Criteria) this;
        }

        public Criteria andLimbsIsNull() {
            addCriterion("limbs is null");
            return (Criteria) this;
        }

        public Criteria andLimbsIsNotNull() {
            addCriterion("limbs is not null");
            return (Criteria) this;
        }

        public Criteria andLimbsEqualTo(Integer value) {
            addCriterion("limbs =", value, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsNotEqualTo(Integer value) {
            addCriterion("limbs <>", value, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsGreaterThan(Integer value) {
            addCriterion("limbs >", value, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsGreaterThanOrEqualTo(Integer value) {
            addCriterion("limbs >=", value, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsLessThan(Integer value) {
            addCriterion("limbs <", value, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsLessThanOrEqualTo(Integer value) {
            addCriterion("limbs <=", value, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsIn(List<Integer> values) {
            addCriterion("limbs in", values, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsNotIn(List<Integer> values) {
            addCriterion("limbs not in", values, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsBetween(Integer value1, Integer value2) {
            addCriterion("limbs between", value1, value2, "limbs");
            return (Criteria) this;
        }

        public Criteria andLimbsNotBetween(Integer value1, Integer value2) {
            addCriterion("limbs not between", value1, value2, "limbs");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andPedigreeIsNull() {
            addCriterion("pedigree is null");
            return (Criteria) this;
        }

        public Criteria andPedigreeIsNotNull() {
            addCriterion("pedigree is not null");
            return (Criteria) this;
        }

        public Criteria andPedigreeEqualTo(Integer value) {
            addCriterion("pedigree =", value, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeNotEqualTo(Integer value) {
            addCriterion("pedigree <>", value, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeGreaterThan(Integer value) {
            addCriterion("pedigree >", value, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pedigree >=", value, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeLessThan(Integer value) {
            addCriterion("pedigree <", value, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeLessThanOrEqualTo(Integer value) {
            addCriterion("pedigree <=", value, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeIn(List<Integer> values) {
            addCriterion("pedigree in", values, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeNotIn(List<Integer> values) {
            addCriterion("pedigree not in", values, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeBetween(Integer value1, Integer value2) {
            addCriterion("pedigree between", value1, value2, "pedigree");
            return (Criteria) this;
        }

        public Criteria andPedigreeNotBetween(Integer value1, Integer value2) {
            addCriterion("pedigree not between", value1, value2, "pedigree");
            return (Criteria) this;
        }

        public Criteria andBrucellosisIsNull() {
            addCriterion("brucellosis is null");
            return (Criteria) this;
        }

        public Criteria andBrucellosisIsNotNull() {
            addCriterion("brucellosis is not null");
            return (Criteria) this;
        }

        public Criteria andBrucellosisEqualTo(Integer value) {
            addCriterion("brucellosis =", value, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisNotEqualTo(Integer value) {
            addCriterion("brucellosis <>", value, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisGreaterThan(Integer value) {
            addCriterion("brucellosis >", value, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisGreaterThanOrEqualTo(Integer value) {
            addCriterion("brucellosis >=", value, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisLessThan(Integer value) {
            addCriterion("brucellosis <", value, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisLessThanOrEqualTo(Integer value) {
            addCriterion("brucellosis <=", value, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisIn(List<Integer> values) {
            addCriterion("brucellosis in", values, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisNotIn(List<Integer> values) {
            addCriterion("brucellosis not in", values, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisBetween(Integer value1, Integer value2) {
            addCriterion("brucellosis between", value1, value2, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andBrucellosisNotBetween(Integer value1, Integer value2) {
            addCriterion("brucellosis not between", value1, value2, "brucellosis");
            return (Criteria) this;
        }

        public Criteria andAntibodyIsNull() {
            addCriterion("antibody is null");
            return (Criteria) this;
        }

        public Criteria andAntibodyIsNotNull() {
            addCriterion("antibody is not null");
            return (Criteria) this;
        }

        public Criteria andAntibodyEqualTo(Integer value) {
            addCriterion("antibody =", value, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyNotEqualTo(Integer value) {
            addCriterion("antibody <>", value, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyGreaterThan(Integer value) {
            addCriterion("antibody >", value, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyGreaterThanOrEqualTo(Integer value) {
            addCriterion("antibody >=", value, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyLessThan(Integer value) {
            addCriterion("antibody <", value, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyLessThanOrEqualTo(Integer value) {
            addCriterion("antibody <=", value, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyIn(List<Integer> values) {
            addCriterion("antibody in", values, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyNotIn(List<Integer> values) {
            addCriterion("antibody not in", values, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyBetween(Integer value1, Integer value2) {
            addCriterion("antibody between", value1, value2, "antibody");
            return (Criteria) this;
        }

        public Criteria andAntibodyNotBetween(Integer value1, Integer value2) {
            addCriterion("antibody not between", value1, value2, "antibody");
            return (Criteria) this;
        }

        public Criteria andFootMouthIsNull() {
            addCriterion("foot_mouth is null");
            return (Criteria) this;
        }

        public Criteria andFootMouthIsNotNull() {
            addCriterion("foot_mouth is not null");
            return (Criteria) this;
        }

        public Criteria andFootMouthEqualTo(Integer value) {
            addCriterion("foot_mouth =", value, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthNotEqualTo(Integer value) {
            addCriterion("foot_mouth <>", value, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthGreaterThan(Integer value) {
            addCriterion("foot_mouth >", value, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthGreaterThanOrEqualTo(Integer value) {
            addCriterion("foot_mouth >=", value, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthLessThan(Integer value) {
            addCriterion("foot_mouth <", value, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthLessThanOrEqualTo(Integer value) {
            addCriterion("foot_mouth <=", value, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthIn(List<Integer> values) {
            addCriterion("foot_mouth in", values, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthNotIn(List<Integer> values) {
            addCriterion("foot_mouth not in", values, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthBetween(Integer value1, Integer value2) {
            addCriterion("foot_mouth between", value1, value2, "footMouth");
            return (Criteria) this;
        }

        public Criteria andFootMouthNotBetween(Integer value1, Integer value2) {
            addCriterion("foot_mouth not between", value1, value2, "footMouth");
            return (Criteria) this;
        }

        public Criteria andAntibioticIsNull() {
            addCriterion("antibiotic is null");
            return (Criteria) this;
        }

        public Criteria andAntibioticIsNotNull() {
            addCriterion("antibiotic is not null");
            return (Criteria) this;
        }

        public Criteria andAntibioticEqualTo(Integer value) {
            addCriterion("antibiotic =", value, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticNotEqualTo(Integer value) {
            addCriterion("antibiotic <>", value, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticGreaterThan(Integer value) {
            addCriterion("antibiotic >", value, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticGreaterThanOrEqualTo(Integer value) {
            addCriterion("antibiotic >=", value, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticLessThan(Integer value) {
            addCriterion("antibiotic <", value, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticLessThanOrEqualTo(Integer value) {
            addCriterion("antibiotic <=", value, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticIn(List<Integer> values) {
            addCriterion("antibiotic in", values, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticNotIn(List<Integer> values) {
            addCriterion("antibiotic not in", values, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticBetween(Integer value1, Integer value2) {
            addCriterion("antibiotic between", value1, value2, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andAntibioticNotBetween(Integer value1, Integer value2) {
            addCriterion("antibiotic not between", value1, value2, "antibiotic");
            return (Criteria) this;
        }

        public Criteria andHormoneIsNull() {
            addCriterion("hormone is null");
            return (Criteria) this;
        }

        public Criteria andHormoneIsNotNull() {
            addCriterion("hormone is not null");
            return (Criteria) this;
        }

        public Criteria andHormoneEqualTo(Integer value) {
            addCriterion("hormone =", value, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneNotEqualTo(Integer value) {
            addCriterion("hormone <>", value, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneGreaterThan(Integer value) {
            addCriterion("hormone >", value, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("hormone >=", value, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneLessThan(Integer value) {
            addCriterion("hormone <", value, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneLessThanOrEqualTo(Integer value) {
            addCriterion("hormone <=", value, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneIn(List<Integer> values) {
            addCriterion("hormone in", values, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneNotIn(List<Integer> values) {
            addCriterion("hormone not in", values, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneBetween(Integer value1, Integer value2) {
            addCriterion("hormone between", value1, value2, "hormone");
            return (Criteria) this;
        }

        public Criteria andHormoneNotBetween(Integer value1, Integer value2) {
            addCriterion("hormone not between", value1, value2, "hormone");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Integer value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Integer value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Integer value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Integer value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Integer value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Integer> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Integer> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Integer value1, Integer value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Integer value1, Integer value2) {
            addCriterion("result not between", value1, value2, "result");
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

        public Criteria andSexLikeInsensitive(String value) {
            addCriterion("upper(sex) like", value.toUpperCase(), "sex");
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