package egovframework.fusion.imgboard.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.fusion.comm.vo.PagingVO;
import egovframework.fusion.imgboard.dto.ImgBoardListDTO;
import egovframework.fusion.imgboard.dto.UpdDTO;
import egovframework.fusion.imgboard.service.HashTagService;
import egovframework.fusion.imgboard.service.ImgBoardLikeService;
import egovframework.fusion.imgboard.service.ImgBoardService;
import egovframework.fusion.imgboard.service.ImgService;
import egovframework.fusion.imgboard.vo.HashTagUpdateVO;
import egovframework.fusion.imgboard.vo.HashTagVO;
import egovframework.fusion.imgboard.vo.ImgBoardLikeCntVO;
import egovframework.fusion.imgboard.vo.ImgBoardLikeVO;
import egovframework.fusion.imgboard.vo.ImgBoardVO;
import egovframework.fusion.imgboard.vo.ImgVO;

@Controller
public class ImgBoardController {

	@Autowired
	ImgBoardService imgBoardService;
	@Autowired
	HashTagService hashTagService;
	@Autowired
	ImgService imgService;
	@Autowired
	ImgBoardLikeService imgBoardLikeService;
	
	
	/*
	 * 갤러리 게시판 호출
	 */
	
	@RequestMapping(value="/imgBoard/imgBoardList.do", method = RequestMethod.GET)
	public String imgBoardList(@RequestParam(value="search") String search,
							   @RequestParam(value="searchText") String searchText) {
		return "views/imgBoard/imgBoardList";
	}
	/*
	 * 갤러리 게시판 리스트 출력
	 */
	@RequestMapping(value="/imgBoard/listElement.do", method = RequestMethod.GET)
	public String listElement(HttpServletRequest request
							  , @ModelAttribute ImgBoardVO imgBoardVo
							  , @ModelAttribute HashTagVO hashTagVo
							  , @ModelAttribute PagingVO pagingVo
							  , @ModelAttribute ImgBoardLikeVO imgBoardLikeVo
							  , Model model) throws Exception{
		//페이징
		int total = 0;	
		 //총 게시글 수
		total = imgBoardService.searchTotal(imgBoardVo);
		String searchParam = pagingVo.getSearch();			
		int filterPageNum = pagingVo.getPageFilter();	
		
		if(filterPageNum == 0) {
			filterPageNum = 10;
			pagingVo.setPageFilter(filterPageNum);
		}
		pagingVo.setPageFilter(filterPageNum);
		
		if(searchParam != null || searchParam != "") {
			 total = imgBoardService.searchTotalCount(pagingVo);
		}
		int totalPage = (int)Math.ceil((double)total/filterPageNum);
		int viewPage = pagingVo.getViewPage();
		
		if(total % filterPageNum*viewPage > 0){
			totalPage++;
		}
	
		int startIndex = (viewPage-1)*filterPageNum + 1;
		int endIndex = startIndex + (filterPageNum -1);
		
		pagingVo.setStartIndex(startIndex);
		pagingVo.setEndIndex(endIndex);
		pagingVo.setTotal(total);
		pagingVo.setTotalPage(totalPage);
		
		
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pagingInfo", pagingVo);
			
		//리스트 출력
		HttpSession session = request.getSession(false);

		String userId =  (String) session.getAttribute("id");
		
		pagingVo.setUserId(userId);
		List<ImgBoardListDTO> imgBoardList = imgBoardService.getImgBoard(pagingVo);		
		List<HashTagVO> hashTagList = hashTagService.getHashTagInfo(hashTagVo);
		
		model.addAttribute("imgBoardList", imgBoardList);
		model.addAttribute("hashTagList",hashTagList);
		
		return "views/imgBoard/listElement";
	}
	
	/*
	 * 게시글 등록 페이지로 이동
	 */
	@RequestMapping(value="/imgBoard/insPageImgBoard.do", method = RequestMethod.GET)
	public String insPageImgBoard() throws Exception{
		return "views/imgBoard/insPageImgBoard";
	}
	/*
	 * 게시글 등록(DB에 저장)
	 */

	@RequestMapping(value="/imgBoard/insImgBoard.do", method = RequestMethod.POST)
	@ResponseBody

