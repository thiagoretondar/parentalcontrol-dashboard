package fei.tcc.repository;

import fei.tcc.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thiagoretondar on 27/11/16.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAllByParentId(Integer parentId);

}
