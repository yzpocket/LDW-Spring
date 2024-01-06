package com.citefred.ldwspring.web;

import com.citefred.ldwspring.config.auth.LoginUser;
import com.citefred.ldwspring.config.auth.dto.SessionUser;
import com.citefred.ldwspring.service.PostsService;
import com.citefred.ldwspring.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    //@GetMapping("/")
    //public String index(Model model, @LoginUser SessionUser sessionUser){
    //    model.addAttribute("posts", postsService.findAllDesc());
    //    if (sessionUser != null) {
    //        model.addAttribute("userName", sessionUser.getName());
    //    }
    //    return "index";
    //}

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser sessionUser){
        model.addAttribute("posts", postsService.findAllDescUsingQueryDSL());
        if (sessionUser != null) {
            model.addAttribute("userName", sessionUser.getUsername());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser sessionUser){
        if (sessionUser != null) {
            model.addAttribute("userName", sessionUser.getUsername());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,
                              Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
