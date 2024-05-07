package org.gluu.agama.saml.service;

import io.jans.as.common.model.common.User;
import io.jans.as.common.service.common.UserService;
import io.jans.service.cdi.util.CdiUtil;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserHelperService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserHelperService.class);

public static String exec(String prefix, String attr, Map<String, Object> profile) {
    logger.debug("Retrieving user identified with prefix:{}, attr:{}, profile:{}", prefix, attr, profile);
        String uid = Optional.ofNullable(prefix).orElse("") + profile.get(attr).toString();
        
        //if not existing, insert user with the uid just built
        UserService userService = CdiUtil.bean(UserService.class);
        logger.debug("Retrieving user identified by {}", uid);
        User user = userService.getUser(uid);
        logger.debug("user identified by {} is {}", uid, user);
        if (user != null) {
            logger.debug("Found!");
        } else {
            logger.debug("Not found. Inserting entry");
            user = new User();
            user.setUserId(uid);
            userService.addUser(user, true);
        }
        return uid;
        
    }

public static String exec(String name) {
    logger.info("\n\n\n Check user identified by name:{}", name);
    String uid = name;
    
    //if not existing, insert user with the uid just built
    UserService userService = CdiUtil.bean(UserService.class);
    logger.debug("\n\n Retrieving user identified by {}", uid);
    User user = userService.getUser(uid);
    logger.info("\n\n user identified by {} is {}", uid, user);
    if (user != null) {
        logger.info("Found user:{}!",user);
    } else {
        logger.info("\n\n\n Not found. Inserting entry");
        user = new User();
        user.setUserId(uid);
        userService.addUser(user, true);
    }
    return uid;
    
}

}
