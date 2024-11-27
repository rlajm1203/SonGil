package com.jnu.mcd.ddobagi.common.support.converter;


import com.jnu.mcd.ddobagi.common.support.AbstractModel;
import com.jnu.mcd.ddobagi.common.support.dto.AbstractDto;

public interface AbstractDtoConverter<T extends AbstractDto, R extends AbstractModel> {
	R from(T t);
}
