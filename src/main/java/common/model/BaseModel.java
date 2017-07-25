package common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 2676523852238939862L;

    private Integer id;

    private List list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public int hashCode() {
        return null == this.getId() ? super.hashCode() : this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!obj.getClass().equals(this.getClass()) && !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        if (this == obj) {
            return true;
        } else {
            BaseModel model = (BaseModel) obj;
            Integer id1 = this.getId();
            Integer id2 = model.getId();
            if (null == id1 && null == id2) {
                return super.equals(obj);
            } else if (null == id1 || null == id2) {
                return false;
            } else {
                return this.getId().equals(model.getId());
            }
        }
    }
}
