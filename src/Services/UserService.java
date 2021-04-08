package Services;

import Entity.Enum.UserRole;
import Repository.LoginRepository;

import java.util.Random;

public class UserService {

    private final LoginRepository loginRepository;

    public UserService() {
        this.loginRepository =  new LoginRepository();
    }


    public int addPatient(String username, UserRole role) {
        return  this.loginRepository.addUser(username, this.generatePassword(), role.getI());
    }

    private String generatePassword(){
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
