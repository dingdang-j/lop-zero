package org.lop.clover.web.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.util.WebUtils;

import org.lop.modules.repository.hibernate.BaseEntity;
import org.lop.modules.repository.hibernate.SimpleHibernateDao;
import org.lop.modules.repository.mapper.QueryFilter;
import org.lop.modules.repository.mapper.QueryFilterHandler;
import org.lop.modules.utils.InstantiateUtils;
import org.lop.modules.utils.ReflectionUtils;
import org.lop.modules.web.Servlets;
import org.lop.modules.web.struts2.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Struts2 Action基类.
 * 
 * @author 潘瑞峥
 * @date 2015年1月9日
 */
public abstract class CrudActionSupport<T extends BaseEntity, PK extends Serializable> extends ActionSupport implements ModelDriven<T>,
		Preparable {

	private static final long serialVersionUID = 1L;

	protected final Logger logger = LoggerFactory.getLogger( this.getClass() );

	/** 进行增删改操作后, 以redirect方式重新打开action默认页的result名. */
	public static final String RELOAD = "reload";

	/** entity. */
	protected T entity;

	/** id. */
	protected PK id;

	/** page请求 - 起始页. */
	protected int page = 0;

	/** page请求 - 每页记录数. */
	protected int size = 10;

	/** page响应. */
	protected Page<T> pageable;

	/** 成功消息. */
	protected String messageSuccess;

	/** 失败消息. */
	protected String messageError;

	protected SimpleHibernateDao<T, PK> dao;

	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute() throws Exception {

		return list();
	}

	/**
	 * 实现空的prepare()函数, 屏蔽所有Action函数公共的二次绑定.
	 */
	@Override
	public void prepare() throws Exception {
	}

	/**
	 * 在input()前执行二次绑定.
	 */
	public void prepareInput() throws Exception {

		prepareModel();
	}

	/**
	 * 在save()前执行二次绑定.
	 */
	public void prepareSave() throws Exception {

		prepareModel();
	}

	/**
	 * 等同于prepare()的内部函数, 供prepardMethodName()函数调用.
	 */
	protected final void prepareModel() throws Exception {

		logger.debug( "prepareModel" );

		if ( null != id ) {

			entity = ( T ) this.dao.get( id );
		} else {

			Class<T> model = ReflectionUtils.getSuperClassGenricType( this.getClass() );
			entity = InstantiateUtils.instantiate( model );
		}
	}

	/**
	 * Json列表.
	 * 
	 * 建议return null.
	 */
	public String json() throws Exception {

		logger.debug( "json" );

		List<QueryFilter> filters = QueryFilterHandler.createFilter( ServletActionContext.getRequest() );

		Page<T> pageable = this.dao.findPage( new PageRequest( page, size ), filters );
		Struts2Utils.renderJson( pageable );

		return null;
	}

	/**
	 * List列表.
	 * 
	 * 建议return SUCCESS.
	 */
	public String list() throws Exception {

		logger.debug( "list" );

		Map<String, Object> queryParams = WebUtils.getParametersStartingWith( ServletActionContext.getRequest(),
				QueryFilterHandler.PARAMS_PREFIX );

		// 将查询参数补全, 用于分页.
		ServletActionContext.getRequest().setAttribute( "queryParams",
				Servlets.encodeParameterStringWithPrefix( queryParams, QueryFilterHandler.PARAMS_PREFIX ) );

		List<QueryFilter> filters = QueryFilterHandler.createFilter( queryParams );

		pageable = this.dao.findPage( new PageRequest( page, size ), filters );

		return SUCCESS;
	}

	/**
	 * 新增或修改界面.
	 * 
	 * 建议return INPUT.
	 */
	@Override
	public String input() throws Exception {

		logger.debug( "input" );

		return INPUT;
	}

	/**
	 * 新增或修改.
	 * 
	 * 建议return RELOAD.
	 */
	public String save() throws Exception {

		logger.debug( "save" );

		try {

			this.dao.save( entity );

			messageSuccess = "操作成功";

		} catch ( Exception e ) {

			messageError = "操作失败: " + e.getMessage();
		}

		return RELOAD;
	}

	@Override
	public T getModel() {
		return entity;
	}

	public void setId( PK id ) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage( int page ) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize( int size ) {
		this.size = size;
	}

	public Page<T> getPageable() {
		return pageable;
	}

	public void setPageable( Page<T> pageable ) {
		this.pageable = pageable;
	}

	public String getMessageSuccess() {
		return messageSuccess;
	}

	public void setMessageSuccess( String messageSuccess ) {
		this.messageSuccess = messageSuccess;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError( String messageError ) {
		this.messageError = messageError;
	}

}