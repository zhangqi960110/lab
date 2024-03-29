package com.csu.lab.controller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/student")
public class StudentsController {

    @Autowired
    private ResearcherService researcherService;

    // 分页获取研究生信息列表
    @GetMapping("/studentList")
    public Message getStudentByPage(@RequestParam("person_type")Integer personType,
                                       @RequestParam("page_index")Integer pageIndex,
                                       @RequestParam("page_size")Integer pageSize) {
        List<Researcher> researcherList = researcherService.queryResearcherListPaged(personType, pageIndex, pageSize);
        return Message.success().add(researcherList);
    }

    // 通过id获取研究生个人信息
    @GetMapping("/studentInfo")
    public Message getStudentById(@RequestParam("rid")Integer rid) {
        Researcher tutor = researcherService.queryResearcherById(rid);
        return Message.success().add(tutor);
    }

    // 添加研究生
    @PostMapping("/addStudent")
    public Message addStudent(@ModelAttribute Researcher researcher) throws Exception {
        researcherService.saveResearcher(researcher);
        return Message.success().add("添加成功");
    }

    // 修改研究生信息
    @PutMapping("/modifyStudent")
    public Message modifyStudent(@ModelAttribute Researcher researcher) {
        researcherService.updateResearcher(researcher);
        return Message.success().add("Success");
    }

    // 删除研究生信息
    @DeleteMapping("/deleteStudent")
    public Message deleteStudent(@RequestParam("rid")Integer rid) {
        researcherService.deleteResearcher(rid);
        return Message.success().add("Success");
    }

}
