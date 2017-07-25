package common.dto;

import common.utils.AfxBeanUtils;
import common.utils.OperatorType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/27.
 * DTO抽象类
 */
public abstract class BaseDTO {

    // 字段之间And Or 关系定义
    // private Map<String, Boolean> andOrRelation = new HashMap<String, Boolean>();
    // 操作关系定义
    private Map<String, OperatorType> operateRelation = new HashMap<String, OperatorType>();

    /** 复制值时候排除的字段的名称 */
    // private List<String> excludeCopyValueFieldNames = this.getDefExcludeCopyValueFieldNames();
    // private List<String> excludeCopyValueFieldNames = new ArrayList<String>();
    // public Map<String, Boolean> getAndOrRelation() {
    // return andOrRelation;
    // }
    //
    // public void setAndOrRelation(Map<String, Boolean> andOrRelation) {
    // this.andOrRelation = andOrRelation;
    // }
    public Map<String, OperatorType> getOperateRelation() {
        return operateRelation;
    }

    public void setOperateRelation(Map<String, OperatorType> operateRelation) {
        this.operateRelation = operateRelation;
    }

    public boolean isExcludedProperty(String propertyName, Object propertyValue) {
        return (propertyName.endsWith("Id") || propertyName.equals("operateRelation"));
    }

    // public final List<String> getExcludeCopyValueFieldNames() {
    // return excludeCopyValueFieldNames;
    // }
    //
    // public final void setExcludeCopyValueFieldNames(List<String> excludeCopyValueFieldNames) {
    // this.excludeCopyValueFieldNames = excludeCopyValueFieldNames;
    // }
    //
    // /** 获取默认的排除字段的名称 */
    // public List<String> getDefExcludeCopyValueFieldNames() {
    // return new ArrayList<String>(Arrays.asList(new String[] {"status"}));
    // }
    public void generateDefOperateRelation() {
        this.operateRelation = new HashMap<String, OperatorType>();
    }

    /**
     * 根据字段名，将当前对象的字段值复制到obj对象对应的字段,在excludeCopyValueFieldNames指定了的字段将不进行复制
     *
     * @param obj
     *            指定对象
     */
    public void copyValuesTo(Object obj) {
        if (null != obj) {
            Map<String, Object> allFieldsValue = AfxBeanUtils.getAllFieldsValue(this, true);
            // if (CollectionUtils.isNotEmpty(this.excludeCopyValueFieldNames)) {
            // for (int i = 0, len = this.excludeCopyValueFieldNames.size(); i < len; i++) {
            // allFieldsValue.remove(this.excludeCopyValueFieldNames.get(i));
            // }
            // }
            Iterator<String> keyIt = allFieldsValue.keySet().iterator();
            while (keyIt.hasNext()) {
                String name = keyIt.next();
                if (AfxBeanUtils.hasField(obj.getClass(), name)) {
                    Object val = allFieldsValue.get(name);
                    AfxBeanUtils.setFieldValue(obj, name, val);
                }
            }
        }
    }
}
