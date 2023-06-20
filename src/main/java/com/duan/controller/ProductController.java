package com.duan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duan.entity.Product;
import com.duan.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	/* Hiển thị tất cả sản phẩm và lọc theo danh sách loại sản phẩm */
	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid") Optional<String> cid) { 	/* Truyền vào một tham số */
		if(cid.isPresent()) { 							/* isPresent: Nếu có mã loại đó lấy ra truy xuất */
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		}else { 			  /* Ngược lại nếu không có mã loại truy xuất tất cả */
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		
		return "product/list";
	}
	
	/* Xem chi tiết sản phẩm */
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "product/detail";
	}
}
