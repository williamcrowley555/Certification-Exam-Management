package com.william.certificationexammanagement.controller;

import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.service.ExamRoomService;
import com.william.certificationexammanagement.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "exam-rooms")
public class ExamRoomController {

    @Autowired
    private ExamRoomService examRoomService;

    @Autowired
    private ExamineService examineService;

    @GetMapping("/examines")
    public String showExamineExamRoom(Model model, @RequestParam(value = "fullName", defaultValue = "") String fullName,
                                      @RequestParam(value = "phone", defaultValue = "") String phone) {
        List<ExamRoom> examRooms = null;
        Examine examine = null;
        fullName = fullName.trim();
        phone = phone.trim();

        if (!fullName.isEmpty() && !phone.isEmpty()) {
            examine = examineService.getExamineByFullNameAndPhone(fullName, phone);
            examRooms = examRoomService.getExamRoomByExamineFullNameAndExaminePhone(fullName, phone);
        }

        model.addAttribute("fullName", fullName);
        model.addAttribute("phone", phone);
        model.addAttribute("examine", examine);
        model.addAttribute("examRooms", examRooms);

        return "exam_rooms";
    }
}
