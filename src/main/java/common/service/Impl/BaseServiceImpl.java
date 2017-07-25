package common.service.Impl;

import common.dto.BaseDTO;
import common.service.IBaseService;
import common.utils.AfxBeanUtils;
import common.utils.OperatorType;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Administrator on 2017/2/27.
 * 基础接口实现
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID> {

    /**
     * 根据DTO，得到以DTO的OperateRelation为基础的属性Criterion列表(Criterion是查询条件)
     *
     * @param dto
     *            DTO对象
     * @return 返回属性Criterion列表
     */
    @Override
    public List<Criterion> getPropertyCriterionByDTO(BaseDTO dto) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (null != dto) {
            dto.generateDefOperateRelation();// 生成OperateRelation
            Map<String, OperatorType> operMap = dto.getOperateRelation();
            Field[] dtoFields = AfxBeanUtils.getAllFields(dto.getClass());
            for (int i = 0; i < dtoFields.length; i++) {
                String dtoFieldName = dtoFields[i].getName();
                Object dtoOrigValue = AfxBeanUtils.getFiledValue(dto, dtoFieldName);
                if (!dto.isExcludedProperty(dtoFieldName, dtoOrigValue)) {
                    OperatorType operType = operMap.get(dtoFieldName);
                    if (null != dtoOrigValue) {// 值不为空的生成正常值
                        operType = (null != operType ? operType : OperatorType.EQ);
                        switch (operType) {// 匹配对操作
                            case EQ:
                                criterionList.add(Restrictions.eq(dtoFieldName, dtoOrigValue));
                                break;
                            case GT:
                                criterionList.add(Restrictions.gt(dtoFieldName, dtoOrigValue));
                                break;
                            case LIKE:
                            case FULLLIKE:
                            case STARTLIKE:
                            case ENDLIKE:
                            case NOTLIKE:
                            case NOTFULLLIKE:
                            case NOTSTARTLIKE:
                            case NOTENDLIKE:
                                Criterion likeCriterion = null;
                                switch (operType) {
                                    case LIKE:
                                    case NOTLIKE:
                                        // 字符串在中间匹配.相当于"like '%value%'"
                                        likeCriterion = Restrictions.like(dtoFieldName, dtoOrigValue.toString(), MatchMode.ANYWHERE);
                                        break;
                                    case FULLLIKE:
                                    case NOTFULLLIKE:
                                        // 字符串精确匹配.相当于"like 'value'"
                                        likeCriterion = Restrictions.like(dtoFieldName, dtoOrigValue.toString(), MatchMode.EXACT);
                                        break;
                                    case STARTLIKE:
                                    case NOTSTARTLIKE:
                                        // 字符串在最前面的位置.相当于"like 'value%'"
                                        likeCriterion = Restrictions.like(dtoFieldName, dtoOrigValue.toString(), MatchMode.START);
                                        break;
                                    case ENDLIKE:
                                    case NOTENDLIKE:
                                        // 字符串在最后面的位置.相当于"like '%value'"
                                        likeCriterion = Restrictions.like(dtoFieldName, dtoOrigValue.toString(), MatchMode.END);
                                        break;
                                    default:// 其他操作不处理
                                        break;
                                }
                                if (null != likeCriterion) {// 必须是有效的匹配
                                    if (OperatorType.NOTLIKE.equals(operType) || OperatorType.NOTFULLLIKE.equals(operType) || OperatorType.NOTSTARTLIKE.equals(operType) || OperatorType.NOTENDLIKE.equals(operType)) {// 如果是有not的，加入not表达式
                                        likeCriterion = Restrictions.not(likeCriterion);
                                    }
                                    criterionList.add(likeCriterion);
                                }
                                break;
                            case ILIKE:
                            case IFULLLIKE:
                            case ISTARTLIKE:
                            case IENDLIKE:
                            case INOTLIKE:
                            case INOTFULLLIKE:
                            case INOTSTARTLIKE:
                            case INOTENDLIKE:
                                Criterion iLikeCriterion = null;
                                switch (operType) {
                                    case ILIKE:
                                    case INOTLIKE:
                                        // 字符串在中间匹配.相当于"like '%value%' (忽略大小写)"
                                        iLikeCriterion = Restrictions.ilike(dtoFieldName, dtoOrigValue.toString(), MatchMode.ANYWHERE);
                                        break;
                                    case IFULLLIKE:
                                    case INOTFULLLIKE:
                                        // 字符串精确匹配.相当于"like 'value' (忽略大小写)"
                                        iLikeCriterion = Restrictions.ilike(dtoFieldName, dtoOrigValue.toString(), MatchMode.EXACT);
                                        break;
                                    case ISTARTLIKE:
                                    case INOTSTARTLIKE:
                                        // 字符串在最前面的位置.相当于"like 'value%' (忽略大小写)"
                                        iLikeCriterion = Restrictions.ilike(dtoFieldName, dtoOrigValue.toString(), MatchMode.START);// 字符串在最前面的位置.相当于"like 'value%'"
                                        break;
                                    case IENDLIKE:
                                    case INOTENDLIKE:
                                        // 字符串在最后面的位置.相当于"like '%value' (忽略大小写)"
                                        iLikeCriterion = Restrictions.ilike(dtoFieldName, dtoOrigValue.toString(), MatchMode.END);
                                        break;
                                    default:// 其他操作不处理
                                        break;
                                }
                                if (null != iLikeCriterion) {// 必须是有效的匹配
                                    if (OperatorType.INOTLIKE.equals(operType) || OperatorType.INOTFULLLIKE.equals(operType) || OperatorType.INOTSTARTLIKE.equals(operType) || OperatorType.INOTENDLIKE.equals(operType)) {// 如果是有not的，加入not表达式
                                        iLikeCriterion = Restrictions.not(iLikeCriterion);
                                    }
                                    criterionList.add(iLikeCriterion);
                                }
                                break;
                            case LT:
                                criterionList.add(Restrictions.lt(dtoFieldName, dtoOrigValue));
                                break;
                            case IN:
                            case NOTIN:
                                Criterion inCriterion = null;
                                if (dtoOrigValue instanceof Collection) {
                                    inCriterion = Restrictions.in(dtoFieldName, (Collection<?>) dtoOrigValue);
                                } else if (dtoOrigValue.getClass().isArray()) {
                                    inCriterion = Restrictions.in(dtoFieldName, (Object[]) AfxBeanUtils.getWrapperObject(dtoOrigValue));
                                }
                                if (null != inCriterion) {// 必须是有效的匹配
                                    if (OperatorType.NOTIN.equals(operType)) {
                                        inCriterion = Restrictions.not(inCriterion);
                                    }
                                    criterionList.add(inCriterion);
                                }
                                break;
                            case NE:
                                criterionList.add(Restrictions.ne(dtoFieldName, dtoOrigValue));
                                break;
                            case GE:
                                criterionList.add(Restrictions.ge(dtoFieldName, dtoOrigValue));
                                break;
                            case LE:
                                criterionList.add(Restrictions.le(dtoFieldName, dtoOrigValue));
                                break;
                            case ISNOTNULL:
                                criterionList.add(Restrictions.isNotNull(dtoFieldName));
                                break;
                            case ISEMPTY:
                                criterionList.add(Restrictions.isEmpty(dtoFieldName));
                                break;
                            case ISNOTEMPTY:
                                criterionList.add(Restrictions.isNotEmpty(dtoFieldName));
                                break;
                            case BETWEEN:
                            case NOTBETWEEN:
                                Criterion betweenCriterion = null;
                                if (dtoOrigValue instanceof Collection) {
                                    Collection<?> dtoCollValue = (Collection<?>) dtoOrigValue;
                                    if (2 <= dtoCollValue.size()) {
                                        Iterator<?> dtoIt = dtoCollValue.iterator();
                                        Object firstValue = dtoIt.next();
                                        Object secondValue = dtoIt.next();
                                        betweenCriterion = Restrictions.between(dtoFieldName, firstValue, secondValue);
                                    }
                                } else if (dtoOrigValue.getClass().isArray()) {
                                    Object[] dtoArrValue = (Object[]) AfxBeanUtils.getWrapperObject(dtoOrigValue);
                                    if (2 <= dtoArrValue.length) {
                                        betweenCriterion = Restrictions.between(dtoFieldName, dtoArrValue[0], dtoArrValue[1]);
                                    }
                                }
                                if (null != betweenCriterion) {// 必须是有效的匹配
                                    if (OperatorType.NOTBETWEEN.equals(operType)) {
                                        betweenCriterion = Restrictions.not(betweenCriterion);
                                    }
                                    criterionList.add(betweenCriterion);
                                }
                                break;
                            default:
                                criterionList.add(Restrictions.eq(dtoFieldName, dtoOrigValue));
                                break;
                        }
                    } else {// 可能需要生成isNull这些
                        if (null != operType) {// 如果有指定操作类型，可能要生成值；如果为空的不生成任何查询
                            if (OperatorType.ISNULL.equals(operType)) {
                                criterionList.add(Restrictions.isNull(dtoFieldName));
                            }
                            // 其他操作类型什么也不干，这里只处理isNull
                        }
                    }
                }
            }
        }
        return criterionList;
    }
}
