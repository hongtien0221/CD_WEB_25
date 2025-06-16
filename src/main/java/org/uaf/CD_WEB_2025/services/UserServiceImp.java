package org.uaf.CD_WEB_2025.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.uaf.CD_WEB_2025.components.ImportFormExcel;
import org.uaf.CD_WEB_2025.entity.Social;
import org.uaf.CD_WEB_2025.entity.User;
import org.uaf.CD_WEB_2025.reponsitory.SocialReponesitory;
import org.uaf.CD_WEB_2025.reponsitory.UserReponesitory;
import org.uaf.CD_WEB_2025.services.IServices.IUserService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    private final UserReponesitory userRepository;
    private final SocialReponesitory socialRepository;

    @Autowired
    public UserServiceImp(UserReponesitory userRepository, SocialReponesitory socialRepository) {
        this.userRepository = userRepository;
        this.socialRepository = socialRepository;
    }

    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public long getCountUser() {
        return userRepository.count();
    }

    @Override
    public List<User> getUserById(String id) {
        return userRepository.getUserByIdUser(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public int getMaxId() {
        return userRepository.getMaxID();
    }

    @Override
    public void createUser(String name, String phone, String email, String passw) {
        String importDate = LocalDateTime.now().toLocalDate().toString();
        User u = new User();
        u.setIdUser("user" + (getMaxId() + 1));
        u.setNameUser(name);
        u.setPhone(phone);
        u.setEmail(email);
        u.setPassw(passw);
        u.setDecentralization(0);
        u.setDateSignup(Date.valueOf(importDate));
        userRepository.save(u);
    }

    @Override
    @Transactional
    public void updateUser(byte decentralization, String userId) {
        userRepository.updateUser(decentralization, userId);
    }

    @Override
    public List<User> searchUser(String keyword) {
        return keyword.isEmpty() ? userRepository.findAll() : userRepository.findUser(keyword);
    }

    @Override
    public User checkLogin(String username) {
        return userRepository.findByIdUser(username);
    }

    @Override
    public boolean checkUserExit(String email, String phone) {
        List<User> list = userRepository.checkUserExit(email, phone);
        return list.stream().anyMatch(u ->
                email.equals(u.getEmail()) || phone.equals(u.getPhone()));
    }

    @Override
    public void updateAccount(String iduser, String address, String passw, String name, String phone,
                              String email, String birthday, Date datesignup, boolean sex) {
        User u = new User();
        u.setIdUser(iduser);
        u.setAddress(address);
        u.setPassw(passw);
        u.setNameUser(name);
        u.setPhone(phone);
        u.setEmail(email);
        u.setBirthday(Date.valueOf(birthday));
        u.setSex(sex);
        u.setDateSignup(datesignup);
        u.setDecentralization(0);
        userRepository.save(u);
    }

    public String getEncryptPassUser(String idUser) {
        return userRepository.findById(idUser)
                .map(User::getPassw)
                .orElse(null);
    }

    public Date getDateSignup(String idUser) {
        return userRepository.findById(idUser)
                .map(User::getDateSignup)
                .orElse(null);
    }

    @Override
    public List<User> getNewbie() {
        return userRepository.getNewbie(LocalDateTime.now().getMonthValue());
    }

    @Override
    public User getUserByIdUser(String idUser) {
        return userRepository.findUserByIdUser(idUser);
    }

    @Override
    public boolean checkIdAccount(String id) {
        return socialRepository.findAll().stream()
                .anyMatch(s -> id.equals(s.getIdAccount()));
    }

    @Override
    public void addUserFB(String name, String idAccount, String email) {
        String importDate = LocalDateTime.now().toLocalDate().toString();
        String generatedId = "user" + (getCountUser() + 1);

        User u = new User();
        u.setIdUser(generatedId);
        u.setNameUser(name);
        u.setEmail(email);
        u.setPhone(null);
        u.setPassw(null);
        u.setDecentralization(0);
        u.setDateSignup(Date.valueOf(importDate));

        Social s = new Social();
        s.setIdUser(generatedId);
        s.setIdAccount(idAccount);
        s.setStatus(1);

        socialRepository.save(s);
        userRepository.save(u);
    }

    @Override
    public User getUserByIdAccount(String idAccount) {
        return userRepository.getUserByIdAccount(idAccount);
    }

    @Override
    public List<User> getListEmployee() {
        return userRepository.getListEmployee();
    }

    @Override
    public void saveFromExcel(MultipartFile file) {
        try {
            List<User> users = ImportFormExcel.excelToUser(file.getInputStream());
            userRepository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc file Excel: " + e.getMessage());
        }
    }

    @Override
    public Page<User> getListUserPage(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10);
        return userRepository.findAll(pageable);
    }
}
