package com.william.certificationexammanagement.controller;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.service.ExamCourseService;
import com.william.certificationexammanagement.service.ExamRoomService;
import com.william.certificationexammanagement.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "exam-rooms")
public class ExamRoomController {

    @Autowired
    private ExamRoomService examRoomService;

    @Autowired
    private ExamineService examineService;

    @Autowired
    private ExamCourseService examCourseService;

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

    @GetMapping("/details")
    public String showExamRoomDetailsByExamCourse(Model model, @RequestParam(value = "examCourseId", required = false) Long examCourseId,
                                                  @RequestParam(value = "examRoomId", required = false) Long examRoomId) {
        List<ExamCourse> examCourses = examCourseService.getExamCourses();
        List<ExamRoom> examRooms = null;
        Set<Examine> examines = null;

        ExamCourse currentExamCourse = null;
        ExamRoom currentExamRoom = null;

        if (examCourseId != null) {
            currentExamCourse = examCourseService.getExamCourseById(examCourseId);
            examRooms = examRoomService.getExamRoomByExamCourse(currentExamCourse);
        }

        if (examRoomId != null) {
            currentExamRoom = examRoomService.getExamRoomById(examRoomId);
            examines = currentExamRoom.getExamines();

            if (examCourseId == null) {
                currentExamCourse = currentExamRoom.getExamCourse();
                examRooms = examRoomService.getExamRoomByExamCourse(currentExamCourse);
            }
        }

        model.addAttribute("currentExamCourse", currentExamCourse);
        model.addAttribute("currentExamRoom", currentExamRoom);
        model.addAttribute("examCourses", examCourses);
        model.addAttribute("examRooms", examRooms);
        model.addAttribute("examines", examines);

        return "exam_room_details";
    }

}