	public String insImgBoard(MultipartHttpServletRequest request
							 , ImgBoardVO imgBoardVO
							 , HashTagVO hashTagVO
							 ) throws Exception{
		String msg = "success";

		HttpSession session = request.getSession(false);

		String userId =  (String) session.getAttribute("id");
		
		/*게시글 입력(제목,내용)*/
		imgBoardVO.setImgBoardWriter(userId);		
		imgBoardService.insImgBoard(imgBoardVO);
		
		/*게시글 입력(해시태그)*/
		
		List<String>hashTagList = hashTagVO.getHashTagList();
		
		hashTagVO.setHashTagWriter(userId);
		
		if(hashTagList.size() != 0 && hashTagList != null) {
			
			String hashTagValue [] = hashTagList.get(0).split(",") ;
			
			
			List<String>hashTag = hashTagVO.getHashTag();

			for(int i=0; i<hashTagValue.length; i++) {
				hashTag.add(hashTagValue[i]);
			}
			for(String n : hashTag) {
				System.out.println("asdasdasd"+n);
			}
			
			hashTag.remove(null);
			hashTag.remove("");

			hashTagService.insHashTag(hashTagVO);
		}
		
		
		/*입력한 이미지+텍스트 데이터받아오기*/
		List<MultipartFile> files = request.getFiles("imgae");
		
		String uploadRoot = request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/"); // 서버의 실제경로를 받아옴
		String uploadContext = "/upload";
		String uploadFolder = uploadRoot + uploadContext;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();		
		String toDay = sdf.format(date);

		/* 날짜 구분자('-') -> 경로구분자('\')
		 * str 변수의 값의 문자열 중 '-'을 String 클래스의 replace 메서드를 사용하여 File.separator로 변경해주는 코드를 작성
		 * */

		String dataPath = toDay.replace("-", "/");

		/*폴더 생성*/
		File uploadPath = new File(uploadFolder, dataPath);
		// 만약 폴더가 존재하지 않는다면 폴더를 생성하도록 
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
	
		List<ImgVO> imageInfo = new ArrayList<>();	
		
		for(MultipartFile multipartFile : files) {
			/* 파일 이름*/

			//입력한 이미지+텍스트 데이터받아오기
			ImgVO imgVO = new ImgVO();
			
			String imgOriginName = multipartFile.getOriginalFilename();
			
			int startIndex;
			startIndex = multipartFile.getContentType().indexOf('/')+1;	
			String format;
			format = multipartFile.getContentType().substring(startIndex);

			String uuid = UUID.randomUUID().toString();
			
			String imgSaveName = uuid + "." + format;

			long size= multipartFile.getSize();

			/* 파일 위치, 파일 이름을 합친 File 객체*/
			File saveFile = new File(uploadPath, imgSaveName);
			
			String saveFolder = uploadFolder.replaceAll(uploadRoot, "");

			imgVO.setImgOriginName(imgOriginName);
			imgVO.setImgSaveName(imgSaveName);
			imgVO.setImgFormat(format);
			imgVO.setImgSize(size);
			imgVO.setImgWriter(userId);
			imgVO.setImgSavePath(saveFolder+ "/" +  dataPath);
			imageInfo.add(imgVO);
			
			multipartFile.transferTo(saveFile);
			
			//썸네일
			String thumbPath = uploadFolder + "/" + "resize";
			//File thumbNailFile = new File(uploadFolder, "s_" + imgSaveName);
			File thumbNailFile = new File(thumbPath, imgSaveName);
			BufferedImage bufImg = ImageIO.read(saveFile);
			//썸네일 크기및 색상정보 설정
			BufferedImage bufImgInfo= new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
			//그리기
			Graphics2D graphic = bufImgInfo.createGraphics();
			
			graphic.drawImage(bufImg, 0, 0, 400, 600, null);
			
			ImageIO.write(bufImg,"jpg" ,thumbNailFile);
					
		}
		for(ImgVO item : imageInfo) {
			System.out.println("imgINfo:::::::::::::::::"+item);
		}
		imgService.insImg(imageInfo);
		
		int imgBoardId = imgBoardService.getMaxBoardId(userId);
		imgBoardService.insThumbNail(imgBoardId);
	
		return msg;
	}
	/*
	 * 상세페이지로 이동
	 */
	@RequestMapping(value="/imgBoard/detailPage.do", method = RequestMethod.GET)
	public String detailPage (@RequestParam(value="imgBoardId") int imgBoardId, Model model) throws Exception {
		ImgBoardVO boardInfo = imgBoardService.getImgBoardDetailInfo(imgBoardId);
		List<HashTagVO> hashTag = hashTagService.getHashTagDetailInfo(imgBoardId);
		List<ImgVO> imgInfo = imgService.getImgDetailInfo(imgBoardId);
			
		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("hashTag", hashTag);
		model.addAttribute("imgInfo", imgInfo);
		
		return "views/imgBoard/imgBoardDetail";
	}
	
