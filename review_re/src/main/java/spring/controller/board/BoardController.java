package spring.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.board.Board;
import spring.model.board.BoardDao;
import spring.model.board.Book;
import spring.service.NaverBookService;

@Controller
public class BoardController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private NaverBookService service; 
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping("/text")
	public String test() {
		return "board/text";
	}
	
//	@RequestMapping("/list")
//	public String list(Model model) {
//		List<Board> board = boardDao.board_list();
//		Map<Integer, Book> book = null;
//		for(Board b: board) {
//			book = boardDao.book_list(b.getSearch_no());
//		}
//		
//		model.addAttribute("board", board);
//		model.addAttribute("book", book);
//		log.info("",book.get(171));
//		
//		return "board/list"; 
//	}
	
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request, @RequestParam(required=false) int item) {
		String pageStr = request.getParameter("page");
		int pageNo;
		
		try{
			pageNo = Integer.parseInt(pageStr);
			if(pageNo <= 0) throw new Exception();
		} catch(Exception e){
			pageNo = 1;
		}
		
		int boardSize = 10;
		int boardCount = boardDao.count(item); 
		
		int start = boardSize * pageNo - boardSize + 1;
		int end = start + boardSize - 1;
		if(end > boardCount)
			end = boardCount;
		
		int blockSize = 10;
		int blockTotal = (boardCount + boardSize-1) / boardSize;
		int startBlock = (pageNo - 1) / blockSize * blockSize + 1;
		int endBlock = startBlock + blockSize - 1;
		
		if(blockTotal<endBlock)
			endBlock = blockTotal;
		
		String url = "list?a=1";
		
		List<Board> board = boardDao.board_list(start, end, item);
		Map<Integer, String> nickname = new HashMap<>();	
		Map<Integer, Book> book = null;
		for(Board b: board) {
			book = boardDao.book_list(b.getSearch_no());
			b.setB_item_no(b.getItem_no());
			b.setB_head(b.getHead());
			nickname.put(b.getNo(), boardDao.search_nickname(b.getWriter()));
		}
		
		model.addAttribute("nickname", nickname);
		model.addAttribute("board", board);
		model.addAttribute("book", book);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("endBlock", endBlock);
		model.addAttribute("blockTotal", blockTotal);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("url", url);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("item", item);
		
		return "board/list"; 
	}
	
	@RequestMapping("/book-write")
	public String book_write(Model model, @RequestParam(required=false) int item){
		model.addAttribute("item", item);
		
		return "board/book-write";
	}

	@RequestMapping(value= {"/book-write/{mode}"}, method=RequestMethod.POST)
	public String write_post(Model model, HttpServletRequest request, @PathVariable String mode) {
		Book book = new Book();
		book.setImage(request.getParameter("image"));
		book.setTitle(request.getParameter("book_title"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPubdate(request.getParameter("pubdate"));
		log.info("book : " + book.toString());
		
		Board board = new Board();
		board.setItem_no(Integer.parseInt(request.getParameter("item_no")));
		board.setB_item_no(board.getItem_no());
		board.setHead(Integer.parseInt(request.getParameter("head")));
		board.setB_head(board.getHead());
		board.setWriter(request.getParameter("writer"));
		board.setTitle(request.getParameter("title"));
		board.setDetail(request.getParameter("ir1"));
		log.info("book-write board : " + board.toString());
		
		//String notice = request.getParameter("notice");
		String notice = "false";
		notice = (notice.equals("true")) ? "true" : "false";
		
		board.setNotice(notice);
		
		String tag = request.getParameter("tag");
		if(tag!=null) {
			tag.replaceAll(" ", "");//공백제거
			tag.replaceAll(",", "#");
		}
		board.setTag(tag);
		
		if(mode.equals("write")) {
			int num = 0;
			List<Book> list = boardDao.exist_book(book);
			for(Book b:list) {
				num = b.getNo();
			}

			//책이 존재하지 않으면 새로 저장
			if(num==0) 
				num = boardDao.search_write(book);

			boardDao.write(board, num);

		}
		
		String nickname = boardDao.search_nickname(board.getWriter());
		
		model.addAttribute("nickname", nickname);
		model.addAttribute("mode", mode);
		model.addAttribute("book", book);
		model.addAttribute("board", board);
		model.addAttribute("item", board.getItem_no());
		
		return "board/book-detail";
	}
	
	@RequestMapping("/book-detail")
	public String bookDetail(Model model, @RequestParam(required=false) int no, @RequestParam(required=false) int item_no) {
		Board board = null;
		Book book = null;
		
		List<Board> b_list = boardDao.detail_board(no, item_no);
		board = b_list.get(0);
		book = boardDao.detail_book(board.getSearch_no());
		
		String nickname = boardDao.search_nickname(board.getWriter());

		model.addAttribute("nickname", nickname);		
		model.addAttribute("board", board);
		model.addAttribute("book", book);
		model.addAttribute("item", board.getItem_no());
		
		return "board/book-detail";
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
    
	@RequestMapping("/book-revise/{no}/{item_no}")
	public String bookDetail_re(Model model, @PathVariable int no, @PathVariable int item_no) {
		Board board = null;
		Book book = null;
		
		List<Board> b_list = boardDao.detail_board(no, item_no);
		board = b_list.get(0);
		book = boardDao.detail_book(board.getSearch_no());
		
		String nickname = boardDao.search_nickname(board.getWriter());

		model.addAttribute("nickname", nickname);		
		model.addAttribute("board", board);
		model.addAttribute("book", book);
		model.addAttribute("item", board.getItem_no());
		
		return "board/book-revise";
	}
	
    @RequestMapping(value= {"/book-revise"}, method=RequestMethod.POST)
    public String bookRevise(Model model, HttpServletRequest request, @RequestParam(required=false) int no, @RequestParam(required=false) int item_no) {
    	Book book = new Book();
		book.setImage(request.getParameter("image"));
		book.setTitle(request.getParameter("book_title"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPubdate(request.getParameter("pubdate"));
		
		Board board = new Board();
		board.setItem_no(Integer.parseInt(request.getParameter("item_no")));
		board.setB_item_no(board.getItem_no());
		board.setHead(Integer.parseInt(request.getParameter("head")));
		board.setB_head(board.getHead());
		board.setWriter(request.getParameter("writer"));
		board.setTitle(request.getParameter("title"));
		board.setDetail(request.getParameter("ir1"));
		
		//String notice = request.getParameter("notice");
		String notice = "false";
		notice = (notice.equals("true")) ? "true" : "false";
		
		board.setNotice(notice);
		
		String tag = request.getParameter("tag");
		if(tag!=null) {
			tag.replaceAll(" ", "");//공백제거
			tag.replaceAll(",", "#");
		}
		board.setTag(tag);
		
		List<Board> b_list = boardDao.detail_board(no, item_no);
		Board d_board = b_list.get(0);
		log.info(d_board.toString());	
		
		int num = 0;
		if(d_board!=null) {
			log.info("if문 실행");
			List<Book> list = boardDao.exist_book(book);
			for(Book b:list) {
				num = b.getNo();
			}
	
			//책이 존재하지 않으면 새로 저장
			if(num==0) 
				num = boardDao.search_write(book);
				
			board.setSearch_no(num);
			boardDao.update_board(board, book);
		}
		
		String nickname = boardDao.search_nickname(board.getWriter());
		
		model.addAttribute("nickname", nickname);
		model.addAttribute("book", book);
		model.addAttribute("board", board);
		log.info("수정 후 board : " + board.toString());
		
		if(item_no!=0)
			model.addAttribute("item", item_no);
		else
			model.addAttribute("item", board.getItem_no());
		
		return "board/book-revise";
    }
    
    @RequestMapping(value= {"/book-delete/{no}/{item_no}"}, method=RequestMethod.POST)
    public String bookDelete(Model model, @PathVariable int no, @PathVariable int item_no, @RequestParam String writer) {
    	boardDao.delete_board(no, item_no, writer);
    	
    	//model.addAttribute("item", item_no);
    	
    	return "redirect:/list?item="+item_no;
    }
    
    @RequestMapping(value =  {"/movie-write", "/show-write"}, method=RequestMethod.GET)
	public String write_get(HttpServletRequest request,HttpSession session) {
		session.getAttribute("nickname");
		
		String servletPath  = (String)request.getServletPath();
		if(servletPath.equals("/movie-write")) return "board/movie-write";
		else if(servletPath.equals("/show-write")) return "/show-write";
		else return "/";
	}
    
    
}
