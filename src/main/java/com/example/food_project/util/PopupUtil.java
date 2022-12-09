package com.example.food_project.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.example.food_project.constants.ParamConstant.*;

@Component
public class PopupUtil {

    public boolean showPopup(ModelAndView mav){
        if (_isMsgShow) {

            mav.addObject(FLAG_MSG_PARAM, true);
            mav.addObject(ICON_PARAM, icon);
            mav.addObject(MSG_PARAM, _msg);
        }
        return false;
    }
}
