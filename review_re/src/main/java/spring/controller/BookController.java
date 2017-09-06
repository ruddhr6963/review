package spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public String write(@RequestParam(required=false)String image, @RequestParam(required=false)String title, @RequestParam(required=false)String author, @RequestParam(required=false)String publisher, @RequestParam(required=false)String pubdate) {
    	log.info("image : " + image);
    	log.info("title : " + title);
    	log.info("author : " + author);
    	log.info("publisher : " + publisher);
    	log.info("pubdate : " + pubdate);
    	
    	return "board/book-board";
    }
    
    //키워드가 있을때도 있고 없을때도있음 
    //있을때는 가져가고 없을때는 안가져가고 
    @RequestMapping("/bookList")
    public ModelAndView bookList(@RequestParam(required=false)String keyword){
    //public ModelAndView bookList(@PathVariable String keyword){
    	log.info("함수 실행");
    	log.info("키워드 : " + keyword);
        ModelAndView mav = new ModelAndView();
        
        if(keyword !=null)
        {
            mav.addObject("bookList",service.searchBook(keyword,10,1));
            mav.addObject("keyword", keyword);
        }
        
        mav.setViewName("board/bookList");
        return mav;
    }
    
    @RequestMapping("/")
    public void search() {
    	log.info("함수 호출");
    }
}


