package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegistrationException("User can not be null");
        }

        if (user.getPassword() == null) {
            throw new RegistrationException("Password can not be null");
        }

        if (user.getAge() == null) {
            throw new RegistrationException("Age can not be null");
        }

        if (user.getLogin() == null) {
            throw new RegistrationException("Login can not be null");
        }

        User current = storageDao.get(user.getLogin());

        if (current != null) {
            throw new RegistrationException("Such User login already exists");
        }

        if (user.getAge() < 18) {
            throw new RegistrationException("User must be older than 17");

        }

        if (user.getLogin().length() < 6) {
            throw new RegistrationException("User's login must be longer than 5 characters");

        }

        if (user.getPassword().length() < 6) {
            throw new RegistrationException("User's password must be longer than 5 characters");
        }

        return storageDao.add(user);
    }
}