	/*
	 *좋아요 (입력)
	 */
	
	@RequestMapping(value="/imgBoard/like.do", method = RequestMethod.POST)
	@ResponseBody
	public String like (ImgBoardLikeVO imgBoardLikeVo, 
						ImgBoardLikeCntVO imgBoardLikeCntVo,
						HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession(false);

		String userId =  (String) session.getAttribute("id");
		
		imgBoardLikeVo.setPushLikeUser(userId);
		imgBoardLikeCntVo.setPushLikeUser(userId);
		//회원의 likeYn값을 조회
		String likeYn = imgBoardLikeCntVo.getLikeYn();
		int likeYnRes = imgBoardLikeService.getLikeYnRes(imgBoardLikeCntVo);

		//처음눌렀으면
		if(likeYnRes == 0) {
			imgBoardLikeVo.setLikeYn("Y");
			imgBoardLikeCntVo.setLikeYn("Y");
			imgBoardLikeService.insLikeInfo(imgBoardLikeVo); // 처음 눌렀을 때 로그정보 입력
			imgBoardLikeService.insLikeCntInfo(imgBoardLikeCntVo); //처음 눌렀을 때 카운트테이블
		}else {
			if (likeYn.equals("Y")) {
				imgBoardLikeVo.setLikeYn("N");
				imgBoardLikeCntVo.setLikeYn("N");
				imgBoardLikeService.insLikeInfo(imgBoardLikeVo); //로그정보 insert
				imgBoardLikeService.upLikeYn(imgBoardLikeCntVo); // 카운트 테이블 update
			}else if (likeYn.equals("N")) {
				imgBoardLikeVo.setLikeYn("Y");
				imgBoardLikeCntVo.setLikeYn("Y");
				imgBoardLikeService.insLikeInfo(imgBoardLikeVo); //로그정보 insert
				imgBoardLikeService.upLikeYn(imgBoardLikeCntVo); // 카운트 테이블 update
			}
		}
		

		return "success";
	}
	/*
	 * 수정페이지 호출
	 */
	@RequestMapping(value="/imgBoard/updatePageImgBoard.do", method = RequestMethod.GET)
	public String updElement(@RequestParam(value="imgBoardId",required=false) int imgBoardId, Model model) throws Exception{

		return "views/imgBoard/updatePageImgBoard";
	}

	/*
	 * 수정페이지 
	 */
	@RequestMapping(value="/imgBoard/updElement.do", method = RequestMethod.GET)
	
	public String updatePageImgBoard(HttpServletRequest request
									, @RequestParam(value="imgBoardId", required=false) int imgBoardId,  Model model) throws Exception{
									
		ImgBoardVO boardInfo = imgBoardService.getImgBoardDetailInfo(imgBoardId);
		List<HashTagVO> hashTag = hashTagService.getHashTagDetailInfo(imgBoardId);
		List<ImgVO> imgInfo = imgService.getImgDetailInfo(imgBoardId);
			
		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("hashTag",hashTag);
		model.addAttribute("imgInfo",imgInfo);
		
		return "views/imgBoard/updElement";
	}
	
