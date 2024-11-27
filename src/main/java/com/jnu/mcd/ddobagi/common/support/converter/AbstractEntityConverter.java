package com.jnu.mcd.ddobagi.common.support.converter;


import com.jnu.mcd.ddobagi.common.persistence.BaseEntity;
import com.jnu.mcd.ddobagi.common.support.AbstractModel;

public interface AbstractEntityConverter<T extends BaseEntity, R extends AbstractModel> {
	R from(T t);

	T toEntity(R t);
}
