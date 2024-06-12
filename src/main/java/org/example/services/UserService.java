package org.example.services;

import org.example.dtos.UserDTO;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.utils.CPFUtils;
import org.example.utils.constants.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Validações de cadastro do usuario
    public void UserValidation(UserEntity user) throws Exception{
        if(user.getType() == null){
            throw new Exception("User nulo.");
        }
        if(user.getType() == UserType.MERCHANT){
            if(user.getCpf() == null || CPFUtils.isCPF(user.getCpf())){
                throw new Exception(" - Usuário lojista deve ter CNPJ");
            }
        }else{
            if(user.getCpf() == null || !CPFUtils.isCPF(user.getCpf())){
                throw new Exception(" - Usuário deve ter CPF");
            }
        }

//        if(userRepository.existsCpf(user.getCpf()) != null){
//            throw new Exception("");
//        }
//        if(userRepository.existsEmail(user.getEmail()) != null){
//            throw new Exception("");
//        }

    }

    public UserEntity createUser(UserDTO user){
        UserEntity newUser = new UserEntity();
        newUser.setNome(user.name());
        newUser.setEmail(user.email());
        newUser.setSenha(user.password());
        newUser.setCpf(user.document());
        newUser.setBalance(user.balance());
        newUser.setType(user.type());

        return newUser;
    }

    public void UserSave(UserEntity user){
        try {
            UserValidation(user);

            this.userRepository.save(user);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity UserFindById(Long id) throws Exception{
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }


}
