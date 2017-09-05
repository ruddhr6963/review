package spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Book;
import spring.service.NaverBookService;
 
@Controller
@RequestMapping("/board")
public class BookController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private NaverBookService service; 
    
    @RequestMapping("/list")
	public String list() {
		return "board/list";
	}
    
    @RequestMapping("/book-board")
    public String write(Model model, @RequestParam(required=false) String image, @RequestParam(required=false) String title, @RequestParam(required=false) String author, @RequestParam(required=false) String publisher, @RequestParam(required=false) String pubdate) {
    	log.info("image : " + image);
    	log.info("title : " + title);
    	log.info("author : " + author);
    	log.info("publisher : " + publisher);
    	log.info("pubdate : " + pubdate);
    	
    	Book book = new Book();
    	
    	model.addAttribute("name", title);
    	
    	if(image==null)
    		image = "http://placehold.it/120x120";
    	if(title==null)
    		title = "책 제목";
    	if(author==null)
    		author = "저자";
    	if(publisher==null)
    		publisher = "출판사";
    	if(pubdate==null)
    		pubdate = "출판일";

    	book.setImage(image);
    	book.setTitle(title);
    	book.setAuthor(author);
    	book.setPublisher(publisher);
    	book.setPubdate(pubdate);
    	
    	model.addAttribute("search_book", book);
    	
    	return "board/book-board";
    }
    
    //키워드가 있을때도 있고 없을때도있음 
    //있을때는 가져가고 없을때는 안가져가고 
    @RequestMapping("/bookList")
    public String bookList(Model model, @RequestParam(required=false)String keyword){        
        if(keyword !=null)
        {
        	model.addAttribute("bookList", service.searchBook(keyword,10,1));
            model.addAttribute("keyword", keyword);
        }
        
        return "board/bookList";
    }

}


