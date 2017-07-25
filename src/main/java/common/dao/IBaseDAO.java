package common.dao;

import common.dao.callback.ParameterSetterCallback;
import common.utils.PageResult;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/27.
 * Hibernate基础工具接口
 */
public interface IBaseDAO<T, ID extends Serializable> {

//    public void index();

//    public void searchTest(String key);

    /**
     * 根据id获取实体
     *
     * @param id
     *            唯一标识
     * @return 持久化对象
     */
    public T get(ID id);

    /**
     * 保存实体
     *
     * @param entity
     *            实体对象
     * @return 唯一标识
     */
    public ID save(T entity);

    /**
     * 更新实体
     *
     * @param entity
     *            实体对象
     */
    public void update(T entity);

    /**
     * 根据实体执行保存或者更新
     *
     * @param entity
     *            实体
     */
    public void saveOrUpdate(T entity);

    /**
     * 根据实体执行保存或者更新(带有合并功能，能够合并session中的持久化实体和游离态的实体)
     *
     * @param entity
     *            实体
     * @return 返回合并后的实体(持久态)
     */
    public T merge(T entity);

    /**
     * 根据提供的HQL来更新数据
     *
     * @param hql
     *            自定义的HQL，(注意：写实体后面的语法即可，因为内部已经补上"UPDATE "防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回受影响的行数
     */
    public int updateByHQL(String hql, ParameterSetterCallback callback);

    /**
     * 根据提供的SQL来更新数据
     *
     * @param sql
     *            自定义的SQL，(注意：写实体后面的语法即可，因为内部已经补上"UPDATE "防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回受影响的行数
     */
    public int updateBySQL(String sql, ParameterSetterCallback callback);

    /**
     * 查询所有的记录
     *
     * @return 返回查询到的实体
     */
    public List<T> listAll();

