package rizki.practicum.learning.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rizki.practicum.learning.configuration.Routes;
import rizki.practicum.learning.entity.User;
import rizki.practicum.learning.service.role.RoleDefinition;
import rizki.practicum.learning.service.role.RoleServiceInterface;
import rizki.practicum.learning.service.storage.StorageServiceInterface;
import rizki.practicum.learning.service.user.UserServiceInterface;
import rizki.practicum.learning.util.Confirmation;
import rizki.practicum.learning.util.response.ResponseWrapper;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @Autowired @Qualifier("UserPhotoStorageService")
    private StorageServiceInterface storageService;

    @Autowired
    private RoleServiceInterface roleService;

    @Autowired
    private ResponseWrapper responseWrapper;

    @PostMapping(Routes.UserRoutes.USER_REGISTER)
    public ResponseEntity<Map<String,Object>> register_user(
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("identity") String identity,
            @RequestParam(value="photo", required=false) MultipartFile photo
            ){

        User newUser = new User();
        newUser.setName(name);
        newUser.setPhoto(null);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setIdentity(identity);
        newUser.setRole(roleService.getRole(RoleDefinition.Practican.initial));
        try{
            Confirmation confirmation = userService.createUser(newUser);
            if(photo!=null) newUser.setPhoto(storageService.store(photo));
            return responseWrapper.restResponseCollectionWrapper(HttpStatus.CREATED,null,
                    Routes.UserRoutes.USER_REGISTER,1,confirmation.getMessage());
        }catch (Exception e){
            return responseWrapper.restResponseCollectionWrapper(HttpStatus.NOT_ACCEPTABLE,null,
                    Routes.UserRoutes.USER_REGISTER,0,null);
        }
    }

    @PostMapping(Routes.UserRoutes.USER_UPDATE)
    public ResponseEntity<Map<String,Object>> updateUser(
            @PathVariable("id") String id,
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ){
        try {
            User userEdit = userService.getUser(id);
            if(email!=null){
                userEdit.setEmail(email);
            }
            if(name!=null){
                userEdit.setName(name);
            }
            if(password!=null){
                userEdit.setPassword(password);
            }
            Confirmation confirmation = userService.updateUser(userEdit);
            return responseWrapper.restResponseCollectionWrapper(HttpStatus.OK,
                    null, Routes.UserRoutes.USER_UPDATE,1,confirmation.getMessage());
        } catch (Exception e) {
            return responseWrapper.restResponseCollectionWrapper(HttpStatus.OK,
                    null, Routes.UserRoutes.USER_UPDATE,0,null);
        }
    }
}
