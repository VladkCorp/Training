package com.Udm1.OnlineShopBackEnd;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultUserStoringService implements UserStoringService {

    private static final String USER_INFO_STORAGE_FILE_NAME = "Users.csv";
    private static final int USER_FIRSTNAME_INDEX = 0;
    private static final int USER_LASTNAME_INDEX = 1;
    private static final int USER_PASSWORD_INDEX = 2;
    private static final int USER_EMAIL_INDEX = 3;
    private static final int USER_ID_INDEX = 4;

    private UserManagementService userManagementService;

    {
        userManagementService = DefaultUserManagementService.getInstance();
    }

    private static DefaultUserStoringService instance;

    public static DefaultUserStoringService getInstance() {
        if (instance == null) {
            instance = new DefaultUserStoringService();
        }
        return instance;
    }

    @Override
    public void saveUser(User user) {
        try {
            Files.writeString(Paths.get(USER_INFO_STORAGE_FILE_NAME),
                    System.lineSeparator() + convertToStorableString(user),
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertToStorableString(User user) {
        return user.getFirstName() + "," + user.getLastName() + ","
                + user.getPassword() + "," + user.getEmail() + "," + user.getId();
    }

    @Override
    public void serializeUsers() {
        try {
            for (int i = 0; i < userManagementService
                    .getRecentUsers().size(); i++) {
                saveUser(userManagementService
                        .getRecentUsers().get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deserializeUsers() {
        userManagementService.addUsers(loadUsers());
    }

    @Override
    public List<User> loadUsers() {
        try (var stream = Files
                .lines(Paths.get(USER_INFO_STORAGE_FILE_NAME))) {
            return stream.filter(Objects::nonNull)
                    .filter(line -> !line.isEmpty())
                    .map(line -> {
                        String[] userElements = line.split(",");
                        return new DefaultUser(userElements[USER_FIRSTNAME_INDEX],
                                userElements[USER_LASTNAME_INDEX], userElements[USER_PASSWORD_INDEX],
                                userElements[USER_EMAIL_INDEX], userElements[USER_ID_INDEX]);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