	/*
	 * 수정처리 
	 */
	@RequestMapping(value="/imgBoard/update.do", method = RequestMethod.POST)
	@ResponseBody
	public String update(MultipartHttpServletRequest request
			 			, @ModelAttribute ImgBoardVO imgBoardVO
			 			, @ModelAttribute HashTagVO hashTagVO
			 			, @RequestParam(value="originImgId", required = false) List<String> originImgId) throws Exception{
		
		HttpSession session = request.getSession(false);

		String userId = (String) session.getAttribute("id");

		
		/*게시글 수정력(제목,내용)*/
		imgBoardVO.setImgBoardWriter(userId);		
		imgBoardService.updateContents(imgBoardVO);
		
		/*게시글 입력(해시태그)*/
		hashTagVO.setHashTagWriter(userId);
		
		List<String>hashTagList = hashTagVO.getHashTagList();
		
		hashTagVO.setHashTagWriter(userId);
		
		if(hashTagList.size() != 0 && hashTagList != null) {
			
			
			String hashTagValue [] = hashTagList.get(0).split(",") ;
					
			List<String>hashTag = hashTagVO.getHashTag();

			for(int i=0; i<hashTagValue.length; i++) {
				hashTag.add(hashTagValue[i]);
			}
				
			hashTag.remove(null);
			hashTag.remove("");
			System.out.println("ListHashTga::::::::::"+hashTag);
			
			int imgBoardId = hashTagVO.getImgBoardId();
			System.out.println(":::::::::" + imgBoardId);
			hashTagService.delHashTag(imgBoardId);
			
			hashTagVO.setHashTagWriter(userId);
			System.out.println("hashTagVO:::::::::::::" + hashTagVO);

			hashTagService.insHashTag(hashTagVO);
		}
//		List<String>hashTag = hashTagVO.getHashTag();
//		List<Integer>hashTagId = hashTagVO.getListHashTagId();
//		List<HashTagUpdateVO> hashTagList = new ArrayList<>(); 
//		
//		for(int i=0; i<hashTagId.size(); i++) {
//			HashTagUpdateVO updateVo = new HashTagUpdateVO();
//			updateVo.setHashTagId(hashTagId.get(i));
//			updateVo.setHashTagContent(hashTag.get(i));
//			
//			hashTagList.add(updateVo);
//		}
		
		/*이미지 수정*/
		List<MultipartFile> updFiles = request.getFiles("updateImg");
		updFiles.remove(null);
		List<MultipartFile> insFiles = request.getFiles("imgae");
		insFiles.remove(null);
		
		String uploadRoot = request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/"); // 서버의 실제경로를 받아옴
		String uploadContext = "/upload";
		String uploadFolder = uploadRoot + uploadContext;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();		
		String toDay = sdf.format(date);

		/* 날짜 구분자('-') -> 경로구분자('\')
		 * str 변수의 값의 문자열 중 '-'을 String 클래스의 replace 메서드를 사용하여 File.separator로 변경해주는 코드를 작성
		 * */

		String dataPath = toDay.replace("-", "/");

		/*폴더 생성*/
		File uploadPath = new File(uploadFolder, dataPath);
		// 만약 폴더가 존재하지 않는다면 폴더를 생성하도록 
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
	
		List<ImgVO> imageInfo = new ArrayList<>();	
		List<Integer> imgIds = new ArrayList<>();
		
		//수정하는 board 삭제여부 Y로 업데이트
		if(updFiles.size() != 0 || !updFiles.isEmpty() || updFiles != null) {
			for(MultipartFile multipartFile : updFiles) {
				/* 파일 이름*/
				//입력한 이미지+텍스트 데이터받아오기
				ImgVO imgVO = new ImgVO();
				
				String imgOriginName = multipartFile.getOriginalFilename();
				
				int startIndex;
				startIndex = multipartFile.getContentType().indexOf('/')+1;	
				String format;
				format = multipartFile.getContentType().substring(startIndex);
	
				String uuid = UUID.randomUUID().toString();
				
				String imgSaveName = uuid + "." + format;
	
				long size= multipartFile.getSize();
	
				/* 파일 위치, 파일 이름을 합친 File 객체*/
				File saveFile = new File(uploadPath, imgSaveName);
				
				String saveFolder = uploadFolder.replaceAll(uploadRoot, "");
	
				imgVO.setImgOriginName(imgOriginName);
				imgVO.setImgSaveName(imgSaveName);
				imgVO.setImgFormat(format);
				imgVO.setImgSize(size);
				imgVO.setImgWriter(userId);
				imgVO.setImgSavePath(saveFolder+ "/" +  dataPath);
				imageInfo.add(imgVO);
				
				multipartFile.transferTo(saveFile);
				
				//썸네일
				String thumbPath = uploadFolder + "/" + "resize";
				//File thumbNailFile = new File(uploadFolder, "s_" + imgSaveName);
				File thumbNailFile = new File(thumbPath, imgSaveName);
				BufferedImage bufImg = ImageIO.read(saveFile);
				//썸네일 크기및 색상정보 설정
				BufferedImage bufImgInfo= new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
				//그리기
				Graphics2D graphic = bufImgInfo.createGraphics();
				
				graphic.drawImage(bufImg, 0, 0, 400, 600, null);
				
				ImageIO.write(bufImg,"jpg" ,thumbNailFile);	
				
				
				}
				if(originImgId.size()!=0 && originImgId != null) {
				String originId [] = originImgId.get(0).split(",");
				for(int i=0; i<originId.length; i++) {
					imgIds.add(Integer.parseInt(originId[i]));
				}
			imgService.delImgMulti(imgIds);
			}
		//imgService.insImg(imageInfo);
		}
		if(insFiles.size() != 0  || !insFiles.isEmpty() || insFiles != null) {
			for(MultipartFile multipartFile : insFiles) {
				/* 파일 이름*/
				//입력한 이미지+텍스트 데이터받아오기
				ImgVO imgVO = new ImgVO();
				
				String imgOriginName = multipartFile.getOriginalFilename();
				
				int startIndex;
				startIndex = multipartFile.getContentType().indexOf('/')+1;	
				String format;
				format = multipartFile.getContentType().substring(startIndex);

				String uuid = UUID.randomUUID().toString();
				
				String imgSaveName = uuid + "." + format;

				long size= multipartFile.getSize();

				/* 파일 위치, 파일 이름을 합친 File 객체*/
				File saveFile = new File(uploadPath, imgSaveName);
				
				String saveFolder = uploadFolder.replaceAll(uploadRoot, "");

				imgVO.setImgOriginName(imgOriginName);
				imgVO.setImgSaveName(imgSaveName);
				imgVO.setImgFormat(format);
				imgVO.setImgSize(size);
				imgVO.setImgWriter(userId);
				imgVO.setImgSavePath(saveFolder+ "/" +  dataPath);
				imageInfo.add(imgVO);
				
				multipartFile.transferTo(saveFile);
				
				//썸네일
				String thumbPath = uploadFolder + "/" + "resize";
				//File thumbNailFile = new File(uploadFolder, "s_" + imgSaveName);
				File thumbNailFile = new File(thumbPath, imgSaveName);
				BufferedImage bufImg = ImageIO.read(saveFile);
				//썸네일 크기및 색상정보 설정
				BufferedImage bufImgInfo= new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
				//그리기
				Graphics2D graphic = bufImgInfo.createGraphics();
				
				graphic.drawImage(bufImg, 0, 0, 400, 600, null);
				
				ImageIO.write(bufImg,"jpg" ,thumbNailFile);	
				}
			System.out.println("::::::::2::::::::::::" + imageInfo);
			imgService.insImg(imageInfo);
		
			int imgBoardId = imgBoardService.getMaxBoardId(userId);
			imgBoardService.insThumbNail(imgBoardId);
		}
		return "success";
	}
	
	/*
	 * 게시글 삭제
	 */
	@RequestMapping(value="/imgBoard/delImgBoard.do", method = RequestMethod.POST)
	@ResponseBody
	public String delImgBoard (ImgBoardVO imgBoardVo) throws Exception {
		
		int imgBoardId = imgBoardVo.getImgBoardId();
		
		imgBoardService.delImgBoard(imgBoardId);
		
		return "views/imgBoard/imgBoardList";
	}
	
	/*
	 * 이미지 삭제
	 */
	@RequestMapping(value="/imgBoard/delImg.do", method = RequestMethod.POST)
	@ResponseBody
	public String delImg (int imgId, int imgBoardId) throws Exception {
		
		imgService.delImg(imgId);
		imgBoardService.insThumbNail(imgBoardId);
		
		return "views/imgBoard/imgBoardList";
	}
	
}