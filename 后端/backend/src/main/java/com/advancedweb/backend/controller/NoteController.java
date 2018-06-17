package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.model.Note;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.repository.NoteRepository;
import com.advancedweb.backend.service.NodeService;
import com.advancedweb.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class NoteController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add_note/{user_name}/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success addNote(@PathVariable String user_name, @PathVariable String course_id,
                           @PathVariable String mindmap_id, @PathVariable String node_id,
                            @RequestBody Note note) {
        Success s = new Success();
        s.setSuccess(false);

        //保存note
        noteRepository.save(note);
        //获得学生 和 node
        Student thisStudent = userService.findStudentByName(user_name);
        Node thisNode = nodeService.findByNodeId(course_id+" "+mindmap_id,node_id);

        if (thisNode!=null&&thisStudent!=null) {
            //创建学生-> WRITE-> note关系
            thisStudent.write(note);
            userService.saveStudent(thisStudent);
            //创建Node->  HAS_NOTE -> note关系
            thisNode.saveNote(note);
            nodeService.save(thisNode);
            s.setSuccess(true);
        }
        return s;

    }

    @RequestMapping(value = "/public_note/{user_name}/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public List<Note> publicNote(@PathVariable String user_name, @PathVariable String course_id,
                                 @PathVariable String mindmap_id, @PathVariable String node_id) {

        List<Note> notes = new LinkedList<>();
        //获得学生 和 node
        Student thisStudent = userService.findStudentByName(user_name);
        Node thisNode = nodeService.findByNodeId(course_id+" "+mindmap_id,node_id);

        Note[] stu_notes= userService.getStudentNotes(thisStudent.getId());

        Note[] node_notes= nodeService.getNotes(thisNode.getLong_id());


        for (Note stu_note : stu_notes){
            if(stu_note.getAccess().equals("private"))
                continue;
            for (Note node_note:node_notes) {
                if (stu_note.getNote_id().equals(node_note.getNote_id())){
                    notes.add(stu_note);
                    break;
                }
            }
        }

        return notes;
    }
    @RequestMapping(value = "/private_note/{user_name}/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public List<Note> privateNote(@PathVariable String user_name, @PathVariable String course_id,
                                 @PathVariable String mindmap_id, @PathVariable String node_id) {

        List<Note> notes = new LinkedList<>();
        //获得学生 和 node
        Student thisStudent = userService.findStudentByName(user_name);
        Node thisNode = nodeService.findByNodeId(course_id+" "+mindmap_id,node_id);

        Note[] stu_notes= userService.getStudentNotes(thisStudent.getId());

        Note[] node_notes= nodeService.getNotes(thisNode.getLong_id());


        for (Note stu_note : stu_notes){
            if(stu_note.getAccess().equals("public"))
                continue;
            for (Note node_note:node_notes) {
                if (stu_note.getNote_id().equals(node_note.getNote_id())){
                    notes.add(stu_note);
                    break;
                }
            }
        }

        return notes;
    }

    @RequestMapping(value = "/search_note/{user_name}/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public List<Note> searchNote(@PathVariable String user_name, @PathVariable String course_id,
                                  @PathVariable String mindmap_id, @PathVariable String node_id) {

        List<Note> notes = new LinkedList<>();
        //获得学生 和 node
        Student thisStudent = userService.findStudentByName(user_name);
        Node thisNode = nodeService.findByNodeId(course_id+" "+mindmap_id,node_id);

        Note[] stu_notes= userService.getStudentNotes(thisStudent.getId());

        Note[] node_notes= nodeService.getNotes(thisNode.getLong_id());

        for (Note node_note : node_notes){
            if(node_note.getAccess().equals("private"))
                continue;

            boolean note_by_stu = false;
            for (Note stu_note:stu_notes) {
                if (stu_note.getNote_id().equals(node_note.getNote_id())){
                    note_by_stu =true;
                    break;
                }
            }
            if (!note_by_stu)
                notes.add(node_note);
        }

        return notes;
    }
}
