package com.form.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{



	/*	//全件取得
	Page<User> findAll(Pageable pageable);

	//エンティティeを削除する　void remove(object e)
	void remove(Object user);

	//エンティティeを新規登録する　void persist(Object e)
	void persist(Object e);

	//key（主キー）で検索して見つけたエンティティを返す
	//E find(E.class, Object key)
	User find(User.class, Object key);
*/
}
