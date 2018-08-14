package com.tao.pojo;

import java.util.ArrayList;
import java.util.List;

public class Note2tagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Note2tagExample() {
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

        public Criteria andNoteguidIsNull() {
            addCriterion("noteGuid is null");
            return (Criteria) this;
        }

        public Criteria andNoteguidIsNotNull() {
            addCriterion("noteGuid is not null");
            return (Criteria) this;
        }

        public Criteria andNoteguidEqualTo(String value) {
            addCriterion("noteGuid =", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidNotEqualTo(String value) {
            addCriterion("noteGuid <>", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidGreaterThan(String value) {
            addCriterion("noteGuid >", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidGreaterThanOrEqualTo(String value) {
            addCriterion("noteGuid >=", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidLessThan(String value) {
            addCriterion("noteGuid <", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidLessThanOrEqualTo(String value) {
            addCriterion("noteGuid <=", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidLike(String value) {
            addCriterion("noteGuid like", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidNotLike(String value) {
            addCriterion("noteGuid not like", value, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidIn(List<String> values) {
            addCriterion("noteGuid in", values, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidNotIn(List<String> values) {
            addCriterion("noteGuid not in", values, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidBetween(String value1, String value2) {
            addCriterion("noteGuid between", value1, value2, "noteguid");
            return (Criteria) this;
        }

        public Criteria andNoteguidNotBetween(String value1, String value2) {
            addCriterion("noteGuid not between", value1, value2, "noteguid");
            return (Criteria) this;
        }

        public Criteria andTagguidIsNull() {
            addCriterion("tagGuid is null");
            return (Criteria) this;
        }

        public Criteria andTagguidIsNotNull() {
            addCriterion("tagGuid is not null");
            return (Criteria) this;
        }

        public Criteria andTagguidEqualTo(String value) {
            addCriterion("tagGuid =", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidNotEqualTo(String value) {
            addCriterion("tagGuid <>", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidGreaterThan(String value) {
            addCriterion("tagGuid >", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidGreaterThanOrEqualTo(String value) {
            addCriterion("tagGuid >=", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidLessThan(String value) {
            addCriterion("tagGuid <", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidLessThanOrEqualTo(String value) {
            addCriterion("tagGuid <=", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidLike(String value) {
            addCriterion("tagGuid like", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidNotLike(String value) {
            addCriterion("tagGuid not like", value, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidIn(List<String> values) {
            addCriterion("tagGuid in", values, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidNotIn(List<String> values) {
            addCriterion("tagGuid not in", values, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidBetween(String value1, String value2) {
            addCriterion("tagGuid between", value1, value2, "tagguid");
            return (Criteria) this;
        }

        public Criteria andTagguidNotBetween(String value1, String value2) {
            addCriterion("tagGuid not between", value1, value2, "tagguid");
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