    /**
     * 查询所有的记录,并排序
     *
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     *
     * @return 返回查询到的实体
     */
    public List<T> listAll(Order[] orders);

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @return 返回查询到的实体
     */
    public List<T> listByProperty(String property, Object value);

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @return 返回查询到的实体
     */
    public List<T> listByProperty(String property, Object value, Order[] orders);

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    public List<T> listByProperty(String property, Object value, Order[] orders, String[] groupPropertyNames);

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByProperty(String property, Object value, int pageNumber, int pageSize);

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByProperty(String property, Object value, Order[] orders, int pageNumber, int pageSize);

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByProperty(String property, Object value, Order[] orders, String[] groupPropertyNames, int pageNumber, int pageSize);

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @return 返回查询到的实体
     */
    public List<T> listByPropertySet(Map<String, Object> criterionMap);

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @return 返回查询到的实体
     */
    public List<T> listByPropertySet(Map<String, Object> criterionMap, Order[] orders);

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    public List<T> listByPropertySet(Map<String, Object> criterionMap, Order[] orders, String[] groupPropertyNames);

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByPropertySet(Map<String, Object> criterionMap, int pageNumber, int pageSize);

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByPropertySet(Map<String, Object> criterionMap, Order[] orders, int pageNumber, int pageSize);

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByPropertySet(Map<String, Object> criterionMap, Order[] orders, String[] groupPropertyNames, int pageNumber, int pageSize);

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @return 返回查询到的实体
     */
    public List<T> listByCriteria(Criterion[] criterion);

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @return 返回查询到的实体
     */
    public List<T> listByCriteria(Criterion[] criterion, String alias);

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @return 返回查询到的实体
     */
    public List<T> listByCriteria(Criterion[] criterion, Order[] orders);

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    public List<T> listByCriteria(Criterion[] criterion, Order[] orders, String[] groupPropertyNames);

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param alias
     *            当前类的别名
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    public List<T> listByCriteria(Criterion[] criterion, String alias, Order[] orders, String[] groupPropertyNames);

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion);

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param alias
     *            当前实体的别名
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, String alias);

    /**
     * 按Criterion查询列表数据.
     *
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, Order[] orders);

    /**
     * 按Criterion查询列表数据.
     *
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, Order[] orders, String[] groupPropertyNames);

    /**
     * 按Criterion查询列表数据.
     *
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @param criterion
     *            数量可变的Criterion.
     * @param alias
     *            当前实体别名
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, String alias, Order[] orders, String[] groupPropertyNames);

    /**
     * 根据HQL查询记录
     *
     * @param hql
     *            自定义的HQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回查询到的实体
     */
    public List<T> listByHQL(String hql, ParameterSetterCallback callback);

    /**
     * 根据HQL查询记录
     *
     * @param hql
     *            自定义的HQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    public PageResult listByHQL(String hql, ParameterSetterCallback callback, int pageNumber, int pageSize);

    /**
     * 根据SQL查询记录
     *
     * @param sql
     *            自定义的SQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理SQL中对应参数的设置
     * @return 返回查询到的记录
     */
    public List<?> listBySQL(String sql, ParameterSetterCallback callback);

    /**
     * 根据SQL查询记录
     *
     * @param sql
     *            自定义的SQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理SQL中对应参数的设置
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的记录的分页对象
     */
    public PageResult listBySQL(String sql, ParameterSetterCallback callback, int pageNumber, int pageSize);

    /**
     * 按属性获取唯一对象
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @return 返回唯一的实体
     */
    public T getUniqueByProperty(String property, Object value);

    /**
     * 按属性获取唯一对象
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回唯一的实体
     */
    public T getUniqueByProperty(String property, Object value, String[] groupPropertyNames);

    /**
     * 创建一个DetachedCriteria查询对象，默认要查询的类型是当前支持实体
     *
     * @return DetachedCriteria查询对象
     */
    public DetachedCriteria createDetachedCriteria();

    /**
     * 创建一个DetachedCriteria查询对象
     *
     * @param entityClz
     *            要针对创建的实体类类对象
     * @return DetachedCriteria查询对象
     */
    public DetachedCriteria createDetachedCriteria(Class<?> entityClz);

    /**
     * 创建一个DetachedCriteria查询对象
     *
     * @param entityClz
     *            要针对创建的实体类类对象
     * @param alias
     *            实体的别名
     * @return DetachedCriteria查询对象
     */
    public DetachedCriteria createDetachedCriteria(Class<?> entityClz, String alias);

    /**
     * 创建一个DetachedCriteria查询对象
     *
     * @param entityClz
     *            要针对创建的实体类类对象
     * @param criterions
     *            多个条件
     * @param alias
     *            实体的别名
     * @return DetachedCriteria查询对象
     */
    public DetachedCriteria createDetachedCriteria(Class<?> entityClz, Criterion[] criterions, String alias);

    /**
     * 按criterions统计记录数
     *
     * @param criterions
     *            指定的criterions数组
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 记录数
     */
    public int countByCriteria(Criterion[] criterions, String[] groupPropertyNames);

    /**
     * 按criterions统计记录数
     *
     * @param criterions
     *            指定的criterions数组
     * @return 记录数
     */
    public int countByCriteria(Criterion[] criterions);

    /**
     * 按指定的HQL语句统计记录数
     *
     * @param hql
     *            hql查询语句
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 记录数
     */
    public int countByHQLQuery(String hql, ParameterSetterCallback callback);

    /**
     * 按指定的SQL语句统计记录数
     *
     * @param sql
     *            sql查询语句
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 记录数
     */
    public int countBySQLQuery(String sql, ParameterSetterCallback callback);

    /**
     * 根据id删除记录
     *
     * @param id
     *            唯一标识
     * @return 返回受影响的行数
     */
    public Integer deleteById(ID id);

    /**
     * 根据多个id删除记录
     *
     * @param ids
     *            多个唯一标识
     * @return 返回受影响的行数
     */
    public Integer deleteByIds(ID[] ids);

    /**
     * 根据实体删除记录
     *
     * @param entity
     *            实体
     */
    public void deleteByEntity(T entity);

    /**
     * 根据多个实体删除记录
     *
     * @param entitys
     *            多个实体
     */
    public void deleteByEntitys(T[] entitys);

    /**
     * 根据属性值删除记录
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @return 返回受影响的行数
     */
    public int deleteByProperty(String property, Object value);

    /**
     * 根据属性集合删除记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @return 返回受影响的行数
     */
    public int deleteByPropertySet(Map<String, Object> criterionMap);

    /**
     * 根据过滤条件删除记录
     *
     * @param criterions
     *            过滤条件数组
     * @return 返回受影响的行数
     */
    public Integer deleteByCriteria(Criterion[] criterions);

    /**
     * 根据过滤条件删除记录
     *
     * @param criterions
     *            过滤条件数组
     * @param alias
     *            查询中包括的主别名
     * @return 返回受影响的行数
     */
    public Integer deleteByCriteria(Criterion[] criterions, String alias);

    /**
     * 根据提供的HQL来删除数据
     *
     * @param hql
     *            自定义的HQL，(注意：写实体后面的语法即可，因为内部已经补上"DELETE FROM"防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回受影响的行数
     */
    public int deleteByHQL(String hql, ParameterSetterCallback callback);

    /**
     * 根据提供的SQL来删除数据
     *
     * @param sql
     *            自定义的SQL，(注意：写实体后面的语法即可，因为内部已经补上"DELETE FROM"防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理SQL中对应参数的设置
     * @return 返回受影响的行数
     */
    public int deleteBySQL(String sql, ParameterSetterCallback callback);

}
