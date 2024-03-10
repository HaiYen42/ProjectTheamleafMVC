package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    @GetMapping("/category")
    public String index(Model model) {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Áo1", true));
        categoryList.add(new Category(2, "Áo 2", false));
        categoryList.add(new Category(3, "Quần", true));
        model.addAttribute("categorylist", categoryList);
        return "admin/category/index";
    }

    @GetMapping("/category/add")
    public String formAdd(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/add";
    }

    @PostMapping("/category/add")
    public String add(@ModelAttribute("category") Category category, Model model) {
        System.out.println("Category"+ category.getCategoryName());
        System.out.println(category.getStatus());
        categoryService.saveOrUpdate(category);
        model.addAttribute("category", new Category());
        model.addAttribute("message", "Create success !");
        return "admin/category/add";
    }

    @GetMapping("/category/edit")
    public String formEdit(Model model) {
        return "admin/category/edit";
    }


}
