package common.service;

import common.dto.BaseDTO;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 * 基础接口
 */
public interface IBaseService<T, ID extends Serializable> {

    /**
     * 根据DTO，得到以DTO的OperateRelation为基础的属性Criterion列表(Criterion是查询条件)
     *
     * @param dto
     *            DTO对象
     * @return 返回属性Criterion列表
     */
    public List<Criterion> getPropertyCriterionByDTO(BaseDTO dto);
}