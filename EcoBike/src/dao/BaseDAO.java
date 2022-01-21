package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * má»—i Ä‘á»‘i tÆ°á»£ng Ä‘á»�u cÃ³ hÃ m chung cÆ¡ báº£n sau:
 * findALl():  láº¥y táº¥t  cáº£ cÃ¡c báº£n ghi tá»« 1 báº£ng trong db
 * findById(): láº¥y ra má»™t báº£n ghi cÃ³ id tÆ°Æ¡ng á»©ng
 * insert(): thÃªm má»™t báº£n ghi
 * update(): thay Ä‘á»•i dá»¯ liá»‡u cá»§a má»™t báº£n ghi
 * delete(): xÃ³a má»™t báº£n ghi
 * getObject(), getList(): chuyá»ƒn resultSet vá»� 1 Ä‘á»‘i tÆ°á»£ng  hoáº·c 1 list Ä‘á»‘i tÆ°á»£ng
 */
	
public interface BaseDAO<T> {
	
    List<T> findAll() throws SQLException;

    T findById(int id) throws SQLException;

    T insert(T t) throws SQLException;

    boolean update(T t) throws SQLException;

}
