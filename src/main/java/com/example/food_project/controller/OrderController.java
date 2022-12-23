package com.example.food_project.controller;

import com.example.food_project.dto.TOrderDTO;
import com.example.food_project.entity.TOrderEntity;
import com.example.food_project.services.OrderStatusService;
import com.example.food_project.services.TOrderService;
import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.example.food_project.constants.ParamConstant.*;
import static com.example.food_project.constants.ParamConstant.SIGNIN_PARAM;
import static com.example.food_project.constants.TemplateConstant.HOME_TEMP;
import static com.example.food_project.constants.TemplateConstant.ORDER_TEMP;
import static com.example.food_project.constants.ViewConstant.HOME_VIEW;
import static com.example.food_project.constants.ViewConstant.ORDER_VIEW;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
@RequestMapping(ORDER_VIEW)
public class OrderController {

    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    TOrderService tOrderService;

    @GetMapping()
    public ModelAndView orderView() {
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(ORDER_TEMP);
        mav.addObject("path", ORDER_VIEW);
        var authentication = getContext().getAuthentication();


        if (client != null) {
            var user = userService.getUser(authentication.getName());
            List<TOrderEntity> tOrderEntities = tOrderService.getListIdOrderByIdUser(user.getId());
            orderStatusService.updateAllStatus(tOrderEntities);
            List<Integer> listOrder = tOrderService.getTOrder(user.getId());
            List<Integer> listIdPrepare = orderStatusService.findIdOrderByStatus(listOrder, STATUS_PREPARE_PARAM);
            List<Integer> listIdOnWay = orderStatusService.findIdOrderByStatus(listOrder, STATUS_ONWAY_PARAM);
            List<Integer> listIdComplete = orderStatusService.findIdOrderByStatus(listOrder, STATUS_COMPLETE_PARAM);

            mav.addObject("listPrepare", tOrderService.findByListId(listIdPrepare));
            mav.addObject("listOnWay", tOrderService.findByListId(listIdOnWay));
            mav.addObject("listComplete", tOrderService.findByListId(listIdComplete));


            mav.addObject(CLIENT_PARAM, user);
            mav.addObject(SIGNIN_PARAM, true);
        } else {
            mav.addObject(SIGNIN_PARAM, false);
        }
        return mav;
    }
}
