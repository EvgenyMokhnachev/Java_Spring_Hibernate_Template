package controllers;

import database.dto.User;
import requestViews.UserRequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import response.JsonResponse;
import services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes({"sessionUser"})
public class UserCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public @ResponseBody JsonResponse register(@Valid @RequestBody UserRequestView userRequestView,
                                                BindingResult bindingResult,
                                                ModelMap modelMap) {

        JsonResponse jsonResponse = new JsonResponse(bindingResult);

        if (jsonResponse.isSuccess()) {
            User user = userService.registerUser(userRequestView.getUsername(), userRequestView.getEmail(), userRequestView.getPassword());
            modelMap.put("sessionUser", user);
        }

        return jsonResponse;
    }

}

