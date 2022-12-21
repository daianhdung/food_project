package com.example.food_project.controller.api;

import com.example.food_project.entity.FoodOrderEntity;
import com.example.food_project.entity.TOrderEntity;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.example.food_project.constants.ParamConstant.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("api")
public class CheckoutApi {

    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;
    @Autowired
    UserFavorService userFavorService;
    @Autowired
    TOrderService tOrderService;
    @Autowired
    FoodOrderService foodOrderService;

    @PostMapping("/order")
    public ResponseEntity<?> orderFood(@RequestBody List<FoodOrderEntity> foodOrderEntityList){
        var authentication = getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        TOrderEntity tOrder = new TOrderEntity();

        //Create date.now
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("Success");
//            }
//        };
//
//        timer.schedule(task, 0);test2

        tOrder.setUser(user);
        tOrder.setOrderDate((formattedDate));
        TOrderEntity savetOrder = tOrderService.newOrder(tOrder);

        foodOrderService.insertNewListFoodOrder(savetOrder.getId(), foodOrderEntityList);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setStatus(200);

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
