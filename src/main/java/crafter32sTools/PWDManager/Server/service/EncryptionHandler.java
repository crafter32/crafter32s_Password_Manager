package crafter32sTools.PWDManager.Server.service;

import crafter32sTools.PWDManager.Server.DTO.LoginDTO;
import crafter32sTools.PWDManager.Server.DTO.LoginParamDTO;
import crafter32sTools.PWDManager.Server.DTO.UserDTO;
import crafter32sTools.PWDManager.Server.repository.LoginParamRepository;
import crafter32sTools.PWDManager.Server.repository.LoginRepository;
import crafter32sTools.PWDManager.Server.repository.UserRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class EncryptionHandler {
    private static final String ALGORITHM = "AES";

    static Logger logger = LoggerFactory.getLogger(EncryptionHandler.class);

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static LoginRepository loginRepository;
    @Autowired
    private static LoginParamRepository loginParamRepository;

    protected static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    protected static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }

    protected static String encrypt(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        logger.info("Data successfully encrypted!");
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }
    protected static String decrypt(String encryptedData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        logger.info("Data successfully decrypted!");
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }

    protected static UserDTO encryptUser(UserDTO userDTO) throws Exception {
        UserDTO encrypted = userDTO;
        if(encrypted.id() == null) encrypted = UserDTO.fromUser(userRepository.save(encrypted.toUser()));
        return UserDTO.fromUser(userRepository.save(new UserDTO(
                encrypted.id(),
                encrypt(encrypted.username(), encrypted.id().toString()),
                encrypt(encrypted.email(), encrypted.id().toString()),
                hashPassword(encrypted.password()),
                LoginDTO.toList(encryptLoginList(LoginDTO.fromList(encrypted.login())))
        ).toUser()));
    }

    protected static List<LoginDTO> encryptLoginList(List<LoginDTO> list){
        List<LoginDTO> res = new ArrayList<>();
        for(var x : list){
            res.add(encryptLogin(x));
        }
        return res;
    }

    protected static LoginDTO encryptLogin(LoginDTO loginDTO){
        LoginDTO encrypted = loginDTO;
        if(encrypted.getId() == null) encrypted = LoginDTO.fromLogin(loginRepository.save(encrypted.toLogin()));
        return LoginDTO.fromLogin(loginRepository.save(new LoginDTO(
                encrypted.getId(),
                encrypted.getLoginParams(),
                encrypted.getTimestamp()
        ).toLogin()));
    }

    protected static LoginParamDTO encryptLoginParam(LoginParamDTO loginParamDTO){
        LoginParamDTO encrypted = loginParamDTO;
        if(encrypted.getId() == null) encrypted = LoginParamDTO.fromLoginParam(loginParamRepository.save(encrypted.toLoginParam()));
        return LoginParamDTO.fromLoginParam(loginParamRepository.save(new LoginParamDTO(
                encrypted.getId(),
                encrypted.getName(),
                encrypted.getValue()
        ).toLoginParam()));
    }

    protected static List<LoginParamDTO> encryptLoginParamList(List<LoginParamDTO> list){
        List<LoginParamDTO> res = new ArrayList<>();
        for(var x : list){
            res.add(encryptLoginParam(x));
        }
        return res;
    }

    protected static UserDTO decryptUser(UserDTO userDTO) throws Exception {
        return new UserDTO(
                userDTO.id(),
                decrypt(userDTO.username(), userDTO.id().toString()),
                decrypt(userDTO.email(), userDTO.id().toString()),
                userDTO.password(),
                userDTO.login()
        );
    }
}
