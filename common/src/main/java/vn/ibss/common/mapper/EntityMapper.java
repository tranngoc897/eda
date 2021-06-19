package vn.ibss.common.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityMapper<E, D, I extends Serializable> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected abstract Class<E> getEntityClass();

    public abstract D convertToDTO(E entity);

    public E mapToEntity(D dto) {
        E entity;
        try {
            entity = getEntityClass().newInstance();
        } catch (Exception e) {
            return null;
        }
        return convertToEntity(entity, dto);
    }

    public abstract E convertToEntity(E entity, D dto) ;

    public List<D> convertToDTOList(List<E> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
