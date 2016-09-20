package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenji on 9/16/2016.
 */
@Controller
public class WebToDoController {
    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.name = "Zach";
            user.password = "hunter2";
            users.save(user);
        }
    }

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, password);
            users.save(user);
        }
        else if (!password.equals(user.getPassword())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @Autowired
    TodoRepository todos;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }

        List<Todo> todoList = new ArrayList<Todo>();
        User savedUser = (User) session.getAttribute("user");
        if (savedUser != null) {
            todoList = todos.findByUser(savedUser);
        }
        model.addAttribute("todos", todoList);
        return "home";
    }

    @RequestMapping(path = "/add-todo", method = RequestMethod.POST)
    public String addTodo(HttpSession session, String text) {
        User user = (User) session.getAttribute("user");
        Todo todo = new Todo(text, user);
        todos.save(todo);
        return "redirect:/";
    }

    @RequestMapping(path = "/toggle-todo", method = RequestMethod.GET)
    public String toggleTodo(HttpSession session,  Integer todoID) {
        Todo todo = todos.findOne(todoID);
        System.out.println(todo.isDone);
        todo.isDone = !todo.isDone;
        todos.save(todo);
        System.out.println(todo.isDone);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteGame(Model model, Integer todoID) {
        if (todoID != null) {
            todos.delete(todoID);
        }

        return "redirect:/";
    }
}
