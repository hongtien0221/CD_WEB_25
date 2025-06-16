package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.CD_WEB_2025.entity.User;

import java.util.List;

@Repository
public interface UserReponesitory extends JpaRepository<User, String> {

    // Tìm người dùng theo idUser (cho đăng nhập)
    @Query("SELECT u FROM User u WHERE u.idUser = :idUser")
    User findByIdUser(String idUser);

    // Tìm người dùng theo idUser (cho sửa thông tin)
    @Query("SELECT u FROM User u WHERE u.idUser = :idUser")
    User findUserByIdUser(String idUser);

    // Lấy danh sách người dùng theo id
    @Query("SELECT u FROM User u WHERE u.idUser = :id")
    List<User> getUserByIdUser(String id);

    // Cập nhật phân quyền người dùng
    @Modifying
    @Query("UPDATE User u SET u.decentralization = :decentralization WHERE u.idUser = :userId")
    void updateUser(byte decentralization, String userId);

    // Tìm người dùng theo từ khóa (email hoặc tên)
    @Query("SELECT u FROM User u WHERE u.email LIKE %:keyword% OR u.nameUser LIKE %:keyword%")
    List<User> findUser(String keyword);

    // Kiểm tra người dùng có tồn tại qua email hoặc phone
    @Query("SELECT u FROM User u WHERE u.email = :email OR u.phone = :phone")
    List<User> checkUserExit(String email, String phone);

    // Lấy danh sách nhân viên (decentralization = 1)
    @Query("SELECT u FROM User u WHERE u.decentralization = 1")
    List<User> getListEmployee();

    // Lấy user mới đăng ký trong tháng hiện tại
    @Query("SELECT u FROM User u WHERE FUNCTION('MONTH', u.dateSignup) = :month")
    List<User> getNewbie(int month);

    // Tìm user theo idAccount từ bảng Social
    @Query("SELECT u FROM User u WHERE u.idUser IN (SELECT s.idUser FROM Social s WHERE s.idAccount = :idAccount)")
    User getUserByIdAccount(String idAccount);

    // Phân trang danh sách người dùng
    @Override
    Page<User> findAll(Pageable pageable);

    // Lấy ID người dùng cao nhất (giả sử định dạng: 'user123')
    @Query("SELECT MAX(CAST(SUBSTRING(u.idUser, 5) AS int)) FROM User u WHERE u.idUser LIKE 'user%'")
    int getMaxID();
}
